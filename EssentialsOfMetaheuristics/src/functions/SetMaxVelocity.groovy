package functions

import geneticTreeStructure.InternalNode

class SetMaxVelocity implements FunctionInterface {
        def name = "SetMaxVelocity"
        def arity = 1
                
        String toString() {
                "Name: " + name
        }
        
        // takes a list of the node's children
        String makeJava(list){
                "setMaxVelocity(" + list[0].makeJava() + ");"
        }
    
    def isEqual(function) {
        this.name == function.name
    }
}