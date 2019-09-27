package th.go.rd.rdoffline.exception;


import th.go.rd.rdoffline.model.ApiView;

import java.util.List;

public class ApiErrorsView extends ApiView {

	private static final long serialVersionUID = 8309912861269565591L;
	
	private List<ApiFieldError> fieldErrors;
	private List<ApiGlobalError> globalErrors;
	private List<Integer> stateTracking;

	public ApiErrorsView(List<ApiFieldError> fieldErrors, List<ApiGlobalError> globalErrors) {
		super(1); //FAIL
		this.fieldErrors = fieldErrors;
		this.globalErrors = globalErrors;
	}

	public ApiErrorsView(List<ApiFieldError> fieldErrors, List<ApiGlobalError> globalErrors, List<Integer> stateTracking) {
		super(1); //FAIL
		this.fieldErrors = fieldErrors;
		this.globalErrors = globalErrors;
		this.stateTracking = stateTracking;
	}
	
	public List<ApiFieldError> getFieldErrors() {
		return fieldErrors;
	}

	public List<ApiGlobalError> getGlobalErrors() {
		return globalErrors;
	}

	public List<Integer> getStateTracking() {
		return stateTracking;
	}
}
