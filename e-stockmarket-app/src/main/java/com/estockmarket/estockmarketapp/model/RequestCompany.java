package com.estockmarket.estockmarketapp.model;

import lombok.*;

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
