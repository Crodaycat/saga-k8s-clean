package com.food.ordering.system.kafka.producer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaMessageHelper {

    public <T> ListenableFutureCallback<SendResult<String, T>> getKafkaCallback(
            String paymentResponseTopicName,
            T avroModel,
            String orderId,
            String avroModelName) {
        return new ListenableFutureCallback<SendResult<String, T>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error(
                        "Error while sending {} message {} to topic {}",
                        avroModelName,
                        avroModel.toString(),
                        paymentResponseTopicName,
                        ex);
            }

            @Override
            public void onSuccess(SendResult<String, T> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info(
                        "Received successful response from kafka for order id: {} Topic {} Partition: {} Offset: {} Timestamp {}",
                        orderId,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp());
            }
        };
    }

}
