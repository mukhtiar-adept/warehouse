package com.progressoft.warehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author  mukhtiar.ahmed
 * version 1.0
 */
@Entity
@Data
@Table(name = "currency_codes")
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyCodeEntity extends AuditingEntity {


    @Id
    @GeneratedValue(
        strategy= GenerationType.AUTO,
        generator="native"
    )
    @GenericGenerator(
        name = "native",
        strategy = "native"
    )
    private Long id;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @Column(name = "currency_name", nullable = false)
    private String currencyName;

    @Size(min = 3, max = 3)
    @Column(name = "iso_code", nullable = false, length=3)
    private String isoCode;

}
