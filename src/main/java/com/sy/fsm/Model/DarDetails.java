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
@Table(name = "dar_details")
public class DarDetails	 {
	
	@Id
	@Column(name = "id")
	@JsonProperty("ID")
	public UUID id;
	
	@Column(name = "dar_no")
	@JsonProperty("DAR.NO")
	public String darNO;
	
	@Column(name = "dar_process_date")
	@JsonProperty("DAR Process Date")
	public Timestamp darProcessDate;
	
	@Column(name = "planned_activity",columnDefinition="TEXT")
	@JsonProperty("Planned Activity")
	public String plannedActivity;
	
	@Column(name = "delivery_place_name_and_address",columnDefinition="TEXT")
	@JsonProperty("Delivery Place Name And Address")
	public String deliveryPlaceNameAndAddress;
	
	@Column(name = "state_cum_area")
	@JsonProperty("State Cum Area")
	public String stateCumArea;
	
	@Column(name = "client_name")
	@JsonProperty("Client Name")
	public String clientName;
	
	@Column(name = "client_mobile_no")
	@JsonProperty("Client Mobile No")
	public String clientMobileNO;
		
	@Column(name = "about_the_client",columnDefinition="TEXT")
	@JsonProperty("About the Client")
	public String aboutTheClient;
	
	@Column(name = "product_details",columnDefinition="TEXT")
	@JsonProperty("Product Details")
	public String productDetails;
	
	@Column(name = "from_location",columnDefinition="TEXT")
	@JsonProperty("From Location")
	public String fromLocation;
	
	@Column(name = "to_location",columnDefinition="TEXT")
	@JsonProperty("To Location")
	public String toLocation;
	
	@Column(name = "total_expenses")
	@JsonProperty("Total Expenses")
	public String totalExpenses;
	
	@Column(name = "status_to_visit")
	@JsonProperty("Status To Visit")
	public String statusToVisit;
	
	@Column(name = "remarks")
	@JsonProperty("Remarks")
	public String remarks;
	
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

	public String getDarNO() {
		return darNO;
	}

	public void setDarNO(String darNO) {
		this.darNO = darNO;
	}

	public Timestamp getDarProcessDate() {
		return darProcessDate;
	}

	public void setDarProcessDate(Timestamp darProcessDate) {
		this.darProcessDate = darProcessDate;
	}

	public String getPlannedActivity() {
		return plannedActivity;
	}

	public void setPlannedActivity(String plannedActivity) {
		this.plannedActivity = plannedActivity;
	}

	public String getDeliveryPlaceNameAndAddress() {
		return deliveryPlaceNameAndAddress;
	}

	public void setDeliveryPlaceNameAndAddress(String deliveryPlaceNameAndAddress) {
		this.deliveryPlaceNameAndAddress = deliveryPlaceNameAndAddress;
	}

	public String getStateCumArea() {
		return stateCumArea;
	}

	public void setStateCumArea(String stateCumArea) {
		this.stateCumArea = stateCumArea;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientMobileNO() {
		return clientMobileNO;
	}

	public void setClientMobileNO(String clientMobileNO) {
		this.clientMobileNO = clientMobileNO;
	}

	public String getAboutTheClient() {
		return aboutTheClient;
	}

	public void setAboutTheClient(String aboutTheClient) {
		this.aboutTheClient = aboutTheClient;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(String totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public String getStatusToVisit() {
		return statusToVisit;
	}

	public void setStatusToVisit(String statusToVisit) {
		this.statusToVisit = statusToVisit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public DarDetails(UUID id, String darNO, Timestamp darProcessDate, String plannedActivity,
			String deliveryPlaceNameAndAddress, String stateCumArea, String clientName, String clientMobileNO,
			String aboutTheClient, String productDetails, String fromLocation, String toLocation, String totalExpenses,
			String statusToVisit, String remarks, Timestamp createdDate, String createdBy) {
		super();
		this.id = id;
		this.darNO = darNO;
		this.darProcessDate = darProcessDate;
		this.plannedActivity = plannedActivity;
		this.deliveryPlaceNameAndAddress = deliveryPlaceNameAndAddress;
		this.stateCumArea = stateCumArea;
		this.clientName = clientName;
		this.clientMobileNO = clientMobileNO;
		this.aboutTheClient = aboutTheClient;
		this.productDetails = productDetails;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.totalExpenses = totalExpenses;
		this.statusToVisit = statusToVisit;
		this.remarks = remarks;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	public DarDetails() {
		super();
	}

		
}
