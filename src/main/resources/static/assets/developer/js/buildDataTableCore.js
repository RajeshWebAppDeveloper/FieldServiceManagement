
	 
function createDataTable(responseData,editFunction,deleteFunction,tableId){     

        var containerObj                        = document.createElement("table");
        var containerHeaderObj                  = document.createElement("thead");
        var containerBodyObj                    = document.createElement("tbody");      

try{            
        
        containerObj.setAttribute("class","table table-striped table-bordered dt-responsive nowrap w-100 datatable dataTable no-footer");
        containerObj.setAttribute("id",tableId);
        containerHeaderObj.setAttribute("class","thead-light");         
        
        containerObj.appendChild(containerHeaderObj);
        containerObj.appendChild(containerBodyObj);             
                
        var actualData = responseData.data;
        
        
        const createRow = () =>{
                var dataRow = document.createElement("TR");                     
                return dataRow;
        };

        const createColumn = (elementName,rowKey,rowValue,className) =>{                                

                var rowCell = document.createElement(elementName);
                rowCell.innerHTML = rowValue;
                rowCell.setAttribute("name",rowKey);
                //rowCell.setAttribute("title",rowValue);
                if(className != ""){
                        rowCell.setAttribute("class",className);        
                }
                
                return rowCell;
        };

        const dataRowActionColumn = () =>{
                var button = `<button class="btn btn-sm btn-white text-success me-2" onclick="`+editFunction+`"><i class="far fa-edit me-1"></i> Edit</button><buttonn class="btn btn-sm btn-white text-info me-2" onclick="`+deleteFunction+`"><i class="far fa-trash-alt me-1"></i> Delete</button>`;
        return button;
        };
        
        var headerArray = new Array();          
        for(var obj in actualData){
                if(actualData.hasOwnProperty(obj)){
                        var dataRow = createRow();
                        containerHeaderObj.appendChild(dataRow);
                        var gg = 0;
                        for(var prop in actualData[obj]){
                        if(actualData[obj].hasOwnProperty(prop)){
                                headerArray.push(prop);
                                console.log("HEADER :"+ gg +">>>"+ headerArray[gg]);
                                var columnData = createColumn("TH",prop,prop,"");                                       
                                dataRow.appendChild(columnData);                                        
                                gg++;
                        }                               
                    }
                    var columnActionData = createColumn("TH","Event Buttons","","text-end");
                dataRow.appendChild(columnActionData);
                        break;
           }
        }
        
        var gtgt =0;                            
        for(var obj in actualData){
                gtgt++;
                if(actualData.hasOwnProperty(obj)){
                        var dataRow = createRow();                              
                        containerBodyObj.appendChild(dataRow);
                        var gg=0;
                        for(var prop in actualData[obj]){
                        if(actualData[obj].hasOwnProperty(prop)){
                                var rowData = createColumn("TD",prop,actualData[obj][prop],"");
                                dataRow.appendChild(rowData);                                   
                                gg++;
                        }                               
                    }
                    var actionHTML = dataRowActionColumn();
                actionHTML = createColumn("TD","Event Buttons",actionHTML,"");
                dataRow.appendChild(actionHTML);
                }
        }                               
        
        }catch(exp){
                alert(exp);
        }

        return containerObj.outerHTML;
        
};

function searchTable(input,tableParentId) {
    var filter, tableBody, tr, td, i, txtValue;
    filter = input.value.toLowerCase();
    tableBody = document.getElementById(tableParentId).childNodes[0].childNodes[1];
    tr = tableBody.getElementsByTagName('tr');

    for (i = 0; i < tr.length; i++) {
        tr[i].style.display = "none";
        td = tr[i].getElementsByTagName('td');
        for (var j = 0; j < td.length; j++) {
            if (td[j]) {
                txtValue = td[j].textContent || td[j].innerText;
                if (txtValue.toLowerCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                    break;
                }
            }
        }
    }
};


/************************************************************************************** */


