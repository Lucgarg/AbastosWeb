<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*, com.abastos.market.web.util.*"%>
<%@include file= "/html/commons/usuario/header.jsp"%>
	<section class="tiendas">
		<div class="tiendas2">
	</section>
	<section>
		<nav>
		</nav>
	</section>
	<section class ="productoDetalle"><div>
                <form action="<%=UrlBuilder.builder(request, ActionNames.OFERTA)%>" method="post">
                <input type="hidden"name=<%=ActionNames.ACTION%> value=<%=ActionNames.CREAR%>>
               	<label>Nombre</label><br>
               	<input type="text" name=<%=ParameterNames.NOMBRE_OFERTA%>><br>
               	<label>Elije un tipo de descuento</label><br>
               	<select name="tipoOferta" id="tipOferta">
               	<%List<TipoOferta> tipOfert = (List<TipoOferta>) request.getAttribute(AttributesNames.TIPO);
               	for(TipoOferta tp: tipOfert){
               	%>
               		<option value="<%=tp.getId()%>"><%=tp.getNombre()%></option>
               		<%}%>
               	</select><br>
               	<label class="ofertaCL">Elige tienda(este tipo de oferta sólo se podrá aplicar en una tienda)</label>
               	<select name="<%=ParameterNames.ID_TIENDA%>" id="tiendaSelect"></select>
               	<select name="<%=ParameterNames.PRODUCTO_OFERTA%>" id="productoOfertaSelect"></select>
               	</select>
               	<label>Elije uno de los dos: </label><br>
               	   	<label>descuento porcentual</label><br>
               	<input type="text" name=<%=ParameterNames.DESCT_PCN%>><br>
               	   	<label>descuento fijo</label><br>
               	<input type="text" name=<%=ParameterNames.DESCT_FIJO%>><br>
               	<label>En caso de elegir el tipo de oferta "segunda unidad"</label><br>
               	   	<label>Número mínimo de unidades con precio sin descuento</label><br>
               	<input type="text" name=<%=ParameterNames.NUMERADOR%>><br>
               	   	<label>Número de unidades necesarias para recibir un descuento</label><br>
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
<%@include file="/html/commons/usuario/footer.jsp"%>