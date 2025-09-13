package com.sy.fsm.Model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetails {
    
    @Id
    @Column(name = "id")
    @JsonProperty("ID")
    private UUID id;
    
    @Column(name = "file_name")
    @JsonProperty("File Name")
    private String fileName;
    
    @Column(name = "user_id")
    @JsonProperty("User ID")
    private String userId;
    
    @Column(name = "user_name")
    @JsonProperty("User Name")
    private String userName;
    
    @Column(name = "leader_user_id")
    @JsonProperty("Leader User ID")
    private String leaderUserId;
    
    
    
    @Column(name = "password")
    @JsonProperty("Password")
    private String password;
    
    @Column(name = "email_id")
    @JsonProperty("Email ID")
    private String emailId;
    
    @Column(name = "mobile_no")
    @JsonProperty("Mobile No")
    private String mobileNo;
    
    @Column(name = "role_name")
    @JsonProperty("Role Name")
    private String roleName;
    
    @Column(name = "branch")
    @JsonProperty("Branch")
    private String branch;
    
    @Column(name = "rep_code")
    @JsonProperty("Rep Code")
    private String repCode;
    
    @Column(name = "rep_account")
    @JsonProperty("Rep Account")
    private String repAccount;
    
    @Column(name = "user_rights", columnDefinition = "TEXT")
    @JsonProperty("User Rights")
    private String userRights;
    
    

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getRepCode() {
		return repCode;
	}

	public void setRepCode(String repCode) {
		this.repCode = repCode;
	}

	public String getRepAccount() {
		return repAccount;
	}

	public void setRepAccount(String repAccount) {
		this.repAccount = repAccount;
	}

	public String getUserRights() {
		return userRights;
	}

	public void setUserRights(String userRights) {
		this.userRights = userRights;
	}

	public String getLeaderUserId() {
		return leaderUserId;
	}

	public void setLeaderUserId(String leaderUserId) {
		this.leaderUserId = leaderUserId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public UserDetails(UUID id, String userId, String userName, String password, String emailId, String mobileNo,
			String roleName, String branch, String repCode, String repAccount, String userRights, String leaderUserId,
			String fileName) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.roleName = roleName;
		this.branch = branch;
		this.repCode = repCode;
		this.repAccount = repAccount;
		this.userRights = userRights;
		this.leaderUserId = leaderUserId;
		this.fileName = fileName;
	}

	public UserDetails() {
		super();
	}

	    
    
}
