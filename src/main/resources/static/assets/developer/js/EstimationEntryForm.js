

function getEstimationEntryForm(showType){
	var btnHTML = ``;									

	if(showType == "add") {
		
	    btnHTML = `
	        <div class="row">
	            <div class="col-12 col-lg-6 d-flex justify-content-lg-start justify-content-center mb-2 mb-lg-0">
	                <button class="btn btn-light w-100" data-bs-dismiss="modal" onclick="clearEstimationDetails()">Clear</button>
	            </div>
	            <div class="col-12 col-lg-6 d-flex justify-content-lg-end justify-content-center">
	                <button class="btn btn-primary w-100" data-bs-dismiss="modal" onclick="updateEstimationDetails()">Save</button>
	            </div>
	        </div>`;
	} else if(showType == "edit") {		
	    btnHTML = `
	        <div class="row">
	            <div class="col-12 col-lg-4 d-flex justify-content-lg-start justify-content-center mb-2 mb-lg-0">
	                <button class="btn btn-primary w-100" data-bs-dismiss="modal" style="background:#fe3f51;" onclick="deleteEstimationDetails('')">Delete</button>
	            </div>
	            <div class="col-12 col-lg-4 d-flex justify-content-lg-center justify-content-center mb-2 mb-lg-0">
	                <button class="btn btn-light w-100" data-bs-dismiss="modal" onclick="clearEstimationDetails()">Clear</button>
	            </div>
	            <div class="col-12 col-lg-4 d-flex justify-content-lg-end justify-content-center">
	                <button class="btn btn-primary w-100" data-bs-dismiss="modal" onclick="updateEstimationDetails()">Save</button>
	            </div>
	        </div>`;
	}

	var form = `<div class="content">
			     			
                	<div class="row align-items-center">
	                    <div class="col"></div>
	                    <div class="col-auto">	                            	
	                        <a href="#" class="btn btn-primary btn-rounded float-right" onclick="showEstimationListForm('')"><i class="fas fa-arrow-left"></i> Back to Table</a>
	                    </div>
                	</div>
					<div class="row align-items-center">
                    	<div class="col">
                        	<h2 class="page-title">Estimation Process</h2>
                    	</div>                    
                	</div>           
					<div class="row align-items-right" id="estimation_form_export_element_row">
						<div class="col"></div>
						<div class="text-end">
							<select class="btn btn-light float-right btn-rounded" id="estimation_form_report_type_select">
								<option>pdf</option>							
								<option>excel</option>
							</select>
                        	<a href="#" class="btn btn-primary float-right btn-rounded" onclick="exportJasperReportInEstimation()">Export</a>
						</div>
                    </div>                	     
                	<div class="row">
                    	<div class="card-box">                        
                            	<div class="row">
									    <div class="col-12 col-lg-6">
									        <div class="mb-3">
									            <label for="fsm_Estimation_detail_EstimationNO" class="form-label">EST.NO</label>
									            <input type="hidden" class="form-control" id="fsm_Estimation_detail_uuid_id" name="UUID">
									            <input type="text" class="form-control" id="fsm_Estimation_detail_EstimationNO" name="EstimationNO"  placeholder="Auto-Generate" disabled>
									        </div>
									        <div class="mb-3">
									            <label for="fsm_Estimation_detail_customer_name" class="form-label">Customer Name</label>
									            <input type="text" class="form-control" id="fsm_Estimation_detail_customer_name" name="Customer Name">
									        </div>
									        <div class="mb-3">
									            <label for="fsm_Estimation_detail_EstimationProcessDate" class="form-label">Estimation Process Date</label>
									            <input type="date" class="form-control" id="fsm_Estimation_detail_EstimationProcessDate" name="EstimationProcessDate" data-mask="00/00/0000 00:00:00" autocomplete="off">
									        </div>									        
									        																				
									        <div class="mb-3">
									            <label for="fsm_Estimation_detail_billing_address" class="form-label">Billing Address </label>
									            <textarea class="form-control" id="fsm_Estimation_detail_billing_address" name="Billing Address"></textarea>
									        </div>
									        
											<div class="mb-3">
									            <label for="fsm_Estimation_detail_customer_city" class="form-label">Customer City</label>
									            <input type="text" class="form-control" id="fsm_Estimation_detail_customer_city" name="Customer City">
									        </div>
									        <div class="mb-3">
									            <label for="fsm_Estimation_detail_customer_pin_code" class="form-label">Customer Pin code</label>
									            <input type="text" class="form-control" id="fsm_Estimation_detail_customer_pin_code" name="Customer City">
									        </div>
									        <div class="mb-3">
									            <label for="fsm_Estimation_detail_customer_phone" class="form-label">Customer Phone No</label>
									            <input type="tel" class="form-control" id="fsm_Estimation_detail_customer_phone" name="Customer Phone No">
									        </div>
									        <div class="mb-3">
									            <label for="fsm_Estimation_detail_customer_email" class="form-label">Customer Email</label>
									            <input type="tel" class="form-control" id="fsm_Estimation_detail_customer_email" name="Customer Email">
									        </div>
									    </div>
									    
									    <div class="col-12 col-lg-6">
									    	<div class="mb-3">
									            <label for="fsm_Estimation_detail_rep_account" class="form-label">Rep A/C</label>
									            <input type="text" class="form-control" id="fsm_Estimation_detail_rep_account" name="Rep A/C">
									        </div>																		
											<div class="mb-3">
									            <label for="fsm_Estimation_detail_rep_attd" class="form-label">Rep Attd</label>
									            <input class="form-control" id="fsm_Estimation_detail_rep_attd" name="Rep Attd" />												
									        </div>

									        <div class="mb-3">
									            <label for="fsm_Estimation_detail_delivery_city" class="form-label">Delivery City</label>
									            <input type="text" class="form-control" id="fsm_Estimation_detail_delivery_city" name="Delivery City">
									        </div>
									        <div class="mb-3">															
									            <label for="fsm_Estimation_detail_delivery_ddress" class="form-label">Delivery Address</label>
									            <textarea class="form-control" id="fsm_Estimation_detail_delivery_ddress" name="Delivery Address"></textarea>
											</div>
											<div class="mb-3">
									            <label for="fsm_Estimation_detail_delivery_pin_code" class="form-label">Delivery Pin code</label>
									            <input type="text" class="form-control" id="fsm_Estimation_detail_delivery_pin_code" name="Delivery Pin code">
									        </div>
									        <div class="mb-3">
									            <label for="fsm_Estimation_detail_warrantly" class="form-label">Warrenty</label>
									            <input type="text" class="form-control" id="fsm_Estimation_detail_warrantly" name="Warrenty" />
									        </div>
									        <div class="mb-3">
									            <label for="fsm_Estimation_detail_Pan_and_gst" class="form-label">Pan / GST</label>
									            <input type="text" class="form-control" id="fsm_Estimation_detail_Pan_and_gst" name="Pan / GST">
									        </div>									        
									    </div>
									</div>



								<div class="card-body">	                    
								    <div class="row">
								        <div class="col-sm-12">
								            <div class="card mb-0">
								                <div class="card-body">
								                    <div class="table-responsive">
														<div class="row">
															<div class="col-6">
																<h2 class="card-title">Add Product</h2>
															</div>
															<div class="col-6">
																<div class="text-sm-end">
																	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#taskModal" onclick="dynamicEstimationProductRow()">Create</button>
																</div>
															</div>
														</div>
								                        <table class="table table-center table-hover datatable">
								                            <thead class="thead-light">
								                                <tr>
								                                    <th class="form-label">S.No</th>
								                                    <th class="form-label">Product Details</th>
								                                    <th class="form-label">Pdt.sl.code</th>
								                                    <th class="form-label">Qty</th>
								                                    <th class="form-label">Unit Price</th>
								                                    <th class="form-label">Tax %</th>
								                                    <th class="form-label">Total</th>
								                                    <th class="form-label">Delete</th>
								                                </tr>
								                            </thead>
								                            <tbody id="estimation_product_tbody_id"></tbody>
								                            <tbody>
								                                <tr>
								                                    <td colspan="5"></td>
								                                    <td class="form-label">Total Product:</td>
								                                    <td colspan="2" id="estimation_total_product_id">0.00</td>
								                                </tr>
								                            </tbody>
								                            <thead class="thead-light">
								                                <tr>
								                                   <td class="form-label">Ref:</td>
								                                    <td colspan="3">
								                                    	<input type="text" class="form-control" id="fsm_Estimation_detail_ref" name="Ref">
								                                    </td>
								                                    <td class="form-label">Its have Discount ?:</td>
								                                    <td colspan="3">
									                                    <select class="form-select" id="fsm_Estimation_detail_its_have_discount" name="Its have Discount ?" onclick="toggleItsHaveDiscount(this.value)">
																			<option>No</option>
																			<option>Yes</option>						
																		</select>
								                                    </td>
								                                </tr>
								                                <tr>
								                                   <td class="form-label">Remarks:</td>                       
								                                    <td colspan="7">
									                                   <textarea class="form-control" id="fsm_Estimation_detail_remarks" name="remarks"></textarea>
								                                    </td>
								                                </tr>
															</thead>
														</table>
														<div class="table-responsive" id="estimation_table_discount_toggle_container" style="display:none;">
														<table class="table table-center table-hover datatable" >
															<tbody>	
								                            	<tr>
								                                   <td colspan="5" class="form-label">Discount Estimate:</td>
								                                    <td colspan="2">
								                                    	<input type="text" class="form-control" id="fsm_Estimation_detail_discount_estimate" name="Discount Estimate">
								                                    </td>
								                                    <td class="form-label">For %</td>               
								                                </tr>
								                                <tr>
								                                   <td colspan="5" class="form-label">Demo Piece Estimate:</td>
								                                    <td colspan="2">
								                                    	<input type="text" class="form-control" id="fsm_Estimation_detail_demo_piece_estimate" name="Discount Estimate">
								                                    </td>
								                                    <td class="form-label">For %</td>               
								                                </tr>
								                                <tr>
								                                   <td colspan="5" class="form-label">Stock Clearance Estimate:</td>
								                                    <td colspan="2">
								                                    	<input type="text" class="form-control" id="fsm_Estimation_detail_stock_clearance_estimate" name="Stock Clearance Estimate">
								                                    </td>
								                                    <td class="form-label">For %</td>               
								                                </tr>
								                                <tr>
								                                   <td colspan="5" class="form-label">Dicount Amount:</td>
								                                    <td colspan="3">
								                                    	<input type="text" class="form-control" id="fsm_Estimation_detail_discount_amount" name="Discoutn Amount">
								                                    </td>
								                                                  
								                                </tr>
								                                
																  
															</tbody>
														</table>
														</div>
														<table class="table table-center table-hover datatable">
															<thead class="thead-light">
															    <tr>
																	<td colspan="5" class="form-label">GST Amount:</td>
								                                    <td colspan="3">
								                                    	<input type="text" class="form-control" id="fsm_Estimation_detail_gst" name="GST Amount">
								                                    </td>                    
											                	</tr>     
								                                <tr>
								                                   <td colspan="5" class="form-label">Delivery Charges:</td>
								                                    <td colspan="3">
								                                    	<input type="text" class="form-control" id="fsm_Estimation_detail_delivery_charges" name="Delivery Charges">
								                                    </td>                               
								                                </tr>
								                                <tr>
								                                   <td colspan="5" class="form-label">Total Amount:</td>
								                                    <td colspan="3">
								                                    	<input type="text" class="form-control" id="fsm_Estimation_detail_total_amount" name="Total Amount">
								                                    </td>              
								                                </tr>
								                                <tr>
								                                   <td colspan="5" class="form-label">Approval Status:</td>
								                                    <td colspan="3">
								                                    	<select class="form-select" id="fsm_Estimation_detail_approval_status1" name="Estimation Status">
																			<option value="Estimation Enquiry">Estimation Enquiry</option>
																			<option value="Cancel Estimation">Cancel Estimation</option>	
																			<option value="Convert To Order">Convert To Order</option>	
																			<option value="Not Matured">Not Matured</option>	
																		</select>
								                                    </td>              
								                                </tr>
								                            </thead>
								                        </table>
								                    </div>
								                </div>
								            </div>	
								        </div>    
								    </div>                    
								</div>			                                              	            
								<div class="row">
								    <div class="mb-0 mt-3"> `+btnHTML+`</div>
								</div>
                                            
                    </div>                    
                </div>  
                <div class="backgroundFormEnterBodyContainerCls"></div>
                                                         
            </div>`;
	return form;
};


																	


