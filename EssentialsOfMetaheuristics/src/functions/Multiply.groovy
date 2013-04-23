package functions

class Multiply implements FunctionInterface {
    def name = "Multiply"
    def arity = 2

    def doMath = { x, y ->
        if(x==null && y == null) {
            1
        } else if(x == null) {
            y
        } else if(y == null) {
            x
        } else {
            x * y
        }
    }

    String toString() {
        "Name: " + name
    }

    def isEqual(function) {
        this.name == function.name
    }

    String makeJava(list) {
        def leftChild = list[0]
        def rightChild = list[1]

        def toReturn = "(${leftChild.makeJava()} * ${rightChild.makeJava()})"

        toReturn
    }
}
