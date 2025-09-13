var selectRecordStr = "";
function getTeamMemberListForm(){
	
	var form = `<div class="modal fade show" id="" tabindex="-1" role="dialog" style="display: block;" aria-modal="true" >
					
							<div class="modal-content" style="width:70%;left:15%;top:10%;">
								<div class="modal-header">
									<h2 class="modal-title">Add Team Member</h2>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="closeTeamMemberEntryForm()"></button>
								</div>
								<div class="modal-body mx-3" id="team_member_list_container" style="overflow:auto"></div>
								<div class="modal-footer">									
									<button class="btn btn-light" data-bs-dismiss="modal" onclick="closeTeamMemberEntryForm()">Cancel</button>
									<button class="btn btn-primary" data-bs-dismiss="modal" onclick="addTeamMembersToAdmin()">Add Team Members</button>																		
								</div>
							</div>
						
					</div>`;
	return form;
};

function closeTeamMemberEntryForm(){
	var containerId = "transparent_general_form_container_id";
	document.getElementById(containerId).style.display = "none";
};

function showUserEntryAddTeamMemberForm(action){
    try{
		var containerId = "transparent_general_form_container_id";
        document.getElementById(containerId).innerHTML = getTeamMemberListForm();
		getAllTeamMemberList("team_member_list_container");
		document.getElementById(containerId).style.display = "block";				
    }catch(exp){        
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};


async function getAllTeamMemberList(containerId){
    var jsonObj = JSON.parse("{}");
    jsonObj['Admin User ID'] = document.getElementById("user_entry_user_id_txt").value;      	
    let url = "/fsm/addTeamMemberWithUsersDetailsList";
	let itemName= "addTeamMemberWithUsersDetailsList";
    getDataFromServicePoint(url,jsonObj)
        .then(async data => await populateAllTeamMemberListVResponse(data,containerId)) 
        .catch(error => handleError(itemName,error));
};


async function populateAllTeamMemberListVResponse(vResponse,containerId){		
    if(vResponse.status == "true"){
		var dataArray = vResponse.data;		
		
		var editFunction = "";
		var deleteFunction = "";
		selectRecordStr = JSON.stringify(vResponse.teamMemberData);
		var idField = "ID";
		var imageOrStatusKeyJsonObj = {
			image:"File Name"
		};
		var statusClassMapping = {};
		var tableId = containerId+"_table_id";		
		document.getElementById(containerId).innerHTML = await createDataTableWithCheckboxEditAndDelete(vResponse, editFunction, deleteFunction, tableId, selectRecordStr, idField, imageOrStatusKeyJsonObj,statusClassMapping);
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
					             "targets": [1,6,13,14], 
								 "className": "hidden-column" 
					        	} 
			                ]		
			         });						
		}		
		     	 	      	
	}
};


async function buildSelectedTeamMembersList(){
	var containerId = "team_member_list_container";
	var tableId = containerId+"_table_id";
	var tableBody = document.getElementById(tableId).childNodes[1];
	var selectCheckboxBasedJsonArray = [];
	for(var gg=0 ; gg < tableBody.childNodes.length;gg++){
		var checkboxInputTag = tableBody.childNodes[gg].childNodes[0].childNodes[0];		
		if(checkboxInputTag.checked){			
			var jsonObj = await buildDynamicChexBoxColumnTableJsonObj(checkboxInputTag);		
			selectCheckboxBasedJsonArray.push(jsonObj);	
		}		
	}
	return selectCheckboxBasedJsonArray;
};

/*****************************************Update *********************************************** */
async function addTeamMembersToAdmin(){	
	var canContinue	= true;
    var jsonObj = JSON.parse("{}");
	var selectTeamMemberJsonArray = await buildSelectedTeamMembersList();
	jsonObj['Admin User ID'] = document.getElementById("user_entry_user_id_txt").value;
	jsonObj['SelectedTeamMember'] = JSON.stringify(selectTeamMemberJsonArray);
	   
	if(canContinue){  		
	    let url = "/fsm/addTeamMembersToAdmin";
		let itemName = "addTeamMembersToAdmin";
	    await getDataFromServicePoint(url,jsonObj)
	        .then(async data => await populateTeamMemberDetailsVResponse(data)) 
	        .catch(error => handleError(itemName,error));
	}
};

async function populateTeamMemberDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){	
		toastr.success("Successfully Add Team Members","Completed", {closeButton: !0,tapToDismiss: !1});
		closeTeamMemberEntryForm();		
		showUsersListForm('');			
	}else{
		toastr.warning("Invalid TeamMember Details","Warning", {closeButton: !0,tapToDismiss: !1});
	}
};

