package com.estockmarket.stock.query.api.tdo;

import com.estockmarket.stock.core.dto.BaseResponse;
import com.estockmarket.stock.core.models.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StockLookupResponse that = (StockLookupResponse) o;
        return Objects.equals(stocks, that.stocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stocks);
    }
}
