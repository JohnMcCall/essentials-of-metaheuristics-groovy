package geneticTreeStructure

class InternalNode extends AbstractNode {

    def InternalNode(function){
        this.function = function
        leafNode = false
    }

    def childrenToString(){
        def toReturn = ""
        children.each() { toReturn = it.toString() + " " + toReturn }
    }

    def addChild(node){
        def newTree = this.getRoot().copy()
        def foundNode = newTree.get(newTree, this)
        foundNode.children.add(node)
        foundNode.children.each { it.parent = foundNode }
        return newTree
    }

    def removeChild(nodeToRemove){
        def newTree = this.getRoot().copy()
        remove(newTree, nodeToRemove, newTree.getIndex(this))
        return newTree
    }

    private remove(tree, nodeToRemove, index) {
        def foundNode = tree.get(tree, index, 0)
        def listIndex = -1
        foundNode.children.eachWithIndex { it, i ->
            if(it.isEqual(nodeToRemove)) {
                listIndex = i
            }
        }
        foundNode.children.remove(listIndex)
    }

    def makeJava() {
        function.makeJava(children)
    }


    String toString() {
        "function: " + function.toString() + " children: " + childrenToString()
    }
}
