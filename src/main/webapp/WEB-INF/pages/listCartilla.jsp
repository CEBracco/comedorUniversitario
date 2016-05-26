<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
									<div class="col-md-6">
										<h3><spring:message code="listCartilla.head"/></h3>
									</div>
									<div class="col-md-6">
										<a href="createCartilla" class="btn btn-raised btn-danger btn-sm withoutMargin marginTop pull-right animsition-link"><spring:message code="listCartilla.addButton"/></a>
									</div>
								</div>
								<table id="grid-data-api" class="table table-condensed table-hover table-striped">
								    <thead>
								        <tr>
								        	<th data-column-id="id" data-visible="false" data-visible-in-selection="false">Id</th>
								            <th data-column-id="initDate" data-order="asc"><spring:message code="table.inicio"/></th>
								            <th data-column-id="finalDate" data-order="asc"><spring:message code="table.fin"/></th>
								            <th data-column-id="link" data-formatter="link" data-sortable="false" data-width="30%"><spring:message code="table.acciones"/></th>
								        </tr>
								    </thead>
								    <tbody>

								       	<c:forEach items="${cartillaList}" var="cartilla">
					                    <tr>
					                    	<td><c:out value="${cartilla.id}" /></td>
											<td><fmt:formatDate value="${cartilla.inicio}" pattern="dd/MM/yyyy" /></td>
											<td><fmt:formatDate value="${cartilla.fin}" pattern="dd/MM/yyyy" /></td>
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
            
				            +">"
							+ "       <span class='glyphicon glyphicon glyphicon-search' aria-hidden='true'></span> <span class='hidden-xs hidden-sm'><spring:message code="table.ver"/></span>"
							+ "     </button>"
							+ "<a href='editCartilla?id="+ row.id +"'>"
							+ "<button class='btn btn-raised btn-default btn-sm withoutMargin command'>"
							+ "<span class='glyphicon glyphicon glyphicon-pencil' aria-hidden='true'></span> <span class='hidden-xs hidden-sm'><spring:message code="table.editar"/></span>"
							+ "</button>"
							+"</a>"
							+ "<a href='#'>"
							+"<button class='btn btn-raised btn-default btn-sm withoutMargin command-delete'"
    						+"data-row-nombre='"+row.nombre+"'"
							+"data-row-id='"+row.id+"'"
							+"data-row-link='deleteCartilla'>"
							
							+ "<span class='glyphicon glyphicon glyphicon-trash' aria-hidden='true'></span> <span class='hidden-xs hidden-sm'><spring:message code="table.eliminar"/></span>"
							+ "</button>"
							+"</a>";
				        }
				    }
				}).on("loaded.rs.jquery.bootgrid", function(){
				        /* Executes after data is loaded and rendered */
				        grid.find(".command").on("click", function(e){
				        	
				        	bootbox.dialog({
				        		  title: "<spring:message code="listCartilla.detalles"/>",
				        		  message:  "<div class='well'><p><b><spring:message code="table.nombre"/>: </b>"+$(this).data("row-nombre").toString()+"</p>"+
				        		  			"</div>"
				        	});
				        })
				});
		</script>
	</jsp:body>
</t:template>