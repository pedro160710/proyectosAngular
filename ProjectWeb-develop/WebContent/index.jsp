<%@page import="java.util.List"%>
<%@page import="modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<style>
* {
	background-image: url('./imagenes/login.png');
	background-position: 500px 50px;
	background-repeat: no-repeat;
	
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<script src="js/jquery-1.4.1.min.js" type="text/javascript"></script>
<script src="js/jquery.jcarousel.pack.js" type="text/javascript"></script>
<script src="js/jquery-func.js" type="text/javascript"></script>
<title>Login</title>
</head>
<body style="background-color: transparent;">
	<%
		String tituloPagina = "Crear Local";
	%>
	<%-- 	<%@include file="../commons/header.jsp"%> --%>



	<form method="post" action="Login">
		<table >
			<tr style="background-color: transparent; border: none">

				<th style="background-color: transparent; border: none"><input
					style="margin: 295px 0px 0px 700px;" size="10px" type="text"
					name="usuario" /></th>

			</tr>
			<tr style="background-color: transparent; border: none">

				<th style="background-color: transparent; border: none"><input
					style="margin: 7px 0px 0px 700px;" size="10px" type="text"
					name="password" /></th>

			</tr>
			<tr style="background-color: transparent; border: none">

				<th style="background-color: transparent; border: none"><input
					style="margin: 10px 0px 0px 710px;  background: #434647;" type="submit" value="Ingresar"
					class="boton" /></th>

			</tr>
			
			

		</table>

	</form>


</body>
</html>
