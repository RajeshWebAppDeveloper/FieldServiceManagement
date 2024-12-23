

function getDarEntryForm(showType){
	var btnHTML = ``;									

	if(showType == "add") {
		
	    btnHTML = `
	        <div class="row">
	            <div class="col-12 col-lg-6 d-flex justify-content-lg-start justify-content-center mb-2 mb-lg-0">
	                <button class="btn btn-light w-100" data-bs-dismiss="modal" onclick="clearDarDetails()">Clear</button>
	            </div>
	            <div class="col-12 col-lg-6 d-flex justify-content-lg-end justify-content-center">
	                <button class="btn btn-primary w-100" data-bs-dismiss="modal" onclick="updateDarDetails()">Save</button>
	            </div>
	        </div>`;
	} else if(showType == "edit") {		
	    btnHTML = `
	        <div class="row">
	            <div class="col-12 col-lg-4 d-flex justify-content-lg-start justify-content-center mb-2 mb-lg-0">
	                <button class="btn btn-primary w-100" data-bs-dismiss="modal" style="background:#fe3f51;" onclick="deleteDarDetails('')">Delete</button>
	            </div>
	            <div class="col-12 col-lg-4 d-flex justify-content-lg-center justify-content-center mb-2 mb-lg-0">
	                <button class="btn btn-light w-100" data-bs-dismiss="modal" onclick="clearDarDetails()">Clear</button>
	            </div>
	            <div class="col-12 col-lg-4 d-flex justify-content-lg-end justify-content-center">
	                <button class="btn btn-primary w-100" data-bs-dismiss="modal" onclick="updateDarDetails()">Save</button>
	            </div>
	        </div>`;
	}

	var form = `<div class="content">
			     			
                	<div class="row align-items-center">
	                    <div class="col"></div>
	                    <div class="col-auto">	                            	
	                        <a href="#" class="btn btn-primary btn-rounded float-right" onclick="showDarListForm('')"><i class="fas fa-arrow-left"></i> Back to Table</a>
	                    </div>
                	</div>
                	<div class="row align-items-center">
                    	<div class="col">
                        	<h2 class="page-title">DAR Process</h2>
                    	</div>                    
                	</div>                
                	<div class="row">
                    	<div class="card-box">                        
                            	<div class="row">
									    <div class="col-12 col-lg-6">
									        <div class="mb-3">
									            <label for="fsm_dar_detail_darNO" class="form-label">DAR.NO</label>
									            <input type="hidden" class="form-control" id="fsm_dar_detail_uuid_id" name="UUID">
									            <input type="text" class="form-control" id="fsm_dar_detail_darNO" name="darNO"  placeholder="Auto-Generate" disabled>
									        </div>
									        <div class="mb-3">
									            <label for="fsm_dar_detail_darProcessDate" class="form-label">DAR Process Date</label>
									            <input type="date" class="form-control" id="fsm_dar_detail_darProcessDate" name="darProcessDate" data-mask="00/00/0000 00:00:00" autocomplete="off">
									        </div>									        
									        <div class="mb-3">
									            <label for="fsm_dar_detail_stateCumArea" class="form-label">State Cum Area</label>
									            <input type="text" class="form-control" id="fsm_dar_detail_stateCumArea" name="stateCumArea">
									        </div>
									        <div class="mb-3">
									            <label for="fsm_dar_detail_plannedActivity" class="form-label">Planned Activity</label>
									            <textarea class="form-control" id="fsm_dar_detail_plannedActivity" name="plannedActivity"></textarea>
									        </div>
									        <div class="mb-3">
									            <label for="fsm_dar_detail_deliveryPlaceNameAndAddress" class="form-label">Delivery Place Name And Address</label>
									            <textarea class="form-control" id="fsm_dar_detail_deliveryPlaceNameAndAddress" name="deliveryPlaceNameAndAddress"></textarea>
									        </div>
											<div class="mb-3">
									            <label for="fsm_dar_detail_remarks" class="form-label">Remarks</label>
									            <textarea class="form-control" id="fsm_dar_detail_remarks" name="remarks"></textarea>
									        </div>
											<div class="mb-3">
									            <label for="fsm_dar_detail_statusToVisit" class="form-label">Status To Visit</label>
									            <select class="form-select" id="fsm_dar_detail_statusToVisit" name="statusinvisit">
													<option></option>
													<option>Demo success</option>
													<option>Product delivered</option>
												</select>
									        </div>
									    </div>
									    
									    <div class="col-12 col-lg-6">
									        <div class="mb-3">
									            <label for="fsm_dar_detail_clientName" class="form-label">Client Name</label>
									            <input type="text" class="form-control" id="fsm_dar_detail_clientName" name="clientName">
									        </div>
									        <div class="mb-3">
									            <label for="fsm_dar_detail_clientMobileNO" class="form-label">Client Mobile No</label>
									            <input type="text" class="form-control" id="fsm_dar_detail_clientMobileNO" name="clientMobileNO" data-mask="(00) 00000-0000">
									        </div>
									        <div class="mb-3">
									            <label for="fsm_dar_detail_fromLocation" class="form-label">From Location</label>
									            <input type="text" class="form-control" id="fsm_dar_detail_fromLocation" name="fromLocation">
									        </div>
									        <div class="mb-3">
									            <label for="fsm_dar_detail_toLocation" class="form-label">To Location</label>
									            <input type="text" class="form-control" id="fsm_dar_detail_toLocation" name="toLocation">
									        </div>
									        <div class="mb-3">
									            <label for="fsm_dar_detail_aboutTheClient" class="form-label">About the Client</label>
									            <textarea class="form-control" id="fsm_dar_detail_aboutTheClient" name="aboutTheClient"></textarea>
									        </div>
									        <div class="mb-3">
									            <label for="fsm_dar_detail_productDetails" class="form-label">Product Details</label>
									            <textarea class="form-control" id="fsm_dar_detail_productDetails" name="productDetails"></textarea>
									        </div>
									    </div>
									</div>
							<div class="card-body">	                    
								    <div class="row">
								        <div class="col-sm-12">
								            <div class="card mb-0">
								                <div class="card-body">
								                    <div class="table-responsive">
														<div class="row">
															<div class="col-6">
																<h2 class="card-title">Add Expenses</h2>
															</div>
															<div class="col-6">
																<div class="text-sm-end">
																	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#taskModal" onclick="dynamicExpenseRow()">Create</button>
																</div>
															</div>
														</div>
								                        <table class="table table-center table-hover datatable">
								                            <thead class="thead-light">
								                                <tr>
								                                    <th class="form-label">Serial No</th>
								                                    <th class="form-label">Expense Description</th>
								                                    <th class="form-label">Amount</th>
								                                    <th class="form-label">Image</th>
								                                    <th class="form-label">Delete</th>
								                                </tr>
								                            </thead>
								                            <tbody id="expense_tbody_id"></tbody>
								                            <tfoot>
								                                <tr>
								                                    <td colspan="1"></td>
								                                    <td class="form-label">Total Expense:</td>
								                                    <td colspan="2" id="total_expense_id">0.00</td>
								                                </tr>
								                            </tfoot>
								                        </table>
								                    </div>
								                </div>
								            </div>	
								        </div>    
								    </div>                    
								</div>			                                              	            
								<div class="row">
								    <div class="mb-0 mt-3"> `+btnHTML+`</div>
								</div>
                                            
                    </div>                    
                </div>  
                <div class="backgroundFormEnterBodyContainerCls"></div>
                                                         
            </div>`;
	return form;
};


																	


