package com.tanphi.laptopshop.service;


import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.request.authen.UpdateProfileRequest;

public interface AccountsService {
    Accounts findAccountByGmail(String email);
    Accounts findAccountById(Integer id);
    void updateProfile(Integer customerId,UpdateProfileRequest request);
}
