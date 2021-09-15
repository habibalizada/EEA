package com.estockmarket.stock.query.api.handlers;

import com.estockmarket.stock.core.models.Stock;
import com.estockmarket.stock.query.api.queries.*;
import com.estockmarket.stock.query.api.repositories.StockRepository;
import com.estockmarket.stock.query.api.tdo.StockLookupResponse;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StockQueryHandlerImpl implements StockQueryHandler {
    private final StockRepository stockRepository;

    @Autowired
    public StockQueryHandlerImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @QueryHandler
    @Override
    public StockLookupResponse findAllStocks(FindAllStocksQuery query) {
        var stockIterator = stockRepository.findAll();
        if (!stockIterator.iterator().hasNext()){
            return new StockLookupResponse("No Stocks were found!");
        }
        var stocks = new ArrayList<Stock>();
        stockIterator.forEach(i -> stocks.add(i));
        return new StockLookupResponse("Successfully returned " + stocks.size() + " Stock(s)!", stocks);
    }

    @QueryHandler
    @Override
    public StockLookupResponse findLatestStockByCompanyCode(FindLatestStockByCompanyCodeQuery query) {
        var stock = stockRepository.findLatestStockByCompany(query.getCompanyCode());
        var response = stock != null
                ? new StockLookupResponse("Stock successfully returned!", stock)
                : new StockLookupResponse("No Stock found for company code: " + query.getCompanyCode());
        return response;
    }

    @QueryHandler
    @Override
    public StockLookupResponse findStockById(FindStockByIdQuery query) {
        var stock = stockRepository.findById(query.getId());
        var response = stock.isPresent()
                ? new StockLookupResponse("Stock successfully returned!" ,stock.get())
                : new StockLookupResponse("No Stock found for ID: " + query.getId());
        return response;
    }

    @QueryHandler
    @Override
    public StockLookupResponse findStocksBetweenStarAdnEndDates(FindStocksBetweenStartAndEndDatesQuery query) {
        var stocks = stockRepository.findStocksBetweenStartAndEndDates(query.getCompanyCode(), query.getStartDate(), query.getEndDate());
        if (stocks.isEmpty()) {
            return new StockLookupResponse("No Stocks were found!");
        }
        return new StockLookupResponse("Successfully returned " + stocks.size() + " Stocks" ,stocks);
    }

    @QueryHandler
    @Override
    public StockLookupResponse findStockByCompanyCode(FindStocksByCompanyCodeQuery query) {
        var stocks = stockRepository.findByCompanyCode(query.getCompanyCode());
        if (stocks.isEmpty()) {
            return new StockLookupResponse("No Stocks were found!");
        }
        return new StockLookupResponse("Successfully returned " + stocks.size() + " Stocks" , stocks);
    }
}
