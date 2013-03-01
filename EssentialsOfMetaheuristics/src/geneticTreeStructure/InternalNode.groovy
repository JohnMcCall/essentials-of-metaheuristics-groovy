package geneticTreeStructure

class InternalNode extends AbstractNode {

    def InternalNode(function, listOfChildren){
        this.function = function
        children = listOfChildren
        leafNode = false
    }

    def childrenToString(){
        def toReturn = ""
        children.each() { toReturn = it.toString() + " " + toReturn }
    }

    def copy(){
        new InternalNode(function, children.clone())
    }

    def addChild(node){
        children.add(node)
    }

    String toString() {
        "function: " + function.toString() + " children: " + childrenToString()
    }

}