function showEstimationEntryForm(showType){
    try{
        hideAllLayer();
		var containerId = "est_entry_form";
        document.getElementById(containerId).innerHTML = getEstimationEntryForm(showType);		
        document.getElementById(containerId).style.display = "block";
		getProductListToDatalistTagOption();
		
		if(showType != 'edit'){
			document.getElementById("estimation_form_export_element_row").style.display="none";
			getUUIDForEstimationDetails();	
		}else{
			document.getElementById("estimation_form_export_element_row").style.display="block";
		}	
		createOptionTagInSelectTag("fsm_Estimation_detail_approval_status1",estimation_ApprovalStatusArrayString);						
    }catch(exp){        
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};

async function getUUIDForEstimationDetails(){
	var canContinue	= true;
    var jsonObj = JSON.parse("{}");
	jsonObj['ID'] = "";
			
	if(canContinue){  		
	    let url = "/fsm/generateUUID";
		let itemName = "generateUUID";
	    await getDataFromServicePoint(url,jsonObj)
	        .then(async data => await populateUUIDEstimationDetailsVResponse(data)) 
	        .catch(error => handleError(itemName,error));
	}
};

function populateUUIDEstimationDetailsVResponse(vResponseObj){	
    if(vResponseObj.status == "true"){		
		document.getElementById("fsm_Estimation_detail_uuid_id").value = vResponseObj.id; 							        		
	}else{
		toastr.warning("UUID its not Generate","Warning", {closeButton: !0,tapToDismiss: !1});
	}
};


/*****************************************Update *********************************************** */
async function updateEstimationDetails(){	
	var canContinue	= true;

	var jsonObj = JSON.parse("{}");	
		jsonObj['ID'] = document.getElementById("fsm_Estimation_detail_uuid_id").value;
	    jsonObj['EST.NO'] = document.getElementById("fsm_Estimation_detail_EstimationNO").value;    
	    jsonObj['Customer Name'] = document.getElementById("fsm_Estimation_detail_customer_name").value;
	    jsonObj['Estimation Process Date'] = document.getElementById("fsm_Estimation_detail_EstimationProcessDate").value;
	    jsonObj['Rep Attd'] = document.getElementById("fsm_Estimation_detail_rep_attd").value;
	    jsonObj['Rep A/C'] = document.getElementById("fsm_Estimation_detail_rep_account").value;
	    jsonObj['Billing Address'] = document.getElementById("fsm_Estimation_detail_billing_address").value;
	    jsonObj['Delivery Address'] = document.getElementById("fsm_Estimation_detail_delivery_ddress").value;
	    jsonObj['Customer City'] = document.getElementById("fsm_Estimation_detail_customer_city").value;
	    jsonObj['Customer Pin Code'] = document.getElementById("fsm_Estimation_detail_customer_pin_code").value;
	    jsonObj['Customer Phone'] = document.getElementById("fsm_Estimation_detail_customer_phone").value;
	    jsonObj['Customer Email'] = document.getElementById("fsm_Estimation_detail_customer_email").value;
	    jsonObj['Delivery City'] = document.getElementById("fsm_Estimation_detail_delivery_city").value;
	    jsonObj['Delivery Pin Code'] = document.getElementById("fsm_Estimation_detail_delivery_pin_code").value;  
	    jsonObj['Warranty'] = document.getElementById("fsm_Estimation_detail_warrantly").value;
	    jsonObj['Pan / GST'] = document.getElementById("fsm_Estimation_detail_Pan_and_gst").value;
	    jsonObj['Total Product'] = document.getElementById("estimation_total_product_id").innerHTML;
	    jsonObj['Ref'] = document.getElementById("fsm_Estimation_detail_ref").value;
	    jsonObj['Remarks'] = document.getElementById("fsm_Estimation_detail_remarks").value;
	    jsonObj['Its Have Discount'] = document.getElementById("fsm_Estimation_detail_its_have_discount").value;
	    jsonObj['Discount Estimate'] = document.getElementById("fsm_Estimation_detail_discount_estimate").value;
	    jsonObj['Demo Piece Estimate'] = document.getElementById("fsm_Estimation_detail_demo_piece_estimate").value;
	    jsonObj['Stock Clearance Estimate'] = document.getElementById("fsm_Estimation_detail_stock_clearance_estimate").value;
	    jsonObj['Discount Amount'] = document.getElementById("fsm_Estimation_detail_discount_amount").value;
	    jsonObj['GST'] = document.getElementById("fsm_Estimation_detail_gst").value;
	    jsonObj['Delivery Charges'] = document.getElementById("fsm_Estimation_detail_delivery_charges").value;
	    jsonObj['Total Amount'] = document.getElementById("fsm_Estimation_detail_total_amount").value;
	    jsonObj['Estimation Status'] = document.getElementById("fsm_Estimation_detail_approval_status1").value;	
	    jsonObj['Created Date'] = new Date().toISOString();
	    jsonObj['Created By'] = logginerUserId;  
	/*var inputNames = "Estimation"; 
	var inputTypes = "text";
	var inputElementIds = "fsm_Estimation_detail_EstimationProcessDate";
	canContinue ? canContinue = await checkValidationDynamicInputRow(inputNames,inputTypes,inputElementIds): canContinue = false;*/
	if(canContinue){  		
	    let url = "/fsm/updateEstimationDetails";
		let itemName = "updateEstimationDetails";
	    await getDataFromServicePoint(url,jsonObj)
	        .then(async data => await populateEstimationDetailsVResponse(data,jsonObj['Estimation Status'])) 
	        .catch(error => handleError(itemName,error));
	}
};

async function populateEstimationDetailsVResponse(vResponseObj,estimationStatus){
    if(vResponseObj.status == "true"){	
		updateEstimationProductsDetails();
		if(estimationStatus == "Convert To Order"){
			convertToOrderStatusUpdateProductsDetails(vResponseObj.id);
		}							        		
	}else{
		toastr.warning("Invalid Estimation Details","Warning", {closeButton: !0,tapToDismiss: !1});
	}
};

/*****************************************Clear *********************************************** */
function clearEstimationDetails(){    
	document.getElementById("fsm_Estimation_detail_uuid_id").value = "";
	document.getElementById("fsm_Estimation_detail_EstimationNO").value = "";	
	document.getElementById("fsm_Estimation_detail_customer_name").value = "";
	document.getElementById("fsm_Estimation_detail_EstimationProcessDate").value = "";	
	document.getElementById("fsm_Estimation_detail_billing_address").value = "";
	document.getElementById("fsm_Estimation_detail_customer_city").value = "";
	document.getElementById("fsm_Estimation_detail_customer_pin_code").value = "";
	document.getElementById("fsm_Estimation_detail_customer_phone").value = "";
	document.getElementById("fsm_Estimation_detail_customer_email").value = "";
	document.getElementById("fsm_Estimation_detail_rep_account").value = "";
	document.getElementById("fsm_Estimation_detail_rep_attd").value = "";
	document.getElementById("fsm_Estimation_detail_delivery_city").value = "";
	document.getElementById("fsm_Estimation_detail_delivery_ddress").value = "";
	document.getElementById("fsm_Estimation_detail_delivery_pin_code").value = "";  // Note: There are two fields with the same ID, this might cause issues
	document.getElementById("fsm_Estimation_detail_warrantly").value = "";
	document.getElementById("fsm_Estimation_detail_Pan_and_gst").value = "";
	document.getElementById("estimation_total_product_id").innerHTML = "0.00";
	document.getElementById("fsm_Estimation_detail_ref").value = "";
	document.getElementById("fsm_Estimation_detail_its_have_discount").value = "";
	document.getElementById("fsm_Estimation_detail_remarks").value = "";
	document.getElementById("fsm_Estimation_detail_discount_estimate").value = "";
	document.getElementById("fsm_Estimation_detail_demo_piece_estimate").value = "";
	document.getElementById("fsm_Estimation_detail_stock_clearance_estimate").value = "";
	document.getElementById("fsm_Estimation_detail_discount_amount").value = "";
	document.getElementById("fsm_Estimation_detail_gst").value = "";
	document.getElementById("fsm_Estimation_detail_delivery_charges").value = "";
	document.getElementById("fsm_Estimation_detail_total_amount").value = "";
	document.getElementById("fsm_Estimation_detail_approval_status1").value = "";

	
	document.getElementById('estimation_product_tbody_id').innerHTML = "";		   
};
/*****************************************Edit *********************************************** */


async function editEstimationDetails(vObj){
	
	var jsonObj = JSON.parse("{}");	
	jsonObj = await buildDynamicChexBoxColumnTableJsonObj(vObj);	
	swal({
	        title: "Confirmation",
	        text: "Do you want to Edit ?",
	        icon: "info",
	        buttons: true,
	        dangerMode: true,
	 }).then((confirmation) => {
	        if (confirmation) {	
				showEstimationEntryForm('edit');
							
				let url = "/fsm/editEstimationDetails";
				let itemName= "editEstimationDetails";
			    getDataFromServicePoint(url,jsonObj)
			        .then(data => populateEditEstimationDetailsVResponse(data)) 
			        .catch(error => handleError(itemName,error));     
	        } 
	 });				
};

function populateEditEstimationDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){				
		var jsonObj = vResponseObj.data;						
		
    document.getElementById("fsm_Estimation_detail_uuid_id").value = jsonObj['ID'];
    document.getElementById("fsm_Estimation_detail_EstimationNO").value = jsonObj['EST.NO'];    
    document.getElementById("fsm_Estimation_detail_customer_name").value = jsonObj['Customer Name'];
    document.getElementById("fsm_Estimation_detail_EstimationProcessDate").value = jsonObj['Estimation Process Date'];
    document.getElementById("fsm_Estimation_detail_rep_attd").value = jsonObj['Rep Attd'];
    document.getElementById("fsm_Estimation_detail_rep_account").value = jsonObj['Rep A/C'];
    document.getElementById("fsm_Estimation_detail_billing_address").value = jsonObj['Billing Address'];
    document.getElementById("fsm_Estimation_detail_delivery_ddress").value = jsonObj['Delivery Address'];
    document.getElementById("fsm_Estimation_detail_customer_city").value = jsonObj['Customer City'];
    document.getElementById("fsm_Estimation_detail_customer_pin_code").value = jsonObj['Customer Pin Code'];
    document.getElementById("fsm_Estimation_detail_customer_phone").value = jsonObj['Customer Phone'];
    document.getElementById("fsm_Estimation_detail_customer_email").value = jsonObj['Customer Email'];
    document.getElementById("fsm_Estimation_detail_delivery_city").value = jsonObj['Delivery City'];
    document.getElementById("fsm_Estimation_detail_delivery_pin_code").value = jsonObj['Delivery Pin Code'];  
    document.getElementById("fsm_Estimation_detail_warrantly").value = jsonObj['Warranty'];
    document.getElementById("fsm_Estimation_detail_Pan_and_gst").value = jsonObj['Pan / GST'];
    document.getElementById("estimation_total_product_id").innerHTML = jsonObj['Total Product'];
    document.getElementById("fsm_Estimation_detail_ref").value = jsonObj['Ref'];
    document.getElementById("fsm_Estimation_detail_remarks").value = jsonObj['Remarks'];
    document.getElementById("fsm_Estimation_detail_its_have_discount").value = jsonObj['Its Have Discount'];
    document.getElementById("fsm_Estimation_detail_discount_estimate").value = jsonObj['Discount Estimate'];
    document.getElementById("fsm_Estimation_detail_demo_piece_estimate").value = jsonObj['Demo Piece Estimate'];
    document.getElementById("fsm_Estimation_detail_stock_clearance_estimate").value = jsonObj['Stock Clearance Estimate'];
    document.getElementById("fsm_Estimation_detail_discount_amount").value = jsonObj['Discount Amount'];
    document.getElementById("fsm_Estimation_detail_gst").value = jsonObj['GST'];
    document.getElementById("fsm_Estimation_detail_delivery_charges").value = jsonObj['Delivery Charges'];
    document.getElementById("fsm_Estimation_detail_total_amount").value = jsonObj['Total Amount'];
    document.getElementById("fsm_Estimation_detail_approval_status1").value = jsonObj['Estimation Status'];
	toggleItsHaveDiscount(jsonObj['Its Have Discount']);
	var productsJsonObj = vResponseObj.estProductData;
		loadEstimationProductRowsFromJson(productsJsonObj);		    		
	}
};
/*****************************************Delete *********************************************** */
async function deleteEstimationDetails(vObj){
	var jsonObj = JSON.parse("{}");
	if(vObj == ""){
		jsonObj['ID'] = document.getElementById("fsm_Estimation_detail_uuid_id").value;		    		    		   
	}else{
		jsonObj = await buildDynamicChexBoxColumnTableJsonObj(vObj);
	}		
	
		swal({
		        title: "Confirmation",
		        text: "Do you want to delete ?",
		        icon: "warning",
		        buttons: true,
		        dangerMode: true,
		 }).then((confirmation) => {
		        if (confirmation) {
					let url = "/fsm/deleteEstimationDetails";
					let itemName= "deleteEstimationDetails";
	    			getDataFromServicePoint(url,jsonObj)
	        			.then(data => populateDeleteEstimationDetailsVResponse(data)) 
	        			.catch(error => handleError(itemName,error));
				}
		});	
				
};

function populateDeleteEstimationDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){			
		toastr.success("Deleted Successfully","Completed", {closeButton: !0,tapToDismiss: !1});			
		showEstimationListForm('');		    		    		   	
	}
};

 

function dynamicEstimationProductRow() {
    const tbody = document.getElementById("estimation_product_tbody_id");	
	var newProductCounter = tbody.childNodes.length+1;
    const newRow = document.createElement("tr");
    const estimationId = document.getElementById("fsm_Estimation_detail_uuid_id").value;
    newRow.innerHTML = `<td><input 
			type="hidden" name="ID" class="form-control" placeholder="UUID" /><input 
			type="hidden" name="ReferenceID" class="form-control" value="${estimationId}" />
            ${newProductCounter}</td><td><input 
			type="text" name="ProductDetails" list="product_name_list_datalist_tag" class="form-control" placeholder="Product Name" onkeyPress="allowEnterKeyOnlyForLoadProductDetails(event,'ProductName',this)" /></td><td><input 
			type="text" name="ProductCode" list="product_id_list_datalist_tag"  class="form-control" placeholder="Pdt.sl.code" onkeyPress="allowEnterKeyOnlyForLoadProductDetails(event,'ProductID',this)" /></td><td><input 
			type="number" name="ProductQty" class="form-control" placeholder="0.00" min="0" onkeypress="onlyNumberKey(event)" onkeyup="calculateRowTotal(this)" /></td><td><input 
			type="number" name="ProductUnitPrice" class="form-control" placeholder="0.00" min="0" onkeypress="onlyNumberKey(event)" onkeyup="calculateRowTotal(this)" /></td><td><input 
			type="number" name="ProductTax" class="form-control" placeholder="0.00" min="0" onkeypress="onlyNumberKey(event)" onkeyup="calculateRowTotal(this)" /></td><td><input 
			type="number" name="ProductTotal" class="form-control" placeholder="0.00" onkeypress="onlyNumberKey(event)" readonly /></td><td><button 
			type="button" class="btn btn-danger" onclick="deleteEstimationProductsDetails(this)">Delete</button></td>`;

    tbody.appendChild(newRow);

    newProductCounter++;
    updateTotalProducts();
};

