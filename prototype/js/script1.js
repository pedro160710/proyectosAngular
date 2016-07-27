function Person(){
  this.name="jorge";
  this.age=25;
}

function Actions(){
  this.walk= function(){
    console.log("camina");
  };
  this.stop= function(){
    console.log(" se detiene ");
  };
}
Person.prototype= new Actions();

var person1= new Person();
console.log(person1.name);
person1.walk();
person1.stop();
console.log("Actions is prototype of Person "+ Actions.isPrototypeOf(Person));

/*************************************/
var arreglo= {funcion1:function(){console.log("funcion 1");}, funcion2: function(){console.log("funcion 2");}};
var arregoPersonal= Object.create(arreglo);
arregoPersonal.funcion1();
console.log("arregoPersonal is prototype of arreglo "+ arreglo.isPrototypeOf(arregoPersonal));
