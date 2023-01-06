package com.tanphi.laptopshop.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanphi.laptopshop.request.review.CreateReviewRequest;
import com.tanphi.laptopshop.service.ReviewsService;

@PreAuthorize("hasAuthority('CUSTOMER')")
@RestController
@RequestMapping("/reviews")
public class ReviewsController {
	@Autowired
	private ReviewsService reviewsService;
	@PostMapping("")
	public ResponseEntity<?> createReviews(@Valid @RequestBody CreateReviewRequest request,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
		}
		reviewsService.createReviews(request);
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Viết phản hồi thành công");
		return ResponseEntity.ok(apiResponse);
	}
		
}
