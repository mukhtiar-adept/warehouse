package com.progressoft.warehouse.consumer;

import com.progressoft.warehouse.annotations.LogMethod;
import com.progressoft.warehouse.config.RabbitMqConfig;
import com.progressoft.warehouse.model.Deal;
import com.progressoft.warehouse.service.DealService;
import com.progressoft.warehouse.util.WarehouseHealper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMQConsumer {


    private final DealService dealService;

    @PostConstruct
    public void checkConfiguration() {
        WarehouseHealper.checkConfigNotNull(dealService, "dealService");
    }

    @RabbitListener(queues = RabbitMqConfig.DEAL_QUEUE)
    @LogMethod
    public void recievedMessage(Deal deal) {
        System.out.println("Received Message From RabbitMQ: " + deal);
        dealService.create(deal);
    }
}
