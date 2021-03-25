<%@page import="com.abastos.market.web.util.UrlBuilder, com.abastos.model.*, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=UrlBuilder.getUrl(request, "css/estilo.css")%>">
<link rel="stylesheet" media="(min-width: 800px)" href="<%=UrlBuilder.getUrl(request, "css/prueba.css")%>">
<script defer src="<%=UrlBuilder.getUrl(request, "js/script.js")%>"></script>
<script defer src="<%=UrlBuilder.getUrl(request, "js/request.js")%>"></script>
</head>
<body>

<%Carrito carrito = (Carrito)SessionManager.get(request,AttributesNames.CARRITO);%>
		<header>
		<%
		
		
		
		Localidad localidad = (Localidad)SessionManager.get(request, AttributesNames.LOCALIDAD);
		%>
		<input type="radio" id="null" name="seleccion" checked>
       <input type="radio" id="foNav" name="seleccion">
       <input type="radio" id="registro" name="seleccion">
       <input type="radio" id="Idiomas" name="seleccion">
       <input type="radio" id="logIn" name="seleccion">
       
       <div class="footerNav">
       	<%
       	Tienda tienda = (Tienda)SessionManager.get(request, AttributesNames.TIENDA);
       	Particular particular = (Particular)SessionManager.get(request,AttributesNames.USUARIO);
       	%>
       	<%
       	Empresa empresa = (Empresa)SessionManager.get(request,AttributesNames.EMPRESA);
       	%>
        <%
        if(particular !=null){
        %>
          <%@include file="/html/commons/particular/particular-view.jsp"%>
        <%
        }else if(empresa != null){
        %>
       <%@include file="/html/commons/empresa/empresa-view.jsp"%>
           	<%
           	}else{
           	%>
       <%@include file="/html/commons/usuario/user-view.jsp" %>
           	<%
           	}
           	%>
           	
           	<div class="tipUsuario">
           	<button class="cerrarLabel"></button>
           		<label>Elige el tipo de perfil</label>
           		<a href=<%=UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.REGISTRO, ParameterNames.TIP_USUARIO, ActionNames.EMPRESA)%>><button type="button">Empresa</button></a>
           		<a href=<%=UrlBuilder.getUrlForController(request, ControllerPath.PRECREATE, ActionNames.REGISTRO, ParameterNames.TIP_USUARIO, ActionNames.PARTICULAR)%>><button type="button">Particular</button></a>
           	
           	</div>
           <div class="registro">
			<button class="cerrarLabel"></button>
			<form action="<%=UrlBuilder.getUrl(request, ControllerPath.USUARIO)%>" method="post">
				<input type="hidden" name="<%=ParameterNames.URL%>" value="<%=UrlBuilder.urlCallBack(request)%>">
				<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.LOG_IN%>"/>
				<label for="particularLog">Particular</label> <input type="radio"
				id="particularLog" name="tipUsuario" value="particular" checked> <label for="empresaLog">Empresa</label>
			<input type="radio" id="empresaLog" value="empresa" name="tipUsuario"><br>
			<label for="usuario">Usuario o email</label><br>
				 <input
					type="text" name=<%=ParameterNames.NOMBRE_USUARIO%>><br> <label for="password" >Contraseña</label><br>
				<input type="password" name=<%=ParameterNames.PASSWORD%>><br> <input type="submit">
			</form>
		</div>
           <div class="idiomas">
           <button class="cerrarLabel"></button>
           		<form action="<%=UrlBuilder.getUrl(request, ControllerPath.IDIOMA)%>">
           		<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.SELECCIONAR%>">
           		<label>Selecciona idioma</label><br>
               
               
             	<button type="submit" name="<%=ParameterNames.IDIOMA%>" value="es" style="background: url(<%=UrlBuilder.getUrlforImg(request, "img-idioma/spain.svg")%>) center center no-repeat; background-size:contain; font-size:2rem; width: 10%; border: none">&nbsp</button>
               	<button type="submit" name="<%=ParameterNames.IDIOMA%>" value="en" style="background: url(<%=UrlBuilder.getUrlforImg(request, "img-idioma/united-kingdom.svg")%>) center center no-repeat; background-size:contain; font-size:2rem; width: 10%;border:none">&nbsp</button>
              
          		</form>
           </div>
           <figure><img src="<%=UrlBuilder.getUrlforImg(request, "logo.jpg")%>" alt=""></figure>
           <section>
            <%if(tienda != null){%>
               <%@include file="/html/commons/producto/buscador-producto.jsp" %>
               <%}else{%>
                <%@include file="/html/commons/tienda/buscador-tienda.jsp" %>
                <%}%>
           </section>
            </div>
		</header>
 