package functions

import geneticTreeStructure.InternalNode

class GetEnergy implements FunctionInterface {
	def name = "GetEnergy"
	def arity = 0
		
	String toString() {
		"Name: " + name
	}
	
	// takes a list of the node's children
	String makeJava(list){
		"getEnergy();"
	}
    
    def isEqual(function) {
        this.name == function.name
    }
}