package com.progressoft.warehouse.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.progressoft.warehouse.dto.DealDTO;
import com.progressoft.warehouse.entity.DealEntity;
import com.progressoft.warehouse.serializer.LocalDateTimeDeserializer;
import com.progressoft.warehouse.serializer.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 *
 * @author  mukhtiar.ahmed
 * version 1.0
 */
@Data
@NoArgsConstructor
public class Deal implements Serializable {

    private String id;
    private String fromCurrencyISOCode;
    private String toCurrencyISOCode;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dealTime;
    private BigDecimal amount;


    public DealEntity toEntity() {
        DealEntity entity = new DealEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    public DealDTO toDTO() {
        DealDTO dto = new DealDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }


}
