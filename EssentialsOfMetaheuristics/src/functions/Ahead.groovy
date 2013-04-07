package functions

import geneticTreeStructure.InternalNode

class Ahead implements FunctionInterface {
	def name = "Ahead"
	def arity = 2
        def random = new Random()
		
	String toString() {
		"Name: " + name
	}
	
	// takes a list of the node's children
	String makeJava(list){
		def toReturn = "ahead( ${random.nextInt(51)+10} );\n"
                list.each {toReturn += it.makeJava()}
                toReturn
	}
    
    def isEqual(function) {
        this.name == function.name
    }
}