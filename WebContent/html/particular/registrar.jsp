<%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
<%@include file= "/html/commons/usuario/header.jsp"%>
<section class="block search">
		<div class="block_second">
		<%Map<String, String[]> parametros = (Map<String, String[]>)request.getParameterMap();%>
	</section>
	<section>
		<nav>
		</nav>
	</section>
       <section class="centralBlock registro">
        	<div>
        
          <form action=<%=UrlBuilder.getUrl(request, ControllerPath.PARTICULAR)%> method="post">
          		<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.REGISTRO%>">
          		<fieldset>
          		<%if(errores.printError(ActionNames.REGISTRO)!= null){%>
          			<p class="error"><%=errores.printError(ActionNames.REGISTRO)%></p>
          		<%}else if(errores.hasErrors()){%>
          		<p class="error"><%=ErrorNames.ERR_IN_FIELD%></p>
          		<%}%>
          		<label class="mandatory">Nombre usuario</label>
          		<input type="text" name="<%=ParameterNames.NOMBRE_USUARIO%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.NOMBRE_USUARIO, 0)%>">
          		<%if(errores.printError(ParameterNames.NOMBRE_USUARIO)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.NOMBRE_USUARIO)%></p>
          		<%}%>
          		<label class="mandatory">Apellidos</label>
          		<input type="text" name="<%=ParameterNames.APELLIDOS%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.APELLIDOS, 0)%>">
          		<%if(errores.printError(ParameterNames.APELLIDOS)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.APELLIDOS)%></p>
          		<%}%>
          		<label class="mandatory">Alias</label>
          		<input type="text" name="<%=ParameterNames.ALIAS%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.ALIAS, 0)%>">
          		<%if(errores.printError(ParameterNames.ALIAS)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.ALIAS)%></p>
          		<%}%>
          		</fieldset>
          		<fieldset>
          		<label class="mandatory">Contraseña</label>
          		<input type="password" name=<%=ParameterNames.PASSWORD%> value="<%=ParameterUtils.printParam(parametros, ParameterNames.PASSWORD, 0)%>">
          		<input type="password" name=<%=ParameterNames.PASSWORD%> value="<%=ParameterUtils.printParam(parametros, ParameterNames.PASSWORD, 1)%>">
          			<%if(errores.printError(ParameterNames.PASSWORD)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.PASSWORD)%></p>
          		<%}%>
          		<label class="mandatory">Información de contacto</label>
          			<label>Número de teléfono</label>
          		<input type="text" name=<%=ParameterNames.TELEFONO%> value="<%=ParameterUtils.printParam(parametros, ParameterNames.TELEFONO, 0)%>">
          			<%if(errores.printError(ParameterNames.TELEFONO)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.TELEFONO)%></p>
          		<%}%>
          			<label>Número de móvil</label>
          		<input type="text" name=<%=ParameterNames.MOVIL%> value="<%=ParameterUtils.printParam(parametros, ParameterNames.MOVIL, 0)%>">
          			<%if(errores.printError(ParameterNames.MOVIL)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.MOVIL)%></p>
          		<%}%>
          			<%if(errores.printError(ParameterNames.CONTACTO)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.CONTACTO)%></p>
          		<%}%>
          		<label class="mandatory">Correo electrónico</label>
          		<input type="text" name="<%=ParameterNames.EMAIL%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.EMAIL, 0)%>">
          			<%if(errores.printError(ParameterNames.EMAIL)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.EMAIL)%></p>
          		<%}%>
          		</fieldset>
          		<fieldset>
          		<label class="mandatory">Calle</label>
          		<input type="text" name="<%=ParameterNames.CALLE%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.CALLE, 0)%>">
          			<%if(errores.printError(ParameterNames.CALLE)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.CALLE)%></p>
          		<%}%>
          		<label class="mandatory">Número</label>
          		<input type="text" name="<%=ParameterNames.NUMERO%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.NUMERO, 0)%>">
          			<%if(errores.printError(ParameterNames.NUMERO)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.NUMERO)%></p>
          		<%}%>
          		<label class="mandatory">Piso</label>
          		<input type="text" name="<%=ParameterNames.PISO%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.PISO, 0)%>">
          				<%if(errores.printError(ParameterNames.PISO)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.PISO)%></p>
          		<%}%>
          			<label class="mandatory">Código postal</label>
          		<input type="text" name="<%=ParameterNames.CODIGO_POSTAL%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.CODIGO_POSTAL, 0)%>">
          			<%if(errores.printError(ParameterNames.CODIGO_POSTAL)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.CODIGO_POSTAL)%></p>
          		<%}%>
          		</fieldset>
          		<fieldset>
          		 <label>Pais</label>
          		<label class="select form" for="pais">
                <select  name=<%=ParameterNames.PAIS%> id="pais">
                <option disabled selected value>--selecciona un pais--</option>
                <%List<Pais> paises = (List<Pais>) request.getAttribute(AttributesNames.PAISES); 
                for(Pais p: paises){ %>
                <option value="<%=p.getId()%>"><%=p.getNombre()%></option>
                <%}%>
                </select></label>
              	
                <label>Comunidad</label>
                	<label class="select form" for="comunidad">
                 <select name=<%=ParameterNames.COMUNIDAD%> id="comunidad">
                          <option disabled selected value>--selecciona una comunidad--</option>
                </select></label>
                
                 <label>Provincia</label>
                 <label class="select form" for="provincia">
                 <select name=<%=ParameterNames.PROVINCIA%> id="provincia">
                     
                <option disabled selected value>--selecciona una provincia--</option>
  				
                </select></label>
              
                <label class="mandatory">Localidad</label>
                <label class="select form" for="localidad">
                <select name=<%=ParameterNames.LOCALIDAD%> id="localidad">
             	           <option disabled selected value>--selecciona una localidad--</option>
             	           
            		</select></label>
            	<%if(errores.printError(ParameterNames.LOCALIDAD)!= null){%>
          			<p class="error"><%=errores.printError(ParameterNames.LOCALIDAD)%></p>
          		<%}%>
            	<label>se enviara un correo al email indicado para confirmar su registro</label>
           		<button type="submit" value="registrar" class="Buscar">Registrar</button>
               
          		</fieldset>
          		<div id="buttons">
          		 <button type="button" class="next Buscar">siguiente</button>
          		</div>
          </form>
          </div>
       </section>
	<%@include file="/html/commons/usuario/footer.jsp"%>