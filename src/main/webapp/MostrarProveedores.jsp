<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="js/Validar.js"></script>

	<title>Lista de Videojuegos</title>
</head>
<body>
<select name="comboBox" id="proveedores">
    <option value="todos">Todos</option>
 </select>
 <br>
<%@ page import ="mx.com.cursodia.javaEE2022.Beans.Proveedor"%>
<%@ page import ="java.util.List"%>
<%
	
	List<Proveedor> lista = Proveedor.buscarTodos();
	
	//recorrer
	for(Proveedor v:lista)
	{
		%><!-- aqui se meten comandos con el ResultSet y son independientes cada uno -->
		<%= v.getNom_prov() %>
		<%= v.getEmail_prov() %>
		<%= v.getTel_prov() %>
		<%= v.getCve_prov() %>
		<input type="button" class="edit" value="Editar" onclick="location.href= 'FormularioInsertarVideojuego.jsp?CVE=<%=v.getCve_prov()%>'"/>
		<input type="button" class="delete" value="Borrar" onclick="alertar();"/>
		<br/>
		<%
	}
	
	//response.sendRedirect("MostrarVideojuegos.jsp");
	
	
%>
<!-- Hacer un link para que podamos acceder desde aqui -->
<a href="FormularioInsertarProveedor.jsp">Inserta Proveedor</a>
<a href="MostrarVideojuegos.do">Mostrar Videojuegos</a>
</body>
</html>