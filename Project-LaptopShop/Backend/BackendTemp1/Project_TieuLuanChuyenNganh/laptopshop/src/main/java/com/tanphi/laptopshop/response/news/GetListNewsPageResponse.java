package com.tanphi.laptopshop.response.news;

import java.util.List;

import com.tanphi.laptopshop.entity.News;
import com.tanphi.laptopshop.response.product.GetProductResponse;

import lombok.Data;
@Data
public class GetListNewsPageResponse {
	private Integer totalPage;
	private Integer currentPage;
	private List<News> listNews;
}
