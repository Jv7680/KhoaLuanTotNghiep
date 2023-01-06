package com.tanphi.laptopshop.service;

import com.tanphi.laptopshop.entity.Accounts; 	
import com.tanphi.laptopshop.entity.Cart;
import com.tanphi.laptopshop.entity.Product;
import com.tanphi.laptopshop.entity.enums.IsDeleteStatus;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.repository.AccountsRepo;
import com.tanphi.laptopshop.repository.CartRepo;
import com.tanphi.laptopshop.repository.ProductRepo;
import com.tanphi.laptopshop.request.cart.CartRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.validation.Valid;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private AccountsRepo accountsRepo;
	@Autowired
	private ProductRepo productRepo;

	@Override
	public List<Cart> getListCartByCustomer(Integer customerId) {
		Accounts accounts=accountsRepo.findAccountsByAccountId(customerId);
		if(accounts==null)
		{
			throw new BadRequestException("Người dùng không tồn tại");
		}
		List<Cart> listCarts = cartRepo.findCartByIsdeletedAndAccounts(IsDeleteStatus.NO.getCode(), accounts);
		return listCarts;
	}

	@Override
	public void addToCart(CartRequest cartRequest) {
		Cart dbCart=cartRepo.findCartByIsdeletedAndAccountsAndProduct(IsDeleteStatus.NO.getCode(), accountsRepo.findAccountsByAccountId(cartRequest.getAccountId()), productRepo.findProductByProductIdAndIsdeleted(cartRequest.getProductId(), IsDeleteStatus.NO.getCode()));
		Product product=productRepo.findProductByProductIdAndIsdeleted(cartRequest.getProductId(), IsDeleteStatus.NO.getCode());
		Integer quantityCartRequest=cartRequest.getCartProductQuantity();
		Integer quantityDBProduct=product.getQuantity();
		if(quantityCartRequest>quantityDBProduct)
		{
			throw new BadRequestException("Mặt hàng không đủ số lượng");
		}
		else if(quantityCartRequest<=quantityDBProduct) {
			product.setQuantity(quantityDBProduct-quantityCartRequest);
			productRepo.save(product);
		}
		if(dbCart==null)
		{
			Cart newCart=new Cart();
			newCart.setAccounts(accountsRepo.findAccountsByAccountId(cartRequest.getAccountId()));
			newCart.setCartProductQuantity(cartRequest.getCartProductQuantity());
			newCart.setProduct(productRepo.findProductByProductIdAndIsdeleted(cartRequest.getProductId(), IsDeleteStatus.NO.getCode()));
			newCart.setIsdeleted(IsDeleteStatus.NO.getCode());
			cartRepo.save(newCart);
		}
		else {
			Integer dbQuantity=dbCart.getCartProductQuantity();
			dbCart.setCartProductQuantity(dbQuantity+cartRequest.getCartProductQuantity());
			cartRepo.save(dbCart);
		}
		
		
	}

	@Override
	public void deleteCart(CartRequest cartRequest) {
		// TODO Auto-generated method stub
		Cart dbCart=cartRepo.findCartByIsdeletedAndAccountsAndProduct(IsDeleteStatus.NO.getCode(), accountsRepo.findAccountsByAccountId(cartRequest.getAccountId()), productRepo.findProductByProductIdAndIsdeleted(cartRequest.getProductId(), IsDeleteStatus.NO.getCode()));
		if(dbCart==null)
		{
			throw new BadRequestException("Mặt hàng không tồn tại trong giỏ");
		}
		else {
			dbCart.setIsdeleted(IsDeleteStatus.YES.getCode());
			cartRepo.save(dbCart);
		}
	}

	@Override
	public void updateCart(@Valid CartRequest cartRequest) {
		// TODO Auto-generated method stub
		Cart dbCart=cartRepo.findCartByIsdeletedAndAccountsAndProduct(IsDeleteStatus.NO.getCode(), accountsRepo.findAccountsByAccountId(cartRequest.getAccountId()), productRepo.findProductByProductIdAndIsdeleted(cartRequest.getProductId(), IsDeleteStatus.NO.getCode()));
		if(dbCart==null)
		{
			throw new BadRequestException("Mặt hàng không tồn tại trong giỏ");
		}
		else {
			dbCart.setCartProductQuantity(cartRequest.getCartProductQuantity());
			cartRepo.save(dbCart);
		}
	}

}
