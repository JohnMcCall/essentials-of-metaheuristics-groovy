package functions

import geneticTreeStructure.TerminalFunctionNode

class Ahead implements FunctionInterface {
    def name = "Ahead"
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

        def toReturn = "setAhead( ${innerJava} * moveDirection );\n"
        list.each {toReturn += it.makeJava()}
        toReturn
    }

    def isEqual(function) {
        this.name == function.name
    }
}