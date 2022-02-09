$(document)
	.ready(
		function() {
			if ($("#cardNumberSizeList").val() != null && $("#cardNumberSizeList").val() > 0) {
				var pmSize = $("#cardNumberSizeList").val();
				countId = parseInt($("#cardNumberSizeList").val()) - 1;
				var supplierId = $("#id").val();
				for (var l = 0; l < pmSize; l++) {
					if (l == 0) {
						$("#paymentTypeGp").removeClass("custom_error_hide");
						$("#cardNumberGp").removeClass("custom_error_hide");
						$("#cardUserNameGp").removeClass("custom_error_hide");
						$("#headerGp").removeClass("custom_error_hide");
						getPaymentMethodService(supplierId.toString(), (l).toString());
					} else {
						var slot = $("#clone_method").clone();
						slot.prop('id', 'clone_' + (l));
						slot.find("#paymentTypeGp").prop('id', 'pGp_' + (l));

						slot.find("#paymentType").removeClass("custom_error_hide");
						slot.find("#paymentType").prop('name', 'cardNumberDTOList[' + (l) + '].paymentTypeId');
						slot.find("#paymentType").prop('id', 'p_' + (l));

						slot.find("#payment_type_msg").prop('id', 'payment_type_msg_' + (l));

						slot.find("#cardNumberGp").prop('id', 'noGp_' + (l));

						slot.find("#cardNo").removeClass("custom_error_hide");
						slot.find("#cardNo").prop('name', 'cardNumberDTOList[' + (l) + '].no');
						slot.find("#cardNo").prop('id', 'no_' + (l));

						slot.find("#dup_no_msg").prop('id', 'dup_no_msg_' + (l));
						slot.find("#must_num_msg").prop('id', 'must_num_msg_' + (l));

						slot.find("#cardUserNameGp").prop('id', 'unGp_' + (l));

						slot.find("#cardUserName").removeClass("custom_error_hide");
						slot.find("#cardUserName").prop('name', 'cardNumberDTOList[' + (l) + '].name');
						slot.find("#cardUserName").prop('id', 'un_' + (l));

						if (l == 1) {
							slot.insertAfter($("#clone_method"));
						} else if (l > 1) {
							slot.insertAfter($("#clone_" + (l - 1)));
						}

						getPaymentMethodService(supplierId.toString(), (l).toString());
					}
				}
				var getUrl = "";
				var getData = {};
				var getErrMsg = "Cannot get this.";

				function getPaymentMethodService(supplierId, array) {

					getUrl = contextPath + "getPaymentMethod.html";
					getData = {
						supplierId: supplierId,
						array: array
					};
					getOkCallBack()

				}

				function getOkCallBack() {
					ajaxCall(getUrl, getData, getDataSuccessCallBack,
						getErrMsg);
				}

				function getDataSuccessCallBack(data) {
					var obj = JSON.parse(data);
					if (obj != null && obj != undefined && obj != "") {
						var idArray = obj.split("/");
						if (idArray[3] == 0) {
							$('#paymentType').val(parseInt(idArray[0]));
							$('#cardNo').val(idArray[1]);
							$('#cardUserName').val(idArray[2]);
						} else {
							$('#p_' + (idArray[3])).val(parseInt(idArray[0]));
							$('#no_' + (idArray[3])).val(idArray[1]);
							$('#un_' + (idArray[3])).val(idArray[2]);
						}
					}
				}
			}
			document.getElementById("phoneNumbersTemp").value = document
				.getElementById("mainPhoneNumber").value;

			$("#saveSupplierButton").click(function(e) {
				var checkValid = true;
				$("#phoneNo").removeClass("is-invalid");
				$("#dup_phno_msg").html("");

				if ($("#name").val() == null || $("#name").val() == "") {
					$("#name").addClass("is-invalid");
					$("#name_msg").removeClass("custom_error_hide");
					$("#name_msg").html("This field is required.");
					checkValid = false;
				} else {
					$("#name").removeClass("is-invalid");
					$("#name_msg").addClass("custom_error_hide");
					$("#name_msg").html("");
				}

				var checkedCategories = [];
				var cardNos = [];
				var paymentTypes = [];
				$(".checkSingle:checked").each(function() {
					checkedCategories.push($(this).val());
				});
				if (checkedCategories.length) {
					$(".checkSingle").removeClass("is-invalid");
					$("#category_msg").add("custom_error_hide");
					$("#category_msg").html("");
					$("#selectedCategoryDTOList").val(checkedCategories);
				} else {
					$(".checkSingle").addClass("is-invalid");
					$("#category_msg").removeClass("custom_error_hide");
					$("#category_msg").html("Please select at least one category.");
					checkValid = false;
				}

				if ($("#paymentTypeGp").hasClass("custom_error_hide") && $("#cardNumberGp").hasClass("custom_error_hide")) {
					$("#paymentType").removeClass("is-invalid");
					$("#payment_type_msg").html("");
					$("#payment_type_msg").addClass("custom_error_hide");

					$("#cardNo").removeClass("is-invalid");
					$("#must_num_msg").html("");
					$("#must_num_msg").addClass("custom_error_hide");

					for (var i = 1; i <= countId; i++) {

						$("#p_" + i).removeClass("is-invalid");
						$("#payment_type_msg_" + i).html("");
						$("#payment_type_msg_" + i).addClass("custom_error_hide");

						$("#no_" + i).removeClass("is-invalid");
						$("#must_num_msg_" + i).html("");
						$("#must_num_msg_" + i).addClass("custom_error_hide");
					}
				} else {
					if ($("#paymentType").val() == undefined || $("#paymentType").val() == "") {
						$("#paymentType").addClass("is-invalid");
						$("#payment_type_msg").html("Please select payment type.");
						$("#payment_type_msg").removeClass("custom_error_hide");
						checkValid = false;
					} else {
						paymentTypes.push($("#paymentType").val());
						$("#paymentType").removeClass("is-invalid");
						$("#payment_type_msg").html("");
						$("#payment_type_msg").addClass("custom_error_hide");
					}
					if (!$.isNumeric($("#cardNo").val())) {
						$("#cardNo").addClass("is-invalid");
						$("#must_num_msg").html("Card Number must be numbers.");
						$("#must_num_msg").removeClass("custom_error_hide");
						checkValid = false;
					} else {
						cardNos.push($("#cardNo").val());
						$("#cardNo").removeClass("is-invalid");
						$("#must_num_msg").html("");
						$("#must_num_msg").addClass("custom_error_hide");
					}
					for (var i = 1; i <= countId; i++) {

						if ($("#p_" + i).val() == undefined || $("#p_" + i).val() == "") {
							$("#p_" + i).addClass("is-invalid");
							$("#payment_type_msg_" + i).html("Please select payment type.");
							$("#payment_type_msg_" + i).removeClass("custom_error_hide");
							checkValid = false;
						} else {
							$("#p_" + i).removeClass("is-invalid");
							$("#payment_type_msg_" + i).html("");
							$("#payment_type_msg_" + i).addClass("custom_error_hide");
						}

						if (!$.isNumeric($("#no_" + i).val())) {
							$("#no_" + i).addClass("is-invalid");
							$("#must_num_msg_" + i).html("Card Number must be numbers.");
							$("#must_num_msg_" + i).removeClass("custom_error_hide");
							checkValid = false;
						} else {
							$("#no_" + i).removeClass("is-invalid");
							$("#must_num_msg_" + i).html("");
							$("#must_num_msg_" + i).addClass("custom_error_hide");
						}
					}
				}
				for (var i = 0; i <= countId; i++) {
					var notValid = false;
					if (!$.isNumeric($("#no_" + i).val())) {
						notValid = true;
					}
					if (!notValid) {
						var flag = false;
						for (var l = 0; l < cardNos.length; l++) {
							if (cardNos[l] == $("#no_" + i).val() && paymentTypes[l]==$("#p_"+i).val()) {
								flag = true;
								break;
							};
						}
						if (!flag) {
							cardNos.push($("#no_" + i).val());
							paymentTypes.push($("#p_" + i).val());
							$("#dup_no_msg_" + i).addClass("custom_error_hide");
						} else {
							checkValid = false;
							$("#no_" + i).addClass("is-invalid");
							$("#dup_no_msg_" + i).html("Duplicate card number");
							$("#dup_no_msg_" + i).removeClass("custom_error_hide");
						}
					}
				}
				if (checkValid) {
					$("#supplier-form").attr("action", "supplier.html");
					$("#supplier-form").submit();
				}
			});
		});