//var selectRecordStr = [];
/*
const statusClassMapping = {
    "accepted": "text-success font-weight-bold",
    "rejected": "text-danger font-weight-bold",
    "pending": "text-warning font-weight-bold",
    "in progress": "text-info font-weight-bold",
};


yellow : badge badge-subtle-warning
blud:    badge badge-subtle-info
red:     badge badge-subtle-danger
green:   badge badge-subtle-success
*/
function createDataTableWithCheckboxEditAndDelete(responseData, editFunction, deleteFunction, tableId, selectRecordStr, idField, imageOrStatusKeyJsonObj, statusClassMapping) {
    var containerObj = document.createElement("table");
    var containerHeaderObj = document.createElement("thead");
    var containerBodyObj = document.createElement("tbody");

    try {
        containerObj.setAttribute("class", "table table-striped table-bordered dt-responsive nowrap w-100 datatable dataTable no-footer");
        containerObj.setAttribute("id", tableId);
        containerHeaderObj.setAttribute("class", "thead-light");

        containerObj.appendChild(containerHeaderObj);
        containerObj.appendChild(containerBodyObj);

        var actualData = responseData.data;

        // Function to create a new row
        const createRow = () => {
            var dataRow = document.createElement("TR");
            return dataRow;
        };

        // Function to create a column (cell), handles both headers and data rows
        const createColumn = (elementName, rowKey, rowValue, className, isHeader = false) => {
            var rowCell = document.createElement(elementName);
            rowCell.setAttribute("name", rowKey);
            if (className) rowCell.setAttribute("class", className);

            // For header rows, just set the text content
            if (isHeader) {
                rowCell.innerHTML = rowValue;
                return rowCell;
            }

            // Handling for data rows
            if (imageOrStatusKeyJsonObj['image'] && rowKey === imageOrStatusKeyJsonObj['image']) {
                rowCell.innerHTML = `<img src="/fsm/RetrieveFile/${rowValue}" alt="Profile Image" width="50" height="50" style="border-radius:50px">`;

                if (rowValue == "") {
                    rowCell.innerHTML = `<img src="/assets/img/avatars/user.png" alt="Profile Image" width="50" height="50" style="border-radius:50px">`;
                }
            } else if (imageOrStatusKeyJsonObj['status'] && rowKey == imageOrStatusKeyJsonObj['status']) {
                let highlightClass = statusClassMapping[rowValue] || "badge badge-subtle-warning";
                if (highlightClass) {
                    var statusSpanTag = document.createElement("SPAN");
                    statusSpanTag.setAttribute("class", highlightClass);
                    statusSpanTag.setAttribute("style", "font-size:16px;font-weight:bold;");
                    statusSpanTag.innerHTML = rowValue;
                    rowCell.appendChild(statusSpanTag);
                } else {
                    rowCell.innerHTML = rowValue;
                }
            } else {
                rowCell.innerHTML = rowValue;
            }

            return rowCell;
        };

        // Create action buttons (Edit, Delete)
        const dataRowActionColumn = (rowId) => {
            var button = `
                <button class="btn btn-sm btn-white text-primary me-2" onclick="` + editFunction + `">
                    <i class="far fa-edit me-1"></i> Edit
                </button>
                <button class="btn btn-sm btn-white text-danger me-2" onclick="` + deleteFunction + `">
                    <i class="far fa-trash-alt me-1"></i> Delete
                </button>`;
            return button;
        };

        // Create header row
        var headerArray = [];
        var dataRow = createRow();
        containerHeaderObj.appendChild(dataRow);

        // Add checkbox header
        var checkboxHeader = createColumn("TH", "Select", `<input type="checkbox" id="selectAll" onclick="toggleSelectAll()">`, "", true);
        dataRow.appendChild(checkboxHeader);

        // Loop through properties to create header columns
        for (var prop in actualData[0]) {
            headerArray.push(prop);
            var columnData = createColumn("TH", prop, prop, "", true); // Pass 'true' for headers
            dataRow.appendChild(columnData);
        }

        // Add action header
        var columnActionData = createColumn("TH", "Event Buttons", "", "text-end", true); // Pass 'true' for headers
        dataRow.appendChild(columnActionData);

        // Loop through the data to create body rows
        actualData.forEach((obj, index) => {
            var dataRow = createRow();
            containerBodyObj.appendChild(dataRow);

            // Check if the current row's record is selected
            var isChecked = selectRecordStr.includes(obj[idField]) ? 'checked' : '';

            // Add checkbox in each row
            var checkboxData = createColumn("TD", "Select", `<input type="checkbox" class="rowCheckbox" name="${obj[idField]}" ${isChecked} onclick="toggleSelection('${obj[idField]}')">`, "");
            dataRow.appendChild(checkboxData);

            // Loop through each property to fill rows
            for (var prop in obj) {
                var rowData = createColumn("TD", prop, obj[prop], "");
                dataRow.appendChild(rowData);
            }

            // Add action buttons (Edit, Delete)
            var actionHTML = dataRowActionColumn(obj[idField]);
            actionHTML = createColumn("TD", "Event Buttons", actionHTML, "text-end");
            dataRow.appendChild(actionHTML);
        });
    } catch (exp) {
        alert(exp);
    }

    return containerObj.outerHTML;
};



