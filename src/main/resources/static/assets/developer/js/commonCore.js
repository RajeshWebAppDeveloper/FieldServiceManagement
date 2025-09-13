function buildDynamicTableJsonObj(vObj){
	var jsonObj = JSON.parse('{}');
	try{		
		var tableRow = vObj.parentNode.parentNode;
		for(var gg=0;gg < tableRow.childNodes.length;gg++){						
			var rowKey = tableRow.childNodes[gg].getAttribute("name");
			if(rowKey != "Event Buttons"){
				var rowValue = tableRow.childNodes[gg].innerHTML;
				jsonObj[rowKey] = rowValue;			
			}			
		}		
	}catch(exp){
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
		alert(exp);		
	}		
	return jsonObj;
};

async function buildDynamicChexBoxColumnTableJsonObj(vObj){
	var jsonObj = JSON.parse('{}');
	try{		
		var tableRow = vObj.parentNode.parentNode;
		for(var gg=1;gg < tableRow.childNodes.length;gg++){						
			var rowKey = tableRow.childNodes[gg].getAttribute("name");
			if(rowKey != "Event Buttons"){
				var rowValue = tableRow.childNodes[gg].innerHTML;
				jsonObj[rowKey] = rowValue;			
			}			
		}		
	}catch(exp){
		toastr.error(exp,"Error", {closeButton: !0,tapToDismiss: !1});
		alert(exp);		
	}		
	return jsonObj;
};

function convertTextIntoHTML(htmlTextFormat){
	const domParser = new DOMParser(); 
	const newHTML = domParser.parseFromString(htmlTextFormat, "text/html"); 	
	return newHTML.body.firstChild;
};

document.addEventListener("DOMContentLoaded", function() {
	$(".tasks-check-all").click(function() {
		if ($(this).prop("checked")) {
			$(this).closest('.table').find("input[type='checkbox']").prop("checked", true);
		} else {
			$(this).closest('.table').find("input[type='checkbox']").prop("checked", false);
		}
	});
});

function formatDateToString(dateElem) {
    if (dateElem.value) {
        const date = new Date(dateElem.value);
        return date.toISOString().slice(0, 19).replace("T", " "); // Format for backend
    }
    return null;
};