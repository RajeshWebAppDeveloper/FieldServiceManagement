
function getProductListForm(){
    var form = `
	<div class="content">
	    <div class="row mb-3">
	        <div class="col-sm-4 col-3">
	            <h2 class="page-title">Products Details</h2>
	        </div>
	        <div class="col-sm-8 col-9 text-end m-b-20">
	            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="getProductList('products_list_form')">
	                <i class="fas fa-refresh"></i> Refresh
	            </a>
				<a href="#" class="btn btn-primary float-right btn-rounded" onclick="importProductCSVfile()">
	                <i class="fas fa-file-import"></i> Import
	            </a>
	            <a href="#" class="btn btn-primary float-right btn-rounded" onclick="showProductEntryForm('add')">
	                <i class="fas fa-plus"></i> Add Product
	            </a>
	        </div>
	    </div>

	    <!-- Filter Form -->
	    <div class="buy-form">
	        <div class="col-12">
	            <div class="card">
	                <div class="card-body">
	                    <form id="product_list_filter_form" class="row g-3" autocomplete="off">
	                        <div class="col-md-3">
	                            <label for="product_list_filter_productId" class="form-label">Product ID</label>
	                            <input type="text" class="form-control" id="product_list_filter_productId" placeholder="Enter Product ID" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="product_list_filter_productName" class="form-label">Product Name</label>
	                            <input type="text" class="form-control" id="product_list_filter_productName" placeholder="Enter Product Name" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="product_list_filter_productCategory" class="form-label">Product Category</label>
	                            <input type="text" class="form-control" id="product_list_filter_productCategory" placeholder="Enter Product Category" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="product_list_filter_createdBy" class="form-label">Created By</label>
	                            <input type="text" class="form-control" id="product_list_filter_createdBy" placeholder="Enter Created By" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="product_list_filter_createdFromDate" class="form-label">Created From Date</label>
	                            <input type="datetime-local" class="form-control" id="product_list_filter_createdFromDate" autocomplete="off">
	                        </div>
	                        <div class="col-md-3">
	                            <label for="product_list_filter_createdToDate" class="form-label">Created To Date</label>
	                            <input type="datetime-local" class="form-control" id="product_list_filter_createdToDate" autocomplete="off">
	                        </div>

	                        <!-- Filter and Clear Buttons -->
	                        <div class="col-12 text-end mt-3">
							<button type="button" class="btn btn-secondary" onclick="clearProductFilterInputs()">Clear Filters</button>
	                            <button type="button" class="btn btn-primary" onclick="filterProduct()">Filter</button>
	                            
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
	                    <div class="table-responsive" id="product_list_table_container" class="dt-container dt-bootstrap5 dt-empty-footer"></div>                                       
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
 
`;
    return form;
};


function showProductListForm(backMethod){
    try{
		
        hideAllLayer();	
        var containerId = "product_list_form";         
        document.getElementById(containerId).style.display = "block";
		if(backMethod != "true"){		
			getProductList(containerId);	
		}		
    }catch(exp){
        alert(exp);
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};


async function getProductList(containerId){
    var jsonObj = JSON.parse("{}");
    jsonObj['ID'] = "all";      	
    let url = "/fsm/getProductDetailsList";
	let itemName= "getProductDetailsList";
    getDataFromServicePoint(url,jsonObj)
        .then(async data => await populateProductListVResponse(data,containerId)) 
        .catch(error => handleError(itemName,error));
};


async function populateProductListVResponse(vResponse,containerId){		
    if(vResponse.status == "true"){
		var dataArray = vResponse.data;		
		var editFunction = "editProductDetails(this)";
		var deleteFunction = "deleteProductDetails(this)";
		var tableId = containerId+"_table_id";
		document.getElementById("product_list_table_container").innerHTML = await createDataTable(vResponse,editFunction,deleteFunction,tableId);
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


function clearProductFilterInputs() {
    document.getElementById('product_list_filter_form').reset();
};

async function filterProduct() {   
    var canContinue = true;
    var jsonObj = {}; // Initialize as an empty object

    // Populate jsonObj with expected fields matching Java method parameters
    jsonObj['productId'] = document.getElementById("product_list_filter_productId").value;
    jsonObj['productName'] = document.getElementById("product_list_filter_productName").value;
    jsonObj['productCategory'] = document.getElementById("product_list_filter_productCategory").value;

    // Get createdFromDate and createdToDate as ISO strings, if available
    const createdFromDateElem = document.getElementById("product_list_filter_createdFromDate");
    const createdToDateElem = document.getElementById("product_list_filter_createdToDate");

    if (createdFromDateElem.value) {
        jsonObj['createdFromDate'] = new Date(createdFromDateElem.value).toISOString();
    }
    if (createdToDateElem.value) {
        jsonObj['createdToDate'] = new Date(createdToDateElem.value).toISOString();
    }

    jsonObj['createdBy'] = document.getElementById("product_list_filter_createdBy").value;

    if (canContinue) {                
        const url = "/fsm/getFilterProductDetailsList";
        const itemName = "getFilterProductDetailsList";

        // Call the backend with the constructed JSON object
        await getDataFromServicePoint(url, jsonObj)
            .then(async data => await populateProductListVResponse(data, "product_list_form"))
            .catch(error => handleErrorForList(itemName,error,'product_list_form'));
    }
};

async function importProductCSVfile() {
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
            const response = await fetch('/fsm/importProductCSVfile', {  // Corrected the endpoint
                method: 'POST',
                body: formData
            });

            if (response.ok) {
                const vResponseObj = await response.json();
                console.log(vResponseObj);

                if (vResponseObj.status === "true") {
                    toastr.success("Successfully Imported.", "Completed", { closeButton: true, tapToDismiss: false });
                    showProductListForm('');
                } else {
                    toastr.warning(vResponseObj.message || "Importing process failed.", "Warning", { closeButton: true, tapToDismiss: false });
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

