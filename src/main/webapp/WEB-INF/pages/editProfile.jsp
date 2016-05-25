<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<t:template>
	<jsp:body>
    	<div class="container-fluid">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="well">
						<div class="row">
							<div class="container-fluid">
								<h3><spring:message code="editProfile.head"/></h3>
								<form:form id="usuarioRegisterForm" modelAttribute="usuario" method="post" action="saveProfile" autocomplete="off">
									
									<form:hidden path="id" value="${usuarioObject.id}" />
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="nombre"><spring:message code="table.nombre"/></label>
												<form:input cssClass="form-control" id="nombre" type="text" path="nombre" value="${usuarioObject.nombre}" required="required"/>
												<p class="help-block"><spring:message code="editProfile.helpBlock.nombre"/></p>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="apellido"><spring:message code="table.apellido"/></label>
												<form:input cssClass="form-control" id="apellido" type="text" path="apellido" value="${usuarioObject.apellido}" required="required"/>
												<p class="help-block"><spring:message code="editProfile.helpBlock.apellido"/></p>	
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group label-floating">
												<label class="control-label" for="documento"><spring:message code="table.documento"/></label>
												<form:input cssClass="form-control" id="documento" type="number" path="dni" value="${usuarioObject.dni}" required="required"/>
												<p class="help-block"><spring:message code="editProfile.helpBlock.documento"/></p>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="container-fluid">
										<input class="btn btn-block btn-danger btn-raised" type="submit" value="<spring:message code="form.submit"/>">
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
	</jsp:body>
</t:template>