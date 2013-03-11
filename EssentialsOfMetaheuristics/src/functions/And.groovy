package functions

class And implements FunctionInterface {
    def name = "And"
    def arity = 2

    def doMath = {x, y -> x && y}

    String toString(){
        "Name: " + name
    }

    def isEqual(function) {
        this.name == function.name
    }
}
