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
<%@ page import ="mx.com.cursodia.javaEE2022.Beans.Videojuego"%>
<%@ page import ="java.util.List"%>
<%
	
	List<Videojuego> lista = Videojuego.buscarTodos();
	
	//recorrer
	for(Videojuego v:lista)
	{
		%><!-- aqui se meten comandos con el ResultSet y son independientes cada uno -->
		<%= v.getCve_vid() %>
		<%= v.getTit_vid() %>
		<%= v.getPre_vid() %>
		<%= v.getCvepro_vid() %>
		<%= v.getInv_vid() %>
		<input type="button" class="edit" value="Editar" onclick="location.href= 'FormularioInsertarVideojuego.jsp?CVE=<%=v.getCve_vid()%>'"/>
		<br/>
		<%
	}
	
	//response.sendRedirect("MostrarVideojuegos.jsp");
	
	
%>
<!-- Hacer un link para que podamos acceder desde aqui -->
<a href="FormularioInsertarVideojuego.jsp">Inserta Videojuego</a>
</body>
</html>