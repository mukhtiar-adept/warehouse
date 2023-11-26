package com.progressoft.warehouse.repository;

import com.progressoft.warehouse.entity.CurrencyCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author  mukhtiar.ahmed
 * version 1.0
 */
@Repository
public interface CurrencyCodeRepository extends JpaRepository<CurrencyCodeEntity, Long> {

    boolean existsByIsoCode(String isoCode);


}
