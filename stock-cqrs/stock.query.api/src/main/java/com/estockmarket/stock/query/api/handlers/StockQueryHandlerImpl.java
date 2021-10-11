package com.estockmarket.stock.query.api.handlers;

import com.estockmarket.stock.core.models.Stock;
import com.estockmarket.stock.query.api.queries.*;
import com.estockmarket.stock.query.api.repositories.StockRepository;
import com.estockmarket.stock.query.api.tdo.StockLookupResponse;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StockQueryHandlerImpl implements StockQueryHandler {
    public static final String SUCCESSFULLY_RETURNED = "Successfully returned ";
    public static final String NO_STOCKS_WERE_FOUND = "No Stocks were found!";
    private final StockRepository stockRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(StockQueryHandlerImpl.class);

    @Autowired
    public StockQueryHandlerImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @QueryHandler
    @Override
    public StockLookupResponse findAllStocks(FindAllStocksQuery query) {
        var stockIterator = stockRepository.findAll();
        if (!stockIterator.iterator().hasNext()){
            return new StockLookupResponse(NO_STOCKS_WERE_FOUND);
        }
        var stocks = new ArrayList<Stock>();
        stockIterator.forEach(stocks::add);
        LOGGER.info("{} {}  Stock(s)!", SUCCESSFULLY_RETURNED, stocks.size());
        return new StockLookupResponse(SUCCESSFULLY_RETURNED + stocks.size() + " Stock(s)!", stocks);
    }

    @QueryHandler
    @Override
    public StockLookupResponse findLatestStockByCompanyCode(FindLatestStockByCompanyCodeQuery query) {
        var stock = stockRepository.findLatestStockByCompany(query.getCompanyCode());
        return  stock != null
                ? new StockLookupResponse("Stock successfully returned!", stock)
                : new StockLookupResponse("No Stock found for company code: " + query.getCompanyCode());
    }

    @QueryHandler
    @Override
    public StockLookupResponse findStockById(FindStockByIdQuery query) {
        var stock = stockRepository.findById(query.getId());
        return stock.isPresent()
                ? new StockLookupResponse("Stock successfully returned!" ,stock.get())
                : new StockLookupResponse("No Stock found for ID: " + query.getId());
    }

    @QueryHandler
    @Override
    public StockLookupResponse findStocksBetweenStarAdnEndDates(FindStocksBetweenStartAndEndDatesQuery query) {
        var stocks = stockRepository.findStocksBetweenStartAndEndDates(query.getCompanyCode(), query.getStartDate(), query.getEndDate());
        if (stocks.isEmpty()) {
            return new StockLookupResponse(NO_STOCKS_WERE_FOUND);
        }
        LOGGER.info("{} {}  Stock(s)!", SUCCESSFULLY_RETURNED, stocks.size());
        return new StockLookupResponse(SUCCESSFULLY_RETURNED + stocks.size() + " Stocks" ,stocks);
    }

    @QueryHandler
    @Override
    public StockLookupResponse findStockByCompanyCode(FindStocksByCompanyCodeQuery query) {
        var stocks = stockRepository.findByCompanyCode(query.getCompanyCode());
        if (stocks.isEmpty()) {
            return new StockLookupResponse(NO_STOCKS_WERE_FOUND);
        }
        LOGGER.info("{} {}  Stock(s)!", SUCCESSFULLY_RETURNED, stocks.size());
        return new StockLookupResponse(SUCCESSFULLY_RETURNED + stocks.size() + " Stocks" , stocks);
    }
}
