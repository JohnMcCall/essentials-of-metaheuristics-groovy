package geneticTreeStructure

class AbstractNode {
	// This should be a closure(?)
	def function
	
	// Going to be a constant OR a string variable
	def value
	
	// This is a list of this nodes children
	def children
	
	//TODO
	def eval(variableMap) {
		if(children.size() > 0) {
			function.doMath(children[0].eval(variableMap),
					   		children[1].eval(variableMap))
		} else {
			if(value instanceof String) {
				variableMap.get(value)
			} else {
				value
			}
		}
	}
	
}
