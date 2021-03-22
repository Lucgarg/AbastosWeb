
<%@page import="com.abastos.market.web.util.*"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*" %>
<%@include file= "/html/commons/usuario/header.jsp"%>
	
	<section class="tiendas">
		<div class="tiendas2">
	</section>
	
	<section>
		<nav>
		</nav>
	</section>
	<section class ="productoDetalle"><div>
                <form action="<%=UrlBuilder.getUrl(request, ActionNames.PRODUCTO)%>" method="post">
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
		<%@include file="/html/commons/usuario/footer.jsp"%>