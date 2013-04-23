package functions

class GetHeading implements FunctionInterface {
    def name = "GetHeading"
    def arity = 0

    String toString() {
        "e.getHeading()"
    }

    // takes a list of the node's children
    String makeJava(list){
        "e.getHeading()"
    }

    def isEqual(function) {
        this.name == function.name
    }
}