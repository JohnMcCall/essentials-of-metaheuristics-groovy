package functions

class Add implements FunctionInterface {
	def name = "Add"
	def arity = 2
	
	def doMath = {x, y -> x + y}
	
	String toString(){
		"Name: " + name
	}
	
	String makeJava(list) {
		def leftChild = list[0]
		def rightChild = list[1]
		leftChild.makeJava() + " + " +  rightChild.makeJava()
	}
    
    def isEqual(function) {
        this.name == function.name
    }

}
