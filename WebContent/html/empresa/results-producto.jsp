<%@page import="com.abastos.market.web.util.AttributesNames"%>
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
				<input type="hidden" name="action" value="buscar" /> <input
					type="text" name="nombre"> <input class="Buscar header"
					type="submit" value="buscar">
			</form>
		</section>
	</header>
	<section class="tiendas">
	</section>
	<section>
		
	</section>
	<section class="tiendas producto">
		<div class="tiendas2">
			<%
		List<Producto> results = (List<Producto>) request.getAttribute(AttributesNames.PRODUCTO);
		
		for(Producto p : results){
			%>
			<%List<Tienda> tienda = (List<Tienda>) request.getAttribute(AttributesNames.RESULTS_TIENDA);%>
			
			<div>
				<figure>
					<img src="http://localhost:8080/abastos/imgs/productos/<%=p.getId()%>-principal.jpg" />
				</figure>
				<%for(Tienda t: tienda){
					if(t.getId() == p.getIdTienda()){%>
					<p>
					<a
						href="http://localhost:8080/abastos/producto?action=detalle&id=<%=p.getId()%>&idTienda=<%=t.getId()%>"><%=p.getNombre()%></a>
				</p>
				
				<p>
				
					</p>
						<%=t.getNombre()%><%}}
					%>
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
		<%String id = (String)request.getAttribute(AttributesNames.EMPRESA); %>
		<button><a href="http://localhost:8080/abastos/precreate?action=producto&idEmpresa=<%=id%>"> Crear producto</a></button>
	</section>
	<input type="radio" id="null" name="seleccion" checked>
	<input type="radio" id="foNav" name="seleccion">
	<input type="radio" id="registro" name="seleccion">
	<input type="radio" id="Idiomas" name="seleccion">
	<input type="radio" id="logIn" name="seleccion">

	<div class="footerNav">

		<div>
		
						<label primerBloque="p"for=registro><a href="http://localhost:8080/abastos/empresa?action=buscar&idEmpresa=<%=id%>">Mis tiendas</a></label><label segundoBloque="v" for="logIn"><a href="http://localhost:8080/abastos/oferta?action=buscar&idEmpresa=<%=id%>">Mis ofertas
				</a></label><label   for="foNav"><div ></div></label><label  for=null><div tercerBloque="y" nombre="Valoraciones"></div></label><label
				cuartoBloque="w"><a href="http://localhost:8080/abastos/producto?action=buscar&idEmpresa=<%=id%>">Mis productos</a></label>
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
			<form>
				<label for="usuario">Usuario o email</label><br> <input
					type="text"><br> <label for="password">Contraseña</label><br>
				<input type="password"><br> <input type="submit">
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