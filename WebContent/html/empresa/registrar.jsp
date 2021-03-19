
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
          <form action=<%=UrlBuilder.builder(request, ViewPathsActions.EMPRESA_ACTION_REGISTRAR)%> method="post">
          		<input type="hidden" name="<%=ActionNames.ACTION%>" value="<%=ActionNames.REGISTRO%>">
          		<fieldset>
          		<label>Nombre usuario</label><br>
          		<input type="text" name="<%=ParameterNames.NOMBRE_USUARIO%>"><br>
          		<label>Apellidos</label><br>
          		<input type="text" name="<%=ParameterNames.APELLIDOS%>"><br>
          		<label>Alias</label><br>
          		<input type="text" name="<%=ParameterNames.AlIAS%>"><br>
          		</fieldset>
          		<fieldset>
          		<label>Contraseña</label>
          		<input type="password" name=<%=ParameterNames.PASSWORD%>>
          		</fieldset>
          		<fieldset>
          		<label>CIF</label><br>
          		<input type="text" name="<%=ParameterNames.CIF%>"><br>
          		<label>Razón social</label><br>
          		<input type="text" name="<%=ParameterNames.RAZON_SOCIAL%>"><br>
          		<label>Email</label><br>
          		<input type="text" name="<%=ParameterNames.EMAIL%>"><br>
          		</fieldset>
          		<fieldset>
          		<label>Calle</label><br>
          		<input type="text" name="<%=ParameterNames.CALLE%>"><br>
          		<label>Número</label><br>
          		<input type="text" name="<%=ParameterNames.NUMERO%>"><br>
          		<label>Piso</label><br>
          		<input type="text" name="<%=ParameterNames.PISO%>"><br>
          			<label>Código postal</label><br>
          		<input type="text" name="<%=ParameterNames.CODIGO_POSTAL%>"><br>
          		</fieldset>
          		<fieldset>
          		 <label>Pais  </label><br>
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