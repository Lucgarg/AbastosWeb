/***funcion para intervalo de fecha en oferta en resultado de productos */

function counterDate() {
	//fecha actual
  let dat2 = new Date();
  let faltan = "";
//se realizan un buble for anidado para saber que campo de fecha se esta comprobando en ese momento.
//data es la referencia al actual nodo que tiene la fecha ya cambiada
//dataClone es una copia del nodo original que cambia cada vez que se actualiza la duracion
  for (item2 of data) {
    for (item of dataClone) {
      //se utiliza un map donde el key es el clon de node original, y el valor el contenedor padre de ese nodo, que en este caso al no cambiar no se realiza una copia de este.
      if (item2.parentElement.isEqualNode(map.get(item))) {
		//se selecciona la fecha que en ese momento aparece en pantalla
        faltan = item2.parentElement.querySelector(".fecha + p");
		//realizacion de calculos para obtener el tiempo que falta
        let dat1 = new Date(item.innerHTML);
        let diferencia = Math.abs(dat1 - dat2);
        let seconds = diferencia / 1000;
        let diferenciaYears = Math.abs(dat1.getFullYear() - dat2.getFullYear());
        let diferenciaMeses = Math.abs(dat1.getMonth() - dat2.getMonth());
        let dias = Math.floor(seconds / 86400) % 30;
        seconds -= dias * 86400;
        let horas = Math.floor(seconds / 3600) % 24;
        seconds -= horas * 3600;
        let minutes = Math.floor(seconds / 60) % 60;
        seconds -= minutes * 60;
        let segundos = Math.floor(seconds % 60);
     	//dependiendo de si faltan meses, dias o horas el tiempo mostrado es diferente
        if (diferenciaMeses > 0) {
          item2.innerHTML = diferenciaMeses;
          faltan.innerHTML = "meses";
        } else if (dias > 0) {
          item2.innerHTML = dias;
          faltan.innerHTML = "dias";
        } else {
          item2.innerHTML = horas + ":" + minutes + ":" + segundos;
         
        }
      }
    }
  }
}

/*funciones crear y buscar cookies*/
function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(";");

  for (var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == " ") {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return null;
}
function setCookie(ckey, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000);
  var expires = "expires=" + d.toUTCString();
  document.cookie = ckey + "=" + cvalue + ";" + expires + ";path=/";
}

