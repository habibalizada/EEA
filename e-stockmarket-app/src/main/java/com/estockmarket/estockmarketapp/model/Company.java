package com.estockmarket.estockmarketapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "code", name = "unique_code")})
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull(message = "Code is required")
    @Column(name = "code", length = 200, unique = true)
    private String code;
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "CEO is required")
    private String ceo;
    @NotNull(message = "Turnover is required")
    @Min(value = 10000000, message = "Turnover should be over 10cr")
    private BigDecimal turnover;
    @NotNull(message = "Website is required")
    private String website;
    @NotNull(message = "Stock Exchange is required")
    private String enlistedStockExchange;
}
