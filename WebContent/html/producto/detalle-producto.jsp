<%@page import="com.abastos.market.web.util.ParameterNames"%>
<%@page import="com.abastos.market.web.util.AttributesNames"%>
<%@page import="com.abastos.market.web.util.ActionNames"%>
<%@ page import="java.util.*, com.abastos.model.*"%>
<%@include file= "/html/commons/usuario/header.jsp"%>
<%@include file= "/html/commons/producto/right-nav.jsp"%>
<%@include file= "/html/commons/producto/left-nav.jsp"%>
	<section class ="productoDetalle"><div>
                <figure class="imagenProducto">
                    <input type="radio" id="prueba1" name="sli">
                    <input type="radio" id="prueba2" name="sli">
                    <input type="radio" id="prueba3" name="sli">
                    <ul>
                    <%Producto p = (Producto)request.getAttribute(AttributesNames.PRODUCTO);%>
                        <li><img src="<%=request.getContextPath()%>/imgs/productos/<%=p.getId()%>-principal.jpg" alt="" class="img1"></li>
                        <li><img src="<%=request.getContextPath()%>/imgs/productos/<%=p.getId()%>-galeria.jpg" alt="" class="img2"></li>
                       
                    </ul>
                    <figcaption>
                        <label for="prueba3"></label>
                        <label for="prueba2"></label>
                        <label for="prueba1"></label>
                  <ul>
                            <li><label for="prueba1"><img src="<%=request.getContextPath()%>/imgs/productos/<%=p.getId()%>-principal.jpg" alt="" class="img2"></label></li>
                            <li><label for="prueba2"><img src="<%=request.getContextPath()%>/imgs/productos/<%=p.getId()%>-galeria.jpg" alt="" class="img2"></label></li>
                          
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
                	   
                	  
                        <select name="<%=ParameterNames.LISTA%>" id="selectLista">
                        <%for(Lista l: listas){%>
                        	<option value="<%=l.getId()%>"><%=l.getNombre()%></option>
                        <%}%>
                        </select>
                        <input type="submit" name="<%=p.getId()%>" value="añadir a mi lista"/>
                    
                	<%}%>
            </div>
        </section>

<%@include file="/html/commons/usuario/footer.jsp"%>