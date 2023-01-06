package com.tanphi.laptopshop.service;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.Cart;
import com.tanphi.laptopshop.entity.OrderDetail;
import com.tanphi.laptopshop.entity.OrderDetailIDKey;
import com.tanphi.laptopshop.entity.Orders;
import com.tanphi.laptopshop.entity.enums.IsDeleteStatus;
import com.tanphi.laptopshop.entity.enums.OrderStatus;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.repository.AccountsRepo;
import com.tanphi.laptopshop.repository.CartRepo;
import com.tanphi.laptopshop.repository.OrdersDetailRepo;
import com.tanphi.laptopshop.repository.OrdersRepo;
import com.tanphi.laptopshop.repository.ProductRepo;
import com.tanphi.laptopshop.request.cart.CartRequest;
import com.tanphi.laptopshop.request.orders.OrderCreateRequest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private AccountsRepo accountsRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private OrdersRepo ordersRepo;
	@Autowired
	private OrdersDetailRepo ordersDetailRepo;
	
	@Override
	public void createOrders(OrderCreateRequest orderCreateRequest) {
		try {
			Accounts accounts=accountsRepo.findAccountsByAccountId(orderCreateRequest.getAccountId());
			Orders orders=new Orders();
			orders.setAccounts(accounts);
			Timestamp nowTimestamp = Timestamp.from(Instant.now());
			orders.setOrderDate(nowTimestamp);
			orders.setStatus(OrderStatus.WAITING_FOR_APPROVAL.getCode());
			orders.setShipping(0);
			orders.setAddress(orderCreateRequest.getAddress());
			orders.setCustomerNote(orderCreateRequest.getCustomerNote());
			orders.setTotalAmount(orderCreateRequest.getTotal().intValue());
			orders.setPhonenumber(orderCreateRequest.getPhoneNumber());
			orders.setRecipientname(orderCreateRequest.getRecipientname());
			Orders newOrders=ordersRepo.save(orders);
			for(CartRequest cartRequest:orderCreateRequest.getCartItemList())
			{
				//Xóa giỏ hàng
				Cart dbCart=cartRepo.findCartByIsdeletedAndAccountsAndProduct(IsDeleteStatus.NO.getCode(), accountsRepo.findAccountsByAccountId(cartRequest.getAccountId()), productRepo.findProductByProductIdAndIsdeleted(cartRequest.getProductId(), IsDeleteStatus.NO.getCode()));
				dbCart.setIsdeleted(IsDeleteStatus.YES.getCode());
				cartRepo.save(dbCart);
				
				//Thêm chi tiết đơn hàng
				OrderDetail orderDetail=new OrderDetail();
				orderDetail.setOrder(newOrders);
				orderDetail.setProduct(productRepo.findProductByProductIdAndIsdeleted(cartRequest.getProductId(), IsDeleteStatus.NO.getCode()));
				orderDetail.setQuantity(cartRequest.getCartProductQuantity());
				OrderDetailIDKey orderDetailIDKey=new OrderDetailIDKey();
				orderDetailIDKey.setOrderId(newOrders.getOrderId());
				orderDetailIDKey.setProductId(cartRequest.getProductId());
				orderDetail.setOrderDetailIDKey(orderDetailIDKey);
				ordersDetailRepo.save(orderDetail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cancleOrders(Integer ordersID) {
		Orders orders=ordersRepo.findOrdersByOrderId(ordersID);
		if(orders==null)
		{
			throw new BadRequestException("Đơn hàng không tồn tại");
		}
		if(orders.getStatus()!=OrderStatus.WAITING_FOR_APPROVAL.getCode())
		{
			Integer status=orders.getStatus();
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
			throw new BadRequestException("Đơn hàng không thể hủy vì ở trạng thái: "+strStatus);
		}
		orders.setStatus(OrderStatus.CANCELED.getCode());
		ordersRepo.save(orders);
	}



	@Override
	public List<Orders> getOrdersByStatus(Integer customerID, Integer status) {
		// TODO Auto-generated method stub
		Accounts accounts=accountsRepo.findAccountsByAccountId(customerID);
		List<Orders> lstOrders=ordersRepo.findOrdersByStatusAndAccountsOrderByOrderDateDesc(status, accounts);
		return lstOrders;
	}



	@Override
	public Page<Orders> GetListOrdersByStatus(Integer status, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Orders> pageOrders=ordersRepo.findOrdersByStatusOrderByOrderDateDesc(status,pageable);
		return pageOrders;
	}



	@Override
	public void UpdateOrderStatus(Integer ordersID, Integer orderStatus) {
		// TODO Auto-generated method stub
		Orders orders=ordersRepo.findOrdersByOrderId(ordersID);
		if(orders==null)
		{
			throw new BadRequestException("Đơn hàng không tồn tại");
		}
		orders.setStatus(orderStatus);
		ordersRepo.save(orders);
	}



	@Override
	public Page<Orders> GetAllOrdersPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Orders> pageOrders=ordersRepo.findAllByOrderByOrderDate(pageable);
		return pageOrders;
	}



	@Override
	public Orders GetOrdersById(int id) {
		// TODO Auto-generated method stub
		Orders orders=ordersRepo.findOrdersByOrderId(id);
		if(orders==null)
		{
			throw new BadRequestException("Đơn hàng "+id+" không tồn tại");
		}
		return orders;
	}	
}
