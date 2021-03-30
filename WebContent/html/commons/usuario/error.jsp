<%@page import="com.abastos.market.web.util.AttributesNames, com.abastos.market.web.controllers.*"%>

<%Errors errores = (Errors)request.getAttribute(AttributesNames.ERROR);
if(errores == null){
	errores = new Errors();
}
%>