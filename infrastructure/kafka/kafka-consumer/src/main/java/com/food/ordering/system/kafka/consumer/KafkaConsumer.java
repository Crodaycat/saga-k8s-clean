package com.food.ordering.system.kafka.consumer;

import org.apache.avro.specific.SpecificRecordBase;

import java.util.List;

public interface KafkaConsumer<Type extends SpecificRecordBase> {
    void receive(List<Type> messages, List<String> keys, List<Integer> partitions, List<Long> offsets);
}
