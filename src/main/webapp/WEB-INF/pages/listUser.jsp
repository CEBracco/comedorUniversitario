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
								<h3>Listado de Usuarios</h3>
								<table id="grid-data-api" class="table table-condensed table-hover table-striped" data-toggle="bootgrid">
								    <thead>
								        <tr>
								            <th data-column-id="apellido" data-order="asc">Apellido</th>
								            <th data-column-id="nombre">Nombre</th>
								            <th data-column-id="documento">Documento</th>
								        </tr>
								    </thead>
								    <tbody>
								        <tr>
								            <td>Bracco</td>
								            <td>Christian</td>
								            <td>38156451</td>
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