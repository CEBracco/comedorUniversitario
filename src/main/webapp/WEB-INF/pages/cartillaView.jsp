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
								<div class="row text-center">
									<h2><spring:message code="cartillaView.head"/></h2>
								</div>
								<hr>
								<div class="row">
									<div class="col-md-4 text-center">
										<a href="getAllPlatos" class="btn btn-primary btn-fab big animsition-link">P</a>
										<h4><spring:message code="cartillaView.platos"/></h4>
									</div>
									<div class="col-md-4 text-center">
										<a href="getAllCartillas" class="btn btn-danger btn-fab big animsition-link">C</a>
										<h4><spring:message code="cartillaView.cartillas"/></h4>
									</div>
									<div class="col-md-4 text-center">
										<a href="getAllMenus" class="btn btn-success btn-fab big animsition-link">M</a>
										<h4><spring:message code="cartillaView.menus"/></h4>
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