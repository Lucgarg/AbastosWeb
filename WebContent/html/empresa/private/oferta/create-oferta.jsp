<%@ page
	import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.market.web.util.*"%>
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
	<h1 id="title_section">Crea tu oferta</h1>
		<%
		if (errores.printError(ParameterNames.ERROR) != null) {
		%>
		<p class="error"><%=errores.printError(ParameterNames.ERROR)%></p>
		<%
		}
		%>
		<form action="<%=UrlBuilder.getUrl(request, ControllerPath.OFERTA)%>"
			method="post">
			<input type="hidden" name=<%=ActionNames.ACTION%>
				value=<%=ActionNames.CREAR%>>
			<fieldset class="centralBlock_form">
				<label>Nombre</label> <input type="text"
					name=<%=ParameterNames.NOMBRE_OFERTA%>
					value="<%=ParameterUtils.printParam(parametros, ParameterNames.NOMBRE_OFERTA, 0)%>">
				<%
				if (errores.printError(ParameterNames.NOMBRE_OFERTA) != null) {
				%>
				<p class="error"><%=errores.printError(ParameterNames.NOMBRE_OFERTA)%></p>
				<%
				}
				%>
				<label>Elije un tipo de descuento</label> <label class="select form"
					for="tipOferta"> <select name="tipoOferta" id="tipOferta">
						<%
						List<TipoOferta> tipOfert = (List<TipoOferta>) request.getAttribute(AttributesNames.TIPO);
						for (TipoOferta tp : tipOfert) {
						%>
						<option value="<%=tp.getId()%>"><%=tp.getNombre()%></option>
						<%
						}
						%>
				</select></label>
				<%
				if (errores.printError(ParameterNames.TIPO_OFERTA) != null) {
				%>
				<p class="error"><%=errores.printError(ParameterNames.TIPO_OFERTA)%></p>
				<%
				}
				%>
				<label class="ofertaCL">Elige tienda(este tipo de oferta
					sólo se podrá aplicar en una tienda)</label> <label class="select form"
					for="tiendaSelect" style="display: none;"> <select
					name="<%=ParameterNames.ID_TIENDA%>" id="tiendaSelect"></select></label> <label
					class="select form" for="productoOfertaSelect"
					style="display: none;"> <select
					name="<%=ParameterNames.PRODUCTO_OFERTA%>"
					id="productoOfertaSelect"></select></label>
				<%
				if (errores.printError(ParameterNames.PRODUCTO_OFERTA) != null) {
				%>
				<p class="error"><%=errores.printError(ParameterNames.PRODUCTO_OFERTA)%></p>
				<%
				}
				%>

				<label>Elije uno de los dos: </label> <label>descuento
					porcentual</label> <input type="text" name=<%=ParameterNames.DESCT_PCN%>
					value="<%=ParameterUtils.printParam(parametros, ParameterNames.DESCT_PCN, 0)%>">
						<%
				if (errores.printError(ParameterNames.DESCT_PCN) != null) {
				%>
				<p class="error"><%=errores.printError(ParameterNames.DESCT_PCN)%></p>
				<%
				}
				%>
				<label>descuento fijo</label> <input type="text"
					name=<%=ParameterNames.DESCT_FIJO%>
					value="<%=ParameterUtils.printParam(parametros, ParameterNames.DESCT_FIJO, 0)%>">
								<%
				if (errores.printError(ParameterNames.DESCT_FIJO) != null) {
				%>
				<p class="error"><%=errores.printError(ParameterNames.DESCT_FIJO)%></p>
				<%
				}
				%>
				<%
				if (errores.printError(ParameterNames.DESCUENTOS) != null) {
				%>
				<p class="error"><%=errores.printError(ParameterNames.DESCUENTOS)%></p>
				<%
				}
				%>
				<label class="">En caso de elegir el tipo de oferta "segunda unidad"</label>
				<label class="">Número mínimo de unidades con
					precio sin descuento</label> <input class="" type="text"
					name=<%=ParameterNames.NUMERADOR%>
					value="<%=ParameterUtils.printParam(parametros, ParameterNames.NUMERADOR, 0)%>">
				<label class="">Número de unidades necesarias
					para recibir un descuento</label> <input class="" type="text"
					name=<%=ParameterNames.DENOMINADOR%>
					value="<%=ParameterUtils.printParam(parametros, ParameterNames.DENOMINADOR, 0)%>">
				<%
				if (errores.printError(ParameterNames.NUMBERS) != null) {
				%>
				<p class="error"><%=errores.printError(ParameterNames.NUMBERS)%></p>
				<%
				}
				%>
				<label>Fecha de vigencia</label> <input type="text"
					name=<%=ParameterNames.FECHA_VIG%> placeholder="10-12-2001"
					value="<%=ParameterUtils.printParam(parametros, ParameterNames.FECHA_VIG, 0)%>">
				<input type="text" name=<%=ParameterNames.HORA_VIG%>
					placeholder="22:50"
					value="<%=ParameterUtils.printParam(parametros, ParameterNames.HORA_VIG, 0)%>">
				<%
				if (errores.printError(ParameterNames.FECHA_VIG) != null) {
				%>
				<p class="error"><%=errores.printError(ParameterNames.FECHA_VIG)%></p>
				<%
				}
				%>
				<label>Fecha de caducidad</label> <input type="text"
					name=<%=ParameterNames.FECHA_CAD%> placeholder="10-12-2001"
					value="<%=ParameterUtils.printParam(parametros, ParameterNames.FECHA_CAD, 0)%>">
				<input type="text" name=<%=ParameterNames.HORA_CAD%>
					placeholder="22:50"
					value="<%=ParameterUtils.printParam(parametros, ParameterNames.HORA_CAD, 0)%>">
				<%
				if (errores.printError(ParameterNames.FECHA_CAD) != null) {
				%>
				<p class="error"><%=errores.printError(ParameterNames.FECHA_CAD)%></p>
				<%
				}
				%>
				<%
				if (errores.printError(ParameterNames.FECHAS) != null) {
				%>
				<p class="error"><%=errores.printError(ParameterNames.FECHAS)%></p>
				<%
				}
				%>
				<button type="submit" class="Buscar">crear oferta</button>
			</fieldset>
		</form>

	</div>
</section>
<%@include file="/html/commons/usuario/footer.jsp"%>