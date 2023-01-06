package com.tanphi.laptopshop.response.orders;

import java.util.List;

import lombok.Data;

@Data
public class GetListOrdersByStatus {
	private Integer status;
	private String strStatus;
	List<GetOrdersByStatus> listOrders;
		
}
