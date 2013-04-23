package functions

class GetX implements FunctionInterface {
        def name = "GetX"
        def arity = 0
                
        String toString() {
                "Name: " + name
        }
        
        // takes a list of the node's children
        String makeJava(list){
                "getX()"
        }
    
    def isEqual(function) {
        this.name == function.name
    }
}