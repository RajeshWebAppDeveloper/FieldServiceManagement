
function getDarListForm(){
    var form = `
			<div class="content">
			    <div class="row mb-3">
			        <div class="col-sm-4 col-3">
			            <h2 class="page-title">Dars Details</h2>
			        </div>
			        <div class="col-sm-8 col-9 text-end m-b-20">
			            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="getDarList('dars_list_form')">
			                <i class="fas fa-refresh"></i> Refresh
			            </a>
			            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="showDarEntryForm('add')">
			                <i class="fas fa-plus"></i> Add Dar
			            </a>
			        </div>
			    </div>
		
			    <!-- Filter Form -->
			    <div class="buy-form">
			        <div class="col-12">
			            <div class="card">
			                <div class="card-body">
			                    <form id="dar_list_filter_form" class="row g-3" autocomplete="off">
			                        <div class="col-md-3">
			                            <label for="dar_list_filter_darNo" class="form-label">Dar No</label>
			                            <input type="text" class="form-control" id="dar_list_filter_darNo" placeholder="Enter Dar No">
			                        </div>
			                        <div class="col-md-3">
			                            <label for="dar_list_filter_darPerformFromDate" class="form-label">Dar Perform From Date</label>
			                            <input type="datetime-local" class="form-control" id="dar_list_filter_darPerformFromDate">
			                        </div>
			                        <div class="col-md-3">
			                            <label for="dar_list_filter_darPerformToDate" class="form-label">Dar Perform To Date</label>
			                            <input type="datetime-local" class="form-control" id="dar_list_filter_darPerformToDate">
			                        </div>
			                        <div class="col-md-3">
			                            <label for="dar_list_filter_clientName" class="form-label">Client Name</label>
			                            <input type="text" class="form-control" id="dar_list_filter_clientName" placeholder="Enter Client Name">
			                        </div>
			                        <div class="col-md-3">
			                            <label for="dar_list_filter_clientMobileNo" class="form-label">Client Mobile No</label>
			                            <input type="text" class="form-control" id="dar_list_filter_clientMobileNo" placeholder="Enter Client Mobile No">
			                        </div>
									<div class="col-md-3">
									    <label for="dar_list_filter_statusToVisit" class="form-label">Status to Visit</label>
									    <select class="form-select" id="dar_list_filter_statusToVisit" name="statusinvisit">
									        <option value="">-- Select Status --</option>
									        <option>Demo Success</option>
									        <option>Product Delivered</option>									        
									    </select>
									</div>

			                        <div class="col-md-3">
			                            <label for="dar_list_filter_createdFromDate" class="form-label">Created From Date</label>
			                            <input type="datetime-local" class="form-control" id="dar_list_filter_createdFromDate">
			                        </div>
			                        <div class="col-md-3">
			                            <label for="dar_list_filter_createdToDate" class="form-label">Created To Date</label>
			                            <input type="datetime-local" class="form-control" id="dar_list_filter_createdToDate">
			                        </div>
			                        <div class="col-md-3">
			                            <label for="dar_list_filter_createdBy" class="form-label">Created By</label>
			                            <input type="text" class="form-control" id="dar_list_filter_createdBy" placeholder="Enter Created By">
			                        </div>
		
			                        <!-- Filter Buttons -->
			                        <div class="col-12 text-end mt-3">
										<select class="btn btn-light float-right btn-rounded" id="dar_form_report_type_select">
											<option>pdf</option>							
											<option>excel</option>
										</select>
										<button type="button" class="btn btn-primary" onclick="exportDar()">Export Report</button>			                            
			                            <button type="button" class="btn btn-secondary" onclick="clearDarFilters()">Clear</button>
										<button type="button" class="btn btn-primary" onclick="filterDar()">Filter</button>
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
			                    <div class="table-responsive" id="dar_list_table_container" class="dt-container dt-bootstrap5 dt-empty-footer"></div>                                   
			                </div>
			            </div>
			        </div>
			    </div>
			</div>

	
			
			`;
    return form;
};


