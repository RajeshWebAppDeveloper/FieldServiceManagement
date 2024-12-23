
function getEstimationListForm(){
    var form = `
	<div class="content">
	    <div class="row mb-3">
	        <div class="col-sm-4 col-3">
	            <h2 class="page-title">Estimations Details</h2>
	        </div>
	        <div class="col-sm-8 col-9 text-end m-b-20">
	            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="getEstimationList('ests_list_form')">
	                <i class="fas fa-refresh"></i> Refresh
	            </a>
	            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="showEstimationEntryForm('add')">
	                <i class="fas fa-plus"></i> Add Estimation
	            </a>
	        </div>
	    </div>

	    <!-- Filter Form -->
	    <div class="buy-form">
	        <div class="col-12">
	            <div class="card">
	                <div class="card-body">
	                    <form id="estimation_list_filter_form" class="row g-3" autocomplete="off">
	                        <div class="col-md-3">
	                            <label for="estimation_list_filter_estNo" class="form-label">Estimation No</label>
	                            <input type="text" class="form-control" id="estimation_list_filter_estNo" placeholder="Enter Estimation No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="estimation_list_filter_customerName" class="form-label">Customer Name</label>
	                            <input type="text" class="form-control" id="estimation_list_filter_customerName" placeholder="Enter Customer Name">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="estimation_list_filter_estimationPerformFromDate" class="form-label">Estimation Perform From Date</label>
	                            <input type="datetime-local" class="form-control" id="estimation_list_filter_estimationPerformFromDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="estimation_list_filter_estimationPerformToDate" class="form-label">Estimation Perform To Date</label>
	                            <input type="datetime-local" class="form-control" id="estimation_list_filter_estimationPerformToDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="estimation_list_filter_repAttD" class="form-label">Rep AttD</label>
	                            <input type="text" class="form-control" id="estimation_list_filter_repAttD" placeholder="Enter Rep AttD">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="estimation_list_filter_repAccount" class="form-label">Rep Account</label>
	                            <input type="text" class="form-control" id="estimation_list_filter_repAccount" placeholder="Enter Rep Account">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="estimation_list_filter_mobileNo" class="form-label">Mobile No</label>
	                            <input type="text" class="form-control" id="estimation_list_filter_mobileNo" placeholder="Enter Mobile No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="estimation_list_filter_itsHaveDiscount" class="form-label">Has Discount</label>	                            
								<select class="form-select" id="estimation_list_filter_itsHaveDiscount" name="Approval Status">
									<option></option>	
									<option>Yes</option>
									<option>No</option>										
								</select>
	                        </div>
	                        <div class="col-md-3">
	                            <label for="estimation_list_filter_estimationStatus" class="form-label">Estimation Status</label>
								<select class="form-select" id="fsm_Estimation_detail_approval_status" name="Approval Status">
									<option>Estimation Enquiry</option>
									<option>Cancel Estimation</option>	
									<option>Convert To Order</option>	
									<option>Not Matured</option>	
								</select>
	                        </div>
	                        <div class="col-md-3">
	                            <label for="estimation_list_filter_createdFromDate" class="form-label">Created From Date</label>
	                            <input type="datetime-local" class="form-control" id="estimation_list_filter_createdFromDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="estimation_list_filter_createdToDate" class="form-label">Created To Date</label>
	                            <input type="datetime-local" class="form-control" id="estimation_list_filter_createdToDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="estimation_list_filter_createdBy" class="form-label">Created By</label>
	                            <input type="text" class="form-control" id="estimation_list_filter_createdBy" placeholder="Enter Created By">
	                        </div>

	                        <!-- Filter and Clear Buttons -->
	                        <div class="col-12 text-end mt-3">
	                            <button type="button" class="btn btn-primary" onclick="filterEstimation()">Filter</button>
	                            <button type="button" class="btn btn-secondary" onclick="clearEstimationFilters()">Clear</button>
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
	                    <div class="table-responsive" id="est_list_table_container" class="dt-container dt-bootstrap5 dt-empty-footer"></div>                                   
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
`;
    return form;
};


