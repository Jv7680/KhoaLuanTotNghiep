package com.tanphi.laptopshop.mapper;

import com.tanphi.laptopshop.entity.OrderDetail;
import com.tanphi.laptopshop.response.orders.GetOrdersDetailResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrdersDetailMapper {
	public static GetOrdersDetailResponse toResponseGetOrdersDetail(OrderDetail orderDetail)
	{
		GetOrdersDetailResponse tmp=new GetOrdersDetailResponse();
		tmp.setOrderId(orderDetail.getOrderDetailIDKey().getOrderId());
		tmp.setProductId(orderDetail.getOrderDetailIDKey().getProductId());
		tmp.setPrice(orderDetail.getProduct().getUnitprice());
		tmp.setImgLink(orderDetail.getProduct().getImage());
		tmp.setProductName(orderDetail.getProduct().getProductName());;
		tmp.setQuantity(orderDetail.getQuantity());
		return tmp;
	}
	public static List<GetOrdersDetailResponse> toResponseGetListOrdersDetail(Set<OrderDetail> orderDetails)
	{
		List<GetOrdersDetailResponse> tmp=new ArrayList<GetOrdersDetailResponse>();
		for(OrderDetail orderDetail:orderDetails)
		{
			GetOrdersDetailResponse itemTmp=toResponseGetOrdersDetail(orderDetail);
			tmp.add(itemTmp);
		}
		return tmp;
	}
	public static List<GetOrdersDetailResponse> toResponseGetListOrdersDetailIsReviewed(Set<OrderDetail> orderDetails,Map<String, Boolean> mapReviews) {
		// TODO Auto-generated method stub
		List<GetOrdersDetailResponse> tmp=new ArrayList<GetOrdersDetailResponse>();
		for(OrderDetail orderDetail:orderDetails)
		{
			GetOrdersDetailResponse itemTmp=toResponseGetOrdersDetailIsReviewed(orderDetail,mapReviews);
			tmp.add(itemTmp);
		}
		return tmp;
	}
	private static GetOrdersDetailResponse toResponseGetOrdersDetailIsReviewed(OrderDetail orderDetail,
			Map<String, Boolean> mapReviews) {
		// TODO Auto-generated method stub
		GetOrdersDetailResponse tmp=new GetOrdersDetailResponse();
		tmp.setOrderId(orderDetail.getOrderDetailIDKey().getOrderId());
		tmp.setProductId(orderDetail.getOrderDetailIDKey().getProductId());
		tmp.setImgLink(orderDetail.getProduct().getImage());
		tmp.setPrice(orderDetail.getProduct().getUnitprice());
		tmp.setProductName(orderDetail.getProduct().getProductName());;
		tmp.setQuantity(orderDetail.getQuantity());
		String key=String.join("_", orderDetail.getOrderDetailIDKey().getOrderId().toString(),orderDetail.getOrderDetailIDKey().getProductId().toString());
		if(mapReviews.containsKey(key))
		{
			tmp.setIsReviewed(mapReviews.get(key));
		}
		else {
			tmp.setIsReviewed(false);
		}
		return tmp;
	}
}
