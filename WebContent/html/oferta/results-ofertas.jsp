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
		</section>
	</header>
	<section class="tiendas">
		<div class="tiendas2">
	</section>
	<section>
		<nav>
			
		</nav>
	</section>
	<section class="tiendas producto">
		<div class="tiendas2">
		<%
		List<Oferta> results = (List<Oferta>) request.getAttribute(AttributesNames.OFERTAS);
		%>
		<%
		for(Oferta o : results){
			%>
		
			<div>
				<p><%=o.getNombreOferta() %></p>
				<p><%=o.getNombreTipoOfer() %></p>
				<p><%=o.getFechaDesde() %></p>
				<p><%=o.getFechaHasta()%></p>
				<%if(o.getIdTipoOferta() == 2){%>
				<p>numerador: <%=o.getNumerador()%></p>
				<p>denominador: <%=o.getDenominador()%></p>
				<%} if(o.getIdTipoOferta() ==3){
				List<Producto> productos = (List<Producto>)request.getAttribute(AttributesNames.PRODUCTO_OFERTA);
					for(Producto p: productos){
						if(p.getId() == o.getIdProdOferta()){%>

				<p>Producto que recibe la oferta: <%=p.getNombre()%></p>
				<%}}}%>
				<%if(o.getDescuentoFijo() != 0.0){%>
				<p>Descuento fijo: <%=o.getDescuentoFijo()%></p>
				<%}else{ %>
				<p>Descuento PCN: <%=o.getDescuentoPcn()%></p>
				<% 
			}%>
			</div>
			<% } %>
		</div>
			<%String idEmpresa = (String)request.getAttribute(AttributesNames.EMPRESA); %>
		<button><a href="<%=request.getContextPath()%><%=ViewPaths.PRECREATE_ACTION_EMPRESA%><%=idEmpresa%>"> Crear oferta</a></button>
	</section>
	<input type="radio" id="null" name="seleccion" checked>
	<input type="radio" id="foNav" name="seleccion">
	<input type="radio" id="registro" name="seleccion">
	<input type="radio" id="Idiomas" name="seleccion">
	<input type="radio" id="logIn" name="seleccion">

	<div class="footerNav">

		<div>
	
<label primerBloque="p"for=registro><a href="<%=request.getContextPath()%><%=ViewPaths.EMPRESA_ACTION_BUSCAR%><%=idEmpresa%>">Mis tiendas</a></label><label segundoBloque="v" for="logIn">
				<a href="<%=request.getContextPath()%><%=ViewPaths.OFERTA_ACTION_BUSCAR%><%=idEmpresa%>">Mis ofertas
				</a></label><label   for="foNav"><div ></div></label><label  for=null><div tercerBloque="y" nombre="Valoraciones"></div></label><label
				cuartoBloque="w"><a href="<%=request.getContextPath()%><%=ViewPaths.PRODUCTO_ACTION_BUSCAR_EMPRESA%><%=idEmpresa%>">Mis productos</a></label>
		</div>
		
		<div class="idiomas">
			<label for="espanol">Espa�ol</label> <input type="radio" id="espanol"
				name="idioma"> <label for="ingles">Ingl�s</label> <input
				type="radio" id="ingles" name="idioma">
		</div>
		<figure>
			<img src="<%=request.getContextPath()%>/imgs/logo_Mesa de trabajo 1.jpg" alt="">
		</figure>
		<section>
			
		</section>
	</div>



</body>
</html>