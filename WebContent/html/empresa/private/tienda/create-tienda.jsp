<%@page import="com.abastos.market.web.util.*"%>
<%@ page import="java.util.*, com.abastos.model.*, com.abastos.service.*" %>
<%@include file= "/html/commons/usuario/header.jsp"%>
	<section class="tiendas">
		<div class="tiendas2">
	</section>
	<section>
		<nav>
		</nav>
	</section>
	<section class ="productoDetalle"><div>
                <form action="<%=UrlBuilder.getUrl(request, ActionNames.TIENDA)%>" method="post">
                
                <input type="hidden"name=<%=ActionNames.ACTION%> value=<%=ActionNames.CREAR%>>
                <label>Nombre de la tienda</label><br>
                <input type="text" name=<%=ParameterNames.NOMBRE_TIENDA%>><br>
                <label>Móvil</label><br>
                <input type="text" name=<%=ParameterNames.MOVIL%>><br>
                <label>Teléfono</label><br>
                <input type="text" name=<%=ParameterNames.TELEFONO%>><br>
                <label>Correo electrónico</label><br>
                <input type="email" name=<%=ParameterNames.EMAIL%>><br>
                <label>Envio a domicilio</label><br>
                <input type="radio" name=<%=ParameterNames.ENVIO_DOMICILIO%> value="true">si</input>
                <input type="radio" name=<%=ParameterNames.ENVIO_DOMICILIO%> value="false">no</input><br>
                <label>Dirección</label><br>
                
                <label>Calle</label><br>
                <input type="text" name=<%=ParameterNames.CALLE%>><br>
                <label>Piso</label><br>]
                <input type="text" name=<%=ParameterNames.PISO%>><br>
				<label>Número</label><br>
				<input type="text" name=<%=ParameterNames.NUMERO%>><br>
				<label>Código Postal</label><br>
				<input type="text" name=<%=ParameterNames.CODIGO_POSTAL%>><br>
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
               	<label>Seleccionar categoria de la tienda</label>
               	<select name=<%=ParameterNames.CATEGORIA%>>
                      <%List<Categoria> categorias = (List<Categoria>) request.getAttribute(AttributesNames.CATEGORIAS); 
                for(Categoria cat: categorias){ %>
                <option value="<%=cat.getId()%>"><%=cat.getNombre()%></option>
                <%}%>
               	</select>
               	<input type="submit" value="crear">
                </form>
                
            </div>
        </section>
	<%@include file="/html/commons/usuario/footer.jsp"%>