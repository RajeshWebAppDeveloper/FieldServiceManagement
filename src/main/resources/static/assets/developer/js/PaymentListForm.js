
function getPaymentListForm(){
    var form = `
	<div class="content">
	    <div class="row mb-3">
	        <div class="col-sm-4 col-3">
	            <h2 class="page-title"  onclick="prompt1()">Payments Details</h2>
	        </div>
	        <div class="col-sm-8 col-9 text-end m-b-20">
	            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="getPaymentList('payments_list_form')">
	                <i class="fas fa-refresh"></i> Refresh
	            </a>	           
	        </div>
	    </div>

	    <!-- Filter Form -->
	    <div class="buy-form">
	        <div class="col-12">
	            <div class="card">
	                <div class="card-body">
	                    <form id="payment_list_filter_form" class="row g-3" autocomplete="off">
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_eNo" class="form-label">E No</label>
	                            <input type="text" class="form-control" id="payment_list_filter_eNo" placeholder="Enter E No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_estNo" class="form-label">Est No</label>
	                            <input type="text" class="form-control" id="payment_list_filter_estNo" placeholder="Enter Est No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_paymentNo" class="form-label">Order No</label>
	                            <input type="text" class="form-control" id="payment_list_filter_paymentNo" placeholder="Enter Payment No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_soNo" class="form-label">SO No</label>
	                            <input type="text" class="form-control" id="payment_list_filter_soNo" placeholder="Enter SO No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_ddNo" class="form-label">DD No</label>
	                            <input type="text" class="form-control" id="payment_list_filter_ddNo" placeholder="Enter DD No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_customerName" class="form-label">Customer Name</label>
	                            <input type="text" class="form-control" id="payment_list_filter_customerName" placeholder="Enter Customer Name">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_paymentPerformFromDate" class="form-label">Payment Perform From Date</label>
	                            <input type="datetime-local" class="form-control" id="payment_list_filter_paymentPerformFromDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_paymentPerformToDate" class="form-label">Payment Perform To Date</label>
	                            <input type="datetime-local" class="form-control" id="payment_list_filter_paymentPerformToDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_repAttD" class="form-label">Rep AttD</label>
	                            <input type="text" class="form-control" id="payment_list_filter_repAttD" placeholder="Enter Rep AttD">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_demoPlan" class="form-label">Demo Plan</label>
	                            <select class="form-select" id="fsm_payment_detail_demo_plan" name="Demo Plan">
									<option value=""></option>
									<option value="No">No</option>
	                                <option value="Yes">Yes</option>
	                            </select>
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_demoFromDate" class="form-label">Demo From Date</label>
	                            <input type="datetime-local" class="form-control" id="payment_list_filter_demoFromDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_demoToDate" class="form-label">Demo To Date</label>
	                            <input type="datetime-local" class="form-control" id="payment_list_filter_demoToDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_mobileNo" class="form-label">Mobile No</label>
	                            <input type="text" class="form-control" id="payment_list_filter_mobileNo" placeholder="Enter Mobile No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_itsHaveDiscount" class="form-label">Has Discount</label>	                            
								<select class="form-select" id="payment_list_filter_itsHaveDiscount" name="Has Discount">
									<option value=""></option>
									<option value="No">No</option>
	                                <option value="Yes">Yes</option>
	                            </select>
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_paymentStatus" class="form-label">Payment Status</label>
	                            <select class="form-select" id="payment_list_filter_paymentStatus" name="Payment Status">
	                                <option value="">-- Select Status --</option>
	                                <option>New Payment</option>
	                                <option>Cancel Payment</option>
	                                <option>Payment Confirmed</option>
	                                <option>Payment Received</option>	                                
	                            </select>
	                        </div>
							<div class="col-md-3">
	                            <label for="payment_list_filter_createdBy" class="form-label">Created By</label>
	                            <input type="text" class="form-control" id="payment_list_filter_createdBy" placeholder="Enter Created By">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_createdFromDate" class="form-label">Created From Date</label>
	                            <input type="datetime-local" class="form-control" id="payment_list_filter_createdFromDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="payment_list_filter_createdToDate" class="form-label">Created To Date</label>
	                            <input type="datetime-local" class="form-control" id="payment_list_filter_createdToDate">
	                        </div>
	                        

	                        <!-- Filter and Clear Buttons -->
	                        <div class="col-12 text-end mt-3">
	                            <button type="button" class="btn btn-primary" onclick="filterPayment()">Filter</button>
	                            <button type="button" class="btn btn-secondary" onclick="clearPaymentFilters()">Clear</button>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>

	    <!-- Table -->
	    <div class="buy-form">                          
	        <div class="col-12">
	            <div class="card">                                                                                                              
	                <div class="card-body">
	                    <div class="table-responsive" id="payment_list_table_container" class="dt-container dt-bootstrap5 dt-empty-footer"></div>                                 
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
		`;
    return form;
};


