package functions

class Subtract implements FunctionInterface {
        def name = "Subtract"
        def arity = 2
        
        def doMath = {x, y -> x - y}
        
        String toString(){
                "Name: " + name
        }
        
        def isEqual(function) {
            this.name == function.name
        }

}
