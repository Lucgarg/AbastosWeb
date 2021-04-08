<%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@include file= "/html/commons/usuario/header.jsp"%>
<section class="tiendas">
		<div class="tiendas2">
		<%Map<String, String[]> parametros = (Map<String, String[]>)request.getParameterMap();%>
	</section>
	<section>
		<nav>
		</nav>
	</section>
       <section class="productoDetalle registro">
        	<div>
          <form action=<%=UrlBuilder.getUrl(request, ControllerPath.PARTICULAR)%> method="post">
          		<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.REGISTRO%>">
          		<fieldset>
          		<%if(errores.printError(ActionNames.REGISTRO)!= null){%>
          			<p class="error"><%=errores.printError(ActionNames.REGISTRO)%></p>
          		<%}else if(errores.hasErrors()){%>
          		<p class="error"><%=ErrorNames.ERR_IN_FIELD%></p>
          		<%}%>
          		<label>Nombre usuario</label><br>
          		<input type="text" name="<%=ParameterNames.NOMBRE_USUARIO%>" value="<%=ParameterUtils.printParam(request, ParameterNames.NOMBRE_USUARIO, 0)%>"><br>
          		<%if(errores.printError(ParameterNames.NOMBRE_USUARIO)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.NOMBRE_USUARIO)%></p>
          		<%}%>
          		<label>Apellidos</label><br>
          		<input type="text" name="<%=ParameterNames.APELLIDOS%>" value="<%=ParameterUtils.printParam(request, ParameterNames.APELLIDOS, 0)%>"><br>
          		<%if(errores.printError(ParameterNames.APELLIDOS)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.APELLIDOS)%></p>
          		<%}%>
          		<label>Alias</label><br>
          		<input type="text" name="<%=ParameterNames.ALIAS%>" value="<%=ParameterUtils.printParam(request, ParameterNames.ALIAS, 0)%>"><br>
          		<%if(errores.printError(ParameterNames.ALIAS)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.ALIAS)%></p>
          		<%}%>
          		</fieldset>
          		<fieldset>
          		<label>Contraseña</label><br>
          		<input type="password" name=<%=ParameterNames.PASSWORD%> value="<%=ParameterUtils.printParam(request, ParameterNames.PASSWORD, 0)%>"><br>
          		<input type="password" name=<%=ParameterNames.PASSWORD%> value="<%=ParameterUtils.printParam(request, ParameterNames.PASSWORD, 1)%>"><br>
          			<%if(errores.printError(ParameterNames.PASSWORD)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.PASSWORD)%></p>
          		<%}%>
          			<label>Número de teléfono</label><br>
          		<input type="text" name=<%=ParameterNames.TELEFONO%> value="<%=ParameterUtils.printParam(request, ParameterNames.TELEFONO, 0)%>"><br>
          			<%if(errores.printError(ParameterNames.TELEFONO)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.TELEFONO)%></p>
          		<%}%>
          			<labe>Número de móvil</label><br>
          		<input type="text" name=<%=ParameterNames.MOVIL%> value="<%=ParameterUtils.printParam(request, ParameterNames.MOVIL, 0)%>"><br>
          			<%if(errores.printError(ParameterNames.MOVIL)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.MOVIL)%></p>
          		<%}%>
          		<label>Correo electrónico</label><br>
          		<input type="text" name="<%=ParameterNames.EMAIL%>" value="<%=ParameterUtils.printParam(request, ParameterNames.EMAIL, 0)%>"><br>
          			<%if(errores.printError(ParameterNames.EMAIL)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.EMAIL)%></p>
          		<%}%>
          		</fieldset>
          		<fieldset>
          		<label>Calle</label><br>
          		<input type="text" name="<%=ParameterNames.CALLE%>" value="<%=ParameterUtils.printParam(request, ParameterNames.CALLE, 0)%>"><br>
          			<%if(errores.printError(ParameterNames.CALLE)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.CALLE)%></p>
          		<%}%>
          		<label>Número</label><br>
          		<input type="text" name="<%=ParameterNames.NUMERO%>" value="<%=ParameterUtils.printParam(request, ParameterNames.NUMERO, 0)%>"><br>
          			<%if(errores.printError(ParameterNames.NUMERO)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.NUMERO)%></p>
          		<%}%>
          		<label>Piso</label><br>
          		<input type="text" name="<%=ParameterNames.PISO%>" value="<%=ParameterUtils.printParam(request, ParameterNames.PISO, 0)%>"><br>
          				<%if(errores.printError(ParameterNames.PISO)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.PISO)%></p>
          		<%}%>
          			<label>Código postal</label><br>
          		<input type="text" name="<%=ParameterNames.CODIGO_POSTAL%>" value="<%=ParameterUtils.printParam(request, ParameterNames.CODIGO_POSTAL, 0)%>"><br>
          			<%if(errores.printError(ParameterNames.CODIGO_POSTAL)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.CODIGO_POSTAL)%></p>
          		<%}%>
          		</fieldset>
          		<fieldset>
          		 <label>Pais</label><br>
          		<label class="select" for="pais">
                <select  name=<%=ParameterNames.PAIS%> id="pais">
                <option disabled selected value>--selecciona un pais--</option>
                <%List<Pais> paises = (List<Pais>) request.getAttribute(AttributesNames.PAISES); 
                for(Pais p: paises){ %>
                <option value="<%=p.getId()%>"><%=p.getNombre()%></option>
                <%}%>
                </select></label><br>
              	
                <label>Comunidad</label><br>
                	<label class="select" for="comunidad">
                 <select name=<%=ParameterNames.COMUNIDAD%> id="comunidad">
                          <option disabled selected value>--selecciona una comunidad--</option>
                </select></label><br>
                
                 <label>Provincia</label><br>
                 <label class="select" for="provincia">
                 <select name=<%=ParameterNames.PROVINCIA%> id="provincia">
                     
                <option disabled selected value>--selecciona una provincia--</option>
  				
                </select></label><br>
              
                <label>Localidad</label><br>
                <label class="select" for="localidad">
                <select name=<%=ParameterNames.LOCALIDAD%> id="localidad">
             	           <option disabled selected value>--selecciona una localidad--</option>
            		</select></label><br>
            	<label>se enviara un correo al email indicado para confirmar su registro</label><br>
           		<input type="submit" value="registrar"><br>
               
          		</fieldset>
          		 <br><button type="button" class="next">siguiente</button>
          		
          </form>
          </div>
       </section>
	<%@include file="/html/commons/usuario/footer.jsp"%>