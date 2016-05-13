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
								<h3>Cambiar Contraseña</h3>
								<form method="post" action="savePassword">
									
									<div class="row">
										<div class="col-md-12">
											<div class="form-group label-floating">
												<label class="control-label" for="oldPassword">Contraseña actual</label>
												<input id="oldPassword" name="oldPassword" class="form-control" type="password" required="required"/>
												<p class="help-block">Ingrese la contraseña actual que utiliza para acceder</p>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="newPassword">Nueva contraseña</label>
												<input id="newPassword" name="newPassword" class="form-control" type="password" required="required"/>
												<p class="help-block">Ingrese la nueva contraseña</p>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="repeatePassword">Repetir contraseña</label>
												<input id="repeatePassword" class="form-control" type="password" required="required"/>
												<p class="help-block">Ingrese nuevamente la nueva contraseña</p>
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