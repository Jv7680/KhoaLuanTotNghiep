package com.tanphi.laptopshop.security;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.enums.ActiveAccountStatus;
import com.tanphi.laptopshop.entity.enums.IsDeleteStatus;
import com.tanphi.laptopshop.exception.DuplicateRecoredException;
import com.tanphi.laptopshop.repository.AccountsRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
    private AccountsRepo accountsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws DuplicateRecoredException {
        Accounts accounts=accountsRepo.findAccountsByUsernameAndActiveAccount(username, ActiveAccountStatus.ACTIVE.getCode());
        if (accounts == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserPrincipal.create(accounts);
    }
}
