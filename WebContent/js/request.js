/**
 * functions
 */
/*function para eliminar opciones de select*/
 function remove(valor){
     
 
    for(let i = valor.options.length-1; i >= 1 ; i--){
        valor.options[i].remove();
    }

}
/*function para crear url */
function createUrl(){
    let origin  = location.origin;
    let host = location.host;
    let pathname = location.pathname.split("/")[1];
    let cont =   origin +  "/" +  pathname;
    return cont;
    }
/*función para ajax */
function createRequest(){
        try{
            request = new XMLHttpRequest();
            }catch(e){
                try{
                    request = new ActiveXObject("Microsoft.XMLHTTP");
                    
                }catch(e){
                    request = null;
                }
            }
            return request;
        
    }
/*function para localizacion */ 
function getDetails(e){
        request = createRequest();
        if(request == null){
            return;
        }
        let cont = createUrl();
        var url= cont + "/localizacion?action="+ e.currentTarget.id + "&"+ e.currentTarget.id + "=" + e.currentTarget.value;
    
    request.open("GET", url, true);
        let guardar = e.currentTarget;
    request.onreadystatechange = function(){displayDetails(guardar)};
    request.send(null);
    }

/*function para productoOferta*/

/***Select tiendas*/
function selectTiendas(){
        request = createRequest();
        if(request == null){
            return;
        }
        let cont = createUrl();
        var url= cont + "/tienda?action=buscar&ajax=true";
    
    request.open("GET", url, true);
      
    request.onreadystatechange = function(){createSelectTienda()};
    request.send(null);
    }
function createSelectTienda(){
if(request.readyState == 4){
        
            if(request.status==200){
		 
		
		let tiendas =  JSON.parse(request.responseText);
				
			for(let i= 0; i < tiendas.length; i++){
				let optionTienda = document.createElement("option");
			optionTienda.text = tiendas[i].nombre;
			optionTienda.value = tiendas[i].id;
			tienda.add(optionTienda);
	}
}
}
}
/***Select lista */
function updateLista(){
		
        request = createRequest();
        if(request == null){
            return;
        }
        let cont = createUrl();
        var url= cont + "/lista?action=actualizar&idLista=" + selectLista.value + "&nombreCastellano=" + nombreProducto.innerHTML + "&producto=" + buttonLista.name +
		"&precio=" + precioProducto.innerHTML +   "&ajax=true";
    
    request.open("GET", url, true);
      
    request.onreadystatechange;
    request.send(null);
    }



/***Select productos*/
function selectProductos(){
		
        request = createRequest();
        if(request == null){
            return;
        }
        let cont = createUrl();
        var url= cont + "/producto?action=buscar&tienda=" + tienda.value +  "&ajax=true";
    
    request.open("GET", url, true);
      
    request.onreadystatechange = function(){createSelectProducto()};
    request.send(null);
    }
function createSelectProducto(){
if(request.readyState == 4){
        
            if(request.status==200){
		 
		
		let productos =  JSON.parse(request.responseText);
				
			for(let i= 0; i < productos.length; i++){
				let optionTienda = document.createElement("option");
			optionTienda.text = productos[i].nombre;
			optionTienda.value = productos[i].id;
			productoOferta.add(optionTienda);
	}
}
}
}

/*function para carrito */
function AddProducts(e){
    request = createRequest();
    if(request == null){
        return;
    }
	let parentInput = e.currentTarget.parentElement.querySelector("input");
    let cont = createUrl();
    var url= cont + "/pedido?action=add&producto=" + e.currentTarget.name + "&numeroUnidades=" + parentInput.value;

request.open("GET", url, true);
    let guardar = e.currentTarget;
    request.onreadystatechange = function(){countLinea(guardar)};
request.send(null);
}

function countLinea(valor){
	if(request.readyState == 4){
        
            if(request.status==200){
		 
		count.style.display = "initial";
		let linea =  JSON.parse(request.responseText);
				
		count.innerHTML = linea;
	}
}
}

/*funcion para rellenar selects de localizacion */
    function displayDetails(valor){
     
        if(request.readyState == 4){
        
            if(request.status==200){
                    
                    let palabra = JSON.parse(request.responseText);
                    if(valor.name == "pais"){
                        remove(document.getElementById("comunidad"));
                        remove(document.getElementById("provincia"));
                        remove(document.getElementById("localidad"));
                    for(let i =0; i < palabra.length; i++ ){
                        
                        let option = document.createElement("option");
                        option.text = palabra[i].nombre;
                        option.value = palabra[i].id;
                        document.getElementById("comunidad").add(option);	
                    }
                    }
                
                    else if(valor.name == "comunidad"){
                        remove(document.getElementById("provincia"));
                        remove(document.getElementById("localidad"));
                    for(let i =0; i < palabra.length; i++ ){
                        
                        let option = document.createElement("option");
                        option.text = palabra[i].nombre;
                        option.value = palabra[i].id;
                        document.getElementById("provincia").add(option);	
                    }
                }
                    else if(valor.name == "provincia"){
                        remove(document.getElementById("localidad"));
                    for(let i =0; i < palabra.length; i++ ){
                            
                        let option = document.createElement("option");
                        option.text = palabra[i].nombre;
                        option.value = palabra[i].id;
                        document.getElementById("localidad").add(option);	
                    }
                }
                
            }
            }
            }

 /*variables*/

let count = document.getElementById("count");
let carrito = document.getElementsByClassName("carritoCompra");
let tienda = document.getElementById("tiendaSelect");
let productoOferta = document.getElementById("productoOfertaSelect");
let tipoOferta = document.getElementById("tipOferta");
let selectLista = document.getElementById("selectLista");
let buttonLista = document.querySelector("#selectLista + input");
let nombreProducto = document.getElementById("NomPro");
let precioProducto = document.querySelector("#NomPro + span + p");
/******************
create elements
/**********************/

/*
 * flujo
 */
 if(document.getElementById("pais") != null){
 document.getElementById("pais").onchange = getDetails;
 document.getElementById("comunidad").onchange = getDetails;
 document.getElementById("provincia").onchange = getDetails;
 }
if(document.getElementsByClassName("carritoCompra") !=null){
	for(element of carrito){
		element.addEventListener("click", AddProducts);
	}

}
	if(tipoOferta != null){
	tipoOferta.onchange = function(){
		
	if(tipoOferta.value == "3"){
		
	selectTiendas();
	
	}
	}
	}
	if(tienda != null){
	tienda.onchange = selectProductos;
	}
	if(buttonLista != null){
	buttonLista.addEventListener("click", updateLista);	
	}

     