// Updated toggleSelectAll function
function toggleSelectAll() {
    var checkboxes = document.querySelectorAll('.rowCheckbox');
    var selectAll = document.getElementById('selectAll');
    
    checkboxes.forEach(checkbox => {
        checkbox.checked = selectAll.checked;
        var recordId = checkbox.getAttribute('name');
        toggleSelection(recordId, selectAll.checked); // Pass both recordId and selection status
    });
};

// Updated toggleSelection function
function toggleSelection(recordId, isSelected) {
    if (isSelected === undefined) {  
        // Fetch the checkbox status dynamically if not provided
        isSelected = document.querySelector(`input.rowCheckbox[name="${recordId}"]`).checked;
    }

    // Ensure selectRecordStr is an array that stores selected record IDs
    if (!Array.isArray(selectRecordStr)) {
        selectRecordStr = [];
    }

    if (isSelected) {
        if (!selectRecordStr.includes(recordId)) {
            selectRecordStr.push(recordId);
        }
    } else {
        selectRecordStr = selectRecordStr.filter(id => id !== recordId);
    }

    console.log("Selected Records: ", selectRecordStr);
};

/***************************************************************************************************************************************/
/*const selectOptionsMapping = {
    status: ["Active", "Inactive", "Pending"],
    role: ["Admin", "User", "Guest"] // for any other dropdowns
};
*/
function createDataTableWithCheckboxEdit_Delete_DropDown(responseData, exportFunction, editFunction, deleteFunction, tableId, selectRecordStr, idField, imageOrStatusKeyJsonObj, statusClassMapping, selectOptionsMapping, selectOptionsBasedOnChangeFunction) {
    var containerObj = document.createElement("table");
    containerObj.className = "table table-striped table-bordered dt-responsive nowrap w-100 datatable dataTable no-footer";
    containerObj.id = tableId;

    var containerHeaderObj = document.createElement("thead");
    containerHeaderObj.className = "thead-light";
    var containerBodyObj = document.createElement("tbody");

    containerObj.appendChild(containerHeaderObj);
    containerObj.appendChild(containerBodyObj);

    var actualData = responseData.data;

    const createRow = () => document.createElement("TR");

    const createColumn = (elementName, rowKey, rowValue, className, isHeader = false) => {
        var rowCell = document.createElement(elementName);
        rowCell.setAttribute("name", rowKey);
        if (className) rowCell.className = className;

        if (isHeader) {
            rowCell.innerHTML = rowValue;
        } else {
            if (rowKey === imageOrStatusKeyJsonObj['image']) {
                rowCell.innerHTML = rowValue 
                    ? `<img src="/fsm/RetrieveFile/${rowValue}" alt="Profile Image" width="50" height="50" style="border-radius:50px">` 
                    : `<img src="/assets/img/avatars/user.png" alt="Profile Image" width="50" height="50" style="border-radius:50px">`;
            } else if (rowKey === imageOrStatusKeyJsonObj['status']) {
                let highlightClass = statusClassMapping[rowValue] || "badge badge-subtle-info";
                rowCell.innerHTML = highlightClass 
                    ? `<span class="${highlightClass}" style="font-size:16px;font-weight:bold;">${rowValue}</span>` 
                    : rowValue;
            } else if (selectOptionsMapping[rowKey]) {
                let selectElem = document.createElement("select");
                selectElem.className = "form-select";
                selectElem.setAttribute("onchange",selectOptionsBasedOnChangeFunction);             
                selectOptionsMapping[rowKey].forEach(optionValue => {
                    let optionElem = document.createElement("option");                    
                    optionElem.value = optionValue;
                    optionElem.text = optionValue;
                    if (optionValue === rowValue) {
                        optionElem.setAttribute("selected","true");
                    }
                    selectElem.appendChild(optionElem);
                });
                rowCell.appendChild(selectElem);
            } else {
                rowCell.innerHTML = rowValue;
            }
        }
        return rowCell;
    };

    // Create header row
    var headerRow = createRow();
    containerHeaderObj.appendChild(headerRow);

    // Add checkbox header
    var checkboxHeader = createColumn("TH", "Select", `<input type="checkbox" id="selectAll" onclick="toggleSelectAll()">`, "", true);
    headerRow.appendChild(checkboxHeader);

    // Add data headers
    Object.keys(actualData[0]).forEach((prop) => {
        headerRow.appendChild(createColumn("TH", prop, prop, "", true));
    });

    headerRow.appendChild(createColumn("TH", "Event Buttons", "", "text-end", true));
    
    const createActionButtons = (rowId) => {
        let exportSelect = document.createElement("select");
        exportSelect.className = "btn btn-sm text-primary me-2";        
        exportSelect.innerHTML = '<option>pdf</option><option>excel</option>';

        let exportButton = document.createElement("button");
        exportButton.className = "btn btn-sm btn-white text-primary me-2";        
		exportButton.setAttribute("onclick",exportFunction);
        exportButton.innerHTML = '<i class="fa fa-file-export me-1"></i> Export';
		
        let editButton = document.createElement("button");
        editButton.className = "btn btn-sm btn-white text-primary me-2";
        editButton.setAttribute("onclick",editFunction);
        editButton.innerHTML = '<i class="far fa-edit me-1"></i> Edit';

        let deleteButton = document.createElement("button");
        deleteButton.className = "btn btn-sm btn-white text-danger me-2";        
		deleteButton.setAttribute("onclick",deleteFunction);
        deleteButton.innerHTML = '<i class="far fa-trash-alt me-1"></i> Delete';

        let actionContainer = document.createElement("TD");
        actionContainer.className = "text-end";
        actionContainer.append(exportSelect, exportButton, editButton, deleteButton);

        return actionContainer;
    };

    // Populate table rows
    actualData.forEach((obj) => {
        var dataRow = createRow();
        containerBodyObj.appendChild(dataRow);

        // Checkbox column
        let isChecked = selectRecordStr.includes(obj[idField]) ? 'checked' : '';
        dataRow.appendChild(createColumn("TD", "Select", `<input type="checkbox" class="rowCheckbox" name="${obj[idField]}" ${isChecked} onclick="toggleSelection('${obj[idField]}')">`, ""));

        // Data columns
        Object.entries(obj).forEach(([key, value]) => {
            dataRow.appendChild(createColumn("TD", key, value, ""));
        });

        // Action buttons column
        dataRow.appendChild(createActionButtons(obj[idField]));
    });

    return containerObj.outerHTML;
};



