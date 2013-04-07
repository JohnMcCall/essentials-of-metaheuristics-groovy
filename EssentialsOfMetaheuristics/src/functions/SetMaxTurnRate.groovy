package functions

import geneticTreeStructure.InternalNode

class SetMaxTurnRate implements FunctionInterface {
        def name = "SetMaxTurnRate"
        def arity = 2
        def random = new Random()
                
        String toString() {
                "Name: " + name
        }
        
        // takes a list of the node's children
    String makeJava(list){
        def toReturn = "setMaxTurnRate( ${random.nextInt(11)} );\n"
        list.each {toReturn += it.makeJava()}
        toReturn
    }
    
    def isEqual(function) {
        this.name == function.name
    }
}