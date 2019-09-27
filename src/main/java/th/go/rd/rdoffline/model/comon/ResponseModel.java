package th.go.rd.rdoffline.model.comon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ResponseModel {

	private String groupCode;
	private Object data;
	private static MessageSource message;
	
	@Autowired
	private MessageSource messageSource;
	
	@PostConstruct
	public void init() {
		message = this.messageSource;
	}

	public static ResponseEntity<ResponseModel> success() {
		return success(null);
	}

	public static ResponseEntity<ResponseModel> success(Object data) {
		return new ResponseEntity<ResponseModel>(new ResponseModel().setData(data), HttpStatus.OK);
	}
	
	public static ResponseEntity<ResponseModel> serverError() {
		String errorMessage = message.getMessage("msg.rest.err.general", new Object[0], LocaleContextHolder.getLocale());
		return serverError(errorMessage);
	}

	public static ResponseEntity<ResponseModel> serverError(Object data) {
		return new ResponseEntity<ResponseModel>(new ResponseModel().setData(data), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public static ResponseEntity<ResponseModel> badRequest(Object data) {
		return new ResponseEntity<ResponseModel>(new ResponseModel().setData(data), HttpStatus.BAD_REQUEST);
	}
	
	public static ResponseEntity<ResponseModel> preConditionFailed(Object data) {
		return new ResponseEntity<ResponseModel>(new ResponseModel().setData(data), HttpStatus.PRECONDITION_FAILED);
	}
	
	public Object getData() {
		return data;
	}

	public ResponseModel setData(Object data) {
		this.data = data;
		return this;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public ResponseModel setGroupCode(String groupCode) {
		this.groupCode = groupCode;
		return this;
	}
}
