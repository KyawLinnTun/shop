<%@ include file="../../include/importTag.jsp"%>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/validation.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/script.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/commonAjax.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/supplier/form-validation-supplier.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/supplier/supplier.css">
<div class="main-body">
	<div class="page-wrapper">
		<div class="row">
			<div class="col-sm-12">
				<label class="col-md-6 control-label"
					style="font-size: 20px; text-align: left;"> <spring:message
						code="${pageTitle}${language}" />
				</label>
				<div class="card">
					<div class="card-body">
						<form:form modelAttribute="supplierDTO" method="POST"
							id="supplier-form" action="supplier.html"
							enctype="multipart/form-data">
							<form:hidden path="id" id="id" />
							<form:hidden path="phoneNo" id="mainPhoneNumber" />
							<form:hidden path="cardNumberSizeList" id="cardNumberSizeList" />
							<form:hidden path="selectedCategoryDTOList"
								id="selectedCategoryDTOList" />
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="mandatory" for="name"><spring:message
												code="labels.name_label${language}" /></label> <input type="text"
											name="name" value="${supplierDTO.name }" class="form-control"
											id="name" placeholder="<spring:message
												code='labels.enter_supplier_name${language}' />" maxlength="70">
										<label class="text-danger custom_error_hide"
											style="font-size: 80%;" id="name_msg"></label>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="name"><spring:message
												code="labels.status_label${language}" /></label>
										<form:select path="status" class="form-control" id="status">
											<form:options items="${statusList }" itemLabel="desc"
												itemValue="code" />
										</form:select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="name"><spring:message
												code="labels.address_label${language}" /></label>
										<textarea id="address" rows="3" spellcheck="false"
											name="address" class="form-control"
											placeholder="<spring:message
												code='labels.enter_supplier_address${language}' />" maxlength="500">${supplierDTO.address }</textarea>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="mandatory" for="name"><spring:message
												code="labels.category_label${language}" /></label>
										<div class="row">
											<c:forEach varStatus="status" var="row"
												items="${categoryDTOList}">
												<div class="col-lg-4 mb-3">
													<div class="checkbox checkbox-info d-inline">
														<input type="checkbox" name="checkbox-i-1"
															id="checkbox${row.id }" class="checkSingle"
															value="${row.id }"
															${row.selectedFacility == true ? 'checked' : ''}>
														<c:if test="${language == '' or language == null }">
															<label for="checkbox${row.id }" class="cr">${row.name }</label>
														</c:if>
														<c:if test="${language != '' and language != null }">
															<label for="checkbox${row.id }" class="cr">${row.mmFontName }</label>
														</c:if>
													</div>
												</div>
											</c:forEach>
										</div>
										<label class="text-danger custom_error_hide"
											style="font-size: 80%;" id="category_msg"></label>
									</div>
								</div>
							</div>
							<fieldset class="col-md-12 mb-4">
								<legend>
									<spring:message code="labels.phone_numbers_label${language}" />
								</legend>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<input type="text" class="form-control" id="phoneNo"
												placeholder="<spring:message
												code='labels.enter_phone_numbers${language}' />" name="tempPh"
												value="${supplierDTO.tempPh }" /> <label
												class="text-danger custom_error_hide"
												style="font-size: 80%;" id="dup_phno_msg"></label>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<button type="button" class="btn btn-sm btn-primary"
												onclick="addNewPhoneNo()" data-toggle="tooltip">
												<spring:message code="labels.add_btn${language}" />
											</button>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-1">
										<div class="form-group">
											<label><spring:message
													code="labels.phone_numbers_label${language}" /></label>
										</div>
									</div>
									<div class="col-md-5">
										<div class="form-group">
											<input type="hidden" id="phoneNumbersTemp"> <select
												id="e1" class="form-control phone-numbers"
												name="phoneNumbers" multiple="multiple">
												<c:forEach items="${supplierDTO.phNoList}" var="p">
													<option value="${p}" selected="selected">${p}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-md-6"></div>
								</div>

							</fieldset>
							<fieldset class="col-md-12 mb-4">
								<legend>
									<spring:message code="labels.payment_methods_label${language}" />
									<button type="button" class="btn btn-sm btn-primary"
										id="btn-add" onclick="addPaymentMethod()">
										<spring:message code="labels.add_btn${language}" />
									</button>
									<button type="button" class="btn btn-sm btn-danger"
										id="btn-remove" onclick="removePaymentMethod()">
										<spring:message code="labels.remove_btn${language}" />
									</button>
								</legend>
								<div class="row custom_error_hide" id="headerGp">
									<div class="col-md-4">
										<div class="form-group">
											<label class="mandatory" for="paymentType"><spring:message
													code="labels.payment_type_label${language}" /></label>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="mandatory"><spring:message
													code="labels.card_number_label${language}" /></label>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="mandatory"><spring:message
													code="labels.name_label${language}" /></label>
										</div>
									</div>
								</div>
								<div class="row" id="clone_method">
									<div class="col-md-4">
										<div class="form-group custom_error_hide" id="paymentTypeGp">
											<form:select path="cardNumberDTOList[0].paymentTypeId"
												class="form-control" id="paymentType">
												<form:option value=""><spring:message
												code='labels.select_payment_type${language}' /></form:option>
												<form:options items="${paymentTypeDTOList }"
													itemLabel="name" itemValue="id" />
											</form:select>
											<label class="text-danger custom_error_hide"
												style="font-size: 11px;" id="payment_type_msg"></label>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group custom_error_hide" id="cardNumberGp">
											<input type="text" name="cardNumberDTOList[0].no"
												value="${supplierDTO.cardNumberDTOList[0].no }"
												class="form-control" id="cardNo"
												placeholder="<spring:message
												code='labels.enter_card_number${language}' />" maxlength="70"> <label
												class="text-danger custom_error_hide"
												style="font-size: 80%;" id="dup_no_msg"></label> <label
												class="text-danger custom_error_hide"
												style="font-size: 80%;" id="must_num_msg"></label>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group custom_error_hide" id="cardUserNameGp">
											<input type="text" name="cardNumberDTOList[0].name"
												value="${supplierDTO.cardNumberDTOList[0].name }"
												class="form-control" id="cardUserName"
												placeholder="<spring:message
												code='labels.enter_account_name${language}' />" maxlength="70">
										</div>
									</div>
								</div>
							</fieldset>
							<button type="button" class="btn btn-primary"
								id="saveSupplierButton" title="btn btn-primary"
								data-toggle="tooltip">
								<c:if
									test="${supplierDTO!=null && supplierDTO.id ne 
									null && supplierDTO.id>0}">
									<spring:message code="labels.update_btn${language}" />
								</c:if>
								<c:if test="${supplierDTO!=null && supplierDTO.id eq null}">
									<spring:message code="labels.save_btn${language}" />
								</c:if>
							</button>
							<a href="supplier.html" class="btn btn-outline-danger"> <spring:message
									code="labels.reset_btn${language}" />
							</a>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	var contextPath = '${pageContext.request.contextPath}/';
</script>