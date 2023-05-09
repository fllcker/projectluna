package ru.fllcker.authservice.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestTemplateErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<ErrorDetails> handleHttpClientErrorException(HttpClientErrorException ex) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setType("about:blank");
        errorDetails.setTitle(ex.getStatusText());
        errorDetails.setStatus(ex.getStatusCode().value());
        errorDetails.setDetail(ex.getResponseBodyAsString());
        errorDetails.setInstance(ex.getResponseBodyAsString());
        return ResponseEntity.status(ex.getStatusCode()).body(errorDetails);
    }
}

