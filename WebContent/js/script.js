

/*funcion para cerrar ventanas emergentes */
function cerrar(){
        document.getElementById("registro").checked = false;
        document.getElementById("logIn").checked = false;
        document.getElementById("Idiomas").checked = false;
};
/*funcion para paginar registro */
function next(valor) {

    let campo = valor.querySelectorAll("fieldset");

    for (let i = 0; i < campo.length; i++) {

        if (campo[i].style.display != "none") {

            if (campo[0].style.display != "none") {

                valor.insertBefore(buttonBack, valor.querySelectorAll("button")[0]);
            }
            campo[i].style.display = "none";
            if (i <= 3) {
                campo[i + 1].style.display = "initial";
            }
            if ((i + 1) == campo.length - 1) {

                valor.querySelectorAll("button")[1].style.display = "none";
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

            campo[i - 1].style.display = "initial";


            if ((i - 1) == campo.length - 2) {

                valor.querySelector(".next").style.display = "initial";
            }

            i = campo.length;

        }
    }
}

/*****************************
 * flujo de acciones
 */
/*creacion de elementos*/
let buttonBack = document.createElement("button");
buttonBack.innerHTML = "atras";
buttonBack.setAttribute("class", "back");
buttonBack.setAttribute("type", "button");
buttonBack.addEventListener("click", function () { back(buttonBack.parentElement) });

/*selectores*/
let field = document.querySelector(".valoracion");
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



/*funciones para paginar registro*/
if(document.querySelector(".next") != null){
let forma = document.querySelector(".productoDetalle").querySelector("div").querySelectorAll("form");

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
	console.log(contador.innerHTML);
	contador.style.display = "initial";
	}
}