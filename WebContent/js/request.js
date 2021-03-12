/**
 * 
 */
document.getElementById("pais").onchange = getDetails;
document.getElementById("comunidad").onchange = getDetails;
document.getElementById("provincia").onchange = getDetails;



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
function getDetails(e){
	request = createRequest();
	if(request == null){
		return;
	}
	
	var url="http://localhost:8080/abastos/localizacion?action="+ e.currentTarget.id + "&"+ e.currentTarget.id + "=" + e.currentTarget.value;

request.open("GET", url, true);
	let guardar = e.currentTarget;
request.onreadystatechange = function(){displayDetails(guardar)};
request.send(null);
}

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
	
function remove(valor){
	

	for(let i = valor.options.length-1; i >= 1 ; i--){
		valor.options[i].remove();
	}
}