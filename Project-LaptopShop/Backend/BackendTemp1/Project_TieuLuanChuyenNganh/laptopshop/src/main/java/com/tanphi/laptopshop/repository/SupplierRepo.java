package com.tanphi.laptopshop.repository;

import com.tanphi.laptopshop.entity.Product;
import com.tanphi.laptopshop.entity.Supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Integer>{
	List<Supplier> findSupplierByIsdeleted(Integer status);
	Supplier findSupplierBySupplierIdAndIsdeleted(Integer id,Integer status);
	Supplier findSupplierBySupplierNameAndIsdeleted(String name,Integer status);
}
