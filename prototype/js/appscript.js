

// Con JavaScript
tabla1 = document.getElementById('tabla');

// Con Prototype
var tabla= $("tabla");



// Con JavaScript
// var entrada1=document.getElementById('entrada').value;

// Con Prototype
var entrada= $F('entrada');




// Con JavaScript
// seleccion1= document.getElementById("municipio").options[document.getElementById("municipio").selectedIndex].value;
 
// Con Prototype
var selec= $F("municipio");



var todosParrafos = $$('p');
var parrafosInteriores = $$('#principal p');

function mostrarResultado(){
	// console.log(tabla.type());
	console.log(entrada);
	console.log(selec);
	console.log(todosParrafos);

}


