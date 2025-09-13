function getWarningFormHTML(alertType,alertMessage){
	var form = `<div class="alertInnerBoxContainer">
					
						<div class="alertBoxInnerheader">
							<h4 style="color:#7638ff;font-size: 2.125rem;" class="modal-title">`+alertType+` !!!</h4>						
						</div>
						<div class="alertBoxInnercontent">																					
							<h6 style="color:grey;text-align:center;font-size: 1.125rem;">`+alertMessage+`</h6>													
							<div class="alertBoxInneraction">
								<button class="btn btn-primary" onclick="closeAlertForm()">Ok</button>
							</div>
						</div>						
					
				</div>`;
	return form;
};
					
function showAlertForm(alertType,alertMessage){
	document.getElementById("transparent_warning_container_id").style.visibility="visible";
	document.getElementById("transparent_warning_container_id").innerHTML = getWarningFormHTML(alertType,alertMessage);
};

function closeAlertForm(){
	document.getElementById("transparent_warning_container_id").style.visibility="hidden";
	validationFocusInputTag.focus();	
};

/***************************************Confirmation Alert************************************************ */



function showConfirmation() {
  Swal.fire({
    title: "Are you sure?",
    text: "You won't be able to revert this!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "Yes",
    cancelButtonText: "No",
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    buttonsStyling: true
  }).then((result) => {
    alert(result);
  });
};
