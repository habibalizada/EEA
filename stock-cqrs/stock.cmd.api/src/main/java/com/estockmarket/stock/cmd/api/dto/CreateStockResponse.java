package com.estockmarket.stock.cmd.api.dto;

import com.estockmarket.stock.core.dto.BaseResponse;

public class CreateStockResponse extends BaseResponse {
    private String companyCode;

    public CreateStockResponse(String companyCode, String message) {
        super(message);
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
