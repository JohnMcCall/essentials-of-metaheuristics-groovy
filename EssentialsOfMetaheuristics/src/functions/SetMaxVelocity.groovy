package functions

import geneticTreeStructure.InternalNode

class SetMaxVelocity implements FunctionInterface {
        def name = "SetMaxVelocity"
        def arity = 2
        def random = new Random()
        
                
        String toString() {
                "Name: " + name
        }
        
        // takes a list of the node's children
        String makeJava(list){
                def toReturn = "setMaxVelocity( ${random.nextInt(9)} );\n"
                list.each {toReturn += it.makeJava()}
                toReturn
        }
    
    def isEqual(function) {
        this.name == function.name
    }
}