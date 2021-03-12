<%@page import="com.abastos.market.web.util.ParameterNames"%>
<%@page import="com.abastos.market.web.util.ActionNames"%>
<%@page import="com.abastos.market.web.util.AttributesNames"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://localhost:8080/abastos/css/estilo.css">
<link rel="stylesheet" media="(min-width: 800px)" href="http://localhost:8080/abastos/css/prueba.css">
<script defer src="js/script.js"></script>
</head>
<body>

	<header class="sectionTiendas">
		<figure>
			<img src="http://localhost:8080/abastos/img/logo_Mesa de trabajo 1.jpg" alt="">
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
	<section class ="productoDetalle"><div>
                <form action="http://localhost:8080/abastos/producto" method="post">
                
                <%String id = (String) request.getAttribute(AttributesNames.EMPRESA); %>
                
                <input type="hidden" name=<%=ActionNames.EMPRESA%> value="<%=id%>">
                <input type="hidden"name=<%=ActionNames.ACTION%> value=<%=ActionNames.CREAR%>>
                
               	<label>Nombre producto</label><br>
               	<label>Castellano</label>
               	<input type="text" name=<%=ParameterNames.NOMBRE_CASTELLANO%>><br>
               	<label>Inglés</label>
               	<input type="text" name=<%=ParameterNames.NOMBRE_INGLES%>><br>
               	<label>Caracteristicas del producto</label><br>
               	<label>Castellano</label>
               	<input type="text" name=<%=ParameterNames.CARACT_CASTELLANO%>><br>
               	<label>Inglés</label>
               	<input type="text" name=<%=ParameterNames.CARACT_INGLES%>><br>
               	<label>Precio</label><br>
               	<input type="text" name=<%=ParameterNames.PRECIO%>><br>
               	<label>Stock del producto</label>
               	<input type="text" name=<%=ParameterNames.STOCK%>><br>
               	<label>Selecciona la tienda</label><br>
               	 <select name=<%=ParameterNames.ID_TIENDA%>>
               	<option disabled selected value>--selecciona una tienda--</option>
               	<% List<Tienda> tiendas = (List<Tienda>)request.getAttribute(AttributesNames.RESULTS_TIENDA);
               	for(Tienda t: tiendas){%>
               	<option value="<%=t.getId()%>"><%=t.getNombre()%></option>
               	<%}%>
               	</select><br>
               	<label>Oferta</label><br>
               	<select name="oferta">
               	<option disabled selected value>--selecciona una oferta--</option>
               	<% List<Oferta> ofertas = (List<Oferta>)request.getAttribute(AttributesNames.OFERTAS);
               	for(Oferta o: ofertas){%>
               	<option value="<%=o.getId() %>"><%=o.getNombreOferta()%> tipo de oferta: <%=o.getNombreTipoOfer()%></option>
               	<%}%>
               	</select><br>
               	<%List<Categoria> categorias = (List<Categoria>) request.getAttribute(AttributesNames.CATEGORIAS);%>
               	<label>Selecciona la categoria</label>
               	<select name=<%=ParameterNames.CATEGORIA%>>
               	<%for(Categoria c: categorias){ 
               		for(Categoria cat: c.getCategorias()){
               	%>
               	
               	<optgroup label="<%=cat.getNombre()%>"></optgroup>
               	<%for(Categoria categ: cat.getCategorias()){%>
               	<option value="<%=categ.getId()%>"><%=categ.getNombre()%></option>
               	
               	<%}}}%>
               	</select><br>
               	
               	<label>Elige el origen el producto</label><br>
               	<select name=<%=ParameterNames.ORIGEN%>>
               		<option value="N">Nacional</option>
               		<option value="I">Internacional</option>
               		<option value="L">Local</option>
               	</select>
               		<input type="submit" value="crear">
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
				<label primerBloque="p"for=registro><a href="http://localhost:8080/abastos/empresa?action=buscar&idEmpresa=<%=id%>">Mis tiendas</a></label><label segundoBloque="v" for="logIn"><a href="http://localhost:8080/abastos/oferta?action=buscar&idEmpresa=<%=id%>">Mis ofertas
				</a></label><label   for="foNav"><div ></div></label><label  for=null><div tercerBloque="y" nombre="Valoraciones"></div></label><label
				cuartoBloque="w"><a href="http://localhost:8080/abastos/producto?action=buscar&idEmpresa=<%=id%>">Mis productos</a></label>
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
	
		</section>
	</div>



</body>
</html>