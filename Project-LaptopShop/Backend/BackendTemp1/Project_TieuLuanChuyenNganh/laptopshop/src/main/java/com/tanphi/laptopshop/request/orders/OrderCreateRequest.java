package com.tanphi.laptopshop.request.orders;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.tanphi.laptopshop.request.cart.CartRequest;

import lombok.Data;
@Data
public class OrderCreateRequest {
	private Integer accountId;
	private String address;
	private String phoneNumber;
	private String recipientname;
	private Long total;
	private String customerNote;
	@NotEmpty(message = "Vui lòng chọn mặt hàng để ")
	private List<CartRequest> cartItemList;
}
