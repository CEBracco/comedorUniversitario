<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
										<h3>Listado de Tickets sede <b>${sedeObject.nombre}</b></h3>
									</div>
									
								</div>
								<table id="grid-data-api" class="table table-condensed table-hover table-striped">
								    <thead>
								        <tr>
								        	<th data-column-id="id" data-visible="false" data-visible-in-selection="false">Id</th>
								        	<th data-column-id="dni" data-order="asc">Dni comensal </th>
								            <th data-column-id="nombre" data-order="asc">Nombre comensal </th>
								            
								            <th data-column-id="reservas" data-order="asc">Cant reserva </th>
								           
								            <th data-column-id="monto">Monto </th>
								     		
								        </tr>
								    </thead>
								    <tbody>

								       	<c:forEach items="${ticketList}" var="ticket">
					                    <tr>
					                    	<td><c:out value="${ticket.id}" /></td>
					                    	<td><c:out value="${ticket.comensal.dni}" /></td>
											<td><c:out value="${ticket.comensal.nombre}" /></td>
											
											<td><c:out value="${fn:length(ticket.reservas)}" /></td>
											
											<td><c:out value="$ ${ticket.monto}" /></td>
											
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
				            + "data-row-nombre='"+ row.nombre + "'"
				            + "data-row-dni='"+ row.domicilio + "'"
				            + "data-row-='"+ row.telefono + "'"
				            + "data-row-email='"+ row.email + "'"
            
				            +">"
							+ "       <span class='glyphicon glyphicon glyphicon-search' aria-hidden='true'></span> Ver"
							+ "     </button>"
							;
				        }
				    }
				}).on("loaded.rs.jquery.bootgrid", function(){
				        /* Executes after data is loaded and rendered */
				        grid.find(".command").on("click", function(e){
				        	
				        	bootbox.dialog({
				        		  title: "Detalles de la sede",
				        		  message:  "<div class='well'><p><b>Nombre: </b>"+$(this).data("row-nombre").toString()+"</p></div>"
				        	});
				        })
				});
		</script>
	</jsp:body>
</t:template>