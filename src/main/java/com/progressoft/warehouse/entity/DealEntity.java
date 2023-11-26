package com.progressoft.warehouse.entity;

import com.progressoft.warehouse.model.Deal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 *
 * @author  mukhtiar.ahmed
 * version 1.0
 */
@Entity
@Data
@Table(name = "deals")
@NoArgsConstructor
public class DealEntity extends AuditingEntity {

    @Id
    private String id;

    @Column(name = "from_currency_iso_code", nullable = false, length=3)
    @Size(min = 3, max = 3)
    private String fromCurrencyISOCode;

    @Column(name = "to_currency_iso_code", nullable = false, length=3)
    @Size(min = 3, max = 3)
    private String toCurrencyISOCode;

    @Column(name = "deal_time", nullable = false)
    private LocalDateTime dealTime;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    public Deal toModel() {
        Deal model = new Deal();
        BeanUtils.copyProperties(this, model);
        return model;
    }

}
