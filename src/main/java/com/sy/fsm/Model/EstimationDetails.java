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
@Table(name = "estimation_details")
public class EstimationDetails	 {
	
	@Id
	@Column(name = "id")
	@JsonProperty("ID")
	public UUID id;
	
	@Column(name = "est_no")
	@JsonProperty("EST.NO")
	public String estNO;
	
	@Column(name = "customer_name")
	@JsonProperty("Customer Name")
	public String customerName;
	
	@Column(name = "estimation_process_date")
	@JsonProperty("Estimation Process Date")
	public Timestamp estimationProcessDate;
	
	@Column(name = "rep_attd")
	@JsonProperty("Rep Attd")
	public String repAttd;
	
	@Column(name = "rep_account")
	@JsonProperty("Rep A/C")
	public String repAccount;

	@Column(name = "billing_address",columnDefinition="TEXT")
	@JsonProperty("Billing Address")
	public String billingAddress;
	
	@Column(name = "delivery_address",columnDefinition="TEXT")
	@JsonProperty("Delivery Address")
	public String deliveryAddress;
	
	@Column(name = "customer_city")
	@JsonProperty("Customer City")
	public String customerCity;
	
	@Column(name = "customer_pin_code")
	@JsonProperty("Customer Pin Code")
	public String customerPinCode;
	
	@Column(name = "customer_phone")
	@JsonProperty("Customer Phone")
	public String customerPhone;
	
	@Column(name = "customer_email")
	@JsonProperty("Customer Email")
	public String customerEmail;
	
	@Column(name = "delivery_city")
	@JsonProperty("Delivery City")
	public String deliveryCity;
	
	@Column(name = "delivery_pin_code")
	@JsonProperty("Delivery Pin Code")
	public String deliveryPinCode;
			
	@Column(name = "warranty")
	@JsonProperty("Warranty")
	public String warranty;	
	
	@Column(name = "pan_and_gst")
	@JsonProperty("Pan / GST")
	public String panAndGst;
	
	@Column(name = "total_product")
	@JsonProperty("Total Product")
	public String totalProduct;
	
	@Column(name = "ref")
	@JsonProperty("Ref")
	public String ref;
	
	@Column(name = "remarks",columnDefinition="TEXT")
	@JsonProperty("Remarks")
	public String remarks;
	
	@Column(name = "its_have_discount")
	@JsonProperty("Its Have Discount")
	public String itsHaveDiscount;
	
	@Column(name = "discount_estimate")
	@JsonProperty("Discount Estimate")
	public String discountEstimate;
	
	@Column(name = "demo_piece_estimate")
	@JsonProperty("Demo Piece Estimate")
	public String demoPieceEstimate;
	
	@Column(name = "stock_clearance_estimate")
	@JsonProperty("Stock Clearance Estimate")
	public String stockClearanceEstimate;
	
	@Column(name = "discount_amount")
	@JsonProperty("Discount Amount")
	public String discountAmount;
	
	@Column(name = "gst")
	@JsonProperty("GST")
	public String gst;
	
	@Column(name = "delivery_charges")
	@JsonProperty("Delivery Charges")
	public String deliveryCharges;
	
	@Column(name = "total_amount")
	@JsonProperty("Total Amount")
	public String totalAmount;
	
	@Column(name = "register_status")
	@JsonProperty("Estimation Status")
	public String registerStatus;
	
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

	public String getEstNO() {
		return estNO;
	}

	public void setEstNO(String estNO) {
		this.estNO = estNO;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Timestamp getEstimationProcessDate() {
		return estimationProcessDate;
	}

	public void setEstimationProcessDate(Timestamp estimationProcessDate) {
		this.estimationProcessDate = estimationProcessDate;
	}

	public String getRepAttd() {
		return repAttd;
	}

	public void setRepAttd(String repAttd) {
		this.repAttd = repAttd;
	}

	public String getRepAccount() {
		return repAccount;
	}

	public void setRepAccount(String repAccount) {
		this.repAccount = repAccount;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerPinCode() {
		return customerPinCode;
	}

	public void setCustomerPinCode(String customerPinCode) {
		this.customerPinCode = customerPinCode;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getDeliveryPinCode() {
		return deliveryPinCode;
	}

	public void setDeliveryPinCode(String deliveryPinCode) {
		this.deliveryPinCode = deliveryPinCode;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getPanAndGst() {
		return panAndGst;
	}

	public void setPanAndGst(String panAndGst) {
		this.panAndGst = panAndGst;
	}

	public String getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(String totalProduct) {
		this.totalProduct = totalProduct;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getItsHaveDiscount() {
		return itsHaveDiscount;
	}

	public void setItsHaveDiscount(String itsHaveDiscount) {
		this.itsHaveDiscount = itsHaveDiscount;
	}

	public String getDiscountEstimate() {
		return discountEstimate;
	}

	public void setDiscountEstimate(String discountEstimate) {
		this.discountEstimate = discountEstimate;
	}

	public String getDemoPieceEstimate() {
		return demoPieceEstimate;
	}

	public void setDemoPieceEstimate(String demoPieceEstimate) {
		this.demoPieceEstimate = demoPieceEstimate;
	}

	public String getStockClearanceEstimate() {
		return stockClearanceEstimate;
	}

	public void setStockClearanceEstimate(String stockClearanceEstimate) {
		this.stockClearanceEstimate = stockClearanceEstimate;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(String deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(String registerStatus) {
		this.registerStatus = registerStatus;
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

	public EstimationDetails(UUID id, String estNO, String customerName, Timestamp estimationProcessDate,
			String repAttd, String repAccount, String billingAddress, String deliveryAddress, String customerCity,
			String customerPinCode, String customerPhone, String customerEmail, String deliveryCity,
			String deliveryPinCode, String warranty, String panAndGst, String totalProduct, String ref, String remarks,
			String itsHaveDiscount, String discountEstimate, String demoPieceEstimate, String stockClearanceEstimate,
			String discountAmount, String gst, String deliveryCharges, String totalAmount, String registerStatus,
			Timestamp createdDate, String createdBy) {
		super();
		this.id = id;
		this.estNO = estNO;
		this.customerName = customerName;
		this.estimationProcessDate = estimationProcessDate;
		this.repAttd = repAttd;
		this.repAccount = repAccount;
		this.billingAddress = billingAddress;
		this.deliveryAddress = deliveryAddress;
		this.customerCity = customerCity;
		this.customerPinCode = customerPinCode;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.deliveryCity = deliveryCity;
		this.deliveryPinCode = deliveryPinCode;
		this.warranty = warranty;
		this.panAndGst = panAndGst;
		this.totalProduct = totalProduct;
		this.ref = ref;
		this.remarks = remarks;
		this.itsHaveDiscount = itsHaveDiscount;
		this.discountEstimate = discountEstimate;
		this.demoPieceEstimate = demoPieceEstimate;
		this.stockClearanceEstimate = stockClearanceEstimate;
		this.discountAmount = discountAmount;
		this.gst = gst;
		this.deliveryCharges = deliveryCharges;
		this.totalAmount = totalAmount;
		this.registerStatus = registerStatus;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	public EstimationDetails() {
		super();
	}
	
	
}
