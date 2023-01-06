package com.tanphi.laptopshop.repository;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.Cart;
import com.tanphi.laptopshop.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {
    List<Cart> findCartByIsdeletedAndAccounts(Integer status,Accounts accounts);
    Cart findCartByIsdeletedAndAccountsAndProduct(Integer status,Accounts accounts,Product product);
}
