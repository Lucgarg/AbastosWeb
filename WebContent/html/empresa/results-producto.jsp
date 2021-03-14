<%@page import="com.abastos.market.web.util.*"%>
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
	href="<%=request.getContextPath()%>/css/estilo.css">
<link rel="stylesheet" media="(min-width: 800px)"
	href="<%=request.getContextPath()%>/css/prueba.css">
<script defer src="js/script.js"></script>
</head>
<body>
	<header class="sectionTiendas">
		<figure>
			<img
				src="<%=request.getContextPath()%>/imgs/logo_Mesa de trabajo 1.jpg"
				alt="">
		</figure>
		<section>
			<h1>Todos los ayuntamientos</h1>
			<form action="<%=request.getContextPath()%>/tienda" method="post">
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
					<img src="<%=request.getContextPath()%>/imgs/productos/<%=p.getId()%>-principal.jpg" />
				</figure>
				<%for(Tienda t: tienda){
					if(t.getId() == p.getIdTienda()){%>
					<p>
					<a
						href="<%=request.getContextPath()%><%=ViewPaths.PRODUCTO_ACTION_DETALLE%><%=p.getId()%>&tienda=<%=t.getId()%>"><%=p.getNombre()%></a>
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
		<button><a href="<%=request.getContextPath()%><%=ViewPaths.PRECREATE_ACTION_PRODUCTO%><%=id%>"> Crear producto</a></button>
	</section>
	<input type="radio" id="null" name="seleccion" checked>
	<input type="radio" id="foNav" name="seleccion">
	<input type="radio" id="registro" name="seleccion">
	<input type="radio" id="Idiomas" name="seleccion">
	<input type="radio" id="logIn" name="seleccion">

	<div class="footerNav">

		<div>
		
					<label primerBloque="p"for=registro><a href="<%=request.getContextPath()%><%=ViewPaths.EMPRESA_ACTION_BUSCAR%><%=id%>">Mis tiendas</a></label><label segundoBloque="v" for="logIn">
				<a href="<%=request.getContextPath()%><%=ViewPaths.OFERTA_ACTION_BUSCAR%><%=id%>">Mis ofertas
				</a></label><label   for="foNav"><div ></div></label><label  for=null><div tercerBloque="y" nombre="Valoraciones"></div></label><label
				cuartoBloque="w"><a href="<%=request.getContextPath()%><%=ViewPaths.PRODUCTO_ACTION_BUSCAR_EMPRESA%><%=id%>">Mis productos</a></label>
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
			<img src="<%=request.getContextPath()%>/imgs/logo_Mesa de trabajo 1.jpg" alt="">
		</figure>
		<section>
			<h1>Todos los ayuntamientos</h1>
			<form action="<%=request.getContextPath()%>/tienda" method="post">
				<input type="hidden" name="action" value="buscar" /> <input
					type="text" name="nombre"> <input class="Buscar header"
					type="submit" value="buscar">
			</form>
		</section>
	</div>

</body>
</html>