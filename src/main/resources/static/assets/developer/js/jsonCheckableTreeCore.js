
function buildRolesTree(vElement,jsonObj){
	var unorderListTag = document.createElement("UL");		
	unorderListTag.setAttribute("class","treeUlistCls");
	vElement.appendChild(unorderListTag);
	for(var gg_key in jsonObj){
		var listTag = document.createElement("LI");
		listTag.setAttribute("class","treeListCls");
		var inputTag = document.createElement("INPUT");
		inputTag.setAttribute("class","treeInputCls");
		inputTag.setAttribute("type","checkbox");
		inputTag.setAttribute("id",gg_key+"_id");
		inputTag.onchange = function(){
			checkedTreeSelector(this);
		};
		listTag.appendChild(inputTag);				
		var lblTag = document.createElement("LABEL");			
		lblTag.setAttribute("class","treeLabelCls");
		lblTag.setAttribute("for",gg_key+"_id");
		lblTag.innerHTML = gg_key;
		listTag.appendChild(lblTag);				
		unorderListTag.appendChild(listTag);				
		if(jsonObj[gg_key] != ""){
			buildRolesTree(listTag,jsonObj[gg_key]);									
		}				
	}
};





function checkedTreeSelector(vObj){

	if(vObj.checked && vObj.nextSibling.nextSibling != null){
		var ulist = vObj.nextSibling.nextSibling;
		for(var gg = 0 ; gg < ulist.childNodes.length ; gg++){
			ulist.childNodes[gg].childNodes[0].checked = true;
			if(ulist.childNodes[gg].childNodes[0].checked){
				checkedTreeSelector(ulist.childNodes[gg].childNodes[0]);
			}
		}
	}else if(!vObj.checked && vObj.nextSibling.nextSibling != null){
		var ulist = vObj.nextSibling.nextSibling;
		for(var gg = 0 ; gg < ulist.childNodes.length ; gg++){
			ulist.childNodes[gg].childNodes[0].checked = false;				
			if(!ulist.childNodes[gg].childNodes[0].checked){
				checkedTreeSelector(ulist.childNodes[gg].childNodes[0]);
			}
		}
	}
};



function buildCheckedJSONObject(ulElement){
	var jsonObj = JSON.parse("{}");								
	for(var hh = 0 ; hh < ulElement.childNodes.length;hh++){
		var inputTag = ulElement.childNodes[hh].childNodes[0];
		var labelValue = ulElement.childNodes[hh].childNodes[1].innerHTML;			
		if(inputTag.checked){
			jsonObj[labelValue] = "";
			if(inputTag.nextSibling.nextSibling != null){
				jsonObj[labelValue] = buildCheckedJSONObject(inputTag.nextSibling.nextSibling);
			}
		}
	}	

	return jsonObj;
};


function buildDataTableColumnsRolesTreeView(vElement,jsonObj){	
	var unorderListTag = document.createElement("UL");		
	unorderListTag.setAttribute("class","treeUlistCls");
	vElement.appendChild(unorderListTag);
	for(var gg_key in jsonObj){
		var listTag = document.createElement("LI");
		listTag.setAttribute("class","treeListCls");
		var iconDownTag = document.createElement("i");
		iconDownTag.setAttribute("style","position: relative;left: 1%;");
		iconDownTag.setAttribute("class","fa fa-caret-down");
		iconDownTag.setAttribute("src","/assets/developer/img/titleIcon.jpeg");				
		listTag.appendChild(iconDownTag);				
		var lblTag = document.createElement("LABEL");			
		lblTag.setAttribute("class","treeLabelCls");		
		lblTag.innerHTML = gg_key;
		listTag.appendChild(lblTag);				
		unorderListTag.appendChild(listTag);				
		if(jsonObj[gg_key] != ""){
			buildDataTableColumnsRolesTreeView(listTag,jsonObj[gg_key]);									
		}				
	}
};




function autoCheckTreeStructureView(vElement,jsonObjStr){	
	if(vElement.tagName == "UL"){
		for(var gg=0;gg < vElement.childNodes.length;gg++){			
			if(vElement.childNodes[gg].tagName == "LI"){
				var propertyName = vElement.childNodes[gg].childNodes[1].innerHTML;			
				if(jsonObjStr.includes(propertyName)){					
					vElement.childNodes[gg].childNodes[0].checked = true;
				}
				if(vElement.childNodes[gg].childNodes[2] != null){					
					autoCheckTreeStructureView(vElement.childNodes[gg].childNodes[2],jsonObjStr);
				}
			}
		}
	}
};

