<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
	<jsp:attribute name="head">
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/libs/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>"/>
		<script src="<c:url value="/resources/libs/bootstrap-datepicker/js/bootstrap-datepicker.min.js"/>"></script>
		<script src="<c:url value="/resources/libs/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js"/>" charset="UTF-8"></script>
		<script src="<c:url value="/resources/js/cartilla.js"/>"></script>
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
									<c:choose>
									    <c:when test="${edit}">
									    	<h3>Editar cartilla</h3>
									    </c:when>
									    <c:otherwise>
											<h3>Nueva cartilla</h3>
									    </c:otherwise>	
									</c:choose>
									</div>
									<div class="col-md-6">
										<a href="javascript:void(0)" onclick="displayDay()" class="btn btn-raised btn-danger btn-sm withoutMargin marginTop pull-right">Agregar Dia</a>
									</div>
								</div>
								
								<form:form id="cartillaRegisterForm" modelAttribute="cartilla" method="post" action="saveCartilla" autocomplete="off">
									<c:choose>
									    <c:when test="${empty cartillaObject.id}">
									    	<form:hidden path="id" value="0" />
									    </c:when>
									    <c:otherwise>
											<form:hidden path="id" value="${cartillaObject.id}" />
									    </c:otherwise>
									</c:choose>
									
									<div class="row">
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="initDate">Fecha de inicio</label>
												<fmt:formatDate value="${cartillaObject.inicio}" var="initDateString" pattern="dd/MM/yyyy" />
												<form:input cssClass="form-control" path="inicio" value="${initDateString}" required="required" data-provide="datepicker" data-date-format="dd/mm/yyyy" data-date-language="es"/>
												<p class="help-block">Ingrese fecha de inicio de la cartilla</p>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group label-floating">
												<label class="control-label" for="finalDate">Fecha de vencimiento</label>
												<fmt:formatDate value="${cartillaObject.fin}" var="finalDateString" pattern="dd/MM/yyyy" />
												<form:input cssClass="form-control" path="fin" value="${finalDateString}" required="required" data-provide="datepicker" data-date-format="dd/mm/yyyy" data-date-language="es"/>
												<p class="help-block">Ingrese fecha de vencimiento de la cartilla</p>	
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group label-floating">
												<label class="control-label" for="nombre">Precio</label>
												<fmt:formatNumber value="${cartillaObject.precio}" var="precioCartilla" maxFractionDigits="2"/>
												<form:input type="number" cssClass="form-control" path="precio" value="${precioCartilla}" required="required"/>
												<p class="help-block">Ingrese el precio de los menús de la cartilla</p>
											</div>
										</div>
									</div>
									<br>
									
									<!-- selector de menu a clonar -->
									<div class="form-group selectMenuDefault hide">
										<div class="input-group">
											<select name="idMenusDefault" class="form-control">
												<option selected="selected" value="" disabled>Seleccionar Menú...</option>
												<c:forEach items="${menuList}" var="menu">
												<option value="${menu.id}" label="${menu.nombre}"/>
												</c:forEach>
											</select>
											<span class="input-group-btn clearButton">
												<button type="button" class="btn btn-fab btn-fab-mini">
												<i class="material-icons">clear</i>
												</button>
											</span>
										</div>
									</div>
									
									<!-- dia a clonar -->
									<div class="col-md-12 hide defaultDay">
										<div class="panel panel-danger">
											<div class="panel-heading clearfix">
												<h3 class="panel-title pull-left"></h3>
											    <button type="button" class="close">&times;</button>
											</div>
											<div class="panel-body menus">
												
												<div class="selectContainer"></div>
												
												<input type="hidden" name="idMenusDefault" value="finDia">
												<div class="row">
													<div class="col-md-4"></div>
													<div class="col-md-4">
														<button type="button" class="btn btn-block btn-danger btn-raised addMenu">Añadir Menú</button>
													</div>
													<div class="col-md-4"></div>
												</div>
											</div>
										</div>
									</div>
									
									<h4>Días Asociados</h4>
									<div id="daysContainer" class="row">
										
										<c:forEach items="${diaList}" var="dia">
										<div class="col-md-12 day">
											<div class="panel panel-danger">
												<div class="panel-heading clearfix">
													<h3 class="panel-title pull-left"></h3>
												    <button type="button" class="close">&times;</button>
												</div>
												<div class="panel-body menus">
													<div class="selectContainer">
														
														<c:forEach items="${dia.menus}" var="menuDelDia">
														<!-- selector de menu a clonar -->
														<div class="form-group selectMenu">
															<div class="input-group">
																<select name="idMenus" class="form-control">
																	<option value="" disabled>Seleccionar Menú...</option>
																	<c:forEach items="${menuList}" var="menu">
																	<c:choose>
																		<c:when test="${menu.id == menuDelDia.id}">
																		<option value="${menu.id}" label="${menu.nombre}" selected/>
																		</c:when>
																		<c:otherwise>
																		<option value="${menu.id}" label="${menu.nombre}"/>
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
													
													<input type="hidden" name="idMenus" value="finDia">
													<div class="row">
														<div class="col-md-4"></div>
														<div class="col-md-4">
															<button type="button" class="btn btn-block btn-danger btn-raised addMenu">Añadir Menú</button>
														</div>
														<div class="col-md-4"></div>
													</div>
												</div>
											</div>
										</div>
										</c:forEach>
										
									</div>
									
									<div class="row">
										<div class="container-fluid">
										<input class="btn btn-block btn-danger btn-raised" type="submit" value="Registrar" id="saveCartilla">
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