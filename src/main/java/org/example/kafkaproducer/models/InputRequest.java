package org.example.kafkaproducer.models;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

@Data
@Validated
public class InputRequest {

    @Pattern(regexp = "(?:[0-9]{1,3}\\.){3}[0-9]{1,3}")
    private String ipAddress;
    private String message;

}
