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
								<table id="grid-data-api" class="table table-condensed table-hover table-striped">
								    <thead>
								        <tr>
								            <th data-column-id="nombre" data-order="asc">Nombre</th>
								            <th data-column-id="domicilio">Domicilio</th>
								            <th data-column-id="telefono">Tel&eacute;fono</th>
								            <th data-column-id="email">Email</th>
								            <th data-column-id="link" data-formatter="link" data-sortable="false">Detalles</th>
								        </tr>
								    </thead>
								    <tbody>
								       	<c:forEach items="${sedeList}" var="sede">
					                    <tr>
											<td><c:out value="${sede.nombre}" /></td>
											<td><c:out value="${sede.domicilio}" /></td>
											<td><c:out value="${sede.telefono}" /></td>
											<td><c:out value="${sede.mail}" /></td>
										</tr>
					                    </c:forEach>
								    </tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="<c:url value="/resources/libs/jquery.bootgrid/jquery.bootgrid.js" />"></script>
		<script>
		$("#grid-data-api").bootgrid({
		    formatters: {
		        "link": function(column, row)
		        {
		            return "<a class='btn btn-raised btn-default btn-sm withoutMargin'>"
					+ "       <span class='glyphicon glyphicon glyphicon-search' aria-hidden='true'></span> Ver"
					+ "     </a>";
		        }
		    }
		});
		</script>
	</jsp:body>
</t:template>