package com.progressoft.warehouse.service;

import com.progressoft.warehouse.annotations.LogMethod;
import com.progressoft.warehouse.config.RabbitMqConfig;
import com.progressoft.warehouse.exception.EntityAlreadyExistsException;
import com.progressoft.warehouse.exception.InvalidDataException;
import com.progressoft.warehouse.model.Deal;
import com.progressoft.warehouse.repository.CurrencyCodeRepository;
import com.progressoft.warehouse.repository.DealRepository;
import com.progressoft.warehouse.util.WarehouseHealper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
/**
 *
 * @author  mukhtiar.ahmed
 * version 1.0
 */
@Service
@AllArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;

    private final CurrencyCodeRepository currencyCodeRepository;

    private final AmqpTemplate amqpTemplate;

    @PostConstruct
    public void checkConfiguration() {
        WarehouseHealper.checkConfigNotNull(dealRepository, "dealRepository");
        WarehouseHealper.checkConfigNotNull(currencyCodeRepository, "currencyCodeRepository");
        WarehouseHealper.checkConfigNotNull(amqpTemplate, "amqpTemplate");
    }

    @LogMethod
    public void processDeal(Deal deal) {

        // Check valid iso currency code
        if (!currencyCodeRepository.existsByIsoCode(deal.getFromCurrencyISOCode())) {
            throw new InvalidDataException("Deal with invalid from currency iso code.");
        }

        // Check valid iso currency code
        if (!currencyCodeRepository.existsByIsoCode(deal.getToCurrencyISOCode())) {
            throw new InvalidDataException("Deal with invalid to currency iso code.");
        }

        // Check if the deal with the same unique id already exists
        if (dealRepository.existsById(deal.getId())) {
            throw new EntityAlreadyExistsException("Deal with the same unique id already exists.");
        }

        amqpTemplate.convertAndSend(RabbitMqConfig.DEAL_EXCHANGE, deal);

        // Save the deal to the database
        dealRepository.save(deal.toEntity());
    }

    @LogMethod
    public void create(Deal deal) {
        dealRepository.save(deal.toEntity());
    }

}
