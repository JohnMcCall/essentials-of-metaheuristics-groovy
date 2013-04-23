package functions

class GetDistance implements FunctionInterface {
    def name = "GetDistance"
    def arity = 0

    String toString() {
        "e.getDistance()"
    }

    // takes a list of the node's children
    String makeJava(list){
        "e.getDistance()"
    }

    def isEqual(function) {
        this.name == function.name
    }
}