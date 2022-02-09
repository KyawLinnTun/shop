<%@ include file="../../include/importTag.jsp"%>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/validation.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/script.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/commonAjax.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/json/manufactureCountry.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/json/manufactureCountry_mm.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/item/item.js"></script>
<style>
#select2-category-container {
	color: #adb7be;
	padding-top: 5px;
	padding-left: 25px;
}

#select2-manufactureCountryList-container {
	color: #adb7be;
	padding-top: 5px;
	padding-left: 25px;
}
</style>
<div class="main-body">
	<div id="deleteItemConfirm" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">
						<spring:message code="labels.delete_item${language}" />
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p id="confirmText">
						<spring:message
							code="labels.are_you_sure_you_want_to_delete_item${language}" />
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">No</button>
					<form action="deleteItemById.html" method="POST">
						<input id="itemId" type="hidden" name="id">
						<button type="submit" class="btn btn-primary">Yes</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade bd-example-modal-lg" id="duplicateItemsList"
		tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<spring:message
							code="labels.please_check_following_list_created_or_not${language}" />
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="table-responsive">
						<table id="duplicateTable" class="table table-hover"
							style="width: 100%">
							<thead>
								<tr class="table-header">
									<th><spring:message code="labels.no_label${language }" /></th>
									<th><spring:message code="labels.item_label${language }" /></th>
									<th><spring:message
											code="labels.category_label${language }" /></th>
									<th><spring:message code="labels.size_label${language }" /></th>
									<th><spring:message code="labels.color_label${language }" /></th>
									<th><spring:message
											code="labels.manufacture_country_label${language }" /></th>
									<th><spring:message code="labels.brand_label${language }" /></th>
									<th><spring:message code="labels.status_label${language }" /></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<a href="item-list.html" class="btn btn-primary"><spring:message
							code="labels.yes_btn${language }" /></a>
					<button type="button" class="btn btn-primary" id="createNewBtn"
						title="btn btn-primary" data-toggle="tooltip">
						<spring:message code="labels.no_create_new_btn${language }" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="page-wrapper">
		<div class="row">
			<div class="col-sm-12">
				<label class="col-md-6 control-label"
					style="font-size: 20px; text-align: left;"> <spring:message
						code="${pageTitle}${language}" />
				</label>
				<div class="card">
					<div class="card-body">
						<form:form modelAttribute="itemDTO" method="POST" id="item-form"
							action="item.html" enctype="multipart/form-data">
							<input type="hidden" id="manufactureCountryValue"
								value="${itemDTO.manufactureCountry }">
							<input type="hidden" id="language" value="${language }">
							<form:hidden path="image" value="${itemDTO.image}" />
							<form:hidden path="id" id="id" />
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="item" class="mandatory"><spring:message
												code="labels.item_label${language}" /></label> <input type="text"
											name="name" value="${itemDTO.name }" class="form-control"
											id="itemName"
											placeholder="<spring:message
												code='labels.enter_item_name_place_holder${language}' />"
											maxlength="150"> <label
											class="text-danger custom_error_hide" style="font-size: 80%;"
											id="item_name_msg"></label>
									</div>
								</div>
								<div class="col-md-5">
									<div class="form-group">
										<label><spring:message
												code="labels.image_label${language}" /></label>
										<div class="custom-file">
											<input type="file" class="custom-file-input" name="imageFile"
												accept="image/x-png, image/jpeg"
												onchange="document.getElementById('output').src = window.URL.createObjectURL(this.files[0]);document.getElementById('output').style.width='80px';document.getElementById('output').style.height= '65px';javascript:document.getElementById('fileName').innerHTML = this.files.item(0).name;
												document.getElementById('imgherf').href = window.URL.createObjectURL(this.files[0]);"
												id="validatedCustomFile"> <label id="fileName"
												style="font-size: 12px;" class="custom-file-label"
												for="validatedCustomFile"> <c:choose>
													<c:when
														test="${itemDTO.sourceName!='' && itemDTO.sourceName!=null}">
														itemDTO.sourceName
													</c:when>
													<c:otherwise>
														<spring:message code="labels.upload_item_image${language}" />
													</c:otherwise>
												</c:choose>
											</label>

										</div>
									</div>
								</div>
								<c:if
									test="${itemDTO.image ne null and not empty itemDTO.image}">
									<a href="${imagePath}${itemDTO.image}" data-lightbox="roadtrip"
										id="imgherf" data-title="Item Image" data-footer="Item Image"><img
										id="output" src="${imagePath}${itemDTO.image}"
										style="width: 80px; height: 65px;" alt=""></a>
								</c:if>
								<c:if test="${itemDTO.image eq null or empty itemDTO.image}">
									<a href="" data-lightbox="roadtrip" id="imgherf"
										data-title="Item Image" data-footer="Item Image"><img
										id="output"></a>
								</c:if>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="mandatory"><spring:message
												code="labels.buying_price_label${language }" /></label> <input
											type="text" name="buyPrice" value="${itemDTO.buyPrice }"
											class="form-control" id="buyingPrice"
											placeholder="<spring:message
												code='labels.enter_buying_price${language}' />">
										<label class="text-danger custom_error_hide"
											style="font-size: 80%;" id="buyPrice_msg"></label>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="mandatory"><spring:message
												code="labels.sell_price_label${language }" /></label> <input
											type="text" name="price" value="${itemDTO.price }"
											class="form-control" id="sellPrice"
											placeholder="<spring:message
												code='labels.enter_sell_price${language}' />">
										<label class="text-danger custom_error_hide"
											style="font-size: 80%;" id="sellPrice_msg"></label>
									</div>
								</div>
							</div>
							<div class="row">
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
										<label class="text-danger custom_error_hide"
											style="font-size: 80%;" id="category_msg"></label>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label><spring:message
												code="labels.size_label${language }" /></label> <input type="text"
											name="size" value="${itemDTO.size }" class="form-control"
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
											name="color" value="${itemDTO.color }" class="form-control"
											id="color"
											placeholder="<spring:message
												code='labels.enter_color_place_holder${language}' />"
											maxlength="20">
									</div>
								</div>
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
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><spring:message
												code="labels.brand_label${language }" /></label> <input type="text"
											id="brand" name="brand" value="${itemDTO.brand }"
											class="form-control form-control-sm"
											placeholder="<spring:message
												code='labels.enter_brand_place_holder${language}' />"
											maxlength="100">
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
												code="labels.description_label${language }" /></label>
										<textarea id="description" rows="3" spellcheck="false"
											name="description" class="form-control"
											placeholder="<spring:message
												code='labels.enter_description${language}' />"
											maxlength="2000">${itemDTO.description }</textarea>
									</div>
								</div>
							</div>
							<button type="button" class="btn btn-primary" id="saveItemButton"
								title="btn btn-primary" data-toggle="tooltip">
								<c:if
									test="${itemDTO!=null && itemDTO.id ne 
									null && itemDTO.id>0}">
									<spring:message code="labels.update_btn${language }" />
								</c:if>
								<c:if test="${itemDTO!=null && itemDTO.id eq null}">
									<spring:message code="labels.save_btn${language }" />
								</c:if>
							</button>
							<a href="item.html" class="btn btn-outline-danger"><spring:message
									code="labels.reset_btn${language }" /></a>
							<c:if test="${showDeleteBtn==true }">
								<button type="button" class="btn btn-danger"
									onclick="deleteItem()" title="btn btn-primary"
									data-toggle="tooltip">
									<spring:message code="labels.delete_btn${language }" />
								</button>
							</c:if>
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