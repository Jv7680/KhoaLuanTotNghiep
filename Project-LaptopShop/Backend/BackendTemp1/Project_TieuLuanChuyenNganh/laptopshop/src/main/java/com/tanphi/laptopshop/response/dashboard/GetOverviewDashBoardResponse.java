package com.tanphi.laptopshop.response.dashboard;

import lombok.Data;

@Data
public class GetOverviewDashBoardResponse {
	private int month;
	private int year;
	private Integer totalCustomer;
	private Integer newOrders;
	private Integer totalProducts;
	private Long totalRevenue;
}