function showDarListForm(backMethod){
    try{
		
        hideAllLayer();	
        var containerId = "dar_list_form";         
        document.getElementById(containerId).style.display = "block";
		if(backMethod != "true"){		
			getDarList(containerId);	
		}		
							
		createOptionTagInSelectTag("dar_list_filter_statusToVisit",dar_StatusToVisitArrayString);		
    }catch(exp){
        alert(exp);
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};


async function getDarList(containerId){
    var jsonObj = JSON.parse("{}");
    jsonObj['User ID'] = logginerUserId;      	
    let url = "/fsm/getDarDetailsList";
	let itemName= "getDarDetailsList";
    getDataFromServicePoint(url,jsonObj)
        .then(async data => await populateDarListVResponse(data,containerId)) 
        .catch(error => handleError(itemName,error));
};


async function populateDarListVResponse(vResponse,containerId){		
   
	if(vResponse.status == "true"){		
			var dataArray = vResponse.data;		
			var editFunction = "editDarDetails(this)";
			var deleteFunction = "deleteDarDetails(this)";
			selectRecordStr = "";
			var idField = "ID";
			var imageOrStatusKeyJsonObj = {		
				status:"Status To Visit"
			};
			var statusClassMapping = {
				"Demo Success":"badge badge-subtle-info"
				,"Product Delivered":"badge badge-subtle-success"
			};
			var tableId = containerId+"_table_id";		
			document.getElementById("dar_list_table_container").innerHTML = await createDataTableWithCheckboxEditAndDelete(vResponse, editFunction, deleteFunction, tableId, selectRecordStr, idField, imageOrStatusKeyJsonObj,statusClassMapping);
			
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




function clearDarFilters() {
    document.getElementById('dar_list_filter_form').reset();
};
	

async function filterDar() {   
    let canContinue = true;
    let jsonObj = {}; // Initialize as an empty object

    // Collect values from input fields and add them to jsonObj with matching keys
    jsonObj['darNo'] = document.getElementById("dar_list_filter_darNo").value;


    // Retrieve and format dates
    const darPerformFromDateElem = document.getElementById("dar_list_filter_darPerformFromDate");
    const darPerformToDateElem = document.getElementById("dar_list_filter_darPerformToDate");
    if (darPerformFromDateElem.value) {
        jsonObj['darPerformFromDate'] = new Date(darPerformFromDateElem.value).toISOString();
    }
    if (darPerformToDateElem.value) {
        jsonObj['darPerformToDate'] = new Date(darPerformToDateElem.value).toISOString();
    }

    jsonObj['clientName'] = document.getElementById("dar_list_filter_clientName").value;
    jsonObj['clientMobileNo'] = document.getElementById("dar_list_filter_clientMobileNo").value;
    jsonObj['statusToVisit'] = document.getElementById("dar_list_filter_statusToVisit").value;

    const createdFromDateElem = document.getElementById("dar_list_filter_createdFromDate");
    const createdToDateElem = document.getElementById("dar_list_filter_createdToDate");
    if (createdFromDateElem.value) {
        jsonObj['createdFromDate'] = new Date(createdFromDateElem.value).toISOString();
    }
    if (createdToDateElem.value) {
        jsonObj['createdToDate'] = new Date(createdToDateElem.value).toISOString();
    }

    jsonObj['createdBy'] = document.getElementById("dar_list_filter_createdBy").value;

    if (canContinue) {                
        const url = "/fsm/getFilterDarDetailsList";
        const itemName = "getFilterDarDetailsList";

        await getDataFromServicePoint(url, jsonObj)
            .then(async data => await populateDarListVResponse(data, "dar_list_form"))
            .catch(error => handleErrorForList(itemName,error,'dar_list_table_container'));
    }
};


async function exportDar() {   
    let canContinue = true;
    let jsonObj = {}; 

    jsonObj['darNo'] = document.getElementById("dar_list_filter_darNo").value;
    const darPerformFromDateElem = document.getElementById("dar_list_filter_darPerformFromDate");
    const darPerformToDateElem = document.getElementById("dar_list_filter_darPerformToDate");
    const createdFromDateElem = document.getElementById("dar_list_filter_createdFromDate");
    const createdToDateElem = document.getElementById("dar_list_filter_createdToDate");	    
  
    jsonObj['darPerformFromDate'] = formatDateToString(darPerformFromDateElem);
    jsonObj['darPerformToDate'] = formatDateToString(darPerformToDateElem);
   
  
    jsonObj['clientName'] = document.getElementById("dar_list_filter_clientName").value;
    jsonObj['clientMobileNo'] = document.getElementById("dar_list_filter_clientMobileNo").value;
    jsonObj['statusToVisit'] = document.getElementById("dar_list_filter_statusToVisit").value;
	jsonObj['createdFromDate'] = formatDateToString(createdFromDateElem);
	jsonObj['createdToDate'] = formatDateToString(createdToDateElem);
    jsonObj['createdBy'] = document.getElementById("dar_list_filter_createdBy").value;
    jsonObj['reportType'] = document.getElementById("dar_form_report_type_select").value;
  
    if (canContinue) {                
        const url = "/fsm/exportJasperReportInDar";
        const itemName = "exportJasperReportInDar";
  
        try {
            const data = await getDataFromServicePoint(url, jsonObj);
            await populateDarExportVResponse(data,jsonObj['Report Type']); 
        } catch (error) {
            handleError(itemName, error); 
        }
    }
};

async function populateDarExportVResponse(vResponseObj,reportType){
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

