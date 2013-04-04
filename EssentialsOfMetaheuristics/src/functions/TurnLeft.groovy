package functions

import geneticTreeStructure.InternalNode

class TurnLeft implements FunctionInterface {
	def name = "TurnLeft"
	def arity = 1
		
	String toString() {
		"Name: " + name
	}
	
	// takes a list of the node's children
	String makeJava(list){
		"turnLeftRadians(" + list[0].makeJava() + ");"
	}
    
    def isEqual(function) {
        this.name == function.name
    }
}