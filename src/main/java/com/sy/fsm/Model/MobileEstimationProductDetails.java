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
public class MobileEstimationProductDetails	 {
	
	@Id
	@Column(name = "id")
	public UUID id;
	
	@Column(name = "reference_id")
	public String referenceId;
	
	@Column(name = "product_details")
	public String product;
	
	@Column(name = "product_code")
	public String serialNo;
	
	@Column(name = "qty")
	public int qty;
	
	@Column(name = "unit_price")
	public int unitPrice;
	
	@Column(name = "tax")
	public int tax;
	
	@Column(name = "total")
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

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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

	public MobileEstimationProductDetails(UUID id, String referenceId, String product, String serialNo, int qty,
			int unitPrice, int tax, float total) {
		super();
		this.id = id;
		this.referenceId = referenceId;
		this.product = product;
		this.serialNo = serialNo;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.tax = tax;
		this.total = total;
	}

	public MobileEstimationProductDetails() {
		super();
	}

		
	
}
