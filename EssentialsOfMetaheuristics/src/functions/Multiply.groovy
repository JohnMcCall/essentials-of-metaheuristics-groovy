package functions

class Multiply implements FunctionInterface {
        def name = "Multiply"
        def arity = 2
        
        def doMath = {x, y -> x * y}
        
        String toString(){
                "Name: " + name
        }
        
        def isEqual(function) {
            this.name == function.name
        }

}