/*funcion para guardar busqueda en buscador omnibox tienda*/
function saveSearchTienda() {
	//se comprueba que la cookie existe sino se crea
  if (cookieResult == null) {
    setCookie("searchShop", inputBuscadorTienda.value, 30);
  } else {
	//se obtiene array con valores
    let arrayCookie = cookieResult.split(",");
	//si el nuevo valor no existe en el array entonces se introduce y se crea de nuevo la cookie
    if (arrayCookie.indexOf(inputBuscadorTienda.value) == -1) {
      cookieResult += "," + inputBuscadorTienda.value;
      setCookie("searchShop", cookieResult, 30);
    }
  }
}
function printSearchTienda() {
	//si la cookie existe se recuperan sus valores, se eliminan los anteriores antes de volver a realizar la isnerción para evitar repeticiones
  if (cookieResult != null) {
    let arrayCookie = cookieResult.split(",");
  
    removeSearch(dataListShop);
    for (item of arrayCookie) {
      let option = document.createElement("option");
      option.value = item;
      dataListShop.insertAdjacentElement("beforeend", option);
    }
  }
}
/*funcion para guardar busqueda en buscador omnibox producto*/
function saveSearchProduct() {
  if (cookieResultP == null) {
    setCookie("searchProduct", inputBuscadorProduct.value, 30);
  } else {
    let arrayCookie = cookieResultP.split(",");
    if (arrayCookie.indexOf(inputBuscadorProduct.value) == -1) {
      cookieResultP += "," + inputBuscadorProduct.value;
      setCookie("searchProduct", cookieResultP, 30);
    }
  }
}
function printSearchProduct() {
  if (cookieResultP != null) {
    let arrayCookie = cookieResultP.split(",");
    console.log(arrayCookie);
    removeSearch(dataListProduct);
    for (item of arrayCookie) {
      let option = document.createElement("option");
      option.value = item;
      dataListProduct.insertAdjacentElement("beforeend", option);
    }
  }
}
/*funcion para eliminar opciones de datalist */
function removeSearch(valor) {
  for (let i = valor.options.length - 1; i >= 1; i--) {
    valor.options[i].remove();
  }
}
/*funcion para cerrar ventanas emergentes en combinancion con css */
function cerrar() {
	//la funcion para mostrar las vestanas se hace desde css, mientras que para cerrarlas unicamente se cambia el valor del checked a false y el resto se realiza desde css
  document.getElementById("registro").checked = false;
  document.getElementById("logIn").checked = false;
  document.getElementById("Idiomas").checked = false;
//ventana que se muestra cuando desde el correo se le da a confirmar registro accediendo al enlace
  if (document.getElementsByClassName("confirmacion")[0] != null) {
    document.getElementsByClassName("confirmacion")[0].remove();
  }
}
/*funcion para paginar registro */
function next(valor) {
	//se recuperar los diferentes fieldset que sirven para dividir las partes que componen al registro
  let campo = valor.querySelectorAll("fieldset");

  for (let i = 0; i < campo.length; i++) {
	//si la ventana esta visible, se inserta el bonton hacia atras
    if (campo[i].style.display != "none") {
      if (campo[0].style.display != "none") {
        contendButtons.insertBefore(
          buttonBack,
          contendButtons.querySelectorAll("button")[0]
        );
      }
	//se oculta el campo anterior
      campo[i].style.display = "none";
//se muestra el nuevo campo
      if (i <= 3) {
        campo[i + 1].style.display = "flex";
      }
//si nos encontramos en la ultima ventana para evitar que se pueda ocultar la ultima ventana se realiza la siguiente comprobacion
      if (i + 1 == campo.length - 1) {
        contendButtons.querySelectorAll("button")[1].style.display = "none";
      }

      i = campo.length;
    }
  }
}
//funcion que se ejecuta cuando se pulsa el boton hacia atras en el registro
function back(valor) {
  let campo = valor.querySelectorAll("fieldset");

  for (let i = 0; i < campo.length; i++) {
    if (campo[i].style.display != "none") {
      if (campo[1].style.display != "none") {
        buttonBack.remove();
      }
      campo[i].style.display = "none";

      campo[i - 1].style.display = "flex";

      if (i - 1 == campo.length - 2) {
        contendButtons.querySelector(".next").style.display = "flex";
      }

      i = campo.length;
    }
  }
}

///funciones para realizar puntuacion

