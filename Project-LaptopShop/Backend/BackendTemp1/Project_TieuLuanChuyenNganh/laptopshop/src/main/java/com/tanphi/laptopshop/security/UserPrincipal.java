package com.tanphi.laptopshop.security;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.enums.Roles;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("serial")
public class UserPrincipal implements UserDetails {

    private Integer id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Integer id, String email, String password, List<GrantedAuthority> authorities) {
		this.id=id;
		this.email=email;
		this.password=password;
		this.authorities=authorities;
	}

	public static UserPrincipal create(Accounts account) {
		String userRole;
		if(account.getRoles()==Roles.ADMIN.getCode())
		{
			userRole = Roles.ADMIN.toString();
		}
		else {
			userRole = Roles.CUSTOMER.toString();
		}
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(userRole));
        return new UserPrincipal(account.getAccountId(), account.getGmail(), account.getPasswords(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
