<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html class="animsition">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, user-scalable=no">
	<!-- -->
	<title><spring:message code="page.titulo"/></title>

	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/libs/bootstrap/css/bootstrap.min.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/frontend.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/libs/font-awesome/css/font-awesome.min.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/footer.css"/>">
    <link href="<c:url value="/resources/libs/material/css/bootstrap-material-design.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/libs/material/css/ripples.min.css"/>" rel="stylesheet">
	<link rel="stylesheet" href="<c:url value="/resources/libs/header/css/style.css"/>">
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
	
	<script src="<c:url value="/resources/js/sugerencia.js"/>"></script>

	<script src="<c:url value="/resources/libs/header/js/main.js"/>"></script>
    <script>
        $(document).ready(function() {
            $.material.init();
            $('#cd-intro').parallax("50%", 0.2);
        });

    </script>
</head>

<header>
	<div class="cd-header">
		<div id="cd-logo"><a href="index" class="animsition-link"><img src="<c:url value="/resources/libs/img/logo2.png"/>" alt="Logo"></a></div>
		<nav class="cd-primary-nav">
			<ul>
				<!-- inser more links here -->
				<c:if test="${empty user.nombre}">
				</c:if>
				<c:choose>
				    <c:when test="${empty user.nombre}">
						<li><a data-toggle="modal" href="#signIn"><spring:message code="page.ingresar"/></a></li>
				    </c:when>
				    <c:otherwise>
				    	<li><a href="#0"><spring:message code="page.perfil"/></a></li>
				    </c:otherwise>	
				</c:choose>
				<li><a href="#0"><spring:message code="page.noticia"/></a></li>
				<li><a href="#0"><spring:message code="page.contacto"/></a></li>
				<li><a href="?language=es_AR">Español</a></li>
				<li><a href="?language=en_CA">Ingles</a></li>
			</ul>
		</nav> <!-- cd-primary-nav -->
	</div>
	<div id="cd-intro">
		<div id="cd-intro-tagline">
			<c:choose>
			    <c:when test="${empty user.nombre}">
					<h1><spring:message code="page.inisesion"/></h1>
					<a id="login-btn" class="cd-btn" data-toggle="modal" href="#signIn"><spring:message code="page.ingresar"/></a>
			    </c:when>
			    <c:otherwise>
					<h1><spring:message code="page.bienvenida"/>, ${user.nombre}!</h1>
					<c:choose>
					    <c:when test="${role == 'Administrador'}">
							<h4><spring:message code="page.administrador"/></h4>
					    </c:when>
					    <c:when test="${role == 'Responsable'}">
							<h4><spring:message code="page.responsable"/></h4>
					    </c:when>
					    <c:otherwise>
					    </c:otherwise>	
					</c:choose>
					<p>(<a href="goodbye" class="animsition-link"><spring:message code="page.salir"/></a>)</p>
					<p class="cd-btn is-hidden withoutBG">¡<spring:message code="page.bienvenida"/>!, ${user.nombre}! (<a href="goodbye" class="animsition-link"><spring:message code="page.salir"/></a>)</p>	    	
			    </c:otherwise>
			</c:choose>
		</div>
	</div>
	
	<div class="cd-secondary-nav">
		<a href="#0" class="cd-secondary-nav-trigger"><spring:message code="page.menu"/><span></span></a> <!-- button visible on small devices -->
		<nav>
			<ul>
				<c:if test="${role == 'Administrador'}">
				<li>
					<a href="getAllUsuarios" class="animsition-link">
						<b><spring:message code="page.sugerencia"/></b>
						<span></span><!-- icon -->
					</a>
				</li>
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
					<b>
					 <a class="animsition-link" href="createSugerencia" >Enviar Sugerencia</a>
					</b>
  
				</li>
				</c:if>
				<li>
					<a href="#cd-placeholder-3" class="animsition-link">
						<b><spring:message code="page.sugerencia"/></b>
						<span></span><!-- icon -->
					</a>
				</li>
				<li>
					<a href="menuCartilla" class="animsition-link">
						<b><spring:message code="page.cartilla"/></b>
						<span></span><!-- icon -->
					</a>
				</li>
				<li>
					<a href="#cd-placeholder-5" class="animsition-link">
						<b><spring:message code="page.estadistica"/></b>
						<span></span><!-- icon -->
					</a>
				</li>
			</ul>
		</nav>
	</div> <!-- .cd-secondary-nav -->
