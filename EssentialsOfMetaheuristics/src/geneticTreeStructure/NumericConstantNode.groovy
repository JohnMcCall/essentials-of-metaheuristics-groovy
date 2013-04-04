package geneticTreeStructure

class NumericConstantNode extends AbstractNode {
	
	def NumericConstantNode(value){
		this.value = value
	}
	
	def copy(){
		new NumericConstantNode(value)
	}
	
	String toString(){
		"Value: " + value
	}
	
	def makeJava() {
		value
	}

}
