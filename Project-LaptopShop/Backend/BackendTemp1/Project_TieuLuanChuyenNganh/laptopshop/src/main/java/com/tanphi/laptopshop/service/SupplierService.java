package com.tanphi.laptopshop.service;

import com.tanphi.laptopshop.entity.Supplier;
import com.tanphi.laptopshop.request.authen.SupplierRequest;

import java.util.List;

import javax.validation.Valid;

public interface SupplierService {

	List<Supplier> GetAllSupplier();

	void CreateSupplier(SupplierRequest request);

	void DeleteSupplier(Integer id);

	void UpdateSupplier(Integer id, @Valid SupplierRequest request);

	Supplier GetSupplierById(Integer id);

}
