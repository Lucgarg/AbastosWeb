<%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@include file= "/html/commons/usuario/header.jsp"%>
<section class="tiendas">
		<div class="tiendas2">
	</section>
	<section>
		<nav>
		</nav>
	</section>
       <section class="productoDetalle">
        	<div>
        	<%String tienda = (String)request.getParameter(AttributesNames.TIENDA);
        	String producto = (String)request.getParameter(AttributesNames.PRODUCTO);
        	String pedido = (String)request.getParameter(AttributesNames.PEDIDO);
        	%>

 	<%if(tienda != null){%>
 	 <form action = "<%=UrlBuilder.builder(request, ViewPathsServlet.VALORACION)%>" class="valoracion">
 	 <input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.PUNTUACION_TIENDA%>">
 	 	 <input type="hidden" name="<%=ParameterNames.ID_TIENDA%>" value="<%=tienda%>">
 	 	  <input type="hidden" name="<%=ParameterNames.PEDIDO%>" value="<%=pedido%>">
        <fieldset>
            <legend>Atención al cliente</legend>
    <label class="star-blank"><input type="radio" id="1" value="1"  name="<%=ParameterNames.PUNTUACION_ATC%>"></label>
    <label class="star-blank"><input type="radio" id="2" value="2" name="<%=ParameterNames.PUNTUACION_ATC%>"></label>
    <label class="star-blank"><input type="radio" id="3" value="3"  name="<%=ParameterNames.PUNTUACION_ATC%>"></label>
    <label class="star-blank"><input type="radio" id="4" value="4" name="<%=ParameterNames.PUNTUACION_ATC%>"></label>
    <label class="star-blank"><input type="radio" id="5" value="5" name="<%=ParameterNames.PUNTUACION_ATC%>"></label>
</fieldset>
<fieldset>
    <legend>Atención a domicilio</legend>
<label class="star-blank"><input type="radio" id="6" value="1"name="<%=ParameterNames.PUNTUACION_ATD%>"></label>
<label class="star-blank"><input type="radio" id="7" value="2"name="<%=ParameterNames.PUNTUACION_ATD%>"></label>
<label class="star-blank"><input type="radio" id="8" value="3" name="<%=ParameterNames.PUNTUACION_ATD%>"></label>
<label class="star-blank"><input type="radio" id="9" value="4" name="<%=ParameterNames.PUNTUACION_ATD%>"></label>
<label class="star-blank"><input type="radio" id="10" value="5" name="<%=ParameterNames.PUNTUACION_ATD%>"></label>
</fieldset>
<fieldset>
    <legend>Precios</legend>
<label class="star-blank"><input type="radio" id="11" value="1" name="<%=ParameterNames.PUNTUACION_PRECIO%>"></label>
<label class="star-blank"><input type="radio" id="12" value="2"name="<%=ParameterNames.PUNTUACION_PRECIO%>"></label>
<label class="star-blank"><input type="radio" id="13" value="3"name="<%=ParameterNames.PUNTUACION_PRECIO%>"></label>
<label class="star-blank"><input type="radio" id="14" value="4"name="<%=ParameterNames.PUNTUACION_PRECIO%>"></label>
<label class="star-blank"><input type="radio" id="15" value="5" name="<%=ParameterNames.PUNTUACION_PRECIO%>"></label>
</fieldset>
<button type="submit">
</form>
<%}else{%>
<form action="<%=UrlBuilder.builder(request, ViewPathsServlet.VALORACION)%>" class="valoracion">
 <input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.PUNTUACION_PRODUCTO%>">
 	 <input type="hidden" name="<%=ParameterNames.ID_PRODUCTO%>" value="<%=producto%>">
 	  <input type="hidden" name="<%=ParameterNames.PEDIDO%>" value="<%=pedido%>">
<fieldset>
    <legend>Valorar producto</legend>
<label class="star-blank"><input type="radio" id="11" value="1" name="<%=ParameterNames.PUNTUACION_PRODUCTO%>"></label>
<label class="star-blank"><input type="radio" id="12" value="2"name="<%=ParameterNames.PUNTUACION_PRODUCTO%>"></label>
<label class="star-blank"><input type="radio" id="13" value="3"name="<%=ParameterNames.PUNTUACION_PRODUCTO%>"></label>
<label class="star-blank"><input type="radio" id="14" value="4"name="<%=ParameterNames.PUNTUACION_PRODUCTO%>"></label>
<label class="star-blank"><input type="radio" id="15" value="5" name="<%=ParameterNames.PUNTUACION_PRODUCTO%>"></label>
</fieldset>
<button type="submit">
</form>
<%}%>
    
          </div>
       </section>
	<%@include file="/html/commons/usuario/footer.jsp"%>