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
								<h3>Alta de Nuevo Usuario</h3>
								<form:form id="usuarioRegisterForm" modelAttribute="usuario" method="post" action="saveUsuario">
									<form:hidden path="id"  value="${usuarioObject.id}" />
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="nombre">Nombre</label>
												<form:input cssClass="form-control" id="nombre" type="text" path="nombre" value="${usuarioObject.nombre}" />
												<p class="help-block">Ingrese Nombre del nuevo Usuario</p>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="apellido">Apellido</label>
												<form:input cssClass="form-control" id="apellido" type="text" path="apellido" value="${usuarioObject.apellido}"/>
												<p class="help-block">Ingrese el Apellido del nuevo Usuario</p>	
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="documento">Documento</label>
												<form:input cssClass="form-control" id="documento" type="number" path="dni" value="${usuarioObject.dni}"/>
												<p class="help-block">Ingrese numero de documento del nuevo Usuario</p>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="password">Contraseña</label>
												<form:input cssClass="form-control" id="password" type="password" path="password" value="${usuarioObject.password}"/>
												<p class="help-block">Ingrese la contraseña del nuevo Usuario</p>	
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
									    	<label class="col-md-1 control-label">Rol de Usuario</label>
										    <div class="col-md-10">
											    <div class="radio radio-danger">
												    <label>
												    	<input type="radio" name="rol" value="administrador" checked>
												    	Administrador
									    			</label>
									    		</div>
									    		<div class="radio radio-danger">
									    			<label>
									    				<input type="radio" name="rol" value="responsable">
									    				Responsable de sede
									    			</label>
									    		</div>
									    	</div>
									    	<div class="col-md-1"></div>
									    </div>
									</div> 
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