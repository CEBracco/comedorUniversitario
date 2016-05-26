<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@attribute name="head" fragment="true" %>


<!DOCTYPE html>
<html class="animsition">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, user-scalable=no">
	
	<title><spring:message code="page.titulo"/></title>

	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/libs/bootstrap/css/bootstrap.min.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/libs/header/css/style.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/libs/font-awesome/css/font-awesome.min.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/footer.css"/>">
    <link href="<c:url value="/resources/libs/material/css/bootstrap-material-design.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/libs/material/css/ripples.min.css"/>" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/template.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/frontend.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/libs/animsition/css/animsition.min.css"/>">
	
	<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
  	<link href="//fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

	<script src="<c:url value="/resources/libs/jQuery/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/libs/header/js/modernizr.js"/>"></script>
	<script src="<c:url value="/resources/libs/bootstrap/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/libs/material/js/ripples.min.js"/>"></script>
    <script src="<c:url value="/resources/libs/material/js/material.min.js"/>"></script>
    <script src="<c:url value="/resources/libs/parallax-nice/scripts/jquery.parallax-1.1.3.js"/>"></script>
	<script src="<c:url value="/resources/libs/parallax-nice/scripts/jquery.localscroll-1.2.7-min.js"/>"></script>
	<script src="<c:url value="/resources/libs/parallax-nice/scripts/jquery.scrollTo-1.4.2-min.js"/>"></script>
	<script src="<c:url value="/resources/libs/animsition/js/animsition.min.js"/>" charset="utf-8"></script>
	<script src="<c:url value="/resources/libs/notify/notify.min.js"/>"></script>
		

	<script src="<c:url value="/resources/js/template.js"/>"></script>
    <script>
        $(document).ready(function() {
            $.material.init();
            $('body').parallax("50%", 0.2);
        });

    </script>
    
    <jsp:invoke fragment="head"/>
</head>

<header>
	<div class="cd-header">
		<nav class="cd-primary-nav">
			<ul>
				<!-- inser more links here -->
				<c:if test="${empty user.nombre}">
				<li><a href="#0"><spring:message code="page.ingresar"/></a></li>
				</c:if>
				<li><a href="#0"><spring:message code="page.noticia"/></a></li>
				<li><a href="#0"><spring:message code="page.contacto"/></a></li>
			</ul>
		</nav> <!-- cd-primary-nav -->
		<div id="cd-logo" class="is-hidden slide-in front"><a href="index" class="animsition-link"><img src="<c:url value="/resources/libs/img/logo2.png"/>" alt="Logo"></a></div>
	</div>
	<!-- <div id="cd-intro">
		<div id="cd-intro-tagline">
			<h1>Secondary Fixed Navigation</h1> -->
<!-- 		</div>
	</div>
 -->
	<c:choose>
	    <c:when test="${empty user.nombre}">
			<a id="login-btn" data-toggle="modal" href="#signIn" class="cd-btn is-hidden slide-in front"><spring:message code="page.ingresar"/></a>
	    </c:when>
	    <c:otherwise>
			<p class="cd-btn is-hidden withoutBG slide-in front"><spring:message code="page.bienvenida"/>, <a href="showProfile" class="animsition-link">${user.nombre}</a> (<a href="goodbye" class="animsition-link"><spring:message code="page.salir"/></a>)</p>	    	
	    </c:otherwise>
	</c:choose>

	<div class="cd-secondary-nav is-fixed animate-children">
		<div id="cd-logo2" class="is-hidden slide-in front"><a href="index" class="animsition-link"><img src="<c:url value="/resources/libs/img/logo2.png"/>" alt="Logo"></a></div>
		<a href="#0" class="cd-secondary-nav-trigger"><spring:message code="page.menu"/><span></span></a> <!-- button visible on small devices -->
		<nav>
			<ul>
				<li>
					<a href="index" class="animsition-link">
						<b><spring:message code="page.inicio"/></b>
						<span></span><!-- icon -->
					</a>
				</li>
				<c:if test="${role == 'Administrador'}">
				<li>
					<a href="getAllUsuarios" class="animsition-link">
						<b><spring:message code="page.usuarios"/></b>
						<span></span><!-- icon -->
					</a>
				</li>
				<li>
					<a href="getAllSedes" class="animsition-link">
						<b><spring:message code="page.sedes"/></b>
						<span></span><!-- icon -->
					</a>
				</li>
				</c:if>
				<c:if test="${role == 'Responsable'}">
				<li>
					<a href="getAllComensalesHabilitados" class="animsition-link">
						<b><spring:message code="page.comensales"/></b>
						<span></span><!-- icon -->
					</a>
				</li>
				</c:if>
				<c:if test="${role == 'Administrador' && role == 'Responsable'}">
				<li>
					<a href="getAllSugerencias" class="animsition-link">
						<b><spring:message code="page.sugerencia"/></b>
						<span></span><!-- icon -->
					</a>
				</li>
				</c:if>
				<c:if test="${role == 'Comensal'}">
				<li>
					<a href="createSugerencia" class="animsition-link">
						<b><spring:message code="page.sugerencia"/></b>
						<span></span><!-- icon -->
					</a>
				</li>
				</c:if>
				<c:if test="${role == 'Administrador'}">
				<li>
					<a href="menuCartilla" class="animsition-link">
						<b><spring:message code="page.cartilla"/></b>
						<span></span><!-- icon -->
					</a>
				</li>
				</c:if>
				<c:if test="${role == 'Comensal'}">
				<li>
					<a href="selectSedeTicket" class="animsition-link">
						<b><spring:message code="page.compra"/></b>
						<span></span><!-- icon -->
					</a>
				</li>
				</c:if>
				<c:if test="${role == 'Responsable'}">
				<li>
					<a href="menuInformes" class="animsition-link">
						<b><spring:message code="page.informes"/></b>
						<span></span><!-- icon -->
					</a>
				</li>
				</c:if>
			</ul>
		</nav>
	</div> <!-- .cd-secondary-nav -->

