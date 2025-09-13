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
import java.time.format.DateTimeParseException;
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
import com.sy.fsm.Model.CategoryDetails;
import com.sy.fsm.Model.DarDetails;
import com.sy.fsm.Model.DarExpensesDetails;
import com.sy.fsm.Model.DefaultPropertiesDetails;
import com.sy.fsm.Model.EstimationDetails;
import com.sy.fsm.Model.EstimationProductDetails;
import com.sy.fsm.Model.MobileCategoryDetails;
import com.sy.fsm.Model.MobileDarDetails;
import com.sy.fsm.Model.MobileDarExpensesDetails;
import com.sy.fsm.Model.MobileDefaultPropertiesDetails;
import com.sy.fsm.Model.MobileEstimationDetails;
import com.sy.fsm.Model.MobileEstimationProductDetails;
import com.sy.fsm.Model.MobileOrderDetails;
import com.sy.fsm.Model.MobileOrderProductDetails;
import com.sy.fsm.Model.MobileProductDetails;
import com.sy.fsm.Model.MobileUserDetails;
import com.sy.fsm.Model.OrderDetails;
import com.sy.fsm.Model.OrderProductDetails;
import com.sy.fsm.Model.ProductDetails;
import com.sy.fsm.Model.UserDetails;
import com.sy.fsm.Repository.CategoryDetailsRepository;
import com.sy.fsm.Repository.DarDetailsRepository;
import com.sy.fsm.Repository.DarExpensesDetailsRepository;
import com.sy.fsm.Repository.EstimationDetailsRepository;
import com.sy.fsm.Repository.EstimationProductDetailsRepository;
import com.sy.fsm.Repository.MobileCategoryDetailsRepository;
import com.sy.fsm.Repository.MobileDarDetailsRepository;
import com.sy.fsm.Repository.MobileDarExpensesDetailsRepository;
import com.sy.fsm.Repository.MobileDefaultPropertiesRepository;
import com.sy.fsm.Repository.MobileEstimationDetailsRepository;
import com.sy.fsm.Repository.MobileEstimationProductDetailsRepository;
import com.sy.fsm.Repository.MobileOrderDetailsRepository;
import com.sy.fsm.Repository.MobileOrderProductDetailsRepository;
import com.sy.fsm.Repository.MobileProductDetailsRepository;
import com.sy.fsm.Repository.MobileUserDetailsRepository;
import com.sy.fsm.Repository.OrderDetailsRepository;
import com.sy.fsm.Repository.OrderProductDetailsRepository;
import com.sy.fsm.Repository.ProductDetailsRepository;
import com.sy.fsm.Repository.UserDetailsRepository;




@org.springframework.web.bind.annotation.RestController
public class MobileRestController {
	
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
	MobileEstimationDetailsRepository mobileEstimationDetailsRepository;
	
	@Autowired
	MobileEstimationProductDetailsRepository mobileEstimationProductDetailsRepository;
	
	@Autowired
	MobileDarDetailsRepository mobileDarDetailsRepository;
	
	@Autowired
	MobileDarExpensesDetailsRepository mobileDarExpensesDetailsRepository;
	
	@Autowired
	MobileProductDetailsRepository mobileProductDetailsRepository;
	
	@Autowired
	MobileCategoryDetailsRepository mobileCategoryDetailsRepository;
	
	@Autowired
	MobileUserDetailsRepository mobileUserDetailsRepository;
	
	@Autowired
	MobileOrderDetailsRepository mobileOrderDetailsRepository;
	
	@Autowired
	MobileOrderProductDetailsRepository mobileOrderProductDetailsRepository;
	
	@Autowired
	MobileDefaultPropertiesRepository mobileDefaultPropertiesRepository;
	
