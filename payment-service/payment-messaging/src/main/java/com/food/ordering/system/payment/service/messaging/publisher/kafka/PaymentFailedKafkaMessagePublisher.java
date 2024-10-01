package com.food.ordering.system.payment.service.messaging.publisher.kafka;

import org.springframework.stereotype.Component;

import com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.food.ordering.system.kafka.producer.KafkaMessageHelper;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import com.food.ordering.system.payment.service.domain.config.PaymentServiceConfigData;
import com.food.ordering.system.payment.service.domain.event.PaymentFailedEvent;
import com.food.ordering.system.payment.service.domain.ports.output.message.publisher.PaymentFailedMessagePublisher;
import com.food.ordering.system.payment.service.messaging.mapper.PaymentMessagingDataMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PaymentFailedKafkaMessagePublisher implements PaymentFailedMessagePublisher {

    private final PaymentMessagingDataMapper paymentMessagingDataMapper;
    private final KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer;
    private final PaymentServiceConfigData paymentServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public PaymentFailedKafkaMessagePublisher(PaymentMessagingDataMapper paymentMessagingDataMapper,
            KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer,
            PaymentServiceConfigData paymentServiceConfigData, KafkaMessageHelper kafkaMessageHelper) {
        this.paymentMessagingDataMapper = paymentMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.paymentServiceConfigData = paymentServiceConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(PaymentFailedEvent paymentFailedEvent) {
        final var payment = paymentFailedEvent.getPayment();
        final var orderId = payment.getOrderId().getValue().toString();

        log.info("Received PaymentFailedEvent for order id: {} ", orderId);

        try {
            final var paymentResponseAvroModel = paymentMessagingDataMapper
                    .paymentFailedEventToPaymentResponseAvroModel(paymentFailedEvent);

            kafkaProducer.send(paymentServiceConfigData.getPaymentResponseTopicName(), orderId,
                    paymentResponseAvroModel,
                    kafkaMessageHelper.getKafkaCallback(
                            paymentServiceConfigData.getPaymentResponseTopicName(),
                            paymentResponseAvroModel,
                            orderId,
                            "PaymentResponseAvroModel"));

            log.info("PaymentResponseAvroModel sent to kafka for order id: {}", orderId);
        } catch (Exception e) {
            log.error(
                    "Error while sending PaymentResponseAvroModel message to kafka with order id: {}, error: {}",
                    orderId,
                    e.getMessage(),
                    e);
        }
    }

}
