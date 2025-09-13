package com.sy.fsm.Model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MobileExpenseRequest {

	private List<MultipartFile> imageFiles;
	private List<MobileDarExpensesDetails> expenses;
	public List<MultipartFile> getImageFiles() {
		return imageFiles;
	}
	public void setImageFiles(List<MultipartFile> imageFiles) {
		this.imageFiles = imageFiles;
	}
	public List<MobileDarExpensesDetails> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<MobileDarExpensesDetails> expenses) {
		this.expenses = expenses;
	}
	public MobileExpenseRequest(List<MultipartFile> imageFiles, List<MobileDarExpensesDetails> expenses) {
		super();
		this.imageFiles = imageFiles;
		this.expenses = expenses;
	}
	public MobileExpenseRequest() {
		super();
	}
}
