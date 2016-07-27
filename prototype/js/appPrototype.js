var Person = Class.create({
	initialize: function(name){this.name= name;}, 
	say: function(message){this.message=message;}

});

var Pirate = Class.create(Person,{say: function($super, message){
	return $super(message)+",yarrr!!";
}});
var john = new Pirate("long jhon");
john.say();
console.log(Pirate.superclass == Person);