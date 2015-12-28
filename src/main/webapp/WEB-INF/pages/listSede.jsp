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
		var grid=$("#grid-data-api").bootgrid({
				    formatters: {
				        "link": function(column, row)
				        {
				            return "<button class='btn btn-raised btn-default btn-sm withoutMargin command'"
				            + "data-row-nombre='"+ row.nombre + "'"
				            + "data-row-domicilio='"+ row.domicilio + "'"
				            + "data-row-telefono='"+ row.telefono + "'"
				            + "data-row-email='"+ row.email + "'"
            
				            +">"
							+ "       <span class='glyphicon glyphicon glyphicon-search' aria-hidden='true'></span> Ver"
							+ "     </button>";
				        }
				    }
				}).on("loaded.rs.jquery.bootgrid", function(){
				        /* Executes after data is loaded and rendered */
				        grid.find(".command").on("click", function(e){
				        	
				        	bootbox.dialog({
				        		  title: "Detalles de la sede",
				        		  message:  "<div class='well'><p><b>Nombre: </b>"+$(this).data("row-nombre").toString()+"</p>"+
				        		  			"<p><b>Domicilio: </b>"+$(this).data("row-domicilio").toString()+"</p>"+
				        		  			"<p><b>Tel&eacute;fono: </b>"+$(this).data("row-telefono").toString()+"</p>"+
				        		  			"<p><b>Email: </b>"+$(this).data("row-email").toString()+"</p></div>"
				        	});
				        })
				});
		</script>
	</jsp:body>
</t:template>