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
@Table(name = "product_category")
public class CategoryDetails	 {
	
	@Id
	@Column(name = "id")
	@JsonProperty("ID")
	public UUID id;
	
	@Column(name = "category_id")
	@JsonProperty("Category ID")
	public String categoryID;
	
	@Column(name = "category")
	@JsonProperty("Category")
	public String category;
	
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

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public CategoryDetails(UUID id, String categoryID, String category, Timestamp createdDate, String createdBy) {
		super();
		this.id = id;
		this.categoryID = categoryID;
		this.category = category;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	public CategoryDetails() {
		super();
	}	
}
