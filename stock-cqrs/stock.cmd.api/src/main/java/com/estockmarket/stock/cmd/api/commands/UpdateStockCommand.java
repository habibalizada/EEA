package com.estockmarket.stock.cmd.api.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStockCommand {

    @TargetAggregateIdentifier
    private int id;
    private String companyCode;
}
