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
/*function para localizacion se pasa el valor del id que sirbe como referencia para la peticion y como parametro al que se le asocia el valor*/ 
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

/*function para productoOferta  en el formulario de creacion de oferta*/

/***Select tiendas*/
function selectTiendas(){
        request = createRequest();
        if(request == null){
            return;
        }
        let cont = createUrl();
        var url= cont + "/tienda-private?action=buscar&ajax=true";
    
    request.open("GET", url, true);
      
    request.onreadystatechange = function(){createSelectTienda()};
    request.send(null);
    }
    //creacion de select de tiendas en formulario creacion de oferta
function createSelectTienda(){
if(request.readyState == 4){
        
            if(request.status==200){
		 
		
		let tiendas =  JSON.parse(request.responseText);
		let tiendaFather = tienda.parentElement;
		tiendaFather.style.display = "inline-block";
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
	else{
			error.innerHTML = "No ha sido posible realizar la busqueda";
			formCreate.insertAdjacentElement("beforebegin", error);
		}
}
}

/***Select lista para el detalle de un producto cuando el usuario particular esta en sesión, permite añadir productos a la lista de deseos sin necesidad de volver a recargar la página*/
function updateLista(){
		
        request = createRequest();
        if(request == null){
            return;
        }
        let cont = createUrl();
        var url= cont + "/lista?action=actualizar&idLista=" + selectLista.value + "&nombreCastellano=" + btoa(nombreProducto.innerHTML) + "&producto=" + buttonLista.name +
		"&precio=" + precioProducto.innerHTML +   "&ajax=true";
   
    request.open("GET", url, true);
      
    request.onreadystatechange = function(){
	if(request.readyState == 4){
            if(request.status==200){
		let result =  JSON.parse(request.responseText);
		if(result == "true"){
		duplicate.remove;
		divLista.insertAdjacentElement("beforeend",addLista);
		}
		else{
			addLista.remove;
			divLista.insertAdjacentElement("beforeend",duplicate);
		}
}
}};
	
    request.send(null);
    }


/*****Select categorias para formulario de creacion de productos***/
function selectCategorias(){
//primero se eliminan las ofertas para evitar que se produzcan resultados no deseados cuando se elige otra categoria
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
    //creacion del select de categoria
function createSelectCategoria(){
	if(request.readyState == 4){
            if(request.status==200){	
		let categoria =  JSON.parse(request.responseText);
		let categoriaFather = categoriaSelect.parentElement;
		categoriaFather.style.display = "inline-block";
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
	else{
			error.innerHTML = "No ha sido posible realizar la busqueda";
			formCreate.insertAdjacentElement("beforebegin", error);
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
		let categFather = categChild.parentElement;
		categFather.style.display = "inline-block";
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
	else{
			error.innerHTML = "No ha sido posible realizar la busqueda";
			formCreate.insertAdjacentElement("beforebegin", error);
		}
}
}

/**************Funcion recordar usuario****************/
function remenberName(){
nameUser.value = "";
 $.post("usuario", {
 action: "recordarNombre",
 tipUsuario: inputTipParticular.checked?"particular":"empresa",
 ajax: true,
 
 
 }, function(json){
		nameUser.value=json;
 })
}



/***Select productos para el formulario de creacion de oferta, se muestran todos los productos de una determinada tienda*/
function selectProductos(){
		remove(productoOferta);
        request = createRequest();
        if(request == null){
            return;
        }
        let cont = createUrl();
        var url= cont + "/producto-private?action=buscar&tienda=" + tienda.value +  "&ajax=true";
    
    request.open("GET", url, true);
      
    request.onreadystatechange = function(){createSelectProducto()};
    request.send(null);
    }
    //creacion del select de productos
function createSelectProducto(){
if(request.readyState == 4){
      
            if(request.status==200){
		 
	
		let productos =  JSON.parse(request.responseText);
		let prodOfertFather = productoOferta.parentElement;
					prodOfertFather.style.display = "inline-block";
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
	else{
			error.innerHTML = "No ha sido posible realizar la busqueda";
			formCreate.insertAdjacentElement("beforebegin", error);
		}

}
}

/*function para añadir productos a carrito cada vez que clica en el boton del carrito, se toma el valor del numero de unidades como el id del producto que esta en el campo name del button*/
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


/*funcion para rellenar selects de localizacion cuando se elige un pais, una provincia o comunidad*/
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
		else{
			error.innerHTML = "No ha sido posible realizar la busqueda";
			formSearch.insertAdjacentElement("beforeend", error);
		}
            }
            }
/**funcion para crear select en crear oferta y mostrar los campos pertinentes*/
function createOferta(){
	remove(tienda);

	// si se elige la oferta compra y llevate se ocultan los campos de numero de unidades y se cargan las tiendas y los productos mediante una llamada ajax
	if(tipoOferta.value == "3"){
	textInfoCL.style.display = "initial";
	tienda.style.display = "initial";
		
		for (let item of campoSegUnd) {
	item.value = "";
	item.style.display = "none";
	
	
	}
	selectTiendas();
	
	}
	/**ocultar selectores productoOferta*/
	if(tipoOferta.value=="2" || tipoOferta.value == "1"){
				$(".buyAndCarry").css({"display":"none"});
				
	
	}
	//si se eligen el tipo de oferta segunda unidad, se eliminan los select asociados a la oferta compra y llevate
	if(tipoOferta.value == "2"){
	for (let item of campoSegUnd) {
	item.style.display = "initial";
	
	}

	}
	else{
	for (let item of campoSegUnd) {
	item.value = "";
	item.style.display = "none";
	
	
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
let buttonLista = document.querySelector("#submitLista");
let nombreProducto = document.getElementById("NomPro");
let precioProducto = document.querySelector("#precioForAdd");
let textInfoCL = document.querySelector(".ofertaCL");
let campoSegUnd = document.getElementsByClassName("segundaUnidad");
let categoriaSelect = document.getElementById("categoria");

let categChild = document.getElementById("categChild");

let tiendaProCre = document.getElementById("tiendaProdCre");
let formSearch = document.getElementById("formSearch");
let inputTipParticular = document.getElementById("particularLog");
let inputTipEmp = document.getElementById("empresaLog");
let nameUser = document.getElementById("nameUser");
let divLista = document.querySelector(".centralBlock div");
let formCreate = document.querySelector(".centralBlock_form");
/******************
create elements
/**********************/
let error = document.createElement("p");
error.setAttribute("class","error");
let addLista = document.createElement("p");
addLista.innerHTML = "El producto ha sido puesto en la lista";
let duplicate = document.createElement("p");
duplicate.innerHTML = "El producto ya esta en la lista";
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
	createOferta();
	tipoOferta.onchange = createOferta;
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
    /**Recordar usuario**/
    if(inputTipParticular != null || inputTipEmp != null){
	
	if(inputTipParticular.checked || inputTipEmp.checked){
		remenberName();
	}
	inputTipParticular.addEventListener("click", remenberName);
	inputTipEmp.addEventListener("click", remenberName);
    }