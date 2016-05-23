<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:template>
	<jsp:attribute name="head">
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/libs/jquery.bootgrid/jquery.bootgrid.min.css" />">
		<script src="<c:url value="/resources/libs/bootbox/bootbox.min.js"/>"></script>
	</jsp:attribute>

	<jsp:body>
    	<div class="container-fluid">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="well">
						<div class="row">
							<div class="container-fluid">
								<div class="row">
									<div class="col-md-6">
										<h3>Listado de Usuarios</h3>
									</div>
									<div class="col-md-6">
										<a href="createUsuario" class="btn btn-raised btn-danger btn-sm withoutMargin marginTop pull-right animsition-link">Agregar Usuario</a>
									</div>
								</div>
								<table id="grid-data-api" class="table table-condensed table-hover table-striped">
								    <thead>
								        <tr>
								        	<th data-column-id="id" data-visible="false" data-visible-in-selection="false">Id</th>
								            <th data-column-id="apellido" data-order="asc">Apellido</th>
								            <th data-column-id="nombre">Nombre</th>
								            <th data-column-id="documento">Documento</th>
								            <th data-column-id="link" data-formatter="link" data-sortable="false">Acciones</th>
								        </tr>
								    </thead>
								    <tbody>
					                    <c:forEach items="${comenList}" var="comen">
					                    <tr>
					                    	<td><c:out value="${comen.id}" /></td>
											<td><c:out value="${comen.apellido}" /></td>
											<td><c:out value="${comen.nombre}" /></td>
											<td><c:out value="${comen.dni}" /></td>
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
		<script src="<c:url value="/resources/js/borrar.js" />"></script>
		<script>
		var grid=$("#grid-data-api").bootgrid({
				    formatters: {
				        "link": function(column, row)
				        {
				            return "<button class='btn btn-raised btn-default btn-sm withoutMargin command'"
				            + "data-row-id='"+ row.id + "'"
				            + "data-row-apellido='"+ row.apellido + "'"
				            + "data-row-nombre='"+ row.nombre + "'"
				            + "data-row-documento='"+ row.documento + "'"
            
				            +">"
							+ "       <span class='glyphicon glyphicon glyphicon-search' aria-hidden='true'></span> Ver"
							+ "     </button>"
							+ "<a href='../creditCharge?idComensal="+ row.id +"'>"
							+"<button class='btn btn-raised btn-default btn-sm withoutMargin command'>"
							+ "<span class='glyphicon glyphicon glyphicon-pencil' aria-hidden='true'></span> Cargar Saldo"
							+ "</button>"
							+ "</a>";
							
				        }
				    }
				}).on("loaded.rs.jquery.bootgrid", function(){
				        /* Executes after data is loaded and rendered */
				        grid.find(".command").on("click", function(e){
				        	
				        	bootbox.dialog({
				        		  title: "Detalles del usuario",
				        		  message:  "<div class='well'><p><b>Nombre: </b>"+$(this).data("row-nombre").toString()+"</p>"+
				        		  			"<p><b>Apellido: </b>"+$(this).data("row-apellido").toString()+"</p>"+
				        		  			"<p><b>Documento: </b>"+$(this).data("row-documento").toString()+"</p>"+
				        		  			"</div>"
				        	});
				        })
				});
		</script>
	</jsp:body>
</t:template>