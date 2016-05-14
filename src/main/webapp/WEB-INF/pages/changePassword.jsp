<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
								<h3>Cambiar Contraseña</h3>
								<form method="post" action="savePassword" data-toggle="validator" role="form">
									
									<div class="row">
										<div class="col-md-12">
											<div class="form-group label-floating">
												<label class="control-label" for="oldPassword">Contraseña actual</label>
												<input id="oldPassword" name="oldPassword" class="form-control" type="password" required />
												<p class="help-block">Ingrese la contraseña actual que utiliza para acceder</p>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="newPassword">Nueva contraseña</label>
												<input id="newPassword" name="newPassword" class="form-control" type="password" required />
												<p class="help-block">Ingrese la nueva contraseña</p>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="repeatePassword">Confirmar contraseña</label>
												<input id="repeatePassword" class="form-control" type="password" data-match="#newPassword" data-match-error="La contraseña no es igual a la ingresada" required  />
												<p class="help-block with-errors">Ingrese nuevamente la nueva contraseña</p>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="container-fluid">
										<input class="btn btn-block btn-danger btn-raised" type="submit" value="Registrar">
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