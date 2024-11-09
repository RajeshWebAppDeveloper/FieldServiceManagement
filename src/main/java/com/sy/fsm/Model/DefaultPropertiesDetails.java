package com.sy.fsm.Model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "default_properties")
public class DefaultPropertiesDetails {
	@Id	 
	@Column(name = "id")
	@JsonProperty("ID")
	 private UUID id;
	 
	 @Column(name = "property_name")
	 @JsonProperty("Property Name")
	 private String propertyName;
	 
	 @Column(name = "property_value")
	 @JsonProperty("Property Value")
	 private String propertyValue;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public DefaultPropertiesDetails(UUID id, String propertyName, String propertyValue) {
		super();
		this.id = id;
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}

	public DefaultPropertiesDetails() {
		super();
	}
	 
	 
}