package com.tanphi.laptopshop.service;


import com.tanphi.laptopshop.request.review.CreateReviewRequest;
import java.util.Map;

public interface ReviewsService {
	void createReviews(CreateReviewRequest request);

	Map<String, Boolean> getReviewById(Integer customerID);
}
