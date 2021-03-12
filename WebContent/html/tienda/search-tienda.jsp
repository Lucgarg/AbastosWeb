<%@page import="com.abastos.market.web.util.ParameterNames"%>
<%@page import="com.abastos.market.web.util.ActionNames"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://localhost:8080/abastos/css/estilo.css">
<link rel="stylesheet" media="(min-width: 800px)" href="http://localhost:8080/abastos/css/prueba.css">
<script defer src="http://localhost:8080/abastos/js/script.js"></script>
<script defer src="js/request.js"></script>
</head>
<body>

  <header class="logo">
           <figure><img src="http://localhost:8080/abastos/imgs/logo_Mesa de trabajo 1.jpg" alt=""></figure>
       </header>
       <section class="localizacion">
           <form action="http://localhost:8080/abastos/tienda" method="post">
           <input type="hidden" name=<%=ActionNames.ACTION%> value=<%=ActionNames.BUSCAR%>/>
            <label class="select" for="1">
               <select name=<%=ParameterNames.PROVINCIA%> id="provincia">
                    <option disabled selected value>--selecciona una Provincia--</option>
               </select>
            </label>
            <label class="select" for="localidad">
               <select name="<%=ParameterNames.LOCALIDAD%>" id="localidad">
                    <option disabled selected value>--selecciona una localidad--</option>
                 
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
            <div class="forma">   
           
               <label for="particular">Particular</label>
               <input type="radio" id="particular" name="perfil" checked>
               
               <label for="empresa">Empresa</label>
               <input type="radio" id="empresa" name="perfil">
             
               
           
           <form>
           
               <label for="nomUsu">Nombre de usuario </label><br>
               <input type="text" id="nomUsu" required><br>
               <label for="email">Correo electrónico </label><br>
               <input type="email" id="email" required><br>
               <label for="password">Contraseña </label><br>
               <input type="password" id="password" required><br>
               <label for="password">Repetir Contraseña </label><br>
               <input type="password" id="password"><br>
               <label for="pais">Pais</label><br>
               <input type="text" id="pais"><br>
               <label for="comunidadAutonoma">Comunidad autonoma</label><br>
               <input type="text" id="comunidadAutonoma"><br>
               <label for="provincia">Provincia</label><br>
               <input type="text" id="provincia"><br>
               <label for="localidad">Localidad</label><br>
               <input type="text" id="localidad"><br>
               <label for="codigoPostal">Codigo postal</label><br>
               <input type="text" id="codigoPostal"><br>
               <label for="calle">Calle</label><br>
               <input type="text" id="calle"><br>
               <label for="piso">Piso</label><br>
               <input type="text" id="piso"><br>
               <button type="submit" >Registrarse</button>
           </form>
           <!--forma empresa-->
          
           <form>
        
               <label for="nomTien">Nombre de Tienda </label><br>
               <input type="text" id="nomTien" required><br>
              
               <label>Categoria de la tienda</label><br>
               <select name="categoria" id="categoria">
                   <option value="cat1">ca</option>
                   <option value="cat2">categoria</option>
                   <option value="cat3">categoria</option>
                   <option value="cat4">categoria</option>
                   <option value="cat5">categoria</option>
                   <option value="cat6">categoria</option>
                   <option value="cat7">categoria</option>
                   <option value="cat8">categoria</option>
               </select>
               <label for="nomUsu">Nombre de usuario</label><br>
               <input type="text" id="nomUsu" required><br>
               <label for="email">Correo electrónico </label><br>
               <input type="email" id="email" required><br>
               <label for="telf">Teléfono </label><br>
               <input type="text" id="telf" required><br>
               <label for="mov">Móvil </label><br>
               <input type="text" id="mov" required><br>
               <label for="password">Repetir Contraseña </label><br>
               <input type="password" id="password"><br>
               <label for="pais">Pais</label><br>
               <input type="text" id="pais"><br>
               <label for="comunidadAutonoma">Comunidad autonoma</label><br>
               <input type="text" id="comunidadAutonoma"><br>
               <label for="provincia">Provincia</label><br>
               <input type="text" id="provincia"><br>
               <label for="localidad">Localidad</label><br>
               <input type="text" id="localidad"><br>
               <label for="codigoPostal">Codigo postal</label><br>
               <input type="text" id="codigoPostal"><br>
               <label for="calle">Calle</label><br>
               <input type="text" id="calle"><br>
               <label for="piso">Piso</label><br>
               <input type="text" id="piso"><br>
               <label for="cif">CIF</label><br>
               <input type="text" id="cif"><br>
               <p>Para mantener la calidad y confianza es neccesario verificar la existencia de la empresa.</p>

                <p>Una vez verificados los datos recibira un código en el correo indicado.</p>
               <button type="submit" >Registrarse</button>
               
           </form>
           </div>
           <div class="registro">
			<button class="cerrarLabel"></button>
			<form action="http://localhost:8080/abastos/particular" method="post">
				<input type="hidden" name="action" value="logIn"/>
				<label for="particularLog">Particular</label> <input type="radio"
				id="particularLog" name="perfil" value="1" checked> <label for="empresaLog">Empresa</label>
			<input type="radio" id="empresaLog" value="2" name="perfil"><br>
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
           <figure><img src="http://localhost:8080/abastos/imgs/logo_Mesa de trabajo 1.jpg" alt=""></figure>
           <section>
               <h1>Todos los ayuntamientos</h1>
              
           </section>
            </div>
</body>
</html>