function showDarEntryForm(showType){
    try{
        hideAllLayer();
		var containerId = "dar_entry_form";
        document.getElementById(containerId).innerHTML = getDarEntryForm(showType);		
        document.getElementById(containerId).style.display = "block";			
		if(showType != "edit"){
			getUUIDForDarDetails();	
		}		
				
		createOptionTagInSelectTag("fsm_dar_detail_statusToVisit",dar_StatusToVisitArrayString);
    }catch(exp){        
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
    }
};

async function getUUIDForDarDetails(){
	var canContinue	= true;
    var jsonObj = JSON.parse("{}");
	jsonObj['ID'] = "";
			
	if(canContinue){  		
	    let url = "/fsm/generateUUID";
		let itemName = "generateUUID";
	    await getDataFromServicePoint(url,jsonObj)
	        .then(async data => await populateUUIDDarDetailsVResponse(data)) 
	        .catch(error => handleError(itemName,error));
	}
};

function populateUUIDDarDetailsVResponse(vResponseObj){	
    if(vResponseObj.status == "true"){		
		document.getElementById("fsm_dar_detail_uuid_id").value = vResponseObj.id; 							        		
	}else{
		toastr.warning("UUID its not Generate","Warning", {closeButton: !0,tapToDismiss: !1});
	}
};


