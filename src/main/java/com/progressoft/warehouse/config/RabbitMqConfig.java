package com.progressoft.warehouse.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String DEAL_QUEUE = "deal_queue_fanout";

    public static final String DEAL_EXCHANGE = "warehouse_exchange";

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(DEAL_EXCHANGE);
    }

    @Bean
    Queue dealQueue() {
        return new Queue(DEAL_QUEUE, true);
    }

    @Bean
    Binding deliveryBinding(Queue dealQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(dealQueue).to(exchange);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