function puntuar(e) {
  let valor = e.currentTarget.parentElement.parentElement;

  array = valor.querySelectorAll(".star-blank, .yellow-star");
  array1 = valor.querySelectorAll("input");
  console.log(array);
  for (let i = 0; i < array1.length; i++) {
    if (valor.querySelectorAll("input")[i].checked === true) {
      for (let j = 0; j <= i; j++) {
        array[j].className = "yellow-star";
      }
      for (let f = i + 1; f < array.length; f++) {
        array[f].className = "star-blank";
      }
    }
  }
}
function updatePuntuacion(valor) {
  array = valor.querySelectorAll(".star-blank, .yellow-star");
  array1 = valor.querySelectorAll("input");
  console.log(array);
  for (let i = 0; i < array1.length; i++) {
    if (valor.querySelectorAll("input")[i].checked === true) {
      for (let j = 0; j <= i; j++) {
        array[j].className = "yellow-star";
      }
      for (let f = i + 1; f < array.length; f++) {
        array[f].className = "star-blank";
      }
    }
  }
}
//***funcion para comprobar campos formulario empresa**/
function testRegEmp(e) {
  let nombre = new RegExp("(\\s*[0-9]{0,10}[a-zA-Z]{2,20}[0-9]{0,10}){1,5}");
  let apellidos = new RegExp("(\\s*[a-zA-Z]{2,20}){1,5}");
  let email = new RegExp("[a-zA_Z1-9]+@[a-zA-Z1-9]+.[a-zA-Z]+");
  let numberNotValidator = new RegExp("(\\s*[a-zA-Z]{2,20}){1,5}");
  let pisoValidator = new RegExp("[1-9]{0,2}[a-zA-Z]{1,5}");
  let cdValidator = new RegExp(/\b^[1-9][0-9]{4}\b/);
	let cif = new RegExp("^[a-zA-Z]{1}\\d{7}[a-zA-Z0-9]{1}$");
	let razonSocial = new RegExp(/(\s*[a-zA-Z]{2,20}){1,5}\s+([a-zA-Z]\.){2,5}/);
	let isNumber = new RegExp("[1-9]{1,3}");
  let inputEmpresa = registroEmpresa.querySelectorAll("input");
  let actualInput = e.currentTarget;
//se comprueba cual es el input sobre el que se realiza el evento
if(actualInput == inputEmpresa[1]){
  test(
    inputEmpresa[1],
    nombre,
    "El nombre no cumple con el formato"
  );
}
if(actualInput == inputEmpresa[2]){
  test(
    inputEmpresa[2],
    apellidos,
    "Los apellidos no pueden contener numeros"
  );
}
if(actualInput == inputEmpresa[3]){
  test(
    inputEmpresa[3],
    nombre,
    "El alias no cumple con el formato"
  );
}
  //comprobacion que las password coinciden
if(actualInput == inputEmpresa[5] || actualInput == inputEmpresa[4]){
  if (inputEmpresa[4].value != inputEmpresa[5].value) {
    inputEmpresa[4].style.backgroundColor = "red";
    inputEmpresa[5].style.backgroundColor = "red";
    let errTarget = inputEmpresa[5].nextSibling;
    let err = document.createElement("p");
    err.setAttribute("class", "error");
    err.innerHTML = "passwords  diferentes";
    if (!errTarget.isEqualNode(err)) {
      inputEmpresa[5].parentNode.insertBefore(err, inputEmpresa[5].nextSibling);
    }
  } else {
    inputEmpresa[4].style.backgroundColor = "white";
    inputEmpresa[5].style.backgroundColor = "white";
    inputEmpresa[5].nextSibling.remove();
  }
}
if(actualInput == inputEmpresa[6]){
	test(inputEmpresa[6],cif, "El cif no cumple con el formato: A58818501");
	}
	if(actualInput == inputEmpresa[7]){
	test(inputEmpresa[7], razonSocial, "La razon social no debe tener numeros y debe terminar con el tipo de sociedad: ejemplo.s.l.");
}

if(actualInput == inputEmpresa[8]){
	test(inputEmpresa[8], email, "El email no cumple con el formato");
}
if(actualInput == inputEmpresa[9]){
	test(inputEmpresa[9],numberNotValidator, "la calle no puede tener numeros");
}
if(actualInput == inputEmpresa[10]){
	test(inputEmpresa[10],  isNumber, "El numero no puede contener letras");
}
if(actualInput == inputEmpresa[11]){
	test(inputEmpresa[11], pisoValidator, "El piso no cumple con el formato");
}
if(actualInput == inputEmpresa[12]){
	test(inputEmpresa[12], cdValidator, "el codigo postal no cumple con el formato");
}
}
function test(valor, reg, textError) {
	//se comprueba que el valor cumple el el regExp, sino se cambia el backgrouncolor y se crea un tag p debajo con la class error
  if (!reg.test(valor.value)) {
    valor.style.backgroundColor = "red";
    let errTarget = valor.nextSibling;
    let err = document.createElement("p");
    err.setAttribute("class", "error");
    err.innerHTML = textError;
	//para evitar que se vuelva a insertar el tag se comprueba si el que se va a insertar es el mismo que esta presente
    if (!errTarget.isEqualNode(err)) {
      valor.parentNode.insertBefore(err, valor.nextSibling);
    }
  } else {
	//si es correcto entonces el campo se pone a su color original 
    valor.style.backgroundColor = "rgba(167, 201, 88, 0.2)";
	let targetNext = valor.nextSibling;
	//se comprueba que el node siguiente al actual tiene la class error, en tal caso se elimina
	if(targetNext.className == "error"){
    valor.nextSibling.remove();
	}
  }
}

/*****************************
 * flujo de acciones
 */

/*selectores*/
let field = document.querySelector(".valoracion");
let contendButtons = document.getElementById("buttons");
let labels = document.querySelectorAll(".header > div:nth-child(1)  label");
let buttonNav = document.querySelector("#button_nav");
let windMin = window.matchMedia("(min-width: 950px)");
let windMax = window.matchMedia("(max-width: 949px)");
let detailCart = document.querySelector(".block_second--detail.cart");
let inputCart = document.querySelector(
  ".block_second--detail form > input[type=checkbox]"
);
let precioFinal = document.querySelector(".precioFinal");
let puntosTotales = document.querySelector("#puntos");
let data = document.querySelectorAll(".fecha");
let buscadorTienda = document.getElementById("buscadorOmniShop");
let inputBuscadorTienda = document.querySelector(
  '#buscadorOmniShop > input[type="text"]'
);
let dataListShop = document.getElementById("omniSearchShop");
let buttonSearchTienda = document.querySelector("#buscadorOmniShop > button");
let buscadorProduct = document.getElementById("buscadorOmniProduct");
let inputBuscadorProduct = document.querySelector(
  '#buscadorOmniProduct > input[type="text"]'
);
let dataListProduct = document.getElementById("omniSearchProduct");
let buttonSearchProduct = document.querySelector(
  "#buscadorOmniProduct > button"
);
let registroEmpresa = document.getElementById("empresa");
/****cookies */
let cookieResult = getCookie("searchShop");
let cookieResultP = getCookie("searchProduct");
//mapa para guardar la copia del nodo de origen y el contenedor padre
let map = new Map();
let dataClone = [];
if (data != null) {
  for (item of data) {
    let node = item.cloneNode(true);
    dataClone.push(node);
    map.set(node, item.parentElement);
  }
}

