package th.go.rd.rdoffline.model;

public class ApiSuccessView extends ApiView {

	private static final long serialVersionUID = 2080798628377525336L;

	private Object data;

	public ApiSuccessView(Object resp) {
		super(0); //SUCCESS
		this.data = resp;
	}

	public Object getData() {
		return data;
	}

}
