
var validationFocusInputTag;

async function checkValidationDynamicInputRow(inputNames,inputTypes,inputElementIds){	
	var vStatus = true;
	var field = "";
	var fieldValue = "";
	try{
		
		var inputNamesArray = inputNames.split(",");
		var inputTypesArray = inputTypes.split(",");
		var inputElementIdsArray = inputElementIds.split(",");		
		for(var gg=0;gg < inputNamesArray.length;gg++){													
				if(inputTypesArray[gg] == 'text' 
					|| inputTypesArray[gg] == "date" 				
					|| inputTypesArray[gg] == "time" 				
					|| inputTypesArray[gg] == "email" 
					|| inputTypesArray[gg] == "datetime-local" 				
					|| inputTypesArray[gg] == "password" 				
					|| inputTypesArray[gg] == "month"
					|| inputTypesArray[gg] == "textarea"
					|| inputTypesArray[gg] == "textreadonly" ){				
					fieldValue = document.getElementById(inputElementIdsArray[gg]).value;					
					if(fieldValue.trim() == ""){
						vStatus = false;
						await showAlertForm("Warning","Please complete the field, "+inputNamesArray[gg]);
						toastr.warning("Please complete the field,"+inputNamesArray[gg],"Warning", {closeButton: !0,tapToDismiss: !1});					
						validationFocusInputTag = document.getElementById(inputElementIdsArray[gg]);
						break;
					}
				}else if(inputTypesArray[gg] == "datetime-local"){				
					fieldValue = document.getElementById(inputElementIdsArray[gg]).value;					
					if(fieldValue.trim() == ""){
						vStatus = false;
						await showAlertForm("Warning","Please complete the field, "+inputNamesArray[gg]);
						toastr.warning("Please complete the field,"+inputNamesArray[gg],"Warning", {closeButton: !0,tapToDismiss: !1});							
						validationFocusInputTag = document.getElementById(inputElementIdsArray[gg]);
						break;
					}		
						
				}else if(inputTypesArray[gg] == "select"){				
					fieldValue = document.getElementById(inputElementIdsArray[gg]).value;					
					if(fieldValue.trim() == "" || fieldValue.trim() == "--Select--"){
						vStatus = false;
						await showAlertForm("Warning","Please complete the field, "+inputNamesArray[gg]);
						toastr.warning("Please complete the field,"+inputNamesArray[gg],"Warning", {closeButton: !0,tapToDismiss: !1});							
						validationFocusInputTag = document.getElementById(inputElementIdsArray[gg]);
						break;
					}		
				}else if(inputTypesArray[gg] == "number"){				
					fieldValue = document.getElementById(inputElementIdsArray[gg]).value;						
					if(fieldValue.trim() == ""){
						vStatus = false;					
						await showAlertForm("Warning","Please complete the field, "+inputNamesArray[gg]);
						toastr.warning("Please complete the field,"+inputNamesArray[gg],"Warning", {tapToDismiss: !1});							
						validationFocusInputTag = document.getElementById(inputElementIdsArray[gg]);
						break;
					}
					if(vStatus && isNaN(fieldValue)){
						vStatus = false;					
						await showAlertForm("Warning",inputNamesArray[gg] +",field value is not a Number");
						toastr.warning(inputNamesArray[gg] +",field value is not a Number","Warning", {closeButton: !0,tapToDismiss: !1});					
						validationFocusInputTag = document.getElementById(inputElementIdsArray[gg]);
						break;
					}		
				}else if(inputTypesArray[gg] == "tel"){	
					field = document.getElementById(inputElementIdsArray[gg]);							
					fieldValue = document.getElementById(inputElementIdsArray[gg]).value;					
					var fieldMaxLength = field.maxLength;
					if(fieldValue.trim() == ""){
						vStatus = false;					
						await showAlertForm("Warning","Please complete the field, "+inputNamesArray[gg]);
						toastr.warning("Please complete the field,"+inputNamesArray[gg],"Warning", {closeButton: !0,tapToDismiss: !1});							
						validationFocusInputTag = field;
						break;
					}
					if(vStatus && isNaN(fieldValue)){
						vStatus = false;					
						await showAlertForm("Warning",inputNamesArray[gg] +",field value is not a Number");
						toastr.warning(inputNamesArray[gg]+"field value is not a Number","Warning", {closeButton: !0,tapToDismiss: !1});							
						validationFocusInputTag = document.getElementById(inputElementIdsArray[gg]);					
						break;
					}							
					if(vStatus && fieldValue.trim().length < fieldMaxLength){
						vStatus = false;
						await showAlertForm("Warning",inputNamesArray[gg]+" field value atleast "+fieldMaxLength+" digits.");
						toastr.warning(inputNamesArray[gg]+" field value atleast "+fieldMaxLength+" digits.","Warning", {closeButton: !0,tapToDismiss: !1});	
						validationFocusInputTag = document.getElementById(inputElementIdsArray[gg]);					
						break;
					}				
				}else if(inputTypesArray[gg] == "label"){								
					fieldValue = document.getElementById(inputElementIdsArray[gg]).innerHTML;	
					if(fieldValue.trim() == ""){
						vStatus = false;					
						await showAlertForm("Warning","Please complete the field, "+inputNamesArray[gg]);
						toastr.warning("Please complete the field,"+inputNamesArray[gg],"Warning", {closeButton: !0,tapToDismiss: !1});						
						validationFocusInputTag = document.getElementById(inputElementIdsArray[gg]);
						break;
					}		
				}
						
		}
		
	}catch(exp){
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});		
	}		
	return vStatus;
};


