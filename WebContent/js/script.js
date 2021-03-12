/*Elegir usuario*/
if(document.getElementById("particularLog") != null || document.getElementById("empresaLog") !=null){
document.getElementById("particularLog").addEventListener("click", function(){
    document.querySelector(".registro").querySelector("form").action = "http://localhost:8080/abastos/particular";
	
});
document.getElementById("empresaLog").addEventListener("click", function(){
    document.querySelector(".registro").querySelector("form").action = "http://localhost:8080/abastos/empresa";
	
});}

/*cerrar ventanas*/
if(document.getElementsByClassName("cerrarLabel")[0] !=null){
document.getElementsByClassName("cerrarLabel")[0].onclick = cerrar;
}
if(document.getElementsByClassName("cerrarLabel")[1] != null){
document.getElementsByClassName("cerrarLabel")[1].onclick = cerrar;
}

function cerrar(){
    document.getElementById("registro").checked = false;
    document.getElementById("logIn").checked = false;
};



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
