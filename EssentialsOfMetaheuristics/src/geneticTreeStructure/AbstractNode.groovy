package geneticTreeStructure

class AbstractNode {
    // This should be a closure(?)
    def function = null

    // Going to be a constant OR a string variable
    def value = null

    // This is a list of this nodes children
    def children = []

    def leafNode = true

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
            println(value)
            1
        } else {
            println(function)
            def counter = 0
            children.each { counter += it.size() }
            println("counter: " + counter)
            
            counter++
            return counter
        }
    }

}