var countId = -1;
function addPaymentMethod() {
	console.log("countId = " + countId);
	if (countId == -1) {
		$("#headerGp").removeClass("custom_error_hide");
		$("#paymentTypeGp").removeClass("custom_error_hide");
		$("#cardNumberGp").removeClass("custom_error_hide");
		$("#cardUserNameGp").removeClass("custom_error_hide");
		countId = 0;
	} else {
		var flag = 0;
		if (!$("#paymentTypeGp").hasClass("custom_error_hide")) {
			if ($("#paymentType").val() == undefined || $("#paymentType").val() == "") {
				$("#paymentType").addClass("is-invalid");
				$("#payment_type_msg").html("Please select payment type.");
				$("#payment_type_msg").removeClass("custom_error_hide");
				console.log("Payment Type Err");
				flag++;
			} else {
				$("#paymentType").removeClass("is-invalid");
				$("#payment_type_msg").html("");
				$("#payment_type_msg").addClass("custom_error_hide");
			}
		}

		if (!$("#cardNumberGp").hasClass("custom_error_hide")) {
			if (!$.isNumeric($("#cardNo").val())) {
				$("#cardNo").addClass("is-invalid");
				$("#must_num_msg").html("Card Number must be numbers.");
				$("#must_num_msg").removeClass("custom_error_hide");
				console.log("Card No Err");
				flag++;
			} else {
				$("#cardNo").removeClass("is-invalid");
				$("#must_num_msg").html("");
				$("#must_num_msg").addClass("custom_error_hide");
			}
		}

		for (var i = 1; i <= countId; i++) {

			if ($("#p_" + i).val() == undefined || $("#p_" + i).val() == "") {
				flag++;
				$("#p_" + i).addClass("is-invalid");
				$("#payment_type_msg_" + i).html("Please select payment type.");
				$("#payment_type_msg_" + i).removeClass("custom_error_hide");
				console.log("Payment Type Err = " + countId);
			} else {
				$("#p_" + i).removeClass("is-invalid");
				$("#payment_type_msg_" + i).html("");
				$("#payment_type_msg_" + i).addClass("custom_error_hide");
			}

			if (!$.isNumeric($("#no_" + i).val())) {
				$("#no_" + i).addClass("is-invalid");
				$("#must_num_msg_" + i).html("Card Number must be numbers.");
				$("#must_num_msg_" + i).removeClass("custom_error_hide");
				console.log("Card No Err = " + countId);
				flag++;
			} else {
				$("#no_" + i).removeClass("is-invalid");
				$("#must_num_msg_" + i).html("");
				$("#must_num_msg_" + i).addClass("custom_error_hide");
			}
		}
		if (flag == 0) {
			console.log("flag 0");
			var slot = $("#clone_method").clone();
			slot.prop('id', 'clone_' + (countId + 1));
			slot.find("#paymentTypeGp").prop('id', 'pGp_' + (countId + 1));

			slot.find("#paymentType").removeClass("custom_error_hide");
			slot.find("#paymentType").prop('name', 'cardNumberDTOList[' + (countId + 1) + '].paymentTypeId');
			slot.find("#paymentType").prop('id', 'p_' + (countId + 1));

			slot.find("#payment_type_msg").prop('id', 'payment_type_msg_' + (countId + 1));

			slot.find("#cardNumberGp").prop('id', 'noGp_' + (countId + 1));

			slot.find("#cardNo").removeClass("custom_error_hide");
			slot.find("#cardNo").prop('name', 'cardNumberDTOList[' + (countId + 1) + '].no');
			slot.find("#cardNo").prop('id', 'no_' + (countId + 1));

			slot.find("#dup_no_msg").prop('id', 'dup_no_msg_' + (countId + 1));
			slot.find("#must_num_msg").prop('id', 'must_num_msg_' + (countId + 1));

			slot.find("#cardUserNameGp").prop('id', 'unGp_' + (countId + 1));

			slot.find("#cardUserName").removeClass("custom_error_hide");
			slot.find("#cardUserName").prop('name', 'cardNumberDTOList[' + (countId + 1) + '].name');
			slot.find("#cardUserName").prop('id', 'un_' + (countId + 1));

			if (countId == 0) {
				slot.insertAfter($("#clone_method"));
			} else {
				slot.insertAfter($("#clone_" + countId));
			}
			$('#p_' + (countId + 1)).val('');
			$('#no_' + (countId + 1)).val('');
			$('#un_' + (countId + 1)).val('');
			countId++;
		}
	}
}

