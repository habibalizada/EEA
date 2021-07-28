package com.estockmarket.estockmarketapp.Exception;

public class CompanyCollectionException  extends Exception{

    private  static final long serialVersionUID = 1L;

    public CompanyCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String code) {
        return "Company with code: " + code + " not found";
    }

    public static String StockNotFoundException(String code) {
        return "Stock with company code: " + code + " not found";
    }

    public static String CompanyAlreadyExists() {
        return "Company with given code already exists";
    }

    public static String NotFoundWithIdException(Long id) {
        return "Company with id: " + id + " not found";
    }
}
