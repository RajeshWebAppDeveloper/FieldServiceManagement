
function getOrderEntryForm(showType){
    var btnHTML = ``;                                   

    if(showType == "add") {
        
        btnHTML = `
            <div class="row">
                <div class="col-12 col-lg-6 d-flex justify-content-lg-start justify-content-center mb-2 mb-lg-0">
                    <button class="btn btn-light w-100" data-bs-dismiss="modal" onclick="clearOrderDetails()">Clear</button>
                </div>
                <div class="col-12 col-lg-6 d-flex justify-content-lg-end justify-content-center">
                    <button class="btn btn-primary w-100" data-bs-dismiss="modal" onclick="updateOrderDetails()">Save</button>
                </div>
            </div>`;
    } else if(showType == "edit") {     
        btnHTML = `
            <div class="row">
                <div class="col-12 col-lg-4 d-flex justify-content-lg-start justify-content-center mb-2 mb-lg-0">
                    <button class="btn btn-primary w-100" data-bs-dismiss="modal" style="background:#fe3f51;" onclick="deleteOrderDetails('')">Delete</button>
                </div>
                <div class="col-12 col-lg-4 d-flex justify-content-lg-center justify-content-center mb-2 mb-lg-0">
                    <button class="btn btn-light w-100" data-bs-dismiss="modal" onclick="clearOrderDetails()">Clear</button>
                </div>
                <div class="col-12 col-lg-4 d-flex justify-content-lg-end justify-content-center">
                    <button class="btn btn-primary w-100" data-bs-dismiss="modal" onclick="updateOrderDetails()">Save</button>
                </div>
            </div>`;
    }

    var form = `<div class="content">
                            
                    <div class="row align-items-center">
                        <div class="col"></div>
                        <div class="col-auto">                                  
                            <a href="#" class="btn btn-primary btn-rounded float-right" onclick="showOrderListForm('')"><i class="fas fa-arrow-left"></i> Back to Table</a>
                        </div>
                    </div>
                    <div class="row align-items-center">
                        <div class="col">
                            <h2 class="page-title">Order Process</h2>
                        </div>                    
                    </div>
					<div class="row align-items-right" id="order_form_export_element_row">
						<div class="col"></div>
						<div class="text-end">
							<select class="btn btn-light float-right btn-rounded" id="order_form_report_type_select">
								<option>pdf</option>							
								<option>excel</option>
							</select>
                        	<a href="#" class="btn btn-primary float-right btn-rounded" onclick="exportJasperReportInOrder()">Export</a>
						</div>
                    </div>                             
                    <div class="row">
                        <div class="card-box">                        
                                <div class="row">
                                    <!-- Left Column -->
                                    <div class="col-12 col-lg-6">
                                        <div class="mb-3">
                                            <label for="fsm_order_detail_e_no" class="form-label">E.No</label>
                                            <input type="hidden" class="form-control" id="fsm_Order_detail_uuid_id" >
											
                                          <input type="hidden" class="form-control" id="fsm_Order_detail_rep_attd" >
                                            <input type="hidden" class="form-control" id="fsm_Order_detail_delivery_city">  
                                            <input type="hidden" class="form-control" id="fsm_Order_detail_delivery_pin_code" >                                        
                                            <input type="hidden" class="form-control"   
                                             id="fsm_Order_detail_its_have_discount">                                     
                                             <input type="hidden" class="form-control"   
                                             id="fsm_Order_detail_discount_estimate">                                     
                                             <input type="hidden" class="form-control"   
                                             id="fsm_Order_detail_demo_piece_estimate">                                   
                                             <input type="hidden" class="form-control"   
                                             id="fsm_Order_detail_stock_clearance_estimate">                              
                                             <input type="hidden" class="form-control"   
                                             id="fsm_Order_detail_discount_amount">                                            
                                            <input type="text" class="form-control" id="fsm_order_detail_e_no" name="E.No" placeholder="Enter E.No">
                                        </div>

                                        <div class="mb-3">
                                            <label for="fsm_order_detail_est_no" class="form-label">Est No</label>
                                            <input type="text" class="form-control" id="fsm_order_detail_est_no" name="Est No" placeholder="Enter Est No" disabled>
                                        </div>
										<div class="mb-3">
                                            <label for="fsm_Order_detail_d_d_no" class="form-label">D.D No </label>                                            
											<input type="text" class="form-control" id="fsm_Order_detail_d_d_no" name="D.D No" placeholder="Enter D.D.No">
                                        </div>
                                        <div class="mb-3">
                                            <label for="fsm_order_detail_customer_name" class="form-label">Customer Name</label>
                                            <input type="text" class="form-control" id="fsm_order_detail_customer_name" name="Customer Name" placeholder="Enter Customer Name">
                                        </div>

                                        <div class="mb-3">
                                            <label for="fsm_order_detail_customer_city" class="form-label">Customer City</label>
                                            <input type="text" class="form-control" id="fsm_order_detail_customer_city" name="Customer City" placeholder="Enter Customer City">
                                        </div>

                                        <div class="mb-3">
                                            <label for="fsm_order_detail_customer_pin_code" class="form-label">Customer Pin Code</label>
                                            <input type="text" class="form-control" id="fsm_order_detail_customer_pin_code" name="Customer Pin Code" placeholder="Enter Customer Pin Code">
                                        </div>

                                        <div class="mb-3">
                                            <label for="fsm_order_detail_customer_phone" class="form-label">Customer Phone</label>
                                            <input type="tel" class="form-control" id="fsm_order_detail_customer_phone" name="Customer Phone" placeholder="Enter Customer Phone">
                                        </div>

                                        <div class="mb-3">
                                            <label for="fsm_order_detail_customer_email" class="form-label">Customer Email</label>
                                            <input type="email" class="form-control" id="fsm_order_detail_customer_email" name="Customer Email" placeholder="Enter Customer Email">
                                        </div>

                                        <div class="mb-3">
                                            <label for="fsm_order_detail_billing_name" class="form-label">Billing Name</label>
                                            <input type="text" class="form-control" id="fsm_order_detail_billing_name" name="Billing Name" placeholder="Enter Billing Name">
                                        </div>
										<div class="mb-3">
	                                        <label for="fsm_order_detail_billing_address" class="form-label">Billing Address</label>
	                                        <textarea class="form-control" id="fsm_order_detail_billing_address" name="Billing Address" placeholder="Enter Billing Address"></textarea>
	                                    </div>
                                        
                                    </div>

                                    <!-- Right Column -->
                                    <div class="col-12 col-lg-6">
                                        <div class="mb-3">
                                            <label for="fsm_order_detail_order_no" class="form-label">Order No</label>
                                            <input type="text" class="form-control" id="fsm_order_detail_order_no" name="Order No" placeholder="Enter Order No" disabled>
                                        </div>

                                        <div class="mb-3">
                                            <label for="fsm_order_detail_so_no" class="form-label">SO No</label>
                                            <input type="text" class="form-control" id="fsm_order_detail_so_no" name="SO No" placeholder="Enter SO No">
                                        </div>

                                        <div class="mb-3">
                                            <label for="fsm_order_detail_order_process_date" class="form-label">Order Process Date</label>
                                            <input type="date" class="form-control" id="fsm_order_detail_order_process_date" name="Order Process Date">
                                        </div>

                                        <div class="mb-3">
                                            <label for="fsm_order_detail_rep_code" class="form-label">Rep Code</label>
                                            <input type="text" class="form-control" id="fsm_order_detail_rep_code" name="Rep Code" placeholder="Enter Rep Code">
                                        </div>

                                        <div class="mb-3">
                                            <label for="fsm_order_detail_demo_plan" class="form-label">Demo Plan</label>
                                            <select class="form-select" id="fsm_order_detail_demo_plan" name="Demo Plan" onchange="toggleDemoDate(this.value)">
                                                <option value="No">No</option>
                                                <option value="Yes">Yes</option>
                                            </select>
                                        </div>																			

                                        <!-- Conditionally displayed Demo Date -->
                                        <div class="mb-3" id="demo_date_field" style="display: none;">
                                            <label for="fsm_order_detail_demo_date" class="form-label">Demo Date</label>
                                            <input type="date" class="form-control" id="fsm_order_detail_demo_date" name="Demo Date">
                                        </div>

                                        <div class="mb-3">
                                            <label for="fsm_order_detail_warranty" class="form-label">Warranty</label>                                            
											<input type="text" class="form-control" id="fsm_order_detail_warranty" name="Warrenty" />
                                        </div>

                                        <div class="mb-3">
                                            <label for="fsm_order_detail_pan_and_gst" class="form-label">PAN / GST</label>
                                            <input type="text" class="form-control" id="fsm_order_detail_pan_and_gst" name="PAN And GST" placeholder="Enter PAN / GST">
                                        </div>
										<div class="mb-3">
                                            <label for="fsm_order_detail_payment_mode" class="form-label">Payment Method</label>
                                            <select class="form-select" id="fsm_order_detail_payment_mode" name="Payment Method" >
                                                <option value="cash">cash</option>
                                                <option value="neft">neft</option>
												<option value="cheque">cheque</option>
                                            </select>
                                        </div>	
										<div class="mb-3">
                                            <label for="fsm_order_detail_payment_incharges" class="form-label">Payment InCharges</label>
                                            <input type="text" class="form-control" id="fsm_order_detail_payment_incharges" name="Payment Incharges" placeholder="Enter Details">
                                        </div>
										<div class="mb-3">
                                            <label for="fsm_order_detail_payment_term_date" class="form-label">Payment Term Date</label>
                                            <input type="date" class="form-control" id="fsm_order_detail_payment_term_date" name="Payment Incharges" >
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
                                                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#taskModal" onclick="dynamicOrderProductRow()">Create</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <table class="table table-center table-hover datatable">
                                                        <thead class="thead-light">
                                                            <tr>
                                                                <th class="form-label">S.No</th>
																<th class="form-label">Product Type</th>
                                                                <th class="form-label">Product Details</th>
                                                                <th class="form-label">Pdt.sl.code</th>
                                                                <th class="form-label">Qty</th>
                                                                <th class="form-label">Unit Price</th>
                                                                <th class="form-label">Tax %</th>
                                                                <th class="form-label">Total</th>
                                                                <th class="form-label">Delete</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="order_product_tbody_id"></tbody>
                                                        <tbody>
                                                            <tr>
                                                                <td colspan="6"></td>
                                                                <td class="form-label">Total Product:</td>
                                                                <td colspan="2" id="order_total_product_id">0.00</td>
                                                            </tr>
                                                        </tbody>
                                                        <thead class="thead-light">
                                                            <tr>
                                                                <td class="form-label">Delivery Address:</td>
                                                                <td colspan="4">
                                                                    <textarea class="form-control" id="fsm_Order_detail_delivery_address" name="Delivery Address"></textarea>
                                                                </td>
                                                                <td class="form-label">Expected Date:</td>
                                                                <td colspan="3">
                                                                    <input type="date" class="form-control" id="fsm_Order_detail_expected_date" name="Expected Date">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="form-label">Ship Mode Name:</td>
                                                                <td colspan="4">
                                                                    <input type="text" class="form-control" id="fsm_Order_detail_ship_mode_name" name="Ship Mode Name">
                                                                </td>
                                                                <td class="form-label" >Remarks:</td>
                                                                <td colspan="3">
                                                                    <textarea class="form-control" id="fsm_Order_detail_remarks" name="Remarks"></textarea>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="form-label">Total Product Amount:</td>
                                                                <td colspan="4">
                                                                    <input type="text" class="form-control" id="fsm_Order_detail_total_product_amount" name="Total Product Amount" disabled>
                                                                </td>
                                                                <td class="form-label">GST Amount:</td>
                                                                <td colspan="3">
                                                                    <input type="text" class="form-control" id="fsm_Order_detail_gst" name="GST Amount" onkeyup="updateTotalOrderProducts()">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="form-label">Delivery Charges:</td>
                                                                <td colspan="4">
                                                                    <input type="text" class="form-control" id="fsm_Order_detail_delivery_charges" name="Delivery Charges" onkeyup="updateTotalOrderProducts()">
                                                                </td>
                                                                <td class="form-label">Total Amount:</td>
                                                                <td colspan="3">
                                                                    <input type="text" class="form-control" id="fsm_Order_detail_total_amount" name="Total Amount">
                                                                </td>
                                                            </tr>                                                            
                                                            <tr>
                                                                <td colspan="5" class="form-label">Approval Status:</td>
                                                                <td colspan="4">
                                                                    <select class="form-select" id="fsm_Order_detail_approval_status" name="Approval Status" onchange="toggleIfItsPartPay(this.value)">
																		<option>New Order</option>																	
                                                                        <option>Cancel Order</option>
                                                                        <option>Payment Confirmated</option>    
                                                                        <option>Partial Payment</option>    
                                                                    </select>
                                                                </td>              
                                                            </tr>
                                                        </thead>
                                                    </table>
													<div class="table-responsive" id="order_table_part_pay_toggle_container" style="display:none;">
														<table class="table table-center table-hover datatable" >
															<thead>	
																<tr>
	                                                                <td class="form-label">Less Advance:</td>
	                                                                <td colspan="4">
	                                                                    <input type="text" class="form-control" id="fsm_Order_detail_less_advance" name="Less Advance">
	                                                                </td>
	                                                                <td class="form-label">Balance:</td>
	                                                                <td colspan="3">
	                                                                    <input type="text" class="form-control" id="fsm_Order_detail_balance" name="Balance">
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




                                                                    



function showOrderEntryForm(showType){
    try{
        hideAllLayer();
		var containerId = "est_entry_form";
        document.getElementById(containerId).innerHTML = getOrderEntryForm(showType);		
        document.getElementById(containerId).style.display = "block";
		getProductListToDatalistTagOption();
		if(showType != 'edit'){
			document.getElementById("order_form_export_element_row").style.display="none";
			getUUIDForOrderDetails();	
		}else{
			document.getElementById("order_form_export_element_row").style.display="block";
		}		
		createOptionTagInSelectTag("fsm_Order_detail_approval_status",order_ApprovalStatusArrayString);				
    }catch(exp){        
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};

async function getUUIDForOrderDetails(){
	var canContinue	= true;
    var jsonObj = JSON.parse("{}");
	jsonObj['ID'] = "";
			
	if(canContinue){  		
	    let url = "/fsm/generateUUID";
		let itemName = "generateUUID";
	    await getDataFromServicePoint(url,jsonObj)
	        .then(async data => await populateUUIDOrderDetailsVResponse(data)) 
	        .catch(error => handleError(itemName,error));
	}
};

function populateUUIDOrderDetailsVResponse(vResponseObj){	
    if(vResponseObj.status == "true"){		
		document.getElementById("fsm_Order_detail_uuid_id").value = vResponseObj.id; 							        		
	}else{
		toastr.warning("UUID its not Generate","Warning", {closeButton: !0,tapToDismiss: !1});
	}
};

/*****************************************Update *********************************************** */
async function updateOrderDetails(){    
    var canContinue = true;

    var jsonObj = JSON.parse("{}"); 

	jsonObj['ID'] = document.getElementById("fsm_Order_detail_uuid_id").value;
	jsonObj['D.D.No'] = document.getElementById("fsm_Order_detail_d_d_no").value;
	
	jsonObj['Delivery City'] = document.getElementById("fsm_Order_detail_delivery_city").value;
	jsonObj['Delivery Pin Code'] = document.getElementById("fsm_Order_detail_delivery_pin_code").value;
	jsonObj['Its Have Discount'] = document.getElementById("fsm_Order_detail_its_have_discount").value;
	jsonObj['Discount Estimate'] = document.getElementById("fsm_Order_detail_discount_estimate").value;
	jsonObj['Demo Piece Estimate'] = document.getElementById("fsm_Order_detail_demo_piece_estimate").value;
	jsonObj['Stock Clearance Estimate'] = document.getElementById("fsm_Order_detail_stock_clearance_estimate").value;
	jsonObj['Discount Amount'] = document.getElementById("fsm_Order_detail_discount_amount").value;
	jsonObj['E.No'] = document.getElementById("fsm_order_detail_e_no").value;
	jsonObj['Est No'] = document.getElementById("fsm_order_detail_est_no").value;
	jsonObj['Customer Name'] = document.getElementById("fsm_order_detail_customer_name").value;
	jsonObj['Customer City'] = document.getElementById("fsm_order_detail_customer_city").value;
	jsonObj['Customer Pin Code'] = document.getElementById("fsm_order_detail_customer_pin_code").value;
	jsonObj['Customer Phone'] = document.getElementById("fsm_order_detail_customer_phone").value;
	jsonObj['Customer Email'] = document.getElementById("fsm_order_detail_customer_email").value;
	jsonObj['Billing Name'] = document.getElementById("fsm_order_detail_billing_name").value;
	jsonObj['Delivery Address'] = document.getElementById("fsm_Order_detail_delivery_address").value;
	jsonObj['Billing Address'] = document.getElementById("fsm_order_detail_billing_address").value;
	jsonObj['Order No'] = document.getElementById("fsm_order_detail_order_no").value;
	jsonObj['SO No'] = document.getElementById("fsm_order_detail_so_no").value;
	jsonObj['Order Process Date'] = document.getElementById("fsm_order_detail_order_process_date").value;
	jsonObj['Rep Code'] = document.getElementById("fsm_order_detail_rep_code").value;
	jsonObj['Demo Plan'] = document.getElementById("fsm_order_detail_demo_plan").value;
	jsonObj['Demo Date'] = document.getElementById("fsm_order_detail_demo_date").value;
	jsonObj['Warranty'] = document.getElementById("fsm_order_detail_warranty").value;
	jsonObj['PAN / GST'] = document.getElementById("fsm_order_detail_pan_and_gst").value;
	jsonObj['Total Product Amount'] = document.getElementById("order_total_product_id").innerHTML;
	jsonObj['Remarks'] = document.getElementById("fsm_Order_detail_remarks").value;
	jsonObj['Ship Mode Name'] = document.getElementById("fsm_Order_detail_ship_mode_name").value;
	jsonObj['Expected Date'] = document.getElementById("fsm_Order_detail_expected_date").value;
	jsonObj['GST'] = document.getElementById("fsm_Order_detail_gst").value;
	jsonObj['Delivery Charges'] = document.getElementById("fsm_Order_detail_delivery_charges").value;
	jsonObj['Total Amount'] = document.getElementById("fsm_Order_detail_total_amount").value;
	jsonObj['Less Advance'] = document.getElementById("fsm_Order_detail_less_advance").value;
	jsonObj['Balance'] = document.getElementById("fsm_Order_detail_balance").value;
	jsonObj['Order Status'] = document.getElementById("fsm_Order_detail_approval_status").value;
	jsonObj['Payment Mode'] = document.getElementById("fsm_order_detail_payment_mode").value;
	jsonObj['Created Date'] = new Date().toISOString();
	jsonObj['Created By'] = logginerUserId;  
	jsonObj['Payment Charges'] = document.getElementById("fsm_order_detail_payment_incharges").value;
	jsonObj['Payment Term Date'] = document.getElementById("fsm_order_detail_payment_term_date").value;    
           
    /*var inputNames = "Order"; 
    var inputTypes = "text";
    var inputElementIds = "fsm_Order_detail_OrderProcessDate";
    canContinue ? canContinue = await checkValidationDynamicInputRow(inputNames,inputTypes,inputElementIds): canContinue = false;*/
    if(canContinue){        
        let url = "/fsm/updateOrderDetails";
        let itemName = "updateOrderDetails";
        await getDataFromServicePoint(url,jsonObj)
            .then(async data => await populateOrderDetailsVResponse(data)) 
            .catch(error => handleError(itemName,error));
    }
};

async function populateOrderDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){  
        updateOrderProductsDetails();                                           
    }else{
        toastr.warning("Invalid Order Details","Warning", {closeButton: !0,tapToDismiss: !1});
    }
};

/*****************************************Clear *********************************************** */
function clearOrderDetails(){    
	document.getElementById("fsm_Order_detail_uuid_id").value = "";
	document.getElementById("fsm_Order_detail_d_d_no").value = "";
	
	document.getElementById("fsm_Order_detail_delivery_city").value = "";
	document.getElementById("fsm_Order_detail_delivery_pin_code").value = "";
	document.getElementById("fsm_Order_detail_its_have_discount").value = "";
	document.getElementById("fsm_Order_detail_discount_estimate").value = "";
	document.getElementById("fsm_Order_detail_demo_piece_estimate").value = "";
	document.getElementById("fsm_Order_detail_stock_clearance_estimate").value = "";
	document.getElementById("fsm_Order_detail_discount_amount").value = "";
	document.getElementById("fsm_order_detail_e_no").value = "";
	document.getElementById("fsm_order_detail_est_no").value = "";
	document.getElementById("fsm_order_detail_customer_name").value = "";
	document.getElementById("fsm_order_detail_customer_city").value = "";
	document.getElementById("fsm_order_detail_customer_pin_code").value = "";
	document.getElementById("fsm_order_detail_customer_phone").value = "";
	document.getElementById("fsm_order_detail_customer_email").value = "";
	document.getElementById("fsm_order_detail_billing_name").value = "";
	document.getElementById("fsm_order_detail_billing_address").value = "";
	document.getElementById("fsm_order_detail_order_no").value = "";
	document.getElementById("fsm_order_detail_so_no").value = "";
	document.getElementById("fsm_order_detail_order_process_date").value = "";
	document.getElementById("fsm_order_detail_rep_code").value = "";
	document.getElementById("fsm_order_detail_demo_plan").value = "";
	document.getElementById("fsm_order_detail_demo_date").value = "";
	document.getElementById("fsm_order_detail_warranty").value = "";
	document.getElementById("fsm_order_detail_pan_and_gst").value = "";
	document.getElementById("order_total_product_id").innerHTML = "";  // Assuming you want to clear this text
	document.getElementById("fsm_Order_detail_remarks").value = "";
	document.getElementById("fsm_Order_detail_ship_mode_name").value = "";
	document.getElementById("fsm_Order_detail_expected_date").value = "";
	document.getElementById("fsm_Order_detail_gst").value = "";
	document.getElementById("fsm_Order_detail_delivery_charges").value = "";
	document.getElementById("fsm_Order_detail_total_amount").value = "";
	document.getElementById("fsm_Order_detail_less_advance").value = "";
	document.getElementById("fsm_Order_detail_balance").value = "";
	document.getElementById("fsm_Order_detail_approval_status").value = "";
	document.getElementById("fsm_order_detail_payment_mode").value = "";
	document.getElementById("fsm_order_detail_payment_incharges").value = "";
	document.getElementById("fsm_order_detail_payment_term_date").value = "";
    
    document.getElementById('order_product_tbody_id').innerHTML = "";         
};
/*****************************************Edit *********************************************** */


async function editOrderDetails(vObj){
    
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
                showOrderEntryForm('edit');
                            
                let url = "/fsm/editOrderDetails";
                let itemName= "editOrderDetails";
                getDataFromServicePoint(url,jsonObj)
                    .then(data => populateEditOrderDetailsVResponse(data)) 
                    .catch(error => handleError(itemName,error));     
            } 
     });                
};

function populateEditOrderDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){              
        var jsonObj = vResponseObj.data;                        
		document.getElementById("fsm_Order_detail_uuid_id").value = jsonObj['ID'];
		document.getElementById("fsm_Order_detail_delivery_city").value = jsonObj['Delivery City'];
		document.getElementById("fsm_Order_detail_delivery_pin_code").value = jsonObj['Delivery Pin Code'];
		document.getElementById("fsm_Order_detail_its_have_discount").value = jsonObj['Its Have Discount'];
		document.getElementById("fsm_Order_detail_discount_estimate").value = jsonObj['Discount Estimate'];
		document.getElementById("fsm_Order_detail_demo_piece_estimate").value = jsonObj['Demo Piece Estimate'];
		document.getElementById("fsm_Order_detail_stock_clearance_estimate").value = jsonObj['Stock Clearance Estimate'];
		document.getElementById("fsm_Order_detail_discount_amount").value = jsonObj['Discount Amount'];
		document.getElementById("fsm_order_detail_e_no").value = jsonObj['E.No'];
		document.getElementById("fsm_order_detail_est_no").value = jsonObj['Est No'];
		document.getElementById("fsm_order_detail_customer_name").value = jsonObj['Customer Name'];
		document.getElementById("fsm_order_detail_customer_city").value = jsonObj['Customer City'];
		document.getElementById("fsm_order_detail_customer_pin_code").value = jsonObj['Customer Pin Code'];
		document.getElementById("fsm_order_detail_customer_phone").value = jsonObj['Customer Phone'];
		document.getElementById("fsm_order_detail_customer_email").value = jsonObj['Customer Email'];
		document.getElementById("fsm_order_detail_billing_name").value = jsonObj['Billing Name'];
		document.getElementById("fsm_order_detail_billing_address").value = jsonObj['Billing Address'];
		document.getElementById("fsm_Order_detail_delivery_address").value = jsonObj['Delivery Address'] ;
		document.getElementById("fsm_order_detail_order_no").value = jsonObj['Order No'];
		document.getElementById("fsm_order_detail_so_no").value = jsonObj['SO No'];
		document.getElementById("fsm_order_detail_order_process_date").value = jsonObj['Order Process Date'];
		document.getElementById("fsm_order_detail_rep_code").value = jsonObj['Rep Code'];
		document.getElementById("fsm_order_detail_demo_plan").value = jsonObj['Demo Plan'];		
		document.getElementById("fsm_order_detail_demo_date").value = jsonObj['Demo Date'];
		document.getElementById("fsm_order_detail_warranty").value = jsonObj['Warranty'];
		document.getElementById("fsm_order_detail_pan_and_gst").value = jsonObj['PAN / GST'];
		document.getElementById("order_total_product_id").innerHTML = jsonObj['Total Product Amount'];
		document.getElementById("fsm_Order_detail_total_product_amount").value = jsonObj['Total Product Amount'];
		document.getElementById("fsm_Order_detail_remarks").value = jsonObj['Remarks'];
		document.getElementById("fsm_Order_detail_ship_mode_name").value = jsonObj['Ship Mode Name'];
		document.getElementById("fsm_Order_detail_expected_date").value = jsonObj['Expected Date'];
		document.getElementById("fsm_Order_detail_gst").value = jsonObj['GST'];
		document.getElementById("fsm_Order_detail_delivery_charges").value = jsonObj['Delivery Charges'];
		document.getElementById("fsm_Order_detail_total_amount").value = jsonObj['Total Amount'];
		document.getElementById("fsm_Order_detail_less_advance").value = jsonObj['Less Advance'];
		document.getElementById("fsm_Order_detail_balance").value = jsonObj['Balance'];
		document.getElementById("fsm_Order_detail_approval_status").value = jsonObj['Order Status'];
		toggleIfItsPartPay(jsonObj['Order Status']);
		document.getElementById("fsm_order_detail_payment_mode").value = jsonObj['Payment Mode'] ;
		document.getElementById("fsm_order_detail_payment_incharges").value = jsonObj['Payment Charges'];
		document.getElementById("fsm_order_detail_payment_term_date").value = jsonObj['Payment Term Date'];
		
  		var productsJsonObj = vResponseObj.ordProductData;
        loadOrderProductRowsFromJson(productsJsonObj);  
		toggleDemoDate(jsonObj['Demo Plan']);                
    }
};
/*****************************************Delete *********************************************** */
async function deleteOrderDetails(vObj){
    var jsonObj = JSON.parse("{}");
    if(vObj == ""){
        jsonObj['ID'] = document.getElementById("fsm_Order_detail_uuid_id").value;                                 
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
                    let url = "/fsm/deleteOrderDetails";
                    let itemName= "deleteOrderDetails";
                    getDataFromServicePoint(url,jsonObj)
                        .then(data => populateDeleteOrderDetailsVResponse(data)) 
                        .catch(error => handleError(itemName,error));
                }
        }); 
                
};

function populateDeleteOrderDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){          
        toastr.success("Deleted Successfully","Completed", {closeButton: !0,tapToDismiss: !1});         
        showOrderListForm('');                                  
    }
};

 

function dynamicOrderProductRow() {
    const tbody = document.getElementById("order_product_tbody_id");   
    var newOrderProductCounter = tbody.childNodes.length+1;
    const newRow = document.createElement("tr");
	var options = ``;
			
    const orderId = document.getElementById("fsm_Order_detail_uuid_id").value;
    newRow.innerHTML = `<td><input 
            type="hidden" name="ID" class="form-control" placeholder="UUID" /><input 
            type="hidden" name="ReferenceID" class="form-control" value="${orderId}" />
            ${newOrderProductCounter}</td><td><select class="form-select" name="ProductType"><option></option><option>Stock</option><option>Sales</option></select></td><td><input 
            type="text" name="ProductDetails" list="product_name_list_datalist_tag" class="form-control" placeholder="Product Name" onkeyPress="allowEnterKeyOnlyForLoadOrderProductDetails(event,'ProductName',this)" /></td><td><input 
            type="text" name="ProductCode" list="product_id_list_datalist_tag"  class="form-control" placeholder="Pdt.sl.code" onkeyPress="allowEnterKeyOnlyForLoadOrderProductDetails(event,'ProductID',this)" /></td><td><input 
            type="number" name="ProductQty" class="form-control" placeholder="0.00" min="0" onkeypress="onlyNumberKey(event)" onkeyup="calculateRowOrderTotal(this)" /></td><td><input 
            type="number" name="ProductUnitPrice" class="form-control" placeholder="0.00" min="0" onkeypress="onlyNumberKey(event)" onkeyup="calculateRowOrderTotal(this)" /></td><td><input 
            type="number" name="ProductTax" class="form-control" placeholder="0.00" min="0" onkeypress="onlyNumberKey(event)" onkeyup="calculateRowOrderTotal(this)" /></td><td><input 
            type="number" name="ProductTotal" class="form-control" placeholder="0.00" onkeypress="onlyNumberKey(event)" readonly /></td><td><button 
            type="button" class="btn btn-danger" onclick="deleteOrderProductsDetails(this)">Delete</button></td>`;

    tbody.appendChild(newRow);

    newOrderProductCounter++;
    updateTotalOrderProducts();
};

function calculateRowOrderTotal(inputElement) {
    const row = inputElement.closest("tr");
    const qty = parseFloat(row.querySelector('input[name="ProductQty"]').value) || 0;
    const unitPrice = parseFloat(row.querySelector('input[name="ProductUnitPrice"]').value) || 0;
    const tax = parseFloat(row.querySelector('input[name="ProductTax"]').value) || 0;
	const taxPercent = (qty * unitPrice) * tax/100;
    const total = (qty * unitPrice) + taxPercent;

    row.querySelector('input[name="ProductTotal"]').value = total.toFixed(2);
    updateTotalOrderProducts();
};


function updateTotalOrderProducts() {
    let totalProductAmount = 0;
    const rows = document.querySelectorAll("#order_product_tbody_id tr");

    rows.forEach((row) => {
        const totalInput = row.querySelector('input[name="ProductTotal"]');
        if (totalInput) {
            totalProductAmount += parseFloat(totalInput.value) || 0;
        }
    });

    document.getElementById("order_total_product_id").innerHTML= totalProductAmount.toFixed(2);
	document.getElementById("fsm_Order_detail_total_product_amount").value = totalProductAmount.toFixed(2);
	var orderGstAmount = Number(document.getElementById("fsm_Order_detail_gst").value || "0.00");
	var deliveryAmount = Number(document.getElementById("fsm_Order_detail_delivery_charges").value || "0.00");
	document.getElementById("fsm_Order_detail_total_amount").value = totalProductAmount.toFixed(2) + orderGstAmount + deliveryAmount;
};


function loadOrderProductRowsFromJson(jsonData) {
    const tbody = document.getElementById("order_product_tbody_id");
    tbody.innerHTML = ""; 
    let productCounter = 1;

    jsonData.forEach((item) => {
        const newRow = document.createElement("tr");
		var options = `<option></option><option>Stock</option><option>Sales</option>`;
		if(item['Product Type'] == "stock"){
			options = `<option></option><option selected>Stock</option><option>Sales</option>`;
		}else if(item['Product Type'] == "sales"){
			options = `<option></option><option>Stock</option><option selected>Sales</option>`;
		}
        newRow.innerHTML = `<td><input 
                type="hidden" name="ID" class="form-control" value="${item['ID']}" placeholder="ID" /><input 
                type="hidden" name="ReferenceID" class="form-control" value="${item['Reference ID']}" placeholder="Reference ID" />
                ${productCounter}</td><td><select name="ProductType" class="form-select">${options}</select></td><td><input 
                type="text" name="ProductDetails" class="form-control" list="product_name_list_datalist_tag" value="${item['Product Details']}" placeholder="Product Details" onkeyPress="allowEnterKeyOnlyForLoadOrderProductDetails(event,'ProductName',this)" /></td><td><input 
                type="text" name="ProductCode" class="form-control" list="product_id_list_datalist_tag" value="${item['Product Code'] || ''}" placeholder="Pdt.sl.code" onkeyPress="allowEnterKeyOnlyForLoadOrderProductDetails(event,'ProductId',this)"/></td><td><input 
                type="number" name="ProductQty" class="form-control" value="${item['Qty'] || 0}" placeholder="Qty" min="0" onkeyup="calculateRowOrderTotal(this)" /></td><td><input 
                type="number" name="ProductUnitPrice" class="form-control" value="${item['Unit Price'] || 0}" placeholder="Unit Price" min="0" onkeyup="calculateRowOrderTotal(this)" /></td><td><input 
                type="number" name="ProductTax" class="form-control" value="${item['Tax'] || 0}" placeholder="Tax" min="0" onkeyup="calculateRowOrderTotal(this)" /></td><td><input type="number" name="ProductTotal" class="form-control" value="${item['Total'] || 0}" placeholder="Total" readonly /></td><td><button 
                type="button" class="btn btn-danger" onclick="deleteOrderProductsDetails(this)">Delete</button></td>`;

        tbody.appendChild(newRow);
        productCounter++;
    });

  
    updateTotalOrderProducts();
};


async function updateOrderProductsDetails() {
    const rows = document.querySelectorAll('#order_product_tbody_id tr');
    const ProductsArray = [];

    rows.forEach((row, index) => {
        const id = row.querySelector('input[name="ID"]').value;
        const referenceId = row.querySelector('input[name="ReferenceID"]').value;       
        const ProductsDetails = row.querySelector('input[name="ProductDetails"]').value;
		const ProductType = row.querySelector('select[name="ProductType"]').value;
        const ProductsCode = row.querySelector('input[name="ProductCode"]').value;
        const ProductsQty = row.querySelector('input[name="ProductQty"]').value;
        const ProductsUnitPrice = row.querySelector('input[name="ProductUnitPrice"]').value;
        const ProductsTax = row.querySelector('input[name="ProductTax"]').value;
        const ProductsTotal = row.querySelector('input[name="ProductTotal"]').value;

        const ProductData = {
            "ID": id,
            "Reference ID":referenceId,
			"Product Type": ProductType,
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
        const response = await fetch('/fsm/updateOrderProductsDetails', {
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
                showOrderListForm('');      
            } else {
                toastr.warning("Invalid Order Details", "Warning", {closeButton: !0,tapToDismiss: !1});
            }
        } else {
            console.error('Error submitting Products:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
};





async function deleteOrderProductsDetails(vObj){
    var row = vObj.parentNode.parentNode;
    var id = vObj.parentNode.parentNode.childNodes[0].childNodes[0].value;
    if( id!= ""){
        var jsonObj = JSON.parse("{}"); 
        jsonObj['orderProductId'] = id;
		jsonObj['orderId'] = document.getElementById("fsm_Order_detail_uuid_id").value; 
            
        swal({
                title: "Confirmation",
                text: "Do you want to delete ?",
                icon: "warning",
                buttons: true,
                dangerMode: true,
         }).then((confirmation) => {
            if (confirmation) {
                let url = "/fsm/deleteOrderProductsDetails";
                let itemName= "deleteOrderProductsDetails";
                getDataFromServicePoint(url,jsonObj)
                    .then(data => populateDeleteOrderProductsDetailsVResponse(data,row)) 
                    .catch(error => handleError(itemName,error));
            }
        }); 
    }else{
        row.remove();
        updateTotalOrderProducts();  
    }                       
};

function populateDeleteOrderProductsDetailsVResponse(vResponseObj,row){
    if(vResponseObj.status == "true"){          
        row.remove();
        updateTotalOrderProducts();                                 
    }
};

async function getOrderProductListToDatalistTagOption(){
    var jsonObj = JSON.parse("{}");
    jsonObj['ID'] = "all";          
    let url = "/fsm/getProductDetailsList";
    let itemName= "getProductDetailsList";
    getDataFromServicePoint(url,jsonObj)
        .then(async data => await populateOrderProductListToSelectTagOptionVResponse(data)) 
        .catch(error => handleError(itemName,error));
};

async function populateOrderProductListToSelectTagOptionVResponse(vResponse){
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

function allowEnterKeyOnlyForLoadOrderProductDetails(evt,propertyName,vObj) {
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode;
    
    if (ASCIICode == 13 || ASCIICode == "13"){
        if(propertyName == "ProductName"){
            loadOrderProductDetailsBasedOnProductName(vObj);    
        }else if(propertyName == "ProductID"){
            loadOrderProductDetailsBasedOnProductID(vObj);
        }
            
    }        
};

async function loadOrderProductDetailsBasedOnProductName(vObj){
    
    var jsonObj = JSON.parse("{}"); 
    jsonObj['ProductName'] = vObj.value;    
                                
    let url = "/fsm/getProductDetailsBasedOnProductName";
    let itemName= "getProductDetailsBasedOnProductName";
    getDataFromServicePoint(url,jsonObj)
        .then(data => populateProductDetailsRecordInOrderVResponse(data,vObj)) 
        .catch(error => handleError(itemName,error));     
                
};

async function loadOrderProductDetailsBasedOnProductID(vObj){
    
    var jsonObj = JSON.parse("{}"); 
    jsonObj['ProductID'] = vObj.value;  
                                
    let url = "/fsm/getProductDetailsBasedOnProductID";
    let itemName= "getProductDetailsBasedOnProductID";
    getDataFromServicePoint(url,jsonObj)
        .then(data => populateProductDetailsRecordInOrderVResponse(data,vObj)) 
        .catch(error => handleError(itemName,error));     
                
};

function populateProductDetailsRecordInOrderVResponse(vResponseObj,inputTag){
    if(vResponseObj.status == "true"){              
        var jsonObj = vResponseObj.data;
        var row = inputTag.parentNode.parentNode;       
        row.childNodes[2].childNodes[0].value = jsonObj['Product Name'];
        row.childNodes[3].childNodes[0].value = jsonObj['Product ID'];
        row.childNodes[5].childNodes[0].value = Number(jsonObj['Unit Price']);
        row.childNodes[6].childNodes[0].value = Number(jsonObj['Tax']);
		updateTotalOrderProducts();
    }
};



function toggleDemoDate(demoPlan) {    
    const demoDateField = document.getElementById("demo_date_field");
    if (demoPlan === "Yes") {
        demoDateField.style.display = "block";
    } else {
        demoDateField.style.display = "none";
    }
};



async function exportJasperReportInOrder(){
	
	var jsonObj = JSON.parse("{}");	
	jsonObj['estNo'] = document.getElementById("fsm_order_detail_est_no").value;
	jsonObj['reportType'] = document.getElementById("order_form_report_type_select").value; 	
		
	let url = "/fsm/exportJasperReportInOrder";
	let itemName= "exportJasperReportInOrder";
    getDataFromServicePoint(url,jsonObj)
        .then(data => populateExportJasperReportInOrderVResponse(data,jsonObj['reportType'])) 
        .catch(error => handleError(itemName,error));     
	       		
};

function populateExportJasperReportInOrderVResponse(vResponseObj,reportType){
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

function toggleIfItsPartPay(status) {
    if (status === "Partial Payment") {
        document.getElementById("order_table_part_pay_toggle_container").style.display = "block";		
    } else {
		document.getElementById("order_table_part_pay_toggle_container").style.display = "none";
    }
};
