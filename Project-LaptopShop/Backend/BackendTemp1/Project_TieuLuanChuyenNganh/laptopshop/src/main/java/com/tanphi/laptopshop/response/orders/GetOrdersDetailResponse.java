package com.tanphi.laptopshop.response.orders;

import lombok.Data;

@Data
public class GetOrdersDetailResponse {
	private Integer orderId;
	private Integer productId;
	private String productName;
	private Integer price;
	private String imgLink;
	private Integer quantity;
	private Boolean isReviewed;
}
