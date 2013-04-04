package functions

import geneticTreeStructure.InternalNode

class Ahead implements FunctionInterface {
	def name = "Ahead"
	def arity = 1
		
	String toString() {
		"Name: " + name
	}
	
	// takes a list of the node's children
	String makeJava(list){
		"ahead(" + list[0].makeJava() + ");"
	}
    
    def isEqual(function) {
        this.name == function.name
    }
}