</header>

<body>
	<main class="cd-main-content">
		<div class="row">
			<div class="row option1">
				<div class="col-md-8"></div>
				<div class="col-md-4">
					<div class="container-fluid">
					<h1><spring:message code="page.quienesSomos"/></h1>
					<p><spring:message code="page.quienesSomos.parrafo"/></p>
					</div>
				</div>
			</div>
			<div class="row option4">
				<div class="col-md-8"></div>
				<div class="col-md-4">
					<div class="container-fluid">
					<h1><spring:message code="page.noticia"/></h1>
					<a class="twitter-timeline" href="https://twitter.com/pruebaComedor" data-widget-id="667197478299697152"  width="700" height="100">>Tweets por el @pruebaComedor.</a>
					<script>!function(d,s,id)
					{var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';
					if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
					</div>
				</div>
			</div>
			<div class="row option2">
				<div class="col-md-8"></div>
				<div class="col-md-4">
					<div class="container-fluid">
					<h1><spring:message code="page.historia"/></h1>
					<p><spring:message code="page.historia.parrafo"/></p>
					</div>
				</div>
			</div>
		
			<div class="row option3">
				<div class="col-md-8"></div>
				<div class="col-md-4">
					<div class="container-fluid">
					<h1><spring:message code="page.nuestrasSedes"/></h1>
					<p><spring:message code="page.nuestrasSedes.parrafo"/></p>
					</div>
				</div>
			</div>
		</div>
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

<c:if test="${not empty error}">
<script>
$(window).load(function(){
	$("#error-modal").modal();
});
</script>
<div id="error-modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content -->
      <div class="modal-content">
        <div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal">&times;</button>
        	<h3><span class="glyphicon glyphicon-remove-sign"></span><spring:message code="page.login.error"/></h3>
        </div>
        <div class="modal-body">
			<p><spring:message code="page.login.error.feedback"/></p>
        </div>
        <div class="modal-footer">
        </div>
      </div>
    </div>
  </div> 
</c:if>

<c:if test="${empty user.nombre}">
  <!-- Modal -->
  <div class="modal fade" id="signIn" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content -->
      <div class="modal-content">
        <div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal">&times;</button>
        	<h3 style="color:white"><span class="glyphicon glyphicon-lock"></span> <spring:message code="page.login.ingresar"/></h3>
        	<br>
        </div>
        <div class="modal-body">
        	<form:form id="UserLoginForm" modelAttribute="usuario" method="post" action="login">
        		<div class="form-group">
        			<spring:message code="page.login.placeHolder.usuario" var="placeHolderU"/>
        			
        			<label for="dni"><span class="glyphicon glyphicon-user"></span> <spring:message code="page.login.documento"/></label>
        			<form:input type="number" path="dni" cssClass="form-control" value="${usuarioObject.dni}" placeholder="${placeHolderU}"  required="required"/>
        		</div>
        		<div class="form-group">
        			<spring:message code="page.login.placeHolder.password" var="placeHolderPass"/>
        			
        			<label for="password"><span class="glyphicon glyphicon-eye-open"></span> <spring:message code="page.login.password"/></label>
        			<form:input type="password" path="password" cssClass="form-control" value="${usuarioObject.password}" placeholder="${placeHolderPass}" required="required"/>
        		</div>
        		
        		<button id="login" type="submit" class="btn btn-default btn-success btn-block btn-raised"><span class="glyphicon glyphicon-off"></span> <spring:message code="page.login.ingresar"/></button>
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
 <div class="modal fade" id="sugerenciaModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content -->
      <div class="modal-content">
        <div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal">&times;</button>
        	<h3 style="color:white"><span> Sugerencia </span></h3>
        	<br>
        </div>
        <div class="modal-body">
        	<form:form id="UserLoginForm" modelAttribute="usuario" method="post" action="login">
	        	<label class="control-label" for="sugerencia"> ingrese su sugerencia</label>
					<form:textarea cssClass="form-control" id="sugerencia" path="nombre" rows="10" cols="30" />	
					<p class="help-block">Escriba una sugerencia para el comedor univeristario</p>
				</div>
				<div class="container-fluid">
					<input class="btn btn-block btn-danger btn-raised" type="submit" value="Enviar" id="saveUser">
				</div>
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

</html>