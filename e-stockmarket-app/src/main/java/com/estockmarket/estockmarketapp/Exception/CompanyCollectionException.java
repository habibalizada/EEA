package com.estockmarket.estockmarketapp.Exception;

public class CompanyCollectionException  extends Exception{

    private  static final long serialVersionUID = 1L;
    private static final String NOT_FOUND_TXT = " not found";

    public CompanyCollectionException(String message) {
        super(message);
    }

    public static String notFoundException(String code) {
        return "Company with code: " + code + NOT_FOUND_TXT;
    }

    public static String stockNotFoundException(String code) {
        return "Stock with company code: " + code + NOT_FOUND_TXT;
    }

    public static String companyAlreadyExists() {
        return "Company with given code already exists";
    }

    public static String notFoundWithIdException(Long id) {
        return "Company with id: " + id + NOT_FOUND_TXT;
    }
}
