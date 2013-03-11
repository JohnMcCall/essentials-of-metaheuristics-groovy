package functions

class Or implements FunctionInterface {
    def name = "Or"
    def arity = 2

    def doMath = {x, y -> x || y}

    String toString(){
        "Name: " + name
    }

    def isEqual(function) {
        this.name == function.name
    }
}
