<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:template>
	<jsp:attribute name="head">
		<script src="<c:url value="/resources/js/ticket.js"/>"></script>
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
										<h3>Compra de tickets</h3>
									</div>
									<div class="col-md-6">
										<a onclick="displayWeek()" href="javascript:void(0)" class="btn btn-raised btn-danger btn-sm withoutMargin marginTop pull-right">Añadir Semana</a>
									</div>
								</div>
								
																
								<div class="col-md-12 defaultWeek hide">
									<div class="panel panel-danger">
										<div class="panel-heading clearfix">
											<h3 class="panel-title pull-left">Semana</h3>
										    <button type="button" class="close">&times;</button>
										</div>
										<div class="panel-body">
											<table class="table table-striped table-hover ">
												<thead>
													<tr>
														<th>Lunes</th>
														<th>Martes</th>
														<th>Miercoles</th>
														<th>Jueves</th>
														<th>Viernes</th>
													</tr>
												</thead>
												
												<tbody>
													<tr>
														<c:forEach items="${cartilla.semana}" var="dia">
														<td>
															<div class="form-group reset-margin">
															<select class="form-control" name="dias" required>
																<option value="0" selected>No Seleccionado</option>
																<c:forEach items="${dia.menus}" var="menu">
																	<option value="${menu.id}">${menu.nombre}</option>
																</c:forEach>
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
								
								<br>
									
								<form method="post" action="saveTicket">
									
									<input type="hidden" name="sede" value="${sede}">


									<div id="weekContainer" class="row">
									
										<div class="col-md-12 week">
											<div class="panel panel-danger">
												<div class="panel-heading clearfix">
													<h3 class="panel-title pull-left">Semana 1</h3>
												    <button type="button" class="close">&times;</button>
												</div>
												<div class="panel-body without-margin">
													<table class="table table-striped table-hover ">
														<thead>
															<tr>
																<th>Lunes</th>
																<th>Martes</th>
																<th>Miercoles</th>
																<th>Jueves</th>
																<th>Viernes</th>
															</tr>
														</thead>
														
														<tbody>
															<tr>
																<c:forEach items="${cartilla.semana}" var="dia">
																<td>
																	<div class="form-group reset-margin">
																	<select class="form-control" name="dias" required>
																		<option value="0" selected>No Seleccionado</option>
																		<c:forEach items="${dia.menus}" var="menu">
																			<option value="${menu.id}">${menu.nombre}</option>
																		</c:forEach>
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
										
									</div>
									<div class="row">
										<div class="col-md-12">
											<input type="submit" class="btn btn-danger btn-lg btn-block btn-raised" value="Aceptar Compra">
										</div>
									</div>
								</form>
								
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
	</jsp:body>
</t:template>