/*creacion de elementos*/
//creacion de boton atras para los campos de registro
let buttonBack = document.createElement("button");
buttonBack.innerHTML = "atras";
buttonBack.setAttribute("class", "back Buscar");
buttonBack.setAttribute("type", "button");
buttonBack.addEventListener("click", function () {
  back(buttonBack.parentElement.parentElement);
});

//contador de linea carrito 
let contador = document.getElementById("count");

/*submit function*/
if (document.getElementById("esp") != null) {
  document.getElementById("esp").onclick = submit;
}
if (document.getElementById("en") != null) {
  document.getElementById("en").onclick = submit;
}

/*cerrar ventanas*/
if (document.getElementsByClassName("cerrarLabel")[0] != null) {
  document.getElementsByClassName("cerrarLabel")[0].onclick = cerrar;
}
if (document.getElementsByClassName("cerrarLabel")[1] != null) {
  document.getElementsByClassName("cerrarLabel")[1].onclick = cerrar;
}
if (document.getElementsByClassName("cerrarLabel")[2] != null) {
  document.getElementsByClassName("cerrarLabel")[2].onclick = cerrar;
}

if (document.getElementsByClassName("cerrarLabel")[3] != null) {
  document.getElementsByClassName("cerrarLabel")[3].onclick = cerrar;
}

/*funciones para paginar registro*/
if (document.querySelector(".next") != null) {
  let forma = document
    .querySelector(".centralBlock")
    .querySelector("div")
    .querySelectorAll("form");

  forma.forEach((element) => {
    element.querySelector(".next").addEventListener("click", function () {
      next(element);
    });
  });
}

/****eventos para cookie para recordar busquedas*** */
if (buttonSearchTienda != null) {
  buttonSearchTienda.addEventListener("click", saveSearchTienda());
  inputBuscadorTienda.addEventListener("onfocus", printSearchTienda());
}
if (buttonSearchProduct != null) {
  buttonSearchProduct.addEventListener("click", saveSearchProduct());
  inputBuscadorProduct.addEventListener("onfocus", printSearchProduct());
}

//***evento formulario registro empresa para comprobar la validacion de datos***/

if (registroEmpresa != null) {
  $("input").focusout(testRegEmp);
}
/*puntuación estrellas*/
//funcion para pintar puntuaciones
document.querySelectorAll("span").forEach((element) => {
	
	//la puntuacion obtenida se divide en su parte entera y decimal
  let valor = element.innerHTML;
  let resultado = valor % 1.0;
  let entero = valor - resultado;
  element.innerHTML = "";
	//en el css ya hay definidas tres clases una para la estrella completa, otra para la mitad de estrella 
	//y otra para la vacia. Primero se pintan las enteras, si hay algun decimal se utiliza la clase que esta asociada el icono de mited de estrella
	//y el resto se cambia a clase estrella vacia
  for (let i = 0; i <= 4; i++) {
    if (i < entero) {
      let star = document.createElement("label");
      star.setAttribute("class", "allStar");
      element.appendChild(star);
    } else if (resultado > 0) {
      let star = document.createElement("label");
      star.setAttribute("class", "middleStar");
      element.appendChild(star);
      resultado = -1;
    } else {
      let star = document.createElement("label");
      star.setAttribute("class", "whiteStar");
      element.appendChild(star);
    }
  }
});