function removePaymentMethod() {
	if (countId > 0) {
		$("#clone_" + countId).remove();
		countId--;
	} else {
		$("#headerGp").addClass("custom_error_hide");
		$("#paymentType").removeClass("is-invalid");
		$("#payment_type_msg").html("");
		$("#payment_type_msg").removeClass("custom_error_hide");

		$("#cardNo").removeClass("is-invalid");
		$("#must_num_msg").html("");
		$("#must_num_msg").addClass("custom_error_hide");

		$("#paymentTypeGp").addClass("custom_error_hide");
		$("#cardNumberGp").addClass("custom_error_hide");
		$("#cardUserNameGp").addClass("custom_error_hide");

		$("#paymentType").val("");
		$("#cardNo").val("");
		$("#cardUserName").val("");
		countId=-1;
	}
}

function addNewPhoneNo() {
	var addedPhoneNumbers = document.getElementById("phoneNo").value;
	var phoneNumbersTemp = document.getElementById("phoneNumbersTemp").value;
	var selectedPhoneNumbers = $("#e1").val();
	if (selectedPhoneNumbers == null) {
		selectedPhoneNumbers = "";
	}
	if (addedPhoneNumbers != null && addedPhoneNumbers != "" && addedPhoneNumbers != undefined) {
		$("#phoneNo").removeClass("is-invalid");
		$("#dup_phno_msg").html("");
		$("#dup_phno_msg").addClass("custom_error_hide");
		$.get("addPhoneNumber.html?phoneNumbersTemp="
			+ phoneNumbersTemp + "&addedPhoneNumbers=" + addedPhoneNumbers
			+ "&selectedPhoneNumbers=" + selectedPhoneNumbers, function(data, status) {
				if(data!='error'){
					var obj = JSON.parse(data);
					var phoneNumbers = obj.phoneNumbers;
					var isAlreadyExists = obj.isAlreadyExists;
					if (isAlreadyExists != null && isAlreadyExists != "") {
						$("#phoneNo").addClass("is-invalid");
						$("#dup_phno_msg").html("This phone number is already added!");
						$("#dup_phno_msg").removeClass("custom_error_hide");
					} else {
						$("#phoneNo").removeClass("is-invalid");
						$("#dup_phno_msg").html("");
						$("#dup_phno_msg").addClass("custom_error_hide");
	
						phoneNumbersTemp = "";
						$('#e1').empty();
						for (var x in phoneNumbers) {
							if (phoneNumbersTemp != null && phoneNumbersTemp != "") {
								phoneNumbersTemp += "," + phoneNumbers[x];
							} else {
								phoneNumbersTemp += phoneNumbers[x];
							}
							$('#e1').append(
								'<option selected="selected" value="' + phoneNumbers[x]
								+ '">' + phoneNumbers[x] + '</option>');
	
						}
						document.getElementById("phoneNo").value = "";
						document.getElementById("phoneNumbersTemp").value = phoneNumbersTemp;
					}
				}else{
					document.getElementById("modalBounceInDown").style.display='block';
					$("#commonLabel").removeClass('custom_error_hide');
					$("#commonLabel").append(
						"<spring:message code='labels.system_error${language}'/>"
					);
				}
			});
	} else {
		$("#phoneNo").addClass("is-invalid");
		$("#dup_phno_msg").html("Phone number is required.");
		$("#dup_phno_msg").removeClass("custom_error_hide");
	}
}

