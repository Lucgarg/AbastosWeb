

/*creacion de elementos*/
let buttonBack = document.createElement("button");
buttonBack.innerHTML = "atras";
buttonBack.setAttribute("class", "back");
buttonBack.setAttribute("type", "button");
buttonBack.addEventListener("click", function () { back(buttonBack.parentElement) });

/*submit function*/
if(document.getElementById("esp") != null){
document.getElementById("esp").onclick = submit;}
if(document.getElementById("en") != null){
document.getElementById("en").onclick = submit;}
function submit(e){
e.currentTarget.parentElement.submit();
}
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

function cerrar(){
    document.getElementById("registro").checked = false;
    document.getElementById("logIn").checked = false;
    document.getElementById("Idiomas").checked = false;
};

/*funciones para paginar registro*/
if(document.querySelector(".next") != null){
let forma = document.querySelector(".productoDetalle").querySelector("div").querySelectorAll("form");

forma.forEach(element => {
    element.querySelector(".next").addEventListener("click", function () { next(element) });
   
});
}
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
