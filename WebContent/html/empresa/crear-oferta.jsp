<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.abastos.model.*"%>
<%@page import="com.abastos.market.web.util.*"%>
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
	<section class ="productoDetalle"><div>
                <div class="informacionProducto">
                  	<label>descuento PCN</label>
                  	<input type="radio" name="descuentoPCN">
                  	<label>descuento Fijo</label>
                  	<input type="radio" name="descuentoFijo">
                  	<label>descuento</label>
                  	<input type="text" name="descuento">
                  	<label>Aplicar descuento a un producto comprando el producto asignado</label>
                  	<select name="producto">
                  		 
                  	</select>
                  	<label>N�mero total de unidades a comprar</label>
					<input type="text" name="denominador">
                  	<label>N�mero de unidades sin descuento</label>
                  	<input type="text" name="numerador">
                  	
                    <input type="submit" value="a�adir al carrito"/>
                </div>
                
            </div>
        </section>
	<input type="radio" id="null" name="seleccion" checked>
	<input type="radio" id="foNav" name="seleccion">
	<input type="radio" id="registro" name="seleccion">
	<input type="radio" id="Idiomas" name="seleccion">
	<input type="radio" id="logIn" name="seleccion">

	<div class="footerNav">
	<%String id = (String)request.getAttribute("idEmpresa"); %>
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
					type="text"><br> <label for="password">Contrase�a</label><br>
				<input type="password"><br> <input type="submit">
			</form>
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