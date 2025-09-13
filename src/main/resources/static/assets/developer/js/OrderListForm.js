
function getOrderListForm(){
    var form = `
	<div class="content">
	    <div class="row mb-3">
	        <div class="col-sm-4 col-3">
	            <h2 class="page-title">Orders Details</h2>
	        </div>
	        <div class="col-sm-8 col-9 text-end m-b-20">
	            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="getOrderList('orders_list_form')">
	                <i class="fas fa-refresh"></i> Refresh
	            </a>
	            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="showOrderEntryForm('add')">
	                <i class="fas fa-plus"></i> Add Order
	            </a>
	        </div>
	    </div>

	    <!-- Filter Form -->
	    <div class="buy-form">
	        <div class="col-12">
	            <div class="card">
	                <div class="card-body">
	                    <form id="order_list_filter_form" class="row g-3" autocomplete="off">
	                        <div class="col-md-3">
	                            <label for="order_list_filter_eNo" class="form-label">E No</label>
	                            <input type="text" class="form-control" id="order_list_filter_eNo" placeholder="Enter E No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_estNo" class="form-label">Est No</label>
	                            <input type="text" class="form-control" id="order_list_filter_estNo" placeholder="Enter Est No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_orderNo" class="form-label">Order No</label>
	                            <input type="text" class="form-control" id="order_list_filter_orderNo" placeholder="Enter Order No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_soNo" class="form-label">SO No</label>
	                            <input type="text" class="form-control" id="order_list_filter_soNo" placeholder="Enter SO No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_ddNo" class="form-label">DD No</label>
	                            <input type="text" class="form-control" id="order_list_filter_ddNo" placeholder="Enter DD No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_customerName" class="form-label">Customer Name</label>
	                            <input type="text" class="form-control" id="order_list_filter_customerName" placeholder="Enter Customer Name">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_orderPerformFromDate" class="form-label">Order Perform From Date</label>
	                            <input type="datetime-local" class="form-control" id="order_list_filter_orderPerformFromDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_orderPerformToDate" class="form-label">Order Perform To Date</label>
	                            <input type="datetime-local" class="form-control" id="order_list_filter_orderPerformToDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_repAttD" class="form-label">Rep AttD</label>
	                            <input type="text" class="form-control" id="order_list_filter_repAttD" placeholder="Enter Rep AttD">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_demoPlan" class="form-label">Demo Plan</label>
	                            <select class="form-select" id="fsm_order_detail_demo_plan" name="Demo Plan">
									<option value=""></option>
									<option value="No">No</option>
	                                <option value="Yes">Yes</option>
	                            </select>
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_demoFromDate" class="form-label">Demo From Date</label>
	                            <input type="datetime-local" class="form-control" id="order_list_filter_demoFromDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_demoToDate" class="form-label">Demo To Date</label>
	                            <input type="datetime-local" class="form-control" id="order_list_filter_demoToDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_mobileNo" class="form-label">Mobile No</label>
	                            <input type="text" class="form-control" id="order_list_filter_mobileNo" placeholder="Enter Mobile No">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_itsHaveDiscount" class="form-label">Has Discount</label>	                            
								<select class="form-select" id="order_list_filter_itsHaveDiscount" name="Has Discount">
									<option value=""></option>
									<option value="No">No</option>
	                                <option value="Yes">Yes</option>
	                            </select>
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_orderStatus" class="form-label">Order Status</label>
	                            <select class="form-select" id="order_list_filter_orderStatus" name="Order Status">
	                                <option value="">-- Select Status --</option>
	                                <option>New Order</option>
	                                <option>Cancel Order</option>
	                                <option>Payment Confirmed</option>
	                                <option>Payment Received</option>	                                
	                            </select>
	                        </div>
							<div class="col-md-3">
	                            <label for="order_list_filter_createdBy" class="form-label">Created By</label>
	                            <input type="text" class="form-control" id="order_list_filter_createdBy" placeholder="Enter Created By">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_createdFromDate" class="form-label">Created From Date</label>
	                            <input type="datetime-local" class="form-control" id="order_list_filter_createdFromDate">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="order_list_filter_createdToDate" class="form-label">Created To Date</label>
	                            <input type="datetime-local" class="form-control" id="order_list_filter_createdToDate">
	                        </div>
	                        

	                        <!-- Filter and Clear Buttons -->
	                        <div class="col-12 text-end mt-3">
	                            <button type="button" class="btn btn-primary" onclick="filterOrder()">Filter</button>
	                            <button type="button" class="btn btn-secondary" onclick="clearOrderFilters()">Clear</button>
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
	                    <div class="table-responsive" id="order_list_table_container" class="dt-container dt-bootstrap5 dt-empty-footer"></div>                                 
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
		`;
    return form;
};


