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
@Table(name = "dar_expenses_details")
public class MobileDarExpensesDetails	 {
	
	@Id
	@Column(name = "id")
	public UUID id;
	
	@Column(name = "reference_id")
	public String referenceId;
	
	@Column(name = "expenses_description")
	public String expensesDescription;
	
	@Column(name = "expenses_amount")
	public String expensesAmount;
	
	@Column(name = "image_file_path")
	public String imageFilePath;
	



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


	public String getExpensesDescription() {
		return expensesDescription;
	}


	public void setExpensesDescription(String expensesDescription) {
		this.expensesDescription = expensesDescription;
	}


	public String getExpensesAmount() {
		return expensesAmount;
	}


	public void setExpensesAmount(String expensesAmount) {
		this.expensesAmount = expensesAmount;
	}


	public String getImageFilePath() {
		return imageFilePath;
	}


	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public MobileDarExpensesDetails(UUID id, String referenceId, String expensesDescription, String expensesAmount,
			String imageFilePath) {
		super();
		this.id = id;
		this.referenceId = referenceId;
		this.expensesDescription = expensesDescription;
		this.expensesAmount = expensesAmount;
		this.imageFilePath = imageFilePath;	
	}


	public MobileDarExpensesDetails() {
		super();
	}

	
}