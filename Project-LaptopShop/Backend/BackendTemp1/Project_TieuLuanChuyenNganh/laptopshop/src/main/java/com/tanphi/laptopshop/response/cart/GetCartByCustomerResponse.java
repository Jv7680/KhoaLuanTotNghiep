package com.tanphi.laptopshop.response.cart;

import com.tanphi.laptopshop.response.product.GetProductResponse;

import lombok.Data;
@Data
public class GetCartByCustomerResponse {
	private Integer cartId;
    private Integer cartProductQuantity;
    private GetProductResponse product;
}
