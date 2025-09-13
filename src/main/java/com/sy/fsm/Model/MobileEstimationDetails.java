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
public class MobileEstimationDetails	 {
	
	@Id
	@Column(name = "id")
	public UUID id;
	
	@Column(name = "est_no")
	public String estNO;
	
	@Column(name = "customer_name")
	public String customerName;
	
	@Column(name = "estimation_process_date")
	public Timestamp estimationProcessDate;
	
	@Column(name = "rep_attd")
	public String repAttd;
	
	@Column(name = "rep_account")
	public String repAcc;

	@Column(name = "billing_address",columnDefinition="TEXT")
	public String billingAddress;
	
	@Column(name = "delivery_address",columnDefinition="TEXT")
	public String deliveryAddress;
	
	@Column(name = "customer_city")
	public String billingCity;
	
	@Column(name = "customer_pin_code")
	public String billingPin;
	
	@Column(name = "customer_phone")
	public String phone;
	
	@Column(name = "customer_email")
	public String email;
	
	@Column(name = "delivery_city")
	public String deliveryCity;
	
	@Column(name = "delivery_pin_code")
	public String deliveryPin;
			
	@Column(name = "warranty")
	public String warranty;	
	
	@Column(name = "pan_and_gst")
	public String panGst;
	
	@Column(name = "total_product")
	public String totalProduct;
	
	@Column(name = "ref")
	public String ref;
	
	@Column(name = "remarks",columnDefinition="TEXT")
	public String remarks;
	
	@Column(name = "its_have_discount")
	public String itsHaveDiscount;
	
	@Column(name = "discount_estimate")
	public String discountEstimate;
	
	@Column(name = "demo_piece_estimate")
	public String demoPieceEstimate;
	
	@Column(name = "stock_clearance_estimate")
	public String stockClearanceEstimate;
	
	@Column(name = "discount_amount")
	public String discountAmount;
	
	@Column(name = "gst")
	public String gst;
	
	@Column(name = "delivery_charges")
	public String deliveryCharges;
	
	@Column(name = "total_amount")
	public String totalAmount;
	
	@Column(name = "register_status")
	public String registerStatus;
	
	@Column(name = "created_date")
	public Timestamp createdDate;
	
	@Column(name = "created_by")
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

	public String getRepAcc() {
		return repAcc;
	}

	public void setRepAcc(String repAcc) {
		this.repAcc = repAcc;
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

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingPin() {
		return billingPin;
	}

	public void setBillingPin(String billingPin) {
		this.billingPin = billingPin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getDeliveryPin() {
		return deliveryPin;
	}

	public void setDeliveryPin(String deliveryPin) {
		this.deliveryPin = deliveryPin;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getPanGst() {
		return panGst;
	}

	public void setPanGst(String panGst) {
		this.panGst = panGst;
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

	public MobileEstimationDetails(UUID id, String estNO, String customerName, Timestamp estimationProcessDate,
			String repAttd, String repAcc, String billingAddress, String deliveryAddress, String billingCity,
			String billingPin, String phone, String email, String deliveryCity, String deliveryPin, String warranty,
			String panGst, String totalProduct, String ref, String remarks, String itsHaveDiscount,
			String discountEstimate, String demoPieceEstimate, String stockClearanceEstimate, String discountAmount,
			String gst, String deliveryCharges, String totalAmount, String registerStatus, Timestamp createdDate,
			String createdBy) {
		super();
		this.id = id;
		this.estNO = estNO;
		this.customerName = customerName;
		this.estimationProcessDate = estimationProcessDate;
		this.repAttd = repAttd;
		this.repAcc = repAcc;
		this.billingAddress = billingAddress;
		this.deliveryAddress = deliveryAddress;
		this.billingCity = billingCity;
		this.billingPin = billingPin;
		this.phone = phone;
		this.email = email;
		this.deliveryCity = deliveryCity;
		this.deliveryPin = deliveryPin;
		this.warranty = warranty;
		this.panGst = panGst;
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

	public MobileEstimationDetails() {
		super();
	}

	
		
	
}
