package com.sy.fsm.Model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ExpenseRequest {

	 private List<MultipartFile> imageFiles;
	 private List<DarExpensesDetails> expenses;
	public List<MultipartFile> getImageFiles() {
		return imageFiles;
	}
	public void setImageFiles(List<MultipartFile> imageFiles) {
		this.imageFiles = imageFiles;
	}
	public List<DarExpensesDetails> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<DarExpensesDetails> expenses) {
		this.expenses = expenses;
	}
	public ExpenseRequest() {
		super();
	}
}
