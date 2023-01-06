package com.tanphi.laptopshop.request.cart;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class CartRequest {
	@NotNull(message = "Vui lòng truyền người dùng")
	private Integer accountId;

	@NotNull(message = "Vui lòng chọn sản phẫm")
	private Integer productId;

	@NotNull(message = "Nhập số lượng sản phẫm")
	@Digits(message = "Vui lòng nhập số", fraction = 0, integer = 5)
	@Min(value = 0, message = "Nhập số dương")
	@Max(value = 10000, message = "Số lượng nhỏ hơn 10000")
	private Integer cartProductQuantity;
}
