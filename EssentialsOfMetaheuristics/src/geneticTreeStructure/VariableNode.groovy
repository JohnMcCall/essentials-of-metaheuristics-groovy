package geneticTreeStructure

class VariableNode extends AbstractNode {
	
	def VariableNode(String value){
		this.value = value
	}
	
	def copy(){
		new VariableNode(value)
	}
	
	String toString(){
		"Value: " + value
	}
    
        String makeJava() {
            ""
        }

}
