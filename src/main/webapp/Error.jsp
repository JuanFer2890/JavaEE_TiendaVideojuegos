<%@ page isErrorPage="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html = lang "es">
<head>
<meta charset="UTF-8">
<title>Uh oh...</title>
</head>
<body>
Ha ocurrido un error en la aplicacion: <%=exception.getMessage() %>
Error interno: <%=exception.getCause().getMessage() %>
</body>
</html>