<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
								<c:choose>
								    <c:when test="${edit}">
								    	<h3><spring:message code="altaPlato.headEditar"/></h3>
								    </c:when>
								    <c:otherwise>
										<h3><spring:message code="altaPlato.headAlta"/></h3>
								    </c:otherwise>	
								</c:choose>
								
								<form:form id="platoRegisterForm" modelAttribute="plato" method="post" action="savePlato" autocomplete="off">
									<c:choose>
									    <c:when test="${empty platoObject.id}">
									    	<form:hidden path="id" value="0" />
									    </c:when>
									    <c:otherwise>
											<form:hidden path="id" value="${platoObject.id}" />
									    </c:otherwise>
									</c:choose>
									
									<div class="form-group label-floating">
										<label class="control-label" for="nombre"><spring:message code="table.nombre"/></label>
										<form:input type="text" cssClass="form-control" path="nombre" value="${platoObject.nombre}" required="required"/>
										<p class="help-block"><spring:message code="altaPlato.formNombreHelp"/></p>
									</div>
									
									
									<h4><spring:message code="altaPlato.formApto"/>:</h4>
									<div class="row">
										<div class="col-md-12">
											<div class="checkbox">
												<label>
												<c:choose>
											    <c:when test="${platoObject.vegetariano}">
											    	<form:checkbox path="vegetariano" checked="checked"/> <spring:message code="table.vegetariano"/>
											    </c:when>
											    <c:otherwise>
													<form:checkbox path="vegetariano" /><spring:message code="table.vegetariano"/>
											    </c:otherwise>
												</c:choose>
												</label>
												<br>
												<label>
												<c:choose>
											    <c:when test="${platoObject.celiaco}">
											    	<form:checkbox path="celiaco" checked="checked" /> <spring:message code="table.celiaco"/>
											    </c:when>
											    <c:otherwise>
													<form:checkbox path="celiaco" /> <spring:message code="table.celiaco"/>
											    </c:otherwise>
												</c:choose>
												</label>
												<br>
												<label>
												<c:choose>
											    <c:when test="${platoObject.hipertenso}">
											    	<form:checkbox path="hipertenso" checked="checked" /> <spring:message code="table.hipertenso"/>
											    </c:when>
											    <c:otherwise>
													<form:checkbox path="hipertenso" /> <spring:message code="table.hipertenso"/>
											    </c:otherwise>
												</c:choose>
												</label>
												<br>
												<label>
												<c:choose>
											    <c:when test="${platoObject.intolerante}">
											    	<form:checkbox path="intolerante" checked="checked" /> <spring:message code="table.intolerante"/>
											    </c:when>
											    <c:otherwise>
													<form:checkbox path="intolerante" /> <spring:message code="table.intolerante"/>
											    </c:otherwise>
												</c:choose>
												</label>
												<br>
												<label>
												<c:choose>
											    <c:when test="${platoObject.diabetico}">
											    	<form:checkbox path="diabetico" checked="checked" /> <spring:message code="table.diabetico"/>
											    </c:when>
											    <c:otherwise>
													<form:checkbox path="diabetico" /> <spring:message code="table.diabetico"/>
											    </c:otherwise>
												</c:choose>
												</label>
																				
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="container-fluid">
										<input class="btn btn-block btn-danger btn-raised" type="submit" value="<spring:message code="form.submit"/>" id="savePlato">
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