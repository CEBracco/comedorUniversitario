<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
	<jsp:attribute name="head">
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/profile.css"/>">
		<script src="<c:url value="/resources/js/profile.js"/>"></script>
		
		<c:if test="${not empty user.foto}">
		<style>
			.user-image{
				background: url(<c:url value="/profilePhoto?id=${user.id}"/>) center;
				background-size: cover;			
			}
			.profile-header .panel-heading {
				background: url(<c:url value="/profilePhoto?id=${user.id}"/>) no-repeat center;
				background-size: cover;
			}
		</style>
		</c:if>		
	</jsp:attribute>
	<jsp:body>
    	<div class="container-fluid
		<c:if test="${empty user.foto}">pull-down</c:if>
		">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					
					<div class="panel profile-header" >
						<div class="panel-heading <c:if test="${empty user.foto}">not-bg</c:if>"></div>
						
						
						<div class="row text-center">
								<div class="user-image">
									<div class="fade-mini">
										<h4><a id="upload_link" href="#">Cambiar Imagen</a></h4>
									</div>
								</div>
								<h4><b>${user.nombre} ${user.apellido}</b></h4>
								<h5>${role}</h5>
								
						</div>
						<div class="panel-body">
							<form id="formPhoto" action="savePhoto" method="post" enctype="multipart/form-data" accept="image/*">
								<input id="upload" type="file" name="file">
							</form>
						</div>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
			
			<div class="row equal">
				<div class="col-md-1"></div>
				<div class="col-md-5">
					<div class="panel">
						<div class="panel-heading">
							<div class="row">
								<div class="col-md-8 col-sm-9 col-xs-5">
									<h3>Mis Datos</h3>
								</div>
								<div class="col-md-4 col-sm-3 col-xs-7">
									<a href="editProfile" class="btn btn-raised btn-danger btn-sm withoutMargin marginTop animsition-link"><span class='glyphicon glyphicon glyphicon-pencil'></span>  Editar</a>
								</div>
							</div>
						</div>
						<div class="panel-body">
								<div class="row">
									<div class="col-xs-6"><p><b>Nombre</b></p></div>
									<div class="col-xs-6"><p>${user.nombre}</p></div>
								</div>
								<div class="row">
									<div class="col-xs-6"><p><b>Apellido</b></p></div>
									<div class="col-xs-6"><p>${user.apellido}</p></div>
								</div>
								<div class="row">
									<div class="col-xs-6"><p><b>Documento</b></p></div>
									<div class="col-xs-6"><p>${user.dni}</p></div>
								</div>
						</div>
					</div>
				</div>
				<div class="col-md-5">
					<div class="panel">
						<div class="panel-heading"><h3>Mi Contraseña</h3></div>
						<div class="panel-body">
							<div class="row text-center">
									<a href="changePassword" class="btn btn-raised btn-danger animsition-link">Cambiar Contraseña</a>
							</div>
							<br>
						</div>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
	</jsp:body>
</t:template>