<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="head" fragment="true" %>

<!DOCTYPE html>
<html class="animsition">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, user-scalable=no">
	
	<title>Comedor Universitario</title>

	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/libs/bootstrap/css/bootstrap.min.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/libs/header/css/style.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/libs/font-awesome/css/font-awesome.min.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/footer.css"/>">
    <link href="<c:url value="/resources/libs/material/css/bootstrap-material-design.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/libs/material/css/ripples.min.css"/>" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/template.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/frontend.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/libs/animsition/css/animsition.min.css"/>">

	<script src="<c:url value="/resources/libs/jQuery/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/libs/header/js/modernizr.js"/>"></script>
	<script src="<c:url value="/resources/libs/bootstrap/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/libs/material/js/ripples.min.js"/>"></script>
    <script src="<c:url value="/resources/libs/material/js/material.min.js"/>"></script>
    <script src="<c:url value="/resources/libs/parallax-nice/scripts/jquery.parallax-1.1.3.js"/>"></script>
	<script src="<c:url value="/resources/libs/parallax-nice/scripts/jquery.localscroll-1.2.7-min.js"/>"></script>
	<script src="<c:url value="/resources/libs/parallax-nice/scripts/jquery.scrollTo-1.4.2-min.js"/>"></script>
	<script src="<c:url value="/resources/libs/animsition/js/animsition.min.js"/>" charset="utf-8"></script>
		

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
				<li><a href="#0">Ingresar</a></li>
				<li><a href="#0">Noticias</a></li>
				<li><a href="#0">Contacto</a></li>
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


	<a data-toggle="modal" href="#signIn" class="cd-btn is-hidden slide-in front">Iniciar sesi&oacute;n</a>
	<div class="cd-secondary-nav is-fixed animate-children">
		<div id="cd-logo2" class="is-hidden slide-in front"><a href="index" class="animsition-link"><img src="<c:url value="/resources/libs/img/logo2.png"/>" alt="Logo"></a></div>
		<a href="#0" class="cd-secondary-nav-trigger">Menu<span></span></a> <!-- button visible on small devices -->
		<nav>
			<ul>
				<li>
					<a href="#cd-placeholder-1" class="animsition-link">
						<b>Intro</b>
						<span></span><!-- icon -->
					</a>
				</li>
				<li>
					<a href="#cd-placeholder-2">
						<b>Location</b>
						<span></span><!-- icon -->
					</a>
				</li>
				<li>
					<a href="#cd-placeholder-3">
						<b>Chat</b>
						<span></span><!-- icon -->
					</a>
				</li>
				<li>
					<a href="#cd-placeholder-4">
						<b>Calendar</b>
						<span></span><!-- icon -->
					</a>
				</li>
				<li>
					<a href="#cd-placeholder-5">
						<b>Stats</b>
						<span></span><!-- icon -->
					</a>
				</li>
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
			<a href="#">Inicio</a>
			
			<a href="#">Servicios</a>
			
			<a href="#">Sedes</a>
			
			<a href="#">+Info</a>
			
			<a href="#">Faq</a>
			
			<a href="#">Contacto</a>
		</p>

		<p class="footer-company-name">Comedor Universitario &copy; 2015</p>
	</div>

	<div class="footer-center">

		<div>
			<i class="fa fa-map-marker"></i>
			<p><span>Ver ubicacion de sedes</span> La Plata, Argentina</p>

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
			<span>Acerca de nosotros</span>
			Lorem ipsum dolor sit amet, consectateur adispicing elit. Fusce euismod convallis velit, eu auctor lacus vehicula sit amet.
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
        	<form role="form" method="post">
        		<div class="form-group">
        			<label for="usuario"><span class="glyphicon glyphicon-user"></span> Usuario</label>
        			<input type="text" class="form-control" id="usuario" name="usuario" placeholder="Ingrese su Email">
        		</div>
        		<div class="form-group">
        			<label for="password"><span class="glyphicon glyphicon-eye-open"></span> Contraseña</label>
        			<input type="text" class="form-control" id="password" name="password" placeholder="Ingrese su Contraseña">
        		</div>
        		<!-- <div class="checkbox">
        		<label><input type="checkbox" value="" checked> Remember me</label>
        		</div> -->
        		<button type="submit" class="btn btn-default btn-success btn-block btn-raised"><span class="glyphicon glyphicon-off"></span> Acceder</button>
        	</form>
        </div>
        <div class="modal-footer">
        	<div class="container-fluid">
	        	<div class="row">
		        	<div class="col-md-6">
		        		<button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
		        	</div>
		        	<div class="col-md-6">
		        		<p>¿No recuerda su <a href="#">Contraseña?</a></p>
		        	</div>
	        	</div>
        	</div>
        </div>
      </div>
    </div>
  </div> 

</html>