function showPaymentListForm(backMethod){
    try{
		
        hideAllLayer();	
        var containerId = "payment_list_form";         
        document.getElementById(containerId).style.display = "block";
		if(backMethod != "true"){		
			getPaymentList(containerId);	
		}		
		createOptionTagInSelectTag("payment_list_filter_paymentStatus",payment_ApprovalStatusArrayString);
    }catch(exp){
        alert(exp);
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};


async function getPaymentList(containerId){
    var jsonObj = JSON.parse("{}");
    jsonObj['User ID'] = logginerUserId; 
    let url = "/fsm/getPaymentDetailsList";
	let itemName= "getPaymentDetailsList";
    getDataFromServicePoint(url,jsonObj)
        .then(async data => await populatePaymentListVResponse(data,containerId)) 
        .catch(error => handleError(itemName,error));
};


async function populatePaymentListVResponse(vResponse,containerId){		
  
	if(vResponse.status == "true"){		
			var dataArray = vResponse.data;	
			var exportFunction = "exportJasperReportInPaymentInTableRow(this)";	
			var deleteFunction = "deletePaymentDetails(this)";
			selectRecordStr = "";
			var idField = "ID";
			var imageOrStatusKeyJsonObj = {};
			var statusClassMapping = {};
			var selectOptionsMapping = {
				"Payment Status": payment_ApprovalStatusArrayString.split(",")
			};
			var selectOptionsBasedOnChangeFunction = "updatePaymentDetailsInTableRow(this)";
			var tableId = containerId+"_table_id";		
			document.getElementById("payment_list_table_container").innerHTML = await createDataTableWithCheckbox_Delete_DropDown(vResponse, exportFunction,deleteFunction, tableId, selectRecordStr, idField, imageOrStatusKeyJsonObj, statusClassMapping, selectOptionsMapping, selectOptionsBasedOnChangeFunction);
			if(dataArray.length > 0){
				await $("#"+tableId).DataTable({				
				                "searching": true,  
				                "paging": true,     
				                "info": true,       
				                "lengthChange": true,
				                "autoWidth": false,  
								"pageLength": 10, 
				                "columnDefs": [
				                    { "paymentable": false, "targets": -1 }
									,{
						             "targets": [0,1], 
									 "className": "hidden-column" 
						        	} 
				                ]		
				         });						
			}		
				     	 	      	
		}
};



async function filterPayment() {   
    let jsonObj = {};

    jsonObj['eNo'] = document.getElementById("payment_list_filter_eNo").value;
    jsonObj['estNo'] = document.getElementById("payment_list_filter_estNo").value;        
    jsonObj['paymentNo'] = document.getElementById("payment_list_filter_paymentNo").value;
    jsonObj['soNo'] = document.getElementById("payment_list_filter_soNo").value;
    jsonObj['ddNo'] = document.getElementById("payment_list_filter_ddNo").value;
    jsonObj['customerName'] = document.getElementById("payment_list_filter_customerName").value;
	
    const paymentPerformFromDatePayment = document.getElementById("payment_list_filter_paymentPerformFromDate");
    jsonObj['paymentPerformFromDate'] = paymentPerformFromDatePayment.value ? new Date(paymentPerformFromDatePayment.value).toISOString() : null;
	
    const paymentPerformToDatePayment = document.getElementById("payment_list_filter_paymentPerformToDate");
    jsonObj['paymentPerformToDate'] = paymentPerformToDatePayment.value ? new Date(paymentPerformToDatePayment.value).toISOString() : null;
	
    jsonObj['repAttD'] = document.getElementById("payment_list_filter_repAttD").value;
    jsonObj['mobileNo'] = document.getElementById("payment_list_filter_mobileNo").value;
    jsonObj['demoPlan'] = document.getElementById("fsm_payment_detail_demo_plan").value;
	
    const demoFromDatePayment = document.getElementById("payment_list_filter_demoFromDate");
    jsonObj['demoFromDate'] = demoFromDatePayment.value ? new Date(demoFromDatePayment.value).toISOString() : null;
	
    const demoToDatePayment = document.getElementById("payment_list_filter_demoToDate");
    jsonObj['demoToDate'] = demoToDatePayment.value ? new Date(demoToDatePayment.value).toISOString() : null;
	
    jsonObj['itsHaveDiscount'] = document.getElementById("payment_list_filter_itsHaveDiscount").value;
    jsonObj['paymentStatus'] = document.getElementById("payment_list_filter_paymentStatus").value;
	
    const createdFromDateElem = document.getElementById("payment_list_filter_createdFromDate");
    jsonObj['createdFromDate'] = createdFromDateElem.value ? new Date(createdFromDateElem.value).toISOString() : null;
	
    const createdToDateElem = document.getElementById("payment_list_filter_createdToDate");
    jsonObj['createdToDate'] = createdToDateElem.value ? new Date(createdToDateElem.value).toISOString() : null;
	
    jsonObj['createdBy'] = document.getElementById("payment_list_filter_createdBy").value;

    let url = "/fsm/getFilterPaymentDetailsList";
    let itemName = "filterPaymentDetails";

    await getDataFromServicePoint(url, jsonObj)
        .then(async data => await populatePaymentListVResponse(data, "payment_list_form"))
        .catch(error => handleError(itemName, error));
};

function clearPaymentFilters() {
    document.getElementById('payment_list_filter_form').reset();
};



async function exportJasperReportInPaymentInTableRow(vObj){
	try{
		var jsonObj = JSON.parse("{}");	
		jsonObj['estNo'] = vObj.parentNode.parentNode.childNodes[3].innerHTML; 
		jsonObj['reportType'] = vObj.previousSibling.value; 			
		let url = "/fsm/exportJasperReportInPayment";
		let itemName= "exportJasperReportInPayment";
	    getDataFromServicePoint(url,jsonObj)
	        .then(data => populateExportJasperReportInEstimationVResponse(data,jsonObj['reportType'])) 
	        .catch(error => handleError(itemName,error));	
	}catch(exp){
		toastr.info(exp,"Info", {closeButton: !0,tapToDismiss: !1});
	}
	     	       		
};


async function deletePaymentDetails(vObj){
    var jsonObj = JSON.parse("{}");    
    jsonObj = await buildDynamicChexBoxColumnTableJsonObj(vObj);             
        swal({
                title: "Confirmation",
                text: "Do you want to delete ?",
                icon: "warning",
                buttons: true,
                dangerMode: true,
         }).then((confirmation) => {
                if (confirmation) {
                    let url = "/fsm/deletePaymentDetails";
                    let itemName= "deletePaymentDetails";
                    getDataFromServicePoint(url,jsonObj)
                        .then(data => populateDeletePaytmentDetailsVResponse(data)) 
                        .catch(error => handleError(itemName,error));
                }
        }); 
                
};

function populateDeletePaytmentDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){          
        toastr.success("Deleted Successfully","Completed", {closeButton: !0,tapToDismiss: !1});         
        showPaymentListForm('');                                  
    }
};


