<%@ include file="../include/importTag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><spring:message code="labels.shop_name${language }"/></title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="CodedThemes" />
<%@ include file="../include/csstemplate.jsp"%>
</head>
<body>

	<%--  <input id="frmMessage" type="hidden" value="${ frmMessage }">  --%>
	
	<tiles:insertAttribute name="body" />

	<%@ include file="../include/jstemplate.jsp"%>
</body>
</html>
