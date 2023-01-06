package com.tanphi.laptopshop.service;


import com.tanphi.laptopshop.entity.Cart;
import com.tanphi.laptopshop.request.cart.CartRequest;

import java.util.List;


public interface CartService {
    List<Cart> getListCartByCustomer(Integer customerId);

	void addToCart(CartRequest cartRequest);

	void deleteCart(CartRequest cartRequest);

	void updateCart(CartRequest cartRequest);
}