async function updatePaymentDetailsInTableRow(vObj){	
		
	var jsonObj = JSON.parse("{}");	
	jsonObj['ID'] = vObj.parentNode.parentNode.childNodes[1].innerHTML;	    
	jsonObj['Payment Status'] = vObj.value;	
	jsonObj['Created Date'] = new Date().toISOString();
	jsonObj['Created By'] = logginerUserId;  
	swal({
	        title: "Confirmation",
	        text: "Do you want to Update Status ?",
	        icon: "info",
	        buttons: true,
	        dangerMode: true,
	 }).then((confirmation) => {
	        if (confirmation) {		  		
    			let url = "/fsm/updatePaymentDetailsInTableRow";
				let itemName = "updatePaymentDetailsInTableRow";
    			getDataFromServicePoint(url,jsonObj)
        			.then(async data => await populateUpdatePaymentDetailsInTableRowVResponse(data,jsonObj)) 
        			.catch(error => handleError(itemName,error));
			}
	 });	
};	

async function populateUpdatePaymentDetailsInTableRowVResponse(vResponseObj,jsonObj){
    if(vResponseObj.status == "true"){		
		if(jsonObj['Payment Status'] == "Other"){
			updatePaymentDetailsInTableRowStatusOtherDescription(jsonObj);	
		}else{
			toastr.success("Successfully Updated","Status Changed", {closeButton: !0,tapToDismiss: !1});
			filterPayment();
		}	
		
	}else{
		toastr.warning("Invalid Payment Details","Warning", {closeButton: !0,tapToDismiss: !1});
	}
};

function updatePaymentDetailsInTableRowStatusOtherDescription(jsonObj){
	swal({
	    title: "Update Status",
	    text: "Other Status Description:",
	    content: {
	        element: "input",
	        attributes: {
	            placeholder: "Enter Other Status Description",
	            type: "text",
	        },
	    },
	    buttons: {
	        cancel: "Cancel",
	        confirm: "Submit",
	    },
	}).then((value) => {
	    if (value) {
			jsonObj['Other Status Description'] = value;
			let url = "/fsm/updatePaymentDetailsInTableRowStatusOtherDescription";
			let itemName = "updatePaymentDetailsInTableRowStatusOtherDescription";
			getDataFromServicePoint(url,jsonObj)
    			.then(async data => await populatePaymentDetailsInTableRowStatusOtherDescription(data)) 
    			.catch(error => handleError(itemName,error));

	    } else {	        
			toastr.warning("Invalid Description Information","Warning", {closeButton: !0,tapToDismiss: !1});
	    }
	});
};


async function populatePaymentDetailsInTableRowStatusOtherDescription(vResponseObj){
    if(vResponseObj.status == "true"){				
		toastr.success("Successfully Updated","Status Changed", {closeButton: !0,tapToDismiss: !1});
		filterPayment();		
	}else{
		toastr.warning("Invalid Payment Details","Warning", {closeButton: !0,tapToDismiss: !1});
	}
};
