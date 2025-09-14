var selectedUserRoleTagName  = "";

function getUsersListForm(){
    var form = `
	<div class="content">
	    <div class="row mb-3">
	        <div class="col-sm-4 col-3">
	            <h2 class="page-title">Users Details</h2>
	        </div>
	        <div class="col-sm-8 col-9 text-end m-b-20">
	            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="getUsersList('users_list_form')">
	                <i class="fas fa-refresh"></i> Refresh
	            </a>
	            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="showUsersEntryForm('add')">
	                <i class="fas fa-plus"></i> Add Users
	            </a>
	        </div>
	    </div>

	    <!-- Filter Form -->
	    <div class="buy-form">
	        <div class="col-12">
	            <div class="card">
	                <div class="card-body">
	                    <form id="users_list_filter_form" class="row g-3" autocomplete="off">
	                        <div class="col-md-3">
	                            <label for="users_list_filter_userId" class="form-label">User ID</label>
	                            <input type="text" class="form-control" id="users_list_filter_userId" placeholder="Enter User ID" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="users_list_filter_userName" class="form-label">User Name</label>
	                            <input type="text" class="form-control" id="users_list_filter_userName" placeholder="Enter User Name" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="users_list_filter_mobileNo" class="form-label">Mobile No</label>
	                            <input type="text" class="form-control" id="users_list_filter_mobileNo" placeholder="Enter Mobile No" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="users_list_filter_roleName" class="form-label">Role Name</label>
	                            <select class="form-select" id="users_list_filter_roleName" autocomplete="off" onchange="filterUserRoleNameBasedTagList(this)"></select>
	                        </div>
	                        <div class="col-md-3">
	                            <label for="users_list_filter_branchName" class="form-label">Branch Name</label>
	                            <input type="text" class="form-control" id="users_list_filter_branchName" placeholder="Enter Branch Name" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="users_list_filter_repCode" class="form-label">Rep Code</label>
	                            <input type="text" class="form-control" id="users_list_filter_repCode" placeholder="Enter Rep Code" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="users_list_filter_repAccount" class="form-label">Rep Account</label>
	                            <input type="text" class="form-control" id="users_list_filter_repAccount" placeholder="Enter Rep Account" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="users_list_filter_leaderUserId" class="form-label">Leader User ID</label>
	                            <input type="text" class="form-control" id="users_list_filter_leaderUserId" placeholder="Enter Leader User ID" autocomplete="off">
	                        </div>

	                        <!-- Filter and Clear Buttons -->
	                        <div class="col-12 text-end mt-3">
								<button type="button" class="btn btn-secondary" onclick="clearFilterInputs()">Clear Filters</button>
	                            <button type="button" class="btn btn-primary" onclick="filterUsers()">Filter</button>	                            
	                        </div>												
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
		
		<div class="buy-form" id="user_role_tag_container"></div>

	    <!-- Table -->
	    <div class="buy-form">                          
	        <div class="col-12">
	            <div class="card">                                                                                                              
	                <div class="card-body">
	                    <div class="table-responsive" id="users_list_table_container" class="dt-container dt-bootstrap5 dt-empty-footer"></div>                                 
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	`;
    return form;
};


