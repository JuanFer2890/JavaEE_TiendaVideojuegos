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
<form action="FiltrarVideojuegos.do" method = "GET">
	<select name="Proveedor">
		<option value="MostrarTodos">Mostrar todos</option>
		<%
		List<Proveedor> listaDeProveedores=null;
		listaDeProveedores = (List<Proveedor>) request.getAttribute("listaDeProveedores");
		for(Proveedor prov:listaDeProveedores)
		{
			%> <option value="<%=prov.getCve_prov()%>"><%=prov.getNom_prov() %></option> <%
		}
		%>
	</select>
	<input type="submit" value="Filtrar">
</form>
<%
	
	List<Videojuego> lista = (List<Videojuego>) request.getAttribute("listaDeVideojuegos");
	
	//recorrer
	for(Videojuego v:lista)
	{
		%><!-- aqui se meten comandos con el ResultSet y son independientes cada uno -->
		<%= v.getCve_vid() %>
		<%= v.getTit_vid() %>
		<%= v.getPre_vid() %>
		<%= v.getCvepro_vid() %>
		<%= v.getInv_vid() %>
		<input type="button" class="edit" value="Editar" onclick="location.href='Editar-NuevoVideojuego.do?CVE=<%=v.getCve_vid()%>'"/>
		<input type="button" class="delete" value="Borrar" onclick="location.href='SeleccionarJuegoParaBorrar.do?CVE=<%=v.getCve_vid()%>'"/>
		<br/>
		<%
	}
	
	//response.sendRedirect("MostrarVideojuegos.jsp");
	
	
%>
<!-- Hacer un link para que podamos acceder desde aqui -->
<a href="Editar-NuevoVideojuego.do">Inserta Videojuego</a>
<a href="MostrarProveedores.jsp">Mostrar Proveedores</a>
</body>
</html>