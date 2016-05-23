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
										<h3><spring:message code="listSugerencia.head"/></h3>
									</div>
									
									<c:if test="${role == 'Comensal'}">
										<div class="col-md-6">
											<a href="createSugerencia" class="btn btn-raised btn-danger btn-sm withoutMargin marginTop pull-right animsition-link"><spring:message code="listSugerencia.eviarSugerencia"/></a>
										</div>
									</c:if>
								</div>
								<table id="grid-data-api" class="table table-condensed table-hover table-striped">
								    <thead>
								        <tr>
								        	
								            <th data-column-id="Tipo" data-order="asc"><spring:message code="listSugerencia.tipo"/></th>
								            <th data-column-id="usuario">Dni Usuario</th>
								           
								  			<th data-column-id="link" data-formatter="link" data-sortable="false"><spring:message code="table.acciones"/></th>
								        </tr>
								    </thead>
								    <tbody>
									<c:choose>
   										 <c:when test="${not empty sugerenciaList}">
										       
										 <c:forEach items="${sugerenciaList}" var="sugerencia">
					                    	<tr>
					                    		
												<td><c:out value="${sugerencia.tipo}" /></td>
												<td><c:out value="${sugerencia.usuario.dni}" /></td>

												
											</tr>
										
					                    </c:forEach>
										       
										    </c:when>    
										    <c:otherwise>
										        <h3>no hay Sugerencias registradas</h3>
										        <
										    </c:otherwise>
										</c:choose>
									
									
									
									
								       	

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
					navigation: 2,
					rowCount: 6,
				    formatters: {
				        "link": function(column, row)
				        {
				           
							
							return "<a  href='"+verSugerencia?id=${sugerencia.id}+"'>"
							+"<button class='btn btn-raised btn-default btn-sm withoutMargin command'"
							+ "       <span class='glyphicon glyphicon glyphicon-search' aria-hidden='true'></span>"+"<spring:message code="table.ver"/>"
							+ "</button>"
							
						;
				        }
				    }
				}).on("loaded.rs.jquery.bootgrid", function(){
				        /* Executes after data is loaded and rendered */
				     
				});
		</script>
	</jsp:body>
</t:template>