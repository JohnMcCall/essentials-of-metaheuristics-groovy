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
		def newNode = nodeToRemove.getRoot().copy()
		Boolean removed = false
		newNode.children.each {
			removed = removeHelper(it, nodeToRemove)
			if(removed) {
				println("NewNode: " + newNode)
				return newNode
			}
		}
    }
	
	private removeHelper(node, nodeToRemove) {
		println("node: " + node + " toRemove: " + nodeToRemove)
		if(node.isEqual(nodeToRemove)) {
			println("Removing Node!")
			println(node.parent.children)
			node.parent.children = node.parent.children.minus(nodeToRemove)
			println("AHHAHA" + node.parent.children.minus(nodeToRemove))
			println(node.parent.children)
			return true
		} else if(node.children.size() > 0) {
			node.children.each {
				return removeHelper(it, nodeToRemove)
			} 
		} else {
			return false
		}
	}

    String toString() {
        "function: " + function.toString() + " children: " + childrenToString()
    }
	
}
