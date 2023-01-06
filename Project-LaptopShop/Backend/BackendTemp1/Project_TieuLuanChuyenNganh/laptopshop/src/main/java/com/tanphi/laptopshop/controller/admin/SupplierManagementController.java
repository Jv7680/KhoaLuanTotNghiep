package com.tanphi.laptopshop.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanphi.laptopshop.controller.ApiResponse;
import com.tanphi.laptopshop.entity.Supplier;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.request.authen.SupplierRequest;
import com.tanphi.laptopshop.service.SupplierService;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("admin/supplier")
public class SupplierManagementController {
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping("/all")
    public ResponseEntity<?> GetAllSupplier()
    {
		List<Supplier> listSupplier=supplierService.GetAllSupplier();
		if(listSupplier==null || listSupplier.size()==0)
		{
			throw new BadRequestException("Không có nhà cung cấp nào");
		}
		return ResponseEntity.ok(listSupplier);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<?> GetSupplierById(@PathVariable Integer id)
    {
		Supplier supplier=supplierService.GetSupplierById(id);
		if(supplier==null)
		{
			throw new BadRequestException("Không có nhà cung cấp có id: "+id);
		}
		return ResponseEntity.ok(supplier);
    }
	
	@PostMapping("")
    public ResponseEntity<?> CreateSupplier(@Valid @RequestBody SupplierRequest request, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        supplierService.CreateSupplier(request);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Thêm nhà cung cấp thành công");
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateSupplier(@PathVariable Integer id,@Valid @RequestBody SupplierRequest request, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
        }
        supplierService.UpdateSupplier(id,request);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Cập nhật nhà cung cấp thành công");
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> DeleteSupplier(@PathVariable Integer id)
    {
        supplierService.DeleteSupplier(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Xóa nhà cung cấp thành công");
        return ResponseEntity.ok(apiResponse);
    }
}
