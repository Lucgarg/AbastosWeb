<%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@include file= "/html/commons/usuario/header.jsp"%>
<section class="block">
		<div class="block_second">
		<%Map<String, String[]> parametros = (Map<String, String[]>)request.getParameterMap();%>
	</section>
	<section>
		<nav>
		</nav>
	</section>
       <section class="centralBlock">
        	<div>
          <form action=<%=UrlBuilder.getUrl(request, ControllerPath.PARTICULAR)%> method="post">
          		<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.REGISTRO%>">
          		
          		
          		<label>Nombre usuario</label>
          		<input type="text" name="<%=ParameterNames.NOMBRE_USUARIO%>" value="<%=particular.getNombre()%>">
          		
          		<label>Apellidos</label>
          		<input type="text" name="<%=ParameterNames.APELLIDOS%>" value="<%=particular.getApellidos()%>">
          		
          		<label>Alias</label>
          		<input type="text" name="<%=ParameterNames.ALIAS%>" value="<%=particular.getAlias()%>">
          		
          		
          	
          		
          			<label>Número de teléfono</label>
          		<input type="text" name=<%=ParameterNames.TELEFONO%> value="<%=ParameterUtils.printParam(particular.getNumeroTelefono())%>">
          			
          			<label>Número de móvil</label>
          		<input type="text" name=<%=ParameterNames.MOVIL%> value="<%=ParameterUtils.printParam(particular.getNumberoMovil())%>">
          			
          		<label>Correo electrónico</label>
          		<input type="text" name="<%=ParameterNames.EMAIL%>" value="<%=particular.getEmail()%>">
          		
          		
          		
          		<label>Direccion</label>
          		<%for(DireccionDto d: particular.getDireccionDto()){%>
          		<fieldset>
          		<legend>Tipo direccion: <%=d.getNombreDireccion()%></legend>
          		<p><%=new StringBuilder(d.getCalle()).append(", ").append("Número: ").append(d.getNumero())
          		.append(", ").append(d.getLocalidad()).append("(").append(d.getComunidadAutonoma())
          				.append("), ").append(d.getPais())%></p>
          		</fieldset>
          		<%}%>
            	
               
          		
          		
          </form>
          </div>
       </section>
	<%@include file="/html/commons/usuario/footer.jsp"%>