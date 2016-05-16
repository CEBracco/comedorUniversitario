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
										<h3>Listado de Sugerencias</h3>
									</div>
									
									<c:if test="${role == 'Comensal'}">
										<div class="col-md-6">
											<a href="createSugerencia" class="btn btn-raised btn-danger btn-sm withoutMargin marginTop pull-right animsition-link">Enviar Sugerencia</a>
										</div>
									</c:if>
								</div>
								<table id="grid-data-api" class="table table-condensed table-hover table-striped">
								    <thead>
								        <tr>
								        	
								            <th data-column-id="Tipo" data-order="asc">Tipo</th>
								            <th data-column-id="usuario">Dni Usuario</th>
								           
								  			<th data-column-id="link" data-formatter="link" data-sortable="false">Acciones</th>
								        </tr>
								    </thead>
								    <tbody>
									<c:choose>
   										 <c:when test="${not empty sugerenciaList}">
										       
										 <c:forEach items="${sugerenciaList}" var="sugerencia">
					                    	<tr>
					                    		
												<td><c:out value="${sugerencia.tipo}" /></td>
												<td><c:out value="${sugerencia.usuario.dni}" /></td>

												<td><a href="verSugerencia?id=${sugerencia.id}" class="btn btn-raised btn-default btn-sm withoutMargin command"> <span class='glyphicon glyphicon glyphicon-search' aria-hidden='true'></span>Ver</a></td>
												
											</tr>
										
					                    </c:forEach>
										       
										    </c:when>    
										    <c:otherwise>
										        <h3>no hay Sugerencias registradas</h3>
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
		<script src="<c:url value="/resources/js/borrar.js" />"></script>
		
		
	</jsp:body>
</t:template>