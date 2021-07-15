package org.example.kafkaproducer.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {

    private int status;
    private String statusText;
    private String message;

}
