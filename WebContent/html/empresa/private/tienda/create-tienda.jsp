<%@page import="com.abastos.market.web.util.*"%>
<%@ page
	import="java.util.*, com.abastos.model.*, com.abastos.service.*"%>
<%@include file="/html/commons/usuario/header.jsp"%>

<section class="tiendas">
	<div class="tiendas2">
</section>
<section>
	<nav></nav>
</section>
<section class="productoDetalle">
	<div>
		<%if(errores.printError(ActionNames.CREAR) != null){%>
		<p class="error"><%=errores.printError(ActionNames.CREAR)%></p>
		<%}%>
		<%if(errores.printError(ActionNames.SEND_EMAIL) != null){%>
		<p class="error"><%=errores.printError(ActionNames.SEND_EMAIL)%></p>
		<%}%>
			<%if(errores.printError(ParameterNames.ERROR) != null){%>
		<p class="error"><%=errores.printError(ParameterNames.ERROR)%></p>
		<%}%>
		<form action="<%=UrlBuilder.getUrl(request, ControllerPath.TIENDA_PRIVATE)%>"
			method="post">

			<input type="hidden" name=<%=ActionNames.ACTION%>
				value=<%=ActionNames.CREAR%>> <label>Nombre de la
				tienda</label><br> <input type="text"
				name=<%=ParameterNames.NOMBRE_TIENDA%>
				value="<%=ParameterUtils.printParam(request, ParameterNames.NOMBRE_TIENDA, 0)%>"><br>
			<label>Móvil</label><br> <input type="text"
				name=<%=ParameterNames.MOVIL%>
				value="<%=ParameterUtils.printParam(request, ParameterNames.MOVIL, 0)%>"><br>
			<label>Teléfono</label><br> <input type="text"
				name=<%=ParameterNames.TELEFONO%>
				value="<%=ParameterUtils.printParam(request, ParameterNames.TELEFONO, 0)%>"><br>
			<label>Correo electrónico</label><br> <input type="email"
				name=<%=ParameterNames.EMAIL%>
				value="<%=ParameterUtils.printParam(request, ParameterNames.EMAIL, 0)%>"><br>
			<label>Envio a domicilio</label><br> <input type="radio"
				name=<%=ParameterNames.ENVIO_DOMICILIO%> value="true"
				<%if(ParameterUtils.printParam(request, ParameterNames.ENVIO_DOMICILIO, 0) 
                		== "true"){%>
				checked <%}%>>si</input> <input type="radio"
				name=<%=ParameterNames.ENVIO_DOMICILIO%> value="false"
				<%if(ParameterUtils.printParam(request, ParameterNames.ENVIO_DOMICILIO, 0) 
                		== "false"){%>
				checked <%}%>>no</input><br> <label>Dirección</label><br>

			<label>Calle</label><br> <input type="text"
				name=<%=ParameterNames.CALLE%>
				value="<%=ParameterUtils.printParam(request, ParameterNames.CALLE, 0)%>"><br> <label>Piso</label><br>]
			<input type="text" name=<%=ParameterNames.PISO%>
			value="<%=ParameterUtils.printParam(request, ParameterNames.PISO, 0)%>"><br> <label>Número</label><br>
			<input type="text" name=<%=ParameterNames.NUMERO%>
			value="<%=ParameterUtils.printParam(request, ParameterNames.NUMERO, 0)%>"><br>
			<label>Código Postal</label><br> <input type="text"
				name=<%=ParameterNames.CODIGO_POSTAL%>
				value="<%=ParameterUtils.printParam(request, ParameterNames.CODIGO_POSTAL, 0)%>"><br> <label>Pais
			</label><br> <select name=<%=ParameterNames.PAIS%> id="pais">
				<option disabled selected value>--selecciona un pais--</option>
				<%List<Pais> paises = (List<Pais>) request.getAttribute(AttributesNames.PAISES); 
                for(Pais p: paises){ %>
				<option value="<%=p.getId()%>"><%=p.getNombre()%></option>
				<%}%>
			</select><br> <label>Comunidad</label><br> <select
				name=<%=ParameterNames.COMUNIDAD%> id="comunidad">
				<option disabled selected value>--selecciona una
					comunidad--</option>
			</select><br> <label>Provincia</label><br> <select
				name=<%=ParameterNames.PROVINCIA%> id="provincia">

				<option disabled selected value>--selecciona una
					provincia--</option>

			</select><br> <label>Localidad</label><br> <select
				name=<%=ParameterNames.LOCALIDAD%> id="localidad">
				<option disabled selected value>--selecciona una
					localidad--</option>


			</select> <label>Seleccionar categoria de la tienda</label> <select
				name=<%=ParameterNames.CATEGORIA%>>
				<%List<Categoria> categorias = (List<Categoria>) request.getAttribute(AttributesNames.CATEGORIAS); 
                for(Categoria cat: categorias){ %>
				<option value="<%=cat.getId()%>"><%=cat.getNombre()%></option>
				<%}%>
			</select> <button class="Buscar" type="input">crear tienda</button>
		</form>

	</div>
</section>
<%@include file="/html/commons/usuario/footer.jsp"%>