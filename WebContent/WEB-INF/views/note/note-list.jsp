<%@ include file="../../include/importTag.jsp"%>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/validation.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/script.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/commonAjax.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/json/manufactureCountry.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/json/manufactureCountry_mm.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/note/note-list.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<style>
#select2-category-container {
	color: #adb7be;
	padding-top: 5px;
	padding-left: 25px;
}

#select2-notedUser-container {
	color: #adb7be;
	padding-top: 5px;
	padding-left: 25px;
}

#select2-diaCategory-container {
	color: #adb7be;
	padding-top: 5px;
	padding-left: 25px;
}

#select2-supplier-container {
	color: #adb7be;
	padding-top: 5px;
	padding-left: 25px;
}

#select2-manufactureCountryList-container {
	color: #adb7be;
	padding-top: 5px;
	padding-left: 25px;
}

#select2-pinSupplier-container {
	color: #adb7be;
	padding-top: 5px;
	padding-left: 25px;
}
</style>
<div class="main-body">
	<div id="deleteNoteConfirm" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">
						<spring:message code="labels.delete_note${language}" />
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p id="confirmText">
						<spring:message
							code="labels.are_you_sure_you_want_to_delete_note${language}" />
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">No</button>
					<form action="deleteNoteById.html" method="POST">
						<input id="noteId" type="hidden" name="id">
						<button type="submit" class="btn btn-primary">Yes</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade bd-example-modal-lg" id="showSupplierList"
		tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<spring:message
							code="labels.fill_your_order_informations${language}" />
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form:form modelAttribute="orderDTO" method="POST" id="orderDialog"
					action="addOrder.html" enctype="multipart/form-data">
					<form:hidden path="filterItemName" id="filterItemName" />
					<form:hidden path="filterCategoryId" id="filterCategoryId" />
					<form:hidden path="filterNotedUserId" id="filterNotedUserId" />
					<form:hidden path="noteId" id="diaNoteId" />
					<form:hidden path="notedSupplier" id="notedSupplier" />
					<div class="modal-body">
						<div class="row">
							<div class="col">
								<div class="form-group">
									<label class="col-form-label mandatory"><spring:message
											code="labels.item_label${language}" /></label> <input type="text"
										id="itemName" name="name" value="${orderDTO.name }"
										class="form-control form-control-sm"
										placeholder="<spring:message code='labels.enter_item_name_place_holder${language}'/>">
								</div>
							</div>
							<div class="col">
								<div class="form-group">
									<label class="col-form-label mandatory"><spring:message
											code="labels.category_label${language}" /></label>
									<form:select path="categoryDTO.id" id="diaCategory"
										class="form-control">
										<form:option value="">
											<spring:message code="labels.select_category${language }" />
										</form:option>
										<form:options items="${categoryDTOList}" itemValue="id"
											itemLabel="name" />
									</form:select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-form-label mandatory"><spring:message
											code="labels.supplier_menu${language }" /></label>
									<form:select path="supplierDTO.id" id="supplier"
										class="form-control">
									</form:select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-form-label mandatory"><spring:message
											code="labels.coming_date_label${language }" /></label> <input
										type="text" id="date" name="receivedDate" class="form-control">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-form-label mandatory"><spring:message
											code="labels.single_price_label${language }" /></label> <input
										type="text" id="singlePrice" name="singlePrice"
										value="${orderDTO.singlePrice }"
										class="form-control form-control-sm"
										placeholder="<spring:message code="labels.enter_single_price${language }"/>">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-form-label mandatory"><spring:message
											code="labels.count_label${language }" /></label> <input
										type="number" id="count" name="count"
										value="${orderDTO.count }"
										class="form-control form-control-sm"
										placeholder="<spring:message code="labels.enter_count${language }"/>">
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-form-label mandatory"><spring:message
											code="labels.total_price_label${language }" /></label> <input
										type="text" id="totalPrice" name="totalPrice"
										value="${orderDTO.totalPrice }"
										class="form-control form-control-sm"
										placeholder="<spring:message code="labels.enter_total_price${language }"/>">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-form-label"><spring:message
											code="labels.brand_label${language }" /></label> <input type="text"
										id="diaBrand" name="brand" value="${orderDTO.brand }"
										class="form-control form-control-sm"
										placeholder="<spring:message code="labels.enter_brand_place_holder${language }"/>"
										maxlength="20">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-form-label"><spring:message
											code="labels.color_label${language }" /></label> <input type="text"
										id="diaColor" name="color" value="${orderDTO.color}"
										class="form-control form-control-sm"
										placeholder="<spring:message code="labels.enter_color_place_holder${language }"/>"
										maxlength="15">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-form-label"><spring:message
											code="labels.manufacture_country_label${language }" /></label> <select
										name="manufactureCountry" id="manufactureCountryList"
										class="form-control">
										<option value=""><spring:message
												code="labels.select_manufacture_country${language }" /></option>
									</select>

								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-form-label"><spring:message
											code="labels.size_label${language }" /></label> <input type="text"
										id="diaSize" name="size" value="${orderDTO.size}"
										class="form-control form-control-sm"
										placeholder="<spring:message code="labels.enter_size_place_holder${language }"/>"
										maxlength="30">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-form-label mandatory"><spring:message
											code="labels.count_description_label${language }" /></label>
									<textarea class="form-control" id="description"
										name="description"></textarea>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-form-label"><spring:message
											code="labels.item_description_label${language }" /></label>
									<textarea class="form-control" id="itemDescription"
										name="itemDescription"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">
							<spring:message code="labels.close_btn${language }" />
						</button>
						<button type="submit" class="btn btn-primary">
							<spring:message code="labels.order_btn${language }" />
						</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<div class="page-wrapper">
		<div class="row">
			<div class="col-sm-12">
				<label class="col-md-6 control-label"
					style="font-size: 20px; text-align: left;"> <spring:message
						code="${pageTitle}${language }" />
				</label>
				<div class="card">
					<div class="card-body">
						<label id="alert_msg" style="display: none;"></label>
						<form:form modelAttribute="noteDTO" method="POST"
							action="note-list.html" enctype="multipart/form-data">
							<input type="hidden" id="language" value="${language }">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label><spring:message
												code="labels.item_label${language }" /></label> <input type="text"
											id="item" name="itemName" value="${noteDTO.itemName }"
											class="form-control form-control-sm"
											placeholder="<spring:message code="labels.enter_item_name_place_holder${language }"/>"
											maxlength="70">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label for="category"><spring:message
												code="labels.category_label${language }" /></label>
										<form:select path="categoryDTO.id" id="category"
											class="js-example-responsive form-control">
											<form:option value="">
												<spring:message code="labels.all_place_holder${language }" />
											</form:option>
											<form:options items="${categoryDTOList}" itemValue="id"
												itemLabel="name" />
										</form:select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label><spring:message
												code="labels.noted_user_label${language }" /></label>
										<form:select path="notedUser.id" id="notedUser"
											class="js-example-responsive form-control">
											<form:option value="">
												<spring:message code="labels.all_place_holder${language }" />
											</form:option>
											<form:options items="${userDTOList}" itemValue="id"
												itemLabel="name" />
										</form:select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 text-right">
									<button type="submit" class="btn btn-primary"
										title="btn btn-primary" data-toggle="tooltip">
										<spring:message code="labels.search_btn${language }" />
									</button>
									<a href="note-list.html"> <input type="button" name="clear"
										data-toggle="tooltip" class="btn btn-outline-danger"
										value="<spring:message code="labels.reset_btn${language }"/>"></a>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><i class="fas fa-pencil-alt"
											style="color: blue; margin-right: 10px;"></i> <spring:message
												code="labels.supplier_menu${language }" /></label>
										<form:select path="supplierDTO.id" id="pinSupplier"
											class="js-example-responsive form-control">
											<form:option value="0">
												<spring:message
													code="labels.select_one_supplier${language }" />
											</form:option>
											<form:options items="${supplierList}" itemValue="id"
												itemLabel="name" />
										</form:select>
									</div>
								</div>
								<div class="col-md-6"></div>
							</div>
							<div class="table-responsive">
								<table
									class="display table nowrap table-striped table-hover zero-configuration"
									style="width: 100%">
									<thead>
										<tr>
											<th><spring:message code="labels.no_label${language }" /></th>
											<th><spring:message code="labels.order_btn${language }" /></th>
											<th><spring:message code="labels.edit_btn${language }" /></th>
											<th><spring:message code="labels.delete_btn${language }" /></th>
											<th><spring:message code="labels.item_label${language }" /></th>
											<th><spring:message
													code="labels.category_label${language }" /></th>
											<th><spring:message code="labels.size_label${language }" /></th>
											<th><spring:message
													code="labels.remaining_label${language }" /></th>
											<th><spring:message
													code="labels.noted_user_label${language }" /></th>
											<th><spring:message
													code="labels.noted_time_label${language }" /></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${noteList}" var="n" varStatus="i">
											<tr>
												<td>${i.index+1 }</td>
												<td><c:if
														test="${sessionScope.loginUser.userType==sessionScope.adminKey }">
														<a
															onclick="showSupplierListDialog('${n.id }','${n.itemName}','${n.size}','${n.color}','${n.manufactureCountry}','${n.brand}','${n.categoryDTO.id}','${n.description }')">
															<i class="fas fa-shopping-cart"></i>
														</a>
													</c:if></td>
												<td><a href="note.html?id=${n.id}"
													style="color: white;"><i class="fa fa-edit"></i></a></td>
												<td><a class="danger" onclick="deleteNote(${n.id})">
														<i class="fa fa-trash"></i>
												</a></td>
												<td>${n.itemName}</td>
												<td>${n.categoryDTO.name }</td>
												<td>${n.size }</td>
												<td>${n.remaining }</td>
												<td>${n.notedUser.name}</td>
												<td>${n.createdDate }</td>
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
</script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>