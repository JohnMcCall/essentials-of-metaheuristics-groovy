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
    
    def isEqual(node) {
        def toReturn = true
        
        if(this.value != node.value) {
            toReturn = false
        }
        if(!this.children.equals(node.children)) {
            toReturn = false
        }
        if(!leafNode && !this.function.isEqual(node.function)) {
            toReturn = false
        }
        if(this.leafNode != node.leafNode) {
            toReturn = false
        }
        
        return toReturn
    }

}
