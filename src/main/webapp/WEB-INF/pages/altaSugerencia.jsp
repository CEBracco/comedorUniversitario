<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:template>
	<jsp:body>
    	<div class="container-fluid">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="well">
						<div class="row">
							<div class="container-fluid">
							<c:choose>
								    <c:when test="${edit}">
								    	<h3>Editar sugerencia</h3>
								    </c:when>
								    <c:otherwise>
										<h3>Crear una Sugerencia</h3>
								    </c:otherwise>	
								</c:choose>
							<form:form id="sugerenciaRegisterForm" modelAttribute="sugerencia" method="post" action="saveSugerencia" autocomplete="off">
									<c:choose>
									    <c:when test="${empty sugerenciaObject.id}">
									    	<form:hidden path="id" value="0" />
									    </c:when>
									    <c:otherwise>
											<form:hidden path="id" value="${sugerenciaObject.id}" />
									    </c:otherwise>
									</c:choose>
									
										
											   
									    	
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="tipo">tipo</label>
												<form:input type="text" cssClass="form-control" path="tipo"  required="required"/>
												<p class="help-block">Ingrese el Tipo de sugerencia</p>	
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<select name="idSedes" class="form-control">
  													<option selected="selected" value="0" disabled>Seleccionar una sede (opcional)</option>
													<c:forEach items="${sedeList}" var="plato">
														<option value="${plato.id}" label="${plato.nombre}"/>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									
									<div class="form-group label-floating">
												<label class="control-label" for="sugerencia">Sugerencia</label>
												<form:textarea type="text" cssClass="form-control" path="sugerencia" required="required"/>
												<p class="help-block">Ingrese su sugerencia</p>	
											</div>
									
									
								
									<div class="row">
										<div class="container-fluid">
										<input class="btn btn-block btn-danger btn-raised" type="submit" value="Registrar" id="saveSugerencia">
										</div>
									</div>
								</form:form>
						
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
	</jsp:body>
</t:template>