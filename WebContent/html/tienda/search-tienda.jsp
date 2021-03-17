	  <%@page import="com.abastos.market.web.util.*, java.util.*, com.abastos.model.*"%>
	<%@include file= "/html/commons/usuario/header.jsp"%>
	  <div class="logo">
           <figure><img src="<%=UrlBuilder.builderImg(request, "logo.jpg")%>" alt=""></figure>
       </div>
       <section class="localizacion">
           <form action="<%=UrlBuilder.builder(request, ViewPathsServlet.TIENDA)%>" method="post">
           <input type="hidden" name=<%=ActionNames.ACTION%> value=<%=ActionNames.BUSCAR%>>
            <label class="select" for="pais">
               <select name=<%=ParameterNames.PAIS%> id="pais">
               <%List<Pais> paises = (List<Pais>)request.getAttribute(AttributesNames.PAISES);%>
            		
                    <option value="0">--selecciona un pais--</option>
                    <%for(Pais p: paises){%>
                    <option value=<%=p.getId()%>><%=p.getNombre()%></option>
                    <%}%>
               </select>
            </label>
            <label class="select" for="comunidad">
               <select name=<%=ParameterNames.COMUNIDAD%> id="comunidad">
                    <option value="0">--selecciona una comunidad--</option>
               </select>
            </label>
            <label class="select" for="provincia">
               <select name=<%=ParameterNames.PROVINCIA%> id="provincia">
                    <option value="0">--selecciona una Provincia--</option>
               </select>
            </label>
            <label class="select" for="localidad">
               <select name="<%=ParameterNames.LOCALIDAD%>" id="localidad">
                    <option value="0">--selecciona una localidad--</option>
                 
               </select>
               </label><br>
               <input type="submit" class="Buscar" value="Buscar">
           </form>
       </section>
<%@include file="/html/commons/usuario/footer.jsp"%>