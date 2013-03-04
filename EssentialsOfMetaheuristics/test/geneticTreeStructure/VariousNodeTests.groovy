package geneticTreeStructure

import functions.Add
import spock.lang.Specification

class VariousNodeTests extends Specification {
    def "testing one-deep tree with two constants"() {
        def constant1 = new NumericConstantNode(3)
        def constant2 = new NumericConstantNode(6)
        def add = new Add()
        def innerNode = new InternalNode(add, [constant1, constant2])

        expect:
        innerNode.eval(null) == 9
    }

    def "testing one-deep tree with two variables"(){
        def variable1 = new VariableNode("x")
        def variable2 = new VariableNode("y")
        def add = new Add()
        def variableMap = new HashMap()
        variableMap.put("x", 5)
        variableMap.put("y", 9)
        def innerNode = new InternalNode(add, [variable1, variable2])

        expect:
        innerNode.eval(variableMap) == 14
    }

    def "testing one-deep tree with one variable and one constant"(){
        def variable1 = new VariableNode("x")
        def constant1 = new NumericConstantNode(2)
        def add = new Add()
        def variableMap = new HashMap()
        variableMap.put("x", 5)
        def innerNode = new InternalNode(add, [variable1, constant1])

        expect:
        innerNode.eval(variableMap) == 7
    }

    def "testing bigger tree"(){
        def constant1 = new NumericConstantNode(2)
        def constant2 = new NumericConstantNode(3)
        def constant3 = new NumericConstantNode(4)

        def variable1 = new VariableNode("x")
        def variable2 = new VariableNode("y")
        def variableMap = new HashMap()
        variableMap.put("x", 5)
        variableMap.put("y", 9)

        def add = new Add()

        def innerNode = new InternalNode(add, [constant1, constant3])
        def innerNode1 = new InternalNode(add, [innerNode, variable1])
        def innerNode2 = new InternalNode(add, [variable2, constant2])
        def innerNode3 = new InternalNode(add, [innerNode1, innerNode2])

        expect:
        innerNode3.eval(variableMap) == 23
    }

    def "testing copy"(){
        def childNode = new NumericConstantNode(3)
        def add = new Add()
        def node = new InternalNode(add, [childNode])
        def copy = node.copy()
		def newNode = new NumericConstantNode(8)

        expect:
        node.function == copy.function
        node.children.equals(copy.children)
		copy.addChild(newNode)
		!(node.children.equals(copy.children))
    }

    def "testing addChild"(){
        def add = new Add()
        def node1 = new InternalNode(add, [])
        def constant1 = new NumericConstantNode(72)
        def constant2 = new NumericConstantNode(6)
        
        expect:
        node1.children.equals([])
        node1.addChild(constant1)
        node1.addChild(constant2)
        node1.children.equals([constant1, constant2])
        node1.eval(78)
    }
    
    def "testing size"(){
        def add = new Add()
        def node1 = new InternalNode(add, [])
        def node2 = new InternalNode(add, [])
        def constant1 = new NumericConstantNode(72)
        def constant2 = new NumericConstantNode(6)
        def constant3 = new NumericConstantNode(1)
        
        expect:
        node1.size() == 1
        node1.addChild(node2)
        node1.size() == 2
        node1.addChild(constant1)
        node1.size() == 3
        node2.addChild(constant2)
        node1.size() == 4
        node2.addChild(constant3)
        node1.size() == 5
    }
    
    def "testing isEqual"() {
        def constant1 = new NumericConstantNode(72)
        def constant2 = new NumericConstantNode(6)
        def constant3 = new NumericConstantNode(6)
        
        expect:
        !(constant1.isEqual(constant2))
        constant2.isEqual(constant3)
    }
    
    def "testing parent functionality"(){
        def add = new Add()
        def constant1 = new NumericConstantNode(72)
        def constant2 = new NumericConstantNode(6)
        def node1 = new InternalNode(add, [constant1, constant2])
        
        expect:
        node1.parent == null
        constant1.parent.isEqual(node1)
        constant2.parent.isEqual(node1)
    }
    
    def "testing removeChild"(){
        def add = new Add()
        def constant1 = new NumericConstantNode(72)
        def constant2 = new NumericConstantNode(6)
        def node1 = new InternalNode(add, [constant1, constant2])
        
        expect:
        node1.children.equals([constant1, constant2])
        node1.removeChild(constant1)
        node1.removeChild(constant2) == []
        node1.children.equals([])
    }

}
