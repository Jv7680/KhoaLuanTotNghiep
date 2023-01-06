package com.tanphi.laptopshop.repository;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.Cart;
import com.tanphi.laptopshop.entity.OrderDetail;
import com.tanphi.laptopshop.entity.Orders;
import com.tanphi.laptopshop.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDetailRepo extends JpaRepository<OrderDetail,Integer> {
}
