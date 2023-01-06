package com.tanphi.laptopshop.repository;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.Orders;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepo extends JpaRepository<Orders,Integer> {
	List<Orders> findOrdersByStatusAndAccountsOrderByOrderDateDesc(Integer status,Accounts accounts);
	Orders findOrdersByOrderId(Integer id);
	Page<Orders> findOrdersByStatusOrderByOrderDateDesc(Integer status,Pageable pageable);
	Page<Orders> findAllByOrderByOrderDate(Pageable pageable);
	Integer countByStatus(Integer status);
	@Query("SELECT SUM(o.totalAmount) FROM Orders o where o.orderDate>=:from and o.orderDate<=:to and o.status=:status")
	Long sumTotalAmountByStatus(Timestamp from,Timestamp to,Integer status);
}
