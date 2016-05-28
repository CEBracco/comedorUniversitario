<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<t:template>
	<jsp:attribute name="head">
		<script src="<c:url value="/resources/js/ticket.js"/>"></script>
		<script>
			initMoney("${cartilla.precio}","${user.saldo}");
			window.onload = function() {
				refreshDates();
			}
		</script>
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
										<h3><spring:message code="buyTicket.head"/></h3>
									</div>
									<div class="col-md-6">
										<a onclick="displayWeek()" href="javascript:void(0)" class="btn btn-raised btn-danger btn-sm withoutMargin marginTop pull-right"><spring:message code="buyTicket.addWeek"/></a>
									</div>
								</div>
								
								${fn:length(semanas)}
								<c:forEach items="${semanas}" var="semana">								
								<div class="col-md-12 defaultWeek hide">
									<div class="panel panel-danger">
										<div class="panel-heading clearfix">
											<h3 class="panel-title pull-left"><spring:message code="buyTicket.week"/></h3>
										    <button type="button" class="close">&times;</button>
										</div>
										<div class="panel-body">
											<table class="table table-striped table-hover ">
												<thead>
													<tr>
														<th><spring:message code="buyTicket.lunes"/> <span class="date"></span></th>
														<th><spring:message code="buyTicket.martes"/> <span class="date"></span></th>
														<th><spring:message code="buyTicket.miercoles"/> <span class="date"></span></th>
														<th><spring:message code="buyTicket.jueves"/> <span class="date"></span></th>
														<th><spring:message code="buyTicket.viernes"/> <span class="date"></span></th>
													</tr>
												</thead>
												
												<tbody>
													<tr>
														<c:forEach items="${semana}" var="dia">
														<td>
															<div class="form-group reset-margin">
															<select class="form-control menuSelect" name="dias" required>
																<option value="0" selected><spring:message code="buyTicket.noSeleccionado"/></option>
																<c:if test="${not empty dia.menus}">
																<c:forEach items="${dia.menus}" var="menu">
																	<option value="${menu.id}">${menu.nombre}</option>
																</c:forEach>
																</c:if>
															</select>
															</div>
														</td>
														</c:forEach>
													</tr>
												
												</tbody>
											</table>
										</div>
									</div>
								</div>
								</c:forEach>
								
								<br>
									
								<form method="post" action="saveTicket">
									
									<input type="hidden" name="sede" value="${sede}">


									<div id="weekContainer" class="row">
										
										<c:choose>
											<c:when test="${empty reservasPorSemana}">
												<script>displayWeek();</script>
											</c:when>
										
											<c:otherwise>
												<script>initWeeks('${fn:length(reservasPorSemana)}');</script>
																				
												<c:forEach items="${reservasPorSemana}" var="reservas" varStatus="loop">
												<div class="col-md-12 week">
													<div class="panel panel-danger">
														<div class="panel-heading clearfix">
															<h3 class="panel-title pull-left"><spring:message code="buyTicket.week"/> ${loop.index + 1}</h3>
														</div>
														<div class="panel-body without-margin">
															<table class="table table-striped table-hover ">
																<thead>
																	<tr>
																		<th><spring:message code="buyTicket.lunes"/> <span class="date"></span></th>
																		<th><spring:message code="buyTicket.martes"/> <span class="date"></span></th>
																		<th><spring:message code="buyTicket.miercoles"/> <span class="date"></span></th>
																		<th><spring:message code="buyTicket.jueves"/> <span class="date"></span></th>
																		<th><spring:message code="buyTicket.viernes"/> <span class="date"></span></th>
																	</tr>
																</thead>
																
																<tbody>
																	<tr>
																			
																		<c:forEach items="${reservas}" var="reserva">
																		<c:if test="${not empty reserva.dia}">
																		<td>
																			<div class="form-group reset-margin">
																			<c:choose>
																				<c:when test="${reserva.id == '0'}">
																					<select class="form-control menuSelect" name="dias" required>
																						<option value="0" selected><spring:message code="buyTicket.noSeleccionado"/></option>
																						<c:forEach items="${reserva.dia.menus}" var="menu">
																							<option value="${menu.id}">${menu.nombre}</option>
																						</c:forEach>
																					</select>
																				</c:when>
																				<c:otherwise>
																					<input type="hidden" value="0" name="dias">
																					<div class="padding-10">
																						<p><spring:message code="buyTicket.comprado"/></p>
																					</div>
																				</c:otherwise>
																			</c:choose>
																			</div>
																		</td>
																		</c:if>
																		</c:forEach>
																		
																	</tr>
																
																</tbody>
															</table>
														</div>
													</div>
												</div>
												</c:forEach>
											</c:otherwise>	
										</c:choose>
										
									</div>
									<div class="row">
										<div class="col-md-12">
											<input id="submitTicket" type="submit" class="btn btn-danger btn-lg btn-block btn-raised" value="<spring:message code="buyTicket.aceptar"/>">
										</div>
									</div>
								</form>
								<hr>
								<div class="row">
										<div class="col-md-12 text-center">
											<p><spring:message code="buyTicket.total"/></p>
											<h3 class="innerTitle"><b><span id="price">$0</span></b></h3>
											<h4 class="innerTitle"><spring:message code="buyTicket.saldo"/>: $<span id="credits">${user.saldo}</span></h4>
											<h6><spring:message code="buyTicket.aclaration"/></h6>
										</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
	</jsp:body>
</t:template>