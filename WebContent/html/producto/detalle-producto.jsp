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
                    <%Producto p = (Producto)request.getAttribute(AttributesNames.PRODUCTO);%>
                      	
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
                    <p><%=p.getPrecio()%></p>
                    <p><%=p.getCaracteristicas()%></p>
                    <form> <input type="number" id="quantity" name="quantity" min="1" max="5"> </form>
                    <p id= "Oferta"></p>
            
                   	<button class="Buscar" type="submit">añadir al carrito</button>
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