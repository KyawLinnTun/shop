$(document)
		.ready(
				function() {
					var activity;
					var language=document.getElementById('language').value;
					if(language!=null && language.length>0){
						console.log('test');
						activity = jsonStr_MM;
					}else{
						activity = jsonStr;
					}
			var selected = "";
			for (var i in activity) {
				$('#manufactureCountryList').append(
					'<option value="' + activity[i].code + '"'
					+ '>' + activity[i].name + '</option>');
			}	
			
			$(function() {
						var isExist = "";
						// [ Add phone validator ] start
						$.validator.addMethod('phone_format', function(value,
								element) {
							return this.optional(element)
									|| /^[0-9,.\-\+\s]*$/.test(value);
						}, 'Please enter a valid phone number.');
						$.validator.addMethod('number_format', function(value,
								element) {
							return $.isNumeric(value) || value == "";
						}, 'Please enter a valid number.');

						$.validator.addMethod('name_validation', function(
								value, element) {
							isAlreadyExist(document.getElementById("id").value,
									value);
							return this.optional(element) || isExist == "";
						}, 'This field is already exist.');
						var getUrl = "";
						var getData = {};
						var getErrMsg = "Cannot get this.";

						function isAlreadyExist(id, name) {

							getUrl = contextPath + "isAlreadyExistName.html";
							getData = {
								id : id,
								name : name
							};
							getOkCallBack()

						}

						function getOkCallBack() {
							ajaxCall(getUrl, getData, getDataSuccessCallBack,
									getErrMsg);
						}

						function getDataSuccessCallBack(data) {
							isExist = data;
						}

						function ajaxCall(url, data, callback, dialogueErrMsg,
								emptyCallBack) {
							$
									.ajax({
										type : "GET",
										encoding : "UTF-8",
										url : url,
										contentType : "application/json",
										data : data,
										dataType : 'json',
										// timeout: 10000,
										success : function(res) {
											if (res.information != null) {
												bootstrap_alert(
														'#alert_placeholder',
														'alert-danger',
														res.information, 9000);
												callback('')
												// $('#modal-info').modal('hide');
											} else if (res.emptyData != null) {
												if (typeof emptyCallBack == "function") {
													emptyCallBack();
												}
											} else {
												if (res.successMessage != null) {
													bootstrap_alert(
															'#alert_placeholder',
															'alert-success',
															res.successMessage,
															9000);
													// /
													// $('#modal-info').modal('hide');
												}
												if (typeof callback == "function") {
													return callback(res.data);
												}
											}
										},
										error : function(e) {
											bootstrap_alert(
													'#alert_placeholder',
													'alert-danger',
													dialogueErrMsg, 9000);
											// $('#modal-info').modal('hide');
										}
									});
						}

						// [ Initialize validation ] start
						$('#orderDialog')
								.validate(
										{
											ignore : '.ignore, .select2-input',
											focusInvalid : false,
											rules : {
												'name' : {
													required : true
												},
												'categoryDTO.id' : {
													required : true
												},
												'supplierDTO.id' : {
													required : true
												},
												'date' : {
													required : true
												},
												'singlePrice' : {
													required : true,
													number_format : true
												},
												'count' : {
													required : true,
													number_format : true
												},
												'totalPrice' : {
													required : true,
													number_format : true
												},
												'description' : {
													required : true
												}
											// Checkbox groups //
											},

											// Errors //

											errorPlacement : function errorPlacement(
													error, element) {
												var $parent = $(element)
														.parents('.form-group');

												// Do not duplicate errors
												if ($parent
														.find('.jquery-validation-error').length) {
													return;
												}

												$parent
														.append(error
																.addClass('jquery-validation-error small form-text invalid-feedback'));
											},
											highlight : function(element) {
												var $el = $(element);
												var $parent = $el
														.parents('.form-group');

												$el.addClass('is-invalid');

												// Select2 and Tagsinput
												if ($el
														.hasClass('select2-hidden-accessible')
														|| $el
																.attr('data-role') === 'tagsinput') {
													$el.parent().addClass(
															'is-invalid');
												}
											},
											unhighlight : function(element) {
												$(element).parents(
														'.form-group').find(
														'.is-invalid')
														.removeClass(
																'is-invalid');
											}
										});

					});
						
				});


var getSupplierListByCheckingItemUrl = "";
					var getItemsData = {};
					var getSupplierListErrMsg = "Cannot get this.";

					function getSupplierListByCheckingItem(categoryId) {

						getSupplierListByCheckingItemUrl = contextPath
								+ "getSupplierListUrl.html";
						getItemsData = {
							categoryId: categoryId
						};
						getSupplierListByCheckingItemOkCallBack()

					}

					function getSupplierListByCheckingItemOkCallBack() {
						ajaxCall(getSupplierListByCheckingItemUrl,
								getItemsData,
								getSupplierListByChecingItemDataSuccessCallBack,
								getSupplierListErrMsg);
					}

					function getSupplierListByChecingItemDataSuccessCallBack(data) {
						var obj = JSON.parse(data);
						var pinId=document.getElementById('pinSupplier').value;
						$('#supplier').empty();
						$('#supplier').append('<option value=""><fmt:message key="labels.select_one_supplier'+document.getElementById('language').value+'"/></option>');
						var selected='';
							for (x in obj) {
								if(obj[x].id==pinId){
											selected=' selected';
								}
								$('#supplier').append(
										'<option value="' + obj[x].id +'"'+selected
										+ '>'
												+ obj[x].supplierLabel + '</option>')
												
								selected=''
	
							}
							$("#showSupplierList").modal('toggle');
					}
					

function showSupplierListDialog(noteId,itemName,size,color,manufactureCountry,brand,categoryId,description){
	getSupplierListByCheckingItem(categoryId);
	$('#noteId').val(noteId);
	$('#itemName').val(itemName);
	$('#diaCategory').val(categoryId);
	$('#manufactureCountryList').val(manufactureCountry);
	$('#diaBrand').val(brand);
	$('#diaColor').val(color);
	$('#filterItemName').val($('#item').val());
	$('#filterCategoryId').val($('#category').val());
	$('#filterNotedUserId').val($('#notedUser').val());
	$('#notedSupplier').val(document.getElementById('pinSupplier').value);
	$('#diaSize').val(size);
	$("#diaNoteId").val(noteId);
	$("#itemDescription").val(description);
}

function deleteNote(noteId) {
	$("#noteId").val(noteId);
	$("#deleteNoteConfirm").modal('toggle');
}
function closeWarningBox() {
	var alertBox = document.getElementById("deleteNoteConfirm");
	alertBox.style.display = "none";
}