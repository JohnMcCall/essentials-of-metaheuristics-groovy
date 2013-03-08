package geneticTreeStructure

class AbstractNode {
    // This should be a closure(?)
    def function = null
    // Going to be a constant OR a string variable
    def value = null
    // This is a list of this nodes children
    def children = []
    def leafNode = true
    def parent = null

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

    def size(){
        if(children.size() == 0){
            1
        } else {
            def counter = 0
            children.each { counter += it.size() }
            counter++
            return counter
        }
    }
	
	def copy() {
		if(children.size() > 0) {
			def newChildren = []
			children.each {
				newChildren.add(it.copy())
			}
			new InternalNode(function, newChildren)
		} else {
			if(value instanceof String) {
				new VariableNode(value)
			} else {
				new NumericConstantNode(value)
			}
		}
	}
    
	// checks if two NODES are equal (not two trees)
    def isEqual(node) {
        def toReturn = true
        
        if(this.value != node.value) {
            toReturn = false
        }
        if(!this.children.equals(node.children)) {
            toReturn = false
        }
        if(!leafNode && !node.leafNode && !this.function.isEqual(node.function)) {
            toReturn = false
        }
        if(this.leafNode != node.leafNode) {
            toReturn = false
        }
        
        return toReturn
    }
	
	def getRoot() {
		if(parent != null) {
			parent.getRoot()
		} else {
			this
		}
	}

	def getValue = null
	
	def get(root, toGet) {
		if(root.isEqual(toGet)) {
			getValue = root
		} else {
			root.children.each {
				if(it.isEqual(toGet)) {
					getValue = it
				} else {
					get(it, toGet)
				}
			}
		}
		return getValue
	}

}
