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
<script defer src="js/request.js"></script>
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
                <form action="http://localhost:8080/abastos/tienda" method="post">
                
                <%String id = (String) request.getAttribute(AttributesNames.EMPRESA);%>
                <input type="hidden" name=<%=ActionNames.EMPRESA%> value="<%=id%>">
                <input type="hidden"name=<%=ActionNames.ACTION%> value=<%=ActionNames.BUSCAR%>>
                <label>Nombre de la tienda</label><br>
                <input type="text" name=<%=ParameterNames.NOMBRE_TIENDA%>><br>
                <label>Móvil</label><br>
                <input type="text" name=<%=ParameterNames.MOVIL%>><br>
                <label>Teléfono</label><br>
                <input type="text" name=<%=ParameterNames.TELEFONO%>><br>
                <label>Correo electrónico</label><br>
                <input type="email" name=<%=ParameterNames.EMAIL%>><br>
                <label>Envio a domicilio</label><br>
                <input type="radio" name=<%=ParameterNames.ENVIO_DOMICILIO%> value="true">si</input>
                <input type="radio" name=<%=ParameterNames.ENVIO_DOMICILIO%> value="false">no</input><br>
                <label>Dirección</label><br>
                
                <label>Calle</label><br>
                <input type="text" name=<%=ParameterNames.CALLE%>><br>
                <label>Piso</label><br>]
                <input type="text" name=<%=ParameterNames.PISO%>><br>
				<label>Número</label><br>
				<input type="text" name=<%=ParameterNames.NUMERO%>><br>
				<label>Código Postal</label><br>
				<input type="text" name=<%=ParameterNames.CODIGO_POSTAL%>><br>
                <label>Pais  </label><br>
                <select  name=<%=ParameterNames.PAIS%> id="pais">
                <option disabled selected value>--selecciona un pais--</option>
                <%List<Pais> paises = (List<Pais>) request.getAttribute(AttributesNames.PAISES); 
                for(Pais p: paises){ %>
                <option value="<%=p.getId()%>"><%=p.getNombre()%></option>
                <%}%>
                </select><br>
              
                <label>Comunidad</label><br>
                 <select name=<%=ParameterNames.COMUNIDAD%> id="comunidad">
                          <option disabled selected value>--selecciona una comunidad--</option>
                </select><br>
                
                 <label>Provincia</label><br>
                 <select name=<%=ParameterNames.PROVINCIA%> id="provincia">
                     
                <option disabled selected value>--selecciona una provincia--</option>
  
                </select><br>
              
                <label>Localidad</label><br>
                <select name=<%=ParameterNames.LOCALIDAD%> id="localidad">
             	           <option disabled selected value>--selecciona una localidad--</option>
            
           
               	</select>
               	<label>Seleccionar categoria de la tienda</label>
               	<select name=<%=ParameterNames.CATEGORIA%>>
                      <%List<Categoria> categorias = (List<Categoria>) request.getAttribute(AttributesNames.CATEGORIAS); 
                for(Categoria cat: categorias){ %>
                <option value="<%=cat.getId()%>"><%=cat.getNombre()%></option>
                <%}%>
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