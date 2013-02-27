package geneticTreeStructure

class VariableNode extends AbstractNode {
	
	def VariableNode(String value){
		this.value = value
	}
	
	String toString(){
		"Value: " + value
	}

}
