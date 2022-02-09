<%@ include file="../../include/importTag.jsp"%>
<style>
#select2-category-container {
	color: #adb7be;
	padding-top: 5px;
	padding-left: 25px;
}
</style>
<div class="main-body">
	<div id="deleteSupplierConfirm" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">
						<spring:message code="labels.delete_note${language }" />
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p id="confirmText">
						<spring:message
							code="labels.are_you_sure_you_want_to_delete_supplier${language }" />
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">
						<spring:message code="labels.no_btn${language }" />
					</button>
					<form action="deleteSupplierById.html" method="POST">
						<input id="supplierId" type="hidden" name="id">
						<button type="submit" class="btn btn-primary">
							<spring:message code="labels.yes_btn${language }" />
						</button>
					</form>
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
						<form:form modelAttribute="supplierDTO" method="POST"
							action="supplier-list.html" enctype="multipart/form-data">

							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label><spring:message
												code="labels.name_label${language}" /></label> <input type="text"
											id="name" name="name" value="${supplierDTO.name }"
											class="form-control form-control-sm"
											placeholder="<spring:message
												code='labels.enter_supplier_name${language}' />" maxlength="70">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label><spring:message
												code="labels.phone_numbers_label${language}" /></label> <input
											type="text" value="${supplierDTO.phoneNo }" name="phoneNo"
											class="form-control" id="phoneNo"
											placeholder="<spring:message
												code='labels.enter_phone_numbers${language}' />" maxlength="70">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label><spring:message
												code="labels.category_label${language}" /></label>
										<c:if test="${language == '' or language == null }">
											<form:select path="categoryId" id="category"
												class="js-example-responsive form-control">
												<form:option value=""><spring:message
												code='labels.all_place_holder${language}' /></form:option>
												<form:options items="${categoryDTOList}" itemValue="id"
													itemLabel="name" />
											</form:select>
										</c:if>
										<c:if test="${language != '' and language != null }">
											<form:select path="categoryId" id="category"
												class="js-example-responsive form-control">
												<form:option value=""><spring:message
												code='labels.all_place_holder${language}' /></form:option>
												<form:options items="${categoryDTOList}" itemValue="id"
													itemLabel="mmFontName" />
											</form:select>
										</c:if>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label><spring:message
												code="labels.status_label${language}" /></label>
										<form:select path="status" class="form-control" id="status">
											<form:option value=""><spring:message
												code='labels.all_place_holder${language}' /></form:option>
											<form:option value="1">Active</form:option>
											<form:option value="2">Inactive</form:option>
										</form:select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 text-right">
									<button type="submit" class="btn btn-primary"
										title="btn btn-primary" data-toggle="tooltip">
										<spring:message code="labels.search_btn${language}" />
									</button>
									<a href="supplier-list.html" class="btn btn-outline-danger">
										<spring:message code="labels.reset_btn${language}" />
									</a>
								</div>
							</div>
							<div class="table-responsive">
								<table
									class="display table nowrap table-striped table-hover zero-configuration"
									style="width: 100%">
									<thead>
										<tr>
											<th><spring:message code="labels.no_label${language}" /></th>
											<th><spring:message code="labels.edit_btn${language}" /></th>
											<th><spring:message code="labels.delete_btn${language}" /></th>
											<th><spring:message code="labels.name_label${language}" /></th>
											<th><spring:message
													code="labels.phone_numbers_label${language}" /></th>
											<th><spring:message
													code="labels.category_label${language}" /></th>
											<th><spring:message
													code="labels.address_label${language}" /></th>
											<th><spring:message
													code="labels.status_label${language}" /></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${supplierList}" var="d" varStatus="i">
											<tr>
												<td>${i.index+1 }</td>
												<td><a href="supplier.html?id=${d.id}"
													style="color: white;"><i class="fa fa-edit"></i></a></td>
												<td><a class="danger" onClick="deleteSupplier(${d.id})">
														<i class="fa fa-trash"></i>
												</a></td>

												<td>${d.name }</td>
												<td>${d.phoneNo }</td>
												<td>${d.selectedCategories }</td>
												<td>${d.address }</td>
												<td><c:if test="${d.status eq 1}">
														<button type="button" class="btn btn-rounded btn-primary"
															style="padding: 0px 20px;">${d.statusDesc}</button>
													</c:if> <c:if test="${d.status eq 2}">
														<button type="button" class="btn btn-rounded btn-danger"
															style="padding: 0px 20px;">${d.statusDesc}</button>
													</c:if></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	var contextPath = '${pageContext.request.contextPath}/';
	function deleteSupplier(supplierId) {
		$("#supplierId").val(supplierId);
		$("#deleteSupplierConfirm").modal('toggle');
	}
	function closeWarningBox() {
		var alertBox = document.getElementById("deleteSupplierConfirm");
		alertBox.style.display = "none";
	}
</script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>