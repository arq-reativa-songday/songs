package br.ufrn.imd.songs.handler;

import br.ufrn.imd.songs.exception.NotFoundException;
import br.ufrn.imd.songs.exception.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = createErrorsList(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, status, request);
    }

    private Map<String, String> createErrorsList(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(Exception ex, WebRequest request) {
        return handleAnyException(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
        return handleAnyException(ex, request, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request, HttpStatusCode httpStatusCode) {
        String message = ex.getMessage();
        return handleExceptionInternal(ex, message, new HttpHeaders(), httpStatusCode, request);
    }
}
