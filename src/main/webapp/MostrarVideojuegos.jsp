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
<%@ page import ="java.sql.ResultSet"%>
<%@ page import ="java.sql.SQLException"%>
<%
ResultSet rs = null;
	
	try
	{
		rs = Videojuego.buscarTodos();
		
		//recorrer
		while(rs.next())
		{
			%><!-- aqui se meten comandos con el ResultSet y son independientes cada uno -->
			<%= rs.getInt("cve_vid") %>
			<%= rs.getString("tit_vid") %>
			<%= rs.getFloat("pre_vid") %>
			<%= rs.getInt("cveprov_vid") %>
			<%= rs.getInt("inv_vid") %>
			
			<p><a href="#" onClick="alertar;">Click Me</a></p>
			<script type="text/javascript">
				alertar;
			</script>
			<br/>
			<%
		}
		
	}catch(SQLException e)
	{
		System.out.println("Error al acceder a la base de datos"+e.getMessage());
	}
	finally
	{
		if(rs != null) rs.close();
	}
	//response.sendRedirect("MostrarVideojuegos.jsp");
	
	
%>
<!-- Hacer un link para que podamos acceder desde aqui -->
<a href="FormularioInsertarVideojuego.jsp">Inserta Videojuego</a>
</body>
</html>