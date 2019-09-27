package th.go.rd.rdoffline.model.comon;

public class ResponseData {
	private int ResponseCode=0;// 0:SUCCESS , 1:ERROR
	private String ResponseDesc="";


	public int getResponseCode() {
		return ResponseCode;
	}
	public void setResponseCode(int responseCode) {
		ResponseCode = responseCode;
	}
	public String getResponseDesc() {
		return ResponseDesc;
	}
	public void setResponseDesc(String responseDesc) {
		ResponseDesc = responseDesc;
	}
	
	
}
