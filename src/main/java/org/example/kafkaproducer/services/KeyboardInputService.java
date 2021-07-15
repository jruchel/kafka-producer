package org.example.kafkaproducer.services;

import lombok.RequiredArgsConstructor;
import org.example.kafkaproducer.producers.KafkaProducer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeyboardInputService {
    private final KafkaProducer producer;

    public void processInput(String ip, String message) {
        producer.sendMessage(ip, message);
    }

}