function calculateRowTotal(inputElement) {
    const row = inputElement.closest("tr");
    const qty = parseFloat(row.querySelector('input[name="ProductQty"]').value) || 0;
    const unitPrice = parseFloat(row.querySelector('input[name="ProductUnitPrice"]').value) || 0;
    const tax = parseFloat(row.querySelector('input[name="ProductTax"]').value) || 0;

	const taxPercent = (qty*unitPrice )*tax/100;
	const total = (qty * unitPrice) + taxPercent;

    row.querySelector('input[name="ProductTotal"]').value = total.toFixed(2);
    updateTotalProducts();
};


function updateTotalProducts() {
	
    let totalProductAmount = 0;
    const rows = document.querySelectorAll("#estimation_product_tbody_id tr");

    rows.forEach((row) => {
        const totalInput = row.querySelector('input[name="ProductTotal"]');
        if (totalInput) {
            totalProductAmount += parseFloat(totalInput.value) || 0;
        }
    });
	
    document.getElementById("estimation_total_product_id").innerHTML = totalProductAmount.toFixed(2);
	
	var discountAmount = Number(document.getElementById("fsm_Estimation_detail_discount_amount").value || "0.00");
	var orderGstAmount = Number(document.getElementById("fsm_Order_detail_gst").value || "0.00");
	var deliveryAmount = Number(document.getElementById("fsm_Order_detail_delivery_charges").value || "0.00");
	document.getElementById("fsm_Order_detail_total_amount").value = totalProductAmount.toFixed(2) + orderGstAmount + deliveryAmount;
};