function createDataTableWithCheckbox_Delete_DropDown(responseData, exportFunction,deleteFunction, tableId, selectRecordStr, idField, imageOrStatusKeyJsonObj, statusClassMapping, selectOptionsMapping, selectOptionsBasedOnChangeFunction) {
    var containerObj = document.createElement("table");
    containerObj.className = "table table-striped table-bordered dt-responsive nowrap w-100 datatable dataTable no-footer";
    containerObj.id = tableId;

    var containerHeaderObj = document.createElement("thead");
    containerHeaderObj.className = "thead-light";
    var containerBodyObj = document.createElement("tbody");

    containerObj.appendChild(containerHeaderObj);
    containerObj.appendChild(containerBodyObj);

    var actualData = responseData.data;

    const createRow = () => document.createElement("TR");

    const createColumn = (elementName, rowKey, rowValue, className, isHeader = false) => {
        var rowCell = document.createElement(elementName);
        rowCell.setAttribute("name", rowKey);
        if (className) rowCell.className = className;

        if (isHeader) {
            rowCell.innerHTML = rowValue;
        } else {
            if (rowKey === imageOrStatusKeyJsonObj['image']) {
                rowCell.innerHTML = rowValue 
                    ? `<img src="/fsm/RetrieveFile/${rowValue}" alt="Profile Image" width="50" height="50" style="border-radius:50px">` 
                    : `<img src="/assets/img/avatars/user.png" alt="Profile Image" width="50" height="50" style="border-radius:50px">`;
            } else if (rowKey === imageOrStatusKeyJsonObj['status']) {
                let highlightClass = statusClassMapping[rowValue] || "badge badge-subtle-warning";
                rowCell.innerHTML = highlightClass 
                    ? `<span class="${highlightClass}" style="font-size:16px;font-weight:bold;">${rowValue}</span>` 
                    : rowValue;
            } else if (selectOptionsMapping[rowKey]) {
                let selectElem = document.createElement("select");
                selectElem.className = "form-select";
                selectElem.setAttribute("onchange",selectOptionsBasedOnChangeFunction);             
                selectOptionsMapping[rowKey].forEach(optionValue => {
                    let optionElem = document.createElement("option");                    
                    optionElem.value = optionValue;
                    optionElem.text = optionValue;
                    if (optionValue === rowValue) {
                        optionElem.setAttribute("selected","true");
                    }
                    selectElem.appendChild(optionElem);
                });
                rowCell.appendChild(selectElem);
            } else {
                rowCell.innerHTML = rowValue;
            }
        }
        return rowCell;
    };

    // Create header row
    var headerRow = createRow();
    containerHeaderObj.appendChild(headerRow);

    // Add checkbox header
    var checkboxHeader = createColumn("TH", "Select", `<input type="checkbox" id="selectAll" onclick="toggleSelectAll()">`, "", true);
    headerRow.appendChild(checkboxHeader);

    // Add data headers
    Object.keys(actualData[0]).forEach((prop) => {
        headerRow.appendChild(createColumn("TH", prop, prop, "", true));
    });

    headerRow.appendChild(createColumn("TH", "Event Buttons", "", "text-end", true));
    
    const createActionButtons = (rowId) => {
        let exportSelect = document.createElement("select");
        exportSelect.className = "btn btn-sm text-primary me-2";        
        exportSelect.innerHTML = '<option>pdf</option><option>excel</option>';

        let exportButton = document.createElement("button");
        exportButton.className = "btn btn-sm btn-white text-primary me-2";        
        exportButton.setAttribute("onclick",exportFunction);
        exportButton.innerHTML = '<i class="fa fa-file-export me-1"></i> Export';                

        let deleteButton = document.createElement("button");
        deleteButton.className = "btn btn-sm btn-white text-danger me-2";        
        deleteButton.setAttribute("onclick",deleteFunction);
        deleteButton.innerHTML = '<i class="far fa-trash-alt me-1"></i> Delete';

        let actionContainer = document.createElement("TD");
        actionContainer.className = "text-end";
        actionContainer.append(exportSelect, exportButton, deleteButton);

        return actionContainer;
    };

    // Populate table rows
    actualData.forEach((obj) => {
        var dataRow = createRow();
        containerBodyObj.appendChild(dataRow);

        // Checkbox column
        let isChecked = selectRecordStr.includes(obj[idField]) ? 'checked' : '';
        dataRow.appendChild(createColumn("TD", "Select", `<input type="checkbox" class="rowCheckbox" name="${obj[idField]}" ${isChecked} onclick="toggleSelection('${obj[idField]}')">`, ""));

        // Data columns
        Object.entries(obj).forEach(([key, value]) => {
            dataRow.appendChild(createColumn("TD", key, value, ""));
        });

        // Action buttons column
        dataRow.appendChild(createActionButtons(obj[idField]));
    });

    return containerObj.outerHTML;
};


