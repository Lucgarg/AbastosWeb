
    <%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.* "%>
<%@include file= "/html/commons/usuario/header.jsp"%>
<section class="block search">
		<div class="block_second">
	</section>
	<%Map<String, String[]> parametros = (Map<String, String[]>)request.getParameterMap();%>

	<section>
		<nav>
		</nav>
	</section>
       <section class="centralBlock registro">
        	<div>
          <form action=<%=UrlBuilder.getUrl(request, ControllerPath.EMPRESA)%> method="post" id="empresa">
          		<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.REGISTRO%>">
          		<fieldset>
          		<%if(errores.printError(ActionNames.REGISTRO)!= null){%>
          			<p class="error"><%=errores.printError(ActionNames.REGISTRO)%></p>
          		<%}%>
          		<label class="mandatory">Nombre usuario</label>
          		<input type="text" name="<%=ParameterNames.NOMBRE_USUARIO%>" <%=ParameterUtils.printParam(parametros, ParameterNames.NOMBRE_USUARIO, 0)%>>
          		<label class="mandatory">Apellidos</label>
          		<input type="text" name="<%=ParameterNames.APELLIDOS%>" value=<%=ParameterUtils.printParam(parametros, ParameterNames.APELLIDOS, 0)%>>
          		<label class="mandatory">Alias</label>
          		<input type="text" name="<%=ParameterNames.ALIAS%>" value=<%=ParameterUtils.printParam(parametros, ParameterNames.ALIAS, 0)%>>
          		</fieldset>
          		<fieldset>
          		<label class="mandatory">Contrase�a</label>
          		<input type="password" name=<%=ParameterNames.PASSWORD%> value=<%=ParameterUtils.printParam(parametros, ParameterNames.PASSWORD, 0)%>>
          		<input type="password" name=<%=ParameterNames.PASSWORD%> value=<%=ParameterUtils.printParam(parametros, ParameterNames.PASSWORD, 1)%>>
          		</fieldset>
          		<fieldset>
          		<label class="mandatory">CIF</label>
          		<input type="text" name="<%=ParameterNames.CIF%>" value=<%=ParameterUtils.printParam(parametros, ParameterNames.CIF, 0)%>>
          		<label class="mandatory">Raz�n social</label>
          		<input type="text" name="<%=ParameterNames.RAZON_SOCIAL%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.RAZON_SOCIAL, 0)%>">
          		<label class="mandatory">Email</label>
          		<input type="text" name="<%=ParameterNames.EMAIL%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.EMAIL, 0)%>">
          		</fieldset>
          		<fieldset>
          		<label class="mandatory">Calle</label>
          		<input type="text" name="<%=ParameterNames.CALLE%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.CALLE, 0)%>">
          		<label class="mandatory">N�mero</label>
          		<input type="text" name="<%=ParameterNames.NUMERO%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.NUMERO, 0)%>">
          		<label class="mandatory">Piso</label>
          		<input type="text" name="<%=ParameterNames.PISO%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.PISO, 0)%>">
          			<label class="mandatory">C�digo postal</label>
          		<input type="text" name="<%=ParameterNames.CODIGO_POSTAL%>" value="<%=ParameterUtils.printParam(parametros, ParameterNames.CODIGO_POSTAL, 0)%>">
          		</fieldset>
          		<fieldset>
          		 <label>Pais</label>
          		   <label class="select form" for="pais">
                <select  name=<%=ParameterNames.PAIS%> id="pais">
                <option value="0">--selecciona un pais--</option>
                <%List<Pais> paises = (List<Pais>) request.getAttribute(AttributesNames.PAISES); 
                for(Pais p: paises){ %>
                <option value="<%=p.getId()%>"><%=p.getNombre()%></option>
                <%}%>
                </select></label>
              
                <label>Comunidad</label>
                 <label class="select form" for="comunidad">
                 <select name=<%=ParameterNames.COMUNIDAD%> id="comunidad">
                          <option value="0">--selecciona una comunidad--</option>
                </select></label>
                
                 <label>Provincia</label>
                  <label class="select form" for="provincia">
                 <select name=<%=ParameterNames.PROVINCIA%> id="provincia">
                     
                <option value="0">--selecciona una provincia--</option>
  
                </select></label>
              
                <label class="mandatory">Localidad</label>
                 <label class="select form" for="localidad">
                <select name=<%=ParameterNames.LOCALIDAD%> id="localidad">
             	           <option value="0">--selecciona una localidad--</option>
            		</select></label>
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