
<%@page import="com.abastos.market.web.util.*"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*" %>
<%@include file= "/html/commons/usuario/header.jsp"%>
	
	<section class="block">
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
                <form action="<%=UrlBuilder.getUrl(request, ControllerPath.PRODUCTO_PRIVATE)%>" method="post">
                <input type="hidden"name=<%=ActionNames.ACTION%> value=<%=ActionNames.CREAR%>>
                <fieldset class="centralBlock_form">
               	<label>Nombre producto</label>
               	<label>Castellano</label>
               	<input type="text" name=<%=ParameterNames.NOMBRE_CASTELLANO%>>
               	<label>Inglés</label>
               	<input type="text" name=<%=ParameterNames.NOMBRE_INGLES%>>
               	<label>Caracteristicas del producto</label>
               	<label>Castellano</label>
               	<input type="text" name=<%=ParameterNames.CARACT_CASTELLANO%>>
               	<label>Inglés</label>
               	<input type="text" name=<%=ParameterNames.CARACT_INGLES%>>
               	<label>Precio</label>
               	<input type="text" name=<%=ParameterNames.PRECIO%>>
               	<label>Stock del producto</label>
               	<input type="text" name=<%=ParameterNames.STOCK%>>
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
               		<option value="N">Nacional</option>
               		<option value="I">Internacional</option>
               		<option value="L">Local</option>
               	</select></label>
               	
               		<button class="Buscar" type="submit">crear producto</button>
               		</fieldset>
                </form>
                
            </div>
        </section>
		<%@include file="/html/commons/usuario/footer.jsp"%>