package application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotConsistentException extends Exception {

    public NotConsistentException(){}

    public NotConsistentException(String message) {
        super(message);
    }
}
