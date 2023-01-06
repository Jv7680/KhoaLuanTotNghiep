package com.tanphi.laptopshop.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tanphi.laptopshop.entity.Reviews;
import com.tanphi.laptopshop.entity.Supplier;
import com.tanphi.laptopshop.response.reviews.GetReviewsResponse;
import com.tanphi.laptopshop.response.supplier.GetAllSupplierResponse;

public class SupplierMapper {
	public static GetAllSupplierResponse toResponeGetAllSupplier(Supplier supplier)
    {
		GetAllSupplierResponse tmp=new GetAllSupplierResponse();
    	tmp.setImageLink(supplier.getImageLink());
    	tmp.setSupplierId(supplier.getSupplierId());
    	tmp.setSupplierName(supplier.getSupplierName());
        return tmp;
    }
	public static List<GetAllSupplierResponse> toResponeGetListsSupplier(List<Supplier> listSuppliers)
    {
        List<GetAllSupplierResponse> list=new ArrayList<>();
        for(Supplier supplier:listSuppliers) {
        	GetAllSupplierResponse tmp=toResponeGetAllSupplier(supplier);
            list.add(tmp);
        }
        return list;
    }
}