/*****************************************Update *********************************************** */
async function updateDarDetails(){	
	var canContinue	= true;
    var jsonObj = JSON.parse("{}");
	jsonObj['ID'] = document.getElementById("fsm_dar_detail_uuid_id").value;
	jsonObj['DAR.NO'] = document.getElementById("fsm_dar_detail_darNO").value;	
    jsonObj['DAR Process Date'] = document.getElementById("fsm_dar_detail_darProcessDate").value;	
	jsonObj['State Cum Area'] = document.getElementById("fsm_dar_detail_stateCumArea").value;
	jsonObj['Planned Activity']  = document.getElementById("fsm_dar_detail_plannedActivity").value;
	jsonObj['Delivery Place Name And Address'] = document.getElementById("fsm_dar_detail_deliveryPlaceNameAndAddress").value;
	jsonObj['Remarks'] = document.getElementById("fsm_dar_detail_remarks").value;
	jsonObj['Client Name'] = document.getElementById("fsm_dar_detail_clientName").value;
	jsonObj['Client Mobile No'] = document.getElementById("fsm_dar_detail_clientMobileNO").value;		
    jsonObj['From Location'] = document.getElementById("fsm_dar_detail_fromLocation").value;
	jsonObj['To Location'] = document.getElementById("fsm_dar_detail_toLocation").value;
	jsonObj['About the Client'] = document.getElementById("fsm_dar_detail_aboutTheClient").value;
	jsonObj['Product Details'] = document.getElementById("fsm_dar_detail_productDetails").value;
	jsonObj['Status To Visit'] = document.getElementById("fsm_dar_detail_statusToVisit").value;		
	jsonObj['Total Expenses'] = document.getElementById('total_expense_id').innerText;		
	jsonObj['Created Date'] = new Date();
	jsonObj['Created By'] = logginerUserId;
		   
	/*var inputNames = "Dar"; 
	var inputTypes = "text";
	var inputElementIds = "fsm_dar_detail_darProcessDate";
	canContinue ? canContinue = await checkValidationDynamicInputRow(inputNames,inputTypes,inputElementIds): canContinue = false;*/
	if(canContinue){  		
	    let url = "/fsm/updateDarDetails";
		let itemName = "updateDarDetails";
	    await getDataFromServicePoint(url,jsonObj)
	        .then(async data => await populateDarDetailsVResponse(data)) 
	        .catch(error => handleError(itemName,error));
	}
};

async function populateDarDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){		
		updateDarExpensesDetails();							        		
	}else{
		toastr.warning("Invalid Dar Details","Warning", {closeButton: !0,tapToDismiss: !1});
	}
};

/*****************************************Clear *********************************************** */
function clearDarDetails(){    
	document.getElementById("fsm_dar_detail_darNO").value= "";	
	document.getElementById("fsm_dar_detail_darProcessDate").value= "";	
	document.getElementById("fsm_dar_detail_stateCumArea").value= "";
	document.getElementById("fsm_dar_detail_plannedActivity").value= "";
	document.getElementById("fsm_dar_detail_deliveryPlaceNameAndAddress").value= "";
	document.getElementById("fsm_dar_detail_remarks").value = "";
	document.getElementById("fsm_dar_detail_clientName").value = "";
	document.getElementById("fsm_dar_detail_clientMobileNO").value= "";		
	document.getElementById("fsm_dar_detail_fromLocation").value= "";
	document.getElementById("fsm_dar_detail_toLocation").value= "";
	document.getElementById("fsm_dar_detail_aboutTheClient").value= "";
	document.getElementById("fsm_dar_detail_productDetails").value= "";
	document.getElementById("fsm_dar_detail_statusToVisit").value = "";		
	document.getElementById('total_expense_id').innerText = "0.00";
	
	document.getElementById('expense_tbody_id').innerHTML = "";		   
};
/*****************************************Edit *********************************************** */


async function editDarDetails(vObj){
		
	var jsonObj = await buildDynamicChexBoxColumnTableJsonObj(vObj);	
	swal({
	        title: "Confirmation",
	        text: "Do you want to Edit ?",
	        icon: "info",
	        buttons: true,
	        dangerMode: true,
	 }).then((confirmation) => {
	        if (confirmation) {	
				showDarEntryForm('edit');
							
				let url = "/fsm/editDarDetails";
				let itemName= "editDarDetails";
			    getDataFromServicePoint(url,jsonObj)
			        .then(data => populateEditDarDetailsVResponse(data)) 
			        .catch(error => handleError(itemName,error));     
	        } 
	 });				
};

function populateEditDarDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){				
		var jsonObj = vResponseObj.data;						
		document.getElementById("fsm_dar_detail_uuid_id").value = jsonObj['ID'];
		document.getElementById("fsm_dar_detail_darNO").value = jsonObj['DAR.NO'];	
		document.getElementById("fsm_dar_detail_darProcessDate").value = jsonObj['DAR Process Date'] ;	
		document.getElementById("fsm_dar_detail_stateCumArea").value = jsonObj['State Cum Area'] ;
		document.getElementById("fsm_dar_detail_plannedActivity").value  = jsonObj['Planned Activity'];
		document.getElementById("fsm_dar_detail_deliveryPlaceNameAndAddress").value = jsonObj['Delivery Place Name And Address'] ;
		document.getElementById("fsm_dar_detail_remarks").value = jsonObj['Remarks']
		document.getElementById("fsm_dar_detail_clientName").value = jsonObj['Client Name'] ;
		document.getElementById("fsm_dar_detail_clientMobileNO").value = jsonObj['Client Mobile No'] ;		
		document.getElementById("fsm_dar_detail_fromLocation").value = jsonObj['From Location'] ;
		document.getElementById("fsm_dar_detail_toLocation").value = jsonObj['To Location'] ;
		document.getElementById("fsm_dar_detail_aboutTheClient").value = jsonObj['About the Client'] ;
		document.getElementById("fsm_dar_detail_productDetails").value = jsonObj['Product Details'] ;
		document.getElementById("fsm_dar_detail_statusToVisit").value = jsonObj['Status To Visit'] ;		
		document.getElementById('total_expense_id').innerText = jsonObj['Total Expenses'] ;
		var expensesJsonObj = vResponseObj.expensesData;
		loadDarExpenseRowsFromJson(expensesJsonObj);		
		
		
	}
};
/*****************************************Delete *********************************************** */
async function deleteDarDetails(vObj){
	var jsonObj = JSON.parse("{}");
	if(vObj == ""){
		jsonObj['ID'] = document.getElementById("fsm_dar_detail_uuid_id").value;		    		    		   
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
					let url = "/fsm/deleteDarDetails";
					let itemName= "deleteDarDetails";
	    			getDataFromServicePoint(url,jsonObj)
	        			.then(data => populateDeleteDarDetailsVResponse(data)) 
	        			.catch(error => handleError(itemName,error));
				}
		});	
				
};

function populateDeleteDarDetailsVResponse(vResponseObj){
    if(vResponseObj.status == "true"){			
		toastr.success("Deleted Successfully","Completed", {closeButton: !0,tapToDismiss: !1});
			
		showDarListForm('');		    		    		   	
	}
};




