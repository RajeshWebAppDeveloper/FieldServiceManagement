package com.sy.fsm.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sy.fsm.Model.DarDetails;
import com.sy.fsm.Model.EstimationDetails;
import com.sy.fsm.Model.MobileEstimationDetails;
import com.sy.fsm.Model.MobileOrderDetails;
import com.sy.fsm.Model.PaymentDetails;
import com.sy.fsm.Repository.DarDetailsRepository;
import com.sy.fsm.Repository.EstimationDetailsRepository;
import com.sy.fsm.Repository.MobileEstimationDetailsRepository;
import com.sy.fsm.Repository.MobileOrderDetailsRepository;
import com.sy.fsm.Repository.PaymentDetailsRepository;
import com.sy.fsm.RestController.MobileRestController.OrderProductDetailsWrapper;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.Timestamp;

@org.springframework.web.bind.annotation.RestController
public class JasperReportRestController {
	
	@Autowired
	private DBController dbController;
	
	@Value("${upload.dir}")
    private String UPLOAD_DIR;
	
	@Autowired
	EstimationDetailsRepository estimationDetailsRepository;
	
	@Autowired
	DarDetailsRepository darDetailsRepository;
	
	@Autowired
	MobileEstimationDetailsRepository mobileEstimationDetailsRepository;
	
	@Autowired
	MobileOrderDetailsRepository mobileOrderDetailsRepository;
	
	@Autowired
	PaymentDetailsRepository paymentDetailsRepository;
	
		
	/*private Timestamp parseTimestamp(String dateStr) {
	    if (dateStr == null || dateStr.isEmpty()) return null;
	    try {
	        return Timestamp.valueOf(dateStr);
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	        return null;
	    }
	}*/
	
