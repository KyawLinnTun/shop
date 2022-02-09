<style>
.control-panel-mailbox {
	position: relative;
	display: inline-block;
	height: 25px;
	width: 25px;
	border: 0;
}
.control-panel-inbox2 {
	position:relative;
	background:#f23f21;
	color:#ffffff;
	padding:0px 1px;
	font-size:12px;
	font-weight:bold;
	z-index:1;
	left:-8px;
	border-radius:1px;
	box-shadow: 0 1px 2px #222;
	top:-5px;
}
</style>
<header class="navbar pcoded-header navbar-expand-lg navbar-light" style="position: fixed;z-index: 1;background: inherit;">
	<div class="m-header">
		<a class="mobile-menu" id="mobile-collapse1" href="#!"><span></span></a>
		<a href="#" class="b-brand">
			<div class="b-bg">
				<i class="feather icon-trending-up"></i>
			</div> <span class="b-title">U Myint Aung</span>
		</a>
	</div>
	<a class="mobile-menu" id="mobile-header" href="#!"> <i
		class="feather icon-more-horizontal"></i>
	</a>
	<div class="collapse navbar-collapse">
		<ul class="navbar-nav mr-auto">
			<li><a href="#!" class="full-screen" style="margin-right: 20px;"
				onclick="javascript:toggleFullScreen()"><i
					class="feather icon-maximize"></i></a> <a href="?language=en"
				style="color: !important;">English - <img
					src="resources/images/flag/english.svg" class="lang-flag me-1"></a>
				| <a href="?language=mm" style="color: !important;">Myanmar - <img
					src="resources/images/flag/myanmar.svg" class="lang-flag me-1"></a>
			</li>
		</ul>
		<ul class="navbar-nav ml-auto">
			<a href="upcoming-orders.html">
				<img src="resources/images/noti.png" class="control-panel-mailbox">
				<label class="control-panel-inbox2">${upcomingSuppliersCount }</label>
			</a>
			<li>
				<div class="dropdown drp-user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						${sessionScope.loginUser.name}&nbsp; </a>
					<div class="dropdown-menu dropdown-menu-right profile-notification">
						<div class="pro-head">
							<span>${sessionScope.loginUser.name}</span> <a href="logout.html"
								class="dud-logout" title="Logout"> <i
								class="feather icon-log-out"></i>
							</a>
						</div>
						<ul class="pro-body">
							<li><a href="logout.html" class="dropdown-item"><i
									class="feather icon-log-out"></i> Log Out</a></li>
						</ul>
					</div>
				</div>
			</li>
		</ul>
	</div>
</header>

<!-- [ chat user list ] start -->
<section class="header-user-list">
	<div class="h-list-body">
		<div class="main-friend-cont scroll-div"></div>
	</div>
</section>
<!-- [ chat user list ] end -->

<!-- [ chat message ] start -->
<section class="header-chat">
	<div class="h-list-header"></div>
	<div class="h-list-body">
		<div class="main-chat-cont scroll-div"></div>
	</div>
	<div class="h-list-footer"></div>
</section>
<!-- [ chat message ] end -->
