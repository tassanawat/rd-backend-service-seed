package th.go.rd.rdoffline.model;

import th.go.rd.rdoffline.model.comon.ResponseData;

import java.io.Serializable;

public class ApiView implements Serializable {

	private static final long serialVersionUID = -3459649314460725723L;
	private ResponseData response = new ResponseData();

	public ApiView(int code) {
		this.response.setResponseCode(code);
	}

	public ResponseData getResponse() {
		return response;
	}

	// private int code=0;// 0:SUCCESS , 1:ERROR
	//
	// public ApiView(int code) {
	// this.code = code;
	// }
	//
	// public int getCode() {
	// return code;
	// }
}
