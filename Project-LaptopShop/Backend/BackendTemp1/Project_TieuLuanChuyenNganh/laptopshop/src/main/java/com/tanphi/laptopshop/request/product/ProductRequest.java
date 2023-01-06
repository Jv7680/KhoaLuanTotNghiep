package com.tanphi.laptopshop.request.product;

import lombok.Data;

@Data
public class ProductRequest {
	private Integer productId;
//	@NotNull(message = "Nhập tên sản phẫm")
//	@NotEmpty(message = "Nhập tên sản phẫm")
//	@Size(max = 50, message = "Nhập tên danh mục nhỏ hơn 50 ký tự")
	//@Pattern(regexp = "^[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ]+(\\s[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ]+)*$", message = "Nhập ký tự không nhập những ký tự đặc biệt")
    private String productName;
    private Integer quantity;
//	@NotNull(message = "Chọn hình sản phẫm")
//	@NotEmpty(message = "Chọn hình sản phẫm")
    private String image;
    private Integer unitprice;
    private Integer discount;
    private String description;
    private Integer supplierId;
    private Integer isDeleted;
}
