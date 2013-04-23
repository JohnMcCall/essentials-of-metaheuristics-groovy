package functions

import geneticTreeStructure.InternalNode

class Back implements FunctionInterface {
    def name = "Back"
    def arity = 2
    def random = new Random()
    def treeMaker
    def args

    String toString() {
        "Name: " + name
    }

    // takes a list of the node's children
    String makeJava(list){
        def tree = treeMaker.doFull(args)
        def innerJava = tree.makeJava()

        def toReturn = "setBack( ${innerJava} * moveDirection );\n"
        list.each {toReturn += it.makeJava()}
        toReturn
    }

    def isEqual(function) {
        this.name == function.name
    }
}