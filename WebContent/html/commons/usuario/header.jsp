<%@page import="com.abastos.market.web.util.UrlBuilder, com.abastos.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=UrlBuilder.builder(request, "css/estilo.css")%>">
<link rel="stylesheet" media="(min-width: 800px)" href="<%=UrlBuilder.builder(request, "css/prueba.css")%>">
<script defer src="<%=UrlBuilder.builder(request, "js/script.js")%>"></script>
<script defer src="<%=UrlBuilder.builder(request, "js/request.js")%>"></script>
</head>
<body>
		<header>
		<% Localidad localidad = (Localidad)SessionManager.get(request, AttributesNames.LOCALIDAD);%>
		<input type="radio" id="null" name="seleccion" checked>
       <input type="radio" id="foNav" name="seleccion">
       <input type="radio" id="registro" name="seleccion">
       <input type="radio" id="Idiomas" name="seleccion">
       <input type="radio" id="logIn" name="seleccion">
       
       <div class="footerNav">
       	<%Particular particular = (Particular)SessionManager.get(request,AttributesNames.USUARIO);%>
       	<%Empresa empresa = (Empresa)SessionManager.get(request,AttributesNames.EMPRESA);%>
        <%if(particular !=null){ %>
          <%@include file="/html/commons/particular/particular-view.jsp"%>
        <%}else if(empresa != null){%>
       <%@include file="/html/commons/empresa/empresa-view.jsp"%>
           	<%}else{%>
       <%@include file="/html/commons/usuario/user-view.jsp" %>
           	<%}%>
           	
           	<div class="tipUsuario">
           	<button class="cerrarLabel"></button>
           		<label>Elige el tipo de perfil</label>
           		<a href=<%=UrlBuilder.builder(request, ViewPathsActions.PRECREATE_ACTION_REGISTRO_EMPRESA)%>><button type="button">Empresa</button></a>
           		<a href=<%=UrlBuilder.builder(request, ViewPathsActions.PRECREATE_ACTION_REGISTRO_PARTICULAR)%>><button type="button">Particular</button></a>
           	
           	</div>
           <div class="registro">
			<button class="cerrarLabel"></button>
			<form action="<%=UrlBuilder.builder(request, ViewPathsServlet.USUARIO)%>" method="post">
				<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.LOG_IN%>"/>
				<label for="particularLog">Particular</label> <input type="radio"
				id="particularLog" name="tipUsuario" value="particular" checked> <label for="empresaLog">Empresa</label>
			<input type="radio" id="empresaLog" value="empresa" name="tipUsuario"><br>
			<label for="usuario">Usuario o email</label><br>
				 <input
					type="text" name=<%=ParameterNames.NOMBRE_USUARIO%>><br> <label for="password" >Contraseña</label><br>
				<input type="password" name=<%=ParameterNames.PASSWORD %>><br> <input type="submit">
			</form>
		</div>
           <div class="idiomas">
           <button class="cerrarLabel"></button>
           		<form action=>
           		<label>Selecciona idioma</label><br>
               
               
             	<img src="<%=UrlBuilder.builderImg(request, "img-idioma/spain.svg")%>" id="esp">
               	<img src="<%=UrlBuilder.builderImg(request, "img-idioma/united-kingdom.svg")%>" id="en">
              
          		</form>
           </div>
           <figure><img src="<%=UrlBuilder.builderImg(request, "logo.jpg")%>" alt=""></figure>
           <section>
               <h1><%=localidad != null? localidad.getNombre():"Todas las tiendas"%></h1>
              <form action=<%=UrlBuilder.builder(request, ViewPathsActions.TIENDA_ACTION_BUSCAR)%>>
              <input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.BUSCAR%>">
              	<input type="text" name="<%=ParameterNames.NOMBRE_TIENDA%>">
              	<input type="submit">
              </form>
           </section>
            </div>
		</header>
 