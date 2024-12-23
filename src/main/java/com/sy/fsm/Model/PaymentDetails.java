package com.sy.fsm.Model;


import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_details")
public class PaymentDetails {

    @Id
    @Column(name = "id")
    @JsonProperty("ID")
    private UUID id;

    @Column(name = "e_no")
    @JsonProperty("E.No")
    private String eNo;

    @Column(name = "est_no")
    @JsonProperty("Est No")
    private String estNo;

    @Column(name = "order_no")
    @JsonProperty("Order No")
    private String orderNo;

    @Column(name = "so_no")
    @JsonProperty("SO No")
    private String soNo;

    @Column(name = "d_d_no")
    @JsonProperty("D.D.No")
    private String ddNo;

    @Column(name = "customer_name")
    @JsonProperty("Customer Name")
    private String customerName;

    @Column(name = "order_process_date")
    @JsonProperty("Order Process Date")
    private Timestamp orderProcessDate;

    @Column(name = "rep_code")
    @JsonProperty("Rep Code")
    private String repCode;

    @Column(name = "billing_name")
    @JsonProperty("Billing Name")
    private String billingName;

    @Column(name = "billing_address", columnDefinition = "TEXT")
    @JsonProperty("Billing Address")
    private String billingAddress;

    @Column(name = "customer_city")
    @JsonProperty("Customer City")
    private String customerCity;

    @Column(name = "customer_pin_code")
    @JsonProperty("Customer Pin Code")
    private String customerPinCode;

    @Column(name = "customer_phone")
    @JsonProperty("Customer Phone")
    private String customerPhone;

    @Column(name = "customer_email")
    @JsonProperty("Customer Email")
    private String customerEmail;

    @Column(name = "delivery_city")
    @JsonProperty("Delivery City")
    private String deliveryCity;

    @Column(name = "demo_plan")
    @JsonProperty("Demo Plan")
    private String demoPlan;

    @Column(name = "payment_charges")
    @JsonProperty("Payment Charges")
    private String paymentCharges;

    @Column(name = "payment_term_date")
    @JsonProperty("Payment Term Date")
    private Timestamp paymentTermDate;

    @Column(name = "warranty")
    @JsonProperty("Warranty")
    private String warranty;

    @Column(name = "pan_and_gst")
    @JsonProperty("PAN / GST")
    private String panAndGst;

    @Column(name = "demo_date")
    @JsonProperty("Demo Date")
    private Timestamp demoDate;

    @Column(name = "delivery_address", columnDefinition = "TEXT")
    @JsonProperty("Delivery Address")
    private String deliveryAddress;

    @Column(name = "delivery_pin_code")
    @JsonProperty("Delivery Pin Code")
    private String deliveryPinCode;

    @Column(name = "expected_date")
    @JsonProperty("Expected Date")
    private Timestamp expectedDate;

    @Column(name = "ship_mode_name")
    @JsonProperty("Ship Mode Name")
    private String shipModeName;

    @Column(name = "remarks", columnDefinition = "TEXT")
    @JsonProperty("Remarks")
    private String remarks;

    @Column(name = "its_have_discount")
    @JsonProperty("Its Have Discount")
    private String itsHaveDiscount;

    @Column(name = "discount_estimate")
    @JsonProperty("Discount Estimate")
    private String discountEstimate;

    @Column(name = "demo_piece_estimate")
    @JsonProperty("Demo Piece Estimate")
    private String demoPieceEstimate;

    @Column(name = "stock_clearance_estimate")
    @JsonProperty("Stock Clearance Estimate")
    private String stockClearanceEstimate;

    @Column(name = "discount_amount")
    @JsonProperty("Discount Amount")
    private String discountAmount;

    @Column(name = "total_product_amount")
    @JsonProperty("Total Product Amount")
    private String totalProductAmount;

    @Column(name = "gst")
    @JsonProperty("GST")
    private String gst;

    @Column(name = "delivery_charges")
    @JsonProperty("Delivery Charges")
    private String deliveryCharges;

    @Column(name = "total_amount")
    @JsonProperty("Total Amount")
    private String totalAmount;

    @Column(name = "less_advance")
    @JsonProperty("Less Advance")
    private String lessAdvance;

    @Column(name = "balance")
    @JsonProperty("Balance")
    private String balance;

    @Column(name = "register_status")
    @JsonProperty("Payment Status")
    private String registerStatus;
    
    @Column(name = "other_status_description")
    @JsonProperty("Other Status Description")
    private String otherStatusDescription = "Null";
    
    @Column(name = "created_date")
	@JsonProperty("Created Date")
	public Timestamp createdDate;
	
	@Column(name = "created_by")
	@JsonProperty("Created By")
	public String createdBy;
	
	@Column(name = "payment_mode")
	@JsonProperty("Payment Mode")
	public String paymentMode;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String geteNo() {
		return eNo;
	}

	public void seteNo(String eNo) {
		this.eNo = eNo;
	}

	public String getEstNo() {
		return estNo;
	}

