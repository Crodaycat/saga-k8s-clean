package com.food.ordering.system.kafka.producer.service;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.Serializable;

public interface KafkaProducer<Key extends Serializable, Value extends SpecificRecordBase> {
    void send(String topicName, Key key, Value message, ListenableFutureCallback<SendResult<Key, Value>> callback);
}
