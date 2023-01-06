package com.tanphi.laptopshop.mapper;

import com.tanphi.laptopshop.entity.Product;
import com.tanphi.laptopshop.entity.Reviews;
import com.tanphi.laptopshop.response.product.GetAllProductPageResponse;
import com.tanphi.laptopshop.response.product.GetProductResponse;
import com.tanphi.laptopshop.response.reviews.GetListReviewsResponse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductMapper {
    public static List<GetProductResponse> toResponeGetAllProduct(List<Product> productList) throws ParseException
    {
        List<GetProductResponse> list=new ArrayList<>();
        for(Product product:productList) {
        	GetProductResponse tmp=toResponeGetProduct(product);
            list.add(tmp);
        }
        return list;
    }
    public static GetProductResponse toResponeGetProduct(Product product) throws ParseException
    {
    	GetProductResponse tmp = new GetProductResponse();
    	GetListReviewsResponse tmpReviews=new GetListReviewsResponse();
    	Set<Reviews> setReviews=product.getReviewsSet();
    	Double doubleRating=0.0;
    	if(setReviews.size()>0)
    	{
    		doubleRating=setReviews.stream().mapToDouble(Reviews::getRate).average().getAsDouble();
    	}
    	tmpReviews.setRating(doubleRating);
    	tmpReviews.setListReviews(ReviewsMapper.toResponeGetListReviews(product.getReviewsSet()));
    	tmp.setReviewsResponses(tmpReviews);
        tmp.setProductId(product.getProductId());
        tmp.setProductName(product.getProductName());
        tmp.setQuantity(product.getQuantity());
        tmp.setImage(product.getImage());
        tmp.setUnitprice(product.getUnitprice());
        tmp.setDiscount(product.getDiscount());
        tmp.setDescription(product.getDescription());
        return tmp;
    }

	public static GetAllProductPageResponse<GetProductResponse> toResponeGetAllProductPage(List<Product> productList,Integer currentPage,Integer totalPage) throws ParseException {
		GetAllProductPageResponse<GetProductResponse> tmp=new GetAllProductPageResponse<>();
		tmp.setCurrentPage(currentPage);
		tmp.setTotalPage(totalPage);
		List<GetProductResponse> listProducts=toResponeGetAllProduct(productList);
		tmp.setListProducts(listProducts);
		return tmp;
	}
	public static GetAllProductPageResponse<Product> toResponeGetAllProductPageAdmin(List<Product> productList,Integer currentPage,Integer totalPage) throws ParseException {
		GetAllProductPageResponse<Product> tmp=new GetAllProductPageResponse<>();
		tmp.setCurrentPage(currentPage);
		tmp.setTotalPage(totalPage);
		tmp.setListProducts(productList);
		return tmp;
	}
}
