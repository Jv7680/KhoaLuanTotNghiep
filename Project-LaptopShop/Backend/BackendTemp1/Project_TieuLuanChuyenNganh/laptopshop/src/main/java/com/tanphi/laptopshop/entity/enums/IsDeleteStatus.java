package com.tanphi.laptopshop.entity.enums;

public enum IsDeleteStatus {
    YES(1),
    NO(0);
    private Integer code;
    IsDeleteStatus(Integer code)
    {
        this.code=code;
    }
    public Integer getCode(){return this.code;}
}