function loadEstimationProductRowsFromJson(jsonData) {
    const tbody = document.getElementById("estimation_product_tbody_id");
    tbody.innerHTML = ""; 
    let productCounter = 1;

    jsonData.forEach((item) => {
        const newRow = document.createElement("tr");

        newRow.innerHTML = `<td><input 
				type="hidden" name="ID" class="form-control" value="${item['ID']}" placeholder="ID" /><input 
				type="hidden" name="ReferenceID" class="form-control" value="${item['Reference ID']}" placeholder="Reference ID" />
                ${productCounter}</td><td><input 
				type="text" name="ProductDetails" class="form-control" list="product_name_list_datalist_tag" value="${item['Product Details']}" placeholder="Product Details" onkeyPress="allowEnterKeyOnlyForLoadProductDetails(event,'ProductName',this)" /></td><td><input 
				type="text" name="ProductCode" class="form-control" list="product_id_list_datalist_tag" value="${item['Product Code'] || ''}" placeholder="Pdt.sl.code" onkeyPress="allowEnterKeyOnlyForLoadProductDetails(event,'ProductId',this)"/></td><td><input 
				type="number" name="ProductQty" class="form-control" value="${item['Qty'] || 0}" placeholder="Qty" min="0" onkeyup="calculateRowTotal(this)" /></td><td><input 
				type="number" name="ProductUnitPrice" class="form-control" value="${item['Unit Price'] || 0}" placeholder="Unit Price" min="0" onkeyup="calculateRowTotal(this)" /></td><td><input 
				type="number" name="ProductTax" class="form-control" value="${item['Tax'] || 0}" placeholder="Tax" min="0" onkeyup="calculateRowTotal(this)" /></td><td><input type="number" name="ProductTotal" class="form-control" value="${item['Total'] || 0}" placeholder="Total" readonly /></td><td><button 
				type="button" class="btn btn-danger" onclick="deleteEstimationProductsDetails(this)">Delete</button></td>`;

        tbody.appendChild(newRow);
        productCounter++;
    });

  
    updateTotalProducts();
};


