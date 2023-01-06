package com.tanphi.laptopshop.response.cart;

import java.util.List;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.Product;

import lombok.Data;
@Data
public class GetAllCartByCustomerResponse {
    private Integer id;
    private List<GetCartByCustomerResponse> listCarts;
    
}
