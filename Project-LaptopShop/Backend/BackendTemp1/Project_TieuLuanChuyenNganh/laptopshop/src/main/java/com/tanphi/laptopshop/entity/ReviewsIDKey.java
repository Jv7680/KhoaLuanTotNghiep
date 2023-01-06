package com.tanphi.laptopshop.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.google.common.base.Objects;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReviewsIDKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer accountId;
	private Integer productId;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ReviewsIDKey that = (ReviewsIDKey) o;
		return Objects.equal(orderId, that.orderId) && Objects.equal(productId, that.productId)
				&& Objects.equal(accountId, that.accountId);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(orderId, productId, accountId);
	}
}
