
<%@page import="com.abastos.market.web.util.*"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*" %>
<%@include file= "/html/commons/usuario/header.jsp"%>
	<%Map<String, String[]> parametros = (Map<String, String[]>)request.getParameterMap();%>
	<section class="block search">
		<div class="block_second">
	</section>
		
	<section>
		<nav>
		</nav>
	</section>
	<section class="centralBlock"><div>
	<h1 id="title_section">Crea tu producto</h1>
		<%if(errores.printError(ActionNames.CREAR) != null){%>
		<p class="error"><%=errores.printError(ActionNames.CREAR)%></p>
		<%}%>
		<%if(errores.printError(ParameterNames.ERROR) != null){%>
		<p class="error"><%=errores.printError(ParameterNames.ERROR)%></p>
		<%}%>
                <form action="<%=UrlBuilder.getUrl(request, ControllerPath.PRODUCTO_PRIVATE)%>" 	
                enctype="multipart/form-data" method="post">
                <input type="hidden"name=<%=ActionNames.ACTION%> value=<%=ActionNames.CREAR%>>
                <fieldset class="centralBlock_form">
               	<label>Nombre producto</label>
               	<label>Castellano</label>
               	<input type="text" name=<%=ParameterNames.NOMBRE_CASTELLANO%> value="<%=ParameterUtils.printParam(parametros, ParameterNames.NOMBRE_CASTELLANO, 0)%>">
               	<label>Inglés</label>
               	<input type="text" name=<%=ParameterNames.NOMBRE_INGLES%> value="<%=ParameterUtils.printParam(parametros, ParameterNames.NOMBRE_INGLES, 0)%>">
               	<label>Caracteristicas del producto</label>
               	<label>Castellano</label>
               	<input type="text" name=<%=ParameterNames.CARACT_CASTELLANO%> value="<%=ParameterUtils.printParam(parametros, ParameterNames.CARACT_CASTELLANO, 0)%>">
               	<label>Inglés</label>
               	<input type="text" name=<%=ParameterNames.CARACT_INGLES%> value="<%=ParameterUtils.printParam(parametros, ParameterNames.CARACT_INGLES, 0)%>">
               	<label>Precio</label>
               	<input type="text" name=<%=ParameterNames.PRECIO%> value="<%=ParameterUtils.printParam(parametros, ParameterNames.PRECIO, 0)%>">
               	<label>Stock del producto</label>
               	<input type="text" name=<%=ParameterNames.STOCK%> value="<%=ParameterUtils.printParam(parametros, ParameterNames.STOCK, 0)%>">
               	<label>Selecciona la tienda</label>
               	 <label class="select form" for="tiendaProdCre">
               	 <select id="tiendaProdCre" name=<%=ParameterNames.ID_TIENDA%>>
               	<option disabled selected value>--selecciona una tienda--</option>
               	<% List<Tienda> tiendas = (List<Tienda>)request.getAttribute(AttributesNames.RESULTS_TIENDA);
               	for(Tienda t: tiendas){%>
               	<option value="<%=t.getId()%>"><%=t.getNombre()%></option>
               	<%}%>
               	</select></label>
               	<label>Oferta</label>
               	 <label class="select form" for="oferta">
               	<select name="oferta" id="oferta">
               	<option disabled selected value>--selecciona una oferta--</option>
               	<% List<Oferta> ofertas = (List<Oferta>)request.getAttribute(AttributesNames.OFERTAS);
               	for(Oferta o: ofertas){%>
               	<option value="<%=o.getId() %>"><%=o.getNombreOferta()%> tipo de oferta: <%=o.getNombreTipoOfer()%></option>
               	<%}%>
               	</select></label>
               <label>Selecciona la categoria</label>
                <label class="select form" for="categoria" style="display: none;">
               <select id="categoria" name="<%=ParameterNames.CATEGORIA%>">
          		
               </select></label>
                <label class="select form" for="categChild" style="display: none;">
               	<select id="categChild" name="<%=ParameterNames.CATEGORIA%>">
          
               </select>
               </label>
               	<label>Elige el origen el producto</label>
               	 <label class="select form" for="origen">
               	<select name=<%=ParameterNames.ORIGEN%> id="origen">
               	<%String origen = ParameterUtils.printParam(parametros, ParameterNames.ORIGEN, 0);%>
               		<option value="N" <%if("N".equals(origen)){%>checked<%}%>>Nacional</option>
               		<option value="I" <%if("I".equals(origen)){%>checked<%}%>>Internacional</option>
               		<option value="L" <%if("L".equals(origen)){%>checked<%}%>>Local</option>
               	</select></label>
				<input type="file" id="img" name="<%=ParameterNames.IMAGEN_PRINCIPAL%>" accept="image/*">
					<input type="file" id="img" name="<%=ParameterNames.IMAGEN_GALERIA%>" accept="image/*">
						<input type="file" id="img" name="<%=ParameterNames.IMAGEN_GALEIRA_SCD%>" accept="image/*">
               		<button class="Buscar" type="submit">crear producto</button>
               		</fieldset>
                </form>
                	

	
	

            </div>
        </section>
		<%@include file="/html/commons/usuario/footer.jsp"%>