<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<t:template>
	<jsp:body>
    	<div class="container-fluid">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="well">
						<div class="row">
							<div class="container-fluid">
								<h3><spring:message code="creditCharge.head"/></h3>
							</div>	
						</div>
						<form:form id="recargaRegisterForm" modelAttribute="recarga" method="post" action="saveRecarga" autocomplete="off">
							<input type="hidden" name="idComensal" value="${comensal.id}" required="required"/>
							
							<div class="row">
								<div class="container-fluid">
									<h4><spring:message code="creditCharge.formHead"/></h4>
									<ul type="none">
										<li><b><spring:message code="table.nombre"/>: </b>${comensal.nombre}</li>
										<li><b><spring:message code="table.apellido"/>: </b>${comensal.apellido}</li>
										<li><b><spring:message code="table.documento"/>: </b>${comensal.dni}</li>
										<li><b><spring:message code="creditCharge.formSaldo"/>: </b>$${comensal.saldo}</li>
									</ul>
								</div>
							</div>
							
							<c:choose>
								<c:when test="${role == 'Administrador'}">
								<div class="row">
									<div class="container-fluid">
										<div class="form-group reset-margin">
											<label class="control-label" for="sede"><spring:message code="creditCharge.formSedePago"/></label>
											<select class="form-control" id="sede" name="idSede" required>
												<option value="0" selected disabled>No Seleccionado</option>
												<c:forEach items="${sedeList}" var="sede">
													<option value="${sede.id}">${sede.nombre}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								</c:when>
								<c:otherwise>
									<input type="hidden" value="0" name="idSede" required>
								</c:otherwise>
								
							</c:choose>
							
							<div class="row">
								<div class="container-fluid">
									<div class="form-group label-floating">
										<label class="control-label" for="documento"><spring:message code="creditCharge.formCarga"/></label>
										<form:input type="number" cssClass="form-control" path="monto" required="required"/>
										<p class="help-block"><spring:message code="creditCharge.formCargaHelp"/></p>
									</div>
								</div>	
							</div>
							
							<div class="row">
								<div class="container-fluid">
									<input class="btn btn-block btn-danger btn-raised" type="submit" value="<spring:message code="form.submit"/>" id="saveRecarga">
								</div>
							</div>
						</form:form>
						
					</div>
					
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
	</jsp:body>
</t:template>