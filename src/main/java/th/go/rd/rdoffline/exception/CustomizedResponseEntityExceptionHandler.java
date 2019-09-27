package th.go.rd.rdoffline.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);
	@Value("${app-stacktrace-enable}")
	private boolean stacktraceEnable;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		List<ApiGlobalError> apiGlobalErrors = new ArrayList<ApiGlobalError>();
		ApiGlobalError apiGlobalError = null;
		
		if (stacktraceEnable) {
			String stackTrace = ExceptionUtils.getStackTrace(ex);
			apiGlobalError = new ApiGlobalError(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(),
					stackTrace);
		} else {
			apiGlobalError = new ApiGlobalError(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
		}
		apiGlobalErrors.add(apiGlobalError);
		ApiErrorsView apiErrorsView = new ApiErrorsView(null, apiGlobalErrors);
		printStackTrace(ex, request, apiErrorsView);
		return new ResponseEntity<>(apiErrorsView, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
		List<ApiGlobalError> apiGlobalErrors = new ArrayList<ApiGlobalError>();
		ApiGlobalError apiGlobalError = null;
		if (stacktraceEnable) {
			String stackTrace = ExceptionUtils.getStackTrace(ex);
			apiGlobalError = new ApiGlobalError(HttpStatus.NOT_FOUND.toString(), ex.getMessage(), stackTrace);
		} else {
			apiGlobalError = new ApiGlobalError(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
		}
		apiGlobalErrors.add(apiGlobalError);
		ApiErrorsView apiErrorsView = new ApiErrorsView(null, apiGlobalErrors);
		printStackTrace(ex, request, apiErrorsView);
		return new ResponseEntity<>(apiErrorsView, HttpStatus.NOT_FOUND);
	}

//	@ExceptionHandler({ SQLException.class, DataAccessException.class })
//	public final ResponseEntity<Object> handleDBExceptions(Exception ex, WebRequest request) {
//		List<ApiGlobalError> apiGlobalErrors = new ArrayList<ApiGlobalError>();
//		ApiGlobalError apiGlobalError = null;
//		if (stacktraceEnable) {
//			String stackTrace = ExceptionUtils.getStackTrace(ex);
//			apiGlobalError = new ApiGlobalError(HttpStatus.PRECONDITION_FAILED.toString(), ex.getMessage(), stackTrace);
//		} else {
//			apiGlobalError = new ApiGlobalError(HttpStatus.PRECONDITION_FAILED.toString(), ex.getMessage());
//		}
//		apiGlobalErrors.add(apiGlobalError);
//		ApiErrorsView apiErrorsView = new ApiErrorsView(null, apiGlobalErrors);
//		printStackTrace(ex, request, apiErrorsView);
//		return new ResponseEntity<>(apiErrorsView, HttpStatus.PRECONDITION_FAILED);// 412 Pre condition fail
//	}

	@ExceptionHandler({ RestException.class })
	public final ResponseEntity<Object> handleDBExceptions(RestException ex, WebRequest request) {
		List<ApiGlobalError> apiGlobalErrors = ex.getApiGlobalErrors();
		List<ApiFieldError> apiFieldErrors = ex.getApiFieldErrors();
		List<Integer> stateTracking = ex.getStateTracking();
		ApiErrorsView apiErrorsView = new ApiErrorsView(apiFieldErrors, apiGlobalErrors, stateTracking);
		printStackTrace(ex, request, apiErrorsView);
		return new ResponseEntity<>(apiErrorsView, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();

		List<ApiFieldError> apiFieldErrors = bindingResult.getFieldErrors().stream()
				.map(fieldError -> new ApiFieldError(
						String.format("%1$s.%2$s", fieldError.getObjectName(), fieldError.getField()),
						fieldError.getCode(), fieldError.getDefaultMessage(), fieldError.getRejectedValue()))
				.collect(toList());

		List<ApiGlobalError> apiGlobalErrors = bindingResult.getGlobalErrors().stream()
				.map(globalError -> new ApiGlobalError(globalError.getCode(), globalError.getDefaultMessage()))
				.collect(toList());

		ApiErrorsView apiErrorsView = new ApiErrorsView(apiFieldErrors, apiGlobalErrors);
		
		printStackTrace(ex, request, apiErrorsView);
		return new ResponseEntity<>(apiErrorsView, HttpStatus.BAD_REQUEST);
	}

	private void printStackTrace(Exception ex, WebRequest request, ApiErrorsView apiErrorsView) {

		StackTraceElement[] stackT = ex.getStackTrace();
		List<String> stackDeatial = new ArrayList<String>();
		for (int i = 0; i < stackT.length; i++) {
			stackDeatial.add(stackT[i].toString());
		}
		
		String jti = "";
		String userName = "";
		/*if (null != authenticationFacade && null != authenticationFacade.getDataLogin()) {
			jti = authenticationFacade.getDataLogin().getJti();
			userName = authenticationFacade.getDataLogin().getUserName();
		}*/

//		HashMap<String, String> paramsData = new HashMap<String, String>();
//		Map<String, String[]> map = request.getParameterMap();
//		for (Map.Entry<String, String[]> entry : map.entrySet()) {
//			paramsData.put(entry.getKey(), (Arrays.toString(entry.getValue())));
//		}
		
		String ipAddress = request.getHeader("X-Forwarded-For");
		if (ipAddress == null) {
			ipAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();
		}
			
		logger.error("IP:{} USERNAME:{} JIT:{} REQUEST:{} ERROR_CLASS:{} EXCEPTION:{} DISPLAY_ERROR_MSG:{}"
				,ipAddress
				,userName
				,jti
				,convertToJson(request.getParameterMap())				
				,stackT[1].getFileName()//,ex.getStackTrace()[1].getFileName()				
				,ex.getStackTrace()//,stackDeatial
				,convertToJson(apiErrorsView));
	}

	public static String convertToJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "convertToJson is fail";
		}
	}
}
