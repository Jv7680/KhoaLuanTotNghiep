package com.tanphi.laptopshop.mapper;

import com.tanphi.laptopshop.entity.Cart;
import com.tanphi.laptopshop.response.cart.GetAllCartByCustomerResponse;
import com.tanphi.laptopshop.response.cart.GetCartByCustomerResponse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CartMapper {
    public static GetAllCartByCustomerResponse toResponeGetAllCartByCustomer(List<Cart> cartList) throws ParseException
    {
    	GetAllCartByCustomerResponse tmp=new GetAllCartByCustomerResponse();
    	tmp.setId(cartList.get(0).getAccounts().getAccountId());
    	List<GetCartByCustomerResponse> listProductsGetProductResponses=new ArrayList<GetCartByCustomerResponse>();
    	for(Cart cart:cartList)
    	{
    		GetCartByCustomerResponse tmpItem=new GetCartByCustomerResponse();
    		tmpItem.setCartId(cart.getCartId());
    		tmpItem.setCartProductQuantity(cart.getCartProductQuantity());
    		tmpItem.setProduct(ProductMapper.toResponeGetProduct(cart.getProduct()));
    		listProductsGetProductResponses.add(tmpItem);
    	}
    	tmp.setListCarts(listProductsGetProductResponses);
    	return tmp;
    }
}
