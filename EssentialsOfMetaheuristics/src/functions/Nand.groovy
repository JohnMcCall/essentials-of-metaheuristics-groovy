package functions

class Nand implements FunctionInterface {
    def name = "Nand"
    def arity = 2

    def doMath = {x, y -> !(x && y)}

    String toString(){
        "Name: " + name
    }

    def isEqual(function) {
        this.name == function.name
    }
}
