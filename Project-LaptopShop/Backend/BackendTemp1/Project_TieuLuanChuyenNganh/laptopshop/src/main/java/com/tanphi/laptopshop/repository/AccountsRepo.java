package com.tanphi.laptopshop.repository;

import com.tanphi.laptopshop.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepo extends JpaRepository<Accounts,Integer> {
    Accounts findAccountsByGmailAndActiveAccount(String email,Integer status);
    Accounts findAccountsByActivationCode(String code);
    Accounts findAccountsByGmail(String email);
    Accounts findAccountsByPasswordresetCode(String code);
    Accounts findAccountsByUsernameAndRoles(String username,Integer roles);
    Accounts findAccountsByUsername(String username);
    Accounts findAccountsByUsernameAndActiveAccount(String username,Integer status);
    Accounts findAccountsByAccountId(Integer accountId);
    Integer countByRolesAndActiveAccount(Integer roles,Integer status);
}
