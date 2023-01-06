package com.tanphi.laptopshop.response.orders;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
@Data
public class GetOrdersByStatus {
	private Integer orderId;
	private Timestamp orderDate;
	private Timestamp receiptDate;
	private Integer totalAmount;
	private Integer shipping;
	private String customerNote;
	private String address;
	private String phoneNumber;
	private String receiptName;
	private Integer totalQuantity;
	private List<GetOrdersDetailResponse> lstOrdersDetail;
}
