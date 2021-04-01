
    <%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.* "%>
<%@include file= "/html/commons/usuario/header.jsp"%>
<section class="tiendas">
		<div class="tiendas2">
	</section>
	<%Map<String, String[]> parametros = (Map<String, String[]>)request.getParameterMap();%>

	<section>
		<nav>
		</nav>
	</section>
       <section class="productoDetalle registro">
        	<div>
          <form action=<%=UrlBuilder.getUrl(request, ControllerPath.EMPRESA)%> method="post">
          		<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.REGISTRO%>">
          		<fieldset>
          		<%if(errores.printError(ActionNames.REGISTRO)!= null){%>
          			<p class="error"><%=errores.printError(ActionNames.REGISTRO)%></p>
          		<%}%>
          		<label>Nombre usuario</label><br>
          		<input type="text" name="<%=ParameterNames.NOMBRE_USUARIO%>" <%=ParameterUtils.printParam(request, ParameterNames.NOMBRE_USUARIO, 0)%>><br>
          		<label>Apellidos</label><br>
          		<input type="text" name="<%=ParameterNames.APELLIDOS%>" value=<%=ParameterUtils.printParam(request, ParameterNames.APELLIDOS, 0)%>><br>
          		<label>Alias</label><br>
          		<input type="text" name="<%=ParameterNames.ALIAS%>" value=<%=ParameterUtils.printParam(request, ParameterNames.ALIAS, 0)%>><br>
          		</fieldset>
          		<fieldset>
          		<label>Contrase�a</label>
          		<input type="password" name=<%=ParameterNames.PASSWORD%> value=<%=ParameterUtils.printParam(request, ParameterNames.PASSWORD, 0)%>>
          		<input type="password" name=<%=ParameterNames.PASSWORD%> value=<%=ParameterUtils.printParam(request, ParameterNames.PASSWORD, 1)%>>
          		</fieldset>
          		<fieldset>
          		<label>CIF</label><br>
          		<input type="text" name="<%=ParameterNames.CIF%>" value=<%=ParameterUtils.printParam(request, ParameterNames.CIF, 0)%>><br>
          		<label>Raz�n social</label><br>
          		<input type="text" name="<%=ParameterNames.RAZON_SOCIAL%>" value="<%=ParameterUtils.printParam(request, ParameterNames.RAZON_SOCIAL, 0)%>"><br>
          		<label>Email</label><br>
          		<input type="text" name="<%=ParameterNames.EMAIL%>" value="<%=ParameterUtils.printParam(request, ParameterNames.EMAIL, 0)%>"><br>
          		</fieldset>
          		<fieldset>
          		<label>Calle</label><br>
          		<input type="text" name="<%=ParameterNames.CALLE%>" value="<%=ParameterUtils.printParam(request, ParameterNames.CALLE, 0)%>"><br>
          		<label>N�mero</label><br>
          		<input type="text" name="<%=ParameterNames.NUMERO%>" value="<%=ParameterUtils.printParam(request, ParameterNames.NUMERO, 0)%>"><br>
          		<label>Piso</label><br>
          		<input type="text" name="<%=ParameterNames.PISO%>" value="<%=ParameterUtils.printParam(request, ParameterNames.PISO, 0)%>"><br>
          			<label>C�digo postal</label><br>
          		<input type="text" name="<%=ParameterNames.CODIGO_POSTAL%>" value="<%=ParameterUtils.printParam(request, ParameterNames.CODIGO_POSTAL, 0)%>"><br>
          		</fieldset>
          		<fieldset>
          		 <label>Pais</label><br>
                <select  name=<%=ParameterNames.PAIS%> id="pais">
                <option disabled selected value>--selecciona un pais--</option>
                <%List<Pais> paises = (List<Pais>) request.getAttribute(AttributesNames.PAISES); 
                for(Pais p: paises){ %>
                <option value="<%=p.getId()%>"><%=p.getNombre()%></option>
                <%}%>
                </select><br>
              
                <label>Comunidad</label><br>
                 <select name=<%=ParameterNames.COMUNIDAD%> id="comunidad">
                          <option disabled selected value>--selecciona una comunidad--</option>
                </select><br>
                
                 <label>Provincia</label><br>
                 <select name=<%=ParameterNames.PROVINCIA%> id="provincia">
                     
                <option disabled selected value>--selecciona una provincia--</option>
  
                </select><br>
              
                <label>Localidad</label><br>
                <select name=<%=ParameterNames.LOCALIDAD%> id="localidad">
             	           <option disabled selected value>--selecciona una localidad--</option>
            		</select>
            	<label>se enviara un correo al email indicado para confirmar su registro</label><br>
           		<input type="submit" value="registrar"><br>
               
          		</fieldset>
          		 <br><button type="button" class="next">siguiente</button>
          		
          </form>
          </div>
       </section>
<%@include file="/html/commons/usuario/footer.jsp"%>