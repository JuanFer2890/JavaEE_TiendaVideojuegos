<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html = lang "es">

		<head>
		<script type="text/javascript" src="js/Validar.js"></script>
		<link rel="stylesheet" href="css/Fuente.css">
		
		<title>Formulario Alta Proveedor</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
		<meta charset="utf-8"/>
	</head>
	<body>
		<%@ page import= "java.util.Objects"%>
		<%@ page import= "java.util.ArrayList" %>
		<%@ page import ="mx.com.cursodia.javaEE2022.Beans.Proveedor"%>
		<%	
			String clave = request.getParameter("CVE");
			if(Objects.isNull(clave))
			{
				%>
				<div class="container-pt-4">
				<div class="row">
				<div class="card text-center">
					<div class="card-body">
					<h1>Formulario alta de nuevo Proveedor</h1>
					<form action ="InsertarProveedor.do" method="GET" Class="mt-4">
						<div class="row">
							<label for="CVE">Clave</label>
							<input type="clave" class="form-control" id = "CVE" placeholder="Clave del proveedor">
						</div>
						<div class="row">
							<label for="NOM">Nombre</label>
							<input type="nombre" class="form-control" id = "NOM" placeholder="Nombre del proveedor">
						</div>
						<div class="row">
							<label for="EMAIL">Email</label>
							<input type="correoElectronico" class="form-control" id = "EMAIL" placeholder="Correo del proveedor">
						</div>
						<div class="row">
							<label for="TEL">Telefono</label>
							<input type="telefono" class="form-control" id = "TEL" placeholder="Telefono del proveedor">
						</div>
						<%%>
						<button type="submit" class="btn btn-primary" onclick="validarProv(); ">Guardar</button>
						
					</form>
					</div>
				</div>
			</div>
			<%
			}
			else
			{
				int cve = Integer.parseInt(clave);
				//Videojuego V = Videojuego.seleccionarVideojuego(cve);
				%><p>La clave es: </p><%=cve%> <%
			}
		 %>
		</div>
	</body>
</html>