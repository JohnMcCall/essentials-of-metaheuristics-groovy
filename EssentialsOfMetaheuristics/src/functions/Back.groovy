package functions

import geneticTreeStructure.InternalNode

class Back implements FunctionInterface {
    def name = "Back"
    def arity = 2
    def random = new Random()


    String toString() {
        "Name: " + name
    }

    // takes a list of the node's children
    String makeJava(list){
        def toReturn = "back( ${random.nextInt(51)+10} );\n"
        list.each {toReturn += it.makeJava()}
        toReturn
    }

    def isEqual(function) {
        this.name == function.name
    }
}