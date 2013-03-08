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

    def addChild(node){
        children.add(node)
        node.parent = this
    }
    
    def removeChild(nodeToRemove){
		def newTree = this.getRoot().copy()
		remove(newTree, nodeToRemove)		
		return newTree
    }
	
	private remove(tree, nodeToRemove) {
		def foundNode = tree.get(tree, nodeToRemove)
		def index = -1
		foundNode.parent.children.eachWithIndex { it, i ->
			if(it.isEqual(nodeToRemove)) {
				index = i
			}
		}
		foundNode.parent.children.remove(index)
	}


    String toString() {
        "function: " + function.toString() + " children: " + childrenToString()
    }
	
}
