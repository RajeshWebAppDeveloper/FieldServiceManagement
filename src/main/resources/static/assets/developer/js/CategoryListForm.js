
function getCategoryListForm(){
    var form = `
	<div class="content">
	    <div class="row mb-3">
	        <div class="col-sm-4 col-3">
	            <h2 class="page-title">Categorys Details</h2>
	        </div>
	        <div class="col-sm-8 col-9 text-end m-b-20">
				
	            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="getCategoryList('categorys_list_form')">
	                <i class="fas fa-refresh"></i> Refresh
	            </a>
				
				<a href="#" class="btn btn-primary float-right btn-rounded" onclick="importCategoryCSVfile()">
	                <i class="fas fa-file-import"></i> Import
	            </a>
				
	            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="showCategoryEntryForm('add')">
	                <i class="fas fa-plus"></i> Add Category
	            </a>
	        </div>
	    </div>

	    <!-- Filter Form -->
	    <div class="buy-form">
	        <div class="col-12">
	            <div class="card">
	                <div class="card-body">
	                    <form id="category_list_filter_form" class="row g-3" autocomplete="off">
	                        <div class="col-md-3">
	                            <label for="category_list_filter_categoryId" class="form-label">Category ID</label>
	                            <input type="text" class="form-control" id="category_list_filter_categoryId" placeholder="Enter Category ID" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="category_list_filter_categoryName" class="form-label">Category Name</label>
	                            <input type="text" class="form-control" id="category_list_filter_categoryName" placeholder="Enter Category Name" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="category_list_filter_createdFromDate" class="form-label">Created From Date</label>
	                            <input type="datetime-local" class="form-control" id="category_list_filter_createdFromDate" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="category_list_filter_createdToDate" class="form-label">Created To Date</label>
	                            <input type="datetime-local" class="form-control" id="category_list_filter_createdToDate" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="category_list_filter_createdBy" class="form-label">Created By</label>
	                            <input type="text" class="form-control" id="category_list_filter_createdBy" placeholder="Enter Created By" autocomplete="off">
	                        </div>

	                        <!-- Filter and Clear Buttons -->
	                        <div class="col-12 text-end mt-3">
							<button type="button" class="btn btn-secondary" onclick="clearCategoryFilterInputs()">Clear Filters</button>
	                            <button type="button" class="btn btn-primary" onclick="filterCategory()">Filter</button>	                            
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
	                    <div class="table-responsive" id="category_list_table_container" class="dt-container dt-bootstrap5 dt-empty-footer"></div>                                      
	                </div>
	            </div>
	        </div>
	    </div>
	</div>

		
			`;
    return form;
};


function showCategoryListForm(backMethod){
    try{
		
        hideAllLayer();	
        var containerId = "category_list_form";         
        document.getElementById(containerId).style.display = "block";
		if(backMethod != "true"){		
			getCategoryList(containerId);	
		}		
    }catch(exp){
        alert(exp);
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};


async function getCategoryList(containerId){
    var jsonObj = JSON.parse("{}");
    jsonObj['ID'] = "all";      	
    let url = "/fsm/getCategoryDetailsList";
	let itemName= "getCategoryDetailsList";
    getDataFromServicePoint(url,jsonObj)
        .then(async data => await populateCategoryListVResponse(data,containerId)) 
        .catch(error => handleError(itemName,error));
};


async function populateCategoryListVResponse(vResponse,containerId){		
    if(vResponse.status == "true"){
		var dataArray = vResponse.data;
		
		var editFunction = "editCategoryDetails(this)";
		var deleteFunction = "deleteCategoryDetails(this)";
		var tableId = containerId+"_table_id";
		document.getElementById("category_list_table_container").innerHTML = await createDataTable(vResponse,editFunction,deleteFunction,tableId);
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


async function getCategoryListToSelectTagOption(containerId){
	var jsonObj = JSON.parse("{}");
    jsonObj['ID'] = "all";      	
    let url = "/fsm/getCategoryDetailsList";
	let itemName= "getCategoryDetailsList";
    getDataFromServicePoint(url,jsonObj)
        .then(async data => await populateCategoryListToSelectTagOptionVResponse(data,containerId)) 
        .catch(error => handleError(itemName,error));
};

function populateCategoryListToSelectTagOptionVResponse(vResponse,containerId){
	if(vResponse.status == "true"){
		var dataArray = vResponse.data;	
		var optionArray = new Array();
		for(var gg = 0 ; gg < dataArray.length;gg++){
			var categoryName = dataArray[gg]['Category'];
			var optionTag = `<option>`+categoryName+`</option>`;
			optionArray.push(optionTag);	
		}
		var optionArrayHTML = optionArray.join("");
		document.getElementById(containerId).innerHTML = optionArrayHTML;
	}
};




function clearCategoryFilterInputs() {
    document.getElementById('category_list_filter_form').reset();
};

async function filterCategory(){   
    var canContinue = true;
    var jsonObj = {}; // Initialize as an empty object

    // Populate jsonObj with expected fields matching Java method parameters
    jsonObj['categoryId'] = document.getElementById("category_list_filter_categoryId").value;
    jsonObj['categoryName'] = document.getElementById("category_list_filter_categoryName").value;

    // Get createdFromDate and createdToDate as ISO strings, if available
    const createdFromDateElem = document.getElementById("category_list_filter_createdFromDate");
    const createdToDateElem = document.getElementById("category_list_filter_createdToDate");

    if (createdFromDateElem.value) {
        jsonObj['createdFromDate'] = new Date(createdFromDateElem.value).toISOString();
    }
    if (createdToDateElem.value) {
        jsonObj['createdToDate'] = new Date(createdToDateElem.value).toISOString();
    }

    jsonObj['createdBy'] = document.getElementById("category_list_filter_createdBy").value;
    
    // Verify all necessary fields are filled before sending request
    if(canContinue){                
        const url = "/fsm/getFilterCategoryDetailsList";
        const itemName = "getFilterCategoryDetailsList";

        // Call the backend with the constructed JSON object
        await getDataFromServicePoint(url, jsonObj)
            .then(async data => await populateCategoryListVResponse(data,"category_list_form"))
            .catch(error => handleErrorForList(itemName,error,'category_list_table_container'));
    }
};


async function importCategoryCSVfile() {
    
    var fileInput = document.createElement("INPUT");    
    fileInput.setAttribute("type", "file");
    fileInput.setAttribute("accept", ".csv"); 
	
    fileInput.addEventListener("change", async () => {
        if (!fileInput.files.length) {
            toastr.warning("Please select a file before submitting.", "Warning", { closeButton: true, tapToDismiss: false });
            return;
        }

        const file = fileInput.files[0];
        const formData = new FormData();
        formData.append('file', file);

        try {       
            const response = await fetch('/fsm/importCategoryCSVfile', {
                method: 'POST',
                body: formData
            });

            if (response.ok) {
                const vResponseObj = await response.json();
                console.log(vResponseObj);
                
                if (vResponseObj.status === "true") {
                    toastr.success("Successfully Imported.", "Completed", { closeButton: true, tapToDismiss: false });
                    showCategoryListForm('');
                } else {
                    toastr.warning("Importing process failed.", "Warning", { closeButton: true, tapToDismiss: false });
                }
            } else {
                console.error('Error importing file:', response.statusText);
                toastr.error("An error occurred during the import process.", "Error", { closeButton: true, tapToDismiss: false });
            }
        } catch (error) {
            console.error('Error:', error);
            toastr.error("An unexpected error occurred.", "Error", { closeButton: true, tapToDismiss: false });
        }
    });

    fileInput.click();
};
