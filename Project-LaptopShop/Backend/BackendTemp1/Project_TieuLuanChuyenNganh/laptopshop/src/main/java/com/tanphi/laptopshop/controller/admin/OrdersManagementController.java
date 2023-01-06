package com.tanphi.laptopshop.controller.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanphi.laptopshop.controller.ApiResponse;
import com.tanphi.laptopshop.entity.Orders;
import com.tanphi.laptopshop.entity.enums.OrderStatus;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.mapper.OrdersMapper;
import com.tanphi.laptopshop.request.orders.UpdateOrdersStatusRequest;
import com.tanphi.laptopshop.service.OrdersService;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("admin/orders")
public class OrdersManagementController {
	@Autowired
	private OrdersService ordersService;

	@GetMapping("/all")
	public ResponseEntity<?> GetAllOrdersPage(@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) {
		String strStatus="";
		Integer status=0;
		int pageSize = 10;
		if (size.isPresent()) {
			pageSize = size.get();
		}
		int pageNumber = 1;
		if (page.isPresent()) {
			pageNumber = page.get();
			page = Optional.of(pageNumber - 1);

		} else {
			page = Optional.of(0);
		}
		Pageable pageable = PageRequest.of(page.get(), pageSize);
		Page<Orders> pageOrders = ordersService.GetAllOrdersPage(pageable);
		if (pageOrders.isEmpty()) {
			throw new BadRequestException("Không có đơn hàng nào");
		}
		return ResponseEntity
				.ok(OrdersMapper.toResponseGetListOrdersByStatus(pageOrders.getContent(), strStatus, status));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> GetOrdersById(@PathVariable int id) {
		Orders order= ordersService.GetOrdersById(id);
		if (order==null) {
			throw new BadRequestException("Không có đơn hàng có id: "+id);
		}
		return ResponseEntity
				.ok(OrdersMapper.toResponseGetOrdersByStatus(order));
	}

	@GetMapping("")
	public ResponseEntity<?> GetListOrdersByStatus(@RequestParam("status") Integer status,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) {
		int pageSize = 10;
		if (size.isPresent()) {
			pageSize = size.get();
		}
		int pageNumber = 1;
		if (page.isPresent()) {
			pageNumber = page.get();
			page = Optional.of(pageNumber - 1);

		} else {
			page = Optional.of(0);
		}
		Pageable pageable = PageRequest.of(page.get(), pageSize);
		Page<Orders> pageOrders = ordersService.GetListOrdersByStatus(status, pageable);
		String strStatus = "";
		if (OrderStatus.APPROVED.getCode() == status) {
			strStatus = OrderStatus.APPROVED.getMessage();
		} else if (OrderStatus.CANCELED.getCode() == status) {
			strStatus = OrderStatus.CANCELED.getMessage();
		} else if (OrderStatus.DELIVERD.getCode() == status) {
			strStatus = OrderStatus.DELIVERD.getMessage();
		} else if (OrderStatus.DELIVERING.getCode() == status) {
			strStatus = OrderStatus.DELIVERING.getMessage();
		} else {
			strStatus = OrderStatus.WAITING_FOR_APPROVAL.getMessage();
			status = OrderStatus.WAITING_FOR_APPROVAL.getCode();
		}
		if (pageOrders.isEmpty()) {
			throw new BadRequestException("Không có đơn hàng nào có trạng thái " + strStatus);
		}
		return ResponseEntity
				.ok(OrdersMapper.toResponseGetListOrdersByStatus(pageOrders.getContent(), strStatus, status));
	}

	@PutMapping("/update/{orderId}")
	public ResponseEntity<?> UpdateOrderStatus(@PathVariable Integer orderId,
			@RequestBody UpdateOrdersStatusRequest request) {
		ordersService.UpdateOrderStatus(orderId, request.getOrderStatus());
		Integer status = request.getOrderStatus();
		String strStatus = "";
		if (OrderStatus.APPROVED.getCode() == status) {
			strStatus = OrderStatus.APPROVED.getMessage();
		} else if (OrderStatus.CANCELED.getCode() == status) {
			strStatus = OrderStatus.CANCELED.getMessage();
		} else if (OrderStatus.DELIVERD.getCode() == status) {
			strStatus = OrderStatus.DELIVERD.getMessage();
		} else if (OrderStatus.DELIVERING.getCode() == status) {
			strStatus = OrderStatus.DELIVERING.getMessage();
		} else {
			strStatus = OrderStatus.WAITING_FOR_APPROVAL.getMessage();
			status = OrderStatus.WAITING_FOR_APPROVAL.getCode();
		}
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Thay đổi đơn hàng có id: " + orderId + " thành " + strStatus);
		return ResponseEntity.ok(apiResponse);
	}
}
