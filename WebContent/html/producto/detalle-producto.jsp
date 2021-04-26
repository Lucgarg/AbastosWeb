<%@page import="com.abastos.market.web.util.ParameterNames"%>
<%@page import="com.abastos.market.web.util.AttributesNames"%>
<%@page import="com.abastos.market.web.util.ActionNames"%>
<%@ page import="java.util.*, com.abastos.model.*"%>
<%@include file= "/html/commons/usuario/header.jsp"%>
<%@include file= "/html/commons/producto/right-nav.jsp"%>
<%@include file= "/html/commons/producto/left-nav.jsp"%>
	<section class="centralBlock"><div>
                <figure class="imagenProducto">
                    <input type="radio" id="prueba1" name="sli">
                    <input type="radio" id="prueba2" name="sli">
                    <input type="radio" id="prueba3" name="sli">
                    <ul>
                    <%Producto p = (Producto)request.getAttribute(AttributesNames.PRODUCTO);
                    	Producto proOfert = (Producto)request.getAttribute(AttributesNames.PRODUCTO_OFERTA);
                    %>
                      	
                        <li> <img src="<%=UrlBuilder.getUrlforImg(request, ParameterNames.IMAGEN_PRINCIPAL, WebConstants.DIRECTORY_PRODUCTO, String.valueOf(p.getId()))%>" alt="" class="img1"></li>
                        <li><img src="<%=UrlBuilder.getUrlforImg(request, ParameterNames.IMAGEN_GALERIA, WebConstants.DIRECTORY_PRODUCTO, String.valueOf(p.getId()))%>" alt="" class="img2"></li>
                          <li><img src="<%=UrlBuilder.getUrlforImg(request, ParameterNames.IMAGEN_GALEIRA_SCD, WebConstants.DIRECTORY_PRODUCTO, String.valueOf(p.getId()))%>" alt="" class="img2"></li>
                       
                    </ul>
                    <figcaption>
                        <label for="prueba3"></label>
                        <label for="prueba2"></label>
                        <label for="prueba1"></label>
                  <ul>
                            <li><label for="prueba1"><img src="<%=UrlBuilder.getUrlforImg(request, ParameterNames.IMAGEN_PRINCIPAL, WebConstants.DIRECTORY_PRODUCTO, String.valueOf(p.getId()))%>" alt="" class="img2"></label></li>
                            <li><label for="prueba2"><img src="<%=UrlBuilder.getUrlforImg(request, ParameterNames.IMAGEN_GALERIA, WebConstants.DIRECTORY_PRODUCTO, String.valueOf(p.getId()))%>" alt="" class="img2"></label></li>
                        	  <li><label for="prueba3"><img src="<%=UrlBuilder.getUrlforImg(request, ParameterNames.IMAGEN_GALEIRA_SCD, WebConstants.DIRECTORY_PRODUCTO, String.valueOf(p.getId()))%>" alt="" class="img2"></label></li>
                </ul> 
                    </figcaption>
                </figure> <div class="informacionProducto">
                    <p id="NomPro"><%=p.getNombre()%></p>
                    <span><%=p.getValoracion() %></span>
                   
                   	<%
					if(p.getOferta() != null){
																	if(p.getOferta().getIdTipoOferta()==1){
								%>
			<p class="precio"><%=p.getPrecio()%></p>
			<%
				}else{
				%>
			<p class="precioNoMostrado"></p>
			<%
  				}
  				%>
		

			<div>
				<p><%=p.getOferta().getNombreOferta()%></p>
				<%
					 if(p.getOferta().getIdTipoOferta() == 1){
					 %>
				<p>
					-<%
					 	if(p.getOferta().getDescuentoFijo() !=0.0){
					 	%>
					<%=p.getOferta().getDescuentoFijo()%>
					&euro;
					<%
					 			  }else{
					 			  %>
					<%=p.getOferta().getDescuentoPcn()%>
					%
					<%
					 }
					 %>
				</p>
				<%
					 	}
					 				 				 if(p.getOferta().getIdTipoOferta() == 2){
					 	%>
				<p><%=p.getOferta().getDenominador()%>
					unidad -
					<%
					 	if(p.getOferta().getDescuentoFijo() != 0.0){
					 	%>
					<%=p.getOferta().getDescuentoFijo()%>
					&euro;
					<%
					 			  }else{
					 			  %>
					<%=p.getOferta().getDescuentoPcn()%>
					%

					<% } %>
				</p>
				<%
					 }
					 			 			 if(p.getOferta().getIdTipoOferta() == 3){
					 %>
				<p>
					Compra y ahorrate del producto
					<a href="<%=UrlBuilder.getUrlForController(request, ControllerPath.PRODUCTO, ActionNames.DETALLE, true, ParameterNames.ID_PRODUCTO, String.valueOf(proOfert.getId()))%>">
					<%=proOfert.getNombre()%></a>

					<%
 					 						 			if(p.getOferta().getDescuentoFijo() != 0.0){
 					 						 			%>
					<%=p.getOferta().getDescuentoFijo()%>
					&euro;
					<%
					 			}else{
					 			%>
					<%=p.getOferta().getDescuentoPcn()%>
					%
					<%
					 }
					 %>
				</p>
				<%
					 }
					 %>
			</div>
			<%
				 }
				 %>

			<p><%=p.getPrecioFinal()%></p>
		
                   
                   
                    <p><%=p.getCaracteristicas()%></p>
                    <%if(empresa == null){%>
                    <form> <input type="number" min="1" max="<%=p.getStock()%>" name="<%=ParameterNames.NUMERO_UNIDADES%>">
				<button type="button" class="carritoCompra" name="<%=p.getId()%>"></button>
                     </form>
               		<%}%>
            
                  
                </div>
                	<%if(particular!= null){%>
                	<%List<Lista> listas = (List<Lista>)request.getAttribute(AttributesNames.LISTA);%>
                	   
                	  <label class="select form" for="selectLista">
                        <select name="<%=ParameterNames.LISTA%>" id="selectLista">
                        <%for(Lista l: listas){%>
                        	<option value="<%=l.getId()%>"><%=l.getNombre()%></option>
                        <%}%>
                        </select>
                        </label>
                        <button class="Buscar" type="submit" id="submitLista" name="<%=p.getId()%>" value="añadir a mi lista"/>añadir a la lista</button>
                    
                	<%}%>
            </div>
        </section>

<%@include file="/html/commons/usuario/footer.jsp"%>