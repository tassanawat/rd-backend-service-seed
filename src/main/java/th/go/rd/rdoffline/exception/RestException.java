package th.go.rd.rdoffline.exception;

import java.util.List;

public class RestException extends RuntimeException {

	private static final long serialVersionUID = -2514960608662013704L;

	private List<ApiGlobalError> apiGlobalErrors;
	private List<ApiFieldError> apiFieldErrors;
	private List<Integer> stateTracking;

	public RestException(List<ApiGlobalError> apiGlobalErrors, List<ApiFieldError> apiFieldErrors, List<Integer> stateTracking) {
		super("");
		this.apiGlobalErrors = apiGlobalErrors;
		this.apiFieldErrors = apiFieldErrors;
		this.stateTracking = stateTracking;
	}

	public List<ApiGlobalError> getApiGlobalErrors() {
		return apiGlobalErrors;
	}

	public List<ApiFieldError> getApiFieldErrors() {
		return apiFieldErrors;
	}

	public List<Integer> getStateTracking() {
		return stateTracking;
	}
	
	// Collection Exception
	/*
	 * AppException("1000::ERROR1","1001::ERROR2","1002::","1003:","1004","::1005")
	 * throw new RestException(errorMSG1, errorMSG2, errorMSG3, errorMSG4);
	 * error = "CODE::MESSAGE,"
	 */
	// public RestException(String... errors) {
	// super("");
	// List<ApiGlobalError> apiGlobalErrors = Arrays.asList(errors).stream().map(s
	// -> s.split("::", 2)).limit(2)
	// .map(a -> new ApiGlobalError(a[0], a[1])).collect(Collectors.toList());
	// this.apiGlobalErrors = apiGlobalErrors;
	// }
	//
	// public RestException(String errorCode, String errorMessage) {
	// super("");
	// List<ApiGlobalError> apiGlobalErrors = Arrays.asList(new
	// ApiGlobalError(errorCode, errorMessage));
	// this.apiGlobalErrors = apiGlobalErrors;
	// }
	//
	// public RestException(List<ApiGlobalError> apiGlobalErrors) {
	// super("");
	// this.apiGlobalErrors = apiGlobalErrors;
	// }

}
