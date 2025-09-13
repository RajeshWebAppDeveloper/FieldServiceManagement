package com.sy.fsm.Model;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_details")
public class ProductDetails	 {
	
	@Id
	@Column(name = "id")
	@JsonProperty("ID")
	public UUID id;
	
	@Column(name = "product_id")
	@JsonProperty("Product ID")
	public String productID;
	
	@Column(name = "product_name")
	@JsonProperty("Product Name")
	public String productName;
	
	@Column(name = "product_category")
	@JsonProperty("Product Category")
	public String productCategory;
	
	@Column(name = "unit_price")
	@JsonProperty("Unit Price")
	public String unitPrice;
	
	@Column(name = "tax")
	@JsonProperty("Tax")
	public String tax;
	
	@Column(name = "created_date")
	@JsonProperty("Created Date")
	public Timestamp createdDate;
	
	@Column(name = "created_by")
	@JsonProperty("Created By")
	public String createdBy;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public ProductDetails(UUID id, String productID, String productName, String productCategory, String unitPrice,
			String tax, Timestamp createdDate, String createdBy) {
		super();
		this.id = id;
		this.productID = productID;
		this.productName = productName;
		this.productCategory = productCategory;
		this.unitPrice = unitPrice;
		this.tax = tax;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	public ProductDetails() {
		super();
	}

}