	public void setEstNo(String estNo) {
		this.estNo = estNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSoNo() {
		return soNo;
	}

	public void setSoNo(String soNo) {
		this.soNo = soNo;
	}

	public String getDdNo() {
		return ddNo;
	}

	public void setDdNo(String ddNo) {
		this.ddNo = ddNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Timestamp getOrderProcessDate() {
		return orderProcessDate;
	}

	public void setOrderProcessDate(Timestamp orderProcessDate) {
		this.orderProcessDate = orderProcessDate;
	}

	public String getRepCode() {
		return repCode;
	}

	public void setRepCode(String repCode) {
		this.repCode = repCode;
	}

	public String getBillingName() {
		return billingName;
	}

	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
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

	public String getDemoPlan() {
		return demoPlan;
	}

	public void setDemoPlan(String demoPlan) {
		this.demoPlan = demoPlan;
	}

	public String getPaymentCharges() {
		return paymentCharges;
	}

	public void setPaymentCharges(String paymentCharges) {
		this.paymentCharges = paymentCharges;
	}

	public Timestamp getPaymentTermDate() {
		return paymentTermDate;
	}

	public void setPaymentTermDate(Timestamp paymentTermDate) {
		this.paymentTermDate = paymentTermDate;
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

	public Timestamp getDemoDate() {
		return demoDate;
	}

	public void setDemoDate(Timestamp demoDate) {
		this.demoDate = demoDate;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryPinCode() {
		return deliveryPinCode;
	}

	public void setDeliveryPinCode(String deliveryPinCode) {
		this.deliveryPinCode = deliveryPinCode;
	}

	public Timestamp getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Timestamp expectedDate) {
		this.expectedDate = expectedDate;
	}

	public String getShipModeName() {
		return shipModeName;
	}

	public void setShipModeName(String shipModeName) {
		this.shipModeName = shipModeName;
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

	public String getTotalProductAmount() {
		return totalProductAmount;
	}

	public void setTotalProductAmount(String totalProductAmount) {
		this.totalProductAmount = totalProductAmount;
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

	public String getLessAdvance() {
		return lessAdvance;
	}

	public void setLessAdvance(String lessAdvance) {
		this.lessAdvance = lessAdvance;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(String registerStatus) {
		this.registerStatus = registerStatus;
	}

	public String getOtherStatusDescription() {
		return otherStatusDescription;
	}

	public void setOtherStatusDescription(String otherStatusDescription) {
		this.otherStatusDescription = otherStatusDescription;
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

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public PaymentDetails(UUID id, String eNo, String estNo, String orderNo, String soNo, String ddNo,
			String customerName, Timestamp orderProcessDate, String repCode, String billingName, String billingAddress,
			String customerCity, String customerPinCode, String customerPhone, String customerEmail,
			String deliveryCity, String demoPlan, String paymentCharges, Timestamp paymentTermDate, String warranty,
			String panAndGst, Timestamp demoDate, String deliveryAddress, String deliveryPinCode,
			Timestamp expectedDate, String shipModeName, String remarks, String itsHaveDiscount,
			String discountEstimate, String demoPieceEstimate, String stockClearanceEstimate, String discountAmount,
			String totalProductAmount, String gst, String deliveryCharges, String totalAmount, String lessAdvance,
			String balance, String registerStatus, String otherStatusDescription, Timestamp createdDate,
			String createdBy, String paymentMode) {
		super();
		this.id = id;
		this.eNo = eNo;
		this.estNo = estNo;
		this.orderNo = orderNo;
		this.soNo = soNo;
		this.ddNo = ddNo;
		this.customerName = customerName;
		this.orderProcessDate = orderProcessDate;
		this.repCode = repCode;
		this.billingName = billingName;
		this.billingAddress = billingAddress;
		this.customerCity = customerCity;
		this.customerPinCode = customerPinCode;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.deliveryCity = deliveryCity;
		this.demoPlan = demoPlan;
		this.paymentCharges = paymentCharges;
		this.paymentTermDate = paymentTermDate;
		this.warranty = warranty;
		this.panAndGst = panAndGst;
		this.demoDate = demoDate;
		this.deliveryAddress = deliveryAddress;
		this.deliveryPinCode = deliveryPinCode;
		this.expectedDate = expectedDate;
		this.shipModeName = shipModeName;
		this.remarks = remarks;
		this.itsHaveDiscount = itsHaveDiscount;
		this.discountEstimate = discountEstimate;
		this.demoPieceEstimate = demoPieceEstimate;
		this.stockClearanceEstimate = stockClearanceEstimate;
		this.discountAmount = discountAmount;
		this.totalProductAmount = totalProductAmount;
		this.gst = gst;
		this.deliveryCharges = deliveryCharges;
		this.totalAmount = totalAmount;
		this.lessAdvance = lessAdvance;
		this.balance = balance;
		this.registerStatus = registerStatus;
		this.otherStatusDescription = otherStatusDescription;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.paymentMode = paymentMode;
	}

	public PaymentDetails() {
		super();
	}

	
	
}
