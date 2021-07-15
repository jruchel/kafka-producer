package org.example.kafkaproducer.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.nio.charset.Charset;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<ExceptionResponse> httpStatusErrors(HttpStatusCodeException throwable) {
        ExceptionResponse response = ExceptionResponse.builder()
                .message(throwable.getMessage())
                .status(throwable.getStatusCode().value())
                .statusText(throwable.getStatusText()).build();
        return ResponseEntity.status(throwable.getStatusCode()).body(response);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionResponse> anyError(Throwable throwable) {
        HttpClientErrorException exception = HttpClientErrorException
                .create(throwable.getMessage(),
                        HttpStatus.CONFLICT,
                        "Conflict",
                        new HttpHeaders(), null, Charset.defaultCharset());

        return httpStatusErrors(exception);
    }

}
