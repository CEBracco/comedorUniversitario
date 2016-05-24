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
									<div class="col-md-6">
										<h3><spring:message code="listMenu.head"/></h3>
									</div>
									<div class="col-md-6">
										<a href="createMenu" class="btn btn-raised btn-danger btn-sm withoutMargin marginTop pull-right animsition-link"><spring:message code="listMenu.addButton"/></a>
									</div>
								</div>
								<table id="grid-data-api" class="table table-condensed table-hover table-striped">
								    <thead>
								        <tr>
								        	<th data-column-id="id" data-visible="false" data-visible-in-selection="false">Id</th>
								            <th data-column-id="nombre" data-order="asc"><spring:message code="table.nombre"/></th>
								            <th data-column-id="vegetariano" data-visible="false"><spring:message code="table.vegetariano"/></th>
    										<th data-column-id="celiaco" data-visible="false"><spring:message code="table.celiaco"/></th>
										    <th data-column-id="hipertenso" data-visible="false"><spring:message code="table.hipertenso"/></th>
										    <th data-column-id="intolerante" data-visible="false"><spring:message code="table.intolerante"/></th>
										    <th data-column-id="diabetico" data-visible="false"><spring:message code="table.diabetico"/></th>
								            <th data-column-id="link" data-formatter="link" data-sortable="false"><spring:message code="table.acciones"/></th>
								        </tr>
								    </thead>
								    <tbody>

								       	<c:forEach items="${menuList}" var="menu">
					                    <tr>
					                    	<td><c:out value="${menu.id}" /></td>
											<td><c:out value="${menu.nombre}" /></td>
											<td>
												<c:choose>
													<c:when test="${menu.vegetariano}">
														<spring:message code="table.si"/>
													</c:when>
													<c:otherwise>
														<spring:message code="table.no"/>
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${menu.celiaco}">
														<spring:message code="table.si"/>
													</c:when>
													<c:otherwise>
														<spring:message code="table.no"/>
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${menu.hipertenso}">
														<spring:message code="table.si"/>
													</c:when>
													<c:otherwise>
														<spring:message code="table.no"/>
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${menu.intolerante}">
														<spring:message code="table.si"/>
													</c:when>
													<c:otherwise>
														<spring:message code="table.no"/>
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${menu.diabetico}">
														<spring:message code="table.si"/>
													</c:when>
													<c:otherwise>
														<spring:message code="table.no"/>
													</c:otherwise>
												</c:choose>
											</td>
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
							+ "       <span class='glyphicon glyphicon glyphicon-search' aria-hidden='true'></span> <spring:message code="table.ver"/>"
							+ "     </button>"
							+ "<a href='editMenu?id="+ row.id +"'>"
							+ "<button class='btn btn-raised btn-default btn-sm withoutMargin command'>"
							+ "<span class='glyphicon glyphicon glyphicon-pencil' aria-hidden='true'></span> <spring:message code="table.editar"/>"
							+ "</button>"
							+"</a>"
							+ "<a href='#'>"
							+"<button class='btn btn-raised btn-default btn-sm withoutMargin command-delete'"
    						+"data-row-nombre='"+row.nombre+"'"
							+"data-row-id='"+row.id+"'"
							+"data-row-link='deleteMenu'>"
							+ "<span class='glyphicon glyphicon glyphicon-trash' aria-hidden='true'></span> <spring:message code="table.eliminar"/>"
							+ "</button>"
							+"</a>";
				        }
				    }
				}).on("loaded.rs.jquery.bootgrid", function(){
				        /* Executes after data is loaded and rendered */
				        grid.find(".command").on("click", function(e){
				        	
				        	bootbox.dialog({
				        		  title: "Detalles del menu",
				        		  message:  "<div class='well'><p><b>Nombre: </b>"+$(this).data("row-nombre").toString()+"</p>"+
				        		  			"</div>"
				        	});
				        })
				});
		</script>
	</jsp:body>
</t:template>