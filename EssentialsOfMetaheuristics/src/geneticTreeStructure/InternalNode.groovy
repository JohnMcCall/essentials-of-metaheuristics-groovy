package geneticTreeStructure

class InternalNode extends AbstractNode {

    def InternalNode(function, listOfChildren){
        this.function = function
        listOfChildren.each{ addChild(it) }
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
        node.parent = this
    }
    
    def removeChild(node){
        children = children.minus(node)
    }

    String toString() {
        "function: " + function.toString() + " children: " + childrenToString()
    }

}
