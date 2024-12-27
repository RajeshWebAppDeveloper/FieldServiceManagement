package com.sy.fsm.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sy.fsm.Model.DefaultPropertiesDetails;
import com.sy.fsm.Model.CategoryDetails;
import com.sy.fsm.Model.DarDetails;
import com.sy.fsm.Model.DarExpensesDetails;
import com.sy.fsm.Model.EstimationDetails;
import com.sy.fsm.Model.EstimationProductDetails;
import com.sy.fsm.Model.MobileEstimationProductDetails;
import com.sy.fsm.Model.MobileOrderProductDetails;
import com.sy.fsm.Model.OrderDetails;
import com.sy.fsm.Model.OrderProductDetails;
import com.sy.fsm.Model.PaymentDetails;
import com.sy.fsm.Model.ProductDetails;
import com.sy.fsm.Model.UserDetails;
import com.sy.fsm.Repository.CategoryDetailsRepository;
import com.sy.fsm.Repository.DarDetailsRepository;
import com.sy.fsm.Repository.DarExpensesDetailsRepository;
import com.sy.fsm.Repository.DefaultPropertiesRepository;
import com.sy.fsm.Repository.EstimationDetailsRepository;
import com.sy.fsm.Repository.EstimationProductDetailsRepository;
import com.sy.fsm.Repository.OrderDetailsRepository;
import com.sy.fsm.Repository.OrderProductDetailsRepository;
import com.sy.fsm.Repository.PaymentDetailsRepository;
import com.sy.fsm.Repository.ProductDetailsRepository;
import com.sy.fsm.Repository.UserDetailsRepository;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;


