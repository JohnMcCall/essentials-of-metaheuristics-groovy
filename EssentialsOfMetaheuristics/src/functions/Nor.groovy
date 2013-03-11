package functions

class Nor implements FunctionInterface {
    def name = "Nor"
    def arity = 2

    def doMath = {x, y -> !(x || y)}

    String toString(){
        "Name: " + name
    }

    def isEqual(function) {
        this.name == function.name
    }
}
