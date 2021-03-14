<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="com.abastos.market.web.util.ParameterNames"%>
<%@page import="com.abastos.market.web.util.AttributesNames"%>
<%@page import="com.abastos.market.web.util.ActionNames"%>
<%@ page import="java.util.*, com.abastos.model.*"%>
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
		<div class="tiendas2">
			<input type="checkbox">
			<%Tienda tienda = (Tienda)request.getAttribute(AttributesNames.TIENDA);%>
			<h1><%=tienda.getNombre()%></h1>

			<div>
				<figure>
					<img src="<%=request.getContextPath()%>/imgs/tiendas/<%=tienda.getId()%>-principal.jpg" alt="">
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

					<li><a>categoria 1</a>
						<ol>
							<li><a hred="">subcategoria1.1</a></li>
							<li><a hred="">subcategoria1.2</a></li>
							<li><a hred="">subcategoria1.3</a></li>
						</ol></li>
					<li><a>categoria 2</a>
						<ol>
							<li><a hred="">subcategoria1.1</a></li>
							<li><a hred="">subcategoria1.2</a></li>
							<li><a hred="">subcategoria1.3</a></li>
						</ol></li>
					<li><a>categoria 3</a>
						<ol>
							<li><a hred="">subcategoria1.1</a></li>
							<li><a hred="">subcategoria1.2</a></li>
							<li><a hred="">subcategoria1.3</a></li>
						</ol></li>
					<li><a>categoria 4</a>
						<ol>
							<li><a hred="">subcategoria1.1</a></li>
							<li><a hred="">subcategoria1.2</a></li>
							<li><a hred="">subcategoria1.3</a></li>
						</ol></li>
					<li><a>categoria 5</a>
						<ol>
							<li><a hred="">subcategoria1.1</a></li>
							<li><a hred="">subcategoria1.2</a></li>
							<li><a hred="">subcategoria1.3</a></li>
						</ol></li>
					<li><a>categoria 6</a></li>
					<li><a>categoria 7</a></li>
					<form action="<%=request.getContextPath()%>/producto" method="post">
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
	<section class ="productoDetalle"><div>
                <figure class="imagenProducto">
                    <input type="radio" id="prueba1" name="sli">
                    <input type="radio" id="prueba2" name="sli">
                    <input type="radio" id="prueba3" name="sli">
                    <ul>
                    <%Producto p = (Producto)request.getAttribute(AttributesNames.PRODUCTO);%>
                        <li><img src="<%=request.getContextPath()%>/imgs/productos/<%=p.getId()%>-principal.jpg" alt="" class="img1"></li>
                        <li><img src="<%=request.getContextPath()%>/imgs/productos/<%=p.getId()%>-galeria.jpg" alt="" class="img2"></li>
                       
                    </ul>
                    <figcaption>
                        <label for="prueba3"></label>
                        <label for="prueba2"></label>
                        <label for="prueba1"></label>
                  <ul>
                            <li><label for="prueba1"><img src="<%=request.getContextPath()%>/imgs/productos/<%=p.getId()%>-principal.jpg" alt="" class="img2"></label></li>
                            <li><label for="prueba2"><img src="<%=request.getContextPath()%>/imgs/productos/<%=p.getId()%>-galeria.jpg" alt="" class="img2"></label></li>
                          
                </ul> 
                    </figcaption>
                </figure> <div class="informacionProducto">
                    <p id="NomPro"><%=p.getNombre()%></p>
                    <span><%=p.getValoracion() %></span>
                    <p><%=p.getPrecio()%></p>
                    <p><%=p.getCaracteristicas()%></p>
                    <form> <input type="number" id="quantity" name="quantity" min="1" max="5"> </form>
                    <p id= "Oferta"></p>
                    <form action="">
                        <select name="" id="">
                            
                            <option value="">lista1</option>
                            <option value="">lista2</option>
                            <option value="">lista3</option>
                        </select>
                        <input type="submit" value="añadir a mi lista"/>
                    </form>
                    <input type="submit" value="añadir al carrito"/>
                </div>
                
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
				<input type="hidden" name="action" value="buscar" /> <input
					type="text" name="nombre"> <input class="Buscar header"
					type="submit" value="buscar">
			</form>
		</section>
	</div>

</body>
</html>