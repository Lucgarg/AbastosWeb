
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
		<%if(errores.printError(ActionNames.CREAR) != null){%>
		<p class="error"><%=errores.printError(ActionNames.CREAR)%></p>
		<%}%>
		<%if(errores.printError(ParameterNames.ERROR) != null){%>
		<p class="error"><%=errores.printError(ParameterNames.ERROR)%></p>
		<%}%>
                <form action="<%=UrlBuilder.getUrl(request, ControllerPath.PRODUCTO_PRIVATE)%>" method="post">
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
               	 <select id="tiendaProdCre" name=<%=ParameterNames.ID_TIENDA%>>
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
               <label>Selecciona la categoria</label>
               <select id="categoria" name="<%=ParameterNames.CATEGORIA%>">
          
               </select>
               	<select id="categChild" name="<%=ParameterNames.CATEGORIA%>">
          
               </select>
               	<label>Elige el origen el producto</label><br>
               	<select name=<%=ParameterNames.ORIGEN%>>
               		<option value="N">Nacional</option>
               		<option value="I">Internacional</option>
               		<option value="L">Local</option>
               	</select>
               	
               		<button class="Buscar" type="submit">crear producto</button>
                </form>
                
            </div>
        </section>
		<%@include file="/html/commons/usuario/footer.jsp"%>