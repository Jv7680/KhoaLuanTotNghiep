package com.tanphi.laptopshop.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.google.common.base.Objects;

@Embeddable
public class OrderDetailIDKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer productId;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
		OrderDetailIDKey that = (OrderDetailIDKey) o;
		return Objects.equal(orderId, that.orderId) && Objects.equal(productId, that.productId);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(orderId, productId);
	}
}
