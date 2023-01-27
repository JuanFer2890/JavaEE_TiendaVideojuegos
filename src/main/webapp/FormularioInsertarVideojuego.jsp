<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html = lang "es">

		<head>
		<script type="text/javascript" src="js/Validar.js"></script>
		<link rel="stylesheet" href="css/Fuente.css">
		
		<title>Formulario Alta Videojuego</title>
		 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
		<meta charset="utf-8"/>
	</head>
	<body>
		<div class="container-pt-4">
			<div class="row">
				<div class="card text-center">
					<div class="card-body">
					<h1>Formulario alta de nuevo Videojuego</h1>
					<form action ="InsertarVideojuego.do" method="GET" Class="mt-4">
						<div class="row">
							<label for="CVE">Clave</label>
							<input type="clave" class="form-control" id = "CVE" placeholder="Clave del videojuego">
						</div>
						<div class="row">
							<label for="TIT">Titulo</label>
							<input type="titulo" class="form-control" id = "TIT" placeholder="Titulo del videojuego">
						</div>
						<div class="row">
							<label for="PRE">Precio</label>
							<input type="precio" class="form-control" id = "PRE" placeholder="Precio del titulo">
						</div>
						<div class="row">
							<label for="CVEPROV">Proveedor</label>
							<input type="claveProveedor" class="form-control" id = "CVEPROV" placeholder="Proveedor del videojuego">
						</div>
						<div class="row">
							<label for="INV">Inventario</label>
							<input type="inventario" class="form-control" id = "INV" placeholder="Stock del producto">
						</div>
						<button type="submit" class="btn btn-primary" onclick="validar();">Guardar</button>
						
					</form>
					</div>
				</div>
			</div>
		</div>
		<!--
		<h1>Formulario alta de nuevo Videojuego</h1>
		<form name="PrimerFormulario">
			CLAVE: 
			<input type="text" name = "cve"><br><br>
			TITULO:
			<input type="text" name = "titulo"><br><br>
			PRECIO:
			<input type="text" name = "precio"><br><br>
			<input type="button" name="boton" value = "Insertar" onclick="validar()">
		</form>-->
	</body>
</html>