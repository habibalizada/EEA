package com.estockmarket.stock.cmd.api.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateStockCommand {

    @TargetAggregateIdentifier
    private String id;
    private String companyCode;
    @NotNull(message = "Stock price must not be null")
    private BigDecimal stockPrice;
}
