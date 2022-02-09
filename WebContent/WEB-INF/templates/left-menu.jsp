<%@ include file="../include/importTag.jsp"%>
<nav class="pcoded-navbar navbar-dark brand-dark">
	<div class="navbar-wrapper">
		<div class="navbar-brand header-logo">
			<a href="#" class="b-brand">
				<div class="b-bg">
					<i class="fas fa-home"></i>
				</div> <span class="b-title"><spring:message code="labels.shop_name${language }"/></span>
			</a> <a class="mobile-menu" id="mobile-collapse" href="#!"><span></span></a>
		</div>
		<div class="navbar-content scroll-div">
			<ul class="nav pcoded-inner-navbar">
				<%-- <c:choose>

					<c:when test="${sessionScope.loginUser.userRoleName != 'Admin'}"> --%>
				<%-- <c:when test="${sessionScope.loginUser.userRoleName == 'Admin'}"> --%>
				<li
					class="nav-item pcoded-hasmenu ${ activePage == 1 ? 'pcoded-hasmenu active' :  'pcoded-hasmenu' }">
					<a href="#!" class="nav-link"> <span class="pcoded-micon"><i
							class="fas fa-th-large"></i></span> <span class="pcoded-mtext">Dashboard</span>
				</a>
					<ul class="pcoded-submenu">
						<li class="${pageTitle == 'Dashboard' ? 'active' :  '' }"><a
							href="#" class="">Dashboard</a></li>
					</ul>
				</li>
				<li
					class="nav-item pcoded-hasmenu ${ activePage == 2 ? 'pcoded-hasmenu active' :  'pcoded-hasmenu' }" style="${loginUser.userType==sessionScope.adminKey?'':'display:none'}">
					<a href="#!" class="nav-link"> <span class="pcoded-micon"><i
							class="fas fa-user"></i></span> <span class="pcoded-mtext"><spring:message code="labels.user_menu${language }"/></span>
				</a>

					<ul class="pcoded-submenu">
						<li class="${pageTitle} == <spring:message code="labels.user_create_menu${language }"/> ? 'active' :  ''"><a
							href="#" class=""><spring:message code="labels.user_create_menu${language }"/></a></li>
						<li class="${pageTitle} == <spring:message code="labels.user_list_menu${language }"/> ? 'active' :  ''"><a
							href="#" class=""><spring:message code="labels.user_list_menu${language }"/></a></li>
					</ul>
				</li>
				<li
					class="nav-item pcoded-hasmenu ${ activePage == 3 ? 'pcoded-hasmenu active' :  'pcoded-hasmenu' }" style="${loginUser.userType==sessionScope.adminKey?'':'display:none'}">
					<a href="#!" class="nav-link"> <span class="pcoded-micon"><i
							class="fas fa-user-tie"></i></span> <span class="pcoded-mtext"><spring:message code="labels.supplier_menu${language }"/></span>
				</a>

					<ul class="pcoded-submenu">
						<li class="${pageTitle} == <spring:message code="labels.supplier_create_menu${language }"/> ? 'active' :  ''"><a
							href="supplier.html" class=""><spring:message code="labels.supplier_create_menu${language }"/></a></li>
						<li class="${pageTitle} == <spring:message code="labels.supplier_list_menu${language }"/> ? 'active' :  ''"><a
							href="supplier-list.html" class=""><spring:message code="labels.supplier_list_menu${language }"/></a></li>
					</ul>
				</li>
				<li
					class="nav-item pcoded-hasmenu ${ activePage == 4 ? 'pcoded-hasmenu active' :  'pcoded-hasmenu' }">
					<a href="#!" class="nav-link"> <span class="pcoded-micon"><i
							class="feather icon-clipboard"></i></span> <span class="pcoded-mtext"><spring:message code="labels.note_menu${language }"/></span>
				</a>

					<ul class="pcoded-submenu">
						<li class="${pageTitle} == <spring:message code="labels.note_create_menu${language }"/> ? 'active' :  ''"><a
							href="note.html" class=""><spring:message code="labels.note_create_menu${language }"/></a></li>
						<li class="${pageTitle} == <spring:message code="labels.note_list_menu${language }"/> ? 'active' :  ''"><a
							href="note-list.html" class=""><spring:message code="labels.note_list_menu${language }"/></a></li>
					</ul>
				</li>
				<li
					class="nav-item pcoded-hasmenu ${ activePage == 6 ? 'pcoded-hasmenu active' :  'pcoded-hasmenu' }">
					<a href="#!" class="nav-link"> <span class="pcoded-micon"><i
							class="fas fa-warehouse"></i></span> <span class="pcoded-mtext"><spring:message code="labels.item_menu${language }"/></span>
				</a>

					<ul class="pcoded-submenu">
						<li class="${pageTitle} == <spring:message code="labels.item_create_menu${language }"/> ? 'active' :  ''"><a
							href="item.html" class=""><spring:message code="labels.item_create_menu${language }"/></a></li>
						<li class="${pageTitle} == <spring:message code="labels.item_list_menu${language }"/> ? 'active' :  ''"><a
							href="item-list.html" class=""><spring:message code="labels.item_list_menu${language }"/></a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>
