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
                    <form action="">
                        <select name="" id="">
                            
                            <option value="">lista1</option>
                            <option value="">lista2</option>
                            <option value="">lista3</option>
                        </select>
                        <input type="submit" value="añadir a mi lista"/>
                    </form>
                    <input type="submit" value="añadir al carrito"/>
                </div>
                
            </div>
        </section>

<%@include file="/html/commons/usuario/footer.jsp"%>