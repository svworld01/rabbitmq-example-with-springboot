package com.saurabh.rappidmq.publisher;

import com.saurabh.rappidmq.config.MessagingConfig;
import com.saurabh.rappidmq.dto.Order;
import com.saurabh.rappidmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @GetMapping("/hello")
    public String hello(){
        return "Hello from Order Service";
    }

    @PostMapping("/rest/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName){
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "Order placed successfully into "+restaurantName);
        template.convertAndSend(MessagingConfig.EXCHANGE,MessagingConfig.ROUTING_KEY, orderStatus);
        return "Order Placed";
    }
}
