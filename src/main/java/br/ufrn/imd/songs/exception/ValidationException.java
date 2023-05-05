package br.ufrn.imd.songs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    public ValidationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ValidationException(String msg) {
        super(msg);
    }
}
