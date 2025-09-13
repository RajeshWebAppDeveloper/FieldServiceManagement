package com.sy.fsm.Model;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estimation_product_details")
public class EstimationProductDetails	 {
	
	@Id
	@Column(name = "id")
	@JsonProperty("ID")
	public UUID id;
	
	@Column(name = "reference_id")
	@JsonProperty("Reference ID")
	public String referenceId;
	
	@Column(name = "product_details")
	@JsonProperty("Product Details")
	public String productDetails;
	
	@Column(name = "product_code")
	@JsonProperty("Product Code")
	public String productCode;
	
	@Column(name = "qty")
	@JsonProperty("Qty")
	public int qty;
	
	@Column(name = "unit_price")
	@JsonProperty("Unit Price")
	public int unitPrice;
	
	@Column(name = "tax")
	@JsonProperty("Tax")
	public int tax;
	
	@Column(name = "total")
	@JsonProperty("Total")
	public float total;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public EstimationProductDetails(UUID id, String referenceId, String productDetails, String productCode, int qty,
			int unitPrice, int tax, float total) {
		super();
		this.id = id;
		this.referenceId = referenceId;
		this.productDetails = productDetails;
		this.productCode = productCode;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.tax = tax;
		this.total = total;
	}

	public EstimationProductDetails() {
		super();
	}
	
	
}
