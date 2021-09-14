package com.estockmarket.stock.query.api.handlers;

import com.estockmarket.stock.core.events.StockCreatedEvent;
import com.estockmarket.stock.core.events.StockDeletedEvent;
import com.estockmarket.stock.core.events.StockUpdatedEvent;

public interface StockEventHandler {
    void on(StockCreatedEvent event);
    void on(StockUpdatedEvent event);
    void on(StockDeletedEvent event);
}
