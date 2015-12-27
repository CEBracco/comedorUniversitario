<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

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
								<form action="" method="post">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="nombre">Nombre</label>
												<input class="form-control" id="nombre" type="text">
												<p class="help-block">Ingrese Nombre del nuevo Usuario</p>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="apellido">Apellido</label>
												<input class="form-control" id="apellido" type="text">
												<p class="help-block">Ingrese el Apellido del nuevo Usuario</p>	
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="documento">Documento</label>
												<input class="form-control" id="documento" type="number">
												<p class="help-block">Ingrese numero de documento del nuevo Usuario</p>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="password">Contraseña</label>
												<input class="form-control" id="password" type="password">
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