<%@page import="com.abastos.market.web.util.*"%>
<%@ page
	import="java.util.*, com.abastos.model.*, com.abastos.service.*"%>
<%@include file="/html/commons/usuario/header.jsp"%>

<section class="block">
	<div class="block_second">
</section>
<section>
	<nav></nav>
</section>
<section class="centralBlock">
	<div>
	<h1 id="title_section">Crea tu tienda</h1>
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
				value=<%=ActionNames.CREAR%>> 
				<fieldset class="centralBlock_form">
				<label>Nombre de la
				tienda</label> <input type="text"
				name=<%=ParameterNames.NOMBRE_TIENDA%>
				value="<%=ParameterUtils.printParam(request, ParameterNames.NOMBRE_TIENDA, 0)%>">
			<label>M�vil</label> <input type="text"
				name=<%=ParameterNames.MOVIL%>
				value="<%=ParameterUtils.printParam(request, ParameterNames.MOVIL, 0)%>">
			<label>Tel�fono</label> <input type="text"
				name=<%=ParameterNames.TELEFONO%>
				value="<%=ParameterUtils.printParam(request, ParameterNames.TELEFONO, 0)%>">
			<label>Correo electr�nico</label> <input type="email"
				name=<%=ParameterNames.EMAIL%>
				value="<%=ParameterUtils.printParam(request, ParameterNames.EMAIL, 0)%>">
			<label>Envio a domicilio</label>
			<div id="buttons">
			 <input type="radio"
				name=<%=ParameterNames.ENVIO_DOMICILIO%> value="true"
				<%if(ParameterUtils.printParam(request, ParameterNames.ENVIO_DOMICILIO, 0) 
                		== "true"){%>
				checked <%}%>>si</input> <input type="radio"
				name=<%=ParameterNames.ENVIO_DOMICILIO%> value="false"
				<%if(ParameterUtils.printParam(request, ParameterNames.ENVIO_DOMICILIO, 0) 
                		== "false"){%>
				checked <%}%>>no</input>
				</div>
				 <label>Direcci�n</label>

			<label>Calle</label> <input type="text"
				name=<%=ParameterNames.CALLE%>
				value="<%=ParameterUtils.printParam(request, ParameterNames.CALLE, 0)%>">
				 <label>Piso</label>
			<input type="text" name=<%=ParameterNames.PISO%>
			value="<%=ParameterUtils.printParam(request, ParameterNames.PISO, 0)%>"> <label>N�mero</label>
			<input type="text" name=<%=ParameterNames.NUMERO%>
			value="<%=ParameterUtils.printParam(request, ParameterNames.NUMERO, 0)%>">
			<label>C�digo Postal</label> <input type="text"
				name=<%=ParameterNames.CODIGO_POSTAL%>
				value="<%=ParameterUtils.printParam(request, ParameterNames.CODIGO_POSTAL, 0)%>"> <label>Pais
			</label> 
			<label class="select form" for="pais">
			<select name=<%=ParameterNames.PAIS%> id="pais">
				<option disabled selected value>--selecciona un pais--</option>
				<%List<Pais> paises = (List<Pais>) request.getAttribute(AttributesNames.PAISES); 
                for(Pais p: paises){ %>
				<option value="<%=p.getId()%>"><%=p.getNombre()%></option>
				<%}%>
			</select></label>
			
			 <label>Comunidad</label>
			 <label class="select form" for="comunidad">
			  <select name=<%=ParameterNames.COMUNIDAD%> id="comunidad">
				<option disabled selected value>--selecciona una comunidad--</option>
			</select></label>
			
			 <label>Provincia</label>
			 <label class="select form" for="provincia">
			  <select name=<%=ParameterNames.PROVINCIA%> id="provincia">
				<option disabled selected value>--selecciona una provincia--</option>
			</select> </label>
			<label>Localidad</label> 
			 <label class="select form" for="localidad">
			<select name=<%=ParameterNames.LOCALIDAD%> id="localidad">
				<option disabled selected value>--selecciona una localidad--</option>
			</select></label>
			 <label>Seleccionar categoria de la tienda</label> 
			  <label class="select form" for="categoriaTienda">
			 <select name=<%=ParameterNames.CATEGORIA%> id="categoriaTienda">
				<%List<Categoria> categorias = (List<Categoria>) request.getAttribute(AttributesNames.CATEGORIAS); 
                for(Categoria cat: categorias){ %>
				<option value="<%=cat.getId()%>"><%=cat.getNombre()%></option>
				<%}%>
			</select></label>
			 <button class="Buscar" type="input">crear tienda</button>
				</fieldset>
		</form>

	</div>
</section>
<%@include file="/html/commons/usuario/footer.jsp"%>