package com.estockmarket.stock.query.api.handlers;

import com.estockmarket.stock.core.events.StockCreatedEvent;
import com.estockmarket.stock.core.events.StockDeletedEvent;
import com.estockmarket.stock.core.events.StockUpdatedEvent;
import com.estockmarket.stock.core.models.Stock;
import com.estockmarket.stock.query.api.repositories.StockRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("stock-group")
public class StockEventHandlerImpl implements StockEventHandler {
    private final StockRepository stockRepository;


    @Autowired
    public StockEventHandlerImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @EventHandler
    @Override
    public void on(StockCreatedEvent event) {
        var stock = Stock.builder()
                .id(event.getId())
                .companyCode(event.getCompanyCode())
                .stockPrice(event.getStockPrice())
                .createDateTime(event.getCreateDateTime())
                .build();
        stockRepository.save(stock);
    }

    @EventHandler
    @Override
    public void on(StockUpdatedEvent event) {
        var stock = stockRepository.findById(event.getId());

        if (stock.isEmpty()) return;

        stock.get().setCompanyCode(event.getCompanyCode());
        stockRepository.save(stock.get());
    }

    // TODO: 9/14/21 handle update all stocks by companyDode

    @EventHandler
    @Override
    public void on(StockDeletedEvent event) {
        stockRepository.deleteById(event.getId());
    }

    // TODO: 9/14/21 handle delete by companyCode
}
