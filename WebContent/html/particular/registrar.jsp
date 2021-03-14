<%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<header class="sectionTiendas">
		<figure>
			<img src="<%=request.getContextPath()%>/img/logo_Mesa de trabajo 1.jpg" alt="">
		</figure>
		<section>
		</section>
	</header>
<section class="tiendas">
		<div class="tiendas2">
	</section>
	

	<section>
		<nav>
		</nav>
	</section>
       <section class="productoDetalle">
        	<div>
          <form action=<%=request.getContextPath()%><%=ViewPaths.PARTICULAR_ACTION_REGISTRO%> method="post">
          		<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.REGISTRO%>">
          		<fieldset>
          		<label>Nombre usuario</label><br>
          		<input type="text" name="<%=ParameterNames.NOMBRE_USUARIO%>"><br>
          		<label>Apellidos</label><br>
          		<input type="text" name="<%=ParameterNames.APELLIDOS%>"><br>
          		<label>Alias</label><br>
          		<input type="text" name="<%=ParameterNames.AlIAS%>"><br>
          		</fieldset>
          		<fieldset>
          		<label>Contraseña</label>
          		<input type="password" name=<%=ParameterNames.PASSWORD%>>
          			<label>Número de teléfono</label>
          		<input type="text" name=<%=ParameterNames.TELEFONO%>>
          			<labe>Número de móvil</label>
          		<input type="text" name=<%=ParameterNames.MOVIL%>>
          		<label>Correo electrónico</label><br>
          		<input type="email" name="<%=ParameterNames.EMAIL%>"><br>
          		</fieldset>
          		<fieldset>
          		<label>Calle</label><br>
          		<input type="text" name="<%=ParameterNames.CALLE%>"><br>
          		<label>Número</label><br>
          		<input type="text" name="<%=ParameterNames.NUMERO%>"><br>
          		<label>Piso</label><br>
          		<input type="text" name="<%=ParameterNames.PISO%>"><br>
          			<label>Código postal</label><br>
          		<input type="text" name="<%=ParameterNames.CODIGO_POSTAL%>"><br>
          		</fieldset>
          		<fieldset>
          		 <label>Pais</label><br>
                <select  name=<%=ParameterNames.PAIS%> id="pais">
                <option disabled selected value>--selecciona un pais--</option>
                <%List<Pais> paises = (List<Pais>) request.getAttribute(AttributesNames.PAISES); 
                for(Pais p: paises){ %>
                <option value="<%=p.getId()%>"><%=p.getNombre()%></option>
                <%}%>
                </select><br>
              
                <label>Comunidad</label><br>
                 <select name=<%=ParameterNames.COMUNIDAD%> id="comunidad">
                          <option disabled selected value>--selecciona una comunidad--</option>
                </select><br>
                
                 <label>Provincia</label><br>
                 <select name=<%=ParameterNames.PROVINCIA%> id="provincia">
                     
                <option disabled selected value>--selecciona una provincia--</option>
  
                </select><br>
              
                <label>Localidad</label><br>
                <select name=<%=ParameterNames.LOCALIDAD%> id="localidad">
             	           <option disabled selected value>--selecciona una localidad--</option>
            		</select>
            	<label>se enviara un correo al email indicado para confirmar su registro</label><br>
           		<input type="submit" value="registrar"><br>
               
          		</fieldset>
          		 <br><button type="button" class="next">siguiente</button>
          		
          </form>
          </div>
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