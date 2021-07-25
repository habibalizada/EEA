package com.estockmarket.estockmarketapp.model;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

//@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "code", name = "unique_code")})
//@Table(name = "company")
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
    @NotNull(message = "Code is mandatory")
//    @Size(max=100)
//    @UniqueElements(message = "Code should be unique")
    private String code;
    @NotNull(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Ceo is mandatory")
    private String ceo;
    @NotNull(message = "Turnover is mandatory")
    @DecimalMin(value = "10000000", inclusive = true)
    private BigDecimal turnover;
    @NotNull(message = "Website is mandatory")
    private String website;
    @NotNull(message = "Enlisted stock exchange is mandatory")
    private String enlistedStockExchange;
}
