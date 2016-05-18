<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
								    	<h3>Editar usuario</h3>
								    </c:when>
								    <c:otherwise>
										<h3>Alta de nuevo usuario</h3>
								    </c:otherwise>	
								</c:choose>
								<form:form id="usuarioRegisterForm" modelAttribute="usuario" method="post" action="saveUsuario" autocomplete="off">
									<c:choose>
									    <c:when test="${empty usuarioObject.id}">
									    	<form:hidden path="id" value="0" />
									    </c:when>
									    <c:otherwise>
											<form:hidden path="id" value="${usuarioObject.id}" />
									    </c:otherwise>
									</c:choose>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="nombre">Nombre</label>
												<form:input cssClass="form-control" id="nombre" type="text" path="nombre" value="${usuarioObject.nombre}" required="required"/>
												<p class="help-block">Ingrese Nombre del nuevo Usuario</p>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="apellido">Apellido</label>
												<form:input cssClass="form-control" id="apellido" type="text" path="apellido" value="${usuarioObject.apellido}" required="required"/>
												<p class="help-block">Ingrese el Apellido del nuevo Usuario</p>	
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="documento">Documento</label>
												<form:input cssClass="form-control" id="documento" type="number" path="dni" value="${usuarioObject.dni}" required="required"/>
												<p class="help-block">Ingrese numero de documento del nuevo Usuario</p>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="password">Contraseña</label>
												<form:input cssClass="form-control" id="password" type="password" path="password" value="${usuarioObject.password}" required="required"/>
												<p class="help-block">Ingrese la contraseña del nuevo Usuario</p>	
											</div>
										</div>
									</div>
									<c:if test="${not edit}">
									<div class="row">
										<div class="form-group">
									    	<label for="rol" class="col-md-1 control-label">Rol de Usuario</label>
										    <div class="col-md-11">
											    <form:select cssClass="form-control" path="rol" required="required">
											    	<c:choose>
													    <c:when test="${usuarioObjectRole == 'Administrador'}">
													    	<form:option value="admin" label="Administrador" selected="true" />
				   											<form:option value="respo" label="Responsable" />
													    </c:when>
													    <c:otherwise>
				   											<form:option value="admin" label="Administrador"/>
				   											<form:option value="respo" label="Responsable" selected="true" />
													    </c:otherwise>	
													</c:choose>
												</form:select>
									    		</div>
									    	</div>
									    	<div class="col-md-1"></div>
									</div>
									</c:if>
									
									<div class="row">
										<div class="container-fluid">
										<input class="btn btn-block btn-danger btn-raised" type="submit" value="Registrar">
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