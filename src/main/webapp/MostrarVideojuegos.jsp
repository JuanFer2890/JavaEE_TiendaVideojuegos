<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="js/Validar.js"></script>

	<title>Lista de Videojuegos</title>
</head>
<body>
 <br>
<%@ page import ="mx.com.cursodia.javaEE2022.Beans.Videojuego"%>
<%@ page import ="mx.com.cursodia.javaEE2022.Beans.Proveedor"%>
<%@ page import ="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="FiltrarVideojuegos.do" method = "GET">
	<select name="Proveedor">
		<option value="MostrarTodos">Mostrar todos</option>
		<c:forEach var="Proveedor" items="${listaDeProveedores}">
			<option value="${Proveedor.getCve_prov()}">${Proveedor.getNom_prov()}</option>
		</c:forEach>
	</select>
	<input type="submit" value="Filtrar">
</form>

<%//List<Videojuego> lista = (List<Videojuego>) request.getAttribute("listaDeVideojuegos");%>

<table cellspacing="15">
	<tr>
		<th>Clave</th>
		<th>Titulo</th>
		<th>Precio</th>
		<th>Cve Prov</th>
		<th>Inv</th>
	</tr>
	
	<c:forEach var="V" items="${listaDeVideojuegos}">
		<tr>
			<td>${V.getCve_vid()}</td>
			<td>${V.getTit_vid()}</td>
			<td>${V.getPre_vid()}</td>
			<td>${V.getCveprov_vid()}</td>
			<td>${V.getInv_vid()}</td>
			<td><input type="button" class="edit" value="Editar" onclick="location.href='Editar_NuevoVideojuego.do?CVE=${V.getCve_vid()}'"/></td>
			<td><input type="button" class="delete" value="Borrar" onclick="location.href='SeleccionarJuegoParaBorrar.do?CVE=${V.getCve_vid()}'"/></td>
		</tr>
	</c:forEach>
</table>


		
<!-- Hacer un link para que podamos acceder desde aqui -->
<a href="Editar_NuevoVideojuego.do">Inserta Videojuego</a>
<a href="MostrarProveedores.jsp">Mostrar Proveedores</a>
</body>
</html>