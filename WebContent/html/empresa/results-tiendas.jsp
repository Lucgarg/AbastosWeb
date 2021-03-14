<%@page import="com.abastos.market.web.util.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*" %>
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
		<% Empresa empresa = (Empresa) request.getAttribute(AttributesNames.EMPRESA);%>
			<h1>Todos los ayuntamientos</h1>
			<form action="<%=request.getContextPath()%>/tienda" method="post">
			<input type="hidden" name="idEmpresa" value=<%=empresa.getId()%>>
			<input type="hidden" name="action" value="buscar"/>
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
					
					<li><a href="<%=request.getContextPath()%>/tienda?action=buscar&categoria=<%=cat.getId()%>&idEmpresa=<%=empresa.getId()%>"><%=cat.getNombre()%></a></li>
					<%}%>
					<form action="<%=request.getContextPath()%>/tienda" method="post">
					<input type="hidden" name=<%=ActionNames.ACTION%> value=<%=ActionNames.BUSCAR%>/>
					
					<input type="hidden" name=<%=ActionNames.EMPRESA%> value=<%=empresa.getId()%>>
						<label>Envio a domicilio</label>
						<input type="checkbox" name=<%=ParameterNames.ENVIO_DOMICILIO%> value="true">
					
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
					<a href="<%=request.getContextPath()%>/tienda?action=detalle&id=<%=t.getId()%>"><%=t.getNombre()%></a>
				</p>
				<p><%=t.getDireccionDto().getLocalidad()%></p>
				<span><%=t.getPuntuacionMedia().getValoracionMedia()%></span>
			</div>
			<% 
			}
		%>
		</div>
		<button><a href="<%=request.getContextPath()%><%=ViewPaths.PRECREATE_ACTION_TIENDA%><%=empresa.getId()%>"> Crear tienda</a></button>
	</section>
	<input type="radio" id="null" name="seleccion" checked>
	<input type="radio" id="foNav" name="seleccion">
	<input type="radio" id="registro" name="seleccion">
	<input type="radio" id="Idiomas" name="seleccion">
	<input type="radio" id="logIn" name="seleccion">

	<div class="footerNav">

		<div>
			<label primerBloque="p"for=registro><a href="<%=request.getContextPath()%><%=ViewPaths.EMPRESA_ACTION_BUSCAR%><%=empresa.getId()%>">Mis tiendas</a></label><label segundoBloque="v" for="logIn">
				<a href="<%=request.getContextPath()%><%=ViewPaths.OFERTA_ACTION_BUSCAR%><%=empresa.getId()%>">Mis ofertas
				</a></label><label   for="foNav"><div ></div></label><label  for=null><div tercerBloque="y" nombre="Valoraciones"></div></label><label
				cuartoBloque="w"><a href="<%=request.getContextPath()%><%=ViewPaths.PRODUCTO_ACTION_BUSCAR_EMPRESA%><%=empresa.getId()%>">Mis productos</a></label>
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
				<input type="hidden" name="idEmpresa" value=<%=empresa.getId()%>>
			<input type="hidden" name="action" value="buscar"/>
			<input type="text" name="nombre">
			<input class="Buscar header" type="submit" value="buscar">
			</form>
		</section>
	</div>



</body>
</html>