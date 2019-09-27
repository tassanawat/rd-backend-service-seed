package th.go.rd.rdoffline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -5902194089070566061L;

	public NotFoundException(String message) {
		super(message);
	}
}
