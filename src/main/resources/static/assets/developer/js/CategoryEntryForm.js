function getCategoryEntryForm(){
	
	var form = `<div class="modal fade show" id="" tabindex="-1" role="dialog" style="display: block;" aria-modal="true">
					<div class="modal-dialog modal-dialog-centered" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h2 class="modal-title">Create Category</h2>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="closeCategoryEntryForm()"></button>
								</div>
								<div class="modal-body mx-3">
									<div class="mb-3">
										<label class="form-label">Category Id</label>
										<input type="hidden" class="form-control" placeholder="Enter the new Category" id="fsm_category_details_uuid_id">
										<input type="text" class="form-control" placeholder="Enter the new Category" id="fsm_category_details_uuid_category_id">								
									</div>	
									<div class="mb-3">
										<label class="form-label">Category Name</label>										
										<input type="text" class="form-control" placeholder="Enter the new Category" id="fsm_category_details_new_category">
									</div>									
								</div>
								<div class="modal-footer">
									<button class="btn btn-primary" data-bs-dismiss="modal" id="fsm_category_details_delete_btn" style="background:#fe3f51;display:none;" onclick="deleteCategoryDetails('')">Delete</button>
									<button class="btn btn-light" data-bs-dismiss="modal" onclick="clearCategoryDetails()">Clear</button>
									<button class="btn btn-primary" data-bs-dismiss="modal" onclick="updateCategoryDetails()">Save</button>																		
								</div>
							</div>
						</div>
					</div>`;
	return form;
};

function closeCategoryEntryForm(){
	var containerId = "transparent_general_form_container_id";
	document.getElementById(containerId).style.display = "none";
};

function showCategoryEntryForm(action){
    try{
        //hideAllLayer();
		var containerId = "transparent_general_form_container_id";
        document.getElementById(containerId).innerHTML = getCategoryEntryForm();
		if(action == "edit"){			
			document.getElementById("fsm_category_details_delete_btn").style.display = "block";
		}
        document.getElementById(containerId).style.display = "block";		
    }catch(exp){        
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};



/*****************************************Update *********************************************** */
async function updateCategoryDetails(){	
	var canContinue	= true;
    var jsonObj = JSON.parse("{}");
	jsonObj['ID'] = document.getElementById("fsm_category_details_uuid_id").value;
    jsonObj['Category'] = document.getElementById("fsm_category_details_new_category").value;
	jsonObj['Category ID'] = document.getElementById("fsm_category_details_uuid_category_id").value;		
	jsonObj['Created Date'] = new Date();
	jsonObj['Created By'] = logginerUserId;	   
	var inputNames = "Category"; 
	var inputTypes = "text";
	var inputElementIds = "fsm_category_details_new_category";
	canContinue ? canContinue = await checkValidationDynamicInputRow(inputNames,inputTypes,inputElementIds): canContinue = false;
	if(canContinue){  		
	    let url = "/fsm/updateCategoryDetails";
		let itemName = "updateCategoryDetails";
	    await getDataFromServicePoint(url,jsonObj)
	        .then(async data => await populateCategoryDetailsVResponse(data)) 
	        .catch(error => handleError(itemName,error));
	}
};

async function populateCategoryDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){	
		toastr.success("Successfully Updated","Completed", {closeButton: !0,tapToDismiss: !1});		
		closeCategoryEntryForm();		        
		showCategoryListForm('');		
	}else{
		toastr.warning("Invalid Category Details","Warning", {closeButton: !0,tapToDismiss: !1});
	}
};

/*****************************************Clear *********************************************** */
function clearCategoryDetails(){    
    document.getElementById("fsm_category_details_uuid_id").value = "";
    document.getElementById("fsm_category_details_new_category").value = "";
	document.getElementById("fsm_category_details_uuid_category_id").value ="";    
};
/*****************************************Edit *********************************************** */


async function editCategoryDetails(vObj){
	
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
				let url = "/fsm/editCategoryDetails";
				let itemName= "editCategoryDetails";
			    getDataFromServicePoint(url,jsonObj)
			        .then(data => populateEditCategoryDetailsVResponse(data)) 
			        .catch(error => handleError(itemName,error));     
	        } 
	 });				
};

function populateEditCategoryDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){
		showCategoryEntryForm('edit');		
		var dataObj = vResponseObj.data;		
		document.getElementById("fsm_category_details_uuid_id").value = dataObj['ID'];
	    document.getElementById("fsm_category_details_new_category").value = dataObj['Category'];
		document.getElementById("fsm_category_details_uuid_category_id").value = dataObj['Category ID'];	    		
	}
};
/*****************************************Delete *********************************************** */
async function deleteCategoryDetails(vObj){
	var jsonObj = JSON.parse("{}");
	if(vObj == ""){
		jsonObj['ID'] = document.getElementById("fsm_category_details_uuid_id").value;		    		    		   
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
					let url = "/fsm/deleteCategoryDetails";
					let itemName= "deleteCategoryDetails";
	    			getDataFromServicePoint(url,jsonObj)
	        			.then(data => populateDeleteCategoryDetailsVResponse(data)) 
	        			.catch(error => handleError(itemName,error));
				}
		});	
				
};

function populateDeleteCategoryDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){			
		toastr.success("Deleted Successfully","Completed", {closeButton: !0,tapToDismiss: !1});
		closeCategoryEntryForm();	
		showCategoryListForm('');		    		    		   	
	}
};