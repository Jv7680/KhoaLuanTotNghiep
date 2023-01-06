package com.tanphi.laptopshop.mapper;

import com.tanphi.laptopshop.entity.Reviews;

import com.tanphi.laptopshop.response.reviews.GetReviewsResponse;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ReviewsMapper {
    public static List<GetReviewsResponse> toResponeGetListReviews(Set<Reviews> reviewsList) throws ParseException
    {
        List<GetReviewsResponse> list=new ArrayList<>();
        for(Reviews reviews:reviewsList) {
        	GetReviewsResponse tmp=toResponeGetReviews(reviews);
            list.add(tmp);
        }
        return list;
    }
	public static GetReviewsResponse toResponeGetReviews(Reviews reviews) throws ParseException
    {
    	GetReviewsResponse tmp=new GetReviewsResponse();
    	tmp.setAccountId(reviews.getAccounts().getAccountId());
    	tmp.setContents(reviews.getContents());
    	tmp.setUsername(reviews.getAccounts().getUsername());
    	tmp.setRating(reviews.getRate());
    	Timestamp tsReviewDate=reviews.getReviewsDate();
    	Date dateReviews = new Date(tsReviewDate.getTime());
    	DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
    	tmp.setReviewsDate(f.format(dateReviews)); 
        return tmp;
    }
}
