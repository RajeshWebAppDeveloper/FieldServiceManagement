package com.sy.fsm.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.sy.fsm.Model.CategoryDetails;
import com.sy.fsm.Model.ProductDetails;
import com.sy.fsm.Repository.CategoryDetailsRepository;
import com.sy.fsm.Repository.ProductDetailsRepository;

@org.springframework.web.bind.annotation.RestController
public class ImportCSVFileController {
	
	@Autowired
	CategoryDetailsRepository categoryDetailsRepository;
	
	@Autowired
	ProductDetailsRepository productDetailsRepository;
	
	@PostMapping(value = "/fsm/importCategoryCSVfile", consumes = "multipart/form-data")
	public String getCategoryDetailsList(@RequestParam("file") MultipartFile file) {
	    System.out.println("/fsm/importCategoryCSVfile:::::::::::::");
	    String vResponse = "{\"status\":\"false\"}";
	    List<CategoryDetails> categories = new ArrayList<>();

	    try {	       
	        if (file.isEmpty()) {
	            vResponse = "{\"status\":\"false\", \"message\":\"No file uploaded.\"}";
	            return vResponse;
	        }

	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
	             CSVReader csvReader = new CSVReader(reader)) {

	            String[] line;
	            boolean skipHeader = true;
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	            while ((line = csvReader.readNext()) != null) {
	                if (skipHeader) {
	                    skipHeader = false;  
	                    continue;
	                }

	                if (line.length < 3) {
	                    vResponse = "{\"status\":\"false\", \"message\":\"Invalid CSV format.\"}";
	                    return vResponse;
	                }

	                UUID id = UUID.randomUUID();
	                String categoryID = line[0].trim();
	                String category = line[1].trim();
	                String createdBy = line[2].trim();
	                
	                String formattedDate = dateFormat.format(new Date()); 
	                Timestamp createdDate = new Timestamp(dateFormat.parse(formattedDate).getTime());

	                CategoryDetails categoryDetails = new CategoryDetails(id, categoryID, category, createdDate, createdBy);
	                categories.add(categoryDetails);
	            }

	            if (!categories.isEmpty()) {
	                categoryDetailsRepository.saveAll(categories);
	                vResponse = "{\"status\":\"true\"}";
	            } else {
	                vResponse = "{\"status\":\"false\", \"message\":\"No valid data to import.\"}";
	            }

	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        vResponse = "{\"status\":\"false\", \"message\":\"An error occurred: " + e.getMessage() + "\"}";
	    }

	    System.out.println("vResponse:::::::::::::" + vResponse);
	    return vResponse;
	}


	@PostMapping(value = "/fsm/importProductCSVfile", consumes = "multipart/form-data")
	public ResponseEntity<Map<String, Object>> importProductCSVfile(@RequestParam("file") MultipartFile file) {
	    System.out.println("/fsm/importProductCSVfile:::::::::::::");
	    
	    Map<String, Object> vResponse = new HashMap<>();
	    List<ProductDetails> products = new ArrayList<>();

	    try {
	        if (file.isEmpty()) {
	            vResponse.put("status", "false");
	            vResponse.put("message", "No file uploaded.");
	            return ResponseEntity.badRequest().body(vResponse);
	        }

	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
	             CSVReader csvReader = new CSVReader(reader)) {

	            String[] line;
	            boolean skipHeader = true;
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	            while ((line = csvReader.readNext()) != null) {
	                if (skipHeader) {
	                    skipHeader = false;  // Skip the header row
	                    continue;
	                }

	                // Validate the number of columns
	                if (line.length < 6) {
	                    vResponse.put("status", "false");
	                    vResponse.put("message", "Invalid CSV format: Insufficient columns.");
	                    return ResponseEntity.badRequest().body(vResponse);
	                }

	                // Extract CSV values
	                String productID = line[0].trim();
	                String productName = line[1].trim();
	                String productCategory = line[2].trim();
	                String unitPrice = line[3].trim();
	                String tax = line[4].trim();
	                String createdBy = line[5].trim();

	                // Validate unitPrice (should be a valid number)
	                try {
	                    Double.parseDouble(unitPrice); // If invalid, it will throw a NumberFormatException
	                } catch (NumberFormatException e) {
	                    vResponse.put("status", "false");
	                    vResponse.put("message", "Invalid value for unitPrice: " + unitPrice);
	                    return ResponseEntity.badRequest().body(vResponse);
	                }

	                // Create product with the current timestamp as creation date
	                UUID id = UUID.randomUUID();
	                String formattedDate = dateFormat.format(new Date());
	                Timestamp createdDate = new Timestamp(dateFormat.parse(formattedDate).getTime());

	                // Add product to the list
	                ProductDetails product = new ProductDetails(id, productID, productName, productCategory, unitPrice, tax, createdDate, createdBy);
	                products.add(product);
	            }

	            // Save products to the repository if there is valid data
	            if (!products.isEmpty()) {
	                productDetailsRepository.saveAll(products);
	                vResponse.put("status", "true");
	            } else {
	                vResponse.put("status", "false");
	                vResponse.put("message", "No valid data to import.");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        vResponse.put("status", "false");
	        vResponse.put("message", "An error occurred: " + e.getMessage());
	    }

	    System.out.println("vResponse:::::::::::::" + vResponse);
	    return ResponseEntity.ok(vResponse);
	}

}
