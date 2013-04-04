package functions

import geneticTreeStructure.InternalNode

class TurnRight implements FunctionInterface {
	def name = "TurnRight"
	def arity = 1
		
	String toString() {
		"Name: " + name
	}
	
	// takes a list of the node's children
	String makeJava(list){
		"turnRightRadians(" + list[0].makeJava() + ");"
	}
    
    def isEqual(function) {
        this.name == function.name
    }
}