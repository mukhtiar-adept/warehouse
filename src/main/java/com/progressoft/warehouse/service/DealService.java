package com.progressoft.warehouse.service;

import com.progressoft.warehouse.model.Deal;

/**
 *
 * @author  mukhtiar.ahmed
 * version 1.0
 */
public interface DealService {

    void processDeal(Deal deal);

    void create(Deal deal);
}
