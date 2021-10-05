package com.estockmarket.stock.query.api.controllers;

import com.estockmarket.stock.core.models.Stock;
import com.estockmarket.stock.query.api.queries.*;
import com.estockmarket.stock.query.api.tdo.ResponseStock;
import com.estockmarket.stock.query.api.tdo.StockLookupResponse;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/market/stock/query")
public class StockLookupController {
    private final QueryGateway queryGateway;

    public StockLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/getall")
    public List<Stock> getAllStocks() {
        try {
            var query = new FindAllStocksQuery();
            var response = queryGateway.query(query, ResponseTypes.instanceOf(StockLookupResponse.class)).join();

            if (response == null || response.getStocks() == null) {
                return new ArrayList<>();
            }

            return response.getStocks();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockLookupResponse> getStockById(@PathVariable(value = "id") String id) {
        try {
            var query = new FindStockByIdQuery(id);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(StockLookupResponse.class)).join();

            if (response == null || response.getStocks() == null) {
                return new ResponseEntity<>(new StockLookupResponse(""), HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get a Stock by ID request";

            return new ResponseEntity<>(new StockLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/info/{companycode}")
    public List<Stock> getStocksByCompanyCode(@PathVariable(value = "companycode") String companycode) {
        try {
            var query = new FindStocksByCompanyCodeQuery(companycode);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(StockLookupResponse.class)).join();

            if (response == null || response.getStocks() == null) {
                return new ArrayList<>();
            }

            return response.getStocks();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @GetMapping("/get/{companycode}/{startdate}/{enddate}")
    public ResponseStock getStocksBetweenStartAndEndDates(@PathVariable(value = "companycode") String companycode, @PathVariable("startdate") String startdate, @PathVariable("enddate") String enddate) {
        try {

            var query = new FindStocksBetweenStartAndEndDatesQuery(companycode, startdate, enddate);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(StockLookupResponse.class)).join();

            if (response == null || response.getStocks() == null) {
                return new ResponseStock();
            }

            List<BigDecimal> prices = new ArrayList<>();
            BigDecimal total = new BigDecimal(0);
            var stocks = response.getStocks();
            for (Stock s: stocks) {
                prices.add(s.getStockPrice());
                total = total.add(s.getStockPrice());
            }

            BigDecimal min = Collections.min(prices);
            BigDecimal max = Collections.max(prices);

            double avgInDouble = total.doubleValue()/stocks.size();

            return new ResponseStock(stocks,min,max,BigDecimal.valueOf(avgInDouble));

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseStock();

        }
    }

    @GetMapping("/info/latest/{companycode}")
    public Stock getLatestStockByCompanyCode(@PathVariable(value = "companycode") String companycode) {
        try {
            var query = new FindLatestStockByCompanyCodeQuery(companycode);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(StockLookupResponse.class)).join();

            if (response == null || response.getStocks() == null) {
                return null;
            }

            return response.getStocks().get(0);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


}
