<%@ include file="../../include/importTag.jsp"%>
<div class="auth-wrapper">
	<div class="auth-content">
		<form:form method="POST" modelAttribute="userDto"
			id="validation-form123" action="login.html">
			<div class="card">
				<div class="card-body text-center">
					<div class="mb-4">
						<i class="feather icon-unlock auth-icon"></i>
					</div>
					<c:if test="${ not empty errorMsg }">
						<div class="alert alert-danger alert-dismissible mr-3"
							style="margin-top: 7px; color: white; background-color: red;border-radius: 5px;width: 310px;">
							<button class="close" aria-hidden="true" data-dismiss="alert"
								type="button">x</button>
								<spring:message code="${errorMsg}${language}"/>
						</div>
					</c:if>
					<h3 class="mb-4">Login</h3>
					<div class="form-group input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroupPrepend"> <i
								class="feather icon-user"></i>
							</span>
						</div>
						<form:input path="userName" type="text"
							class="form-control bg-white" required="required"
							name="validation-required" placeholder="Username"
							autocomplete="off" />
					</div>
					<div class="form-group input-group mb-4">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroupPrepend"> <i
								class="feather icon-lock"></i>
							</span>
						</div>
						<form:input path="password" type="password"
							class="form-control bg-white" required="required"
							name="validation-required" placeholder="Password"
							autocomplete="off" />
					</div>
					<button class="btn btn-primary shadow-2 mb-4" type="submit">Login</button>
				</div>
			</div>
		</form:form>
	</div>
</div>

