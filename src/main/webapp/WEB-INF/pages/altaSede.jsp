<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:template>
	<jsp:body>
    	<div class="container-fluid">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="well">
						<div class="row">
							<div class="container-fluid">
								<c:choose>
								    <c:when test="${edit}">
								    	<h3>Editar sede</h3>
								    </c:when>
								    <c:otherwise>
										<h3>Alta de nueva sede</h3>
								    </c:otherwise>	
								</c:choose>
								
								<form:form id="sedeRegisterForm" modelAttribute="sede" method="post" action="saveSede" autocomplete="off">
									<c:choose>
									    <c:when test="${empty sedeObject.id}">
									    	<form:hidden path="id" value="0" />
									    </c:when>
									    <c:otherwise>
											<form:hidden path="id" value="${sedeObject.id}" />
									    </c:otherwise>
									</c:choose>
									
									<div class="form-group label-floating">
										<label class="control-label" for="nombre">Nombre</label>
										<form:input type="text" cssClass="form-control" path="nombre" value="${sedeObject.nombre}" required="required"/>
										<p class="help-block">Ingrese Nombre de la nueva sede</p>
									</div>
									<div class="form-group label-floating">
										<label class="control-label" for="domicilio">Domicilio</label>
										<form:input type="text" cssClass="form-control" path="domicilio" value="${sedeObject.domicilio}" required="required"/>
										<p class="help-block">Ingrese el Domicilio de la nueva sede</p>	
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="telefono">Tel&eacute;fono</label>
												<form:input type="number" cssClass="form-control" path="telefono" value="${sedeObject.telefono}" required="required"/>
												<p class="help-block">Ingrese el Tel&eacute;fono de la nueva sede</p>	
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="email">Email</label>
												<form:input type="email" cssClass="form-control" path="mail" value="${sedeObject.mail}" required="required"/>
												<p class="help-block">Ingrese el Email de la nueva sede</p>	
											</div>
										</div>
									</div>
									<div class="row">
										<div class="container-fluid">
										<input class="btn btn-block btn-danger btn-raised" type="submit" value="Registrar" id="saveSede">
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