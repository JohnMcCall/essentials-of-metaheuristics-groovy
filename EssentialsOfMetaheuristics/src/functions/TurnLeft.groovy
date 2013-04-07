package functions

import geneticTreeStructure.InternalNode

class TurnLeft implements FunctionInterface {
	def name = "TurnLeft"
	def arity = 2
        def random = new Random()
    
		
	String toString() {
		"Name: " + name
	}
	
	// takes a list of the node's children
        String makeJava(list){
                def toReturn = "turnLeft( ${random.nextInt(361)} );\n"
                list.each {toReturn += it.makeJava()}
                toReturn
        }
    
    def isEqual(function) {
        this.name == function.name
    }
}