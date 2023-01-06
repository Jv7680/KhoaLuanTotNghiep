package com.tanphi.laptopshop.request.review;

import lombok.Data;

@Data
public class CreateReviewRequest {
	private Integer orderId;
	private Integer accountId;
	private Integer productId;
    private String contents;
    private Double rate;
}
