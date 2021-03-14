<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.market.web.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilo.css">
<link rel="stylesheet" media="(min-width: 800px)" href="<%=request.getContextPath()%>/css/prueba.css">
<script defer src="js/script.js"></script>
</head>
<body>
	<header class="sectionTiendas">
		<figure>
			<img src="<%=request.getContextPath()%>/img/logo_Mesa de trabajo 1.jpg" alt="">
		</figure>
		<section>
		<% String localidad = (String)request.getAttribute(AttributesNames.LOCALIDAD);%>
			<h1>Todos los ayuntamientos</h1>
			<form action="<%=request.getContextPath()%>/tienda" method="post">
			<input type="hidden" name=<%=ParameterNames.LOCALIDAD%> value=<%=localidad%>>
			<input type="hidden" name=<%=ActionNames.ACTION %> value=<%=ActionNames.BUSCAR%>/>
			<input type="text" name="nombre">
			<input class="Buscar header" type="submit" value="buscar">
			</form>
		</section>
	</header>
	<section class="tiendas">
		<div class="tiendas2">
	</section>
	<section>
		<nav>
			<label class="menu" for="menu"> <input type="checkbox" />
				<div></div>
				<ul>
				
					<%List<Categoria> catServ = (List<Categoria>)request.getAttribute(AttributesNames.CATEGORIAS);%>
					<%for(Categoria cat: catServ){%>
					
					<li><a href="<%=request.getContextPath()%><%=ViewPaths.TIENDA_ACTION_BUSCAR%><%=cat.getId()%>&localidad=<%=localidad%>"><%=cat.getNombre()%></a></li>
					<%}%>
					<form action="<%=request.getContextPath()%>/tienda" method="post">
					<input type="hidden" name=<%=ActionNames.ACTION%> value=<%=ActionNames.BUSCAR%>/>
					
					<input type="hidden" name=<%=ActionNames.ACTION%> value=<%=localidad%>>
						<label>Envio a domicilio</label>
						<input type="checkbox" name=<%=ParameterNames.ENVIO_DOMICILIO%> value="true">
						<h5>Categoria</h5>
						<select name=<%=ParameterNames.CATEGORIA%>>
							<option disabled selected>todas las categorias</option>
							<option value="1">Deportes</option>
							<option value="2">Alimentación</option>
							<option value="3">Juguetes</option>
							<option value="4">Droguería y salud</option>
							<option value="5">Moda y accesorios</option>
							<option value="6">Mascotas</option>
							<option value="7">Electrónica e informática</option>
							<option value="8">casa y jardín</option>
						</select>
					
						<input type="submit"
							name="buscar" value="buscar">
					</form>
				</ul>
			</label>
		</nav>
	</section>
	<section class="tiendas producto">
		<div class="tiendas2">
		<%
		List<Tienda> results = (List<Tienda>) request.getAttribute(AttributesNames.RESULTS_TIENDA);
		%>
		<%
		for(Tienda t : results){
			%>
		
			<div>
				<figure>
					<a href="productos.html"><img src="<%=request.getContextPath()%>/imgs/tiendas/<%=t.getId()%>-principal.jpg" alt="">
				</figure>
				<p>
					<a href="<%=request.getContextPath()%><%=ViewPaths.TIENDA_ACTION_DETALLE%><%=t.getId()%>"><%=t.getNombre()%></a>
				</p>
				<p><%=t.getDireccionDto().getLocalidad()%></p>
				<span><%=t.getPuntuacionMedia().getValoracionMedia()%></span>
			</div>
			<% 
			}
		%>
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
			<label for="espanol">Español</label> <input type="radio" id="espanol"
				name="idioma"> <label for="ingles">Inglés</label> <input
				type="radio" id="ingles" name="idioma">
		</div>
		<figure>
			<img src="<%=request.getContextPath()%>/imgs/logo_Mesa de trabajo 1.jpg" alt="">
		</figure>
		<section>
			<h1>Todos los ayuntamientos</h1>
			<form action="<%=request.getContextPath()%>/tienda" method="post">
			<input type="hidden" name="localidad" value=<%=localidad%>>
			<input type="hidden" name="action" value="buscar"/>
			<input type="text" name="nombre">
			<input class="Buscar header" type="submit" value="buscar">
			</form>
		</section>
	</div>



</body>
</html>