package com.tanphi.laptopshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="reviews")
public class Reviews {
	@EmbeddedId
	private ReviewsIDKey reviewsIDKey;
    private String contents;
    private Double rate;
    @Column(columnDefinition = "Date")
    private Timestamp reviewsDate;
    private Integer isdeleted;


	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Timestamp getReviewsDate() {
		return reviewsDate;
	}

	public void setReviewsDate(Timestamp reviewsDate) {
		this.reviewsDate = reviewsDate;
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


	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	public ReviewsIDKey getReviewsIDKey() {
		return reviewsIDKey;
	}

	public void setReviewsIDKey(ReviewsIDKey reviewsIDKey) {
		this.reviewsIDKey = reviewsIDKey;
	}

	public Integer getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}


	@ManyToOne
    @JoinColumn(name = "orderId", nullable = false,referencedColumnName = "orderId",insertable=false, updatable=false)
    @JsonBackReference
    private Orders order;

    @ManyToOne
    @JoinColumn (name = "productId", nullable = false,referencedColumnName = "productId",insertable=false, updatable=false)
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "accountId",nullable = false,referencedColumnName = "accountId",insertable = false,updatable = false)
    @JsonBackReference
    private Accounts accounts;
}
