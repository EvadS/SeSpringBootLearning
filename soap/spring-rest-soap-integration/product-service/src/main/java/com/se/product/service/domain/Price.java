package com.se.product.service.domain;

import com.se.product.service.domain.audit.DateAudit;
import com.se.product.service.domain.converters.CurrencyConverter;
import com.se.product.service.model.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Price extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Convert(converter = CurrencyConverter.class)
    private CurrencyType currencyType;

    private Double cost;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;
}
