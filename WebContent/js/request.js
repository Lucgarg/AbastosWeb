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
    	if(formSearch != null){
			url += "&buscar=true"
		}
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
			let firstOption = document.createElement("option");
				firstOption.text= "--Selecciona tienda---";
				firstOption.value="0";
				tienda.add(firstOption);
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
        var url= cont + "/lista?action=actualizar&idLista=" + selectLista.value + "&nombreCastellano=" + btoa(nombreProducto.innerHTML) + "&producto=" + buttonLista.name +
		"&precio=" + precioProducto.innerHTML +   "&ajax=true";
   
    request.open("GET", url, true);
      
    request.onreadystatechange;
    request.send(null);
    }


/*****Select categorias*****/
function selectCategorias(){
		remove(categoriaSelect);
		remove(categChild);
        request = createRequest();
        if(request == null){
            return;
        }
        let cont = createUrl();
        var url= cont + "/precreate?action=categoria&tienda=" + tiendaProCre.value +  "&ajax=true";
    
    request.open("GET", url, true);
      
    request.onreadystatechange = function(){createSelectCategoria()};
    request.send(null);
    }
function createSelectCategoria(){
	if(request.readyState == 4){
            if(request.status==200){	
		let categoria =  JSON.parse(request.responseText);
			let firstOptionCategoria = document.createElement("option");
				firstOptionCategoria.text= "--Selecciona categoria---";
				firstOptionCategoria.value="";
				categoriaSelect.add(firstOptionCategoria);
			for(let i= 0; i < categoria.length; i++){
				let optionCategoria = document.createElement("option");
			optionCategoria.text = categoria[i].nombre;
			optionCategoria.value = categoria[i].id;
			categoriaSelect.add(optionCategoria);
	}
}
}
}

/***Select categoria hija**/
function selectCategChild(){
		remove(categChild);
        request = createRequest();
        if(request == null){
            return;
        }
        let cont = createUrl();
        var url= cont + "/precreate?action=categoria&categoria=" + categoriaSelect.value +  "&ajax=true";
    
    request.open("GET", url, true);
      
    request.onreadystatechange = function(){createSelectCategChild()};
    request.send(null);
    }
function createSelectCategChild(){
	if(request.readyState == 4){
            if(request.status==200){	
		let categoria =  JSON.parse(request.responseText);
		if(categoria.length > 0){
			let firstOptionCategoria = document.createElement("option");
				if(categChild.length == 0){
				firstOptionCategoria.text= "--especifica categoria---";
				firstOptionCategoria.value="";
				categChild.add(firstOptionCategoria);
				}
			for(let i= 0; i < categChild.length; i++){
				let optionCategoria = document.createElement("option");
			optionCategoria.text = categoria[i].nombre;
			optionCategoria.value = categoria[i].id;
			categChild.add(optionCategoria);
	}
	}
}
}
}


/***Select productos*/
function selectProductos(){
		remove(productoOferta);
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
			let firstOptionProduct = document.createElement("option");
				firstOptionProduct.text= "--Selecciona producto---";
				firstOptionProduct.value="";
				productoOferta.add(firstOptionProduct);
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
    var url= cont + "/carrito?action=add&producto=" + e.currentTarget.name + "&numeroUnidades=" + parentInput.value + "&ajax=true";

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
                        remove(comunidad);
                        remove(provincia);
                        remove(localidad);
                    for(let i =0; i < palabra.length; i++ ){
                        
                        let option = document.createElement("option");
                        option.text = palabra[i].nombre;
                        option.value = palabra[i].id;
                        comunidad.add(option);	
                    }
                    }
                
                    else if(valor.name == "comunidad"){
                        remove(provincia);
                        remove(localidad);
                    for(let i =0; i < palabra.length; i++ ){
                        
                        let option = document.createElement("option");
                        option.text = palabra[i].nombre;
                        option.value = palabra[i].id;
                       provincia.add(option);	
                    }
                }
                    else if(valor.name == "provincia"){
                        remove(localidad);
                    for(let i =0; i < palabra.length; i++ ){
                            
                        let option = document.createElement("option");
                        option.text = palabra[i].nombre;
                        option.value = palabra[i].id;
                        localidad.add(option);	
                    }
                }
                
            }
            }
            }

 /*variables*/
let pais = document.getElementById("pais");
let comunidad = document.getElementById("comunidad");
let provincia = document.getElementById("provincia");
let localidad = document.getElementById("localidad");
let count = document.getElementById("count");
let carrito = document.getElementsByClassName("carritoCompra");
let tienda = document.getElementById("tiendaSelect");
let productoOferta = document.getElementById("productoOfertaSelect");
let tipoOferta = document.getElementById("tipOferta");
let selectLista = document.getElementById("selectLista");
let buttonLista = document.querySelector("#selectLista + input");
let nombreProducto = document.getElementById("NomPro");
let precioProducto = document.querySelector("#NomPro + span + p");
let textInfoCL = document.querySelector(".ofertaCL");
let campoSegUnd = document.getElementsByClassName("segundaUnidad");
let categoriaSelect = document.getElementById("categoria");
let categChild = document.getElementById("categChild");
let tiendaProCre = document.getElementById("tiendaProdCre");
let formSearch = document.getElementById("formSearch");
/******************
create elements
/**********************/

/*
 * flujo
 */
 if(pais != null){
 pais.onchange = getDetails;
 comunidad.onchange = getDetails;
provincia.onchange = getDetails;
 }
if(document.getElementsByClassName("carritoCompra") !=null){
	for(element of carrito){
		element.addEventListener("click", AddProducts);
	}

}
	if(tipoOferta != null){
	tipoOferta.onchange = function(){
	remove(tienda);
	if(tipoOferta.value == "3"){
	textInfoCL.style.display = "initial";
	selectTiendas();
	
	}
	if(tipoOferta.value == "2"){
	for (let item of campoSegUnd) {
	item.style.display = "initial";
	
	}
	}
	else{
	for (let item of campoSegUnd) {
	item.style.display = "none";
	
	}
	}
	}
	}
	if(tienda != null){
	tienda.onchange = selectProductos;
	}
	if(buttonLista != null){
	buttonLista.addEventListener("click", updateLista);	
	}
	if(tiendaProCre != null){
	tiendaProCre.onchange = selectCategorias;
	}
	if(categChild != null){
	categoriaSelect.onchange = selectCategChild;
	}
     