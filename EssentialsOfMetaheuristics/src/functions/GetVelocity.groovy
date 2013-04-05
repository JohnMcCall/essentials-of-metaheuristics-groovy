package functions

import geneticTreeStructure.InternalNode

class GetVelocity implements FunctionInterface {
        def name = "GetVelocity"
        def arity = 0
                
        String toString() {
                "Name: " + name
        }
        
        // takes a list of the node's children
        String makeJava(list){
                "getVelocity();"
        }
    
    def isEqual(function) {
        this.name == function.name
    }
}