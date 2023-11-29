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
		<%@ page import= "java.util.Objects"%>
		<%@ page import= "java.util.List" %>
		<%@ page import ="java.util.stream.Collectors"%>
		<%@ page import ="mx.com.cursodia.javaEE2022.Beans.Videojuego"%>
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
					<h1>Formulario alta de nuevo Videojuego</h1>
					<form action ="EjecutarInserccionVideojuego.do" method="GET" Class="mt-4">
						<div class="row">
							<label for="CVE">Clave</label>
							<input type="clave" class="form-control" name="CVE" id = "CVE" placeholder="Clave del videojuego">
						</div>
						<div class="row">
							<label for="TIT">Titulo</label>
							<input type="titulo" class="form-control" name = "TIT" id = "TIT" placeholder="Titulo del videojuego">
						</div>
						<div class="row">
							<label for="PRE">Precio</label>
							<input type="precio" class="form-control" name = "PRE" id = "PRE" placeholder="Precio del titulo">
						</div>
						<div>
							<select name="CVEPROV" id="CVEPROV">
							<%
							//STREAM PARA TRAER SOLAMENTE LOS NOMBRES
								
								List<Proveedor> lista = (List<Proveedor>) request.getAttribute("listaDeProveedores");
								for(Proveedor v:lista)
								{%>
									<option value="<%=v.getCve_prov()%>"><%=v.getNom_prov()%></option>
								<%}
									
							%>
							</select>
						</div>
						<div class="row">
							<label for="INV">Inventario</label>
							<input type="inventario" class="form-control" name = "INV" id = "INV" placeholder="Stock del producto">
						</div>
						<%%>
						<button type="submit" class="btn btn-primary">Guardar</button>
						
					</form>
					</div>
				</div>
			</div>
			<%
			}
			else
			{
				int cve = Integer.parseInt(clave);
				Videojuego V = Videojuego.seleccionarVideojuego(cve);
				%>
				<div class="container-pt-4">
				<div class="row">
				<div class="card text-center">
					<div class="card-body">
					<h1>Formulario Modificar Videojuego</h1>
					<form action ="EjecutarInserccionVideojuego.do?MOD&CVE=<%=V.getCve_vid()%>&TIT=<%=V.getTit_vid()%>&PRE=<%=V.getPre_vid()%>&CVEPROV=<%=V.getCveprov_vid()%>&INV=<%=V.getInv_vid()%>" method="GET" Class="mt-4">
						<input type="hidden" name="MOD" value="<%=true%>"><!-- Esto es para asignar un valor true a MOD -->
						<div class="row">
							<label for="CVE">Clave</label>
							<input type="clave" class="form-control" name="CVE" id = "CVE" placeholder="Clave del videojuego" value = "<%=V.getCve_vid()%>">
						</div>
						<div class="row">
							<label for="TIT">Titulo</label>
							<input type="titulo" class="form-control" name = "TIT" id = "TIT" placeholder="Titulo del videojuego" value = "<%=V.getTit_vid()%>">
						</div>
						<div class="row">
							<label for="PRE">Precio</label>
							<input type="precio" class="form-control" name = "PRE" id = "PRE" placeholder="Precio del titulo" value = "<%=V.getPre_vid()%>">
						</div>
						<div>
							<select name="CVEPROV" id="CVEPROV">
							<%
							//STREAM PARA TRAER SOLAMENTE LOS NOMBRES
								
								List<Proveedor> lista = (List<Proveedor>) request.getAttribute("listaDeProveedores");
								for(Proveedor v:lista)
								{%>
									<option value="<%=v.getCve_prov()%>"><%=v.getNom_prov()%></option>
								<%}
									
							%>
							</select>
						</div>
						<div class="row">
							<label for="INV">Inventario</label>
							<input type="inventario" class="form-control" name = "INV" id = "INV" placeholder="Stock del producto" value = "<%=V.getInv_vid()%>">
						</div>
						<button type="submit" class="btn btn-primary">Actualizar</button>
						<!--<button type="submit" class="btn btn-primary" onclick="actualizar(); ">Actualizar</button> -->
						
					</form>
					</div>
				</div>
			</div>
				
				
				<%
			}
		 %>
		</div>
	</body>
</html>