</header>

<body>
	<main class="cd-main-content">
		<!-- contenido aca!!! -->
		<jsp:doBody/>
	</main>
</body>

<footer class="footer-distributed">

	<div class="footer-left">

		<img src="<c:url value="/resources/libs/img/UNLP-logo.png"/>">

		<p class="footer-links">
			<a href="#"><spring:message code="page.footer.inicio"/></a>
			
			<a href="#"><spring:message code="page.footer.Servicios"/></a>
			
			<a href="#"><spring:message code="page.footer.Sedes"/></a>
			
			<a href="#"><spring:message code="page.footer.info"/></a>
			
			<a href="#"><spring:message code="page.footer.faq"/></a>
			
			<a href="#"><spring:message code="page.footer.contacto"/></a>
		</p>

		<p class="footer-company-name"><spring:message code="page.titulo"/> &copy; 2015</p>
	</div>

	<div class="footer-center">

		<div>
			<i class="fa fa-map-marker"></i>
			<p><span><spring:message code="page.Sedesubicacion"/></span> La Plata, Argentina</p>

		</div>

		<div>
			<i class="fa fa-phone"></i>
			<p>+54 11 42583247</p>
		</div>

		<div>
			<i class="fa fa-envelope"></i>
			<p><a href="mailto:comedor@unlp.com">comedor@unlp.com</a></p>
		</div>

	</div>

	<div class="footer-right">

		<p class="footer-company-about">
			<span><spring:message code="page.quienesSomos"/></span>
			<spring:message code="page.quienesSomos.parrafo"/>
		</p>

		<div class="footer-icons">

			<a href="#"><i class="fa fa-facebook"></i></a>
			<a href="#"><i class="fa fa-twitter"></i></a>
			<a href="#"><i class="fa fa-linkedin"></i></a>

		</div>

	</div>
</footer>

<script>
$(document).ready(function() {
	$('.animsition').animsition();
});
</script>

<c:if test="${not empty msj}">
<script>
$(document).ready(function() {
	$.notify("${msj}", { globalPosition: 'bottom left', 
							 style: 'bootstrap',
							 className: 'success',
							 showAnimation: 'slideDown',
							 hideAnimation: 'fadeOut',
							 showDuration: 700,
							 hideDuration: 900,
							 autoHideDelay: 4000});
});
</script>
</c:if>

<c:if test="${empty user.nombre}">
  <!-- Modal -->
  <div class="modal fade" id="signIn" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content -->
      <div class="modal-content">
        <div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal">&times;</button>
        	<h3 style="color:white"><span class="glyphicon glyphicon-lock"></span> Login</h3>
        	<br>
        </div>
        <div class="modal-body">
        	<form:form id="UserLoginForm" modelAttribute="usuario" method="post" action="login">
        		<div class="form-group">
        			<label for="dni"><span class="glyphicon glyphicon-user"></span> Documento</label>
        			<form:input type="number" path="dni" cssClass="form-control" value="${usuarioObject.dni}" placeholder="Ingrese su Número de Documento" required="required"/>
        		</div>
        		<div class="form-group">
        			<label for="password"><span class="glyphicon glyphicon-eye-open"></span> Contraseña</label>
        			<form:input type="password" path="password" cssClass="form-control" value="${usuarioObject.password}" placeholder="Ingrese su Contraseña" required="required"/>
        		</div>
        		
        		<button id="login" type="submit" class="btn btn-default btn-success btn-block btn-raised"><span class="glyphicon glyphicon-off"></span> Acceder</button>
        	</form:form>
        </div>
        <div class="modal-footer">
        	<div class="container-fluid">
	        	<div class="row">
		        	<div class="col-md-6">
		        		<button class="btn btn-default btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> <spring:message code="page.login.cancelar"/></button>
		        	</div>
		        	<div class="col-md-6">
		        		<p><spring:message code="page.login.noRecuerda"/> <a href="#"><spring:message code="page.login.password"/></a>?</p>
		        	</div>
	        	</div>
        	</div>
        </div>
      </div>
    </div>
  </div> 
</c:if>

</html>