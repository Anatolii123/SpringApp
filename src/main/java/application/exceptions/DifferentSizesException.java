package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DifferentSizesException extends Exception {

    public DifferentSizesException(){}

    public DifferentSizesException(String message) {
        super(message);
    }
}
