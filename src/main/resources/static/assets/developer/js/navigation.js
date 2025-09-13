

async function getProjectMenuFeatures(){
		var parentElement= document.getElementById("sidebar_navigation_menu_container");	
		var rightsTreeStructureObjStr = logginerUserRights;
		var menuNameJsonObj = JSON.parse(rightsTreeStructureObjStr);
		/*var menuIconJsonObj = {
		       "Dashboard": `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bar-chart" viewBox="0 0 16 16">
		                       <path d="M0 0h1v15h15v1H0V0zm11 14V6h2v8h-2zm-4 0V3h2v11H7zm-4 0V9h2v5H3z"/>
		                     </svg>`,
		       "Users": `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-check" viewBox="0 0 16 16">
		                   <path d="M15.854 5.646a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708 0L9.5 8.707a.5.5 0 0 1 .708-.708l1.646 1.647 3.646-3.647a.5.5 0 0 1 .708 0z"/>
		                   <path d="M1 13.5a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 .5.5v.5a2.5 2.5 0 0 1-2.5 2.5h-6A2.5 2.5 0 0 1 1 14v-.5zM3 6a3 3 0 1 1 6 0A3 3 0 0 1 3 6z"/>
		                 </svg>`,
		       "Category": `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-card-list" viewBox="0 0 16 16">
		                     <path d="M14 2a1 1 0 0 1 1 1v10a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V3a1 1 0 0 1 1-1h12zM2 3v10h12V3H2z"/>
		                     <path d="M3 7.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm0 3a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zM3 4.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm8-.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5zm0 3a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5zm0 3a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5z"/>
		                   </svg>`,
		       "Product": `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box" viewBox="0 0 16 16">
		                     <path d="M1.5 1a.5.5 0 0 0-.5.5v2.011c0 .412.124.815.35 1.153l2.713 4.069a.5.5 0 0 0 .848-.027l1.677-2.928a.5.5 0 0 1 .884 0l1.677 2.928a.5.5 0 0 0 .848.027l2.713-4.069a1.61 1.61 0 0 0 .35-1.153V1.5a.5.5 0 0 0-.5-.5H1.5zM1 1h14v2.011a1.61 1.61 0 0 1-.35 1.153L12 7.234V13h-2V8.5a.5.5 0 0 0-.832-.374L8 9.973 6.832 8.126A.5.5 0 0 0 6 8.5V13H4V7.234L1.35 4.164A1.61 1.61 0 0 1 1 3.011V1z"/>
		                   </svg>`,
		       "DAR": `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-gift" viewBox="0 0 16 16">
		                 <path d="M3 8H1v6a2 2 0 0 0 2 2h3V8H3zm9 0H8v8h3a2 2 0 0 0 2-2V8h-1zM2 3h3V2a1 1 0 1 1 2 0v1h2V2a1 1 0 1 1 2 0v1h3a2 2 0 0 1 1.995 1.85L16 5v1a1 1 0 0 1-1 1v2H1V7a1 1 0 0 1-1-1V5a2 2 0 0 1 2-2zm12 2v-.5a.5.5 0 0 0-.5-.5H11v2h3V5zM5 4H2.5a.5.5 0 0 0-.5.5V5h3V4zm2 4h2V4H7v4z"/>
		               </svg>`,
		       "Estimation": `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calculator" viewBox="0 0 16 16">
		                        <path d="M2 1a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2H2zM1 3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v10a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V3zm2 3a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3A.5.5 0 0 1 3 6zm0 2a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3A.5.5 0 0 1 3 8zm0 2a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5zm5-4a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 0 1h-1a.5.5 0 0 1-.5-.5z"/>
		                      </svg>`,
				"Order":`<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bag" viewBox="0 0 16 16">
			                  <path d="M5.5 3a1.5 1.5 0 0 1 3 0v1h-3V3zm4 1V3a2.5 2.5 0 0 0-5 0v1h5zM1 5h14v10a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V5zm2 1a.5.5 0 0 0-.5.5V7h11v-.5a.5.5 0 0 0-.5-.5H3zm10 1H3v7a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1v-7z"/>
				              </svg>`
		   };*/
		   
		   var menuIconJsonObj = {		    
		   		   "Dashboard": "fa fa-bar-chart"
	               ,"Users":"fa fa-user-check"			
	               ,"Catgory":"fa fa-clone"
	               ,"Product":"fa fa-box-open"
	               ,"DAR":"fa fa-gift"
	               ,"Estimation":"fa fa-donate"              
	               ,"Order":"fa fa-envelope"
				   ,"Payment":"fa fa-dollar"
	   			   ,"Default Properties": "fa fa-check-double"
		   		};


		var menuFunctionJsonObj = {
			"Dashboard": "showAdminDashboardForm()"
           ,"Users":"showUsersListForm('')"			
           ,"Catgory":"showCategoryListForm('')"
           ,"Product":"showProductListForm('')"
           ,"DAR":"showDarListForm('')"
           ,"Estimation":"showEstimationListForm('')"              
           ,"Order":"showOrderListForm('')"
		   ,"Payment":"showPaymentListForm('')"
		   ,"Default Properties": "showDefaultPropertiesListForm('')"		
	   };

		await buildProjectMenu(parentElement, menuNameJsonObj, menuIconJsonObj, menuFunctionJsonObj);			
};
async function buildProjectMenu(parentElement, menuNameJsonObj, menuIconJsonObj, menuFunctionJsonObj) {
    var unorderList = document.createElement("UL");
	unorderList.setAttribute("class","sidebar-nav")

    for (let menuName in menuNameJsonObj) {  // Use `let` to bind each menuName properly in the loop
        var list = document.createElement("LI");
        list.setAttribute("title", menuName);
		list.setAttribute("class", "sidebar-item active");

        var aTag = document.createElement("A");
        aTag.setAttribute("href", "#");
		aTag.setAttribute("data-bs-target", "");
		aTag.setAttribute("data-bs-toggle", "");
		aTag.setAttribute("class", "sidebar-link");
		
		if(menuFunctionJsonObj[menuName] == undefined || menuFunctionJsonObj[menuName] == "undefined"){
			aTag.onclick = function(){				
				if(window.getComputedStyle(this.nextSibling).display == "none") {				
	                this.setAttribute("class", "subdrop");
	                this.nextSibling.style.display = "block";
	            }else if(window.getComputedStyle(this.nextSibling).display == "block"){					
	                this.setAttribute("class", "");
	                this.nextSibling.style.display = "none";
	            }	
			}				
		}else{
			aTag.setAttribute("onclick",menuFunctionJsonObj[menuName]);
		}		
        list.appendChild(aTag);

       
				
		var iconTag = document.createElement("I");
		iconTag.setAttribute("class",menuIconJsonObj[menuName]);
        aTag.appendChild(iconTag);

        var menuNameSpanTag = document.createElement("SPAN");
		menuNameSpanTag.setAttribute("class","align-middle");
        menuNameSpanTag.innerHTML = menuName;
        aTag.appendChild(menuNameSpanTag);

        // Check if the submenu exists and is an object (to avoid infinite recursion)
        if (typeof menuNameJsonObj[menuName] === 'object' && Object.keys(menuNameJsonObj[menuName]).length > 0) {
            var menuArrowSpanTag = document.createElement("SPAN");
            menuArrowSpanTag.setAttribute("class", "align-middle");
            aTag.appendChild(menuArrowSpanTag);

            // Recursively build the submenu
            await buildProjectMenu(list, menuNameJsonObj[menuName], menuIconJsonObj, menuFunctionJsonObj);
        }

        unorderList.appendChild(list);
    }

    parentElement.appendChild(unorderList);
};
