package com.tanphi.laptopshop.controller;

import com.tanphi.laptopshop.entity.Product;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.mapper.ProductMapper;
import com.tanphi.laptopshop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

//@PreAuthorize("hasAuthority('CUSTOMER')")
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;

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
		Page<Product> list = productService.getListProductCustomer(pageable);
		int totalPages = list.getTotalPages();
		int currentPage = list.getNumber() + 1;
		List<Product> listPro = list.getContent();
		return ResponseEntity.status(HttpStatus.OK)
				.body(ProductMapper.toResponeGetAllProductPage(listPro, currentPage, totalPages));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id) throws ParseException {
		Product product = productService.getProductById(id);
		if (product == null) {
			throw new BadRequestException("Không có sản phẫm có id: " + String.valueOf(id));
		}
		return ResponseEntity.ok(ProductMapper.toResponeGetProduct(product));
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
			listAllProductPage = productService.getListProductCustomer(pageable);
			int totalPages = listAllProductPage.getTotalPages();
			int currentPage = listAllProductPage.getNumber() + 1;
			List<Product> listPro = listAllProductPage.getContent();
			if (listPro == null || listPro.size() == 0) {
				throw new BadRequestException("Không tìm được sản phẫm");
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(ProductMapper.toResponeGetAllProductPage(listPro, currentPage, totalPages));
		} else {
			// String keywordSearch="%"+keyword+"%";
			listProductByKeywordPage = productService.getListProductCustomerByKeyword(pageable, keyword);
			int totalPages = listProductByKeywordPage.getTotalPages();
			int currentPage = listProductByKeywordPage.getNumber() + 1;
			List<Product> listPro = listProductByKeywordPage.getContent();
			if (listPro == null || listPro.size() == 0) {
				throw new BadRequestException("Không tìm được sản phẫm");
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(ProductMapper.toResponeGetAllProductPage(listPro, currentPage, totalPages));
		}
	}
}
