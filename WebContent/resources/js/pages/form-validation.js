'use strict';
$(document)
		.ready(
				function() {
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
						$('#validation-form123')
								.validate(
										{
											ignore : '.ignore, .select2-input',
											focusInvalid : false,
											rules : {
												'validationName' : {
													required : true,
													name_validation : true
												},
												'validationEmail' : {
													// required : true,
													email : true
												},
												'validationPassword' : {
													required : true,
													minlength : 6,
													maxlength : 20
												},
												'validation-password-confirmation' : {
													required : true,
													minlength : 6,
													equalTo : 'input[name="validation-password"]'
												},
												'validationDoctorLicense' : {
													required : true,
													number_format : true,
													name_validation : true
												},
												'validationLatitude' : {
													number_format : true
												},
												'validationLongitude' : {
													number_format : true
												},
												'validation-required' : {
													required : true
												},
												'validation-url' : {
													required : true,
													url : true
												},
												'validationPhone' : {
													required : true,
													phone_format : true
												},
												'validationStateDTO.id' : {
													required : true
												},
												'validationTownshipDTO.id' : {
													required : true
												},
												'specialtyId' : {
													required : true
												},
												'validationGender' : {
													required : true
												},
												'healthCheckIdList' : {
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
