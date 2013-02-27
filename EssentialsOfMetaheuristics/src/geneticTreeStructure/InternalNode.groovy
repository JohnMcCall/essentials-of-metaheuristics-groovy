package geneticTreeStructure

class InternalNode extends AbstractNode {
	
	def InternalNode(function, listOfChildren){
		this.function = function
		children = listOfChildren
	}
	
	def childrenToString(){
		def toReturn = ""
		children.each() { toReturn = it.toString() + " " + toReturn }
	}
	
	//TODO
	String toString() {
		"function: " + function.toString() + " children: " + childrenToString()
	}

}
