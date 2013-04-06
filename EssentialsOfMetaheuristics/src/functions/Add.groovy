package functions

class Add implements FunctionInterface {
    def name = "Add"
    def arity = 2

    def doMath = { x, y ->
        if(x==null && y == null) {
            1
        } else if(x == null) {
            y
        } else if(y == null) {
            x
        } else {
            x + y
        }
    }

    String toString(){
        "Name: " + name
    }

    String makeJava(list) {
        def leftChild = list[0]
        def rightChild = list[1]

        def toReturn = "${doMath(leftChild.makeJava(), rightChild.makeJava())}"

        toReturn
    }

    def isEqual(function) {
        this.name == function.name
    }
}