function showUsersListForm(backMethod){
    try{
		
        hideAllLayer();	
        var containerId = "users_list_form";         
        document.getElementById(containerId).style.display = "block";
		if(backMethod != "true"){					
			getUsersList(containerId);																
		}
		createOptionTagInSelectTag("users_list_filter_roleName",users_UserRolesArrayString);
		createUserRoleTagButtons("user_role_tag_container",users_UserRolesArrayString);
		document.getElementById("user_role_tag_container").childNodes[0].click();	
				
				
    }catch(exp){
        alert(exp);
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};


async function getUsersList(containerId){
    var jsonObj = JSON.parse("{}");
    jsonObj['ID'] = "all";	
	jsonObj['Role Name'] = selectedUserRoleTagName;	
    let url = "/fsm/getUsersDetailsList";
	let itemName= "getUsersDetailsList";
    getDataFromServicePoint(url,jsonObj)
        .then(async data => await populateUsersListVResponse(data,containerId)) 
        .catch(error => handleError(itemName,error));
};


async function populateUsersListVResponse(vResponse,containerId){		
    /*if(vResponse.status == "true"){
		var dataArray = vResponse.data;		
		var editFunction = "editUsersDetails(this)";
		var deleteFunction = "deleteUsersDetails(this)";
		var tableId = containerId+"_table_id";
		document.getElementById("users_list_table_container").innerHTML = await createDataTable(vResponse,editFunction,deleteFunction,tableId);
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
	}*/
	
	
	if(vResponse.status == "true"){
		var dataArray = vResponse.data;		
		var editFunction = "editUsersDetails(this)";
		var deleteFunction = "deleteUsersDetails(this)";
		selectRecordStr = "";
		var idField = "ID";
		var imageOrStatusKeyJsonObj = {
			image:"File Name"
		};
		var statusClassMapping = {};
		var tableId = containerId+"_table_id";		
		document.getElementById("users_list_table_container").innerHTML = await createDataTableWithCheckboxEditAndDelete(vResponse, editFunction, deleteFunction, tableId, selectRecordStr, idField, imageOrStatusKeyJsonObj,statusClassMapping);
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
					             "targets": [0,1,6,13], 
								 "className": "hidden-column" 
					        	} 
			                ]		
			         });						
		}		
			     	 	      	
	}
};

	    
function clearFilterInputs() {
    document.getElementById('users_list_filter_form').reset();
};

async function filterUsers(){   
    var canContinue = true;
    var jsonObj = {}; 

    jsonObj['userId'] = document.getElementById("users_list_filter_userId").value;
    jsonObj['userName'] = document.getElementById("users_list_filter_userName").value;
    jsonObj['mobileNo'] = document.getElementById("users_list_filter_mobileNo").value;
    jsonObj['roleName'] = document.getElementById("users_list_filter_roleName").value;
    jsonObj['branchName'] = document.getElementById("users_list_filter_branchName").value;
    jsonObj['repCode'] = document.getElementById("users_list_filter_repCode").value;
    jsonObj['repAccount'] = document.getElementById("users_list_filter_repAccount").value;
    jsonObj['leaderUserId'] = document.getElementById("users_list_filter_leaderUserId").value;
    
    if(canContinue){                
        const url = "/fsm/getFilterUsersDetailsList";
        const itemName = "getFilterUsersDetailsList";
        await getDataFromServicePoint(url, jsonObj)
            .then(async data => await populateUsersListVResponse(data, "users_list_form"))
            .catch(error => handleErrorForList(itemName,error,'users_list_table_container'));
    }
};


function createUserRoleTagButtons(containerId,propValuesString){
	var propValuesArray = propValuesString.split(",");
	var btnArray = new Array();
	for(var gg = 0 ; gg < propValuesArray.length;gg++){
		var btnTag= `<button class="btn btn-light" style="margin:5px" onclick="getSelectedUserTagBasedRoleName(this)">`+propValuesArray[gg]+`</button>`;		
		btnArray.push(btnTag);	
	}
	var btnArrayHTML = btnArray.join("");
	document.getElementById(containerId).innerHTML = btnArrayHTML;
};

function getSelectedUserTagBasedRoleName(vObj){
	
	selectedUserRoleTagName = vObj.innerHTML;	   
	if(selectedUserRoleTagName == "All"){
		selectedUserRoleTagName = "";
	}
	document.getElementById("users_list_filter_roleName").value = selectedUserRoleTagName;      
	getUsersList("users_list_form");
	
	var parentContainer = document.getElementById("user_role_tag_container");
	for(var gg=0; gg < parentContainer.childNodes.length;gg++){
		parentContainer.childNodes[gg].style.border = "";
	}
	
	vObj.style.border= "2px solid grey";			
};

function filterUserRoleNameBasedTagList(vObj){	
	var parentContainer = document.getElementById("user_role_tag_container");
	for(var gg=0; gg < parentContainer.childNodes.length;gg++){
		if(parentContainer.childNodes[gg].innerHTML == vObj.value){
			parentContainer.childNodes[gg].click();
		}
	}
};