/*
const jsonObject = [
  { "id": 101, "reference_id": "ABC123","expenseDescription": "Office Supplies", "amount": 50.75, "image": null },
  { "id": 102, "reference_id": "XYZ456", "expenseDescription": "Travel Expenses", "amount": 200.00, "image": null },
  { "id": 103, "reference_id": "LMN789", "expenseDescription": "Meals", "amount": 75.50, "image": null }
];
*/
function loadDarExpenseRowsFromJson(jsonData) {
    const tbody = document.getElementById('expense_tbody_id');
		
    tbody.innerHTML = '';

    jsonData.forEach((item) => {
        const newRow = document.createElement('tr');
   		
        const hiddenIdInput = document.createElement('input');
        hiddenIdInput.type = 'hidden';
        hiddenIdInput.name = 'id';
        hiddenIdInput.value = item['ID'];

        const hiddenRefIdInput = document.createElement('input');
        hiddenRefIdInput.type = 'hidden';
        hiddenRefIdInput.name = 'reference_id';
        hiddenRefIdInput.value = item['Reference ID'];

        // Serial No
        const serialNoCell = document.createElement('td');
        serialNoCell.innerText = (tbody.childNodes.length)+1;

        // Expense Description
        const descriptionCell = document.createElement('td');
        const descriptionInput = document.createElement('input');
        descriptionInput.type = 'text';
        descriptionInput.className = 'form-control';
        descriptionInput.name = 'expenseDescription';
        descriptionInput.value = item['Expenses Description'];
        descriptionCell.appendChild(descriptionInput);

        // Amount
        const amountCell = document.createElement('td');
        const amountInput = document.createElement('input');
        amountInput.type = 'text';
        amountInput.className = 'form-control';
        amountInput.name = 'expenseAmount';
        amountInput.value = item['Expenses Amount'];
        amountInput.onkeyup = updateTotalExpense;  
        amountCell.appendChild(amountInput);

        // Image Upload & Preview
        const imageCell = document.createElement('td');
        const imageWrapper = document.createElement('div');
        imageWrapper.style.display = 'flex';
        imageWrapper.style.flexDirection = 'column';
        imageWrapper.style.alignItems = 'center';

        const imagePreview = document.createElement('img');
        imagePreview.style.width = '500px';
        imagePreview.style.height = '500px';
        imagePreview.style.objectFit = 'cover';
        imagePreview.style.display = item['Image File Path']? 'block' : 'none'; 
        imagePreview.src = item['Image File Path'] ? '/fsm/RetrieveFile/'+item['Image File Path'] : '#';

        const imageInput = document.createElement('input');
        imageInput.type = 'file';
        imageInput.className = 'form-control';
        imageInput.name = 'expenseImage';
        imageInput.onchange = function (event) {
            handleImagePreview(event, imagePreview);
        };

        
        imageWrapper.appendChild(imagePreview);
		imageWrapper.appendChild(imageInput);
        imageCell.appendChild(imageWrapper);

        // Delete Button
        const deleteCell = document.createElement('td');
        const deleteBtn = document.createElement('a');
        deleteBtn.href = '#';
        deleteBtn.className = 'form-control text-danger';
        deleteBtn.style = 'border:none;background:transparent;';
        deleteBtn.innerHTML = '<i class="far fa-trash-alt me-1"></i>';
        deleteBtn.onclick = function () {
            deleteDarExpensesDetails(hiddenIdInput);
        };
        deleteCell.appendChild(deleteBtn);

        // Append all cells to the new row
        newRow.appendChild(hiddenIdInput); // Hidden ID
        newRow.appendChild(hiddenRefIdInput); // Hidden Reference ID
        newRow.appendChild(serialNoCell);
        newRow.appendChild(descriptionCell);
        newRow.appendChild(amountCell);
        newRow.appendChild(imageCell); // Image Upload
        newRow.appendChild(deleteCell);

        // Add the new row to the tbody
        tbody.appendChild(newRow);
    });

    // Update the total expense after loading the rows
    updateTotalExpense();
};


function handleImagePreview(event, imagePreview) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            imagePreview.src = e.target.result;
            imagePreview.style.display = 'block'; // Show the preview
        };
        reader.readAsDataURL(file);
    }
};


function dynamicExpenseRow() {
	
    const tbody = document.getElementById('expense_tbody_id');
    
    const newRow = document.createElement('tr');
    
    const hiddenIdInput = document.createElement('input');
    hiddenIdInput.type = 'hidden';
    hiddenIdInput.name = 'id';
    hiddenIdInput.value = '';

    const hiddenRefIdInput = document.createElement('input');
    hiddenRefIdInput.type = 'hidden';
    hiddenRefIdInput.name = 'reference_id';
    hiddenRefIdInput.value = document.getElementById("fsm_dar_detail_uuid_id").value;

    const serialNoCell = document.createElement('td');
    serialNoCell.innerText = (tbody.childNodes.length)+1;

	const descriptionCell = document.createElement('td');
    const descriptionInput = document.createElement('input');
    descriptionInput.type = 'text';
    descriptionInput.className = 'form-control';
    descriptionInput.name = 'expenseDescription';
    descriptionCell.appendChild(descriptionInput);
        
    const amountCell = document.createElement('td');
    const amountInput = document.createElement('input');
    amountInput.type = 'text';
    amountInput.className = 'form-control';
    amountInput.name = 'expenseAmount';
    amountInput.placeholder = '0.00';
    amountInput.onkeyup = updateTotalExpense;
	amountInput.onkeypress = onlyNumberKey;
    amountCell.appendChild(amountInput);

    const imageCell = document.createElement('td');
    const imageWrapper = document.createElement('div');
    imageWrapper.style.display = 'flex';
    imageWrapper.style.flexDirection = 'column';
    imageWrapper.style.alignItems = 'center';

    const imagePreview = document.createElement('img');
    imagePreview.style.width = '500px';
    imagePreview.style.height = '500px';
    imagePreview.style.objectFit = 'cover';
    imagePreview.style.display = 'none'; 
    imagePreview.src = '#'; 

    const imageInput = document.createElement('input');
    imageInput.type = 'file';
    imageInput.className = 'form-control';
    imageInput.name = 'expenseImage';
    imageInput.onchange = function (event) {
        handleImagePreview(event, imagePreview);
    };

    
    imageWrapper.appendChild(imagePreview);
	imageWrapper.appendChild(imageInput);
    imageCell.appendChild(imageWrapper);

    const deleteCell = document.createElement('td');
    const deleteBtn = document.createElement('BUTTON');    
    deleteBtn.className = 'btn btn-primary text-danger';
    deleteBtn.style = 'border:none;background:none;';
    deleteBtn.innerHTML = '<i class="far fa-trash-alt me-1"></i>';
    deleteBtn.onclick = function () {
        deleteDarExpensesDetails(hiddenIdInput);
    };
    deleteCell.appendChild(deleteBtn);
    
    newRow.appendChild(hiddenIdInput); 
    newRow.appendChild(hiddenRefIdInput); 
    newRow.appendChild(serialNoCell);
    newRow.appendChild(descriptionCell);
    newRow.appendChild(amountCell);
    newRow.appendChild(imageCell); 
    newRow.appendChild(deleteCell);
    
    tbody.appendChild(newRow);
};

