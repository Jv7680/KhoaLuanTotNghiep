package com.tanphi.laptopshop.entity.enums;

public enum OrderStatus {
	WAITING_FOR_APPROVAL(1,"Đang chờ duyệt"),
    APPROVED(2,"Đã duyệt"),
    DELIVERING(3,"Đang giao"),
    DELIVERD(4,"Đã giao"),
    CANCELED(5,"Đã hủy");
    private Integer code;
    private String message;
    OrderStatus(Integer code,String message)
    {
        this.code=code;
        this.setMessage(message);
    }
    public Integer getCode(){return this.code;}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
