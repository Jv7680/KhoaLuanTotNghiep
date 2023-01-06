package com.tanphi.laptopshop.entity.enums;

public enum ActiveAccountStatus {
    ACTIVE(1),
    NO_ACTIVE(0);
    private Integer code;
    ActiveAccountStatus(Integer code)
    {
        this.code=code;
    }
    public Integer getCode(){return this.code;}
}
