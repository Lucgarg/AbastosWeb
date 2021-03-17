<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.market.web.util.*"%>
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
	<section class ="productoDetalle"><div>
                <form action="<%=request.getContextPath()%>/oferta" method="post">
                
                <%String id = (String) request.getAttribute(AttributesNames.EMPRESA); %>
                
                <input type="hidden" name=<%=ActionNames.EMPRESA%> value="<%=id%>">
                <input type="hidden"name=<%=ActionNames.ACTION%> value=<%=ActionNames.CREAR%>>
               	<label>Nombre</label><br>
               	<input type="text" name=<%=ParameterNames.NOMBRE_OFERTA%>><br>
               	<label>Elije un tipo de descuento</label><br>
               	<select name="tipoOferta">
               	<%List<TipoOferta> tipOfert = (List<TipoOferta>) request.getAttribute(AttributesNames.TIPO);
               	for(TipoOferta tp: tipOfert){
               	%>
               		<option value="<%=tp.getId()%>"><%=tp.getNombre()%></option>
               		<%}%>
               	</select><br>
               	<label>Elije uno de los dos: </label><br>
               	   	<label>descuento porcentual</label><br>
               	<input type="text" name=<%=ParameterNames.DESCT_PCN%>><br>
               	   	<label>descuento fijo</label><br>
               	<input type="text" name=<%=ParameterNames.DESCT_FIJO%>><br>
               	<label>En caso de elegir el tipo de oferta "segunda unidad"</label><br>
               	   	<label>N�mero m�nimo de unidades con precio sin descuento</label><br>
               	<input type="text" name=<%=ParameterNames.NUMERADOR%>><br>
               	   	<label>N�mero de unidades necesarias para recibir un descuento</label><br>
               	<input type="text" name=<%=ParameterNames.DENOMINADOR%>><br>
               	   	<label>Fecha de vigencia</label><br>
               	<input type="text" name=<%=ParameterNames.FECHA_VIG%> placeholder="10-12-2001"><br>
               	<input type="text" name=<%=ParameterNames.HORA_VIG%> placeholder="22:50"><br>
               	 	   	<label>Fecha de caducidad</label><br>
            	<input type="text" name=<%=ParameterNames.FECHA_CAD%> placeholder="10-12-2001"><br>
               	<input type="text" name=<%=ParameterNames.HORA_CAD%> placeholder="22:50"><br>
               	
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
		<label primerBloque="p"for=registro><a href="<%=request.getContextPath()%><%=ViewPathsActions.EMPRESA_ACTION_BUSCAR%><%=id%>">Mis tiendas</a></label><label segundoBloque="v" for="logIn">
				<a href="<%=request.getContextPath()%><%=ViewPathsActions.OFERTA_ACTION_BUSCAR%><%=id%>">Mis ofertas
				</a></label><label   for="foNav"><div ></div></label><label  for=null><div tercerBloque="y" nombre="Valoraciones"></div></label><label
				cuartoBloque="w"><a href="<%=request.getContextPath()%><%=ViewPathsActions.PRODUCTO_ACTION_BUSCAR_EMPRESA%><%=id%>">Mis productos</a></label>
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