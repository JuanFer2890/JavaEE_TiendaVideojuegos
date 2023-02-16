<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="Error.jsp"%>
<!-- Imports -->	<!--  esto es para insertar codigo de java -->
<%@ page import ="mx.com.cursodia.javaEE2022.Beans.Proveedor"%>
<%@ page import ="mx.com.cursodia.javaEE2022.DataBaseH.DataBaseException"%>

<%
	//Con esto se extraen los datos
	int cve = Integer.parseInt(request.getParameter("CVE"));
	String nombre = request.getParameter("NOM");
	String email = request.getParameter("EMAIL");
	String tel = request.getParameter("TEL");
	
	Proveedor.insertar(cve, nombre, email, tel);
	
	response.sendRedirect("MostrarProveedores.jsp");
%>