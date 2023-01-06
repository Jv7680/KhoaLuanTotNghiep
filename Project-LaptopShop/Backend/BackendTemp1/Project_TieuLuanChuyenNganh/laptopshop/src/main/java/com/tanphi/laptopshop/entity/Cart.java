package com.tanphi.laptopshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Integer cartId;
    private Integer cartProductQuantity;
    private Integer isdeleted;

    public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getCartProductQuantity() {
		return cartProductQuantity;
	}

	public void setCartProductQuantity(Integer cartProductQuantity) {
		this.cartProductQuantity = cartProductQuantity;
	}

	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}

	@ManyToOne
    @JoinColumn (name = "accountId", nullable = false,referencedColumnName = "accountId")
    @JsonBackReference
    private Accounts accounts;

    @ManyToOne
    @JoinColumn (name = "productId", nullable = false,referencedColumnName = "productId")
    @JsonBackReference
    private Product product;
}
