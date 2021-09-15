package com.estockmarket.stock.query.api.handlers;

import com.estockmarket.stock.query.api.queries.*;
import com.estockmarket.stock.query.api.tdo.StockLookupResponse;

public interface StockQueryHandler {
    StockLookupResponse findAllStocks(FindAllStocksQuery query);
    StockLookupResponse findLatestStockByCompanyCode(FindLatestStockByCompanyCodeQuery query);
    StockLookupResponse findStockById(FindStockByIdQuery query);
    StockLookupResponse findStocksBetweenStarAdnEndDates(FindStocksBetweenStartAndEndDatesQuery query);
    StockLookupResponse findStockByCompanyCode(FindStocksByCompanyCodeQuery query);

}
