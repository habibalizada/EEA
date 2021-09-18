package com.estockmarket.stock.query.api.controllers;

import com.estockmarket.stock.core.models.Stock;
import com.estockmarket.stock.query.api.queries.*;
import com.estockmarket.stock.query.api.tdo.StockLookupResponse;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/market/stock/query")
public class StockLookupController {
    private final QueryGateway queryGateway;

    public StockLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/getall")
//    public ResponseEntity<StockLookupResponse> getAllStocks() {
    public List<Stock> getAllStocks() {
        try {
            var query = new FindAllStocksQuery();
            var response = queryGateway.query(query, ResponseTypes.instanceOf(StockLookupResponse.class)).join();

            if (response == null || response.getStocks() == null) {
//                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                return null;
            }

//            return new ResponseEntity<>(response, HttpStatus.OK);
            return response.getStocks();
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get all Stocks request";
            System.out.println(e.toString());

//            return new ResponseEntity<>(new StockLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
            return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockLookupResponse> getStockById(@PathVariable(value = "id") String id) {
        try {
            var query = new FindStockByIdQuery(id);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(StockLookupResponse.class)).join();

            if (response == null || response.getStocks() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get a Stock by ID request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new StockLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/info/{companycode}")
//    public ResponseEntity<StockLookupResponse> getStocksByCompanyCode(@PathVariable(value = "companycode") String companycode) {
    public Stock getStocksByCompanyCode(@PathVariable(value = "companycode") String companycode) {
        try {
            var query = new FindStocksByCompanyCodeQuery(companycode);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(StockLookupResponse.class)).join();

            if (response == null || response.getStocks() == null) {
//                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                return null;
            }

//            return new ResponseEntity<>(response, HttpStatus.OK);
            return response.getStocks().get(0);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get a Stocks by Company Code request";
            System.out.println(e.toString());

//            return new ResponseEntity<>(new StockLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
            return null;
        }
    }

    @GetMapping("/get/{companycode}/{startdate}/{enddate}")
    public ResponseEntity<StockLookupResponse> getStocksBetweenStartAndEndDates(@PathVariable(value = "companycode") String companycode, @PathVariable("startdate") String startdate, @PathVariable("enddate") String enddate) {
        try {
            var query = new FindStocksBetweenStartAndEndDatesQuery(companycode, startdate, enddate);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(StockLookupResponse.class)).join();

            if (response == null || response.getStocks() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get a Stocks between start and end dates request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new StockLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/info/latest/{companycode}")
//    public ResponseEntity<StockLookupResponse> getLatestStockByCompanyCode(@PathVariable(value = "companycode") String companycode) {
    public Stock getLatestStockByCompanyCode(@PathVariable(value = "companycode") String companycode) {
        try {
            var query = new FindLatestStockByCompanyCodeQuery(companycode);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(StockLookupResponse.class)).join();

            if (response == null || response.getStocks() == null) {
//                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                return null;
            }

//            return new ResponseEntity<>(response, HttpStatus.OK);
            var stocks = response.getStocks();
//            var stock = stocks.get(0);
            return response.getStocks().get(0);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get the latest Stock request";
            System.out.println(e.toString());

//            return new ResponseEntity<>(new StockLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
            return null;
        }
    }



}
