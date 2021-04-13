
	  <%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=UrlBuilder.getUrl(request, "css/estilo.css")%>">
<link rel="stylesheet" media="(min-width: 800px)" href="<%=UrlBuilder.getUrl(request, "css/desktop.css")%>">

</head>
<body>
	<div class="logo">
	<figure>
		<img src="<%=UrlBuilder.getUrlforImg(request, "logo.jpg")%>" alt="">
	</figure>
</div>
       <section class="localizacion">
     
       <p class="error_warn">Upss! lo sentimos no hemos sido capaces de encontrar lo que buscaba</p>
       </section>
</body>