	@RequestMapping(value = "/fsm/customerSignIn", method = RequestMethod.POST, consumes="application/json")
	public String customerSignIn(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/customerSignIn::::::::::::::::"+payload);
			JSONObject jObj = new JSONObject(payload);
			String mobileNo = jObj.getString("phoneNumber");
			String password = jObj.getString("password");
			Optional<UserDetails> userDetailsRecord = userDetailsRepository.findByMoblieNoAndPassword(mobileNo,password);
			if(userDetailsRecord.isPresent()) {
				UserDetails userDetails = userDetailsRecord.get();
				ObjectMapper mapper = new ObjectMapper();
				String sourceString = mapper.writeValueAsString(userDetails);					
				List<UserDetails> userBasedTeamMemberList = userDetailsRepository.findByLeadUserId(userDetails.getUserId());
				String teamMemberSourceString = mapper.writeValueAsString(userBasedTeamMemberList);
				vResponse =  "{\"status\":\"true\",\"data\":"+sourceString+",\"teamMemberData\":"+teamMemberSourceString+"}";											
			}else {
				vResponse =  "{\"status\":\"false\",\"errorMessage\":\"Invalid Phone Number\"}";
			}										
			System.out.println(vResponse);
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
    
	
	/******************************************************update****************************************************/
    @RequestMapping(value = "/fsm/updateMobileEstimationDetails", method = RequestMethod.POST, consumes = "application/json")
    public String updateMobileEstimationDetails(@RequestBody String payload) {
        String vResponse = "{\"status\":\"false\"}";
        try {
            System.out.println("/fsm/updateMobileEstimationDetails:::::::" + payload);	        
            ObjectMapper mapper = new ObjectMapper();
            MobileEstimationDetails newDetails = mapper.readValue(payload, MobileEstimationDetails.class);
            
            UUID id = newDetails.getId(); 
            
            if (newDetails.getEstNO() != null) { 
                Optional<MobileEstimationDetails> existingRecord = mobileEstimationDetailsRepository.findById(id);
                
                if (existingRecord.isPresent()) {                    
                    System.out.println("existingRecord found");
                    MobileEstimationDetails estDetails = existingRecord.get();

                    // Update fields with the new data from `newDetails`
                    estDetails.setEstNO(newDetails.getEstNO());
                    estDetails.setCustomerName(newDetails.getCustomerName());
                    estDetails.setEstimationProcessDate(newDetails.getEstimationProcessDate());
                    estDetails.setRepAttd(newDetails.getRepAttd());
                    estDetails.setRepAcc(newDetails.getRepAcc());
                    estDetails.setBillingAddress(newDetails.getBillingAddress());
                    estDetails.setDeliveryAddress(newDetails.getDeliveryAddress());
                    estDetails.setBillingCity(newDetails.getBillingCity());
                    estDetails.setBillingPin(newDetails.getBillingPin());
                    estDetails.setPhone(newDetails.getPhone());
                    estDetails.setEmail(newDetails.getEmail());
                    estDetails.setDeliveryCity(newDetails.getDeliveryCity());
                    estDetails.setDeliveryPin(newDetails.getDeliveryPin());
                    estDetails.setWarranty(newDetails.getWarranty());
                    estDetails.setPanGst(newDetails.getPanGst());
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

                    
                    mobileEstimationDetailsRepository.save(estDetails);
                    vResponse = "{\"status\":\"true\"}";
                    
                    if (newDetails.getRegisterStatus().equalsIgnoreCase("Convert To Order")) {
                        try {
                        	System.out.println("Convert To Order Process #####");
                        	Optional<OrderDetails> existingOrderRecord = orderDetailsRepository.findByEstNo(newDetails.getEstNO());
                        	if(existingOrderRecord.isPresent()) {
                        		OrderDetails orderDetails = existingOrderRecord.get();
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
	                            orderDetails.setCustomerCity(newDetails.getBillingCity());
	                            orderDetails.setCustomerPinCode(vResponse);
	                            orderDetails.setCustomerPhone(newDetails.getPhone());
	                            orderDetails.setCustomerEmail(newDetails.getEmail());
	                            orderDetails.setDeliveryCity(newDetails.getDeliveryCity());
	                            orderDetails.setDemoPlan("");
	                            orderDetails.setPaymentCharges("");
	                            orderDetails.setPaymentTermDate(null);  // Assuming no payment date
	                            orderDetails.setWarranty(newDetails.getWarranty());
	                            orderDetails.setPanAndGst(newDetails.getPanGst());
	                            orderDetails.setDemoDate(null);
	                            orderDetails.setDeliveryAddress(newDetails.getDeliveryAddress());
	                            orderDetails.setDeliveryPinCode(newDetails.getDeliveryPin());
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
	                            orderDetails.setRegisterStatus(newDetails.getRegisterStatus());
	                            orderDetails.setCreatedDate(newDetails.getCreatedDate());
	                            orderDetails.setCreatedBy(newDetails.getCreatedBy());
	                            orderDetailsRepository.save(orderDetails);
                        	}else {
                        	
	                            OrderDetails orderDetails = new OrderDetails();
	                            orderDetails.setId(UUID.randomUUID());
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
	                            orderDetails.setCustomerCity(newDetails.getBillingCity());
	                            orderDetails.setCustomerPinCode(newDetails.getBillingPin());
	                            orderDetails.setCustomerPhone(newDetails.getPhone());
	                            orderDetails.setCustomerEmail(newDetails.getEmail());
	                            orderDetails.setDeliveryCity(newDetails.getDeliveryCity());
	                            orderDetails.setDemoPlan("");
	                            orderDetails.setPaymentCharges("");
	                            orderDetails.setPaymentTermDate(null);  // Assuming no payment date
	                            orderDetails.setWarranty(newDetails.getWarranty());
	                            orderDetails.setPanAndGst(newDetails.getPanGst());
	                            orderDetails.setDemoDate(null);
	                            orderDetails.setDeliveryAddress(newDetails.getDeliveryAddress());
	                            orderDetails.setDeliveryPinCode(newDetails.getDeliveryPin());
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
	                            orderDetails.setRegisterStatus(newDetails.getRegisterStatus());
	                            orderDetails.setCreatedDate(newDetails.getCreatedDate());
	                            orderDetails.setCreatedBy(newDetails.getCreatedBy());
	                            orderDetailsRepository.save(orderDetails);
                        	}     	                            
                            
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }

                } else {
                    System.out.println("New record - Creating a new estimation");
                    newDetails.setId(id);  // Ensure ID is set
                    newDetails.setEstNO(mobileEstimationDetailsRepository.generateEstimationSequence());
                    mobileEstimationDetailsRepository.save(newDetails);
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
    
    @RequestMapping(value = "/fsm/getMobileEstimationDetailsList", method = RequestMethod.POST, consumes="application/json")
	public String getMobileEstimationDetailsList(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/getMobileEstimationDetailsList:::::::");
			JSONObject jObj = new JSONObject(payload);
			String userId = jObj.getString("userId");
			System.out.println(userId);
			String teamMemberIds = userDetailsRepository.getUserBasedTeamMemberUserIds(userId);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			ObjectMapper mapper = new ObjectMapper();
			List<MobileEstimationDetails> detailsList = mobileEstimationDetailsRepository.getFSMUserIdsBasedEstimationDetailsList(teamMemberIds);
			mapper.setDateFormat(sdf);
			String sourceString = mapper.writeValueAsString(detailsList);
			vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
			System.out.println(sourceString);
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
    
    @RequestMapping(value = "/fsm/editMobileEstimationDetails", method = RequestMethod.POST, consumes="application/json")
	public String editEstimationDetails(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/editMobileEstimationDetails::::::::::::::::"+payload);			
			JSONObject jObj = new JSONObject(payload);
			String idString = jObj.getString("ID");	
			UUID id = UUID.fromString(idString);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Optional<MobileEstimationDetails> existingDetailsRecord = mobileEstimationDetailsRepository.findById(id);
			if(existingDetailsRecord.isPresent()) {
				MobileEstimationDetails estDetails = existingDetailsRecord.get();
				ObjectMapper mapper = new ObjectMapper();
				mapper.setDateFormat(sdf);
				String sourceString = mapper.writeValueAsString(estDetails);
				
				List<MobileEstimationProductDetails> estProductDetailsList = mobileEstimationProductDetailsRepository.findByReferenceId(id.toString());
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
    
    static class ProductDetailsWrapper {
        public List<MobileEstimationProductDetails> productDetails;
    }

    @RequestMapping(value = "/fsm/updateMobileEstimationProductsDetails", method = RequestMethod.POST, consumes = "application/json")
    public String updateMobileEstimationProductsDetails(@RequestBody String payload) {
        String vResponse = "{\"status\":\"false\"}";
        try {
            System.out.println("/fsm/updateMobileEstimationProductsDetails:::::::" + payload);

            ObjectMapper mapper = new ObjectMapper();
            // Use the static wrapper class to deserialize the productDetails
            ProductDetailsWrapper productWrapper = mapper.readValue(payload, ProductDetailsWrapper.class);
            List<MobileEstimationProductDetails> newDetailsList = productWrapper.productDetails;

            for (MobileEstimationProductDetails newDetails : newDetailsList) {
                UUID id = newDetails.getId();
                if (id != null) {
                    Optional<MobileEstimationProductDetails> existingRecord = mobileEstimationProductDetailsRepository.findById(id);
                    if (existingRecord.isPresent()) {
                        System.out.println("existingRecord");
                        MobileEstimationProductDetails estProductDetails = existingRecord.get();
                        estProductDetails.setSerialNo(newDetails.getSerialNo());
                        estProductDetails.setProduct(newDetails.getProduct());
                        estProductDetails.setQty(newDetails.getQty());
                        estProductDetails.setReferenceId(newDetails.getReferenceId());
                        estProductDetails.setTax(newDetails.getTax());
                        estProductDetails.setUnitPrice(newDetails.getUnitPrice());
                        estProductDetails.setTotal(newDetails.getTotal());
                        mobileEstimationProductDetailsRepository.save(estProductDetails);
                    }
                } else {
                    System.out.println("newRecord");
                    newDetails.setId(UUID.randomUUID());
                    mobileEstimationProductDetailsRepository.save(newDetails);
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
    
    @RequestMapping(value = "/fsm/getMobileDarDetailsList", method = RequestMethod.POST, consumes="application/json")
	public String getMobileDarDetailsList(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/getMobileDarDetailsList:::::::");
			JSONObject jObj = new JSONObject(payload);
			String userId = jObj.getString("userId");
			System.out.println(userId);
			String teamMemberIds = userDetailsRepository.getUserBasedTeamMemberUserIds(userId);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			ObjectMapper mapper = new ObjectMapper();
			List<MobileDarDetails> detailsList = mobileDarDetailsRepository.getFSMUserIdsBasedDarDetailsList(teamMemberIds);
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
	@RequestMapping(value = "/fsm/updateMobileDarDetails", method = RequestMethod.POST, consumes = "application/json")
	public String updateMobileDarDetails(@RequestBody String payload) {
	    String vResponse = "{\"status\":\"false\"}";
	    try {
	        System.out.println("/fsm/updateMobileDarDetails:::::::" + payload);	        
	        ObjectMapper mapper = new ObjectMapper();
	        MobileDarDetails newDetails = mapper.readValue(payload, MobileDarDetails.class);	        
	        UUID id = newDetails.getId();	        
	        if (!newDetails.getDarNO().equalsIgnoreCase("")) {	            
	            Optional<MobileDarDetails> existingRecord = mobileDarDetailsRepository.findById(id);
	            if (existingRecord.isPresent()) {	  
	            	System.out.println("existingRecord");
	            	MobileDarDetails darDetails = existingRecord.get();
	            	darDetails.setDarNO(newDetails.getDarNO());
	            	darDetails.setDarProcessDate(newDetails.getDarProcessDate());
	            	darDetails.setPlannedActivity(newDetails.getPlannedActivity());
	            	darDetails.setHospitalNameAndAddress(newDetails.getHospitalNameAndAddress());
	            	darDetails.setStateCumArea(newDetails.getStateCumArea());
	            	darDetails.setDoctorName(newDetails.getDoctorName());
	            	darDetails.setContactNumber(newDetails.getContactNumber());
	            	darDetails.setAboutDoctor(newDetails.getAboutDoctor());
	            	darDetails.setProductDetails(newDetails.getProductDetails());
	            	darDetails.setFromLocation(newDetails.getFromLocation());
	            	darDetails.setToLocation(newDetails.getToLocation());
	            	darDetails.setTotalAmount(newDetails.getTotalAmount());
	            	darDetails.setRemarks(newDetails.getRemarks());
	            	darDetails.setStatusToVisit(newDetails.getStatusToVisit());
	            	darDetails.setCreatedDate(newDetails.getCreatedDate());
	            	darDetails.setCreatedBy(newDetails.getCreatedBy());	            	
	            	mobileDarDetailsRepository.save(darDetails);
	                vResponse = "{\"status\":\"true\"}";
	            }
	        } else {
	        	System.out.println("newRecord");
	        	newDetails.setId(id);
	        	newDetails.setDarNO(mobileDarDetailsRepository.generateDarSequence());
	        	mobileDarDetailsRepository.save(newDetails);
	            vResponse = "{\"status\":\"true\"}";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
	    }
	    System.out.println("VResponse :::" + vResponse);
	    return vResponse;
	}
	
	@PostMapping("/fsm/updateMobileDarExpensesDetails")
    public ResponseEntity<Map<String, String>> uploadExpenses(
            @RequestParam("expenses") String expensesJson,
            @RequestParam("imageFiles") List<MultipartFile> imageFiles) { 
	 String vResponse =  "{\"status\":\"false\"}";
	 Map<String, String> response = new HashMap<>();
        try {
        	System.out.println("/fsm/updateMobileDarExpensesDetails>>>>::::"+expensesJson);
            ObjectMapper objectMapper = new ObjectMapper();
            List<MobileDarExpensesDetails> expensesList = objectMapper.readValue(expensesJson, 
                new TypeReference<List<MobileDarExpensesDetails>>() {});
            System.out.println("expensesList>>>>::::"+expensesList.size());
            for (int i = 0; i < expensesList.size(); i++) {
                MobileDarExpensesDetails expense = expensesList.get(i);
                MultipartFile imageFile = imageFiles.get(i);
                System.out.println("/file>>>>::::::::::"+imageFiles.get(i));
                if (imageFile != null && !imageFile.isEmpty()) {	                   
                    String fileName = imageFile.getOriginalFilename();	                   
                    File file = new File(UPLOAD_DIR, fileName);
                    imageFile.transferTo(file);

                    expense.setImageFilePath(fileName);
                    
                }
                if(expense.getId() != null) {
                	Optional<MobileDarExpensesDetails> expenseDetailsRecord = mobileDarExpensesDetailsRepository.findById(expense.getId());
	                if(expenseDetailsRecord.isPresent()) {
	                	MobileDarExpensesDetails expenseDetails = expenseDetailsRecord.get();
	                	expenseDetails.setExpensesAmount(expense.getExpensesAmount());
		            	expenseDetails.setExpensesDescription(expense.getExpensesDescription());	            
		            	expenseDetails.setReferenceId(expense.getReferenceId());
		            	mobileDarExpensesDetailsRepository.save(expense);
		            }
                }else {
	            	expense.setId(UUID.randomUUID());		            
		            mobileDarExpensesDetailsRepository.save(expense);
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
	
	@RequestMapping(value = "/fsm/getMobileProductDetailsList", method = RequestMethod.POST, consumes="application/json")
	public String getMobileProductDetailsList(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/getMobileProductDetailsList:::::::");
			JSONObject jObj = new JSONObject(payload);
			String id = jObj.getString("id");
			System.out.println(id);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			ObjectMapper mapper = new ObjectMapper();
			List<MobileProductDetails> productList = mobileProductDetailsRepository.findAll();
			mapper.setDateFormat(sdf);
			String sourceString = mapper.writeValueAsString(productList);
			vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
			System.out.println(sourceString);
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
	
	@RequestMapping(value = "/fsm/getMobileCategoryDetailsList", method = RequestMethod.POST, consumes="application/json")
	public String getMobileCategoryDetailsList(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/getMobileCategoryDetailsList:::::::");
			JSONObject jObj = new JSONObject(payload);
			String id = jObj.getString("id");
			System.out.println(id);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			ObjectMapper mapper = new ObjectMapper();
			List<MobileCategoryDetails> propertiesList = mobileCategoryDetailsRepository.findAll();
			mapper.setDateFormat(sdf);
			String sourceString = mapper.writeValueAsString(propertiesList);
			vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
			System.out.println(sourceString);
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
    
	@RequestMapping(value = "/fsm/getProductDetailsBasedOnCategoryInMobile", method = RequestMethod.POST, consumes="application/json")
	public String getProductDetailsBasedOnCategoryInMobile(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/getProductDetailsBasedOnProductIdInMobile:::::::"+payload);
			JSONObject jObj = new JSONObject(payload);
			String categoryName = jObj.getString("categoryName");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			ObjectMapper mapper = new ObjectMapper();
			List<MobileProductDetails> productList= mobileProductDetailsRepository.findByCategory(categoryName);
			mapper.setDateFormat(sdf);
			String sourceString = mapper.writeValueAsString(productList);
			vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
			System.out.println(sourceString);		
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}

	@RequestMapping(value = "/fsm/getMobileProfileUsersDetails", method = RequestMethod.POST, consumes="application/json")
	public String getMobileProfileUsersDetails(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/getMobileProfileUsersDetails::::::::::"+payload);
			JSONObject jObj = new JSONObject(payload);
			String userId = jObj.getString("userId");			
			ObjectMapper mapper = new ObjectMapper();
			Optional<MobileUserDetails> userDetailsRecord = mobileUserDetailsRepository.findByUserId(userId);
			if(userDetailsRecord.isPresent()) {
				MobileUserDetails userDetails = userDetailsRecord.get();
				String sourceString = mapper.writeValueAsString(userDetails);					
				List<MobileUserDetails> userBasedTeamMemberList = mobileUserDetailsRepository.findByLeadUserId(userDetails.getUserId());
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
	
	/******************************************************update with file****************************************************/
	@SuppressWarnings("null")
	@PostMapping("/fsm/updateMobileUsersDetails")
	public ResponseEntity<?> updateUsersDetails(
	        @RequestParam(value = "file", required = false) MultipartFile file,
	        @RequestParam("id") String idString,
	        @RequestParam("userId") String userId,
	        @RequestParam("userName") String userName,
	        @RequestParam("password") String password,
	        @RequestParam("emailId") String emailId,
	        @RequestParam("mobileNo") String mobileNo) {
		
		String vResponse =  "{\"status\":\"false\"}";
		System.out.println("/fsm/updateMobileUsersDetails::::::::::"+idString+"<<<>>>"+userId+"<<>>"+password+"<<<>>>"+emailId);
		
		Map<String, String> response = new HashMap<>();
	    try {
	        

	        MobileUserDetails user = null;
	        if (!idString.equalsIgnoreCase("")) {
	        	UUID id = UUID.fromString(idString);
	            Optional<MobileUserDetails> userDetailsRecord = mobileUserDetailsRepository.findById(id);
	            if (userDetailsRecord.isPresent()) {
	                user = userDetailsRecord.get();
	                user.setUserId(userId);
	    	        user.setUserName(userName);
	    	        user.setPassword(password);
	    	        user.setEmailId(emailId);
	    	        user.setMobileNo(mobileNo);
	    	          

	            }
	        } else {
	            user = new MobileUserDetails(); 
	            user.setId(UUID.randomUUID());		            
	        }

	        
	        if (file != null && !file.isEmpty()) {
	            String fileName = userId + "_" + file.getOriginalFilename(); 		           
	            Path filePath = Paths.get(UPLOAD_DIR + fileName);

	            Files.createDirectories(filePath.getParent()); 
	            Files.write(filePath, file.getBytes()); 

	            user.setFileName(fileName);
	        }

	        mobileUserDetailsRepository.save(user);

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
	
	@RequestMapping(value = "/fsm/convertMobileEstimationToOrder", method = RequestMethod.POST, consumes = "application/json")
    public String convertMobileEstimationToOrder(@RequestBody String payload) {
        String vResponse = "{\"status\":\"false\"}";
        try {
            System.out.println("/fsm/convertMobileEstimationToOrder:::::::" + payload);	        
               ObjectMapper mapper = new ObjectMapper();
               JSONObject jObj = new JSONObject(payload);
    	       String estimationIdString  	= jObj.getString("id");
    	       String estimationStatus = jObj.getString("estimationStatus");
    	       String createdBy = jObj.getString("createdBy");
    	       String createdDateString = jObj.getString("createdDate");    	      
    	       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
               Date parsedDate = sdf.parse(createdDateString);
               Timestamp createdDate = new Timestamp(parsedDate.getTime());
    	       UUID estimationId = UUID.fromString(estimationIdString);
             
               Optional<MobileEstimationDetails> existingRecord = mobileEstimationDetailsRepository.findById(estimationId);	                
               if (existingRecord.isPresent()) {	                    
                
	                MobileEstimationDetails newDetails = existingRecord.get();   
	                newDetails.setRegisterStatus(estimationStatus);
	                mobileEstimationDetailsRepository.save(newDetails);
	                if (estimationStatus.equalsIgnoreCase("Convert To Order")) {
	                		boolean orderProductConvertStatus = false;
	                		String sourceString = "";
	                    	Optional<MobileOrderDetails> existingOrderRecord = mobileOrderDetailsRepository.findByEstNo(newDetails.getEstNO());
	                    	if(existingOrderRecord.isPresent()) {
	                    		System.out.println("existingRecord found");
	                    		MobileOrderDetails orderDetails = existingOrderRecord.get();
	                    				/*orderDetails.seteNo("");  // Set fields according to your logic
	                                    orderDetails.setEstNo(newDetails.getEstNO());
	                                    orderDetails.setOrderNo(mobileOrderDetailsRepository.generateOrderSequence());
	                                    orderDetails.setSoNo("");
	                                    orderDetails.setDdNo("");
	                                    orderDetails.setCustomerName(newDetails.getCustomerName());
	                                    orderDetails.setOrderProcessDate(new Timestamp(System.currentTimeMillis())); // Proper timestamp
	                                    orderDetails.setRepCode(newDetails.getRepAttd());
	                                    orderDetails.setBillingName("");  // Set appropriate values or keep blank
	                                    orderDetails.setBillingAddress(newDetails.getBillingAddress());
	                                    orderDetails.setCustomerCity(newDetails.getBillingCity());
	                                    orderDetails.setCustomerPinCode(newDetails.getBillingPin());
	                                    orderDetails.setCustomerPhone(newDetails.getPhone());
	                                    orderDetails.setCustomerEmail(newDetails.getEmail());
	                                    orderDetails.setDeliveryCity(newDetails.getDeliveryCity());
	                                    orderDetails.setDemoPlan("");
	                                    orderDetails.setPaymentCharges("");
	                                    orderDetails.setPaymentTermDate(null);  // Assuming no payment date
	                                    orderDetails.setWarranty(newDetails.getWarranty());
	                                    orderDetails.setPanAndGst(newDetails.getPanGst());
	                                    orderDetails.setDemoDate(null);
	                                    orderDetails.setDeliveryAddress(newDetails.getDeliveryAddress());
	                                    orderDetails.setDeliveryPinCode(newDetails.getDeliveryPin());
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
	                                    orderDetails.setCreatedBy(createdBy);*/
	                    				
			                    		
		                                orderDetails.setOrderProcessDate(new Timestamp(System.currentTimeMillis())); 
		                                mobileOrderDetailsRepository.save(orderDetails);
	                                    
	                                    sourceString =  mapper.writeValueAsString(orderDetails);
	                                    //orderProductConvertStatus = convertToOrderStatusUpdateProductsDetailsInMobile(estimationIdString,newDetails.getId().toString());
	                                    orderProductConvertStatus = true;
	                                    if(orderProductConvertStatus) {
	                                    	List<MobileOrderProductDetails> ordProductDetailsList = mobileOrderProductDetailsRepository.findByReferenceId(orderDetails.getId().toString());
	                    					String estProductSourceString = mapper.writeValueAsString(ordProductDetailsList);				
	                    					vResponse =  "{\"status\":\"true\",\"data\":"+sourceString+",\"ordProductData\":"+estProductSourceString+"}";
	                                	}else {
	                                		vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
	                                	}
	                    	}else {
	                    				System.out.println("new Record found");
	                                    MobileOrderDetails orderDetails = new MobileOrderDetails();
	                                    UUID orderId = UUID.randomUUID();
	                                    orderDetails.setId(orderId);
	                                    orderDetails.seteNo("");  // Set fields according to your logic
	                                    orderDetails.setEstNo(newDetails.getEstNO());
	                                    orderDetails.setOrderNo(mobileOrderDetailsRepository.generateOrderSequence());
	                                    orderDetails.setSoNo("");
	                                    orderDetails.setDdNo("");
	                                    orderDetails.setCustomerName(newDetails.getCustomerName());
	                                    orderDetails.setOrderProcessDate(new Timestamp(System.currentTimeMillis())); // Proper timestamp
	                                    orderDetails.setRepCode(newDetails.getRepAttd());
	                                    orderDetails.setBillingName("");  // Set appropriate values or keep blank
	                                    orderDetails.setBillingAddress(newDetails.getBillingAddress());
	                                    orderDetails.setCustomerCity(newDetails.getBillingCity());
	                                    orderDetails.setCustomerPinCode(newDetails.getBillingPin());
	                                    orderDetails.setCustomerPhone(newDetails.getPhone());
	                                    orderDetails.setCustomerEmail(newDetails.getEmail());
	                                    orderDetails.setDeliveryCity(newDetails.getDeliveryCity());
	                                    orderDetails.setDemoPlan("");
	                                    orderDetails.setPaymentCharges("");
	                                    orderDetails.setPaymentTermDate(null);  // Assuming no payment date
	                                    orderDetails.setWarranty(newDetails.getWarranty());
	                                    orderDetails.setPanAndGst(newDetails.getPanGst());
	                                    orderDetails.setDemoDate(null);
	                                    orderDetails.setDeliveryAddress(newDetails.getDeliveryAddress());
	                                    orderDetails.setDeliveryPinCode(newDetails.getDeliveryPin());
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
	                                    orderDetails.setPaymentMode("");
	                                    mobileOrderDetailsRepository.save(orderDetails);
	                                    						
	                                    sourceString =  mapper.writeValueAsString(orderDetails);
	                                    orderProductConvertStatus = convertToOrderStatusUpdateProductsDetailsInMobile(estimationIdString,orderId.toString());
	                                    
	                                    if(orderProductConvertStatus) {
	                                    	List<MobileOrderProductDetails> ordProductDetailsList = mobileOrderProductDetailsRepository.findByReferenceId(orderId.toString());
	                    					String estProductSourceString = mapper.writeValueAsString(ordProductDetailsList);				
	                    					vResponse =  "{\"status\":\"true\",\"data\":"+sourceString+",\"ordProductData\":"+estProductSourceString+"}";
	                                	}else {
	                                		vResponse = "{\"status\":\"false\", \"message\":\"An error occurred.\"}";
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
		
	public boolean convertToOrderStatusUpdateProductsDetailsInMobile(String estimationIdString,String orderIdString) {
	    boolean vResponse = false;
	    try {
	        System.out.println("Normal Method ConvertToOrderStatusUpdateProductsDetails:::::::");
	        
	        List<MobileEstimationProductDetails> estProductDetailsList = mobileEstimationProductDetailsRepository.findByReferenceId(estimationIdString);
	        List<MobileOrderProductDetails> oldOrderProductDetailsList = mobileOrderProductDetailsRepository.findByReferenceId(orderIdString);   
	        if(oldOrderProductDetailsList.size() > 0) {
	        	mobileOrderProductDetailsRepository.deleteAll(oldOrderProductDetailsList);
	        }
	        List<MobileOrderProductDetails> orderProductDetailsArrayList = new ArrayList<MobileOrderProductDetails>(); 
	        for(MobileEstimationProductDetails estProductDetails: estProductDetailsList) {
        		MobileOrderProductDetails ordProducts = new MobileOrderProductDetails();
        		ordProducts.setId(UUID.randomUUID());
        		ordProducts.setReferenceId(orderIdString);
        		ordProducts.setProductType("");	        		
        		ordProducts.setProduct(estProductDetails.getProduct());
        		ordProducts.setProductCode(estProductDetails.getSerialNo());
        		ordProducts.setQty(estProductDetails.getQty());	        		
        		ordProducts.setTax(estProductDetails.getTax());
        		ordProducts.setUnitPrice(estProductDetails.getUnitPrice());
        		ordProducts.setTotal(estProductDetails.getTotal());
        		orderProductDetailsArrayList.add(ordProducts);		            	
		    }
	        mobileOrderProductDetailsRepository.saveAll(orderProductDetailsArrayList);  
	        vResponse = true;        
	    } catch (Exception e) {
	    	vResponse = false;
	        e.printStackTrace();	       
	    }
	    System.out.println("VResponse :::" + vResponse);
	    return vResponse;
	}

	@RequestMapping(value = "/fsm/getMobileOrderDetailsList", method = RequestMethod.POST, consumes="application/json")
	public String getMobileOrderDetailsList(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/getMobileOrderDetailsList:::::::");
			JSONObject jObj = new JSONObject(payload);
			String userId = jObj.getString("userId");				
			String teamMemberIds = mobileUserDetailsRepository.getUserBasedTeamMemberUserIds(userId);
			System.out.println(teamMemberIds);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			ObjectMapper mapper = new ObjectMapper();
			List<MobileOrderDetails> detailsList = mobileOrderDetailsRepository.getFSMUserIdsBasedOrderDetailsList(teamMemberIds);
			mapper.setDateFormat(sdf);
			String sourceString = mapper.writeValueAsString(detailsList);
			vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
			System.out.println(sourceString);
		}catch(Exception e4) {
			e4.printStackTrace();
		}
	    return vResponse;
	}
	
	private Timestamp parseTimestamp(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        try {
            Instant instant = Instant.parse(dateStr);
            return Timestamp.from(instant);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null; // or handle exception as needed
        }
    }

    @RequestMapping(value = "/fsm/updateMobileOrderDetails", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Map<String, String>> updateOrderDetails(@RequestBody String payload) {
        Map<String, String> response = new HashMap<>();

        try {
            System.out.println("/fsm/updateMobileOrderDetails:::::::" + payload);        
            JSONObject jObj = new JSONObject(payload);
            String idString = jObj.getString("id");            
            UUID id = UUID.fromString(idString);

            // Extract JSON fields
            String orderProductDate = jObj.optString("orderProductDate", null);
            String paymentInCharge = jObj.getString("paymentInCharge");
            String paymentTermsDate = jObj.optString("paymentTermsDate", null);
            String warranty = jObj.getString("warranty");
            String paymentMode = jObj.getString("paymentMethod");
            String panGst = jObj.getString("panGst");
            String demoPlan = jObj.getString("demoPlan");
            String demoDate = jObj.optString("demoDate", null);
            String expectedDate = jObj.optString("expectedDate", null);
            String shipModeName = jObj.optString("shipModeName", null);
            String gst = jObj.optString("gst", null);
            String deliveryCharges = jObj.getString("deliveryCharges");
            String subtotal = jObj.optString("subtotal", null);
            String lessAdvance = jObj.getString("lessAdvance");
            String balance = jObj.getString("balance");

            // Find existing record
            Optional<MobileOrderDetails> existingRecord = mobileOrderDetailsRepository.findById(id);
            if (existingRecord.isPresent()) {                   
                MobileOrderDetails orderDetails = existingRecord.get();

                // Update fields in orderDetails
                orderDetails.setOrderProcessDate(parseTimestamp(orderProductDate));
                orderDetails.setPaymentCharges(paymentInCharge);
                orderDetails.setPaymentTermDate(parseTimestamp(paymentTermsDate));
                orderDetails.setWarranty(warranty);
                orderDetails.setPaymentMode(paymentMode);
                orderDetails.setPanAndGst(panGst);
                orderDetails.setDemoPlan(demoPlan);
                orderDetails.setDemoDate(parseTimestamp(demoDate));
                orderDetails.setExpectedDate(parseTimestamp(expectedDate));
                orderDetails.setShipModeName(shipModeName);
                orderDetails.setGst(gst);
                orderDetails.setDeliveryCharges(deliveryCharges);
                orderDetails.setTotalProductAmount(subtotal);
                orderDetails.setLessAdvance(lessAdvance);
                orderDetails.setBalance(balance);

                // Save updated details
                mobileOrderDetailsRepository.save(orderDetails);

                response.put("status", "true");
                response.put("message", "Order details updated successfully.");
            } else {
                response.put("status", "false");
                response.put("message", "Order not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "false");
            response.put("message", "An error occurred: " + e.getMessage());
        }

        System.out.println("Response :::" + response);
        return ResponseEntity.ok(response);
    }
    
    
    static class OrderProductDetailsWrapper {
	    public List<MobileOrderProductDetails> productDetails;
	}

	/******************************************************update****************************************************/
	@RequestMapping(value = "/fsm/updateMobileOrderProductsDetails", method = RequestMethod.POST, consumes = "application/json")
	public String updateMobileOrderProductsDetails(@RequestBody String payload) {
	    String vResponse = "{\"status\":\"false\"}";
	    try {
	        System.out.println("/fsm/updateMobileOrderProductsDetails:::::::" + payload);

	        ObjectMapper mapper = new ObjectMapper();
	       
	        OrderProductDetailsWrapper productWrapper = mapper.readValue(payload, OrderProductDetailsWrapper.class);
	        List<MobileOrderProductDetails> newDetailsList = productWrapper.productDetails;

	        for (MobileOrderProductDetails newDetails : newDetailsList) {
	            UUID id = newDetails.getId();
	            if (id != null) {
	                Optional<MobileOrderProductDetails> existingRecord = mobileOrderProductDetailsRepository.findById(id);
	                if (existingRecord.isPresent()) {
	                    System.out.println("existingRecord");
	                    MobileOrderProductDetails ordProductDetails = existingRecord.get();
	                    ordProductDetails.setProductCode(newDetails.getProductCode());
	                    ordProductDetails.setProductType(newDetails.getProductType());
	                    ordProductDetails.setProduct(newDetails.getProduct());
	                    ordProductDetails.setQty(newDetails.getQty());
	                    ordProductDetails.setReferenceId(newDetails.getReferenceId());
	                    ordProductDetails.setTax(newDetails.getTax());
	                    ordProductDetails.setUnitPrice(newDetails.getUnitPrice());
	                    ordProductDetails.setTotal(newDetails.getTotal());
	                    mobileOrderProductDetailsRepository.save(ordProductDetails);
	                }
	            } else {
	                System.out.println("newRecord");
	                newDetails.setId(UUID.randomUUID());
	                mobileOrderProductDetailsRepository.save(newDetails);
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
	
	@RequestMapping(value = "/fsm/deleteMobileOrderProductsDetails", method = RequestMethod.POST, consumes="application/json")
	public String deleteMobileOrderProductsDetails(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/deleteMobileOrderProductsDetails::::::::::"+payload);
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jObj = new JSONObject(payload);
			String idString = jObj.getString("orderProductId");
			String orderIdString = jObj.getString("orderId");
			System.out.println("idString ::"+idString);
			UUID id = UUID.fromString(idString);			
			Optional<MobileOrderProductDetails> ordProductDetailsRecord = mobileOrderProductDetailsRepository.findById(id);			
			if(ordProductDetailsRecord.isPresent()){
				System.out.println("its present.....");
				MobileOrderProductDetails ordProductDetails = ordProductDetailsRecord.get();
				mobileOrderProductDetailsRepository.delete(ordProductDetails);
				
				int totalProductAmount= mobileOrderProductDetailsRepository.findByReferenceIdAndResetTotalProduct(orderIdString);
				System.out.println("totalProductAmount ::"+totalProductAmount);
				UUID orderId = UUID.fromString(orderIdString);
				Optional<MobileOrderDetails> existingDetailsRecord = mobileOrderDetailsRepository.findById(orderId);			
				if(existingDetailsRecord.isPresent()){					
					MobileOrderDetails ordDetails = existingDetailsRecord.get();
					System.out.println("delete Reset Again");
					ordDetails.setTotalProductAmount(totalProductAmount+"");
					int finalTotalOrderAmount = totalProductAmount+Integer.parseInt(ordDetails.getDeliveryCharges())+Integer.parseInt(ordDetails.getGst());
					ordDetails.setTotalAmount(finalTotalOrderAmount+"");
					mobileOrderDetailsRepository.save(ordDetails);
					
					String sourceString = mapper.writeValueAsString(ordDetails);
					System.out.println("sourceString  :::"+sourceString);
				}
				vResponse =  "{\"status\":\"true\"}";
			}
			
		}catch(Exception e4) {
			e4.printStackTrace();			
		}
		System.out.println("vResponse::::"+vResponse);
	    return vResponse;
	}
	
	
	/******************************************************Get by name****************************************************/
	@RequestMapping(value = "/fsm/getMobileDefaultPropertyValuesByName", method = RequestMethod.POST, consumes="application/json")
	public String getMobileDefaultPropertyValuesByName(@RequestBody String payload) {
		String vResponse =  "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/getMobileDefaultPropertyValuesByName::::::::::"+payload);
			JSONObject jObj = new JSONObject(payload);
			String propertyName = jObj.getString("propertyName");
			System.out.println(propertyName);			
			ObjectMapper mapper = new ObjectMapper();
			Optional<MobileDefaultPropertiesDetails> propertyDetailsRecord = mobileDefaultPropertiesRepository.findByPropertyName(propertyName);
			if(propertyDetailsRecord.isPresent()) {
				MobileDefaultPropertiesDetails propertyDetails = propertyDetailsRecord.get();
				String sourceString = mapper.writeValueAsString(propertyDetails);
				vResponse = "{\"status\":\"true\",\"data\":"+sourceString+"}";
				System.out.println(sourceString);
			}			
		}catch(Exception e4) {
			e4.printStackTrace();			
		}
	    return vResponse;
	}	
	

	@RequestMapping(value = "/fsm/mobileUpdateEstimationDetailsInTableRow", method = RequestMethod.POST, consumes = "application/json")
	public String mobileUpdateEstimationDetailsInTableRow(@RequestBody String payload) {
		String vResponse = "{\"status\":\"false\"}";
		try {
			System.out.println("/fsm/mobileUpdateEstimationDetailsInTableRow:::::::" + payload);

			ObjectMapper mapper = new ObjectMapper();
			JSONObject jObj = new JSONObject(payload);
			String estimationIdString = jObj.getString("id");
			String estimationStatus = jObj.getString("estimationStatus");
			String createdDateString = jObj.getString("createdDate");
			String createdBy = jObj.getString("createdBy");

			Instant instant = Instant.parse(createdDateString);
			Timestamp createdDate = Timestamp.from(instant);
			UUID estimationId = UUID.fromString(estimationIdString);

			Optional<MobileEstimationDetails> existingRecord = mobileEstimationDetailsRepository.findById(estimationId);
			if (existingRecord.isPresent()) {
				System.out.println("Existing Estimation Record Found");
				MobileEstimationDetails newDetails = existingRecord.get();
				newDetails.setRegisterStatus(estimationStatus);
				newDetails.setCreatedDate(createdDate);
				newDetails.setCreatedBy(createdBy);
				mobileEstimationDetailsRepository.save(newDetails);
				vResponse = "{\"status\":\"true\"}";
				if (estimationStatus.equalsIgnoreCase("Convert To Order")) {
					String sourceString;
					Optional<MobileOrderDetails> existingOrderRecord = mobileOrderDetailsRepository.findByEstNo(newDetails.getEstNO());
					if (existingOrderRecord.isPresent()) {
						System.out.println("existing Order Record found");
						mobileUpdateOrderDetailsInTableRow(existingOrderRecord.get(), newDetails, createdDate, createdBy);
						vResponse = "{\"status\":\"true\"}";

					} else {
						System.out.println("new Order Record Created");
						MobileOrderDetails orderDetails = new MobileOrderDetails();
						UUID orderId = UUID.randomUUID();
						orderDetails.setId(orderId);
						mobileUpdateOrderDetailsInTableRow(orderDetails, newDetails, createdDate, createdBy);
						mobileOrderDetailsRepository.save(orderDetails);
						boolean orderProductConvertStatus = mobileConvertToOrderStatusUpdateProductsDetailsInTableRow(estimationIdString, orderId.toString());
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

	private MobileOrderDetails mobileUpdateOrderDetailsInTableRow(MobileOrderDetails orderDetails, MobileEstimationDetails newDetails, Timestamp createdDate, String createdBy) {
		orderDetails.seteNo("");
		orderDetails.setEstNo(newDetails.getEstNO());
		orderDetails.setOrderNo(mobileOrderDetailsRepository.generateOrderSequence());
		orderDetails.setSoNo("");
		orderDetails.setDdNo("");
		orderDetails.setCustomerName(newDetails.getCustomerName());
		orderDetails.setOrderProcessDate(new Timestamp(System.currentTimeMillis()));
		orderDetails.setRepCode(newDetails.getRepAttd());
		orderDetails.setBillingName("");
		orderDetails.setBillingAddress(newDetails.getBillingAddress());
		orderDetails.setCustomerCity(newDetails.getBillingCity());
		orderDetails.setCustomerPinCode(newDetails.getBillingPin());
		orderDetails.setCustomerPhone(newDetails.getPhone());
		orderDetails.setCustomerEmail(newDetails.getEmail());
		orderDetails.setDeliveryCity(newDetails.getDeliveryCity());
		orderDetails.setDemoPlan("");
		orderDetails.setPaymentCharges("");
		orderDetails.setPaymentTermDate(null);
		orderDetails.setWarranty(newDetails.getWarranty());
		orderDetails.setPanAndGst(newDetails.getPanGst());
		orderDetails.setDemoDate(null);
		orderDetails.setDeliveryAddress(newDetails.getDeliveryAddress());
		orderDetails.setDeliveryPinCode(newDetails.getDeliveryPin());
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
		return mobileOrderDetailsRepository.save(orderDetails);
	}


	public boolean mobileConvertToOrderStatusUpdateProductsDetailsInTableRow(String estimationIdString, String orderIdString) {
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
	
}
