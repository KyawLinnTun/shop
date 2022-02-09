var activity;
$(document)
		.ready(
				function() {
					$("#saveItemButton").click(
						function() {
							if(itemValidateCheck()==true){
								$.get(
								"checkDuplicateItem.html?itemId=" + $('#id').val() + "&itemName="
								+ $('#itemName').val(),
								function(data, status) {
									var result = JSON.parse(data);
									var duplicateItemList = JSON.parse(result.data);
									if(result.successMessage!=null && result.successMessage!=''){
										document.getElementById("modalBounceInDown").style.display='block';
										$("#commonLabel").removeClass('custom_error_hide');
									}else if (result.data != null && duplicateItemList.length>0) {
										showInfo(duplicateItemList);
									} else {
										document.getElementById("item-form").submit();
									}
							});
						}
					});
					$("#createNewBtn").click(
						function() {
							document.getElementById("item-form").submit();
					});
					var language=document.getElementById('language').value;
					if(language!=null && language.length>0){
						activity = jsonStr_MM;
					}else{
						activity = jsonStr;
					}
					var selected = "";
					var manufactureCountryValue = document.getElementById('manufactureCountryValue').value;
					for (var i in activity) {
						if (activity[i].code == manufactureCountryValue) {
							selected = "selected";
						} else {
							selected = "";
						}
						$('#manufactureCountryList').append(
							'<option value="' + activity[i].code + '" '+selected
							+ '>' + activity[i].name + '</option>');
					}			
				});

function itemValidateCheck() {
	var isValid = false;
	checkValid();
	if (errors == 0) {
		isValid = true;
	}

	event.preventDefault();
	return isValid;
}

function checkValid() {
	errors = 0;
	if ($("#itemName").val() == null || $("#itemName").val() == "") {
		$("#itemName").addClass("is-invalid");
		$("#item_name_msg").removeClass("custom_error_hide");
		$("#item_name_msg").html("This field is required.");
		errors = 1;
	} else {
		$("#itemName").removeClass("is-invalid");
		$("#item_name_msg").addClass("custom_error_hide");
		$("#item_name_msg").html("");
	}
	if (!$.isNumeric($("#buyingPrice").val())) {
		$("#buyingPrice").addClass("is-invalid");
		$("#buyPrice_msg").removeClass("custom_error_hide");
		$("#buyPrice_msg").html("Buying Price must be number.");
		errors = 1;
	} else {
		$("#buyingPrice").removeClass("is-invalid");
		$("#buyPrice_msg").addClass("custom_error_hide");
		$("#buyPrice_msg").html("");
	}
	if (!$.isNumeric($("#sellPrice").val())) {
		$("#sellPrice").addClass("is-invalid");
		$("#sellPrice_msg").removeClass("custom_error_hide");
		$("#sellPrice_msg").html("Sell Price must be number.");
		errors = 1;
	} else {
		$("#sellPrice").removeClass("is-invalid");
		$("#sellPrice_msg").addClass("custom_error_hide");
		$("#sellPrice_msg").html("");
	}
	if ($("#category").val() == null || $("#category").val() == "") {
		$("#category").addClass("is-invalid");
		$("#category_msg").removeClass("custom_error_hide");
		$("#category_msg").html("This field is required.");
		errors = 1;
	} else {
		$("#category").removeClass("is-invalid");
		$("#category_msg").addClass("custom_error_hide");
		$("#category_msg").html("");
	}
}

function showInfo(duplicateItemList) {
	$('table#duplicateTable tbody tr').remove();
	var rows = "<tr><td colspan=\"2\">No result</td>";
	var flag = 0;
	var no=1;
	for (i in duplicateItemList) {
		if (i == 0) {
			rows = "";
		}
		var id = duplicateItemList[i].id;
		var name = duplicateItemList[i].name;
		var size = duplicateItemList[i].size;
		var brand = duplicateItemList[i].brand;
		var color = duplicateItemList[i].color;
		var categoryName = duplicateItemList[i].categoryName;
		var status = duplicateItemList[i].status;
		var statusDesc = duplicateItemList[i].statusDesc;
		var countryCode=duplicateItemList[i].manufactureCountry;
		var manufactureCountry='';
		for (var i in activity) {
			if (activity[i].code == countryCode) {
				manufactureCountry=activity[i].name;
				break;
			} 
		}
		rows += "<tr><td>" + no + "</td><td>" + name + "</td><td>" + categoryName
			+ "</td><td>" + size + "</td><td>" + color + "</td><td>"
			+ manufactureCountry + "</td><td>" + brand + "</td><td>";
		if(status==1){
			rows+="<button class='btn btn-rounded btn-primary' style='padding: 0px 20px;'>Active</button>";
		}else{
			rows+="<button class='btn btn-rounded btn-danger' style='padding: 0px 20px;'>Inactive</button>";
		}
		rows += "</td></tr>";
			no=no+1;

	}
	$(rows).appendTo("#duplicateTable tbody");
	$("#duplicateItemsList").modal('toggle');
}

function deleteItem(itemId) {
	$("#itemId").val($('#id').val());
	$("#deleteItemConfirm").modal('toggle');
}
function closeWarningBox() {
	var alertBox = document.getElementById("deleteItemConfirm");
	alertBox.style.display = "none";
}