function showEstimationListForm(backMethod){
    try{
		
        hideAllLayer();	
        var containerId = "est_list_form";         
        document.getElementById(containerId).style.display = "block";
		if(backMethod != "true"){		
			getEstimationList(containerId);	
		}		
		createOptionTagInSelectTag("fsm_Estimation_detail_approval_status",estimation_ApprovalStatusArrayString);
    }catch(exp){
        alert(exp);
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};


async function getEstimationList(containerId){
    var jsonObj = JSON.parse("{}");
    jsonObj['User ID'] = logginerUserId;   	
    let url = "/fsm/getEstimationDetailsList";
	let itemName= "getEstimationDetailsList";
    getDataFromServicePoint(url,jsonObj)
        .then(async data => await populateEstimationListVResponse(data,containerId)) 
        .catch(error => handleError(itemName,error));
};


async function populateEstimationListVResponse(vResponse,containerId){		

	
	
	if(vResponse.status == "true"){		
				var dataArray = vResponse.data;
				var exportFunction = "exportJasperReportInEstimationInTableRow(this)";		
				var editFunction = "editEstimationDetails(this)";
				var deleteFunction = "deleteEstimationDetails(this)";
				selectRecordStr = "";
				var idField = "ID";
				var imageOrStatusKeyJsonObj = {};
				var statusClassMapping = {};
				var selectOptionsMapping = {
					"Estimation Status": estimation_ApprovalStatusArrayString.split(",")
				};
				
				var selectOptionsBasedOnChangeFunction = "updateEstimationDetailsInTableRow(this)";
				
				var tableId = containerId+"_table_id";		
				document.getElementById("est_list_table_container").innerHTML = await createDataTableWithCheckboxEdit_Delete_DropDown(vResponse,exportFunction, editFunction, deleteFunction, tableId, selectRecordStr, idField, imageOrStatusKeyJsonObj, statusClassMapping, selectOptionsMapping,selectOptionsBasedOnChangeFunction);
				if(dataArray.length > 0){
					await $("#"+tableId).DataTable({				
					                "searching": true,  
					                "paging": true,     
					                "info": true,       
					                "lengthChange": true,
					                "autoWidth": false,  
									"pageLength": 10, 
					                "columnDefs": [
					                    { "orderable": false, "targets": -1 }
										,{
							             "targets": [0,1], 
										 "className": "hidden-column" 
							        	} 
					                ]		
					         });						
				}		
					     	 	      	
			}
};




function clearEstimationFilters() {
    document.getElementById('estimation_list_filter_form').reset();
};

async function filterEstimation() {   
    var jsonObj = {};
    jsonObj['estNo'] = document.getElementById("estimation_list_filter_estNo").value;
    jsonObj['customerName'] = document.getElementById("estimation_list_filter_customerName").value;
    
    var estimationPerformFromDate = document.getElementById("estimation_list_filter_estimationPerformFromDate").value;
    jsonObj['estimationPerformFromDate'] = estimationPerformFromDate ? new Date(estimationPerformFromDate).toISOString() : null;

    var estimationPerformToDate = document.getElementById("estimation_list_filter_estimationPerformToDate").value;
    jsonObj['estimationPerformToDate'] = estimationPerformToDate ? new Date(estimationPerformToDate).toISOString() : null;

    jsonObj['repAttD'] = document.getElementById("estimation_list_filter_repAttD").value;
    jsonObj['repAccount'] = document.getElementById("estimation_list_filter_repAccount").value;
    jsonObj['mobileNo'] = document.getElementById("estimation_list_filter_mobileNo").value;
    jsonObj['itsHaveDiscount'] = document.getElementById("estimation_list_filter_itsHaveDiscount").value;
    jsonObj['estimationStatus'] = document.getElementById("fsm_Estimation_detail_approval_status").value;

    var createdFromDateElem = document.getElementById("estimation_list_filter_createdFromDate").value;
    jsonObj['createdFromDate'] = createdFromDateElem ? new Date(createdFromDateElem).toISOString() : null;
    
    var createdToDateElem = document.getElementById("estimation_list_filter_createdToDate").value;
    jsonObj['createdToDate'] = createdToDateElem ? new Date(createdToDateElem).toISOString() : null;

    jsonObj['createdBy'] = document.getElementById("estimation_list_filter_createdBy").value;

    let url = "/fsm/getFilterEstimationDetailsList";
    await getDataFromServicePoint(url, jsonObj)
        .then(data => populateEstimationListVResponse(data, "est_list_form"))
        .catch(error => handleError("filterEstimation", error));
};




async function updateEstimationDetailsInTableRow(vObj){	
		
	var jsonObj = JSON.parse("{}");	
	jsonObj['ID'] = vObj.parentNode.parentNode.childNodes[1].innerHTML;	    
	jsonObj['Estimation Status'] = vObj.value;	
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
    			let url = "/fsm/updateEstimationDetailsInTableRow";
				let itemName = "updateEstimationDetailsInTableRow";
    			getDataFromServicePoint(url,jsonObj)
        			.then(async data => await populateUpdateEstimationDetailsInTableRowVResponse(data)) 
        			.catch(error => handleError(itemName,error));
			}
	 });	
};

async function populateUpdateEstimationDetailsInTableRowVResponse(vResponseObj){
    if(vResponseObj.status == "true"){			
		toastr.success("Successfully Updated","Status Changed", {closeButton: !0,tapToDismiss: !1});
	}else{
		toastr.warning("Invalid Estimation Details","Warning", {closeButton: !0,tapToDismiss: !1});
	}
};


async function exportJasperReportInEstimationInTableRow(vObj){
	try{
		var jsonObj = JSON.parse("{}");	
		jsonObj['estNo'] = vObj.parentNode.parentNode.childNodes[2].innerHTML; 
		jsonObj['reportType'] = vObj.previousSibling.value; 	
			
		let url = "/fsm/exportJasperReportInEstimation";
		let itemName= "exportJasperReportInEstimation";
	    getDataFromServicePoint(url,jsonObj)
	        .then(data => populateExportJasperReportInEstimationVResponse(data,jsonObj['reportType'])) 
	        .catch(error => handleError(itemName,error));	
	}catch(exp){
		toastr.info(exp,"Info", {closeButton: !0,tapToDismiss: !1});
	}
	     	       		
};


