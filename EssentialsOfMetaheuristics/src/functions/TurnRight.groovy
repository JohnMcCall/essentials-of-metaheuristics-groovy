package functions

import geneticTreeStructure.InternalNode

class TurnRight implements FunctionInterface {
    def name = "TurnRight"
    def arity = 2
    def random = new Random()


    String toString() {
        "Name: " + name
    }

    // takes a list of the node's children
    String makeJava(list){
        def toReturn = "turnRight( ${random.nextInt(361)} );\n"
        list.each {toReturn += it.makeJava()}
        toReturn
    }

    def isEqual(function) {
        this.name == function.name
    }
}