package org.example.kafkaproducer.contollers;

import lombok.RequiredArgsConstructor;
import org.example.kafkaproducer.models.InputRequest;
import org.example.kafkaproducer.services.KeyboardInputService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/input")
@RequiredArgsConstructor
public class KeyboardInputController {

    private final KeyboardInputService keyboardInputService;

    @PostMapping
    public ResponseEntity<Void> sendInput(@RequestBody InputRequest inputRequest) {
        keyboardInputService.processInput(inputRequest.getIpAddress(), inputRequest.getMessage());
        return ResponseEntity.noContent().build();
    }

}
