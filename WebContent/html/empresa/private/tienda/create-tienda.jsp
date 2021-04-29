<%@page import="com.abastos.market.web.util.*"%>
<%@ page
	import="java.util.*, com.abastos.model.*, com.abastos.service.*"%>
<%@include file="/html/commons/usuario/header.jsp"%>
<%Map<String, String[]> parametros = (Map<String, String[]>)request.getParameterMap();%>
<section class="block search">
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
				<label class="mandatory">Nombre de la
				tienda</label> <input type="text"
				name=<%=ParameterNames.NOMBRE_TIENDA%>
				value="<%=ParameterUtils.printParam(parametros, ParameterNames.NOMBRE_TIENDA, 0)%>">
			<label class="mandatory">información de contacto</label>
			<label class="mandatory">Móvil</label> <input type="text"
				name=<%=ParameterNames.MOVIL%>
				value="<%=ParameterUtils.printParam(parametros, ParameterNames.MOVIL, 0)%>">
			<label class="mandatory">Teléfono</label> <input type="text"
				name=<%=ParameterNames.TELEFONO%>
				value="<%=ParameterUtils.printParam(parametros, ParameterNames.TELEFONO, 0)%>">
			<label class="mandatory">Correo electrónico</label> <input type="email"
				name=<%=ParameterNames.EMAIL%>
				value="<%=ParameterUtils.printParam(parametros, ParameterNames.EMAIL, 0)%>">
			<label class="mandatory">Envio a domicilio</label>
			<div id="buttons">
			 <input type="radio"
				name=<%=ParameterNames.ENVIO_DOMICILIO%> value="true"
				<%if(ParameterUtils.printParam(parametros, ParameterNames.ENVIO_DOMICILIO, 0) 
                		== "true"){%>
				checked <%}%>>si</input> <input type="radio"
				name=<%=ParameterNames.ENVIO_DOMICILIO%> value="false"
				<%if(ParameterUtils.printParam(parametros, ParameterNames.ENVIO_DOMICILIO, 0) 
                		== "false"){%>
				checked <%}%>>no</input>
				</div>
				 <label>Dirección</label>

			<label class="mandatory">Calle</label> <input type="text"
				name=<%=ParameterNames.CALLE%>
				value="<%=ParameterUtils.printParam(parametros, ParameterNames.CALLE, 0)%>">
				 <label class="mandatory">Piso</label>
			<input type="text" name=<%=ParameterNames.PISO%>
			value="<%=ParameterUtils.printParam(parametros, ParameterNames.PISO, 0)%>"> <label>Número</label>
			<input type="text" name=<%=ParameterNames.NUMERO%>
			value="<%=ParameterUtils.printParam(parametros, ParameterNames.NUMERO, 0)%>">
			<label class="mandatory">Código Postal</label> <input type="text"
				name=<%=ParameterNames.CODIGO_POSTAL%>
				value="<%=ParameterUtils.printParam(parametros, ParameterNames.CODIGO_POSTAL, 0)%>"> <label>Pais
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
				<option value="0">--selecciona una comunidad--</option>
			</select></label>
			
			 <label>Provincia</label>
			 <label class="select form" for="provincia">
			  <select name=<%=ParameterNames.PROVINCIA%> id="provincia">
				<option value="0">--selecciona una provincia--</option>
			</select> </label>
			<label class="mandatory">Localidad</label> 
			 <label class="select form" for="localidad">
			<select name=<%=ParameterNames.LOCALIDAD%> id="localidad">
				<option value="0">--selecciona una localidad--</option>
			</select></label>
			 <label class="mandatory">Seleccionar categoria de la tienda</label> 
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