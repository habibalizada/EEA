package com.estockmarket.stock.cmd.api.aggregates;

import com.estockmarket.stock.cmd.api.commands.CreateStockCommand;
import com.estockmarket.stock.cmd.api.commands.DeleteStockCommand;
import com.estockmarket.stock.cmd.api.commands.UpdateStockCommand;
import com.estockmarket.stock.core.events.StockCreatedEvent;
import com.estockmarket.stock.core.events.StockDeletedEvent;
import com.estockmarket.stock.core.events.StockUpdatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Aggregate
@NoArgsConstructor
public class StockAggregate {
    @AggregateIdentifier
    private int id;
    private String companyCode;
    private BigDecimal stockPrice;

    @CommandHandler
    public StockAggregate(CreateStockCommand command) {
        var event = StockCreatedEvent.builder()
                .id(command.getId())
                .companyCode(command.getCompanyCode())
                .stockPrice(command.getStockPrice())
                .createDateTime(LocalDateTime.now())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(StockCreatedEvent event) {
        this.id = event.getId();
        this.companyCode = event.getCompanyCode();
        this.stockPrice = event.getStockPrice();
    }

    @CommandHandler
    public void handle(UpdateStockCommand command) {
        var event = StockUpdatedEvent.builder()
                .id(command.getId())
                .companyCode(command.getCompanyCode())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(StockUpdatedEvent event) {
        this.companyCode = event.getCompanyCode();
    }

    @CommandHandler
    public void handle(DeleteStockCommand command) {
        var event = StockDeletedEvent.builder()
                .id(command.getId())
                .companyCode(command.getCompanyCode());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(StockDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
