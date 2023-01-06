package com.tanphi.laptopshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.enums.ActiveAccountStatus;
import com.tanphi.laptopshop.entity.enums.Roles;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.repository.AccountsRepo;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomAuthenticationProviderService implements AuthenticationProvider {
    @Autowired
    private AccountsRepo accountsRepo;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken=null;
        String username=authentication.getName();
        String passwrod=authentication.getCredentials().toString();
        Accounts account=accountsRepo.findAccountsByUsername(username);
        if(account!=null)
        {
            if(username.equals(account.getUsername()) && BCrypt.checkpw(passwrod,account.getPasswords()))
            {
                if(account.getActiveAccount()==ActiveAccountStatus.NO_ACTIVE.getCode())
                {
                    throw new BadRequestException("Tài khoản chưa kích hoạt. Vui lòng kiểm tra lại email để hoàn tất đăng kí");
                }
                Collection<GrantedAuthority> grantedAuthorities=getGrantedAuthority(account);
                authenticationToken=new UsernamePasswordAuthenticationToken(new User(username,passwrod,grantedAuthorities),passwrod,grantedAuthorities);
            }
            else{
                throw new BadRequestException("Nhập sai mật khẩu hoặc tên tài khoản");
            }
        }
        else {
            throw new UsernameNotFoundException("Username " +username+" not found");
        }
        return authenticationToken;
    }

    private Collection<GrantedAuthority> getGrantedAuthority(Accounts account) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(account.getRoles()==Roles.CUSTOMER.getCode()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(Roles.CUSTOMER.toString()));
        }
        else if(account.getRoles()==Roles.ADMIN.getCode())
        {
        	grantedAuthorities.add(new SimpleGrantedAuthority(Roles.ADMIN.toString()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
