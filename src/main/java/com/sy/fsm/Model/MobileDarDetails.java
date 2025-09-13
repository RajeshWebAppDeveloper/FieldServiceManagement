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
public class MobileDarDetails	 {
	
	@Id
	@Column(name = "id")
	public UUID id;
	
	@Column(name = "dar_no")
	public String darNO;
	
	@Column(name = "dar_process_date")
	public Timestamp darProcessDate;
	
	@Column(name = "planned_activity",columnDefinition="TEXT")
	public String plannedActivity;
	
	@Column(name = "delivery_place_name_and_address",columnDefinition="TEXT")
	public String hospitalNameAndAddress;
	
	@Column(name = "state_cum_area")
	public String stateCumArea;
	
	@Column(name = "client_name")
	public String doctorName;
	
	@Column(name = "client_mobile_no")
	public String contactNumber;
		
	@Column(name = "about_the_client",columnDefinition="TEXT")
	public String aboutDoctor;
	
	@Column(name = "product_details",columnDefinition="TEXT")
	public String productDetails;
	
	@Column(name = "from_location",columnDefinition="TEXT")
	public String fromLocation;
	
	@Column(name = "to_location",columnDefinition="TEXT")
	public String toLocation;
	
	@Column(name = "total_expenses")
	public String totalAmount;
	
	@Column(name = "status_to_visit")
	public String statusToVisit;
	
	@Column(name = "remarks")
	public String remarks;
	
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

	public String getHospitalNameAndAddress() {
		return hospitalNameAndAddress;
	}

	public void setHospitalNameAndAddress(String hospitalNameAndAddress) {
		this.hospitalNameAndAddress = hospitalNameAndAddress;
	}

	public String getStateCumArea() {
		return stateCumArea;
	}

	public void setStateCumArea(String stateCumArea) {
		this.stateCumArea = stateCumArea;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAboutDoctor() {
		return aboutDoctor;
	}

	public void setAboutDoctor(String aboutDoctor) {
		this.aboutDoctor = aboutDoctor;
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

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
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

	public MobileDarDetails(UUID id, String darNO, Timestamp darProcessDate, String plannedActivity,
			String hospitalNameAndAddress, String stateCumArea, String doctorName, String contactNumber,
			String aboutDoctor, String productDetails, String fromLocation, String toLocation, String totalAmount,
			String statusToVisit, String remarks, Timestamp createdDate, String createdBy) {
		super();
		this.id = id;
		this.darNO = darNO;
		this.darProcessDate = darProcessDate;
		this.plannedActivity = plannedActivity;
		this.hospitalNameAndAddress = hospitalNameAndAddress;
		this.stateCumArea = stateCumArea;
		this.doctorName = doctorName;
		this.contactNumber = contactNumber;
		this.aboutDoctor = aboutDoctor;
		this.productDetails = productDetails;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.totalAmount = totalAmount;
		this.statusToVisit = statusToVisit;
		this.remarks = remarks;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	public MobileDarDetails() {
		super();
	}

			
		
}
