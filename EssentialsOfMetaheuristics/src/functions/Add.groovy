package functions

class Add implements FunctionInterface {
	def name = "Add"
	def arity = 2
	
	def doMath = {x, y -> x + y}
	
	String toString(){
		"Name: " + name
	}
    
        def isEqual(function) {
            this.name == function.name
        }

}
