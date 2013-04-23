package functions

class GetBearing implements FunctionInterface {
    def name = "GetBearing"
    def arity = 0

    String toString() {
        "e.getBearing()"
    }

    // takes a list of the node's children
    String makeJava(list){
        "e.getBearing()"
    }

    def isEqual(function) {
        this.name == function.name
    }
}