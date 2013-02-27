package geneticTreeStructure

class NumericConstantNode extends AbstractNode {
	
	def NumericConstantNode(int value){
		this.value = value
	}
	
	def copy(){
		new NumericConstantNode(value)
	}
	
	String toString(){
		"Value: " + value
	}

}
