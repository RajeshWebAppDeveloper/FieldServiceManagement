
function getDefaultPropertiesListForm(){
    var form = `
	<div class="content">
	    <div class="row mb-3">
	        <div class="col-sm-4 col-3">
	            <h2 class="page-title">Default Properties</h2>
	        </div>
	        <div class="col-sm-8 col-9 text-end m-b-20">
	            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="getDefaultPropertiesList('categorys_list_form')">
	                <i class="fas fa-refresh"></i> Refresh
	            </a>
	            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="showDefaultPropertiesEntryForm('add')">
	                <i class="fas fa-plus"></i> Add Property
	            </a>
	        </div>
	    </div>
	    <div class="buy-form">                          
	        <div class="col-12">
	            <div class="card">                                                                                                              
	                <div class="card-body">
	                    <div class="table-responsive" id="defaultProperties_list_table_container" class="dt-container dt-bootstrap5 dt-empty-footer"></div>                                      
	                </div>
	            </div>
	        </div>
	    </div>
	</div>`;
    return form;
};


function showDefaultPropertiesListForm(backMethod){
    try{
        hideAllLayer();
        var containerId = "defaultProperties_list_form";         
        document.getElementById(containerId).style.display = "block";
		if(backMethod != "true"){			
			getDefaultPropertiesList(containerId);	
		}		
    }catch(exp){
        alert(exp);
    }
};



async function getDefaultPropertiesList(containerId){
    var jsonObj = JSON.parse("{}");
    jsonObj['ID'] = "";        
    let url = "/fsm/getDefaultPropertiesList";
	let itemName= "getDefaultPropertiesList";
    getDataFromServicePoint(url,jsonObj)
        .then(async data => await populateDefaultPropertiesListVResponse(data,containerId)) 
        .catch(error => handleError(itemName,error));
};

async function populateDefaultPropertiesListVResponse(vResponse,containerId){
    if(vResponse.status == "true"){
		var dataArray = vResponse.data;		
		var editFunction = "editDefaultPropertiesDetails(this)";
		var deleteFunction = "deleteDefaultPropertiesDetails(this)";
		var tableId = containerId+"_table_id";		
			
		document.getElementById("defaultProperties_list_table_container").innerHTML = createDataTable(vResponse,editFunction,deleteFunction,tableId);
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
};
