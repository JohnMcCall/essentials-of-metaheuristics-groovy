package functions

class GetY implements FunctionInterface {
        def name = "GetY"
        def arity = 0
                
        String toString() {
                "Name: " + name
        }
        
        // takes a list of the node's children
        String makeJava(list){
                "getY()"
        }
    
    def isEqual(function) {
        this.name == function.name
    }
}