// function employee(name){
// 	this.name = name;
// 	this.getName= function(){
// 		return this.name;
// 	};
// 	var e1 = new employee("Joorge");
// 	var e2= new employee("Sandra");
// document.write("e1"+e1.getName()+"  "+ "e2"+e2.getName());
// console.log("e1"+e1.getName()+"  "+ "e2"+e2.getName());
// }
 function food() {

	this.init= function(type){
		this.type= type;
	};
	this.eat= function(){
		console.log("you ate the"+this.type);
	};
}


// here we are creating the prototype
const waffle= new food();
waffle.init("waffle");
waffle.eat();

console.log("food  is a prototype of Waffle", food.isPrototypeOf(waffle));