@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Value("${upload.dir}")
    private String UPLOAD_DIR;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	CategoryDetailsRepository categoryDetailsRepository;
	
	@Autowired
	ProductDetailsRepository productDetailsRepository;
	
	@Autowired
	DarDetailsRepository darDetailsRepository;
	
	@Autowired
	DarExpensesDetailsRepository darExpensesDetailsRepository;
	
	@Autowired
	EstimationDetailsRepository estimationDetailsRepository;
	
	@Autowired
	EstimationProductDetailsRepository estimationProductDetailsRepository;
	
	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	OrderProductDetailsRepository orderProductDetailsRepository;
	
	@Autowired
	DefaultPropertiesRepository defaultPropertiesRepository;
	
	@Autowired
	PaymentDetailsRepository paymentDetailsRepository ;
        
    
    @RequestMapping(value = "/fsm/getCategoryDetailsList", method = RequestMethod.POST, consumes="application/json")
	public String getCategoryDetailsList(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/getCategoryDetailsList:::::::");
			JSONObject jObj = new JSONObject(payload);
			String id = jObj.getString("ID");
			System.out.println(id);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			ObjectMapper mapper = new ObjectMapper();
			List<CategoryDetails> propertiesList = categoryDetailsRepository.findAll();
			mapper.setDateFormat(sdf);
			String sourceString = mapper.writeValueAsString(propertiesList);
			vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
			System.out.println(sourceString);
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
    
    /******************************************************update****************************************************/
	@RequestMapping(value = "/fsm/updateCategoryDetails", method = RequestMethod.POST, consumes = "application/json")
	public String updateCategoryDetails(@RequestBody String payload) {
	    String vResponse = "{\"status\":\"false\"}";
	    try {
	        System.out.println("/fsm/updateCategoryDetails:::::::" + payload);	        
	        ObjectMapper mapper = new ObjectMapper();
	        CategoryDetails newDetails = mapper.readValue(payload, CategoryDetails.class);	        
	        UUID id = newDetails.getId();
	        if (id != null) {	            
	            Optional<CategoryDetails> existingRecord = categoryDetailsRepository.findById(id);
	            if (existingRecord.isPresent()) {	                
	            	CategoryDetails categoryDetails = existingRecord.get();
	            	categoryDetails.setCategoryID(newDetails.getCategoryID());
	            	categoryDetails.setCategory(newDetails.getCategory());
	            	categoryDetails.setCreatedDate(newDetails.getCreatedDate());
	            	categoryDetails.setCreatedBy(newDetails.getCreatedBy());	            		               
	            	categoryDetailsRepository.save(categoryDetails);
	                vResponse = "{\"status\":\"true\"}";
	            }
	        } else {
	        	newDetails.setId(UUID.randomUUID());
	        	//newDetails.setCategoryID(categoryDetailsRepository.generateCategorySequence());
	        	categoryDetailsRepository.save(newDetails);
	            vResponse = "{\"status\":\"true\"}";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
	    }
	    System.out.println("VResponse :::" + vResponse);
	    return vResponse;
	}
	@RequestMapping(value = "/fsm/editCategoryDetails", method = RequestMethod.POST, consumes="application/json")
	public String editCategoryDetails(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/editCategoryDetails::::::::::::::::"+payload);			
			JSONObject jObj = new JSONObject(payload);
			String idString = jObj.getString("ID");	
			UUID id = UUID.fromString(idString);
			Optional<CategoryDetails> existingDetailsRecord = categoryDetailsRepository.findById(id);
			if(existingDetailsRecord.isPresent()) {
				CategoryDetails categoryDetails = existingDetailsRecord.get();
				ObjectMapper mapper = new ObjectMapper();
				String sourceString = mapper.writeValueAsString(categoryDetails);
				vResponse =  "{\"status\":\"true\",\"data\":"+sourceString+"}";	            
			}else {
				vResponse =  "{\"status\":\"false\"}";
			}										
			System.out.println(vResponse);
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
	@RequestMapping(value = "/fsm/deleteCategoryDetails", method = RequestMethod.POST, consumes="application/json")
	public String deleteCategoryDetails(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/deleteCategoryDetails::::::::::"+payload);
			JSONObject jObj = new JSONObject(payload);
			String idString = jObj.getString("ID");			
			UUID id = UUID.fromString(idString);			
			Optional<CategoryDetails> existingDetailsRecord = categoryDetailsRepository.findById(id);			
			if(existingDetailsRecord.isPresent()){					
				CategoryDetails categoryDetails = existingDetailsRecord.get();
				categoryDetailsRepository.delete(categoryDetails);									
				vResponse =  "{\"status\":\"true\"}";
			}
			System.out.println("vResponse::::"+vResponse);
		}catch(Exception e4) {
			e4.printStackTrace();			
		}
	    return vResponse;
	}
	
	@RequestMapping(value = "/fsm/getProductDetailsList", method = RequestMethod.POST, consumes="application/json")
	public String getProductDetailsList(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/getProductDetailsList:::::::");
			JSONObject jObj = new JSONObject(payload);
			String id = jObj.getString("ID");
			System.out.println(id);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			ObjectMapper mapper = new ObjectMapper();
			List<ProductDetails> productList = productDetailsRepository.findAll();
			mapper.setDateFormat(sdf);
			String sourceString = mapper.writeValueAsString(productList);
			vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
			System.out.println(sourceString);
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
	@RequestMapping(value = "/fsm/getProductDetailsBasedOnProductName", method = RequestMethod.POST, consumes="application/json")
	public String getProductDetailsBasedOnProductName(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/getProductDetailsBasedOnProductName:::::::");
			JSONObject jObj = new JSONObject(payload);
			String productName = jObj.getString("ProductName");
			System.out.println(productName);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			ObjectMapper mapper = new ObjectMapper();
			Optional<ProductDetails> productRecord= productDetailsRepository.findByProductName(productName);
			if(productRecord.isPresent()) {
				ProductDetails productDetails = productRecord.get();
				mapper.setDateFormat(sdf);
				String sourceString = mapper.writeValueAsString(productDetails);
				vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
				System.out.println(sourceString);
			}			
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
	@RequestMapping(value = "/fsm/getProductDetailsBasedOnProductID", method = RequestMethod.POST, consumes="application/json")
	public String getProductDetailsBasedOnProductID(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/getProductDetailsBasedOnProductID:::::::");
			JSONObject jObj = new JSONObject(payload);
			String productID = jObj.getString("ProductID");
			System.out.println(productID);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			ObjectMapper mapper = new ObjectMapper();
			Optional<ProductDetails> productRecord= productDetailsRepository.findByProductId(productID);
			if(productRecord.isPresent()) {
				ProductDetails productDetails = productRecord.get();
				mapper.setDateFormat(sdf);
				String sourceString = mapper.writeValueAsString(productDetails);
				vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
				System.out.println(sourceString);
			}			
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
	/******************************************************update****************************************************/
	@RequestMapping(value = "/fsm/updateProductDetails", method = RequestMethod.POST, consumes = "application/json")
	public String updateProductDetails(@RequestBody String payload) {
	    String vResponse = "{\"status\":\"false\"}";
	    try {
	        System.out.println("/fsm/updateProductDetails:::::::" + payload);	        
	        ObjectMapper mapper = new ObjectMapper();
	        ProductDetails newDetails = mapper.readValue(payload, ProductDetails.class);	        
	        UUID id = newDetails.getId();
	        if (id != null) {	            
	            Optional<ProductDetails> existingRecord = productDetailsRepository.findById(id);
	            if (existingRecord.isPresent()) {	                
	            	ProductDetails productDetails = existingRecord.get();
	            	productDetails.setProductID(newDetails.getProductID());
	            	productDetails.setProductName(newDetails.getProductName());
	            	productDetails.setProductCategory(newDetails.getProductCategory());
	            	productDetails.setUnitPrice(newDetails.getUnitPrice());
	            	productDetails.setTax(newDetails.getTax());
	            	productDetails.setCreatedDate(newDetails.getCreatedDate());
	            	productDetails.setCreatedBy(newDetails.getCreatedBy());	            		               
	            	productDetailsRepository.save(productDetails);
	                vResponse = "{\"status\":\"true\"}";
	            }
	        } else {
	        	newDetails.setId(UUID.randomUUID());
	        	//newDetails.setProductID(productDetailsRepository.generateProductSequence());
	        	productDetailsRepository.save(newDetails);
	            vResponse = "{\"status\":\"true\"}";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
	    }
	    System.out.println("VResponse :::" + vResponse);
	    return vResponse;
	}
	
	@RequestMapping(value = "/fsm/editProductDetails", method = RequestMethod.POST, consumes="application/json")
	public String editProductDetails(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/editProductDetails::::::::::::::::"+payload);			
			JSONObject jObj = new JSONObject(payload);
			String idString = jObj.getString("ID");	
			UUID id = UUID.fromString(idString);
			Optional<ProductDetails> existingDetailsRecord = productDetailsRepository.findById(id);
			if(existingDetailsRecord.isPresent()) {
				ProductDetails productDetails = existingDetailsRecord.get();
				ObjectMapper mapper = new ObjectMapper();
				String sourceString = mapper.writeValueAsString(productDetails);
				vResponse =  "{\"status\":\"true\",\"data\":"+sourceString+"}";	            
			}else {
				vResponse =  "{\"status\":\"false\"}";
			}										
			System.out.println(vResponse);
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
	@RequestMapping(value = "/fsm/deleteProductDetails", method = RequestMethod.POST, consumes="application/json")
	public String deleteProductDetails(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/deleteProductDetails::::::::::"+payload);
			JSONObject jObj = new JSONObject(payload);
			String idString = jObj.getString("ID");			
			UUID id = UUID.fromString(idString);			
			Optional<ProductDetails> existingDetailsRecord = productDetailsRepository.findById(id);			
			if(existingDetailsRecord.isPresent()){					
				ProductDetails productDetails = existingDetailsRecord.get();
				productDetailsRepository.delete(productDetails);									
				vResponse =  "{\"status\":\"true\"}";
			}
			System.out.println("vResponse::::"+vResponse);
		}catch(Exception e4) {
			e4.printStackTrace();			
		}
	    return vResponse;
	}
	
	@RequestMapping(value = "/fsm/getDarDetailsList", method = RequestMethod.POST, consumes="application/json")
	public String getDarDetailsList(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/getDarDetailsList:::::::");
			JSONObject jObj = new JSONObject(payload);
			String userId = jObj.getString("User ID");
			System.out.println(userId);
			String teamMemberIds = userDetailsRepository.getUserBasedTeamMemberUserIds(userId);
			System.out.println(teamMemberIds);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			ObjectMapper mapper = new ObjectMapper();
			List<DarDetails> detailsList = darDetailsRepository.getFSMUserIdsBasedDarDetailsList(teamMemberIds);
			mapper.setDateFormat(sdf);
			String sourceString = mapper.writeValueAsString(detailsList);
			vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
			System.out.println(sourceString);
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
	
	

	@RequestMapping(value = "/fsm/generateUUID", method = RequestMethod.POST, consumes = "application/json")
	public String generateUUID(@RequestBody String payload) {
    String vResponse = "{\"status\":\"false\"}";
    try {
    	System.out.println("/fsm/generateUUID:::::::");	        	        
    	vResponse =  "{\"status\":\"true\",\"id\":\""+UUID.randomUUID().toString()+"\"}";
		}catch(Exception e) {
			e.printStackTrace();
			vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
		}
		System.out.println("VResponse :::" + vResponse);
		return vResponse;
	}
	/******************************************************update****************************************************/
	@RequestMapping(value = "/fsm/updateDarDetails", method = RequestMethod.POST, consumes = "application/json")
	public String updateDarDetails(@RequestBody String payload) {
	    String vResponse = "{\"status\":\"false\"}";
	    try {
	        System.out.println("/fsm/updateDarDetails:::::::" + payload);	        
	        ObjectMapper mapper = new ObjectMapper();
	        DarDetails newDetails = mapper.readValue(payload, DarDetails.class);	        
	        UUID id = newDetails.getId();	        
	        if (!newDetails.getDarNO().equalsIgnoreCase("")) {	            
	            Optional<DarDetails> existingRecord = darDetailsRepository.findById(id);
	            if (existingRecord.isPresent()) {	  
	            	System.out.println("existingRecord");
	            	DarDetails darDetails = existingRecord.get();
	            	darDetails.setDarNO(newDetails.getDarNO());
	            	darDetails.setDarProcessDate(newDetails.getDarProcessDate());
	            	darDetails.setPlannedActivity(newDetails.getPlannedActivity());
	            	darDetails.setDeliveryPlaceNameAndAddress(newDetails.getDeliveryPlaceNameAndAddress());
	            	darDetails.setStateCumArea(newDetails.getStateCumArea());
	            	darDetails.setClientName(newDetails.getClientName());
	            	darDetails.setClientMobileNO(newDetails.getClientMobileNO());
	            	darDetails.setAboutTheClient(newDetails.getAboutTheClient());
	            	darDetails.setProductDetails(newDetails.getProductDetails());
	            	darDetails.setFromLocation(newDetails.getFromLocation());
	            	darDetails.setToLocation(newDetails.getToLocation());
	            	darDetails.setTotalExpenses(newDetails.getTotalExpenses());
	            	darDetails.setRemarks(newDetails.getRemarks());
	            	darDetails.setStatusToVisit(newDetails.getStatusToVisit());
	            	darDetails.setCreatedDate(newDetails.getCreatedDate());
	            	darDetails.setCreatedBy(newDetails.getCreatedBy());	            	
	            	darDetailsRepository.save(darDetails);
	                vResponse = "{\"status\":\"true\"}";
	            }
	        } else {
	        	System.out.println("newRecord");
	        	newDetails.setId(id);
	        	newDetails.setDarNO(darDetailsRepository.generateDarSequence());
	        	darDetailsRepository.save(newDetails);
	            vResponse = "{\"status\":\"true\"}";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
	    }
	    System.out.println("VResponse :::" + vResponse);
	    return vResponse;
	}
	
	@RequestMapping(value = "/fsm/editDarDetails", method = RequestMethod.POST, consumes="application/json")
	public String editDarDetails(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/editDarDetails::::::::::::::::"+payload);			
			JSONObject jObj = new JSONObject(payload);
			String idString = jObj.getString("ID");	
			UUID id = UUID.fromString(idString);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Optional<DarDetails> existingDetailsRecord = darDetailsRepository.findById(id);
			if(existingDetailsRecord.isPresent()) {
				DarDetails darDetails = existingDetailsRecord.get();
				ObjectMapper mapper = new ObjectMapper();
				mapper.setDateFormat(sdf);
				String sourceString = mapper.writeValueAsString(darDetails);
				List<DarExpensesDetails> expenseDetailsList = darExpensesDetailsRepository.findByReferenceId(id.toString());
				String expenseDetailsSourceString = mapper.writeValueAsString(expenseDetailsList);				
				vResponse =  "{\"status\":\"true\",\"data\":"+sourceString+",\"expensesData\":"+expenseDetailsSourceString+"}";	            
			}else {
				vResponse =  "{\"status\":\"false\"}";
			}										
			System.out.println(vResponse);
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
	
	@RequestMapping(value = "/fsm/deleteDarDetails", method = RequestMethod.POST, consumes="application/json")
	public String deleteDarDetails(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/deleteProductDetails::::::::::"+payload);
			JSONObject jObj = new JSONObject(payload);
			String idString = jObj.getString("ID");			
			UUID id = UUID.fromString(idString);			
			Optional<DarDetails> existingDetailsRecord = darDetailsRepository.findById(id);			
			if(existingDetailsRecord.isPresent()){					
				DarDetails darDetails = existingDetailsRecord.get();
				darDetailsRepository.delete(darDetails);	
				
				List<DarExpensesDetails> expenseDetailsList = darExpensesDetailsRepository.findByReferenceId(id.toString());
				if(expenseDetailsList.size() > 0) {
					darExpensesDetailsRepository.deleteAll(expenseDetailsList);
				}
				vResponse =  "{\"status\":\"true\"}";
			}
			System.out.println("vResponse::::"+vResponse);
		}catch(Exception e4) {
			e4.printStackTrace();			
		}
	    return vResponse;
	}
	
	
	 @PostMapping("/fsm/updateDarExpensesDetails")
	    public ResponseEntity<Map<String, String>> uploadExpenses(
	            @RequestParam("expenses") String expensesJson,
	            @RequestParam("imageFiles") List<MultipartFile> imageFiles) { 
		 String vResponse =  "{\"status\":\"false\"}";
		 Map<String, String> response = new HashMap<>();
	        try {
	        	System.out.println("/fsm/updateDarExpensesDetails>>>>::::"+expensesJson);
	            ObjectMapper objectMapper = new ObjectMapper();
	            List<DarExpensesDetails> expensesList = objectMapper.readValue(expensesJson, 
	                new TypeReference<List<DarExpensesDetails>>() {});
	            System.out.println("expensesList>>>>::::"+expensesList.size());
	            for (int i = 0; i < expensesList.size(); i++) {
	                DarExpensesDetails expense = expensesList.get(i);
	                MultipartFile imageFile = imageFiles.get(i);
	                System.out.println("/file>>>>::::::::::"+imageFiles.get(i));
	                if (imageFile != null && !imageFile.isEmpty()) {	                   
	                    String fileName = imageFile.getOriginalFilename();	                   
	                    File file = new File(UPLOAD_DIR, fileName);
	                    imageFile.transferTo(file);
	
	                    expense.setImageFilePath(fileName);
	                    
	                }
	                if(expense.getId() != null) {
	                	Optional<DarExpensesDetails> expenseDetailsRecord = darExpensesDetailsRepository.findById(expense.getId());
		                if(expenseDetailsRecord.isPresent()) {
		                	DarExpensesDetails expenseDetails = expenseDetailsRecord.get();
		                	expenseDetails.setExpensesAmount(expense.getExpensesAmount());
			            	expenseDetails.setExpensesDescription(expense.getExpensesDescription());	            
			            	expenseDetails.setReferenceId(expense.getReferenceId());
			            	darExpensesDetailsRepository.save(expense);
			            }
	                }else {
		            	expense.setId(UUID.randomUUID());		            
			            darExpensesDetailsRepository.save(expense);
		            }	            
	            }
	            vResponse =  "{\"status\":\"true\"}";
	            System.out.println("vResponse::::"+vResponse);
	            
	            response.put("status", "true");
	            response.put("message", "User details updated successfully.");
	            return ResponseEntity.ok(response);

	        } catch (Exception e) {
	            e.printStackTrace();
	            response.put("status", "false");
	            response.put("error", "File upload failed: " + e.getMessage());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	    }
	 
	 
	 	@GetMapping("/fsm/RetrieveFile/{fileName}")
	    public ResponseEntity<byte[]> getFile(@PathVariable String fileName) throws IOException {
	        Path path = Paths.get(UPLOAD_DIR + fileName);
	        byte[] fileBytes = Files.readAllBytes(path);

	        return ResponseEntity.ok()
	                .header("Content-Type", Files.probeContentType(path))
	                .body(fileBytes);
	    }
	    
	    @RequestMapping(value = "/fsm/deleteDarExpensesDetails", method = RequestMethod.POST, consumes="application/json")
		public String deleteDarExpensesDetails(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/deleteDarExpensesDetails::::::::::"+payload);
				JSONObject jObj = new JSONObject(payload);
				String idString = jObj.getString("ID");			
				UUID id = UUID.fromString(idString);			
				Optional<DarExpensesDetails> expenseDetailsRecord = darExpensesDetailsRepository.findById(id);			
				if(expenseDetailsRecord.isPresent()){					
					DarExpensesDetails expenseDetails = expenseDetailsRecord.get();
					darExpensesDetailsRepository.delete(expenseDetails);	
					
					List<DarExpensesDetails> expenseDetailsList = darExpensesDetailsRepository.findByReferenceId(id.toString());
					if(expenseDetailsList.size() > 0) {
						darExpensesDetailsRepository.deleteAll(expenseDetailsList);
					}
					vResponse =  "{\"status\":\"true\"}";
				}
				System.out.println("vResponse::::"+vResponse);
			}catch(Exception e4) {
				e4.printStackTrace();			
			}
		    return vResponse;
		}
	    
	    @RequestMapping(value = "/fsm/getEstimationDetailsList", method = RequestMethod.POST, consumes="application/json")
		public String getEstimationDetailsList(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/getEstimationDetailsList:::::::");
				JSONObject jObj = new JSONObject(payload);
				String userId = jObj.getString("User ID");
				System.out.println(userId);
				String teamMemberIds = userDetailsRepository.getUserBasedTeamMemberUserIds(userId);
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				ObjectMapper mapper = new ObjectMapper();
				List<EstimationDetails> detailsList = estimationDetailsRepository.getFSMUserIdsBasedEstimationDetailsList(teamMemberIds);
				mapper.setDateFormat(sdf);
				String sourceString = mapper.writeValueAsString(detailsList);
				vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
				System.out.println(sourceString);
			}catch(Exception e4) {
				e4.printStackTrace();
			}
		    return vResponse;
		}
	    
	    /******************************************************update****************************************************/
	    @RequestMapping(value = "/fsm/updateEstimationDetails", method = RequestMethod.POST, consumes = "application/json")
	    public String updateEstimationDetails(@RequestBody String payload) {
	        String vResponse = "{\"status\":\"false\"}";
	        try {
	            System.out.println("/fsm/updateEstimationDetails:::::::" + payload);	        
	            ObjectMapper mapper = new ObjectMapper();
	            EstimationDetails newDetails = mapper.readValue(payload, EstimationDetails.class);
	            
	            UUID id = newDetails.getId(); 
	            
	            if (newDetails.getEstNO() != null) { 
	                Optional<EstimationDetails> existingRecord = estimationDetailsRepository.findById(id);
	                
	                if (existingRecord.isPresent()) {
	                    // Update existing record
	                    System.out.println("existingRecord found");
	                    EstimationDetails estDetails = existingRecord.get();

	                    // Update fields with the new data from `newDetails`
	                    estDetails.setEstNO(newDetails.getEstNO());
	                    estDetails.setCustomerName(newDetails.getCustomerName());
	                    estDetails.setEstimationProcessDate(newDetails.getEstimationProcessDate());
	                    estDetails.setRepAttd(newDetails.getRepAttd());
	                    estDetails.setRepAccount(newDetails.getRepAccount());
	                    estDetails.setBillingAddress(newDetails.getBillingAddress());
	                    estDetails.setDeliveryAddress(newDetails.getDeliveryAddress());
	                    estDetails.setCustomerCity(newDetails.getCustomerCity());
	                    estDetails.setCustomerPinCode(newDetails.getCustomerPinCode());
	                    estDetails.setCustomerPhone(newDetails.getCustomerPhone());
	                    estDetails.setCustomerEmail(newDetails.getCustomerEmail());
	                    estDetails.setDeliveryCity(newDetails.getDeliveryCity());
	                    estDetails.setDeliveryPinCode(newDetails.getDeliveryPinCode());
	                    estDetails.setWarranty(newDetails.getWarranty());
	                    estDetails.setPanAndGst(newDetails.getPanAndGst());
	                    estDetails.setTotalProduct(newDetails.getTotalProduct());
	                    estDetails.setRef(newDetails.getRef());
	                    estDetails.setRemarks(newDetails.getRemarks());
	                    estDetails.setItsHaveDiscount(newDetails.getItsHaveDiscount());
	                    estDetails.setDiscountEstimate(newDetails.getDiscountEstimate());
	                    estDetails.setDemoPieceEstimate(newDetails.getDemoPieceEstimate());
	                    estDetails.setStockClearanceEstimate(newDetails.getStockClearanceEstimate());
	                    estDetails.setDiscountAmount(newDetails.getDiscountAmount());
	                    estDetails.setGst(newDetails.getGst());
	                    estDetails.setDeliveryAddress(newDetails.getDeliveryAddress());
	                    estDetails.setTotalAmount(newDetails.getTotalAmount());
	                    estDetails.setRegisterStatus(newDetails.getRegisterStatus());
	                    estDetails.setCreatedDate(newDetails.getCreatedDate());
	                    estDetails.setCreatedBy(newDetails.getCreatedBy());

	                    
	                    estimationDetailsRepository.save(estDetails);
	                    
	                    System.out.println("current status +++++++++++++++++"+newDetails.getRegisterStatus());
	                    if (newDetails.getRegisterStatus().equalsIgnoreCase("Convert To Order")) {
	                        try {
	                        	
	                        	Optional<OrderDetails> existingOrderRecord = orderDetailsRepository.findByEstNo(newDetails.getEstNO());
	                        	if(existingOrderRecord.isPresent()) {
	                        		System.out.println("Convert To Order Process existing");
	                        		OrderDetails orderDetails = existingOrderRecord.get();
	                        		orderDetails.seteNo(""); 
		                            orderDetails.setEstNo(newDetails.getEstNO());
		                            orderDetails.setOrderNo(orderDetailsRepository.generateOrderSequence());
		                            orderDetails.setSoNo("");
		                            orderDetails.setDdNo("");
		                            orderDetails.setCustomerName(newDetails.getCustomerName());
		                            orderDetails.setOrderProcessDate(new Timestamp(System.currentTimeMillis())); // Proper timestamp
		                            orderDetails.setRepCode(newDetails.getRepAttd());
		                            orderDetails.setBillingName("");  
		                            orderDetails.setBillingAddress(newDetails.getBillingAddress());
		                            orderDetails.setCustomerCity(newDetails.getCustomerCity());
		                            orderDetails.setCustomerPinCode(newDetails.getCustomerPinCode());
		                            orderDetails.setCustomerPhone(newDetails.getCustomerPhone());
		                            orderDetails.setCustomerEmail(newDetails.getCustomerEmail());
		                            orderDetails.setDeliveryCity(newDetails.getDeliveryCity());
		                            orderDetails.setDemoPlan("");
		                            orderDetails.setPaymentCharges("");
		                            orderDetails.setPaymentTermDate(null);  // Assuming no payment date
		                            orderDetails.setWarranty(newDetails.getWarranty());
		                            orderDetails.setPanAndGst(newDetails.getPanAndGst());
		                            orderDetails.setDemoDate(null);
		                            orderDetails.setDeliveryAddress(newDetails.getDeliveryAddress());
		                            orderDetails.setDeliveryPinCode(newDetails.getDeliveryPinCode());
		                            orderDetails.setExpectedDate(null);
		                            orderDetails.setShipModeName("");
		                            orderDetails.setRemarks(newDetails.getRemarks());
		                            orderDetails.setItsHaveDiscount(newDetails.getItsHaveDiscount());
		                            orderDetails.setDiscountEstimate(newDetails.getDiscountEstimate());
		                            orderDetails.setDemoPieceEstimate(newDetails.getDemoPieceEstimate());
		                            orderDetails.setStockClearanceEstimate(newDetails.getStockClearanceEstimate());
		                            orderDetails.setDiscountAmount(newDetails.getDiscountAmount());
		                            orderDetails.setTotalProductAmount(newDetails.getTotalAmount());
		                            orderDetails.setGst(newDetails.getGst());
		                            orderDetails.setDeliveryCharges(newDetails.getDeliveryCharges());
		                            orderDetails.setTotalAmount(newDetails.getTotalAmount());
		                            orderDetails.setLessAdvance("");
		                            orderDetails.setBalance("");
		                            orderDetails.setRegisterStatus("New Order");
		                            orderDetails.setCreatedDate(newDetails.getCreatedDate());
		                            orderDetails.setCreatedBy(newDetails.getCreatedBy());
		                            orderDetails.setPaymentMode("");
		                            orderDetailsRepository.save(orderDetails);
		                            vResponse =  "{\"status\":\"true\",\"id\":\""+newDetails.getId().toString()+"\"}";
		                            System.out.println("Existing Created Order");
	                        	}else {
	                        		System.out.println("Convert To Order Process New");
		                            OrderDetails orderDetails = new OrderDetails();
		                            UUID orderId = UUID.randomUUID();
		                            orderDetails.setId(orderId);
		                            orderDetails.seteNo("");  // Set fields according to your logic
		                            orderDetails.setEstNo(newDetails.getEstNO());
		                            orderDetails.setOrderNo(orderDetailsRepository.generateOrderSequence());
		                            orderDetails.setSoNo("");
		                            orderDetails.setDdNo("");
		                            orderDetails.setCustomerName(newDetails.getCustomerName());
		                            orderDetails.setOrderProcessDate(new Timestamp(System.currentTimeMillis())); // Proper timestamp
		                            orderDetails.setRepCode(newDetails.getRepAttd());
		                            orderDetails.setBillingName("");  // Set appropriate values or keep blank
		                            orderDetails.setBillingAddress(newDetails.getBillingAddress());
		                            orderDetails.setCustomerCity(newDetails.getCustomerCity());
		                            orderDetails.setCustomerPinCode(newDetails.getCustomerPinCode());
		                            orderDetails.setCustomerPhone(newDetails.getCustomerPhone());
		                            orderDetails.setCustomerEmail(newDetails.getCustomerEmail());
		                            orderDetails.setDeliveryCity(newDetails.getDeliveryCity());
		                            orderDetails.setDemoPlan("");
		                            orderDetails.setPaymentCharges("");
		                            orderDetails.setPaymentTermDate(null);  // Assuming no payment date
		                            orderDetails.setWarranty(newDetails.getWarranty());
		                            orderDetails.setPanAndGst(newDetails.getPanAndGst());
		                            orderDetails.setDemoDate(null);
		                            orderDetails.setDeliveryAddress(newDetails.getDeliveryAddress());
		                            orderDetails.setDeliveryPinCode(newDetails.getDeliveryPinCode());
		                            orderDetails.setExpectedDate(null);
		                            orderDetails.setShipModeName("");
		                            orderDetails.setRemarks(newDetails.getRemarks());
		                            orderDetails.setItsHaveDiscount(newDetails.getItsHaveDiscount());
		                            orderDetails.setDiscountEstimate(newDetails.getDiscountEstimate());
		                            orderDetails.setDemoPieceEstimate(newDetails.getDemoPieceEstimate());
		                            orderDetails.setStockClearanceEstimate(newDetails.getStockClearanceEstimate());
		                            orderDetails.setDiscountAmount(newDetails.getDiscountAmount());
		                            orderDetails.setTotalProductAmount(newDetails.getTotalAmount());
		                            orderDetails.setGst(newDetails.getGst());
		                            orderDetails.setDeliveryCharges(newDetails.getDeliveryCharges());
		                            orderDetails.setTotalAmount(newDetails.getTotalAmount());
		                            orderDetails.setLessAdvance("");
		                            orderDetails.setBalance("");
		                            orderDetails.setRegisterStatus("New Order");
		                            orderDetails.setCreatedDate(newDetails.getCreatedDate());
		                            orderDetails.setCreatedBy(newDetails.getCreatedBy());
		                            orderDetails.setPaymentMode("");
		                            orderDetailsRepository.save(orderDetails);
		                            
		                            vResponse =  "{\"status\":\"true\",\"id\":\""+orderId.toString()+"\"}";
		                            System.out.println("Created Order");
	                        	}     	                            
	                        	
	                        } catch (Exception e4) {
	                            e4.printStackTrace();
	                        }
	                    }else {
	                    	System.out.println("Status not a order converting ");
	                    	vResponse = "{\"status\":\"true\"}";
	                    }

	                } else {
	                    System.out.println("New record - Creating a new estimation");
	                    newDetails.setId(id);  // Ensure ID is set
	                    newDetails.setEstNO(estimationDetailsRepository.generateEstimationSequence());
	                    estimationDetailsRepository.save(newDetails);
	                    vResponse = "{\"status\":\"true\"}";
	                }
	            } else {
	                System.out.println("EstNO is empty or null");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
	        }
	        System.out.println("VResponse :::" + vResponse);
	        return vResponse;
	    }

		
		@RequestMapping(value = "/fsm/editEstimationDetails", method = RequestMethod.POST, consumes="application/json")
		public String editEstimationDetails(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/editEstimationDetails::::::::::::::::"+payload);			
				JSONObject jObj = new JSONObject(payload);
				String idString = jObj.getString("ID");	
				UUID id = UUID.fromString(idString);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Optional<EstimationDetails> existingDetailsRecord = estimationDetailsRepository.findById(id);
				if(existingDetailsRecord.isPresent()) {
					EstimationDetails estDetails = existingDetailsRecord.get();
					ObjectMapper mapper = new ObjectMapper();
					mapper.setDateFormat(sdf);
					String sourceString = mapper.writeValueAsString(estDetails);
					
					List<EstimationProductDetails> estProductDetailsList = estimationProductDetailsRepository.findByReferenceId(id.toString());
					String estProductSourceString = mapper.writeValueAsString(estProductDetailsList);				
					vResponse =  "{\"status\":\"true\",\"data\":"+sourceString+",\"estProductData\":"+estProductSourceString+"}";					
				}else {
					vResponse =  "{\"status\":\"false\"}";
				}										
				System.out.println(vResponse);
			}catch(Exception e4) {
				e4.printStackTrace();
			}
		    return vResponse;
		}
		
		@RequestMapping(value = "/fsm/deleteEstimationDetails", method = RequestMethod.POST, consumes="application/json")
		public String deleteEstimationDetails(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/deleteEstimationDetails::::::::::"+payload);
				JSONObject jObj = new JSONObject(payload);
				String idString = jObj.getString("ID");			
				UUID id = UUID.fromString(idString);			
				Optional<EstimationDetails> existingDetailsRecord = estimationDetailsRepository.findById(id);			
				if(existingDetailsRecord.isPresent()){					
					EstimationDetails estDetails = existingDetailsRecord.get();
					estimationDetailsRepository.delete(estDetails);	
					
					List<EstimationProductDetails> estProductDetailsList = estimationProductDetailsRepository.findByReferenceId(id.toString());
					if(estProductDetailsList.size() > 0) {
						estimationProductDetailsRepository.deleteAll(estProductDetailsList);
					}
					vResponse =  "{\"status\":\"true\"}";
				}
				System.out.println("vResponse::::"+vResponse);
			}catch(Exception e4) {
				e4.printStackTrace();			
			}
		    return vResponse;
		}
		
		/******************************************************update****************************************************/
		@RequestMapping(value = "/fsm/updateEstimationProductsDetails", method = RequestMethod.POST, consumes = "application/json")
		public String updateEstimationProductsDetails(@RequestBody String payload) {
		    String vResponse = "{\"status\":\"false\"}";
		    try {
		        System.out.println("/fsm/updateEstimationProductsDetails:::::::" + payload);		        
		        ObjectMapper mapper = new ObjectMapper();
		        List<EstimationProductDetails> newDetailsList = mapper.readValue(payload, 
		                new TypeReference<List<EstimationProductDetails>>() {});
		        for(EstimationProductDetails newDetails: newDetailsList) {
		        	UUID id = newDetails.getId();	        
			        if (id != null) {	            
			            Optional<EstimationProductDetails> existingRecord = estimationProductDetailsRepository.findById(id);
			            if (existingRecord.isPresent()) {	  
			            	System.out.println("existingRecord");
			            	EstimationProductDetails estProductDetails = existingRecord.get();
			            	estProductDetails.setProductCode(newDetails.getProductCode());
			            	estProductDetails.setProductDetails(newDetails.getProductDetails());
			            	estProductDetails.setQty(newDetails.getQty());
			            	estProductDetails.setReferenceId(newDetails.getReferenceId());
			            	estProductDetails.setTax(newDetails.getTax());
			            	estProductDetails.setUnitPrice(newDetails.getUnitPrice());
			            	estProductDetails.setTotal(newDetails.getTotal());
			            	estimationProductDetailsRepository.save(estProductDetails);			            	
			            }
			        } else {
			        	System.out.println("newRecord");
			        	newDetails.setId(UUID.randomUUID());		        	
			        	estimationProductDetailsRepository.save(newDetails);
			            
			        }
		        }
		        vResponse = "{\"status\":\"true\"}";
		    } catch (Exception e) {
		        e.printStackTrace();
		        vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
		    }
		    System.out.println("VResponse :::" + vResponse);
		    return vResponse;
		}
		
		@RequestMapping(value = "/fsm/deleteEstimationProductsDetails", method = RequestMethod.POST, consumes="application/json")
		public String deleteEstimationProductsDetails(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/deleteEstimationProductsDetails::::::::::"+payload);
				JSONObject jObj = new JSONObject(payload);
				String idString = jObj.getString("ID");			
				UUID id = UUID.fromString(idString);			
				Optional<EstimationProductDetails> estProductDetailsRecord = estimationProductDetailsRepository.findById(id);			
				if(estProductDetailsRecord.isPresent()){					
					EstimationProductDetails estProductDetails = estProductDetailsRecord.get();
					estimationProductDetailsRepository.delete(estProductDetails);						
					vResponse =  "{\"status\":\"true\"}";
				}
				System.out.println("vResponse::::"+vResponse);
			}catch(Exception e4) {
				e4.printStackTrace();			
			}
		    return vResponse;
		}
	    
		@RequestMapping(value = "/fsm/getOrderDetailsList", method = RequestMethod.POST, consumes="application/json")
		public String getOrderDetailsList(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/getOrderDetailsList:::::::");
				JSONObject jObj = new JSONObject(payload);
				String userId = jObj.getString("User ID");				
				String teamMemberIds = userDetailsRepository.getUserBasedTeamMemberUserIds(userId);
				System.out.println(teamMemberIds);
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				ObjectMapper mapper = new ObjectMapper();
				List<OrderDetails> detailsList = orderDetailsRepository.getFSMUserIdsBasedOrderDetailsList(teamMemberIds);
				if(detailsList.size() > 0) {
					mapper.setDateFormat(sdf);
					String sourceString = mapper.writeValueAsString(detailsList);
					vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
					System.out.println(sourceString);
				}else {
					vResponse =  "{\"status\":\"true\"}";
				}
				
			}catch(Exception e4) {
				e4.printStackTrace();
			}
		    return vResponse;
		}
		
		/******************************************************update****************************************************/
        @RequestMapping(value = "/fsm/updateOrderDetails", method = RequestMethod.POST, consumes = "application/json")
        public String updateOrderDetails(@RequestBody String payload) {
            String vResponse = "{\"status\":\"false\"}";
            try {
                System.out.println("/fsm/updateOrderDetails:::::::" + payload);         
                ObjectMapper mapper = new ObjectMapper();
                OrderDetails newDetails = mapper.readValue(payload, OrderDetails.class);            
                UUID id = newDetails.getId();           
                if (!newDetails.getOrderNo().equalsIgnoreCase("")) {                
                    Optional<OrderDetails> existingRecord = orderDetailsRepository.findById(newDetails.getId());
                    if (existingRecord.isPresent()) {                      
                        OrderDetails orderDetails = existingRecord.get();
                        orderDetails.seteNo(newDetails.geteNo());
                        orderDetails.setEstNo(newDetails.getEstNo());
                        orderDetails.setOrderNo(newDetails.getOrderNo());
                        orderDetails.setSoNo(newDetails.getSoNo());
                        orderDetails.setDdNo(newDetails.getDdNo());
                        orderDetails.setCustomerName(newDetails.getCustomerName());
                        orderDetails.setOrderProcessDate(newDetails.getOrderProcessDate());
                        orderDetails.setRepCode(newDetails.getRepCode());
                        orderDetails.setBillingName(newDetails.getBillingName());
                        orderDetails.setBillingAddress(newDetails.getBillingAddress());
                        orderDetails.setCustomerCity(newDetails.getCustomerCity());
                        orderDetails.setCustomerPinCode(newDetails.getCustomerPinCode());
                        orderDetails.setCustomerPhone(newDetails.getCustomerPhone());
                        orderDetails.setCustomerEmail(newDetails.getCustomerEmail());
                        orderDetails.setDeliveryCity(newDetails.getDeliveryCity());
                        orderDetails.setDemoPlan(newDetails.getDemoPlan());
                        orderDetails.setPaymentCharges(newDetails.getPaymentCharges());
                        orderDetails.setPaymentTermDate(newDetails.getPaymentTermDate());
                        orderDetails.setWarranty(newDetails.getWarranty());
                        orderDetails.setPanAndGst(newDetails.getPanAndGst());
                        orderDetails.setDemoDate(newDetails.getDemoDate());
                        orderDetails.setDeliveryAddress(newDetails.getDeliveryAddress());
                        orderDetails.setDeliveryPinCode(newDetails.getDeliveryPinCode());
                        orderDetails.setExpectedDate(newDetails.getExpectedDate());
                        orderDetails.setShipModeName(newDetails.getShipModeName());
                        orderDetails.setRemarks(newDetails.getRemarks());
                        orderDetails.setItsHaveDiscount(newDetails.getItsHaveDiscount());
                        orderDetails.setDiscountEstimate(newDetails.getDiscountEstimate());
                        orderDetails.setDemoPieceEstimate(newDetails.getDemoPieceEstimate());
                        orderDetails.setStockClearanceEstimate(newDetails.getStockClearanceEstimate());
                        orderDetails.setDiscountAmount(newDetails.getDiscountAmount());
                        orderDetails.setTotalProductAmount(newDetails.getTotalProductAmount());
                        orderDetails.setGst(newDetails.getGst());
                        orderDetails.setDeliveryCharges(newDetails.getDeliveryCharges());
                        orderDetails.setTotalAmount(newDetails.getTotalAmount());
                        orderDetails.setLessAdvance(newDetails.getLessAdvance());
                        orderDetails.setBalance(newDetails.getBalance());
                        orderDetails.setRegisterStatus(newDetails.getRegisterStatus());
                        orderDetails.setCreatedDate(newDetails.getCreatedDate());
                        orderDetails.setCreatedBy(newDetails.getCreatedBy());
                        orderDetails.setPaymentMode(newDetails.getPaymentMode());
                        orderDetailsRepository.save(orderDetails);
                        vResponse = "{\"status\":\"true\"}";
                        if (newDetails.getRegisterStatus().equalsIgnoreCase("Payment Confirmed")) {
                        	System.out.println("Old Order to new Payment");
	                        Optional<PaymentDetails> existingPaymentRecord = paymentDetailsRepository.findByEstNo(newDetails.getEstNo());
	                        if (existingPaymentRecord.isPresent()) {                            
	                            System.out.println("existing Payment Record found");
	                            updatePaymentDetailsInTableRow(existingPaymentRecord.get(), newDetails, newDetails.getCreatedDate(), newDetails.getCreatedBy());                            
	                            vResponse = "{\"status\":\"true\"}";    
	                            
	                        } else {
	                            System.out.println("new Payment Record Created");
	                            PaymentDetails paymentDetails = new PaymentDetails();                           
	                            paymentDetails.setId(newDetails.getId());
	                            updatePaymentDetailsInTableRow(paymentDetails, newDetails, newDetails.getCreatedDate(), newDetails.getCreatedBy());
	                            paymentDetailsRepository.save(paymentDetails);                                                                                 
	                            vResponse = "{\"status\":\"true\"}";                               
	                        }
	                        
	                    }
                    }else {
                        System.out.println("newRecord");
                        newDetails.setId(UUID.randomUUID());    
                        newDetails.setOrderNo(orderDetailsRepository.generateOrderSequence());
                        orderDetailsRepository.save(newDetails);
                        vResponse = "{\"status\":\"true\"}";
                        if (newDetails.getRegisterStatus().equalsIgnoreCase("Payment Confirmed")) {  
                        	System.out.println("New Order to new Payment");
	                        Optional<PaymentDetails> existingPaymentRecord = paymentDetailsRepository.findByEstNo(newDetails.getEstNo());
	                        if (existingPaymentRecord.isPresent()) {                            
	                            System.out.println("existing Payment Record found");
	                            updatePaymentDetailsInTableRow(existingPaymentRecord.get(), newDetails, newDetails.getCreatedDate(), newDetails.getCreatedBy());                            
	                            vResponse = "{\"status\":\"true\"}";    
	                            
	                        } else {
	                            System.out.println("new Payment Record Created");
	                            PaymentDetails paymentDetails = new PaymentDetails();                           
	                            paymentDetails.setId(newDetails.getId());
	                            updatePaymentDetailsInTableRow(paymentDetails, newDetails, newDetails.getCreatedDate(), newDetails.getCreatedBy());
	                            paymentDetailsRepository.save(paymentDetails);                                                                                 
	                            vResponse = "{\"status\":\"true\"}";                               
	                        }
	                        
	                    }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
            }
            System.out.println("VResponse :::" + vResponse);
            return vResponse;
        }



        
        private PaymentDetails updatePaymentDetailsInTableRow(PaymentDetails paymentDetails, OrderDetails newDetails, Timestamp createdDate, String createdBy) {
            paymentDetails.seteNo("");
            paymentDetails.setEstNo(newDetails.getEstNo());
            paymentDetails.setOrderNo(newDetails.getOrderNo());
            paymentDetails.setSoNo("");
            paymentDetails.setDdNo("");
            paymentDetails.setCustomerName(newDetails.getCustomerName());
            paymentDetails.setOrderProcessDate(new Timestamp(System.currentTimeMillis()));
            paymentDetails.setRepCode(newDetails.getRepCode());
            paymentDetails.setBillingName("");
            paymentDetails.setBillingAddress(newDetails.getBillingAddress());
            paymentDetails.setCustomerCity(newDetails.getCustomerCity());
            paymentDetails.setCustomerPinCode(newDetails.getCustomerPinCode());
            paymentDetails.setCustomerPhone(newDetails.getCustomerPhone());
            paymentDetails.setCustomerEmail(newDetails.getCustomerEmail());
            paymentDetails.setDeliveryCity(newDetails.getDeliveryCity());
            paymentDetails.setDemoPlan("");
            paymentDetails.setPaymentCharges("");
            paymentDetails.setPaymentTermDate(null);
            paymentDetails.setWarranty(newDetails.getWarranty());
            paymentDetails.setPanAndGst(newDetails.getPanAndGst());
            paymentDetails.setDemoDate(null);
            paymentDetails.setDeliveryAddress(newDetails.getDeliveryAddress());
            paymentDetails.setDeliveryPinCode(newDetails.getDeliveryPinCode());
            paymentDetails.setExpectedDate(null);
            paymentDetails.setShipModeName("");
            paymentDetails.setRemarks(newDetails.getRemarks());
            paymentDetails.setItsHaveDiscount(newDetails.getItsHaveDiscount());
            paymentDetails.setDiscountEstimate(newDetails.getDiscountEstimate());
            paymentDetails.setDemoPieceEstimate(newDetails.getDemoPieceEstimate());
            paymentDetails.setStockClearanceEstimate(newDetails.getStockClearanceEstimate());
            paymentDetails.setDiscountAmount(newDetails.getDiscountAmount());
            paymentDetails.setTotalProductAmount(newDetails.getTotalAmount());
            paymentDetails.setGst(newDetails.getGst());
            paymentDetails.setDeliveryCharges(newDetails.getDeliveryCharges());
            paymentDetails.setTotalAmount(newDetails.getTotalAmount());
            paymentDetails.setLessAdvance("");
            paymentDetails.setBalance("");            
            paymentDetails.setRegisterStatus(newDetails.getRegisterStatus());
            paymentDetails.setOtherStatusDescription("Null");
            paymentDetails.setCreatedDate(createdDate);
            paymentDetails.setCreatedBy(createdBy);
            return paymentDetailsRepository.save(paymentDetails);
        }
		
		@RequestMapping(value = "/fsm/editOrderDetails", method = RequestMethod.POST, consumes="application/json")
		public String editOrderDetails(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/editOrderDetails::::::::::::::::"+payload);			
				JSONObject jObj = new JSONObject(payload);
				String idString = jObj.getString("ID");	
				UUID id = UUID.fromString(idString);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Optional<OrderDetails> existingDetailsRecord = orderDetailsRepository.findById(id);
				if(existingDetailsRecord.isPresent()) {
					OrderDetails orderDetails = existingDetailsRecord.get();
					ObjectMapper mapper = new ObjectMapper();
					mapper.setDateFormat(sdf);
					String sourceString = mapper.writeValueAsString(orderDetails);
					
					List<OrderProductDetails> ordProductDetailsList = orderProductDetailsRepository.findByReferenceId(id.toString());
					String estProductSourceString = mapper.writeValueAsString(ordProductDetailsList);				
					vResponse =  "{\"status\":\"true\",\"data\":"+sourceString+",\"ordProductData\":"+estProductSourceString+"}";					
				}else {
					vResponse =  "{\"status\":\"false\"}";
				}										
				System.out.println(vResponse);
			}catch(Exception e4) {
				e4.printStackTrace();
			}
		    return vResponse;
		}
		@RequestMapping(value = "/fsm/deleteOrderDetails", method = RequestMethod.POST, consumes="application/json")
		public String deleteOrderDetails(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/deleteOrderDetails::::::::::"+payload);
			JSONObject jObj = new JSONObject(payload);
			String idString = jObj.getString("ID");			
			UUID id = UUID.fromString(idString);			
			Optional<OrderDetails> existingDetailsRecord = orderDetailsRepository.findById(id);			
			if(existingDetailsRecord.isPresent()){					
				OrderDetails ordDetails = existingDetailsRecord.get();
				orderDetailsRepository.delete(ordDetails);	
				
				List<OrderProductDetails> ordProductDetailsList = orderProductDetailsRepository.findByReferenceId(id.toString());
				if(ordProductDetailsList.size() > 0) {
					orderProductDetailsRepository.deleteAll(ordProductDetailsList);
				}
				vResponse =  "{\"status\":\"true\"}";
			}
			System.out.println("vResponse::::"+vResponse);
		}catch(Exception e4) {
			e4.printStackTrace();			
		}
	    return vResponse;
	}
		
		/******************************************************update****************************************************/
		@RequestMapping(value = "/fsm/updateOrderProductsDetails", method = RequestMethod.POST, consumes = "application/json")
		public String updateOrderProductsDetails(@RequestBody String payload) {
		    String vResponse = "{\"status\":\"false\"}";
		    try {
		        System.out.println("/fsm/updateOrderProductsDetails:::::::" + payload);		        
		        ObjectMapper mapper = new ObjectMapper();
		        List<OrderProductDetails> newDetailsList = mapper.readValue(payload, 
		                new TypeReference<List<OrderProductDetails>>() {});
		        for(OrderProductDetails newDetails: newDetailsList) {
		        	UUID id = newDetails.getId();	        
			        if (id != null) {	            
			            Optional<OrderProductDetails> existingRecord = orderProductDetailsRepository.findById(id);
			            if (existingRecord.isPresent()) {	  
			            	System.out.println("existingRecord");
			            	OrderProductDetails ordProductDetails = existingRecord.get();
			            	ordProductDetails.setProductCode(newDetails.getProductCode());
			            	ordProductDetails.setProductType(newDetails.getProductType());
			            	ordProductDetails.setProductDetails(newDetails.getProductDetails());
			            	ordProductDetails.setQty(newDetails.getQty());
			            	ordProductDetails.setReferenceId(newDetails.getReferenceId());
			            	ordProductDetails.setTax(newDetails.getTax());
			            	ordProductDetails.setUnitPrice(newDetails.getUnitPrice());
			            	ordProductDetails.setTotal(newDetails.getTotal());
			            	orderProductDetailsRepository.save(ordProductDetails);			            	
			            }
			        } else {
			        	System.out.println("newRecord");
			        	newDetails.setId(UUID.randomUUID());		        	
			        	orderProductDetailsRepository.save(newDetails);
			            
			        }
		        }
		        vResponse = "{\"status\":\"true\"}";
		    } catch (Exception e) {
		        e.printStackTrace();
		        vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
		    }
		    System.out.println("VResponse :::" + vResponse);
		    return vResponse;
		}
		
		@RequestMapping(value = "/fsm/deleteOrderProductsDetails", method = RequestMethod.POST, consumes="application/json")
		public String deleteOrderProductsDetails(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/deleteOrderProductsDetails::::::::::"+payload);
				JSONObject jObj = new JSONObject(payload);
				String idString = jObj.getString("orderProductId");
				String orderIdString = jObj.getString("orderId");
				UUID id = UUID.fromString(idString);				
				
								
				Optional<OrderProductDetails> ordProductDetailsRecord = orderProductDetailsRepository.findById(id);			
				if(ordProductDetailsRecord.isPresent()){					
					OrderProductDetails estProductDetails = ordProductDetailsRecord.get();
					orderProductDetailsRepository.delete(estProductDetails);
					
					
					int totalProductAmount= orderProductDetailsRepository.findByReferenceIdAndResetTotalProduct(orderIdString);
					UUID orderId = UUID.fromString(orderIdString);
					Optional<OrderDetails> existingDetailsRecord = orderDetailsRepository.findById(orderId);			
					if(existingDetailsRecord.isPresent()){					
						OrderDetails ordDetails = existingDetailsRecord.get();
						ordDetails.setTotalProductAmount(totalProductAmount+"");
						int finalTotalOrderAmount = totalProductAmount+Integer.parseInt(ordDetails.getDeliveryCharges())+Integer.parseInt(ordDetails.getGst());
						ordDetails.setTotalAmount(finalTotalOrderAmount+"");
						orderDetailsRepository.save(ordDetails);
					}
					vResponse =  "{\"status\":\"true\"}";
				}
				
			}catch(Exception e4) {
				e4.printStackTrace();			
			}
			System.out.println("vResponse::::"+vResponse);
		    return vResponse;
		}
	    
		
		/******************************************************List****************************************************/
		@RequestMapping(value = "/fsm/getUsersDetailsList", method = RequestMethod.POST, consumes="application/json")
		public String getUserDetailsList(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/getUsersDetailsList::::::::::"+payload);
				JSONObject jObj = new JSONObject(payload);
				String idString = jObj.getString("ID");
				System.out.println(idString);
				ObjectMapper mapper = new ObjectMapper();		
				List<UserDetails> userList = userDetailsRepository.findAll();
				String sourceString = mapper.writeValueAsString(userList);
				vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
				
			}catch(Exception e4) {
				e4.printStackTrace();
			}
			System.out.println("vResponse::::"+vResponse);
		    return vResponse;
		}
		/******************************************************edit****************************************************/
		
		@RequestMapping(value = "/fsm/editUsersDetails", method = RequestMethod.POST, consumes="application/json")
		public String editUsersDetails(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/editUsersDetails::::::::::"+payload);
				JSONObject jObj = new JSONObject(payload);
				String idString = jObj.getString("ID");
				UUID id = UUID.fromString(idString);
				ObjectMapper mapper = new ObjectMapper();
				Optional<UserDetails> userDetailsRecord = userDetailsRepository.findById(id);
				if(userDetailsRecord.isPresent()) {
					UserDetails userDetails = userDetailsRecord.get();
					String sourceString = mapper.writeValueAsString(userDetails);					
					List<UserDetails> userBasedTeamMemberList = userDetailsRepository.findByLeadUserId(userDetails.getUserId());
					String teamMemberSourceString = mapper.writeValueAsString(userBasedTeamMemberList);
					vResponse =  "{\"status\":\"true\",\"data\":"+sourceString+",\"teamMemberData\":"+teamMemberSourceString+"}";
					System.out.println(sourceString);
				}			
			}catch(Exception e4) {
				e4.printStackTrace();			
			}
			System.out.println("vResponse::::"+vResponse);
		    return vResponse;
		}
		/******************************************************delete****************************************************/
		@RequestMapping(value = "/fsm/deleteUsersDetails", method = RequestMethod.POST, consumes="application/json")
		public String deleteUsersDetails(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/deleteUsersDetails::::::::::"+payload);
				JSONObject jObj = new JSONObject(payload);
				String idString = jObj.getString("ID");
				UUID id = UUID.fromString(idString);
				ObjectMapper mapper = new ObjectMapper();
				Optional<UserDetails> userDetailsRecord = userDetailsRepository.findById(id);
				if(userDetailsRecord.isPresent()) {
					UserDetails userDetails = userDetailsRecord.get();
					userDetailsRepository.delete(userDetails);
					vResponse = "{\"status\":\"true\"}";				
				}			
			}catch(Exception e4) {
				e4.printStackTrace();			
			}
			System.out.println("vResponse::::"+vResponse);
		    return vResponse;
		}
		/******************************************************update with file****************************************************/
		@SuppressWarnings("null")
		@PostMapping("/fsm/updateUsersDetails")
		public ResponseEntity<?> updateUsersDetails(
		        @RequestParam(value = "file", required = false) MultipartFile file,
		        @RequestParam("ID") String idString,
		        @RequestParam("Users ID") String userId,
		        @RequestParam("Users Name") String userName,
		        @RequestParam("Password") String password,
		        @RequestParam("Email ID") String emailId,
		        @RequestParam("Mobile No") String mobileNo,
		        @RequestParam("Role Name") String roleName,
		        @RequestParam("Branch") String branch,
		        @RequestParam("Rep Code") String repCode,
		        @RequestParam("Rep Account") String repAccount,
		        @RequestParam("User Rights") String userRights,
		        @RequestParam("Leader User ID") String leaderUserId) {
			
			String vResponse =  "{\"status\":\"false\"}";
			System.out.println("/fsm/updateUsersDetails::::::::::"+idString+"<<<>>>"+userId+"<<>>"+password+"<<<>>>"+emailId);
			System.out.println(mobileNo+"<<<>>>"+roleName+"<<>>"+branch+"<<<>>>"+repCode+"<<<>>>"+repAccount+"<<<>>>"+userRights+"<<<>>>"+leaderUserId);
			Map<String, String> response = new HashMap<>();
		    try {
		        

		        UserDetails user = null;
		        if (!idString.equalsIgnoreCase("")) {
		        	UUID id = UUID.fromString(idString);
		            Optional<UserDetails> userDetailsRecord = userDetailsRepository.findById(id);
		            if (userDetailsRecord.isPresent()) {
		                user = userDetailsRecord.get(); 
		            }
		        } else {
		            user = new UserDetails(); 
		            user.setId(UUID.randomUUID());		            
		        }

		        user.setUserId(userId);
		        user.setUserName(userName);
		        user.setPassword(password);
		        user.setEmailId(emailId);
		        user.setMobileNo(mobileNo);
		        user.setRoleName(roleName);
		        user.setBranch(branch);
		        user.setRepCode(repCode);
		        user.setRepAccount(repAccount);
		        user.setUserRights(userRights);
		        user.setLeaderUserId(leaderUserId);  

		        if (file != null && !file.isEmpty()) {
		            String fileName = userId + "_" + file.getOriginalFilename(); 		           
		            Path filePath = Paths.get(UPLOAD_DIR + fileName);

		            Files.createDirectories(filePath.getParent()); 
		            Files.write(filePath, file.getBytes()); 

		            user.setFileName(fileName);
		        }

		        userDetailsRepository.save(user);

		        response.put("status", "true");
	            response.put("message", "User details updated successfully.");
	            
	            vResponse = "{\"status\":\"true\"}";	            
	        	System.out.println("vResponse::::"+vResponse);
	            return ResponseEntity.ok(response);

		    } catch (IOException e) {
	            e.printStackTrace();
	            response.put("status", "false");
	            response.put("error", "File upload failed: " + e.getMessage());
	            vResponse = "{\"status\":\"false\"}";	                
	            System.out.println("Error3::::"+vResponse);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.put("status", "false");
	            response.put("error", "An error occurred: " + e.getMessage());
	            vResponse = "{\"status\":\"false\"}";	                
	            System.out.println("Error4::::"+vResponse);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
		}
		
		@RequestMapping(value = "/fsm/generateUsersId", method = RequestMethod.POST, consumes = "application/json")
		public String generateUsersId(@RequestBody String payload) {
	    String vResponse = "{\"status\":\"false\"}";
	    try {
	    	System.out.println("/fsm/generateUsersId:::::::");
	    	JSONObject jObj = new JSONObject(payload);
			String idString = jObj.getString("ID");
	    	userDetailsRepository.resetUserIdSequence();
	    	String userId = userDetailsRepository.generateUserIdSequence();
	    	vResponse =  "{\"status\":\"true\",\"userId\":\""+userId+"\"}";
			}catch(Exception e) {
				e.printStackTrace();
				vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
			}
			System.out.println("VResponse :::" + vResponse);
			return vResponse;
		}
		
		
		/******************************************************update Team Member****************************************************/
		@RequestMapping(value = "/fsm/addTeamMembersToAdmin", method = RequestMethod.POST, consumes = "application/json")
		public String addTeamMembersToAdmin(@RequestBody String payload) {
		    String vResponse = "{\"status\":\"false\"}";
		    try {
		        System.out.println("/fsm/addTeamMembersToAdmin:::::::" + payload);	 
		        JSONObject jObj = new JSONObject(payload);
				String selectedTeamMember = jObj.getString("SelectedTeamMember");
				String adminUserId = jObj.getString("Admin User ID");				
				ObjectMapper mapper = new ObjectMapper();
				List<UserDetails> oldTeamMemberList = userDetailsRepository.findByLeadUserId(adminUserId);
				for(UserDetails user:oldTeamMemberList) {
					user.setLeaderUserId("");
				}
				
		        List<UserDetails> teamMemberList = mapper.readValue(selectedTeamMember, 
		                new TypeReference<List<UserDetails>>() {});
		        for(UserDetails user:teamMemberList) {
		        	 Optional<UserDetails> userDetailsRecord = userDetailsRepository.findById(user.getId());
		            if (userDetailsRecord.isPresent()) {
		            	UserDetails originalUserDetails = userDetailsRecord.get();
		            	originalUserDetails.setLeaderUserId(adminUserId);
		            	userDetailsRepository.save(originalUserDetails);
		            }		        	
		        }
		        vResponse = "{\"status\":\"true\"}";
		    } catch (Exception e) {
		        e.printStackTrace();
		        vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
		    }
		    System.out.println("VResponse :::" + vResponse);
		    return vResponse;
		}
		
		/******************************************************Add Team Member With Listt****************************************************/
		@RequestMapping(value = "/fsm/addTeamMemberWithUsersDetailsList", method = RequestMethod.POST, consumes="application/json")
		public String addTeamMemberWithUsersDetailsList(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/addTeamMemberWithUsersDetailsList::::::::::"+payload);
				JSONObject jObj = new JSONObject(payload);
				String adminUserId= jObj.getString("Admin User ID");
				System.out.println(adminUserId);
				ObjectMapper mapper = new ObjectMapper();		
				List<UserDetails> userList = userDetailsRepository.findAll();
				String sourceString = mapper.writeValueAsString(userList);
				List<UserDetails> userBasedTeamMemberList = userDetailsRepository.findByLeadUserId(adminUserId);
				String teamMemberSourceString = mapper.writeValueAsString(userBasedTeamMemberList);
				vResponse =  "{\"status\":\"true\",\"data\":"+sourceString+",\"teamMemberData\":"+teamMemberSourceString+"}";
				
			}catch(Exception e4) {
				e4.printStackTrace();
			}
			System.out.println("vResponse::::"+vResponse);
		    return vResponse;
		}
		
		@RequestMapping(value = "/fsm/getAdminDashboardDetailsIndexes", method = RequestMethod.POST, consumes="application/json")
		public String getAdminDashboardDetailsIndexes(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/getAdminDashboardDetailsIndexes::::::::::::::::"+payload);			
				JSONObject jObj = new JSONObject(payload);
				String userId = jObj.getString("userId");				
				String teamMemberIds = userDetailsRepository.getUserBasedTeamMemberUserIds(userId);
				System.out.println(teamMemberIds);
				int productCount = productDetailsRepository.getProductDetailsCount();				
				int darCount = darDetailsRepository.getFSMUserIdsBasedDarDetailsListCount(teamMemberIds);
				int estimationCount = estimationDetailsRepository.getFSMUserIdsBasedEstimationDetailsListCount(teamMemberIds);
				int orderCount = orderDetailsRepository.getFSMUserIdsBasedOrderDetailsListCount(teamMemberIds);		
				Map<String,String> dashboardCountDetails = new HashMap<String,String>();			
				dashboardCountDetails.put("productCount",productCount+"");
				dashboardCountDetails.put("darCount",darCount+"");
				dashboardCountDetails.put("estimationCount",estimationCount+"");
				dashboardCountDetails.put("orderCount",orderCount+"");				
				ObjectMapper mapper = new ObjectMapper();						
				String sourceString = mapper.writeValueAsString(dashboardCountDetails);
				vResponse =  "{\"status\":\"true\",\"data\":"+sourceString+"}";	
				System.out.println(vResponse);
			}catch(Exception e4) {
				e4.printStackTrace();
				vResponse =  "{\"status\":\"false\"}";
			}
		    return vResponse;
		}
		
		@RequestMapping(value = "/fsm/convertToOrderStatusUpdateProductsDetails", method = RequestMethod.POST, consumes = "application/json")
		public String convertToOrderStatusUpdateProductsDetails(@RequestBody String payload) {
		    String vResponse = "{\"status\":\"false\"}";
		    try {
		        System.out.println("/fsm/convertToOrderStatusUpdateProductsDetails:::::::" + payload);
		        JSONObject jObj = new JSONObject(payload);
				String estimationIdString = jObj.getString("ID");
				String orderIdString = jObj.getString("Order ID");
		        ObjectMapper mapper = new ObjectMapper();
		        List<EstimationProductDetails> estProductDetailsList = estimationProductDetailsRepository.findByReferenceId(estimationIdString);
		        List<OrderProductDetails> oldOrderProductDetailsList = orderProductDetailsRepository.findByReferenceId(orderIdString);   
		        if(oldOrderProductDetailsList.size() > 0) {
		        	orderProductDetailsRepository.deleteAll(oldOrderProductDetailsList);
		        }
		        List<OrderProductDetails> orderProductDetailsArrayList = new ArrayList<OrderProductDetails>(); 
		        for(EstimationProductDetails estProductDetails: estProductDetailsList) {
	        		OrderProductDetails ordProducts = new OrderProductDetails();
	        		ordProducts.setId(UUID.randomUUID());
	        		ordProducts.setReferenceId(orderIdString);
	        		ordProducts.setProductType("");	        		
	        		ordProducts.setProductDetails(estProductDetails.getProductDetails());
	        		ordProducts.setProductCode(estProductDetails.getProductCode());
	        		ordProducts.setQty(estProductDetails.getQty());	        		
	        		ordProducts.setTax(estProductDetails.getTax());
	        		ordProducts.setUnitPrice(estProductDetails.getUnitPrice());
	        		ordProducts.setTotal(estProductDetails.getTotal());
	        		orderProductDetailsArrayList.add(ordProducts);		            	
			    }
		        orderProductDetailsRepository.saveAll(orderProductDetailsArrayList);  
		       
		        vResponse = "{\"status\":\"true\"}";
		    } catch (Exception e) {
		        e.printStackTrace();
		        vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
		    }
		    System.out.println("VResponse :::" + vResponse);
		    return vResponse;
		}
		
		
		/************************************Filter********************************/
		@RequestMapping(value = "/fsm/getFilterUsersDetailsList", method = RequestMethod.POST, consumes = "application/json")
		public String getFilterUsersDetailsList(@RequestBody String payload) {
		    String vResponse = "{\"status\":\"false\"}";
		    try {
		        System.out.println("/fsm/getFilterUsersDetailsList::::::::::" + payload);
		        JSONObject jObj = new JSONObject(payload);
		        
		        // Extracting parameters from the JSON payload
		        String userId = jObj.optString("userId", "");
		        String userName = jObj.optString("userName", "");
		        String mobileNo = jObj.optString("mobileNo", "");
		        String roleName = jObj.optString("roleName", "");
		        String branchName = jObj.optString("branchName", "");
		        String repCode = jObj.optString("repCode", "");
		        String repAccount = jObj.optString("repAccount", "");
		        String leaderUserId = jObj.optString("leaderUserId", "");

		        // Call to the repository with the filtering parameters
		        List<UserDetails> userList = userDetailsRepository.getFilteredUserDetails(
		            userId, userName, mobileNo, roleName, branchName, repCode, repAccount, leaderUserId
		        );

		        ObjectMapper mapper = new ObjectMapper();               
		        String sourceString = mapper.writeValueAsString(userList);
		        vResponse = "{\"status\":\"true\",\"data\":" + sourceString + "}";
		        
		    } catch (Exception e4) {
		        e4.printStackTrace();
		    }
		    System.out.println("vResponse::::" + vResponse);
		    return vResponse;
		}
			
		
		@RequestMapping(value = "/fsm/getFilterCategoryDetailsList", method = RequestMethod.POST, consumes = "application/json")
		public String getFilterCategoryDetailsList(@RequestBody String payload) {
		    String vResponse = "{\"status\":\"false\"}";
		    try {
		        System.out.println("/fsm/getFilterCategoryDetailsList:::::::"+payload);
		        JSONObject jObj = new JSONObject(payload);
		        
		        String categoryId = jObj.optString("categoryId", null);
		        String categoryName = jObj.optString("categoryName", null);
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		        
		        Date createdFromDate = jObj.has("createdFromDate") ? sdf.parse(jObj.getString("createdFromDate")) : null;
		        Date createdToDate = jObj.has("createdToDate") ? sdf.parse(jObj.getString("createdToDate")) : null;
		        String createdBy = jObj.optString("createdBy", null);
		        
		        // Call to the repository with the filtering parameters
		        List<CategoryDetails> propertiesList = categoryDetailsRepository.getFilteredCategoryDetails(
		            categoryId, categoryName, createdFromDate, createdToDate, createdBy
		        );

		        ObjectMapper mapper = new ObjectMapper();
		        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		        mapper.setDateFormat(sdf1);
		        String sourceString = mapper.writeValueAsString(propertiesList);
		        vResponse = "{\"status\":\"true\",\"data\":" + sourceString + "}";
		        System.out.println(sourceString);
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return vResponse;
		}

		
		@RequestMapping(value = "/fsm/getFilterProductDetailsList", method = RequestMethod.POST, consumes = "application/json")
		public String getFilterProductDetailsList(@RequestBody String payload) {
		    String vResponse = "{\"status\":\"false\"}";
		    try {
		        System.out.println("/fsm/getFilterProductDetailsList:::::::"+payload);
		        JSONObject jObj = new JSONObject(payload);
		        
		        String productId = jObj.optString("productId", null);
		        String productName = jObj.optString("productName", null);
		        String productCategory = jObj.optString("productCategory", null);
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		        Date createdFromDate = jObj.has("createdFromDate") ? sdf.parse(jObj.getString("createdFromDate")) : null;
		        Date createdToDate = jObj.has("createdToDate") ? sdf.parse(jObj.getString("createdToDate")) : null;
		        String createdBy = jObj.optString("createdBy", null);
		        
		        List<ProductDetails> productList = productDetailsRepository.getFilteredProductDetails(
		            productId, productName, productCategory, createdFromDate, createdToDate, createdBy
		        );

		        ObjectMapper mapper = new ObjectMapper();
		        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		        mapper.setDateFormat(sdf1);
		        String sourceString = mapper.writeValueAsString(productList);
		        vResponse = "{\"status\":\"true\",\"data\":" + sourceString + "}";
		        System.out.println(sourceString);
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return vResponse;
		}
		
		@RequestMapping(value = "/fsm/getFilterDarDetailsList", method = RequestMethod.POST, consumes = "application/json")
		public String getFilterDarDetailsList(@RequestBody String payload) {
		    String vResponse = "{\"status\":\"false\"}";
		    try {
		        System.out.println("/fsm/getFilterDarDetailsList:::::::"+payload);
		        JSONObject jObj = new JSONObject(payload);
		        
		        String darNo = jObj.optString("darNo", null);
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		        Date darPerformFromDate = jObj.has("darPerformFromDate") ? sdf.parse(jObj.getString("darPerformFromDate")) : null;
		        Date darPerformToDate = jObj.has("darPerformToDate") ? sdf.parse(jObj.getString("darPerformToDate")) : null;
		        String clientName = jObj.optString("clientName", null);
		        String clientMobileNo = jObj.optString("clientMobileNo", null);
		        String statusToVisit = jObj.optString("statusToVisit", null);
		        Date createdFromDate = jObj.has("createdFromDate") ? sdf.parse(jObj.getString("createdFromDate")) : null;
		        Date createdToDate = jObj.has("createdToDate") ? sdf.parse(jObj.getString("createdToDate")) : null;
		        String createdBy = jObj.optString("createdBy", null);
		        		        
		        List<DarDetails> detailsList = darDetailsRepository.getFilteredDarDetails(
		            darNo, darPerformFromDate, darPerformToDate, clientName, clientMobileNo, 
		            statusToVisit, createdFromDate, createdToDate, createdBy
		        );

		        ObjectMapper mapper = new ObjectMapper();
		        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		        mapper.setDateFormat(sdf1);
		        String sourceString = mapper.writeValueAsString(detailsList);
		        vResponse = "{\"status\":\"true\",\"data\":" + sourceString + "}";
		        System.out.println(sourceString);
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return vResponse;
		}

		@RequestMapping(value = "/fsm/getFilterEstimationDetailsList", method = RequestMethod.POST, consumes = "application/json")
		public String getFilterEstimationDetailsList(@RequestBody String payload) {
		    String vResponse = "{\"status\":\"false\"}";
		    try {
		        System.out.println("/fsm/getFilterEstimationDetailsList:::::::" + payload);
		        JSONObject jObj = new JSONObject(payload);

		        String estNo = jObj.optString("estNo", null);
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		        
		        Date estimationPerformFromDate = jObj.has("estimationPerformFromDate") && !jObj.isNull("estimationPerformFromDate") 
		            ? sdf.parse(jObj.getString("estimationPerformFromDate")) 
		            : null;
		        
		        Date estimationPerformToDate = jObj.has("estimationPerformToDate") && !jObj.isNull("estimationPerformToDate") 
		            ? sdf.parse(jObj.getString("estimationPerformToDate")) 
		            : null;
		        
		        String customerName = jObj.optString("customerName", null);
		        String repAttD = jObj.optString("repAttD", null);
		        String repAccount = jObj.optString("repAccount", null);
		        String mobileNo = jObj.optString("mobileNo", null);
		        String itsHaveDiscount = jObj.optString("itsHaveDiscount", null);
		        String estimationStatus = jObj.optString("estimationStatus", null);
		        
		        Date createdFromDate = jObj.has("createdFromDate") && !jObj.isNull("createdFromDate") 
		            ? sdf.parse(jObj.getString("createdFromDate")) 
		            : null;
		        
		        Date createdToDate = jObj.has("createdToDate") && !jObj.isNull("createdToDate") 
		            ? sdf.parse(jObj.getString("createdToDate")) 
		            : null;
		        
		        String createdBy = jObj.optString("createdBy", null);

		        // Call to repository with filtered parameters
		        List<EstimationDetails> detailsList = estimationDetailsRepository.getFilteredEstimationDetails(
		            estNo, customerName, estimationPerformFromDate, estimationPerformToDate, 
		            repAttD, repAccount, mobileNo, itsHaveDiscount, 
		            estimationStatus, createdFromDate, createdToDate, createdBy
		        );

		        ObjectMapper mapper = new ObjectMapper();
		        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		        mapper.setDateFormat(sdf1);
		        String sourceString = mapper.writeValueAsString(detailsList);
		        vResponse = "{\"status\":\"true\",\"data\":" + sourceString + "}";
		        System.out.println(vResponse);
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return vResponse;
		}
		
		@RequestMapping(value = "/fsm/getFilterOrderDetailsList", method = RequestMethod.POST, consumes = "application/json")
		public String getFilterOrderDetailsList(@RequestBody String payload) {
		    String vResponse = "{\"status\":\"false\"}";
		    try {
		        System.out.println("/fsm/getFilterOrderDetailsList:::::::" + payload);
		        JSONObject jObj = new JSONObject(payload);

		        String eNo = jObj.optString("eNo", null);
		        String estNo = jObj.optString("estNo", null);
		        String orderNo = jObj.optString("orderNo", null);
		        String soNo = jObj.optString("soNo", null);
		        String ddNo = jObj.optString("ddNo", null);
		        String customerName = jObj.optString("customerName", null);

		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		        Date orderPerformFromDate = jObj.has("orderPerformFromDate") && !jObj.isNull("orderPerformFromDate") 
		            ? sdf.parse(jObj.getString("orderPerformFromDate")) 
		            : null;
		        
		        Date orderPerformToDate = jObj.has("orderPerformToDate") && !jObj.isNull("orderPerformToDate") 
		            ? sdf.parse(jObj.getString("orderPerformToDate")) 
		            : null;
		        
		        String repAttD = jObj.optString("repAttD", null);
		        String mobileNo = jObj.optString("mobileNo", null);
		        String demoPlan = jObj.optString("demoPlan", null);

		        Date demoFromDate = jObj.has("demoFromDate") && !jObj.isNull("demoFromDate") 
		            ? sdf.parse(jObj.getString("demoFromDate")) 
		            : null;
		        
		        Date demoToDate = jObj.has("demoToDate") && !jObj.isNull("demoToDate") 
		            ? sdf.parse(jObj.getString("demoToDate")) 
		            : null;
		        
		        String itsHaveDiscount = jObj.optString("itsHaveDiscount", null);
		        String orderStatus = jObj.optString("orderStatus", null);

		        Date createdFromDate = jObj.has("createdFromDate") && !jObj.isNull("createdFromDate") 
		            ? sdf.parse(jObj.getString("createdFromDate")) 
		            : null;
		        
		        Date createdToDate = jObj.has("createdToDate") && !jObj.isNull("createdToDate") 
		            ? sdf.parse(jObj.getString("createdToDate")) 
		            : null;
		        
		        String createdBy = jObj.optString("createdBy", null);

		        List<OrderDetails> detailsList = orderDetailsRepository.getFilteredOrderDetails(
		            eNo, estNo, orderNo, soNo, ddNo, customerName,
		            orderPerformFromDate, orderPerformToDate, repAttD, mobileNo,
		            demoPlan, demoFromDate, demoToDate, itsHaveDiscount,
		            orderStatus, createdFromDate, createdToDate, createdBy
		        );

		        ObjectMapper mapper = new ObjectMapper();
		        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		        mapper.setDateFormat(sdf1);
		        String sourceString = mapper.writeValueAsString(detailsList);
		        vResponse = "{\"status\":\"true\",\"data\":"+ sourceString +"}";
		        System.out.println(vResponse);
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return vResponse;
		}
		
		/******************************************************update****************************************************/
		@RequestMapping(value = "/fsm/updateDefaultPropertiesDetails", method = RequestMethod.POST, consumes="application/json")
		public String updateDefaultPropertiesDetails(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				JSONObject jObj = new JSONObject(payload);	
				String idString = jObj.getString("ID");
				String propertyName = jObj.getString("Property Name");
				String propertyValue= jObj.getString("Property Value");
				DefaultPropertiesDetails propertyDetails = null;			
				if(!idString.equalsIgnoreCase("")) {
					UUID id = UUID.fromString(idString);
					Optional<DefaultPropertiesDetails> propertyDetailsRecord = defaultPropertiesRepository.findById(id);
					if(propertyDetailsRecord.isPresent()){
						propertyDetails = propertyDetailsRecord.get();
						propertyDetails.setPropertyName(propertyName);
						propertyDetails.setPropertyValue(propertyValue);
						defaultPropertiesRepository.save(propertyDetails);
						vResponse = "{\"status\":\"true\"}";
					}											
				}else {
					ObjectMapper mapper = new ObjectMapper();
					propertyDetails = mapper.readValue(jObj.toString(), DefaultPropertiesDetails.class);
					propertyDetails.setId(UUID.randomUUID());
					defaultPropertiesRepository.save(propertyDetails);
					vResponse = "{\"status\":\"true\"}";	
				}
						
			}catch(Exception e4) {
				e4.printStackTrace();
			}
		    return vResponse;
		}

		/******************************************************List****************************************************/
		@RequestMapping(value = "/fsm/getDefaultPropertiesList", method = RequestMethod.POST, consumes="application/json")
		public String getDefaultPropertiesList(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/getDefaultPropertiesList:::::::" + payload);
				JSONObject jObj = new JSONObject(payload);
				String id = jObj.getString("ID");
				System.out.println(id);
				ObjectMapper mapper = new ObjectMapper();
				List<DefaultPropertiesDetails> propertiesList = defaultPropertiesRepository.findAll();
				String sourceString = mapper.writeValueAsString(propertiesList);
				vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
				
			}catch(Exception e4) {
				e4.printStackTrace();
			}
			System.out.println("vResponse:::::::" + vResponse);
		    return vResponse;
		}

		/******************************************************edit****************************************************/
		
		@RequestMapping(value = "/fsm/editDefaultPropertiesDetails", method = RequestMethod.POST, consumes="application/json")
		public String editDefaultPropertiesDetails(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/editDefaultPropertiesDetails::::::::::::::::"+payload);
				JSONObject jObj = new JSONObject(payload);
				String idString = jObj.getString("ID");
				System.out.println(idString);
				UUID id = UUID.fromString(idString);
				ObjectMapper mapper = new ObjectMapper();
				Optional<DefaultPropertiesDetails> propertyDetailsRecord = defaultPropertiesRepository.findById(id);
				if(propertyDetailsRecord.isPresent()) {
					DefaultPropertiesDetails propertyDetails = propertyDetailsRecord.get();
					String sourceString = mapper.writeValueAsString(propertyDetails);
					vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";					
				}			
			}catch(Exception e4) {
				e4.printStackTrace();			
			}
			System.out.println("vResponse:::::::" + vResponse);
		    return vResponse;
		}
	/******************************************************Get by name****************************************************/
		
		@RequestMapping(value = "/fsm/getDefaultPropertyValuesByName", method = RequestMethod.POST, consumes="application/json")
		public String getDefaultPropertyValuesByName(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/getDefaultPropertyValuesByName:::::::" + payload);
				JSONObject jObj = new JSONObject(payload);
				String propertyName = jObj.getString("Property Name");
				System.out.println(propertyName);			
				ObjectMapper mapper = new ObjectMapper();
				Optional<DefaultPropertiesDetails> propertyDetailsRecord = defaultPropertiesRepository.findByPropertyName(propertyName);
				if(propertyDetailsRecord.isPresent()) {
					DefaultPropertiesDetails propertyDetails = propertyDetailsRecord.get();
					String sourceString = mapper.writeValueAsString(propertyDetails);
					vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
					System.out.println(sourceString);
				}			
			}catch(Exception e4) {
				e4.printStackTrace();			
			}
			System.out.println("vResponse:::::::" + vResponse);
		    return vResponse;
		}
		/******************************************************delete****************************************************/
		@RequestMapping(value = "/fsm/deleteDefaultPropertiesDetails", method = RequestMethod.POST, consumes="application/json")
		public String deleteDefaultPropertiesDetails(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/deleteDefaultPropertiesDetails:::::::" + payload);
				JSONObject jObj = new JSONObject(payload);
				String idString = jObj.getString("ID");
				System.out.println(idString);
				UUID id = UUID.fromString(idString);
				ObjectMapper mapper = new ObjectMapper();
				Optional<DefaultPropertiesDetails> propertyDetailsRecord = defaultPropertiesRepository.findById(id);
				if(propertyDetailsRecord.isPresent()) {
					DefaultPropertiesDetails propertyDetails = propertyDetailsRecord.get();
					defaultPropertiesRepository.delete(propertyDetails);
					vResponse = "{\"status\":\"true\"}";							
				}			
			}catch(Exception e4) {
				e4.printStackTrace();			
			}
			System.out.println("vResponse:::::::" + vResponse);
		    return vResponse;
		}
		
		@RequestMapping(value = "/fsm/updateEstimationDetailsInTableRow", method = RequestMethod.POST, consumes = "application/json")
	    public String updateEstimationDetailsInTableRow(@RequestBody String payload) {
	        String vResponse = "{\"status\":\"false\"}";
	        try {
	            System.out.println("/fsm/updateEstimationDetailsInTableRow:::::::" + payload);

	            ObjectMapper mapper = new ObjectMapper();
	            JSONObject jObj = new JSONObject(payload);
	            String estimationIdString = jObj.getString("ID");
	            String estimationStatus = jObj.getString("Estimation Status");
	            String createdDateString = jObj.getString("Created Date");
	            String createdBy = jObj.getString("Created By");
	           
	            Instant instant = Instant.parse(createdDateString);
	            Timestamp createdDate = Timestamp.from(instant);
	            UUID estimationId = UUID.fromString(estimationIdString);

	            Optional<EstimationDetails> existingRecord = estimationDetailsRepository.findById(estimationId);
	            if (existingRecord.isPresent()) {
		            System.out.println("Existing Estimation Record Found");
	                EstimationDetails newDetails = existingRecord.get();
	                newDetails.setRegisterStatus(estimationStatus);
	                newDetails.setCreatedDate(createdDate);
	                newDetails.setCreatedBy(createdBy);
	                estimationDetailsRepository.save(newDetails);
	                vResponse = "{\"status\":\"true\"}";
	                if (estimationStatus.equalsIgnoreCase("Convert To Order")) {	                		                    
	                    String sourceString;	                    
	                    Optional<OrderDetails> existingOrderRecord = orderDetailsRepository.findByEstNo(newDetails.getEstNO());
	                    if (existingOrderRecord.isPresent()) {	                    	
	                        System.out.println("existing Order Record found");
	                        updateOrderDetailsInTableRow(existingOrderRecord.get(), newDetails, createdDate, createdBy);	                        
	                        vResponse = "{\"status\":\"true\"}";	
		                    
	                    } else {
	                        System.out.println("new Order Record Created");
	                        OrderDetails orderDetails = new OrderDetails();
	                        UUID orderId = UUID.randomUUID();
	                        orderDetails.setId(orderId);
	                        updateOrderDetailsInTableRow(orderDetails, newDetails, createdDate, createdBy);
	                        orderDetailsRepository.save(orderDetails);	                        
	                        boolean orderProductConvertStatus = convertToOrderStatusUpdateProductsDetailsInTableRow(estimationIdString, orderId.toString());
	                        if (orderProductConvertStatus) {
	                        	vResponse = "{\"status\":\"true\"}";	
		                    }else {
		                    	vResponse = "{\"status\":\"false\"}";
		                    }
	                    }
	                    
	                }
	            }else {
	            	vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
	        }
	        System.out.println("VResponse :::" + vResponse);
	        return vResponse;
	    }
		
		
		private OrderDetails updateOrderDetailsInTableRow(OrderDetails orderDetails, EstimationDetails newDetails, Timestamp createdDate, String createdBy) {
	        orderDetails.seteNo("");
	        orderDetails.setEstNo(newDetails.getEstNO());
	        orderDetails.setOrderNo(orderDetailsRepository.generateOrderSequence());
	        orderDetails.setSoNo("");
	        orderDetails.setDdNo("");
	        orderDetails.setCustomerName(newDetails.getCustomerName());
	        orderDetails.setOrderProcessDate(new Timestamp(System.currentTimeMillis()));
	        orderDetails.setRepCode(newDetails.getRepAttd());
	        orderDetails.setBillingName("");
	        orderDetails.setBillingAddress(newDetails.getBillingAddress());
	        orderDetails.setCustomerCity(newDetails.getCustomerCity());
	        orderDetails.setCustomerPinCode(newDetails.getCustomerPinCode());
	        orderDetails.setCustomerPhone(newDetails.getCustomerPhone());
	        orderDetails.setCustomerEmail(newDetails.getCustomerEmail());
	        orderDetails.setDeliveryCity(newDetails.getDeliveryCity());
	        orderDetails.setDemoPlan("");
	        orderDetails.setPaymentCharges("");
	        orderDetails.setPaymentTermDate(null);
	        orderDetails.setWarranty(newDetails.getWarranty());
	        orderDetails.setPanAndGst(newDetails.getPanAndGst());
	        orderDetails.setDemoDate(null);
	        orderDetails.setDeliveryAddress(newDetails.getDeliveryAddress());
	        orderDetails.setDeliveryPinCode(newDetails.getDeliveryPinCode());
	        orderDetails.setExpectedDate(null);
	        orderDetails.setShipModeName("");
	        orderDetails.setRemarks(newDetails.getRemarks());
	        orderDetails.setItsHaveDiscount(newDetails.getItsHaveDiscount());
	        orderDetails.setDiscountEstimate(newDetails.getDiscountEstimate());
	        orderDetails.setDemoPieceEstimate(newDetails.getDemoPieceEstimate());
	        orderDetails.setStockClearanceEstimate(newDetails.getStockClearanceEstimate());
	        orderDetails.setDiscountAmount(newDetails.getDiscountAmount());
	        orderDetails.setTotalProductAmount(newDetails.getTotalAmount());
	        orderDetails.setGst(newDetails.getGst());
	        orderDetails.setDeliveryCharges(newDetails.getDeliveryCharges());
	        orderDetails.setTotalAmount(newDetails.getTotalAmount());
	        orderDetails.setLessAdvance("");
	        orderDetails.setBalance("");
	        orderDetails.setRegisterStatus("New Order");
	        orderDetails.setCreatedDate(createdDate);
	        orderDetails.setCreatedBy(createdBy);
	        return orderDetailsRepository.save(orderDetails);
	    }

	    public boolean convertToOrderStatusUpdateProductsDetailsInTableRow(String estimationIdString, String orderIdString) {
	        boolean vResponse = false;
	        try {
	            System.out.println("Normal Method ConvertToOrderStatusUpdateProductsDetails:::::::");
	            List<EstimationProductDetails> estProductDetailsList = estimationProductDetailsRepository.findByReferenceId(estimationIdString);
	            List<OrderProductDetails> oldOrderProductDetailsList = orderProductDetailsRepository.findByReferenceId(orderIdString);
	            if (!oldOrderProductDetailsList.isEmpty()) {
	                orderProductDetailsRepository.deleteAll(oldOrderProductDetailsList);
	            }
	            List<OrderProductDetails> orderProductDetailsArrayList = new ArrayList<>();
	            for (EstimationProductDetails estProductDetails : estProductDetailsList) {
	                OrderProductDetails ordProducts = new OrderProductDetails();
	                ordProducts.setId(UUID.randomUUID());
	                ordProducts.setReferenceId(orderIdString);
	                ordProducts.setProductType("");
	                ordProducts.setProductDetails(estProductDetails.getProductDetails());
	                ordProducts.setProductCode(estProductDetails.getProductCode());
	                ordProducts.setQty(estProductDetails.getQty());
	                ordProducts.setTax(estProductDetails.getTax());
	                ordProducts.setUnitPrice(estProductDetails.getUnitPrice());
	                ordProducts.setTotal(estProductDetails.getTotal());
	                orderProductDetailsArrayList.add(ordProducts);
	            }
	            orderProductDetailsRepository.saveAll(orderProductDetailsArrayList);
	            vResponse = true;
	        } catch (Exception e) {
	            vResponse = false;
	            e.printStackTrace();
	        }
	        System.out.println("VResponse :::" + vResponse);
	        return vResponse;
	    }

	    @RequestMapping(value = "/fsm/getPaymentDetailsList", method = RequestMethod.POST, consumes="application/json")
		public String getPaymentDetailsList(@RequestBody String payload) {
			String vResponse =  "{\"status\":\"false\"}";
			try {
				System.out.println("/fsm/getPaymentDetailsList:::::::");
				JSONObject jObj = new JSONObject(payload);
				String userId = jObj.getString("User ID");				
				String teamMemberIds = userDetailsRepository.getUserBasedTeamMemberUserIds(userId);
				System.out.println(teamMemberIds);
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				ObjectMapper mapper = new ObjectMapper();
				List<PaymentDetails> detailsList = paymentDetailsRepository.getFSMUserIdsBasedPaymentDetailsList(teamMemberIds);
				if(detailsList.size() > 0) {
					mapper.setDateFormat(sdf);
					String sourceString = mapper.writeValueAsString(detailsList);
					vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";					
				}
				
			}catch(Exception e4) {
				e4.printStackTrace();
			}
			System.out.println(vResponse);
		    return vResponse;
		}
	    
	    @RequestMapping(value = "/fsm/getFilterPaymentDetailsList", method = RequestMethod.POST, consumes = "application/json")
		public String getFilterPaymentDetailsList(@RequestBody String payload) {
		    String vResponse = "{\"status\":\"false\"}";
		    try {
		        System.out.println("/fsm/getFilterPaymentDetailsList:::::::" + payload);
		        JSONObject jObj = new JSONObject(payload);

		        String eNo = jObj.optString("eNo", null);
		        String estNo = jObj.optString("estNo", null);
		        String orderNo = jObj.optString("orderNo", null);
		        String soNo = jObj.optString("soNo", null);
		        String ddNo = jObj.optString("ddNo", null);
		        String customerName = jObj.optString("customerName", null);

		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		        Date orderPerformFromDate = jObj.has("orderPerformFromDate") && !jObj.isNull("orderPerformFromDate") 
		            ? sdf.parse(jObj.getString("orderPerformFromDate")) 
		            : null;
		        
		        Date orderPerformToDate = jObj.has("orderPerformToDate") && !jObj.isNull("orderPerformToDate") 
		            ? sdf.parse(jObj.getString("orderPerformToDate")) 
		            : null;
		        
		        String repAttD = jObj.optString("repAttD", null);
		        String mobileNo = jObj.optString("mobileNo", null);
		        String demoPlan = jObj.optString("demoPlan", null);

		        Date demoFromDate = jObj.has("demoFromDate") && !jObj.isNull("demoFromDate") 
		            ? sdf.parse(jObj.getString("demoFromDate")) 
		            : null;
		        
		        Date demoToDate = jObj.has("demoToDate") && !jObj.isNull("demoToDate") 
		            ? sdf.parse(jObj.getString("demoToDate")) 
		            : null;
		        
		        String itsHaveDiscount = jObj.optString("itsHaveDiscount", null);
		        String paymentStatus = jObj.optString("paymentStatus", null);

		        Date createdFromDate = jObj.has("createdFromDate") && !jObj.isNull("createdFromDate") 
		            ? sdf.parse(jObj.getString("createdFromDate")) 
		            : null;
		        
		        Date createdToDate = jObj.has("createdToDate") && !jObj.isNull("createdToDate") 
		            ? sdf.parse(jObj.getString("createdToDate")) 
		            : null;
		        
		        String createdBy = jObj.optString("createdBy", null);

		        List<PaymentDetails> detailsList = paymentDetailsRepository.getFilteredPaymentDetails(
		            eNo, estNo, orderNo, soNo, ddNo, customerName,
		            orderPerformFromDate, orderPerformToDate, repAttD, mobileNo,
		            demoPlan, demoFromDate, demoToDate, itsHaveDiscount,
		            paymentStatus, createdFromDate, createdToDate, createdBy
		        );

		        ObjectMapper mapper = new ObjectMapper();
		        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		        mapper.setDateFormat(sdf1);
		        String sourceString = mapper.writeValueAsString(detailsList);
		        vResponse = "{\"status\":\"true\",\"data\":"+ sourceString +"}";
		        System.out.println(vResponse);
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return vResponse;
		}
	    
	    	    
	    @RequestMapping(value = "/fsm/updatePaymentDetailsInTableRow", method = RequestMethod.POST, consumes = "application/json")
	    public String updatePaymentDetailsInTableRow(@RequestBody String payload) {
	        String vResponse = "{\"status\":\"false\"}";
	        try {
	            System.out.println("/fsm/updatePaymentDetailsInTableRow:::::::" + payload);
	            ObjectMapper mapper = new ObjectMapper();
	            JSONObject jObj = new JSONObject(payload);
	            String paymentIdString = jObj.getString("ID");
	            String paymentStatus = jObj.getString("Payment Status");
	            String createdDateString = jObj.getString("Created Date");
	            String createdBy = jObj.getString("Created By");	           
	            Instant instant = Instant.parse(createdDateString);
	            Timestamp createdDate = Timestamp.from(instant);
	            UUID paymentId = UUID.fromString(paymentIdString);
	            Optional<PaymentDetails> existingRecord = paymentDetailsRepository.findById(paymentId);
	            if (existingRecord.isPresent()) {
		            System.out.println("Existing Estimation Record Found");
	                PaymentDetails newDetails = existingRecord.get();
	                newDetails.setRegisterStatus(paymentStatus);
	                newDetails.setCreatedDate(createdDate);
	                newDetails.setOtherStatusDescription("Null");
	                newDetails.setCreatedBy(createdBy);
	                paymentDetailsRepository.save(newDetails);
	                vResponse = "{\"status\":\"true\"}";	                
	            }else {
	            	vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
	        }
	        System.out.println("VResponse :::" + vResponse);
	        return vResponse;
	    }
	    
	    @RequestMapping(value = "/fsm/deletePaymentDetails", method = RequestMethod.POST, consumes="application/json")
		public String deletePaymentDetails(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/deletePaymentDetails::::::::::"+payload);
			JSONObject jObj = new JSONObject(payload);
			String idString = jObj.getString("ID");			
			UUID id = UUID.fromString(idString);			
			Optional<PaymentDetails> existingDetailsRecord = paymentDetailsRepository.findById(id);			
			if(existingDetailsRecord.isPresent()){					
				PaymentDetails ordDetails = existingDetailsRecord.get();
				paymentDetailsRepository.delete(ordDetails);					
				vResponse =  "{\"status\":\"true\"}";
			}
			System.out.println("vResponse::::"+vResponse);
		}catch(Exception e4) {
			e4.printStackTrace();			
		}
	    return vResponse;
	}
	    
	    
	    @RequestMapping(value = "/fsm/updatePaymentDetailsInTableRowStatusOtherDescription", method = RequestMethod.POST, consumes = "application/json")
	    public String updatePaymentDetailsInTableRowStatusOtherDescription(@RequestBody String payload) {
	        String vResponse = "{\"status\":\"false\"}";
	        try {
	            System.out.println("/fsm/updatePaymentDetailsInTableRowStatusOtherDescription:::::::" + payload);
	            ObjectMapper mapper = new ObjectMapper();
	            JSONObject jObj = new JSONObject(payload);
	            String paymentIdString = jObj.getString("ID");
	            String paymentStatus = jObj.getString("Payment Status");
	            String otherStatusDescription = jObj.getString("Other Status Description");
	            String createdDateString = jObj.getString("Created Date");
	            String createdBy = jObj.getString("Created By");	           
	            Instant instant = Instant.parse(createdDateString);
	            Timestamp createdDate = Timestamp.from(instant);
	            UUID paymentId = UUID.fromString(paymentIdString);
	            Optional<PaymentDetails> existingRecord = paymentDetailsRepository.findById(paymentId);
	            if (existingRecord.isPresent()) {
		            System.out.println("Existing Estimation Record Found");
	                PaymentDetails newDetails = existingRecord.get();
	                newDetails.setRegisterStatus(paymentStatus);
	                newDetails.setOtherStatusDescription(otherStatusDescription);
	                newDetails.setCreatedDate(createdDate);
	                newDetails.setCreatedBy(createdBy);
	                paymentDetailsRepository.save(newDetails);
	                vResponse = "{\"status\":\"true\"}";	                
	            }else {
	            	vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
	        }
	        System.out.println("VResponse :::" + vResponse);
	        return vResponse;
	    }
	        

}
