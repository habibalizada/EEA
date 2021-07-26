package com.estockmarket.estockmarketapp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "company")
public class Company {

    @Transient
    public static final String SEQUENCE_NAME = "companys_sequence";
    @Id
    private long id;
    @NotEmpty(message = "Code is mandatory")
    private String code;
    @NotEmpty(message = "Name is mandatory")
    private String name;
    @NotEmpty(message = "Ceo is mandatory")
    private String ceo;
    @NotNull(message = "Turnover is mandatory")
    @DecimalMin(value = "10000000", inclusive = true)
    private BigDecimal turnover;
    @NotEmpty(message = "Website is mandatory")
    private String website;
    @NotEmpty(message = "Enlisted stock exchange is mandatory")
    private String enlistedStockExchange;
}
