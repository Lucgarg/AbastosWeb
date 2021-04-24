  <%@page import="com.abastos.market.web.util.SessionManager"%>
<%@page import="com.abastos.market.web.util.AttributesNames"%>
<%@page import="com.abastos.market.web.util.ParameterNames"%>
<%@page import="com.abastos.market.web.util.ActionNames"%>
<%@page import="com.abastos.market.web.util.ControllerPath"%>
<%@page import="com.abastos.market.web.util.UrlBuilder"%>
<%@page import="com.abastos.model.*"%>
<section class="block search">
		<div class="block_second">
			<input type="checkbox">
			<h1>Mi pedido</h1>
			
			<div>
				<div class="block_second--detail cart">
				 
					<p>Precio final</p>
					<p><%=pedido.getPrecioTotal()%></p>
					<form action="<%=UrlBuilder.getUrl(request,ControllerPath.PEDIDO)%>" method="post">
					<input type="hidden" name="<%=ParameterNames.URL%>" value="<%=UrlBuilder.urlCallBack(request, false)%>">
					<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.CREAR%>">
					<%if(particular!=null){%>
					<label>Tienes <%=particular.getPuntos()%> puntos para gastar</label><br>
					<label>Aplicar descuento</label>
					<input type="checkbox" name="<%=ParameterNames.APLICAR_DESCUENTO%>"><br>
					<%}%>
					<button type="submit"  class="Buscar">Finalizar compra</button>
					</form>
				</div>
			
			</div>
	</section>