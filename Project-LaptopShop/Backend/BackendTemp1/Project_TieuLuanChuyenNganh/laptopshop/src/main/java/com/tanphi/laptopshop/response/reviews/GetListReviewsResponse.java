package com.tanphi.laptopshop.response.reviews;

import java.util.List;

import lombok.Data;
@Data
public class GetListReviewsResponse {
	private Double rating;
	private List<GetReviewsResponse> listReviews;
}