/**funcion para calcular precio con descuento por puntos***/
function calcPrecio() {
	//si el input de aplicar descuento esta activo se calculan puntos y se realiza la actualizacion del precio
  if (inputCart.checked) {
    let punts = parseInt(puntos.innerHTML);
    let tPunts = parseInt(punts / 10);
    let pTotal = parseFloat(precioFinal.innerHTML) - tPunts;
	//para evitar que haya valores negativos, el valor minimo es el 0;
    if (pTotal < 0) {
      pTotal = 0;
    }
    pTotal = pTotal.toFixed(2);
    precioFinal.innerHTML = pTotal;
	//si el input no esta activo entonces se recuperar el valor original realizando la suma de los puntos restados
  } else if (inputCart.checked == false) {
    let punts = parseInt(puntos.innerHTML);
    let tPunts = parseInt(punts / 10);
    let pTotal = parseFloat(precioFinal.innerHTML) + tPunts;

    pTotal = pTotal.toFixed(2);
    precioFinal.innerHTML = pTotal;
  }
}

/*funcion para puntuar productor y tiendas*/
if (field != null) {
  field.querySelectorAll("fieldset").forEach((Element) => {
    updatePuntuacion(Element);
    Element.querySelectorAll("input").forEach((Element2) => {
      Element2.addEventListener("click", puntuar);
    });
  });
}

/****comprobacion de si numero de lineas carrito no es null y en tal caso mostrar el contador*/
if (contador != null) {
  if (contador.innerHTML.trim() != "") {
    contador.style.display = "initial";
  }
}

//mediaQueries para realizar evitar que se produzca la desaparicion de elementos cuando se cambia de tipo de resolucion
window.matchMedia("(max-width: 700px)").addListener(function (winMin) {
  if (winMin.matches) {
    $("#foto_perfil").css({ "margin-bottom": "0" });
    $(".header > div:nth-child(1)  label").css({ display: "inline-block" });
    $(".header > div:nth-child(1)>label:nth-child(3)").css({ display: "none" });
  }
});
window.matchMedia("(min-width: 700px)").addListener(function (winMin) {
  if (winMin.matches) {
    $("#foto_perfil").css({ "margin-bottom": "0" });
    $(".header > div:nth-child(1) > #foto_perfil ~ label").css({
      display: "none",
    });
  }
});
window.matchMedia("(min-width: 950px)").addListener(function (winMin) {
  if (winMin.matches) {
    $("#foto_perfil").css({ "margin-bottom": "0" });
    $(".header > div:nth-child(1)  label").css({ display: "inline-block" });
    $(".header > div:nth-child(1)>label:nth-child(3)").css({ display: "none" });
  }
});

window.matchMedia("(max-width: 949px)").addListener(function (winMax) {
  if (winMax.matches) {
    $("#foto_perfil").css({ "margin-bottom": "0" });
    $(".header > div:nth-child(1) > #foto_perfil ~ label").css({
      display: "none",
    });
  }
});
//logica que dependiendo del numero de elementos presentes en el header, ajusta 
//la forma en la que aparecen los elementos, en este caso si son más de 5 elementos y la resolucion ejecuta la mediaquerie especificada en el css entonces los elementos desaparecen
//solo se muestran cuando se pulsa un boton debajo de la imagen de perfil.
/**responsive nav**/
if (labels.length <= 5) {
  $(".header>div:nth-child(1)").css({
    position: "relative",
    "flex-direction": "row",
    "justify-content": "space-around",
  });
  $(".header>div:nth-child(1) label").css({ "margin-bottom": "0" });
}
//evento que se ejecuta cuando la resolucion de pantalla alcanza los limites marcados en el mediaquerie en el .css, se produce cuando hay mas de cinco elementos en el header. Hecho que se produce cuando hay una empresa
//o un particular en sesión.
$("#button_nav").click(function () {
  $("#foto_perfil").css({ "margin-bottom": "20%" });
  $(".header > div:nth-child(1)  label").css({ display: "inline-block" });
  $(".header > div:nth-child(1)>label:nth-child(3)").css({ display: "none" });
});

//funcion para ocultar los elementos una vez que se pulsa el boton anterior.
$(".header>div:nth-child(1)").mouseleave(function () {
  if (buttonNav != null) {
    if (
      window.getComputedStyle(buttonNav).getPropertyValue("display") != "none"
    ) {
      $("#foto_perfil").css({ "margin-bottom": "0" });
      $(".header > div:nth-child(1) > #foto_perfil ~ label").css({
        display: "none",
      });
    }
  }
});

/////calculo precio carrito con descuento con puntos//////
if (detailCart != null) {
  let precioInicio = precioFinal.innerHTML;
  inputCart.addEventListener("click", calcPrecio);
}

if (data != null) {
  setInterval(function () {
    counterDate();
  }, 1000);
}
