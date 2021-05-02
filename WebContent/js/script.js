/***funcion para intervalo de fecha en oferta */

function counterDate(){
	
	let dat2 = new Date();
	let faltan = "";
	for(item2 of data){
	for(item of dataClone){
		console.log(map.get(item));
		if(item2.parentElement.isEqualNode(map.get(item))){
			
	 faltan = item2.parentElement.querySelector(".fecha + p");
	
	let	dat1 = new Date(item.innerHTML);
	let diferencia = Math.abs( dat1 - dat2);
	let seconds = diferencia / 1000;
	let diferenciaYears = Math.abs(dat1.getFullYear() - dat2.getFullYear());
	let diferenciaMeses = Math.abs((dat1.getMonth()) - dat2.getMonth());
	let dias = Math.floor(seconds/86400)%30;
	seconds -= dias * 86400;
	let horas = Math.floor(seconds/3600)%24;
	seconds -= horas * 3600;
	let minutes = Math.floor(seconds / 60) % 60;
	seconds -= minutes * 60;
	let segundos = Math.floor(seconds % 60);
	console.log(diferenciaMeses);
	if(diferenciaMeses > 0){
		item2.innerHTML = diferenciaMeses;
		faltan.innerHTML = "meses";
	}
	else if(dias > 0){
			item2.innerHTML = dias;
		faltan.innerHTML = "dias";
	}
	else {
			item2.innerHTML = horas + ":" + minutes + ":" + segundos;
		faltan.innerHTML = "Quedan";
	}
	}
	}
	}
}

/*funcion para cerrar ventanas emergentes */
function cerrar(){
        document.getElementById("registro").checked = false;
        document.getElementById("logIn").checked = false;
        document.getElementById("Idiomas").checked = false;
        if(document.getElementsByClassName("confirmacion")[0] != null){
		document.getElementsByClassName("confirmacion")[0].remove();
		}
};
/*funcion para paginar registro */
function next(valor) {

    let campo = valor.querySelectorAll("fieldset");

    for (let i = 0; i < campo.length; i++) {

        if (campo[i].style.display != "none") {

            if (campo[0].style.display != "none") {

                contendButtons.insertBefore(buttonBack, contendButtons.querySelectorAll("button")[0]);
            }
            campo[i].style.display = "none";
            if (i <= 3) {
                campo[i + 1].style.display = "flex";
            }
            if ((i + 1) == campo.length - 1) {

                contendButtons.querySelectorAll("button")[1].style.display = "none";
            }

            i = campo.length;
        }
    }
}
function back(valor) {


    let campo = valor.querySelectorAll("fieldset");

    for (let i = 0; i < campo.length; i++) {

        if (campo[i].style.display != "none") {
            if (campo[1].style.display != "none") {
               
                buttonBack.remove();
            }
            campo[i].style.display = "none";

            campo[i - 1].style.display = "flex";


            if ((i - 1) == campo.length - 2) {

                contendButtons.querySelector(".next").style.display = "flex";
            }

            i = campo.length;

        }
    }
}

/*****************************
 * flujo de acciones
 */
 




/*selectores*/
let field = document.querySelector(".valoracion");
let contendButtons  = document.getElementById("buttons");
let labels = document.querySelectorAll(".header > div:nth-child(1)  label");
let buttonNav = document.querySelector("#button_nav");
let windMin = window.matchMedia("(min-width: 950px)");
let windMax = window.matchMedia("(max-width: 949px)");
let detailCart = document.querySelector(".block_second--detail.cart");
let inputCart = document.querySelector(".block_second--detail form > input[type=checkbox]");
let precioFinal = document.querySelector(".precioFinal");
let puntosTotales = document.querySelector("#puntos");
let data = document.querySelectorAll(".fecha");
let map = new Map();
let dataClone = [];
if(data != null){
	for(item of data){
		let node = item.cloneNode(true);
		dataClone.push(node);
		map.set(node, item.parentElement);
	}
	
}

/*creacion de elementos*/
let buttonBack = document.createElement("button");
buttonBack.innerHTML = "atras";
buttonBack.setAttribute("class", "back Buscar");
buttonBack.setAttribute("type", "button");
buttonBack.addEventListener("click", function () { back(buttonBack.parentElement.parentElement) });



//contador de linea carrito
let contador = document.getElementById("count");

/*submit function*/
if(document.getElementById("esp") != null){
document.getElementById("esp").onclick = submit;}
if(document.getElementById("en") != null){
document.getElementById("en").onclick = submit;}


/*cerrar ventanas*/
if(document.getElementsByClassName("cerrarLabel")[0] !=null){
document.getElementsByClassName("cerrarLabel")[0].onclick = cerrar;
}
if(document.getElementsByClassName("cerrarLabel")[1] != null){
document.getElementsByClassName("cerrarLabel")[1].onclick = cerrar;
}
if(document.getElementsByClassName("cerrarLabel")[2] != null){
document.getElementsByClassName("cerrarLabel")[2].onclick = cerrar;
}

if(document.getElementsByClassName("cerrarLabel")[3] != null){
document.getElementsByClassName("cerrarLabel")[3].onclick = cerrar;
}

/*funciones para paginar registro*/
if(document.querySelector(".next") != null){
let forma = document.querySelector(".centralBlock").querySelector("div").querySelectorAll("form");

forma.forEach(element => {
    element.querySelector(".next").addEventListener("click", function () { next(element) });
   
});
}

/*puntuación estrellas*/

