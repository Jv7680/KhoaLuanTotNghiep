package com.tanphi.laptopshop.response.product;

import com.tanphi.laptopshop.response.reviews.GetListReviewsResponse;

import lombok.Data;

@Data
public class GetProductResponse {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private String image;
    private Integer unitprice;
    private Integer discount;
    private String description;
    private String CPU;
    private String ram;
    private String weight;
    private String hardDiskCapacity;
    private String hardDiskType;
    private String screenSize;
    private String screenResolution;
    private String graphicCardName;
    private String graphicCardMemory;
    private String batteryCapacity;
    private String osSupplied;
    private GetListReviewsResponse reviewsResponses;
}
