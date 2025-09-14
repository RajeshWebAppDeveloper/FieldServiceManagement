function getUsersEntryForm(showType) {
    var btnHTML = ``;

    if (showType === "add") {
        btnHTML = `
            <div class="row">
                <div class="col-12 col-lg-6 d-flex justify-content-lg-start justify-content-center mb-2 mb-lg-0">
                    <button class="btn btn-light w-100" onclick="clearUsersDetails()">Clear</button>
                </div>
                <div class="col-12 col-lg-6 d-flex justify-content-lg-end justify-content-center">
                    <button class="btn btn-primary w-100" onclick="updateUsersDetails()">Save</button>
                </div>
            </div>`;
    } else if (showType === "edit") {
        btnHTML = `
            <div class="row">
                <div class="col-12 col-lg-4 d-flex justify-content-lg-start justify-content-center mb-2 mb-lg-0">
                    <button class="btn btn-danger w-100" style="background:#fe3f51;" onclick="deleteUsersDetails('')">Delete</button>
                </div>
                <div class="col-12 col-lg-4 d-flex justify-content-lg-center justify-content-center mb-2 mb-lg-0">
                    <button class="btn btn-light w-100" onclick="clearUsersDetails()">Clear</button>
                </div>
                <div class="col-12 col-lg-4 d-flex justify-content-lg-end justify-content-center">
                    <button class="btn btn-primary w-100" onclick="updateUsersDetails()">Save</button>
                </div>
            </div>`;
    }
	
    var form = `
        <div class="content">
            <div class="row">
                <div class="col-sm-4 col-3"></div>
                <div class="col-sm-8 col-9 text-end m-b-20">
                    <a href="#" class="btn btn-primary float-right btn-rounded" onclick="showUsersListForm('true')">
                        <i class="fas fa-arrow-left"></i> Back to table
                    </a>
                </div>
            </div>
            <div class="row">					
                <div class="col-lg-12 offset-lg-0">
                    <div class="buy-form">
                        <h2 class="page-title">Users Details</h2>
                        <div class="row">   
                            <!-- Centered Profile Image -->
                            <div class="col-12 text-center">
                                <div class="profile-img">
                                    <img class="avatar rounded-circle" src="/assets/img/avatars/user.png" alt="" id="user_entry_image_tag_id" style="width:150px; height:150px;border:1px solid grey;">
                                    <div class="fileupload btn">
                                        <span class="btn-text">edit</span>
                                        <input class="upload" type="file" id="user_entry_image_input_id" accept="image/*" onchange="fileImageLoadSRC(event,'user_entry_image_tag_id')">
                                    </div>
                                </div>
                            </div>
                        
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label class="form-label">Users ID<span class="text-danger">*</span></label>
									<input class="form-control" type="hidden" id="user_entry_user_uuid_txt" disabled>
                                    <input class="form-control" type="text" id="user_entry_user_id_txt" placeholder="Auto-Generate" disabled>
                                </div>
								<div class="form-group">
                                   <label class="form-label">User Name<span class="text-danger">*</span></label>
                                   <input type="text" class="form-control" id="user_entry_user_name_txt">
                               </div>
                                <div class="form-group">
                                    <label class="form-label">Password<span class="text-danger">*</span></label>
                                    <input class="form-control" type="password" id="user_entry_password_txt">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Email ID<span class="text-danger">*</span></label>
                                    <input type="email" class="form-control" id="user_entry_email_id_txt">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Phone No<span class="text-danger">*</span></label>
                                    <input type="tel" class="form-control" id="user_entry_phone_no_txt">
                                </div>
								<div class="form-group">
                                    <label class="form-label">Branch<span class="text-danger">*</span></label>
                                    <select class="form-select" id="user_entry_branch_txt">
										<option>Chennai</option>
										<option>Trichy</option>
										<option>Trivellur</option>
									</select>
                                </div>
                               
                            </div>

                            <!-- Right Side (Role and User Rights) -->
                            <div class="col-lg-6">
								<div class="form-group">
                                    <label class="form-label">Leader User ID</label>
                                    <input class="form-control" type="text" id="user_entry_leader_user_id_txt" placeholder="System Process - Control" >
                                </div>
                            	 <div class="form-group">
                                    <label class="form-label">Rep Code<span class="text-danger">*</span></label>
                                    <input class="form-control" type="text" id="user_entry_rep_code_txt">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Rep Account<span class="text-danger">*</span></label>
                                    <input class="form-control" type="text" id="user_entry_rep_account_txt">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Users Role<span class="text-danger">*</span></label>
                                    <select class="form-select" id="user_entry_role_name_txt">
										<option></option>
										<option>RK</option>
										<option>SRK</option>
										<option>Sales coordinator</option>
										<option>Sales Account</option>
										<option>GS</option>
										<option>VS</option>
										<option>MK</option>
										<option>MH</option>
										<option>GB</option>
										<option>YG</option>
										<option>SR</option>
										<option>Admin</option>
										<option>REP</option>
									</select>
                                </div>
								<div class="form-group">
									<label class="form-label">User Rights<span class="text-danger">*</span></label>
								   <div class="form-group" id="fsm_user_rights_checkbox_tree_container"></div>
                                </div>
                                
                            </div>						
                        </div>                    
						

						<div class="card-body">
						    <div class="row">
						        <div class="col-sm-12">
						            <div class="card mb-0">
						                <div class="card-body">
						                    <div class="row mb-3"> 
						                        <div class="col-6">
						                            <h2 class="card-title">Team Members</h2>
						                        </div>
						                        <div class="col-6 text-end"> 
						                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#taskModal" onclick="showUserEntryAddTeamMemberForm('add')">Add</button>
						                        </div>
						                    </div>
						                    <div class="table-responsive">
						                        <div class="row" id="user_entry_form_team_members_container"></div>
						                    </div>
						                </div>
						            </div>
						        </div>
						    </div>
						</div>
						
						
		
                        <div class="my-4 text-center">
                            ${btnHTML} 
                        </div>
                    </div>
                </div>
            </div>
        </div>`;

    return form;
};