function showOrderListForm(backMethod){
    try{
		
        hideAllLayer();	
        var containerId = "order_list_form";         
        document.getElementById(containerId).style.display = "block";
		if(backMethod != "true"){		
			getOrderList(containerId);	
		}		
		createOptionTagInSelectTag("order_list_filter_orderStatus",order_ApprovalStatusArrayString);
    }catch(exp){
        alert(exp);
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};


async function getOrderList(containerId){
    var jsonObj = JSON.parse("{}");
    jsonObj['User ID'] = logginerUserId; 
    let url = "/fsm/getOrderDetailsList";
	let itemName= "getOrderDetailsList";
    getDataFromServicePoint(url,jsonObj)
        .then(async data => await populateOrderListVResponse(data,containerId)) 
        .catch(error => handleError(itemName,error));
};


async function populateOrderListVResponse(vResponse,containerId){		
   /* if(vResponse.status == "true"){
		var dataArray = vResponse.data;		
		var editFunction = "editOrderDetails(this)";
		var deleteFunction = "deleteOrderDetails(this)";
		var tableId = containerId+"_table_id";
		document.getElementById("order_list_table_container").innerHTML = await createDataTable(vResponse,editFunction,deleteFunction,tableId);
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
					             "targets": [0], 
								 "className": "hidden-column" 
					        	} 
			                ]		
			         });						
		}			        	 	      	
	}
	*/
	
	if(vResponse.status == "true"){		
			var dataArray = vResponse.data;	
			var exportFunction = "exportJasperReportInOrderInTableRow(this)";	
			var editFunction = "editOrderDetails(this)";
			var deleteFunction = "deleteOrderDetails(this)";
			selectRecordStr = "";
			var idField = "ID";
			var imageOrStatusKeyJsonObj = {		
				status:"Order Status"
			};
			var statusClassMapping = {
				"Order Dispatched":"badge badge-subtle-info"
				,"Cancel Order":"badge badge-subtle-danger"
				,"Payment Confirmed":"badge badge-subtle-success"
				,"New Order":"badge badge-subtle-warning"
			};
			var selectOptionsMapping = {};
			var selectOptionsBasedOnChangeFunction = "";
			var tableId = containerId+"_table_id";		
			document.getElementById("order_list_table_container").innerHTML = await await createDataTableWithCheckboxEdit_Delete_DropDown(vResponse,exportFunction, editFunction, deleteFunction, tableId, selectRecordStr, idField, imageOrStatusKeyJsonObj, statusClassMapping, selectOptionsMapping,selectOptionsBasedOnChangeFunction);
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



async function filterOrder() {   
    let jsonObj = {};

    jsonObj['eNo'] = document.getElementById("order_list_filter_eNo").value;
    jsonObj['estNo'] = document.getElementById("order_list_filter_estNo").value;        
    jsonObj['orderNo'] = document.getElementById("order_list_filter_orderNo").value;
    jsonObj['soNo'] = document.getElementById("order_list_filter_soNo").value;
    jsonObj['ddNo'] = document.getElementById("order_list_filter_ddNo").value;
    jsonObj['customerName'] = document.getElementById("order_list_filter_customerName").value;
	
    const orderPerformFromDateOrder = document.getElementById("order_list_filter_orderPerformFromDate");
    jsonObj['orderPerformFromDate'] = orderPerformFromDateOrder.value ? new Date(orderPerformFromDateOrder.value).toISOString() : null;
	
    const orderPerformToDateOrder = document.getElementById("order_list_filter_orderPerformToDate");
    jsonObj['orderPerformToDate'] = orderPerformToDateOrder.value ? new Date(orderPerformToDateOrder.value).toISOString() : null;
	
    jsonObj['repAttD'] = document.getElementById("order_list_filter_repAttD").value;
    jsonObj['mobileNo'] = document.getElementById("order_list_filter_mobileNo").value;
    jsonObj['demoPlan'] = document.getElementById("fsm_order_detail_demo_plan").value;
	
    const demoFromDateOrder = document.getElementById("order_list_filter_demoFromDate");
    jsonObj['demoFromDate'] = demoFromDateOrder.value ? new Date(demoFromDateOrder.value).toISOString() : null;
	
    const demoToDateOrder = document.getElementById("order_list_filter_demoToDate");
    jsonObj['demoToDate'] = demoToDateOrder.value ? new Date(demoToDateOrder.value).toISOString() : null;
	
    jsonObj['itsHaveDiscount'] = document.getElementById("order_list_filter_itsHaveDiscount").value;
    jsonObj['orderStatus'] = document.getElementById("order_list_filter_orderStatus").value;
	
    const createdFromDateElem = document.getElementById("order_list_filter_createdFromDate");
    jsonObj['createdFromDate'] = createdFromDateElem.value ? new Date(createdFromDateElem.value).toISOString() : null;
	
    const createdToDateElem = document.getElementById("order_list_filter_createdToDate");
    jsonObj['createdToDate'] = createdToDateElem.value ? new Date(createdToDateElem.value).toISOString() : null;
	
    jsonObj['createdBy'] = document.getElementById("order_list_filter_createdBy").value;

    let url = "/fsm/getFilterOrderDetailsList";
    let itemName = "filterOrderDetails";

    await getDataFromServicePoint(url, jsonObj)
        .then(async data => await populateOrderListVResponse(data, "order_list_form"))
        .catch(error => handleErrorForList(itemName,error,'order_list_table_container'));
};

function clearOrderFilters() {
    document.getElementById('order_list_filter_form').reset();
};
	

async function exportJasperReportInOrderInTableRow(vObj){
	
	var jsonObj = JSON.parse("{}");	
	jsonObj['estNo'] = vObj.parentNode.parentNode.childNodes[3].innerHTML; 
	jsonObj['reportType'] = vObj.previousSibling.value; 	 	
		
	let url = "/fsm/exportJasperReportInOrder";
	let itemName= "exportJasperReportInOrder";
    getDataFromServicePoint(url,jsonObj)
        .then(data => populateExportJasperReportInOrderVResponse(data,jsonObj['reportType'])) 
        .catch(error => handleError(itemName,error));     
	       		
};

