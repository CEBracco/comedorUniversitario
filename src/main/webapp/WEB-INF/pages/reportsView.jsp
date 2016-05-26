<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
								<div class="row text-center">
									<h2><spring:message code="reportsView.head"/></h2>
								</div>
								<hr>
								<div class="row">
									<div class="col-md-6 text-center">
										<a href="getAllTicketsSedes" class="btn btn-primary btn-fab big animsition-link"><spring:message code="reportsView.ticketsSedesLetra"/></a>
										<h4><spring:message code="reportsView.ticketsSedes"/></h4>
									</div>
									<div class="col-md-6 text-center">
										<a href="getAllPagos" class="btn btn-danger btn-fab big animsition-link"><spring:message code="reportsView.pagosLetra"/></a>
										<h4><spring:message code="reportsView.pagos"/></h4>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
	</jsp:body>
</t:template>