document.querySelectorAll("span").forEach(element => {

let valor = element.innerHTML;
let resultado = valor%1.0;
let entero = valor - resultado;
element.innerHTML = "";

for(let i = 0; i <=4; i++){
    if(i < entero){
    let star = document.createElement("label");
    star.setAttribute("class","allStar");
   element.appendChild(star);
    }
    else if(resultado > 0){
        let star = document.createElement("label");
        star.setAttribute("class","middleStar");
       element.appendChild(star);
       resultado = -1; 
    }
    else {
        let star = document.createElement("label");
        star.setAttribute("class","whiteStar");
       element.appendChild(star); 
    }
}
  } )

/**funcion para calcular precio con descuento por puntos***/
function calcPrecio(){

if(inputCart.checked){
	let punts = parseInt(puntos.innerHTML);
	let tPunts = parseInt(punts/10);
	let pTotal = parseFloat(precioFinal.innerHTML) - tPunts;
	if(pTotal < 0){
		pTotal = 0;
	}
 	pTotal = pTotal.toFixed(2);
	precioFinal.innerHTML = pTotal;	
}

else if(inputCart.checked == false){
	let punts = parseInt(puntos.innerHTML);
	let tPunts = parseInt(punts/10);
	let pTotal = parseFloat(precioFinal.innerHTML) + tPunts;
	
 pTotal = pTotal.toFixed(2);
	precioFinal.innerHTML = pTotal;	
}

}



/*funcion para puntuar productor y tiendas*/
if(field != null){
field.querySelectorAll("fieldset").forEach(Element => {
 	updatePuntuacion(Element);
    Element.querySelectorAll("input").forEach(Element2 => {
      
        Element2.addEventListener("click", puntuar);
     
    })
   

})}

function puntuar(e){
            let valor  = e.currentTarget.parentElement.parentElement;
			
            array =  valor.querySelectorAll(".star-blank, .yellow-star");
            array1 = valor.querySelectorAll("input"); 
          	console.log(array);
            for(let i = 0; i < array1.length; i++){
              
            if(valor.querySelectorAll("input")[i].checked === true){
               
                for(let j = 0; j <= i ; j++){
             
                  array[j].className = "yellow-star";
              
                }
                for(let f = i + 1; f < array.length; f++){
                    array[f].className = "star-blank";
                }
                
                
            }
            }
        }
function updatePuntuacion(valor){

            array =  valor.querySelectorAll(".star-blank, .yellow-star");
            array1 = valor.querySelectorAll("input"); 
          	console.log(array);
            for(let i = 0; i < array1.length; i++){
              
            if(valor.querySelectorAll("input")[i].checked === true){
               
                for(let j = 0; j <= i ; j++){
             
                  array[j].className = "yellow-star";
              
                }
                for(let f = i + 1; f < array.length; f++){
                    array[f].className = "star-blank";
                }
                
                
            }
            }
        }
/****comprobacion de si numero de lineas carrito no es null*/
if(contador!=null){
	if(contador.innerHTML.trim() != ""){
	
	contador.style.display = "initial";
	}
}
window.matchMedia("(max-width: 700px)").addListener(function(winMin){
	if(winMin.matches){
	$("#foto_perfil").css({"margin-bottom":"0"});
	$(".header > div:nth-child(1)  label").css({"display": "inline-block"});
		$(".header > div:nth-child(1)>label:nth-child(3)").css({"display":"none"});
	
}
});
window.matchMedia("(min-width: 700px)").addListener(function(winMin){
	if(winMin.matches){
	$("#foto_perfil").css({"margin-bottom":"0"});
	$(".header > div:nth-child(1) > #foto_perfil ~ label").css({"display": "none"});
	
	
}
});
window.matchMedia("(min-width: 950px)").addListener(function(winMin){
	if(winMin.matches){
	$("#foto_perfil").css({"margin-bottom":"0"});
	$(".header > div:nth-child(1)  label").css({"display": "inline-block"});
		$(".header > div:nth-child(1)>label:nth-child(3)").css({"display":"none"});

}
});

window.matchMedia("(max-width: 949px)").addListener(function(winMax){
	if(winMax.matches){
	$("#foto_perfil").css({"margin-bottom":"0"});
	$(".header > div:nth-child(1) > #foto_perfil ~ label").css({"display": "none"});
	

	}
});
/**responsive nav**/
if(labels.length <= 5){
	$(".header>div:nth-child(1)").css({"position": "relative", "flex-direction":"row", "justify-content": "space-around"});
	$(".header>div:nth-child(1) label").css({"margin-bottom": "0"});
}
$("#button_nav").click(function(){
	
	$("#foto_perfil").css({"margin-bottom":"20%"});
	$(".header > div:nth-child(1)  label").css({"display": "inline-block"});
	$(".header > div:nth-child(1)>label:nth-child(3)").css({"display":"none"});
})



$(".header>div:nth-child(1)").mouseleave(function(){
if(buttonNav != null){
	if(window.getComputedStyle(buttonNav).getPropertyValue("display") != "none"){
		$("#foto_perfil").css({"margin-bottom":"0"});
	$(".header > div:nth-child(1) > #foto_perfil ~ label").css({"display":"none"});
	}
	}
})

/////calculo precio carrito con descuento con puntos//////
if(detailCart != null){
	let precioInicio = precioFinal.innerHTML;
	inputCart.addEventListener("click", calcPrecio);
}

if(data != null){
	setInterval(function(){
	counterDate();},1000);
	
}