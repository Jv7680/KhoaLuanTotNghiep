package com.tanphi.laptopshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="orderdetail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailIDKey orderDetailIDKey;
    private Integer quantity;
    

	public OrderDetailIDKey getOrderDetailIDKey() {
		return orderDetailIDKey;
	}

	public void setOrderDetailIDKey(OrderDetailIDKey orderDetailIDKey) {
		this.orderDetailIDKey = orderDetailIDKey;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne
    @JoinColumn (name = "orderId", nullable = false,referencedColumnName = "orderId",insertable=false, updatable=false)
    @JsonBackReference
    private Orders order;

    @ManyToOne
    @JoinColumn (name = "productId", nullable = false,referencedColumnName = "productId",insertable=false, updatable=false)
    @JsonBackReference
    private Product product;
}
