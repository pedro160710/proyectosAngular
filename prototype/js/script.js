function employee(name){
	this.name = name;
	this.getName= function(){
		return this.name;
	};
	var e1 = new employee("Joorge");
	var e2= new employee("Sandra");
document.write("e1"+e1.getName()+"  "+ "e2"+e2.getName());
}