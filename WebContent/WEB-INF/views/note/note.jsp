<%@ include file="../../include/importTag.jsp"%>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/validation.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/script.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/commonAjax.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/note/note.css">
<script
	src="<%=request.getContextPath()%>/resources/json/manufactureCountry.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/json/manufactureCountry_mm.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/note/note.js"></script>
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
						<form:form modelAttribute="noteDTO" method="POST" id="note-form"
							action="note.html" enctype="multipart/form-data">
							<input type="hidden" id="manufactureCountryValue"
								value="${noteDTO.manufactureCountry }">
							<input type="hidden" id="language" value="${language }">
							<form:hidden path="id" id="id" />
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="item" class="mandatory"><spring:message
												code="labels.item_label${language}" /></label> <input type="text"
											name="itemName" value="${noteDTO.itemName }"
											class="form-control" id="itemName"
											placeholder="<spring:message
												code='labels.enter_item_name_place_holder${language}' />"
											maxlength="150">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="mandatory"><spring:message
												code="labels.category_label${language }" /></label>
										<c:if test="${language == '' or language == null }">
											<form:select path="categoryDTO.id" id="category"
												class="js-example-responsive form-control">
												<form:option value="">
													<spring:message code='labels.select_category${language}' />
												</form:option>
												<form:options items="${categoryDTOList}" itemValue="id"
													itemLabel="name" />
											</form:select>
										</c:if>
										<c:if test="${language != '' and language != null }">
											<form:select path="categoryDTO.id" id="category"
												class="js-example-responsive form-control">
												<form:option value="">
													<spring:message code='labels.select_category${language}' />
												</form:option>
												<form:options items="${categoryDTOList}" itemValue="id"
													itemLabel="mmFontName" />
											</form:select>
										</c:if>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="mandatory"><spring:message
												code="labels.remaining_label${language }" /></label> <input
											type="number" name="remaining" value="${noteDTO.remaining }"
											class="form-control" id="remaining"
											placeholder="<spring:message
												code='labels.enter_remaining_place_holder${language}' />">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label><spring:message
												code="labels.size_label${language }" /></label> <input type="text"
											name="size" value="${noteDTO.size }" class="form-control"
											id="size"
											placeholder="<spring:message
												code='labels.enter_size_place_holder${language}' />"
											maxlength="30">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="color"><spring:message
												code="labels.color_label${language }" /></label> <input type="text"
											name="color" value="${noteDTO.color }" class="form-control"
											id="color"
											placeholder="<spring:message
												code='labels.enter_color_place_holder${language}' />"
											maxlength="20">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label><spring:message
												code="labels.brand_label${language }" /></label> <input type="text"
											id="brand" name="brand" value="${noteDTO.brand }"
											class="form-control form-control-sm"
											placeholder="<spring:message
												code='labels.enter_brand_place_holder${language}' />"
											maxlength="100">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><spring:message
												code="labels.manufacture_country_label${language }" /></label> <select
											name="manufactureCountry" id="manufactureCountryList"
											class="js-example-responsive form-control">
											<option value="0"><spring:message
													code='labels.select_manufacture_country${language}' /></option>
										</select>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="name"><spring:message
												code="labels.description_label${language }" /></label>
										<textarea id="description" rows="3" spellcheck="false"
											name="description" class="form-control"
											placeholder="<spring:message
												code='labels.enter_description${language}' />"
											maxlength="2000">${noteDTO.description }</textarea>
									</div>
								</div>
							</div>
							<button type="submit" class="btn btn-primary" id="saveNoteButton"
								title="btn btn-primary" data-toggle="tooltip">
								<c:if
									test="${noteDTO!=null && noteDTO.id ne 
									null && noteDTO.id>0}">
									<spring:message code="labels.update_btn${language }" />
								</c:if>
								<c:if test="${noteDTO!=null && noteDTO.id eq null}">
									<spring:message code="labels.save_btn${language }" />
								</c:if>
							</button>
							<a href="note.html" class="btn btn-outline-danger"><spring:message
									code="labels.reset_btn${language }" /></a>
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