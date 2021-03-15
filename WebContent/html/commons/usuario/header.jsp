<%@page import="com.abastos.market.web.util.UrlBuilder"%>
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
<script defer src="<%=request.getContextPath()%>/js/script.js"></script>
<script defer src="js/request.js"></script>
</head>
<body>
		<header>
		<% String localidad = (String)request.getAttribute(AttributesNames.LOCALIDAD);%>
		<input type="radio" id="null" name="seleccion" checked>
       <input type="radio" id="foNav" name="seleccion">
       <input type="radio" id="registro" name="seleccion">
       <input type="radio" id="Idiomas" name="seleccion">
       <input type="radio" id="logIn" name="seleccion">
       
       <div class="footerNav">
        
           <div>
           	<label primerBloque="a"for=registro>Registrarse</label><label segundoBloque="l" for="logIn">Inicio
				Sesi�n</label><label   for="foNav"><div ></div></label><label  for=null><div tercerBloque="z"></div></label><label
				cuartoBloque="b"for="Idiomas">Idioma</label>
               </div>
           	<div class="tipUsuario">
           	<button class="cerrarLabel"></button>
           		<label>Elige el tipo de perfil</label>
           		<a href=<%=request.getContextPath()%><%=ViewPaths.PRECREATE_ACTION_REGISTRO_EMPRESA%>><button type="button">Empresa</button></a>
           		<a href=<%=request.getContextPath()%><%=ViewPaths.PRECREATE_ACTION_REGISTRO_PARTICULAR%>><button type="button">Particular</button></a>
           	
           	</div>
           <div class="registro">
			<button class="cerrarLabel"></button>
			<form action="<%=request.getContextPath()%>/usuario" method="post">
				<input type="hidden" name="<%=ActionNames.ACTION%>" value="logIn"/>
				<label for="particularLog">Particular</label> <input type="radio"
				id="particularLog" name="tipUsuario" value="particular" checked> <label for="empresaLog">Empresa</label>
			<input type="radio" id="empresaLog" value="empresa" name="tipUsuario"><br>
			<label for="usuario">Usuario o email</label><br>
				 <input
					type="text" name=<%=ParameterNames.NOMBRE_USUARIO%>><br> <label for="password" >Contrase�a</label><br>
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
           <figure><img src="<%=request.getContextPath()%>/imgs/logo_Mesa de trabajo 1.jpg" alt=""></figure>
           <section>
               <h1>Todos los ayuntamientos</h1>
              
           </section>
            </div>
		</header>
 