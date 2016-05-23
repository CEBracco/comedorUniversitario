<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
								
									<div class="col-md-10">
											<div class="col-md-4"></div>
											<div class="col-md-6">
												<h3><spring:message code="verSugerencia.head"/></h3>
											</div>
									</div>
									
								
										<div class="col-md-12" >
											<div class="panel panel-primary">
									
												<div class="panel-heading">
										    		<h4 class="panel-title"><spring:message code="verSugerencia.sugerencia"/></h4>
										  		</div>
										 		<div class="panel-body">
										 	  		 <p>${sugerencia.sugerencia}</p>
												</div>
											</div>
										</div>
									
										<div class="col-md-12">
										
								<!-- si no se respondio, solo el admin puede responder -->
								
									<c:choose>
								   		 <c:when test="${empty sugerencia.respuesta}">
								   		 		<c:if test="${role != 'Comensal'}">
								    			<form:form id="sugerenciaRespuestaRegisterForm" modelAttribute="sugerencia" method="post" action="saveRespuesta" autocomplete="off">
													<form:hidden path="id" value="${sugerencia.id}" />
											
															<div class="form-group label-floating">
																<label class="control-label" for="respuesta"><spring:message code="verSugerencia.respuesta"/></label>
																<form:textarea type="text" cssClass="form-control" path="respuesta" required="required" />
																<p class="help-block"><spring:message code="verSugerencia.ingRespuesta"/></p>	
															</div>
												
															<div class="row">
																	<div class="container-fluid">
													
																		<input class="btn btn-block btn-danger btn-raised" type="submit" value="<spring:message code="verSugerencia.responder"/>" id="saveRespuesta">
											
																	</div>
															</div>
													</form:form>
													</c:if>
								 		   </c:when>
								    <c:otherwise>
								    
										
											<div class="panel panel-primary">
									
												<div class="panel-heading">
										    		<h4 class="panel-title">Respuesta</h4>
										  		</div>
										 		<div class="panel-body">
										 	  		 <p>${sugerencia.respuesta}</p>
												</div>
											</div>
										
										
								    </c:otherwise>	
								</c:choose>
							
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