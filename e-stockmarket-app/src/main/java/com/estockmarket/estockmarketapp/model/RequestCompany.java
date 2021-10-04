package com.estockmarket.estockmarketapp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestCompany {

    private long id;
    private String code;
    private String name;
    private String ceo;
    private BigDecimal turnover;
    private String website;
    private String enlistedStockExchange;
}
