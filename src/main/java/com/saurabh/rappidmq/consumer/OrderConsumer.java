package com.saurabh.rappidmq.consumer;

import com.saurabh.rappidmq.config.MessagingConfig;
import com.saurabh.rappidmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class OrderConsumer {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) throws InterruptedException {
        TimeUnit.MINUTES.sleep(1);
        System.out.println("Message Received from Queue : "+ orderStatus);
    }
}
