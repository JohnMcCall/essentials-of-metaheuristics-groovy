package geneticTreeStructure

class TerminalFunctionNode extends AbstractNode {
    
    def TerminalFunctionNode(function){
        this.function = function
    }

    def copy(){
        new NumericConstantNode(function)
    }

    String toString(){
        "function: " + function.toString()
    }

    def makeJava() {
        function.makeJava(children)
    }
}
