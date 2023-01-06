package com.tanphi.laptopshop.service;

import com.tanphi.laptopshop.entity.Supplier;
import com.tanphi.laptopshop.entity.enums.IsDeleteStatus;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.repository.SupplierRepo;
import com.tanphi.laptopshop.request.authen.SupplierRequest;
import com.tanphi.laptopshop.request.cart.CartRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.validation.Valid;

@Service
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private SupplierRepo supplierRepo;
	@Override
	public List<Supplier> GetAllSupplier() {
		// TODO Auto-generated method stub
		List<Supplier> listSuppliers=supplierRepo.findSupplierByIsdeleted(IsDeleteStatus.NO.getCode());
		return listSuppliers;
	}
	@Override
	public void CreateSupplier(SupplierRequest request) {
		// TODO Auto-generated method stub
		Supplier dbSupplier=supplierRepo.findSupplierBySupplierNameAndIsdeleted(request.getSupplierName(), IsDeleteStatus.NO.getCode());
		if(dbSupplier!=null)
		{
			throw new BadRequestException("Đã tồn nhà cung cấp có tên: "+request.getSupplierName());			
		}
		Supplier newSupplier=new Supplier();
		newSupplier.setImageLink(request.getImageLink());
		newSupplier.setSupplierName(request.getSupplierName());
		newSupplier.setIsdeleted(IsDeleteStatus.NO.getCode());
		supplierRepo.save(newSupplier);
	}
	@Override
	public void DeleteSupplier(Integer id) {
		// TODO Auto-generated method stub
		Supplier dbSupplier=supplierRepo.findSupplierBySupplierIdAndIsdeleted(id, IsDeleteStatus.NO.getCode());
		if(dbSupplier==null)
		{
			throw new BadRequestException("Không có nhà cung cấp có id: "+id);			
		}
		dbSupplier.setIsdeleted(IsDeleteStatus.YES.getCode());
		supplierRepo.save(dbSupplier);
	}
	@Override
	public void UpdateSupplier(Integer id, @Valid SupplierRequest request) {
		// TODO Auto-generated method stub
		Supplier dbSupplier=supplierRepo.findSupplierBySupplierIdAndIsdeleted(id, IsDeleteStatus.NO.getCode());
		Supplier dbSupplierByName=supplierRepo.findSupplierBySupplierNameAndIsdeleted(request.getSupplierName(), IsDeleteStatus.NO.getCode());
		if(dbSupplierByName!=null)
		{
			throw new BadRequestException("Đã tồn nhà cung cấp có tên: "+request.getSupplierName());			
		}
		if(dbSupplier==null)
		{
			throw new BadRequestException("Không có nhà cung cấp có id: "+id);			
		}
		dbSupplier.setImageLink(request.getImageLink());
		dbSupplier.setSupplierName(request.getSupplierName());
		supplierRepo.save(dbSupplier);
	}
	@Override
	public Supplier GetSupplierById(Integer id) {
		// TODO Auto-generated method stub
		Supplier dbSupplier=supplierRepo.findSupplierBySupplierIdAndIsdeleted(id, IsDeleteStatus.NO.getCode());
		if(dbSupplier==null)
		{
			throw new BadRequestException("Không có nhà cung cấp có id: "+id);			
		}
		return dbSupplier;
	}

}
