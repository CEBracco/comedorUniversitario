<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<t:template>
	<jsp:attribute name="head">
		
		<script src="http://maps.googleapis.com/maps/api/js"></script>
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
								<h3>Sede: ${sedeObject.nombre}</h3>
							</div>
							
							</div>
						</div>
					</div>
			</div>
			<div class="row">
			
				<div class="col-md-1"></div>
				<div class="col-md-5">
					<div class="panel">
						<div class="panel-heading">
							<div class="row">
							<c:choose>
							    <c:when test="${not empty responsableObject.dni}">
							       	<div class="col-md-8 col-sm-9 col-xs-5">
										<h3>Responsable Asignado</h3>
									</div>
									<div class="col-md-4 col-sm-3 col-xs-7">
										<a href="removeResponsableSede?idSede=${sedeObject.id}&idResponsable=${responsableObject.id}" class="btn btn-raised btn-danger btn-sm withoutMargin marginTop animsition-link"><span class='glyphicon glyphicon glyphicon-pencil'></span>Desvincular</a>
									</div>
							    </c:when>    
							    <c:otherwise>
							        <div class="col-md-8 col-sm-9 col-xs-5">
										<h3>Asignar Responsable</h3>
									</div>
									
							    </c:otherwise>
							</c:choose>
								
							</div>
						</div>
					  <div class="panel-body">
					   
					   <c:choose>
						    <c:when test="${not empty responsableObject.dni}">
						        <div class="row">
									<div class="col-xs-6"><p><b>Nombre</b></p></div>
									<div class="col-xs-6"><p>${responsableObject.nombre}</p></div>
								</div>
								<div class="row">
									<div class="col-xs-6"><p><b>Apellido</b></p></div>
									<div class="col-xs-6"><p>${responsableObject.apellido}</p></div>
								</div>
								<div class="row">
									<div class="col-xs-6"><p><b>Documento</b></p></div>
									<div class="col-xs-6"><p>${responsableObject.dni}</p></div>
								</div>
						    </c:when>    
						    <c:otherwise>
						        <table id="grid-data-api" class="table table-condensed table-hover table-striped">
								    <thead>
								        <tr>
								       		 <th data-column-id="id" data-visible="false" data-visible-in-selection="false">Id</th>
								            
								        	<th data-column-id="dni" data-order="asc">Dni Responsable</th>
								            <th data-column-id="nombre">nombre</th>
								            <th data-column-id="link" data-formatter="link" data-sortable="false">Acciones</th>
								        </tr>
								    </thead>
								    <tbody>
									<c:choose>
   										 <c:when test="${not empty responsableList}">
										       
										 <c:forEach items="${responsableList}" var="responsable">
					                    	<tr>
					                    		<td><c:out value="${responsable.id}" /></td>
												<td><c:out value="${responsable.dni}" /></td>
												<td><c:out value="${responsable.nombre}" /></td>

												<td><a href="#" class="btn btn-raised btn-default btn-sm withoutMargin command"> <span ></span>Asignar</a></td>
												
											</tr>
										
					                    </c:forEach>
										       
										    </c:when>    
										    <c:otherwise>
										        <h3>no hay Responsables registrados</h3>
										    </c:otherwise>
										</c:choose>
									 </tbody>
								</table>
						    </c:otherwise>
						</c:choose>
								
					  </div>
					</div>
				</div>
			
					<div class="col-md-5">
					<div class="panel panel-default">
					  <div class="panel-heading">Lugar(proximamente)</div>
					  <div class="panel-body">
							   <div class="row">
											<div id="googleMap" style="width:500px;height:380px;"></div>
							  </div>
					</div>
					</div>
			</div>
			</div>
							
		</div>
		
		<script src="<c:url value="/resources/libs/jquery.bootgrid/jquery.bootgrid.js" />"></script>
		
		<script>
		
		var myCenter=new google.maps.LatLng(-34.9074949,-57.9416048);
		var marker;

		function initialize()
		{
		var mapProp = {
		  center:myCenter,
		  zoom:15,
		  mapTypeId:google.maps.MapTypeId.ROADMAP
		  };

		var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);

		var marker=new google.maps.Marker({
		  position:myCenter,
		  animation:google.maps.Animation.BOUNCE
		  });

		marker.setMap(map);
		}

		google.maps.event.addDomListener(window, 'load', initialize);
		
		
</script>
		<script>
		var grid=$("#grid-data-api").bootgrid({
					navigation: 2,
					rowCount: 6,
				    formatters: {
				        "link": function(column, row)
				        {
				           
							
							return "<a href='assignResponsableSede?idSede="+${sedeObject.id}+"&idResponsable="+row.id+"'>"
							+"<button class='btn btn-raised btn-default btn-sm withoutMargin command'"
							+ "       <span class='glyphicon glyphicon glyphicon-search' aria-hidden='true'></span>Asignar Responsable"
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