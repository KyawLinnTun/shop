<%@ include file="../../include/importTag.jsp"%>
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
						<div class="table-responsive">
							<table
								class="display table nowrap table-striped table-hover zero-configuration"
								style="width: 100%">
								<thead>
									<tr>
										<th><spring:message code="labels.no_label${language}" /></th>
										<th><spring:message code="labels.edit_btn${language}" /></th>
										<th><spring:message
												code="labels.supplier_menu${language}" /></th>
										<th><spring:message
												code="labels.total_price_label${language}" /></th>
										<th><spring:message
												code="labels.items_count_label${language}" /></th>
										<th><spring:message
												code="labels.quantities_label${language}" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${upcomingOrders}" var="u" varStatus="i">
										<tr>
											<td>${i.index+1 }</td>
											<td><a href="#" style="color: white;"><i
													class="fa fa-edit"></i></a></td>
											<td>${u.supplierName }</td>
											<td><fmt:formatNumber pattern="###,###.00">${u.totalPrice }</fmt:formatNumber></td>
											<td>${u.count }</td>
											<td>${u.quantityCount }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	var contextPath = '${pageContext.request.contextPath}/';
</script>