<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilo.css">
<link rel="stylesheet" media="(min-width: 800px)" href="<%=request.getContextPath()%>/css/prueba.css">
<script defer src="<%=request.getContextPath()%>/js/script.js"></script>
<script defer src="js/request.js"></script>
</head>
<body>

  <header class="logo">
           <figure><img src="<%=request.getContextPath()%>/imgs/logo_Mesa de trabajo 1.jpg" alt=""></figure>
       </header>
       <section class="localizacion">
           <form action="<%=request.getContextPath()%>/tienda" method="post">
           <input type="hidden" name=<%=ActionNames.ACTION%> value=<%=ActionNames.BUSCAR%>>
            <label class="select" for="pais">
               <select name=<%=ParameterNames.PAIS%> id="pais">
               <%List<Pais> paises = (List<Pais>)request.getAttribute(AttributesNames.PAISES);%>
            		
                    <option value="0">--selecciona un pais--</option>
                    <%for(Pais p: paises){%>
                    <option value=<%=p.getId()%>><%=p.getNombre()%></option>
                    <%}%>
               </select>
            </label>
            <label class="select" for="comunidad">
               <select name=<%=ParameterNames.COMUNIDAD%> id="comunidad">
                    <option value="0">--selecciona una comunidad--</option>
               </select>
            </label>
            <label class="select" for="provincia">
               <select name=<%=ParameterNames.PROVINCIA%> id="provincia">
                    <option value="0">--selecciona una Provincia--</option>
               </select>
            </label>
            <label class="select" for="localidad">
               <select name="<%=ParameterNames.LOCALIDAD%>" id="localidad">
                    <option value="0">--selecciona una localidad--</option>
                 
               </select>
               </label><br>
               <input type="submit" class="Buscar" value="Buscar">
           </form>
       </section>

       <input type="radio" id="null" name="seleccion" checked>
       <input type="radio" id="foNav" name="seleccion">
       <input type="radio" id="registro" name="seleccion">
       <input type="radio" id="Idiomas" name="seleccion">
       <input type="radio" id="logIn" name="seleccion">
       
       <div class="footerNav">
        
           <div>
           	<label primerBloque="a"for=registro>Registrarse</label><label segundoBloque="l" for="logIn">Inicio
				Sesión</label><label   for="foNav"><div ></div></label><label  for=null><div tercerBloque="z"></div></label><label
				cuartoBloque="b"for="Idiomas">Idioma</label>
               </div>
           	<div class="tipUsuario">
           		<label>Elige el tipo de perfil</label><br>
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
					type="text" name=<%=ParameterNames.NOMBRE_USUARIO%>><br> <label for="password" >Contraseña</label><br>
				<input type="password" name=<%=ParameterNames.PASSWORD %>><br> <input type="submit">
			</form>
		</div>
           <div class="idiomas">
               <label for="espanol">Español</label>
               <input type="radio" id="espanol" name="idioma">
               <label for="ingles">Inglés</label>
               <input type="radio" id="ingles" name="idioma">
           </div>
           <figure><img src="<%=request.getContextPath()%>/imgs/logo_Mesa de trabajo 1.jpg" alt=""></figure>
           <section>
               <h1>Todos los ayuntamientos</h1>
              
           </section>
            </div>
</body>
</html>