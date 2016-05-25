<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<t:template>
	<jsp:attribute name="head">
		<script src="<c:url value="/resources/libs/bootstrap-validator/validator.js"/>"></script>
	</jsp:attribute>
	<jsp:body>
    	<div class="container-fluid">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="well">
						<div class="row">
							<div class="container-fluid">
								<h3><spring:message code="changePassword.head"/></h3>
								<form method="post" action="savePassword" data-toggle="validator" role="form">
									
									<div class="row">
										<div class="col-md-12">
											<div class="form-group label-floating">
												<label class="control-label" for="oldPassword"><spring:message code="changePassword.actual"/></label>
												<input id="oldPassword" name="oldPassword" class="form-control" type="password" required />
												<p class="help-block"><spring:message code="changePassword.actual.help"/></p>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="newPassword"><spring:message code="changePassword.nueva"/></label>
												<input id="newPassword" name="newPassword" class="form-control" type="password" required />
												<p class="help-block"><spring:message code="changePassword.nueva.help"/></p>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="repeatePassword"><spring:message code="changePassword.confirm"/></label>
												<input id="repeatePassword" class="form-control" type="password" data-match="#newPassword" data-match-error="La contraseña no es igual a la ingresada" required  />
												<p class="help-block with-errors"><spring:message code="changePassword.confirm.help"/></p>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="container-fluid">
										<input class="btn btn-block btn-danger btn-raised" type="submit" value="<spring:message code="form.submit"/>">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
	</jsp:body>
</t:template>