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
		foundNode.children.each {
			it.parent = foundNode
		}
		return newTree
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
