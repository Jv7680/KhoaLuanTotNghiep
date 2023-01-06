package com.tanphi.laptopshop.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanphi.laptopshop.entity.Orders;
import com.tanphi.laptopshop.entity.enums.OrderStatus;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.mapper.OrdersMapper;
import com.tanphi.laptopshop.request.orders.OrderCreateRequest;
import com.tanphi.laptopshop.service.OrdersService;
import com.tanphi.laptopshop.service.ReviewsService;

@PreAuthorize("hasAuthority('CUSTOMER')")
@RestController
@RequestMapping("/orders")
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	@Autowired ReviewsService reviewsService;
	
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<?> GetOrdersById(@PathVariable int id) {
		Orders order= ordersService.GetOrdersById(id);
		if (order==null) {
			throw new BadRequestException("Không có đơn hàng có id: "+id);
		}
		return ResponseEntity
				.ok(OrdersMapper.toResponseGetOrdersByStatus(order));
	}

	@PostMapping("")
	public ResponseEntity<?> createOrders(@Valid @RequestBody OrderCreateRequest orderCreateRequest,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
		}
		ordersService.createOrders(orderCreateRequest);
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Tạo đơn hàng thành công");
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/{customerID}")
	public ResponseEntity<?> getListOrdersByStatus(@PathVariable Integer customerID,@RequestParam(value = "status", required = false) Integer status) {
		if(status==null)
		{
			status=1;
		}
		String strStatus="";
		if(OrderStatus.APPROVED.getCode()==status)
		{
			strStatus=OrderStatus.APPROVED.getMessage();
		}
		else if(OrderStatus.CANCELED.getCode()==status)
		{
			strStatus=OrderStatus.CANCELED.getMessage();
		}
		else if(OrderStatus.DELIVERD.getCode()==status)
		{
			strStatus=OrderStatus.DELIVERD.getMessage();
		}
		else if(OrderStatus.DELIVERING.getCode()==status)
		{
			strStatus=OrderStatus.DELIVERING.getMessage();
		}
		else
		{
			strStatus=OrderStatus.WAITING_FOR_APPROVAL.getMessage();
			status=OrderStatus.WAITING_FOR_APPROVAL.getCode();
		}
		List<Orders> listOrders= ordersService.getOrdersByStatus(customerID,status);
		Map<String,Boolean> mapReviewById=reviewsService.getReviewById(customerID);
		return ResponseEntity.ok(OrdersMapper.toResponseGetListOrdersByStatusIsReviewd(listOrders,strStatus,status,mapReviewById));
	}
	
	@PutMapping("/cancel/{ordersID}")
	public ResponseEntity<?> cancleOrders(@PathVariable Integer ordersID) {
		ordersService.cancleOrders(ordersID);
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Hủy đơn hàng thành công");
		return ResponseEntity.ok(apiResponse);
	}
	
}
