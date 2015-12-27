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
								<h3>Alta de Nueva sede</h3>
								
								<form:form id="sedeRegisterForm" modelAttribute="sede" method="post" action="saveSede">
									<form:hidden path="id"  value="0" />
									
									<div class="form-group label-floating">
										<label class="control-label" for="nombre">Nombre</label>
										<form:input type="text" cssClass="form-control" path="nombre" value="${sedeObject.nombre}"/>
										<p class="help-block">Ingrese Nombre de la nueva sede</p>
									</div>
									<div class="form-group label-floating">
										<label class="control-label" for="domicilio">Domicilio</label>
										<form:input type="text" cssClass="form-control" path="domicilio" value="${sedeObject.domicilio}"/>
										<p class="help-block">Ingrese el Domicilio de la nueva sede</p>	
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="telefono">Tel&eacute;fono</label>
												<form:input type="number" cssClass="form-control" path="telefono" value="${sedeObject.telefono}"/>
												<p class="help-block">Ingrese el Tel&eacute;fono de la nueva sede</p>	
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="email">Email</label>
												<form:input type="email" cssClass="form-control" path="mail" value="${sedeObject.mail}"/>
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
								
								
								
								
								
								
								
								<!--<form action="" method="post">
									<div class="form-group label-floating">
										<label class="control-label" for="nombre">Nombre</label>
										<input class="form-control" id="nombre" type="text">
										<p class="help-block">Ingrese Nombre de la nueva sede</p>
									</div>
									<div class="form-group label-floating">
										<label class="control-label" for="domicilio">Domicilio</label>
										<input class="form-control" id="domicilio" type="text">
										<p class="help-block">Ingrese el Domicilio de la nueva sede</p>	
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="telefono">Tel&eacute;fono</label>
												<input class="form-control" id="telefono" type="number">
												<p class="help-block">Ingrese el Tel&eacute;fono de la nueva sede</p>	
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="email">Email</label>
												<input class="form-control" id="email" type="email">
												<p class="help-block">Ingrese el Email de la nueva sede</p>	
											</div>
										</div>
									</div>
									<div class="row">
										<div class="container-fluid">
										<input class="btn btn-block btn-danger btn-raised" type="submit" value="Registrar">
										</div>
									</div>
								</form>-->
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
	</jsp:body>
</t:template>