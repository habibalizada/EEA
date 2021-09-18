package com.estockmarket.estockmarketapp.common;

import java.util.ArrayList;
import java.util.List;

public class StockLookupResponse extends BaseResponse {
    private List<Stock> stocks;

    public StockLookupResponse(String message) {
        super(message);
    }

    public StockLookupResponse(String message, List<Stock> stocks) {
        super(message);
        this.stocks = stocks;
    }

    public StockLookupResponse(String message, Stock stock) {
        super(message);
        this.stocks = new ArrayList<>();
        this.stocks.add(stock);
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