async function updateEstimationProductsDetails() {
    const rows = document.querySelectorAll('#estimation_product_tbody_id tr');
    const ProductsArray = [];

    rows.forEach((row, index) => {
		const id = row.querySelector('input[name="ID"]').value;
		const referenceId = row.querySelector('input[name="ReferenceID"]').value;		
        const ProductsDetails = row.querySelector('input[name="ProductDetails"]').value;
        const ProductsCode = row.querySelector('input[name="ProductCode"]').value;
        const ProductsQty = row.querySelector('input[name="ProductQty"]').value;
        const ProductsUnitPrice = row.querySelector('input[name="ProductUnitPrice"]').value;
        const ProductsTax = row.querySelector('input[name="ProductTax"]').value;
        const ProductsTotal = row.querySelector('input[name="ProductTotal"]').value;

        const ProductData = {
            "ID": id,
			"Reference ID":referenceId,
            "Product Details": ProductsDetails,
            "Product Code": ProductsCode,
            "Qty": ProductsQty,
            "Unit Price": ProductsUnitPrice,
            "Tax": ProductsTax,
            "Total": ProductsTotal
        };

        ProductsArray.push(ProductData);
    });																																																																																
    try {
        const response = await fetch('/fsm/updateEstimationProductsDetails', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(ProductsArray)
        });

        if (response.ok) {
            const vResponseObj = await response.json();

			if(vResponseObj.status === "true"){
				toastr.success("Successfully Updated", "Completed", {closeButton: !0,tapToDismiss: !1});						        
				showEstimationListForm('');		
			} else {
				toastr.warning("Invalid Estimation Details", "Warning", {closeButton: !0,tapToDismiss: !1});
			}
        } else {
            console.error('Error submitting Products:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
};





async function deleteEstimationProductsDetails(vObj){
	var row = vObj.parentNode.parentNode;
	var id = vObj.parentNode.parentNode.childNodes[0].childNodes[0].value;
	if( id!= ""){
		var jsonObj = JSON.parse("{}");	
		jsonObj['ID'] = id;	
			
		swal({
		        title: "Confirmation",
		        text: "Do you want to delete ?",
		        icon: "warning",
		        buttons: true,
		        dangerMode: true,
		 }).then((confirmation) => {
	        if (confirmation) {
				let url = "/fsm/deleteEstimationProductsDetails";
				let itemName= "deleteEstimationProductsDetails";
    			getDataFromServicePoint(url,jsonObj)
        			.then(data => populateDeleteEstimationProductsDetailsVResponse(data,row)) 
        			.catch(error => handleError(itemName,error));
			}
		});	
	}else{
		row.remove();
		updateTotalProducts(); 	
	}						
};

function populateDeleteEstimationProductsDetailsVResponse(vResponseObj,row){
    if(vResponseObj.status == "true"){			
		row.remove();
		updateTotalProducts(); 		    		    		   
	}
};

async function getProductListToDatalistTagOption(){
	var jsonObj = JSON.parse("{}");
    jsonObj['ID'] = "all";      	
    let url = "/fsm/getProductDetailsList";
	let itemName= "getProductDetailsList";
    getDataFromServicePoint(url,jsonObj)
        .then(async data => await populateProductListToSelectTagOptionVResponse(data)) 
        .catch(error => handleError(itemName,error));
};

async function populateProductListToSelectTagOptionVResponse(vResponse){
	if(vResponse.status == "true"){
		var dataArray = vResponse.data;	
		var productNameOptionArray = new Array();
		var productIDOptionArray = new Array();
		for(var gg = 0 ; gg < dataArray.length;gg++){
			var productName = dataArray[gg]['Product Name'];
			var productID = dataArray[gg]['Product ID'];
			var productNameOptionTag = `<option>`+productName+`</option>`;
			var productIDOptionTag = `<option>`+productID+`</option>`;
			productNameOptionArray.push(productNameOptionTag);
			productIDOptionArray.push(productIDOptionTag);	
		}
		var productNameOptionArrayHTML = productNameOptionArray.join("");
		var productIDOptionArrayHTML = productIDOptionArray.join("");
		document.getElementById('product_name_list_datalist_tag').innerHTML = productNameOptionArrayHTML;
		document.getElementById('product_id_list_datalist_tag').innerHTML = productIDOptionArrayHTML;
	}
};

function allowEnterKeyOnlyForLoadProductDetails(evt,propertyName,vObj) {
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode;
	
    if (ASCIICode == 13 || ASCIICode == "13"){
		if(propertyName == "ProductName"){
			loadEstimationProductDetailsBasedOnProductName(vObj);	
		}else if(propertyName == "ProductID"){
			loadEstimationProductDetailsBasedOnProductID(vObj);
		}
			
	}        
};

async function loadEstimationProductDetailsBasedOnProductName(vObj){
	
	var jsonObj = JSON.parse("{}");	
	jsonObj['ProductName'] = vObj.value; 	
								
	let url = "/fsm/getProductDetailsBasedOnProductName";
	let itemName= "getProductDetailsBasedOnProductName";
    getDataFromServicePoint(url,jsonObj)
        .then(data => populateProductDetailsRecordInEstimationVResponse(data,vObj)) 
        .catch(error => handleError(itemName,error));     
	       		
};

async function loadEstimationProductDetailsBasedOnProductID(vObj){
	
	var jsonObj = JSON.parse("{}");	
	jsonObj['ProductID'] = vObj.value; 	
								
	let url = "/fsm/getProductDetailsBasedOnProductID";
	let itemName= "getProductDetailsBasedOnProductID";
    getDataFromServicePoint(url,jsonObj)
        .then(data => populateProductDetailsRecordInEstimationVResponse(data,vObj)) 
        .catch(error => handleError(itemName,error));     
	       		
};

function populateProductDetailsRecordInEstimationVResponse(vResponseObj,inputTag){
    if(vResponseObj.status == "true"){				
		var jsonObj = vResponseObj.data;
		var row = inputTag.parentNode.parentNode;		
		row.childNodes[1].childNodes[0].value = jsonObj['Product Name'];
		row.childNodes[2].childNodes[0].value = jsonObj['Product ID'];
		row.childNodes[4].childNodes[0].value = Number(jsonObj['Unit Price']);
		row.childNodes[5].childNodes[0].value = Number(jsonObj['Tax']);
		updateTotalProducts();
	}
};





function toggleItsHaveDiscount(itsHaveDiscount) {
    if (itsHaveDiscount === "Yes") {
        document.getElementById("estimation_table_discount_toggle_container").style.display = "block";		
    } else {
		document.getElementById("estimation_table_discount_toggle_container").style.display = "none";
    }
};


async function convertToOrderStatusUpdateProductsDetails(orderId){
	
	var jsonObj = JSON.parse("{}");	
	jsonObj['ID'] = document.getElementById("fsm_Estimation_detail_uuid_id").value;
	jsonObj['Order ID'] = orderId;	 
		
	let url = "/fsm/convertToOrderStatusUpdateProductsDetails";
	let itemName= "convertToOrderStatusUpdateProductsDetails";
    getDataFromServicePoint(url,jsonObj)
        .then(data => populateConvertToOrderStatusUpdateProductsDetailsVResponse(data)) 
        .catch(error => handleError(itemName,error));     
	       		
};

function populateConvertToOrderStatusUpdateProductsDetailsVResponse(vResponseObj){
	if(vResponseObj.status == "true"){	
		toastr.success("Estimation Convert To Order Successfully", "Completed", {closeButton: !0,tapToDismiss: !1});						        		
	}else{
		toastr.warning("Estimation Wrong ,not convert to Order","Warning", {closeButton: !0,tapToDismiss: !1});
	}
};

async function exportJasperReportInEstimation(){
	try{
		var jsonObj = JSON.parse("{}");	
		jsonObj['estNo'] = document.getElementById("fsm_Estimation_detail_EstimationNO").value;
		jsonObj['reportType'] = document.getElementById("estimation_form_report_type_select").value; 	
			
		let url = "/fsm/exportJasperReportInEstimation";
		let itemName= "exportJasperReportInEstimation";
	    getDataFromServicePoint(url,jsonObj)
	        .then(data => populateExportJasperReportInEstimationVResponse(data,jsonObj['reportType'])) 
	        .catch(error => handleError(itemName,error));	
	}catch(exp){
		toastr.info(exp,"Info", {closeButton: !0,tapToDismiss: !1});
	}
	     
	       		
};

function populateExportJasperReportInEstimationVResponse(vResponseObj,reportType){
	if(vResponseObj.status == "true"){	
			if(reportType == "pdf"){
				toastr.success("PDF Export Successfully ","Completed", {closeButton: !0,tapToDismiss: !1});	
			}else{
				toastr.success("Excel Export Successfully ","Completed", {closeButton: !0,tapToDismiss: !1});
			}
					
			window.open(`/fsm/exportFiles/download/${vResponseObj.fileName}`);			
		}else{
			toastr.info("Wait to Export ,may be wrong parameter","Warning", {closeButton: !0,tapToDismiss: !1});
		}
};


