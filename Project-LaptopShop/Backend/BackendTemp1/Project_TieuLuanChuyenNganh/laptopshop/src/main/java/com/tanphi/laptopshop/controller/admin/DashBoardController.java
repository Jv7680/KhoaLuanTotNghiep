package com.tanphi.laptopshop.controller.admin;

import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanphi.laptopshop.entity.enums.ActiveAccountStatus;
import com.tanphi.laptopshop.entity.enums.IsDeleteStatus;
import com.tanphi.laptopshop.entity.enums.OrderStatus;
import com.tanphi.laptopshop.entity.enums.Roles;
import com.tanphi.laptopshop.repository.AccountsRepo;
import com.tanphi.laptopshop.repository.OrdersRepo;
import com.tanphi.laptopshop.repository.ProductRepo;
import com.tanphi.laptopshop.response.dashboard.GetOverviewDashBoardResponse;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("admin/dashboard")
public class DashBoardController {
	@Autowired
	private AccountsRepo accountsRepo;
	@Autowired
	private OrdersRepo ordersRepo;
	@Autowired
	private ProductRepo productRepo;

	@GetMapping("/overview")
	public ResponseEntity<?> OverviewDashBoard() {
		GetOverviewDashBoardResponse response = new GetOverviewDashBoardResponse();
		Integer totalCustomer = accountsRepo.countByRolesAndActiveAccount(Roles.CUSTOMER.getCode(),
				ActiveAccountStatus.ACTIVE.getCode());
		if(totalCustomer==null)
		{
			totalCustomer=0;
		}
		Integer newOrders = ordersRepo.countByStatus(OrderStatus.WAITING_FOR_APPROVAL.getCode());
		if(newOrders==null)
		{
			newOrders=0;
		}
		Integer totalProducts = productRepo.countByIsdeleted(IsDeleteStatus.NO.getCode());
		if(totalProducts==null)
		{
			totalProducts=0;
		}
		Calendar dateNows = Calendar.getInstance();
		Month month=LocalDateTime.now().getMonth();
		int numberOfMonth=month.length(Year.now().isLeap());
		String fromString=dateNows.get(Calendar.YEAR)+"-"+(dateNows.get(Calendar.MONTH)+1)+"-01"+" "+"00:00:00";
		String toString=dateNows.get(Calendar.YEAR)+"-"+(dateNows.get(Calendar.MONTH)+1)+"-"+String.valueOf(numberOfMonth)+" "+"23:59:59";
		Timestamp tsFrom=Timestamp.valueOf(fromString);
		Timestamp tsTo=Timestamp.valueOf(toString);
		Long totalRevenue=ordersRepo.sumTotalAmountByStatus(tsFrom, tsTo,OrderStatus.DELIVERD.getCode());
		if(totalRevenue==null)
		{
			totalRevenue=0l;
		}
		response.setMonth(dateNows.get(Calendar.MONTH)+1);
		response.setYear(dateNows.get(Calendar.YEAR));
		response.setNewOrders(newOrders);
		response.setTotalCustomer(totalCustomer);
		response.setTotalProducts(totalProducts);
		response.setTotalRevenue(totalRevenue);
		
		return ResponseEntity.ok(response);
	}
}
