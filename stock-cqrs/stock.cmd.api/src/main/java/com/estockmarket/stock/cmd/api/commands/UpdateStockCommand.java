package com.estockmarket.stock.cmd.api.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStockCommand {

    @TargetAggregateIdentifier
    private String id;
    @NotNull(message = "Company code must not be null")
    private String companyCode;
}
