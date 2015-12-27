<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:template>
	<jsp:attribute name="head">
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/libs/jquery.bootgrid/jquery.bootgrid.min.css" />">
	</jsp:attribute>

	<jsp:body>
    	<div class="container-fluid">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="well">
						<div class="row">
							<div class="container-fluid">
								<h3>Listado de Sedes</h3>
								<table id="grid-data-api" class="table table-condensed table-hover table-striped" data-toggle="bootgrid">
								    <thead>
								        <tr>
								            <th data-column-id="nombre" data-order="asc">Nombre</th>
								            <th data-column-id="domicilio">Domicilio</th>
								            <th data-column-id="telefono">Tel&eacute;fono</th>
								            <th data-column-id="email">Email</th>
								        </tr>
								    </thead>
								    <tbody>
								        <tr>
								            <td>Bosque</td>
								            <td>Calle 50 s/n</td>
								            <td>42583247</td>
								            <td>comedor_bosque@unlp.edu.ar</td>
								        </tr>
								    </tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="<c:url value="/resources/libs/jquery.bootgrid/jquery.bootgrid.js" />"></script>
	</jsp:body>
</t:template>