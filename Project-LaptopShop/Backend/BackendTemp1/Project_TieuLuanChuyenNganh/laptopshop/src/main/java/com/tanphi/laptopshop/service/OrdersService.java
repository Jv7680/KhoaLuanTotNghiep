package com.tanphi.laptopshop.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tanphi.laptopshop.entity.Orders;
import com.tanphi.laptopshop.request.orders.OrderCreateRequest;


public interface OrdersService {
	void createOrders(OrderCreateRequest orderCreateRequest);

	List<Orders> getOrdersByStatus(Integer customerID, Integer status);

	void cancleOrders(Integer ordersID);

	Page<Orders> GetListOrdersByStatus(Integer status, Pageable pageable);

	void UpdateOrderStatus(Integer ordersID, Integer orderStatus);

	Page<Orders> GetAllOrdersPage(Pageable pageable);

	Orders GetOrdersById(int id);

}