	@GetMapping("/fsm/exportFiles/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            // Construct the file path
            Path filePath = Paths.get(UPLOAD_DIR + "/Reports/").resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(filePath);
            contentType = contentType != null ? contentType : "application/octet-stream";

            return ResponseEntity.ok()
                    .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

	
	private Timestamp parseTimestamp(String dateStr) {
	    if (dateStr == null || dateStr.isEmpty()) {
	        return null;
	    }
	    try {
	        return Timestamp.valueOf(dateStr);
	    } catch (IllegalArgumentException e) {
	        return null; // or handle exception as needed
	    }
	}

	
	@PostMapping("/fsm/exportJasperReportInDar")
	public ResponseEntity<Map<String, String>> exportJasperReportInDar(@RequestBody String payload) {
	    Map<String, String> response = new HashMap<>();
	    try (Connection connection = dbController.getConnection()) {
	        System.out.println("/fsm/exportJasperReportInDar:::::"+payload);
	      
	        JSONObject jObj = new JSONObject(payload);
	        
	        String reportType = jObj.getString("reportType");

	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("darNo", jObj.optString("darNo", ""));
	        parameters.put("darPerformFromDate", parseTimestamp(jObj.optString("darPerformFromDate", null)));
	        parameters.put("darPerformToDate", parseTimestamp(jObj.optString("darPerformToDate", null)));
	        parameters.put("clientName", jObj.optString("clientName", ""));
	        parameters.put("clientMobileNo", jObj.optString("clientMobileNo", ""));
	        parameters.put("statusToVisit", jObj.optString("statusToVisit", ""));
	        parameters.put("createdFromDate", parseTimestamp(jObj.optString("createdFromDate", null)));
	        parameters.put("createdToDate", parseTimestamp(jObj.optString("createdToDate", null)));
	        parameters.put("createdBy", jObj.optString("createdBy", ""));
	        
	        // Main report path
	        String jasperFilePath = "/Reports/Dar_Details.jasper";
	        String settingFileName = "Dar_Report_" + System.currentTimeMillis();

	        // Load the main report
	        InputStream jasperStream = this.getClass().getResourceAsStream(jasperFilePath);
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

	        if (reportType.equalsIgnoreCase("pdf")) {
	            String pdfFilePath = UPLOAD_DIR + "/Reports/" + settingFileName + ".pdf";
	            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFilePath);
	            response.put("status", "true");
	            response.put("message", "PDF Report generated successfully.");
	            response.put("fileName", settingFileName + ".pdf");
	            return ResponseEntity.ok(response);
	        } else if (reportType.equalsIgnoreCase("excel")) {
	            String excelFilePath = UPLOAD_DIR + "/Reports/" + settingFileName + ".xlsx";
	            
	            JRXlsxExporter exporter = new JRXlsxExporter();
	            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(excelFilePath));

	            SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
	            reportConfig.setOnePagePerSheet(false);
	            reportConfig.setRemoveEmptySpaceBetweenRows(true);

	            exporter.setConfiguration(reportConfig);
	            exporter.exportReport();
	            response.put("status", "true");
	            response.put("message", "Excel Report generated successfully.");
	            response.put("fileName", settingFileName + ".xlsx");
	            return ResponseEntity.ok(response);
	        }

	        response.put("status", "false");
	        response.put("error", "Record not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("status", "false");
	        response.put("error", "File upload failed: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	
	@SuppressWarnings("unchecked")
	@PostMapping("/fsm/exportJasperReportInEstimation")
	public ResponseEntity<Map<String, String>> exportJasperReportInEstimation(@RequestBody String payload) {
	    Map<String, String> response = new HashMap<>();
	    try {
	    	System.out.println("/fsm/exportJasperReportInEstimation:::::"+payload);
	        JSONObject jobj = new JSONObject(payload);
	        String reportType = jobj.getString("reportType");
	        String estNo = jobj.getString("estNo");

	        // Default report path
	        //String mainJasperFilePath = "/Reports/estimation_discount.jasper";
	        String mainJasperFilePath = "/Reports/estimation_discount_2.jasper";
	        Optional<MobileEstimationDetails> existingRecord = mobileEstimationDetailsRepository.findByEstNo(estNo);
	        Map<String, Object> parameters = new HashMap<>();

	        if (existingRecord.isPresent()) {
	            MobileEstimationDetails estDetails = existingRecord.get();

	            // Add all parameters from MobileEstimationDetails
	            parameters.put("id", estDetails.getId().toString());
	            parameters.put("estimationProcessDate", estDetails.getEstimationProcessDate().toString());
	            parameters.put("estNO", estDetails.getEstNO());
	            parameters.put("customerName", estDetails.getCustomerName());
	            parameters.put("repAttd", estDetails.getRepAttd());
	            parameters.put("repAcc", estDetails.getRepAcc());
	            parameters.put("billingAddress", estDetails.getBillingAddress());
	            parameters.put("deliveryAddress", estDetails.getDeliveryAddress());
	            parameters.put("billingCity", estDetails.getBillingCity());
	            parameters.put("billingPin", estDetails.getBillingPin());
	            parameters.put("phone", estDetails.getPhone());
	            parameters.put("email", estDetails.getEmail());
	            parameters.put("deliveryCity", estDetails.getDeliveryCity());
	            parameters.put("deliveryPin", estDetails.getDeliveryPin());
	            parameters.put("warranty", estDetails.getWarranty());
	            parameters.put("panGst", estDetails.getPanGst());
	            parameters.put("totalProduct", estDetails.getTotalProduct());
	            parameters.put("ref", estDetails.getRef());
	            parameters.put("remarks", estDetails.getRemarks());
	            parameters.put("itsHaveDiscount", estDetails.getItsHaveDiscount());
	            parameters.put("discountEstimate", estDetails.getDiscountEstimate());
	            parameters.put("demoPieceEstimate", estDetails.getDemoPieceEstimate());
	            parameters.put("stockClearanceEstimate", estDetails.getStockClearanceEstimate());
	            parameters.put("discountAmount", estDetails.getDiscountAmount());
	            parameters.put("gst", estDetails.getGst());
	            parameters.put("deliveryCharges", estDetails.getDeliveryCharges());
	            parameters.put("totalAmount", estDetails.getTotalAmount());
	            parameters.put("registerStatus", estDetails.getRegisterStatus());
	            parameters.put("createdDate", estDetails.getCreatedDate().toString());
	            parameters.put("createdBy", estDetails.getCreatedBy());

	           /* mainJasperFilePath = estDetails.getItsHaveDiscount().equalsIgnoreCase("Yes")
	                                 ? "/Reports/estimation_discount.jasper"
	                                 : "/Reports/estimation_normal.jasper";*/
	            
	            mainJasperFilePath = estDetails.getItsHaveDiscount().equalsIgnoreCase("Yes")
                ? "/Reports/estimation_discount_2.jasper"
                : "/Reports/estimation_normal_1.jasper";
	        }
	        
	        // Load main report
	        try (InputStream mainReportStream = getClass().getResourceAsStream(mainJasperFilePath)) {
	            if (mainReportStream == null) {
	                throw new FileNotFoundException("Main report file not found: " + mainJasperFilePath);
	            }

	            JasperReport mainReport = (JasperReport) JRLoader.loadObject(mainReportStream);

	            // Get timestamp for file name
	            String timestamp = String.valueOf(System.currentTimeMillis());
	            String fileExtension = reportType.equalsIgnoreCase("pdf") ? ".pdf" : ".xlsx";
	            String filePath = UPLOAD_DIR + "/Reports/" + estNo + "_" + timestamp + fileExtension;

	            try (Connection connection = dbController.getConnection()) {
	                JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport, parameters, connection);

	                if (reportType.equalsIgnoreCase("pdf")) {
	                    JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
	                } else {
	                    JRXlsxExporter exporter = new JRXlsxExporter();
	                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(filePath));
	                    SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
	                    reportConfig.setOnePagePerSheet(false);
	                    reportConfig.setRemoveEmptySpaceBetweenRows(true);
	                    exporter.setConfiguration(reportConfig);
	                    exporter.exportReport();
	                }

	                response.put("status", "true");
	                response.put("message", reportType.toUpperCase() + " Report generated successfully.");
	                response.put("fileName", estNo + "_" + timestamp + fileExtension);
	                System.out.println("vResponse:::::"+response);
	                return ResponseEntity.ok(response);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("status", "false");
	        response.put("error", "File generation failed: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	

	@SuppressWarnings("unchecked")
	@PostMapping("/fsm/exportJasperReportInOrder")
    public ResponseEntity<Map<String, String>> exportJasperReportInOrder(@RequestBody String payload) {
        Map<String, String> response = new HashMap<>();
        try {
        	System.out.println("/fsm/exportJasperReportInOrder:::::"+payload);
            JSONObject jobj = new JSONObject(payload);
            String reportType = jobj.getString("reportType");
            String estNo = jobj.getString("estNo");

            //String jasperFilePath = "Reports/fsm_order_main.jasper";
            String jasperFilePath = "Reports/fsm_main_order_1.jasper";
            Map<String, Object> parameters = new HashMap<>();

            Optional<MobileOrderDetails> existingRecord = mobileOrderDetailsRepository.findByEstNo(estNo);
            if (existingRecord.isPresent()) {
                MobileOrderDetails orderDetails = existingRecord.get();

                parameters.put("id", orderDetails.getId().toString());
                parameters.put("eNo", orderDetails.geteNo());
                parameters.put("estNo", orderDetails.getEstNo());
                parameters.put("orderNo", orderDetails.getOrderNo());
                parameters.put("soNo", orderDetails.getSoNo());
                parameters.put("ddNo", orderDetails.getDdNo());
                parameters.put("customerName", orderDetails.getCustomerName());
                parameters.put("orderProcessDate", orderDetails.getOrderProcessDate() != null ? orderDetails.getOrderProcessDate().toString() : "");
                parameters.put("repCode", orderDetails.getRepCode());
                parameters.put("billingName", orderDetails.getBillingName());
                parameters.put("billingAddress", orderDetails.getBillingAddress());
                parameters.put("customerCity", orderDetails.getCustomerCity());
                parameters.put("customerPinCode", orderDetails.getCustomerPinCode());
                parameters.put("customerPhone", orderDetails.getCustomerPhone());
                parameters.put("customerEmail", orderDetails.getCustomerEmail());
                parameters.put("deliveryCity", orderDetails.getDeliveryCity());
                parameters.put("demoPlan", orderDetails.getDemoPlan());
                parameters.put("paymentCharges", orderDetails.getPaymentCharges());
                parameters.put("paymentTermDate", orderDetails.getPaymentTermDate() != null ? orderDetails.getPaymentTermDate().toString() : "");
                parameters.put("warranty", orderDetails.getWarranty());
                parameters.put("panAndGst", orderDetails.getPanAndGst());
                parameters.put("demoDate", orderDetails.getDemoDate() != null ? orderDetails.getDemoDate().toString() : "");
                parameters.put("deliveryAddress", orderDetails.getDeliveryAddress());
                parameters.put("deliveryPinCode", orderDetails.getDeliveryPinCode());
                parameters.put("expectedDate", orderDetails.getExpectedDate() != null ? orderDetails.getExpectedDate().toString() : "");
                parameters.put("shipModeName", orderDetails.getShipModeName());
                parameters.put("remarks", orderDetails.getRemarks());
                parameters.put("itsHaveDiscount", orderDetails.getItsHaveDiscount());
                parameters.put("discountEstimate", orderDetails.getDiscountEstimate());
                parameters.put("demoPieceEstimate", orderDetails.getDemoPieceEstimate());
                parameters.put("stockClearanceEstimate", orderDetails.getStockClearanceEstimate());
                parameters.put("discountAmount", orderDetails.getDiscountAmount());
                parameters.put("totalProductAmount", orderDetails.getTotalProductAmount());
                parameters.put("gst", orderDetails.getGst());
                parameters.put("deliveryCharges", orderDetails.getDeliveryCharges());
                parameters.put("totalAmount", orderDetails.getTotalAmount());
                parameters.put("lessAdvance", orderDetails.getLessAdvance());
                parameters.put("balance", orderDetails.getBalance());
                parameters.put("registerStatus", orderDetails.getRegisterStatus());
                parameters.put("createdDate", orderDetails.getCreatedDate() != null ? orderDetails.getCreatedDate().toString() : "");
                parameters.put("createdBy", orderDetails.getCreatedBy());
                parameters.put("paymentMode", orderDetails.getPaymentMode());
            }

            try (InputStream jasperStream = getClass().getClassLoader().getResourceAsStream(jasperFilePath);
            		Connection connection = dbController.getConnection()) {
                if (jasperStream == null) {
                    throw new FileNotFoundException("Main report file not found: " + jasperFilePath);
                }

                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

                String fileExtension = reportType.equalsIgnoreCase("pdf") ? ".pdf" : ".xlsx";
                String timestamp = String.valueOf(System.currentTimeMillis());
                String filePath = UPLOAD_DIR + "/Reports/" + parameters.get("orderNo") + "_" +timestamp+ fileExtension;
                
                if (reportType.equalsIgnoreCase("pdf")) {
                    JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
                } else {
                    JRXlsxExporter exporter = new JRXlsxExporter();
                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(filePath));
                    SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
                    reportConfig.setOnePagePerSheet(false);
                    reportConfig.setRemoveEmptySpaceBetweenRows(true);
                    exporter.setConfiguration(reportConfig);
                    exporter.exportReport();
                }

                response.put("status", "true");
                response.put("message", reportType.toUpperCase() + " Report generated successfully.");
                response.put("fileName", parameters.get("orderNo") + "_" +timestamp+fileExtension);
                System.out.println("vResponse:::::"+response);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "false");
            response.put("error", "File generation failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/fsm/exportJasperReportInPayment")
    public ResponseEntity<Map<String, String>> exportJasperReportInPayment(@RequestBody String payload) {
        Map<String, String> response = new HashMap<>();
        try {
        	System.out.println("/fsm/exportJasperReportInPayment:::::"+payload);
            JSONObject jobj = new JSONObject(payload);
            String reportType = jobj.getString("reportType");
            String estNo = jobj.getString("estNo");

            //String jasperFilePath = "Reports/fsm_order_main.jasper";
            String jasperFilePath = "Reports/fsm_main_order_1.jasper";
            Map<String, Object> parameters = new HashMap<>();

            Optional<PaymentDetails> existingRecord = paymentDetailsRepository.findByEstNo(estNo);
            if (existingRecord.isPresent()) {
                PaymentDetails orderDetails = existingRecord.get();

                parameters.put("id", orderDetails.getId().toString());
                parameters.put("eNo", orderDetails.geteNo());
                parameters.put("estNo", orderDetails.getEstNo());
                parameters.put("orderNo", orderDetails.getOrderNo());
                parameters.put("soNo", orderDetails.getSoNo());
                parameters.put("ddNo", orderDetails.getDdNo());
                parameters.put("customerName", orderDetails.getCustomerName());
                parameters.put("orderProcessDate", orderDetails.getOrderProcessDate() != null ? orderDetails.getOrderProcessDate().toString() : "");
                parameters.put("repCode", orderDetails.getRepCode());
                parameters.put("billingName", orderDetails.getBillingName());
                parameters.put("billingAddress", orderDetails.getBillingAddress());
                parameters.put("customerCity", orderDetails.getCustomerCity());
                parameters.put("customerPinCode", orderDetails.getCustomerPinCode());
                parameters.put("customerPhone", orderDetails.getCustomerPhone());
                parameters.put("customerEmail", orderDetails.getCustomerEmail());
                parameters.put("deliveryCity", orderDetails.getDeliveryCity());
                parameters.put("demoPlan", orderDetails.getDemoPlan());
                parameters.put("paymentCharges", orderDetails.getPaymentCharges());
                parameters.put("paymentTermDate", orderDetails.getPaymentTermDate() != null ? orderDetails.getPaymentTermDate().toString() : "");
                parameters.put("warranty", orderDetails.getWarranty());
                parameters.put("panAndGst", orderDetails.getPanAndGst());
                parameters.put("demoDate", orderDetails.getDemoDate() != null ? orderDetails.getDemoDate().toString() : "");
                parameters.put("deliveryAddress", orderDetails.getDeliveryAddress());
                parameters.put("deliveryPinCode", orderDetails.getDeliveryPinCode());
                parameters.put("expectedDate", orderDetails.getExpectedDate() != null ? orderDetails.getExpectedDate().toString() : "");
                parameters.put("shipModeName", orderDetails.getShipModeName());
                parameters.put("remarks", orderDetails.getRemarks());
                parameters.put("itsHaveDiscount", orderDetails.getItsHaveDiscount());
                parameters.put("discountEstimate", orderDetails.getDiscountEstimate());
                parameters.put("demoPieceEstimate", orderDetails.getDemoPieceEstimate());
                parameters.put("stockClearanceEstimate", orderDetails.getStockClearanceEstimate());
                parameters.put("discountAmount", orderDetails.getDiscountAmount());
                parameters.put("totalProductAmount", orderDetails.getTotalProductAmount());
                parameters.put("gst", orderDetails.getGst());
                parameters.put("deliveryCharges", orderDetails.getDeliveryCharges());
                parameters.put("totalAmount", orderDetails.getTotalAmount());
                parameters.put("lessAdvance", orderDetails.getLessAdvance());
                parameters.put("balance", orderDetails.getBalance());
                parameters.put("registerStatus", orderDetails.getRegisterStatus());
                parameters.put("createdDate", orderDetails.getCreatedDate() != null ? orderDetails.getCreatedDate().toString() : "");
                parameters.put("createdBy", orderDetails.getCreatedBy());
                parameters.put("paymentMode", orderDetails.getPaymentMode());
            }

            try (InputStream jasperStream = getClass().getClassLoader().getResourceAsStream(jasperFilePath);
            		Connection connection = dbController.getConnection()) {
                if (jasperStream == null) {
                    throw new FileNotFoundException("Main report file not found: " + jasperFilePath);
                }

                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

                String fileExtension = reportType.equalsIgnoreCase("pdf") ? ".pdf" : ".xlsx";
                String timestamp = String.valueOf(System.currentTimeMillis());
                String filePath = UPLOAD_DIR + "/Reports/" + parameters.get("orderNo") + "_" +timestamp+ fileExtension;
                
                if (reportType.equalsIgnoreCase("pdf")) {
                    JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
                } else {
                    JRXlsxExporter exporter = new JRXlsxExporter();
                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(filePath));
                    SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
                    reportConfig.setOnePagePerSheet(false);
                    reportConfig.setRemoveEmptySpaceBetweenRows(true);
                    exporter.setConfiguration(reportConfig);
                    exporter.exportReport();
                }

                response.put("status", "true");
                response.put("message", reportType.toUpperCase() + " Report generated successfully.");
                response.put("fileName", parameters.get("orderNo") + "_" +timestamp+fileExtension);
                System.out.println("vResponse:::::"+response);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "false");
            response.put("error", "File generation failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }





}
