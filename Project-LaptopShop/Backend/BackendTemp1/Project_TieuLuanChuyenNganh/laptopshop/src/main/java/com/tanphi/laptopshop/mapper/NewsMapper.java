package com.tanphi.laptopshop.mapper;

import com.tanphi.laptopshop.entity.News;
import com.tanphi.laptopshop.entity.Product;
import com.tanphi.laptopshop.entity.Reviews;
import com.tanphi.laptopshop.response.news.GetListNewsPageResponse;
import com.tanphi.laptopshop.response.product.GetAllProductPageResponse;
import com.tanphi.laptopshop.response.product.GetProductResponse;
import com.tanphi.laptopshop.response.reviews.GetListReviewsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NewsMapper {
    public static GetListNewsPageResponse toResponeGetListNewsPage(List<News> news,Integer currentPage,Integer totalPage)
    {
        GetListNewsPageResponse tmp=new GetListNewsPageResponse();
        tmp.setCurrentPage(currentPage);
        tmp.setListNews(news);
        tmp.setTotalPage(totalPage);
        return tmp;
    }
}
