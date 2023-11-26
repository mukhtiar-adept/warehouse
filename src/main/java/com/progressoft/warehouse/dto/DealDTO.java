package com.progressoft.warehouse.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.progressoft.warehouse.model.Deal;
import com.progressoft.warehouse.serializer.LocalDateTimeDeserializer;
import com.progressoft.warehouse.serializer.LocalDateTimeSerializer;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealDTO {

    @NotBlank
    private String id;
    @Size(min = 3, max = 3)
    private String fromCurrencyISOCode;
    @Size(min = 3, max = 3)
    private String toCurrencyISOCode;
    @NotNull
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dealTime;
    @NotNull
    private BigDecimal amount;


    public Deal toModel() {
        Deal model = new Deal();
        BeanUtils.copyProperties(this, model);
        return model;
    }

}
