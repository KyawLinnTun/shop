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
	src="<%=request.getContextPath()%>/resources/js/item/item-list.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/common.css">
<style>
#select2-category-container {
	color: #adb7be;
	padding-top: 5px;
	padding-left: 25px;
}
</style>
<div class="main-body">
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
						<form:form modelAttribute="itemDTO" method="POST"
							action="item-list.html" enctype="multipart/form-data">
							<input type="hidden" id="language" value="${language }">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label><spring:message
												code="labels.item_label${language }" /></label> <input type="text"
											id="item" name="name" value="${itemDTO.name }"
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
										<spring:message code="labels.search_btn${language }" />
									</button>
									<a href="item-list.html"> <input type="button" name="clear"
										data-toggle="tooltip" class="btn btn-outline-danger"
										value="<spring:message code="labels.reset_btn${language }"/>"></a>
								</div>
							</div>
							<div class="table-responsive">
								<table
									class="display table nowrap table-striped table-hover zero-configuration"
									style="width: 100%">
									<thead>
										<tr>
											<th><spring:message code="labels.no_label${language }" /></th>
											<th><spring:message code="labels.edit_btn${language }" /></th>
											<th><spring:message code="labels.item_label${language }" /></th>
											<th><spring:message code="labels.image_label${language}" /></th>
											<th><spring:message
													code="labels.category_label${language }" /></th>
											<th><spring:message
													code="labels.buying_price_label${language }" /></th>
											<th><spring:message
													code="labels.sell_price_label${language }" /></th>
											<th><spring:message
													code="labels.created_user_label${language }" /></th>
											<th><spring:message
													code="labels.updated_user_label${language }" /></th>
											<th><spring:message
													code="labels.status_label${language}" /></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${itemList}" var="n" varStatus="i">
											<tr>
												<td>${i.index+1 }</td>
												<td><a href="item.html?id=${n.id}"
													style="color: white;"><i class="fa fa-edit"></i></a></td>
												</a></td>
												<td>${n.name}</td>
												<td class="col-xs-1"><c:if
														test="${n.image ne null && n.image ne ''}">
														<a href="${imagePath}${n.image}" data-lightbox="1"
															data-title=""> <img src="${imagePath}${n.image}"
															alt="" style="width: 30px; height: 25px;">
														</a>
													</c:if></td>
												<td>${n.categoryDTO.name }</td>
												<td><fmt:formatNumber pattern="###,###.00">${n.buyPrice }</fmt:formatNumber></td>
												<td><fmt:formatNumber pattern="###,###.00">${n.price }</fmt:formatNumber></td>
												<td>${n.createdUser.name}</td>
												<td>${n.updatedUser.name }</td>
												<td><c:if test="${n.status eq 1}">
														<button type="button" class="btn btn-rounded btn-primary"
															style="padding: 0px 20px;">Active</button>
													</c:if> <c:if test="${n.status eq 2 || n.status eq null}">
														<button type="button" class="btn btn-rounded btn-danger"
															style="padding: 0px 20px;">Inactive</button>
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
</script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>