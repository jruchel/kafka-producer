package org.example.kafkaproducer.producers;

import lombok.RequiredArgsConstructor;
import org.example.kafkaproducer.configs.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> template;
    private final KafkaConfig config;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void sendMessage(String key, String value) {
        ListenableFuture<SendResult<String, String>> future = template.send(config.getTopic(), key, value);
        future.addCallback(getCallback());

    }

    private ListenableFutureCallback<SendResult<String, String>> getCallback() {
        return new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.error(ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info(String.format("Successfully sent: [%s:%s", result.getProducerRecord().key(), result.getProducerRecord().value()));
            }
        };
    }

}
