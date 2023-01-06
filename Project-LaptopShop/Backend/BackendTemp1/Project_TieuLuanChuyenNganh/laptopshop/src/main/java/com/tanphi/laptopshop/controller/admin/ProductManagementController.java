package com.tanphi.laptopshop.controller.admin;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanphi.laptopshop.controller.ApiResponse;
import com.tanphi.laptopshop.entity.Product;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.mapper.ProductMapper;
import com.tanphi.laptopshop.request.product.ProductRequest;
import com.tanphi.laptopshop.service.ProductService;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("admin/product")
public class ProductManagementController {
	@Autowired
	private ProductService productService;

	@PostMapping("")
	public ResponseEntity<?> AddNewProduct(@Valid @RequestBody ProductRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
		}
		productService.AddNewProduct(request);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Thêm sản phẫm thành công");
		return ResponseEntity.ok(apiResponse);
	}

	@PutMapping("/update")
	public ResponseEntity<?> UpdateProduct(@Valid @RequestBody ProductRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
		}
		productService.UpdateProduct(request);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Cập nhật sản phẫm thành công");
		return ResponseEntity.ok(apiResponse);
	}
	
	@PutMapping("/updateStatus")
	public ResponseEntity<?> UpdateStatusProduct(@Valid @RequestBody ProductRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
		}
		productService.UpdateStatusProduct(request);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Thay đổi trạng thái của sản phẫm thành công");
		return ResponseEntity.ok(apiResponse);
	}

	@PutMapping("/delete/{productId}")
	public ResponseEntity<?> DeleteProduct(@PathVariable int productId) {
		productService.DeleteProduct(productId);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Xóa sản phẫm thành công");
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/all")
	public ResponseEntity<Object> getListProduct(@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws ParseException {
		if (page.isPresent()) {
			int pageNumber = page.get();
			page = Optional.of(pageNumber - 1);

		} else {
			page = Optional.of(0);
		}
		int pageSize = 10;
		if (size.isPresent()) {
			pageSize = size.get();
		}
		Pageable pageable = PageRequest.of(page.get(), pageSize);
		Page<Product> list = productService.getListProductAdmin(pageable);
		int totalPages = list.getTotalPages();
		int currentPage = list.getNumber() + 1;
		List<Product> listPro = list.getContent();
		return ResponseEntity.status(HttpStatus.OK)
				.body(ProductMapper.toResponeGetAllProductPageAdmin(listPro, currentPage, totalPages));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id) throws ParseException {
		Product product = productService.getProductByIdAdmin(id);
		if (product == null) {
			throw new BadRequestException("Không có sản phẫm có id: " + String.valueOf(id));
		}
		return ResponseEntity.ok(product);
	}

	@GetMapping("/search")
	public ResponseEntity<Object> getListProductByKeyword(
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "keyword", required = false) String keyword) throws ParseException {
		int pageSize = 10;
		if (size.isPresent()) {
			pageSize = size.get();
		}
		int pageNumber = 1;
		if (page.isPresent()) {
			pageNumber = page.get();
			page = Optional.of(pageNumber - 1);

		} else {
			page = Optional.of(0);
		}
		Pageable pageable = PageRequest.of(page.get(), pageSize);
		Page<Product> listAllProductPage = null;
		Page<Product> listProductByKeywordPage = null;
		if (keyword.isEmpty() || keyword == null) {
			listAllProductPage = productService.getListProductAdmin(pageable);
			int totalPages = listAllProductPage.getTotalPages();
			int currentPage = listAllProductPage.getNumber() + 1;
			List<Product> listPro = listAllProductPage.getContent();
			if (listPro == null || listPro.size() == 0) {
				throw new BadRequestException("Không tìm được sản phẫm");
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(ProductMapper.toResponeGetAllProductPageAdmin(listPro, currentPage, totalPages));
		} else {
			// String keywordSearch="%"+keyword+"%";
			listProductByKeywordPage = productService.getListProductAdminByKeyword(pageable, keyword);
			int totalPages = listProductByKeywordPage.getTotalPages();
			int currentPage = listProductByKeywordPage.getNumber() + 1;
			List<Product> listPro = listProductByKeywordPage.getContent();
			if (listPro == null || listPro.size() == 0) {
				throw new BadRequestException("Không tìm được sản phẫm");
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(ProductMapper.toResponeGetAllProductPageAdmin(listPro, currentPage, totalPages));
		}
	}
}
