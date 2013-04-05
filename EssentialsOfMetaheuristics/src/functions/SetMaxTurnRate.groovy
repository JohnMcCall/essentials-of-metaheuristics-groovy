package functions

import geneticTreeStructure.InternalNode

class SetMaxTurnRate implements FunctionInterface {
        def name = "SetMaxTurnRate"
        def arity = 1
                
        String toString() {
                "Name: " + name
        }
        
        // takes a list of the node's children
        String makeJava(list){
                "setMaxTurnRate(" + list[0].makeJava() + ");"
        }
    
    def isEqual(function) {
        this.name == function.name
    }
}