package com.tanphi.laptopshop.entity.enums;

public enum AuthProvider {
    LOCAL(1),
    FACEBOOK(2),
    GOOGLE(3);
    private Integer code;
    AuthProvider(Integer code)
    {
        this.code=code;
    }
    public Integer getCode(){return this.code;}
}
