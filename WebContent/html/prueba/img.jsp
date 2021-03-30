<%@page import="com.abastos.market.web.util.UrlBuilder"%>
<%@page import="com.abastos.market.web.util.ControllerPath"%>
<%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=UrlBuilder.getUrl(request,ControllerPath.IMAGEN)%>" 
	enctype="multipart/form-data" method="post">
	
	
<input type="file" id="img" name="img" accept="image/*">
	<input type="submit">
	</form>
</body>
</html>