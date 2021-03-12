<%@page import="com.abastos.market.web.util.ParameterNames"%>
<%@page import="com.abastos.market.web.util.AttributesNames"%>
<%@page import="com.abastos.market.web.util.ActionNames"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.service.impl.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://localhost:8080/abastos/css/estilo.css">
<link rel="stylesheet" media="(min-width: 800px)"
	href="http://localhost:8080/abastos/css/prueba.css">
<script defer src="js/script.js"></script>
</head>
<body>
	<header class="sectionTiendas">
		<figure>
			<img
				src="http://localhost:8080/abastos/imgs/logo_Mesa de trabajo 1.jpg"
				alt="">
		</figure>
		<section>
			<h1>Todos los ayuntamientos</h1>
			<form action="http://localhost:8080/abastos/tienda" method="post">
				<input type="hidden" name=<%=ActionNames.ACTION%> value=<%=ActionNames.BUSCAR%> /> <input
					type="text" name="nombre"> <input class="Buscar header"
					type="submit" value="buscar">
			</form>
		</section>
	</header>
	<section class="tiendas">
		<div class="tiendas2">
			<input type="checkbox">
			<%Tienda tienda = (Tienda)request.getAttribute(AttributesNames.TIENDA);%>
			<h1><%=tienda.getNombre()%></h1>

			<div>
				<figure>
					<img src="http://localhost:8080/abastos/imgs/tiendas/<%=tienda.getId()%>-principal.jpg" alt="">
					<figcaption>
						<span><%=tienda.getPuntuacionMedia().getValoracionMedia()%></span>
					</figcaption>
				</figure>

				<div class=informacionTienda>
					<p>
						Jardin y hogar<br>
						<%=tienda.getDireccionDto().getCalle()%><br>
						<%=tienda.getDireccionDto().getPiso()%><br>
						<%=tienda.getNumeroMovil()%><br>
						<%=tienda.getNumeroTelefono()%><br>
						<%=tienda.getEmail()%></p>
					<label class="forma">Atencion al cliente
						<span><%=tienda.getPuntuacionMedia().getValoracionAtncClienteMedia()%></span>
					</label> <label class="forma">Servicio a domicilio
						<span><%=tienda.getPuntuacionMedia().getValoracionServDomicilioMedia()%></span>
					<label class="forma">Precios
						<span><%=tienda.getPuntuacionMedia().getValoracionPrecioMedia()%></span>
					</label>
				</div>

			</div>
	</section>
	<section>
		<nav>
			<label class="menu" for="menu"> <input type="checkbox" />
				<div></div>
				<ul>
					<%List<Categoria> cat = (List<Categoria>)request.getAttribute(AttributesNames.CATEGORIAS);%>
					<%for(Categoria c: cat) {%>
					
					<li><a href="http://localhost:8080/abastos/producto?action=buscar&categoria=<%=c.getId()%>&idTienda=<%=tienda.getId()%>"><%=c.getNombre()%></a>
					<%if(c.getCategorias().size() > 0) {%>
					<ol><% }%>
					<%for(Categoria categoriaProducto: c.getCategorias()) {%>
						
							<li><a href="http://localhost:8080/abastos/producto?action=buscar&categoria=<%=categoriaProducto.getId()%>&idTienda=<%=tienda.getId()%>"><%=categoriaProducto.getNombre()%></a></li>
							<% }
							if(c.getCategorias().size() > 0){%>
							</ol><%}%>
							
							</li>
							<%} %>
					
					<form action="http://localhost:8080/abastos/producto" method="post">
						<input type="hidden" name=<%=ActionNames.ACTION%> value=<%=ActionNames.BUSCAR%> /> <input
							type="text" name=<%=ParameterNames.PRECIO_DESDE%> placeholder="predioDesde"><br>
						<input type="text" name=<%=ParameterNames.PRECIO_HASTA%> placeholder="precioHasta"><br>
						<h5>origen</h5>
						<label for="origenN">Nacional</label> <input type="radio"
							name=<%=ParameterNames.ORIGEN%> id="origenN" value="N"><br> <label
							for="origenI">Internacional</label> <input type="radio"
							name=<%=ParameterNames.ORIGEN%> id="origenI" value="I"><br> <label
							for="origenL">Local</label> <input type="radio" name=<%=ParameterNames.ORIGEN%>
							id="origenL" value="L"><br>

						<h5>Oferta</h5>
						<label for="true">si</label> <input type="checkbox" name=<%=ParameterNames.OFERTA%>
							value="true">

						<h5>Idioma</h5>
						<select name=<%=ParameterNames.IDIOMA%>>
							<option value="es">español</option>
							<option value="en">inglés</option>

						</select><br> <input type="hidden" name=<%=ParameterNames.ID_TIENDA%>
							value=<%=tienda.getId()%>> <input type="submit"
							name="buscar" value="buscar">
					</form>
				</ul>
			</label>
		</nav>
	</section>
	<section class="tiendas producto">
		<div class="tiendas2">
			<%
		List<Producto> results = (List<Producto>) request.getAttribute(AttributesNames.PRODUCTO);
		
		for(Producto p : results){
			%>

			<div>
				<figure>
					<img src="http://localhost:8080/abastos/imgs/productos/<%=p.getId()%>-principal.jpg" />
				</figure>
				<p>
					<a
						href="http://localhost:8080/abastos/producto?action=detalle&id=<%=p.getId()%>&idTienda=<%=tienda.getId()%>"><%=p.getNombre()%></a>
				</p>
				
				<%ProductoService producServ = new ProductoServiceImpl();%>
				
				<%if(p.getOferta() != null){
					if(p.getOferta().getIdTipoOferta()==1){%>
				<p class="precio"><%=p.getPrecio()%></p>
				<%}else{%>
				<p class="precioNoMostrado"></p>  
				<%}%>
						<div class="oferta">
						<p>oferta</p>
					 </div>
					 
					 <div class="ofertaDetalle">
					 <p><%=p.getOferta().getNombreOferta()%></p>
					 <%if(p.getOferta().getIdTipoOferta() == 1){ %>
					 	<p>-<%if(p.getOferta().getDescuentoFijo() !=0.0){%>
					 			<%=p.getOferta().getDescuentoFijo()%>
					 			&euro;
					 			  <%}else{%>
					 				<%=p.getOferta().getDescuentoPcn()%> %
					 <%}%></p>
					 	<%}
					 if(p.getOferta().getIdTipoOferta() == 2){ %>
					 	<p><%=p.getOferta().getDenominador()%>
					 	 unidad -
					 			<%if(p.getOferta().getDescuentoFijo() != 0.0){%>
					 			<%=p.getOferta().getDescuentoFijo()%>
					 			 &euro;
					 			  <%}else{%>
					 				<%=p.getOferta().getDescuentoPcn()%> %
					 				
					 <%}%>
					 </p>
					 <%}
					 if(p.getOferta().getIdTipoOferta() == 3){%>
					 	<p>Compra y ahorrate del producto
					 	<%=producServ.findById(p.getOferta().getIdProdOferta(), "es").getNombre()%> 
					 	
					 			<%if(p.getOferta().getDescuentoFijo() != 0.0){%>
					 			<%=p.getOferta().getDescuentoFijo()%>
					 			 &euro;
					 			<%}else{%>
					 				<%=p.getOferta().getDescuentoPcn()%> %
					 <%}%></p>
					 <%}%>
					 </div>
				 <%}%>
				
					<p><%=p.getPrecioFinal()%></p>
					<p class="carritoCompra"></p>
				<span><%=p.getValoracion()%></span>
			</div>
			<%}%>
		
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
		<div class="forma">

			<label for="particular">Particular</label> <input type="radio"
				id="particular" name="perfil" checked> <label for="empresa">Empresa</label>
			<input type="radio" id="empresa" name="perfil">


			<button class="cerrarLabel"></button>

			<form>

				<label for="nomUsu">Nombre de usuario </label><br> <input
					type="text" id="nomUsu" required><br> <label
					for="email">Correo electrónico </label><br> <input
					type="email" id="email" required><br> <label
					for="password">Contraseña </label><br> <input type="password"
					id="password" required><br> <label for="password">Repetir
					Contraseña </label><br> <input type="password" id="password"><br>
				<label for="pais">Pais</label><br> <input type="text" id="pais"><br>
				<label for="comunidadAutonoma">Comunidad autonoma</label><br> <input
					type="text" id="comunidadAutonoma"><br> <label
					for="provincia">Provincia</label><br> <input type="text"
					id="provincia"><br> <label for="localidad">Localidad</label><br>
				<input type="text" id="localidad"><br> <label
					for="codigoPostal">Codigo postal</label><br> <input
					type="text" id="codigoPostal"><br> <label for="calle">Calle</label><br>
				<input type="text" id="calle"><br> <label for="piso">Piso</label><br>
				<input type="text" id="piso"><br>
				<button type="submit">Registrarse</button>
			</form>
			<!--forma empresa-->


			<form>

				<label for="nomTien">Nombre de Tienda </label><br> <input
					type="text" id="nomTien" required><br> <label>Categoria
					de la tienda</label><br> <select name="categoria" id="categoria">
					<option value="cat1">ca</option>
					<option value="cat2">categoria</option>
					<option value="cat3">categoria</option>
					<option value="cat4">categoria</option>
					<option value="cat5">categoria</option>
					<option value="cat6">categoria</option>
					<option value="cat7">categoria</option>
					<option value="cat8">categoria</option>
				</select> <label for="nomUsu">Nombre de usuario</label><br> <input
					type="text" id="nomUsu" required><br> <label
					for="email">Correo electrónico </label><br> <input
					type="email" id="email" required><br> <label
					for="telf">Teléfono </label><br> <input type="text" id="telf"
					required><br> <label for="mov">Móvil </label><br>
				<input type="text" id="mov" required><br> <label
					for="password">Repetir Contraseña </label><br> <input
					type="password" id="password"><br> <label for="pais">Pais</label><br>
				<input type="text" id="pais"><br> <label
					for="comunidadAutonoma">Comunidad autonoma</label><br> <input
					type="text" id="comunidadAutonoma"><br> <label
					for="provincia">Provincia</label><br> <input type="text"
					id="provincia"><br> <label for="localidad">Localidad</label><br>
				<input type="text" id="localidad"><br> <label
					for="codigoPostal">Codigo postal</label><br> <input
					type="text" id="codigoPostal"><br> <label for="calle">Calle</label><br>
				<input type="text" id="calle"><br> <label for="piso">Piso</label><br>
				<input type="text" id="piso"><br> <label for="cif">CIF</label><br>
				<input type="text" id="cif"><br>
				<p>Para mantener la calidad y confianza es neccesario verificar
					la existencia de la empresa.</p>

				<p>Una vez verificados los datos recibira un código en el correo
					indicado.</p>
				<button type="submit">Registrarse</button>

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
			<label for="espanol">Español</label> <input type="radio" id="espanol"
				name="idioma"> <label for="ingles">Inglés</label> <input
				type="radio" id="ingles" name="idioma">
		</div>
		<figure>
			<img src="http://localhost:8080/abastos/imgs/logo_Mesa de trabajo 1.jpg" alt="">
		</figure>
		<section>
			<h1>Todos los ayuntamientos</h1>
			<form action="http://localhost:8080/abastos/tienda" method="post">
				<input type="hidden" name="action" value="buscar" /> <input
					type="text" name="nombre"> <input class="Buscar header"
					type="submit" value="buscar">
			</form>
		</section>
	</div>

</body>
</html>