async function updateDarExpensesDetails() {
    const rows = document.querySelectorAll('#expense_tbody_id tr');
    const formData = new FormData();

    let expensesArray = [];

    rows.forEach((row, index) => {
        const expensesId = row.querySelector('input[name="id"]').value;
        const expensesReferenceId = row.querySelector('input[name="reference_id"]').value;
        const description = row.querySelector('input[name="expenseDescription"]').value;
        const amount = row.querySelector('input[name="expenseAmount"]').value;
        const imageInput = row.querySelector('input[name="expenseImage"]');
		if(imageInput.value != ""){
			const imageFile = imageInput.files[0];        
	        const expenseData = {
	            "ID": expensesId,
	            "Reference ID": expensesReferenceId,
	            "Expenses Description": description,
	            "Expenses Amount": amount,
				"Image File Path":""
	        };
	
	        expensesArray.push(expenseData);
	
	        
	        if (imageFile) {
	            formData.append(`imageFiles`, imageFile);
	        }

		}else{									        
			showDarListForm('');
			return;		
		}
     });

    formData.append("expenses", JSON.stringify(expensesArray));
	
    try {       
        const response = await fetch('/fsm/updateDarExpensesDetails', {
            method: 'POST',
            body: formData
        });

        if (response.ok) {
            const vResponseObj = await response.json();            
			if(vResponseObj.status === "true"){
				toastr.success("Successfully Updated", "Completed", {closeButton: !0,tapToDismiss: !1});						        
				showDarListForm('');		
			} else {
				toastr.warning("Invalid Dar Details", "Warning", {closeButton: !0,tapToDismiss: !1});
			}		
        } else {
            console.error('Error submitting expenses:', response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
};


async function deleteDarExpensesDetails(vObj){
	var row = vObj.parentNode;
	if(vObj.value != ""){
		var jsonObj = JSON.parse("{}");	
		jsonObj['ID'] = vObj.value;	
			
		swal({
		        title: "Confirmation",
		        text: "Do you want to delete ?",
		        icon: "warning",
		        buttons: true,
		        dangerMode: true,
		 }).then((confirmation) => {
	        if (confirmation) {
				let url = "/fsm/deleteDarExpensesDetails";
				let itemName= "deleteDarExpensesDetails";
    			getDataFromServicePoint(url,jsonObj)
        			.then(data => populateDeleteDarExpensesDetailsVResponse(data,row)) 
        			.catch(error => handleError(itemName,error));
			}
		});	
	}else{
		row.remove();
		updateTotalExpense(); 	
	}						
};

function populateDeleteDarExpensesDetailsVResponse(vResponseObj,row){
    if(vResponseObj.status == "true"){			
		row.remove();
		updateTotalExpense(); 		    		    		   
	}
};




function updateTotalExpense() {
    const tbody = document.getElementById('expense_tbody_id');
    let totalExpense = 0;
    
    tbody.querySelectorAll('tr').forEach((row) => {
        const amountInput = row.querySelector('input[name="expenseAmount"]');
        const amount = parseFloat(amountInput.value) || 0;
        totalExpense += amount;
    });
    
    document.getElementById('total_expense_id').innerText = totalExpense.toFixed(2);
};


//loadDarExpenseRowsFromJson(jsonObject);
