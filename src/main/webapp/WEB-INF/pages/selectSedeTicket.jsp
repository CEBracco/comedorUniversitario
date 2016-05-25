<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
									<div class="col-md-12">
										<h3><spring:message code="selectSedeTicket.head"/></h3>
										<h4><spring:message code="selectSedeTicket.subtitulo"/></h4>
									</div>
								</div>
								<table id="grid-data-api" class="table table-condensed table-hover table-striped">
								    <thead>
								        <tr>
								        	<th data-column-id="id" data-visible="false" data-visible-in-selection="false">Id</th>
								            <th data-column-id="nombre" data-order="asc"><spring:message code="table.nombre"/></th>
								            <th data-column-id="domicilio"><spring:message code="table.domicilio"/></th>
								            <th data-column-id="telefono" data-visible="false"><spring:message code="table.telefono"/></th>
								            <th data-column-id="email"><spring:message code="table.email"/></th>
								           
								            <th data-column-id="link" data-formatter="link" data-sortable="false"><spring:message code="table.acciones"/></th>
								        </tr>
								    </thead>
								    <tbody>

								       	<c:forEach items="${sedeList}" var="sede">
					                    <tr>
					                    	<td><c:out value="${sede.id}" /></td>
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
				            return "<a href='buyTicket?sede="+row.id+"'>"
							+"<button class='btn btn-raised btn-default btn-sm withoutMargin'>"
							+ "       <span class='glyphicon glyphicon-ok-circle' aria-hidden='true'></span> <spring:message code="table.seleccionar"/>"
							+ "</button>"
							+ "</a>"
				            
				            + "<button class='btn btn-raised btn-default btn-sm withoutMargin command'"
				            + "data-row-id='"+ row.id + "'"
				            + "data-row-nombre='"+ row.nombre + "'"
				            + "data-row-domicilio='"+ row.domicilio + "'"
				            + "data-row-telefono='"+ row.telefono + "'"
				            + "data-row-email='"+ row.email + "'"
            
				            +">"
							+ "       <span class='glyphicon glyphicon glyphicon-search' aria-hidden='true'></span> <spring:message code="table.ver"/>"
							+ "     </button>";
							

				        }
				    }
				}).on("loaded.rs.jquery.bootgrid", function(){
				        /* Executes after data is loaded and rendered */
				        grid.find(".command").on("click", function(e){
				        	
				        	bootbox.dialog({
				        		  title: "<spring:message code="listSede.modalTitle"/>",
				        		  message:  "<div class='well'><p><b><spring:message code="table.nombre"/>: </b>"+$(this).data("row-nombre").toString()+"</p>"+
				        		  			"<p><b><spring:message code="table.domicilio"/>: </b>"+$(this).data("row-domicilio").toString()+"</p>"+
				        		  			"<p><b><spring:message code="table.telefono"/>: </b>"+$(this).data("row-telefono").toString()+"</p>"+
				        		  			"<p><b><spring:message code="table.email"/>: </b>"+$(this).data("row-email").toString()+"</p></div>"
				        	});
				        })
				});
		</script>
	</jsp:body>
</t:template>