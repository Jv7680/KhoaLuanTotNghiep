package com.tanphi.laptopshop.service;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.enums.IsDeleteStatus;
import com.tanphi.laptopshop.repository.AccountsRepo;
import com.tanphi.laptopshop.request.authen.UpdateProfileRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    AccountsRepo accountsRepo;
    @Override
    public Accounts findAccountByGmail(String email) {
        return accountsRepo.findAccountsByGmailAndActiveAccount(email, IsDeleteStatus.NO.getCode());
    }
	@Override
	public Accounts findAccountById(Integer id) {
		// TODO Auto-generated method stub
		return accountsRepo.findAccountsByAccountId(id);
	}
	@Override
	public void updateProfile(Integer customerId,UpdateProfileRequest request) {
		// TODO Auto-generated method stub
		Accounts updateAccount=findAccountById(customerId);
		updateAccount.setFirstname(request.getFirstName());
		updateAccount.setLastname(request.getLastName());
		updateAccount.setAddress(request.getAddress());
		accountsRepo.save(updateAccount);
	}
}