function inputFieldIsNull(inputTag,inputValue){
	if(inputValue == "" || inputValue == "null" || inputValue == null || inputValue == "undefined" || inputValue == undefined){
		inputTag.style.border="1px solid #c30010";
		inputTag.nextSibling.style.display="block";
		inputTag.nextSibling.innerHTML = "*Field is empty";
		return false;
	}
	inputTag.style.border="";
	inputTag.nextSibling.innerHTML ="";
	inputTag.nextSibling.style.display="none";
	return true;
};

function dropDownFieldIsNull(inputTag,inputValue){
	if(inputValue == "--Select--"){
		inputTag.style.border="1px solid #c30010";
		inputTag.nextSibling.style.display="block";
		inputTag.nextSibling.innerHTML = "*Field is empty";
		return false;
	}
	inputTag.style.border="";
	inputTag.nextSibling.innerHTML ="";
	inputTag.nextSibling.style.display="none";
	return true;
};

function isNumberKey(inputTag,evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode > 31 && (charCode < 40 || charCode >57)){
		inputTag.style.border="1px solid #c30010";
		toastr.warning("Its not a Number...","Warning", {closeButton: !0,tapToDismiss: !1});					
		return false;		
	}
	inputTag.style.border="";
	
	return true;
};

function maxLengthOfInputData(inputTag,lengthType){
	var typeOfValue = "letters";
	if(inputTag.type == "number" || inputTag.type == "tel"){
		typeOfValue = "digits";
	}	
	if(lengthType == "reached"){
		if((inputTag.value.length+1) > inputTag.maxLength){
			inputTag.style.border="1px solid #6253ff";
			inputTag.nextSibling.style.display="block";
			inputTag.nextSibling.style.color="#6253ff";
			inputTag.nextSibling.innerHTML = "*maximum "+inputTag.maxLength+" "+typeOfValue+" Only.";			
			return false;		
		}	
		inputTag.style.border="";
		inputTag.nextSibling.innerHTML ="";
		inputTag.nextSibling.style.color="";
		inputTag.nextSibling.style.display="none";
		return true;
	}else if(lengthType == "not reached"){				
		if(inputTag.value.length < inputTag.maxLength){						
			inputTag.style.border="1px solid #c30010";
			inputTag.nextSibling.style.display="block";
			inputTag.nextSibling.innerHTML = "*minimum "+inputTag.maxLength+" "+typeOfValue+".";						
			return false;		
		}
		inputTag.style.border="";
		inputTag.nextSibling.innerHTML ="";
		inputTag.nextSibling.style.display="none";	
		return true;
	}		
};

function isOnlyAlphaNumeric(inputTag,e){	
	var regex = new RegExp("^[a-zA-Z0-9]+$");
	var str = String.fromCharCode(!e.charCode ? e.which: e.charCode);
	if(regex.test(str)){
		inputTag.style.border="";
		inputTag.nextSibling.innerHTML ="";
		inputTag.nextSibling.style.display="none";
		return true;
	}
	e.preventDefault();	
	inputTag.style.border="1px solid #c30010";
	inputTag.nextSibling.style.display="block";
	inputTag.nextSibling.innerHTML = "*Only letter or Number";
	return false;
};

function isOnlyAlphabet(inputTag,e){
	var regex = new RegExp("^[a-zA-Z]+$");
	var str = String.fromCharCode(!e.charCode ? e.which: e.charCode);
	if(regex.test(str)){
		inputTag.style.border="";
		inputTag.nextSibling.innerHTML ="";
		inputTag.nextSibling.style.display="none";
		return true;
	}
	e.preventDefault();
	inputTag.style.border="1px solid #c30010";
	inputTag.nextSibling.style.display="block";
	inputTag.nextSibling.innerHTML = "*Only letter or Number";
	return false;
};

function validateEmailFormat(inputTag,e){
	var regex1 = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	var regex2 = /^\w+([\.-]?\w+)*@\w([\.-]?\w+)*(\.\w{2,3})+$/;		
	if(regex1.test(inputTag.value) && regex2.test(inputTag.value)){
		inputTag.style.border="";
		inputTag.nextSibling.innerHTML ="";
		inputTag.nextSibling.style.display="none";
		return true;
	}	
	inputTag.style.border="1px solid #c30010";
	inputTag.nextSibling.style.display="block";
	inputTag.nextSibling.innerHTML = "*Invalid Email ";
	return false;
};


function onlyNumberKey(evt) {
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode;
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
        return false;
    return true;
};
