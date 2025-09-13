function getProductEntryForm(){
	
	var form = `<div class="modal fade show" id="" tabindex="-1" role="dialog" style="display: block;" aria-modal="true">
					<div class="modal-dialog modal-dialog-centered" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h2 class="modal-title">Create Product</h2>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="closeProductEntryForm()"></button>
								</div>
								<div class="modal-body mx-3">
									<div class="mb-3">
										<label class="form-label">Product ID</label>
										<input type="hidden" class="form-control" id="fsm_product_details_uuid_id">
										<input type="text" class="form-control" id="fsm_product_details_product_id">										
									</div>
									<div class="mb-3">
										<label class="form-label">Product Name</label>										
										<input type="text" class="form-control" id="fsm_product_details_new_product">
									</div>
									<div class="mb-3">
										<label class="form-label">Product Category</label>																				
										<select class="form-select" id="fsm_product_details_product_category"></select>
									</div>
									<div class="mb-3">
										<label class="form-label">Unit Price</label>										
										<input type="text" class="form-control" id="fsm_product_details_unit_price">
									</div>																											
									<div class="mb-3">
										<label class="form-label">Tax</label>										
										<input type="text" class="form-control" id="fsm_product_details_tax">
									</div>																											
								</div>
								<div class="modal-footer">
									<button class="btn btn-primary" data-bs-dismiss="modal" id="fsm_product_details_delete_btn" style="background:#fe3f51;display:none;" onclick="deleteProductDetails('')">Delete</button>
									<button class="btn btn-light" data-bs-dismiss="modal" onclick="clearProductDetails()">Clear</button>
									<button class="btn btn-primary" data-bs-dismiss="modal" onclick="updateProductDetails()">Save</button>																		
								</div>
							</div>
						</div>
					</div>`;
	return form;
};

function closeProductEntryForm(){
	var containerId = "transparent_general_form_container_id";
	document.getElementById(containerId).style.display = "none";
};

function showProductEntryForm(action){
    try{
        //hideAllLayer();
		var containerId = "transparent_general_form_container_id";
        document.getElementById(containerId).innerHTML = getProductEntryForm();
		if(action == "edit"){			
			document.getElementById("fsm_product_details_delete_btn").style.display = "block";
		}
        document.getElementById(containerId).style.display = "block";
		getCategoryListToSelectTagOption('fsm_product_details_product_category');		
    }catch(exp){        
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};



/*****************************************Update *********************************************** */
async function updateProductDetails(){	
	var canContinue	= true;
    var jsonObj = JSON.parse("{}");
	jsonObj['ID'] = document.getElementById("fsm_product_details_uuid_id").value;
	jsonObj['Product ID'] = document.getElementById("fsm_product_details_product_id").value;	
    jsonObj['Product Name'] = document.getElementById("fsm_product_details_new_product").value;
	jsonObj['Product Category'] = document.getElementById("fsm_product_details_product_category").value;
	jsonObj['Unit Price'] = document.getElementById("fsm_product_details_unit_price").value;
	jsonObj['Tax'] = document.getElementById("fsm_product_details_tax").value;			
	jsonObj['Created Date'] = new Date();
	jsonObj['Created By'] = logginerUserId;	   
	var inputNames = "Product"; 
	var inputTypes = "text";
	var inputElementIds = "fsm_product_details_new_product";
	canContinue ? canContinue = await checkValidationDynamicInputRow(inputNames,inputTypes,inputElementIds): canContinue = false;
	if(canContinue){  		
	    let url = "/fsm/updateProductDetails";
		let itemName = "updateProductDetails";
	    await getDataFromServicePoint(url,jsonObj)
	        .then(async data => await populateProductDetailsVResponse(data)) 
	        .catch(error => handleError(itemName,error));
	}
};

async function populateProductDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){	
		toastr.success("Successfully Updated","Completed", {closeButton: !0,tapToDismiss: !1});		
		closeProductEntryForm();		        
		showProductListForm('');		
	}else{
		toastr.warning("Invalid Product Details","Warning", {closeButton: !0,tapToDismiss: !1});
	}
};

/*****************************************Clear *********************************************** */
function clearProductDetails(){    
    document.getElementById("fsm_product_details_uuid_id").value = "";    
	document.getElementById("fsm_product_details_product_id").value = "";  		
	document.getElementById("fsm_product_details_new_product").value = "";
	document.getElementById("fsm_product_details_product_category").value = "";
	document.getElementById("fsm_product_details_unit_price").value = "";
	document.getElementById("fsm_product_details_tax").value = "";   
};
/*****************************************Edit *********************************************** */


async function editProductDetails(vObj){
	
	var jsonObj = JSON.parse("{}");	
	jsonObj = await buildDynamicTableJsonObj(vObj);	
	swal({
	        title: "Confirmation",
	        text: "Do you want to Edit ?",
	        icon: "info",
	        buttons: true,
	        dangerMode: true,
	 }).then((confirmation) => {
	        if (confirmation) {	
				showProductEntryForm('edit');
							
				let url = "/fsm/editProductDetails";
				let itemName= "editProductDetails";
			    getDataFromServicePoint(url,jsonObj)
			        .then(data => populateEditProductDetailsVResponse(data)) 
			        .catch(error => handleError(itemName,error));     
	        } 
	 });				
};

function populateEditProductDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){
				
		var dataObj = vResponseObj.data;				
		
		document.getElementById("fsm_product_details_uuid_id").value = dataObj['ID'];
		document.getElementById("fsm_product_details_product_id").value = dataObj['Product ID'];	
		document.getElementById("fsm_product_details_new_product").value = dataObj['Product Name'];
		document.getElementById("fsm_product_details_product_category").value = dataObj['Product Category'];
		document.getElementById("fsm_product_details_unit_price").value = dataObj['Unit Price'];
		document.getElementById("fsm_product_details_tax").value = dataObj['Tax'];	    		
	}
};
/*****************************************Delete *********************************************** */
async function deleteProductDetails(vObj){
	var jsonObj = JSON.parse("{}");
	if(vObj == ""){
		jsonObj['ID'] = document.getElementById("fsm_product_details_uuid_id").value;		    		    		   
	}else{
		jsonObj = await buildDynamicTableJsonObj(vObj);
	}		
	
		swal({
		        title: "Confirmation",
		        text: "Do you want to delete ?",
		        icon: "warning",
		        buttons: true,
		        dangerMode: true,
		 }).then((confirmation) => {
		        if (confirmation) {
					let url = "/fsm/deleteProductDetails";
					let itemName= "deleteProductDetails";
	    			getDataFromServicePoint(url,jsonObj)
	        			.then(data => populateDeleteProductDetailsVResponse(data)) 
	        			.catch(error => handleError(itemName,error));
				}
		});	
				
};

function populateDeleteProductDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){			
		toastr.success("Deleted Successfully","Completed", {closeButton: !0,tapToDismiss: !1});
		closeProductEntryForm();	
		showProductListForm('');		    		    		   	
	}
};


