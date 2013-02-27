package geneticTreeStructure

class NumericConstantNode extends AbstractNode {
	
	def NumericConstantNode(int value){
		this.value = value
	}
	
	String toString(){
		"Value: " + value
	}

}
