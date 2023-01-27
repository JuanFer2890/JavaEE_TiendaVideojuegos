<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Imports -->	<!--  esto es para insertar codigo de java -->
<%@ page import ="mx.com.cursodia.javaEE2022.Beans.Videojuego"%>


<%

	//Con esto se extraen los datos
	int cve = Integer.parseInt(request.getParameter("CVE"));
	String titulo = request.getParameter("TIT");
	float precio = Float.parseFloat(request.getParameter("PRE"));
	int cveprov = Integer.parseInt(request.getParameter("CVEPROV"));
	int inventario = Integer.parseInt(request.getParameter("INV"));
	
	Videojuego.insertar(cve, titulo, precio, cveprov, inventario);
	
	response.sendRedirect("MostrarVideojuegos.jsp");
%>