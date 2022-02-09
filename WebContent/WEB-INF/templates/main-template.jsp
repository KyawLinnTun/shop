<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/importTag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><spring:message code="labels.shop_name${language }"/></title>
<!-- Meta -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<%@ include file="../include/csstemplate.jsp"%>
</head>

<body>
	<input id="frmMessage" type="hidden" value="${ frmMessage }">
	<div id="modalBounceInDown" tabindex="-1" role="dialog"
		class="modal in"
		style="display: ${showAlertDisplay}; padding-right: 16px; background: rgba(0,0,0,.4);">
		<div class="modal-dialog">
			<div class="modal-content animated bounceInDown">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						onclick="closeAlertBox()">
						<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="text-center">
						<c:choose>
							<c:when test="${msgType == 'SUCCESS'}">
								<i class="fas fa-check-circle"></i>
								<h3 class="text-success">Success</h3>
							</c:when>
							<c:when test="${msgType == 'WARNING'}">
								<i class="fas fa-times-circle"></i>
								<h3 class="text-warning">Warning</h3>
							</c:when>
							<c:when test="${msgType == 'DANGER'}">
								<i class="fas fa-times-circle"></i>
								<h3 class="text-danger">Danger</h3>
							</c:when>
							<c:otherwise>
								<i class="fas fa-times-circle"></i>
								<h3 class="text-danger">Danger</h3>
							</c:otherwise>
						</c:choose>
						<p class="custom_error_hide" id="commonLabel">
							<spring:message code='labels.system_error${language }'/>
						</p>
						<c:if test="${message!=null and message!=''}">
							<p>
								<spring:message code="${message}${language}" />
							</p>
						</c:if>
						<div class="m-t-lg">
							<button data-toggle="tooltip" class="btn btn-outline-danger"
								data-dismiss="modal" type="button" onclick="closeAlertBox()">OK</button>
						</div>
					</div>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	<!-- confirm dialog -->
	<div id="warningModalAlert" tabindex="-1" role="dialog"
		class="modal in" style="background: rgba(0, 0, 0, .4);">
		<div class="modal-dialog">
			<div class="modal-content animated bounceInDown">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						onclick="closeWarningBox()">
						<span aria-hidden="true">×</span> <span class="sr-only">Close</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="text-center">
						<span class="text-warning icon icon-exclamation-triangle icon-5x"></span>
						<h3 class="text-warning title">Warning</h3>
						<p class="content"></p>
						<div class="m-t-lg">
							<button class="btn btn-warning confirm-button"
								data-dismiss="modal" type="button">Continue</button>
							<button class="btn btn-default" onclick="closeWarningBox()"
								type="button">Cancel</button>
						</div>
					</div>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	<!-- [ Pre-loader ] start -->
	<div class="loader-bg">
		<div class="loader-track">
			<div class="loader-fill"></div>
		</div>
	</div>
	<!-- [ Pre-loader ] End -->


	<!-- [ navigation menu ] start -->
	<tiles:insertAttribute name="left-menu"></tiles:insertAttribute>
	<!-- [ navigation menu ] end -->

	<!-- [ Header ] start -->
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<!-- [ Header ] end -->

	<!-- [ Main Content ] start -->
	<div class="pcoded-main-container" style="position: relative;top: 40px;">
		<div class="pcoded-wrapper">
			<div class="pcoded-content">
				<div class="pcoded-inner-content">
					<div class="main-body">
						<div class="page-wrapper">
							<tiles:insertAttribute name="body"></tiles:insertAttribute>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- [ Main Content ] end -->

	<%@ include file="../include/jstemplate.jsp"%>
</body>
<script type="text/javascript">
	function closeAlertBox() {
		var alertBox = document.getElementById("modalBounceInDown");
		alertBox.style.display = "none";
		$("#commonLabel").addClass('custom_error_hide');
	}
	function closeWarningBox() {
		var alertBox = document.getElementById("warningModalAlert");
		alertBox.style.display = "none";
	}
	function warningDialogShow(title, content, callback) {
		$("#warningModalAlert .title").text(title);
		$("#warningModalAlert .content").text(content);
		$("#warningModalAlert .confirm-button").click(function() {
			if (typeof callback == 'function') {
				callback();
			}
			closeWarningBox();
		});
		$("#warningModalAlert").css({
			"display" : "block"
		});
	}
</script>
</html>
