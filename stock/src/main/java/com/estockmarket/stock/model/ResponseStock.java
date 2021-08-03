package com.estockmarket.stock.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStock {
    private List<Stock> stocks;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private BigDecimal avePrice;
}
