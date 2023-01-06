package com.tanphi.laptopshop.entity;

import lombok.Data;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="accounts")
@Data
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    private String username;
    private String passwords;
    private String gmail;
    private String firstname;
    private String lastname;
    private String address;
    private String phonenumber;
    private String activationCode;
    private String passwordresetCode;
    private Integer activeAccount;
    private Integer provider;
    private Integer roles;
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswords() {
		return passwords;
	}
	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	public String getPasswordresetCode() {
		return passwordresetCode;
	}
	public void setPasswordresetCode(String passwordresetCode) {
		this.passwordresetCode = passwordresetCode;
	}
	public Integer getActiveAccount() {
		return activeAccount;
	}
	public void setActiveAccount(Integer activeAccount) {
		this.activeAccount = activeAccount;
	}
	public Integer getProvider() {
		return provider;
	}
	public void setProvider(Integer provider) {
		this.provider = provider;
	}
	public Integer getRoles() {
		return roles;
	}
	public void setRoles(Integer roles) {
		this.roles = roles;
	}
	
	@OneToMany(mappedBy = "accounts",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<News> news;	
	@OneToMany(mappedBy = "accounts",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Cart> carts;
	
	@OneToMany(mappedBy = "accounts",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Orders> orders;
}
