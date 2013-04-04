package functions

import geneticTreeStructure.InternalNode

class Back implements FunctionInterface {
	def name = "Back"
	def arity = 1
		
	String toString() {
		"Name: " + name
	}
	
	// takes a list of the node's children
	String makeJava(list){
		"back(" + list[0].makeJava() + ");"
	}
    
    def isEqual(function) {
        this.name == function.name
    }
}