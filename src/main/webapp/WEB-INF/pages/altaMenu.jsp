<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<t:template>
	<jsp:attribute name="head">
		<script src="<c:url value="/resources/js/addPlatos.js"/>"></script>
	</jsp:attribute>
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
								    	<h3 onclick="removeSelect"><spring:message code="altaMenu.headEditar"/></h3>
								    </c:when>
								    <c:otherwise>
										<h3><spring:message code="altaMenu.headAlta"/></h3>
								    </c:otherwise>	
								</c:choose>
								
								<form:form id="menuPlatoRegisterForm" modelAttribute="menu" method="post" action="saveMenu" autocomplete="off">
									<c:choose>
									    <c:when test="${empty menuObject.id}">
									    	<form:hidden path="id" value="0" />
									    </c:when>
									    <c:otherwise>
											<form:hidden path="id" value="${menuObject.id}" />
									    </c:otherwise>
									</c:choose>
	
									
									<div class="form-group label-floating">
										<label class="control-label" for="nombre"><spring:message code="table.nombre"/></label>
										<form:input type="text" cssClass="form-control" path="nombre" value="${menuObject.nombre}" required="required"/>
										<p class="help-block"><spring:message code="altaMenu.formNombreHelp"/></p>
									</div>
									
									<div class="row item">
										<div class="col-md-10">
											<h4><spring:message code="altaMenu.formPatos"/></h4>
										</div>
										<div class="col-md-2">
											<button type="button" onclick="displaySelect()" class="btn btn-sm btn-danger btn-raised pull-right"><spring:message code="altaMenu.formPlato"/></button>
										</div>
									</div>
									
									<!-- select a clonar -->
	 								<div class="form-group selectPlato hide">
										  <div class="input-group">
  												<select name="idPlatosDefault" class="form-control">
  													<option selected="selected" value="" disabled><spring:message code="altaMenu.formPlatoSelect"/></option>
													<c:forEach items="${platoList}" var="plato">
														<option value="${plato.id}" label="${plato.nombre}"/>
													</c:forEach>
												</select>
												<span class="input-group-btn clearButton">
													<button type="button" class="btn btn-fab btn-fab-mini">
														<i class="material-icons">clear</i>
													</button>
											    </span>
										  </div>
									</div>
									
									
									<div id="selectContainer">
										<c:forEach items="${menuObject.platos}" var="platoMenu">
										<div class="form-group selectPlato">
											  <div class="input-group">
	  												<select name="idPlatos" class="form-control" required="required">
														<option value="" disabled><spring:message code="altaMenu.formPlatoSelect"/></option>
															  												
														<c:forEach items="${platoList}" var="plato">
														
															<c:choose>
															    <c:when test="${plato.id == platoMenu.id}">
																	<option value="${plato.id}" label="${plato.nombre}" selected/>													    	
															    </c:when>
															    <c:otherwise>
																	<option value="${plato.id}" label="${plato.nombre}"/>
															    </c:otherwise>	
															</c:choose>
														
														</c:forEach>
													</select>
													<span class="input-group-btn clearButton">
														<button type="button" class="btn btn-fab btn-fab-mini">
															<i class="material-icons">clear</i>
														</button>
												    </span>
											  </div>
										</div>
										</c:forEach>
									</div>

									
									<div class="row">
										<div class="container-fluid">
										<input class="btn btn-block btn-danger btn-raised" type="submit" value="<spring:message code="form.submit"/>" id="saveMenuPlato">
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