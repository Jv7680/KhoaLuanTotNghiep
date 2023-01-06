package com.tanphi.laptopshop.entity.enums;

public enum Roles {
    ADMIN(1),
    CUSTOMER(2);
    private Integer code;
    Roles(Integer code)
    {
        this.code=code;
    }
    public Integer getCode(){return this.code;}

}
