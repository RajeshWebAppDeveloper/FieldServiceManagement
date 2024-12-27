
function hideAllLayer(){
	document.getElementById("dashboard_list_form").style.display ="none";
	document.getElementById("users_list_form").style.display ="none";
	document.getElementById("users_entry_form").style.display ="none";
	document.getElementById("category_list_form").style.display ="none";
	document.getElementById("product_list_form").style.display ="none";
	document.getElementById("dar_list_form").style.display ="none";
	document.getElementById("dar_entry_form").style.display ="none";
	document.getElementById("est_list_form").style.display ="none";
	document.getElementById("est_entry_form").style.display ="none";
	document.getElementById("order_list_form").style.display ="none";
	document.getElementById("order_entry_form").style.display ="none";
	document.getElementById("payment_list_form").style.display ="none";
	document.getElementById("defaultProperties_list_form").style.display ="none";
	
	getDefaultPropertyValuesByName("DAR - Status To Visit");
	getDefaultPropertyValuesByName("Users - User Roles");
	getDefaultPropertyValuesByName("Estimation - Approval Status");	
	getDefaultPropertyValuesByName("Order - Approval Status");
	getDefaultPropertyValuesByName("Payment - Approval Status");	
};


window.document.body.onload = function(){
	try{
		hideAllLayer();		
		showAdminDashboardForm();
		document.getElementById("users_list_form").innerHTML = getUsersListForm();
		document.getElementById("category_list_form").innerHTML = getCategoryListForm();
		document.getElementById("product_list_form").innerHTML = getProductListForm();
		document.getElementById("dar_list_form").innerHTML = getDarListForm();
		document.getElementById("est_list_form").innerHTML = getEstimationListForm();
		document.getElementById("order_list_form").innerHTML = getOrderListForm();
		document.getElementById("payment_list_form").innerHTML = getPaymentListForm();
		document.getElementById("defaultProperties_list_form").innerHTML = getDefaultPropertiesListForm();
		
		/*getDefaultPropertyValuesByName("Category");
		getDefaultPropertyValuesByName("Branch");
		getDefaultPropertyValuesByName("Extra Travel Price Per Km");
		getUserRightsObjectByRoleName();*/
		
		getProjectMenuFeatures();
	}catch(exp){
		alert(exp);
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
	}		
};





async function getDataFromServicePoint(url = ``, formData = {}) {
	
	var timeoutId = setTimeout(function(){
			document.getElementById("transparent_loader_container_id").style.display="block";	
		}, 500);	
	
		
	//Default options are marked with *
    const response = await fetch(url, {
        method: "POST", // *GET, POST, PUT, DELETE, etc.
        mode: "cors", // no-cors, cors, *same-origin
        cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
        credentials: "omit", // include, *same-origin, omit
        headers: {
            "Content-Type": "application/json",
            // "Content-Type": "application/x-www-form-urlencoded",
        },
        redirect: "follow", // manual, *follow, error
        referrer: "no-referrer", // no-referrer, *client
        body: JSON.stringify(formData), // body data type must match "Content-Type" header
    });	
	//hideProgressBar();	
	clearTimeout(timeoutId);
	document.getElementById("transparent_loader_container_id").style.display="none";
  	
    return response.json();
};


//function showProgressBar(){};
//function hideProgressBar(){};

function handleError(formName,error){
	//alert("ERROR:"+formName+"::::"+error);
	toastr.error(formName+"::::"+error,"Error", {closeButton: !0,tapToDismiss: !1});	
};

function handleErrorForList(formName,error,tableContainerId){	
	if(error == "TypeError: can't convert undefined to object"){
		toastr.warning(formName+" -- No Data","Warning", {closeButton: !0,tapToDismiss: !1});		
		document.getElementById(tableContainerId).innerHTML = "";
	}else{
		toastr.error(formName+"::::"+error,"Error", {closeButton: !0,tapToDismiss: !1});
	}
};


function logOut(){
	toastr.info("Sign Out","Info", {closeButton: !0,tapToDismiss: !1});
	window.location.replace("/fsm/login");	
};
