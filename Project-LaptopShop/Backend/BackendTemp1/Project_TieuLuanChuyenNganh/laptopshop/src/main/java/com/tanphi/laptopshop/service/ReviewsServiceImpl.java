package com.tanphi.laptopshop.service;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.Reviews;
import com.tanphi.laptopshop.entity.ReviewsIDKey;
import com.tanphi.laptopshop.entity.enums.IsDeleteStatus;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.repository.AccountsRepo;
import com.tanphi.laptopshop.repository.ReviewsRepo;
import com.tanphi.laptopshop.request.review.CreateReviewRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewsServiceImpl implements ReviewsService {
	@Autowired
	private ReviewsRepo reviewsRepo;
	@Autowired
	private AccountsRepo accoutsRepo;

	@Override
	public void createReviews(CreateReviewRequest request) {
		try {
			Reviews newReview = new Reviews();
			newReview.setContents(request.getContents());
			newReview.setRate(request.getRate());
			newReview.setReviewsDate(Timestamp.from(Instant.now()));
			newReview.setIsdeleted(IsDeleteStatus.NO.getCode());
			ReviewsIDKey reviewsIDKey = new ReviewsIDKey();
			reviewsIDKey.setAccountId(request.getAccountId());
			reviewsIDKey.setOrderId(request.getOrderId());
			reviewsIDKey.setProductId(request.getProductId());
			newReview.setReviewsIDKey(reviewsIDKey);
			reviewsRepo.save(newReview);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public Map<String, Boolean> getReviewById(Integer customerID) {
		// TODO Auto-generated method stub
		Map<String, Boolean> mapReviews=new HashMap<String, Boolean>();
		Accounts accounts=accoutsRepo.findAccountsByAccountId(customerID);
		if(accounts==null)
		{
			throw new BadRequestException("Tài khoản không tồn tại");
		}
		List<Reviews> reviewsList=reviewsRepo.findAllByAccounts(accounts);
		for(Reviews review:reviewsList)
		{
			mapReviews.put(review.getOrder().getOrderId()+"_"+review.getProduct().getProductId(), true);
		}
		return mapReviews;
	}

}
