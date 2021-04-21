<%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@include file= "/html/commons/usuario/header.jsp"%>
<section class="block search">
		<div class="block_second">
		
	</section>
	<section>
		<nav>
		</nav>
	</section>
       <section class="centralBlock">
        	<div>
          <form action=<%=UrlBuilder.getUrl(request, ControllerPath.EMPRESA)%> method="post">
		<%if(errores.printError(ActionNames.REGISTRO)!= null){%>
          			<p class="error"><%=errores.printError(ActionNames.REGISTRO)%></p>
          		<%}%>
          		<label>Nombre usuario</label>
          		<input type="text" name="<%=ParameterNames.NOMBRE_USUARIO%>" value=<%=empresa.getNombre()%>>
          		<label>Apellidos</label>
          		<input type="text" name="<%=ParameterNames.APELLIDOS%>" value=<%=empresa.getApellidos()%>>
          		<label>Alias</label>
          		<input type="text" name="<%=ParameterNames.ALIAS%>" value=<%=empresa.getAlias()%>>    		
          		<label>CIF</label>
          		<input type="text" name="<%=ParameterNames.CIF%>" value=<%=empresa.getCif()%>>
          		<label>Razón social</label>
          		<input type="text" name="<%=ParameterNames.RAZON_SOCIAL%>" value="<%=empresa.getRazonSocial()%>">
          		<label>Email</label>
          		<input type="text" name="<%=ParameterNames.EMAIL%>" value="<%=empresa.getCorreoElectronico()%>">
          		
          		
          		
          		<label>Direccion</label>
          		<%DireccionDto d = empresa.getDireccion();%>
          		<fieldset>
          		<legend>Tipo direccion: <%=d.getNombreDireccion()%></legend>
          		<p><%=new StringBuilder(d.getCalle()).append(", ").append("Número: ").append(d.getNumero())
          		.append(", ").append(d.getLocalidad()).append("(").append(d.getComunidadAutonoma())
          				.append("), ").append(d.getPais())%></p>
          		</fieldset>
          		
            	
               
          		
          		
          </form>
          </div>
       </section>
	<%@include file="/html/commons/usuario/footer.jsp"%>