function showUsersEntryForm(action){
    try{
        hideAllLayer();
        var containerId = "users_entry_form"; 
        document.getElementById(containerId).innerHTML = getUsersEntryForm(action);
		if(action != "edit"){						
			generateForUsersId();
		}
        document.getElementById(containerId).style.display = "block";
		createOptionTagInSelectTag("user_entry_role_name_txt",users_UserRolesArrayString);
		getUserRoleFeatures("fsm_user_rights_checkbox_tree_container");
				
		document.getElementById("user_entry_role_name_txt").value = selectedUserRoleTagName;
    }catch(exp){        
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};

function fileImageLoadSRC(event,imageTagId){
	var imagePreview = document.getElementById(imageTagId);	
	handleImagePreview(event, imagePreview);	 
};

async function generateForUsersId(){
	var canContinue	= true;
    var jsonObj = JSON.parse("{}");
	jsonObj['ID'] = "";
			
	if(canContinue){  		
	    let url = "/fsm/generateUsersId";
		let itemName = "generateUsersId";
	    await getDataFromServicePoint(url,jsonObj)
	        .then(async data => await populateGenerateForUsersIdVResponse(data)) 
	        .catch(error => handleError(itemName,error));
	}
};

async function populateGenerateForUsersIdVResponse(vResponseObj){	
    if(vResponseObj.status == "true"){		
		document.getElementById("user_entry_user_id_txt").value = vResponseObj.userId; 							        		
	}else{
		toastr.warning("User ID its not Generate","Warning", {closeButton: !0,tapToDismiss: !1});
	}
};

/*****************************************Update *********************************************** */
/*async function updateUsersDetails() {
    let canContinue = true;


    if (canContinue) {
        const formData = new FormData();

        const fileInput = document.getElementById("user_entry_image_input_id");
        if (fileInput.files.length > 0) {
            formData.append('file', fileInput.files[0]);
        }
		formData.append('ID',document.getElementById("user_entry_user_uuid_txt").value);
        formData.append('Users ID', document.getElementById("user_entry_user_id_txt").value);
		formData.append('Users Name', document.getElementById("user_entry_user_name_txt").value);		
        formData.append('Password', document.getElementById("user_entry_password_txt").value);
        formData.append('Email ID', document.getElementById("user_entry_email_id_txt").value);
        formData.append('Mobile No', document.getElementById("user_entry_phone_no_txt").value);
        formData.append('Branch', document.getElementById("user_entry_branch_txt").value);
        formData.append('Leader User ID', document.getElementById("user_entry_leader_user_id_txt").value);
        formData.append('Rep Code', document.getElementById("user_entry_rep_code_txt").value);
        formData.append('Rep Account', document.getElementById("user_entry_rep_account_txt").value);
        formData.append('Role Name', document.getElementById("user_entry_role_name_txt").value);
		
		var treeStrucutureContainerObj = document.getElementById("fsm_user_rights_checkbox_tree_container");
		var checkedRolesJsonObj = await buildCheckedJSONObject(treeStrucutureContainerObj.childNodes[0]);		
		formData.append('User Rights',JSON.stringify(checkedRolesJsonObj));

        const timeoutId = setTimeout(() => {
            document.getElementById("transparent_loader_container_id").style.display = "block";
        }, 500);

        try {
            const url = "/fsm/updateUsersDetails";
            const response = await fetch(url, {
                method: 'POST',
                body: formData,
            });

            clearTimeout(timeoutId);
            document.getElementById("transparent_loader_container_id").style.display = "none";

            if (response.ok) {
                const data = await response.json();
                await populateUsersDetailsVResponse(data);
                toastr.success('User details updated successfully.', "Success", { closeButton: true, tapToDismiss: false });
            } else {
                toastr.error('Failed to update user details.', "Error", { closeButton: true, tapToDismiss: false });
            }
        } catch (error) {
            clearTimeout(timeoutId);
            document.getElementById("transparent_loader_container_id").style.display = "none";
            toastr.error(error.message, "Error", { closeButton: true, tapToDismiss: false });
        }
    }
};
*/
async function updateUsersDetails() {
    let canContinue = true;

    if (canContinue) {
        const formData = new FormData();

        const fileInput = document.getElementById("user_entry_image_input_id");
        if (fileInput.files.length > 0) {
            formData.append('file', fileInput.files[0]);
        }
        formData.append('ID', document.getElementById("user_entry_user_uuid_txt").value);
        formData.append('Users ID', document.getElementById("user_entry_user_id_txt").value);
        formData.append('Users Name', document.getElementById("user_entry_user_name_txt").value);
        formData.append('Password', document.getElementById("user_entry_password_txt").value);
        formData.append('Email ID', document.getElementById("user_entry_email_id_txt").value);
        formData.append('Mobile No', document.getElementById("user_entry_phone_no_txt").value);
        formData.append('Branch', document.getElementById("user_entry_branch_txt").value);
        formData.append('Leader User ID', document.getElementById("user_entry_leader_user_id_txt").value);
        formData.append('Rep Code', document.getElementById("user_entry_rep_code_txt").value);
        formData.append('Rep Account', document.getElementById("user_entry_rep_account_txt").value);
        formData.append('Role Name', document.getElementById("user_entry_role_name_txt").value);

        const treeStrucutureContainerObj = document.getElementById("fsm_user_rights_checkbox_tree_container");
        const checkedRolesJsonObj = await buildCheckedJSONObject(treeStrucutureContainerObj.childNodes[0]);
        formData.append('User Rights', JSON.stringify(checkedRolesJsonObj));

        const timeoutId = setTimeout(() => {
            document.getElementById("transparent_loader_container_id").style.display = "block";
        }, 500);

        try {
            const url = "/fsm/updateUsersDetails";
            const response = await fetch(url, {
                method: 'POST',
                body: formData,
            });

            clearTimeout(timeoutId);
            document.getElementById("transparent_loader_container_id").style.display = "none";

            let data;
            try {
                data = await response.json(); // try parsing JSON
            } catch (e) {
                data = { error: await response.text() }; // fallback to raw text
            }

            if (response.ok && data.status === "true") {
                await populateUsersDetailsVResponse(data);
                toastr.success(data.message || 'User details updated successfully.', "Success", {
                    closeButton: true,
                    tapToDismiss: false
                });
            } else {
                toastr.error(
                    data.error || data.message || 'Failed to update user details.',
                    "Error",
                    { closeButton: true, tapToDismiss: false }
                );
            }
        } catch (error) {
            clearTimeout(timeoutId);
            document.getElementById("transparent_loader_container_id").style.display = "none";
            toastr.error(error.message || 'Unexpected error occurred.', "Error", {
                closeButton: true,
                tapToDismiss: false
            });
        }
    }
};

async function populateUsersDetailsVResponse(vResponseObj) {
    if (vResponseObj.status === "true") {
        toastr.success("Successfully Updated", "Completed", {closeButton: true, tapToDismiss: false});
        showUsersListForm('');
    } else {
        toastr.info("Already Exists. Cannot overwrite.", "Info", {closeButton: true, tapToDismiss: false});
    }
};

/*****************************************Clear *********************************************** */
function clearUsersDetails(){    
	document.getElementById("user_entry_user_uuid_txt").value = "";
	document.getElementById("user_entry_user_id_txt").value = ""; 
	document.getElementById("user_entry_user_name_txt").value = "";
	document.getElementById("user_entry_password_txt").value = "";
	document.getElementById("user_entry_email_id_txt").value = "";
	document.getElementById("user_entry_phone_no_txt").value = "";
	document.getElementById("user_entry_branch_txt").value = "";
	document.getElementById("user_entry_leader_user_id_txt").value = "";
	document.getElementById("user_entry_rep_code_txt").value = ""; 
	document.getElementById("user_entry_rep_account_txt").value = ""; 
	document.getElementById("user_entry_role_name_txt").value = "";

	document.getElementById("user_entry_image_input_id").value = "";
	document.getElementById("user_entry_image_tag_id").src = "/assets/img/user.jpg";
	document.getElementById("fsm_user_rights_checkbox_tree_container").innerHTML = "";
	getUserRoleFeatures("fsm_user_rights_checkbox_tree_container");	
};



/*****************************************Edit *********************************************** */
async function editUsersDetails(vObj) {   
    let jsonObj = await buildDynamicChexBoxColumnTableJsonObj(vObj);
    
    swal({
        title: "Confirmation",
        text: "Do you want to Edit?",
        icon: "info",
        buttons: true,
        dangerMode: true,
    }).then(async (confirmation) => {
        if (confirmation) {
			showUsersEntryForm('edit');
            let url = "/fsm/editUsersDetails";
            let itemName = "editUsersDetails";               
			await getDataFromServicePoint(url,jsonObj)
			 .then(async data => await populateEditUsersDetailsVResponse(data)) 
			    .catch(error => handleError(itemName,error));
        }
    });
};

async function populateEditUsersDetailsVResponse(vResponseObj) {
	try{
	    if (vResponseObj.status === "true") {
			
	        const dataObj = vResponseObj.data;			
	        document.getElementById("user_entry_user_uuid_txt").value = dataObj['ID'];
	        document.getElementById("user_entry_user_id_txt").value = dataObj['User ID'];
	        document.getElementById("user_entry_user_name_txt").value = dataObj['User Name'];
	        document.getElementById("user_entry_password_txt").value = dataObj['Password'];
	        document.getElementById("user_entry_email_id_txt").value = dataObj['Email ID'];
	        document.getElementById("user_entry_phone_no_txt").value = dataObj['Mobile No'];
	        document.getElementById("user_entry_role_name_txt").value = dataObj['Role Name'];
	        document.getElementById("user_entry_branch_txt").value = dataObj['Branch'];
	        document.getElementById("user_entry_leader_user_id_txt").value = dataObj['Leader User ID'];
	        document.getElementById("user_entry_rep_code_txt").value = dataObj['Rep Code'];
	        document.getElementById("user_entry_rep_account_txt").value = dataObj['Rep Account'];		
	                    
	        document.getElementById("user_entry_image_tag_id").src = `/fsm/RetrieveFile/${dataObj['File Name']}`;
			
			var treeStructureContainer = document.getElementById("fsm_user_rights_checkbox_tree_container").childNodes[0];				
			autoCheckTreeStructureView(treeStructureContainer,dataObj['User Rights']);
			
			const teamMemberJsonArray = vResponseObj.teamMemberData;
			loadTeamMemberAddedListBox(teamMemberJsonArray);			
	    } else {
	        toastr.info("No user data found or invalid response.", "Info", { closeButton: true, tapToDismiss: false });
	    }
	}catch(exp){
		alert(exp);
	}
};

function getTeamMemberUserDetailsBoxContainerHTML(dataObj){
	var container = `<div class="col-md-6 col-lg-4 mb-4">
                        <div class="card ticket-card">
                            <div class="card-body text-left"> 
                                <div class="profile-img mb-3">
                                    <img src="/fsm/RetrieveFile/${dataObj['File Name']}" alt="" class="img-fluid rounded-circle" style="width:100px;height:100px;">
                                </div>
                                <span class="badge badge-primary" style="color:#3f80ea;font-weight:bold;">${dataObj['User ID']}</span>
                                <h4>Name: ${dataObj['User Name']}</h4>
                                <p class="mb-1"><i class="fas fa-envelope me-1"></i>Email: ${dataObj['Email ID']}</p>
                                <p class="mb-1"><i class="fas fa-phone me-1"></i>Phone: ${dataObj['Mobile No']}</p>
                                <p class="mb-1"><i class="fas fa-briefcase me-1"></i>Branch: ${dataObj['Branch']}</p>
                                <p class="mb-1"><i class="fas fa-user-tag me-1"></i>User Role: ${dataObj['Role Name']}</p>
                                <p class="mb-1"><i class="fas fa-user-check me-1"></i>Rep Code: <strong>${dataObj['Rep Code']}</strong></p>
                                <p class="mb-1"><i class="fas fa-user-circle me-1"></i>Rep Account: <strong>${dataObj['Rep Account']}</strong></p>
                            </div>
                        </div>
                    </div>`;
	return container;	
};
							
function loadTeamMemberAddedListBox(memberJsonArray){
	
	var rowArray = new Array();
	for(var gg=0; gg < memberJsonArray.length;gg++){
		var memberJsonObj = memberJsonArray[gg];
		var row = getTeamMemberUserDetailsBoxContainerHTML(memberJsonObj);
		rowArray.push(row);
	}
	var rowArrayHtml = rowArray.join("");
	document.getElementById("user_entry_form_team_members_container").innerHTML = rowArrayHtml;
};
/*****************************************Delete *********************************************** */
async function deleteUsersDetails(vObj){
	var jsonObj = JSON.parse("{}");	
	if(vObj == ""){
		jsonObj['ID'] = document.getElementById("user_entry_user_uuid_txt").value;		    		    		   
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
					let url = "/fsm/deleteUsersDetails";
					let itemName= "deleteUsersDetails";
	    			getDataFromServicePoint(url,jsonObj)
	        			.then(data => populateDeleteUsersDetailsVResponse(data)) 
	        			.catch(error => handleError(itemName,error));
				}
		});	
				
};

function populateDeleteUsersDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){			
		toastr.success("Deleted Successfully","Completed", {closeButton: !0,tapToDismiss: !1});	
		showUsersListForm('');		    		    		   
		
	}
};


function getUserRoleFeatures(formBodyContainerId){
	var jsonObj = {
					"Dashboard":""
					,"Users":""
					,"Catgory":""
					,"Product":""
					,"DAR":""
					,"Estimation":""
					,"Order":""
					,"Payment":""
					,"Default Properties":""								
				  };
	var vElement = document.getElementById(formBodyContainerId);
	buildRolesTree(vElement,jsonObj);
	
	toastr.info("Without Main-Menu Don't assigns any Sub-Menu,Otherwise process it will be Omit.","User Rights Info", {closeButton: !0,tapToDismiss: !1});
};


