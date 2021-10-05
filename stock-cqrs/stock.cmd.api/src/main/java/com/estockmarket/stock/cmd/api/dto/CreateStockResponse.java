package com.estockmarket.stock.cmd.api.dto;

import com.estockmarket.stock.core.dto.BaseResponse;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CreateStockResponse that = (CreateStockResponse) o;
        return Objects.equals(companyCode, that.companyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companyCode);
    }
}
