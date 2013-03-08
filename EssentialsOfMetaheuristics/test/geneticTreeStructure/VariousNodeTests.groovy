package geneticTreeStructure

import functions.Add
import spock.lang.Specification

class VariousNodeTests extends Specification {
    def "testing one-deep tree with two constants"() {
        def constant1 = new NumericConstantNode(3)
        def constant2 = new NumericConstantNode(6)
        def add = new Add()
        def innerNode = new InternalNode(add)
		innerNode = innerNode.addChild(constant1)
		innerNode = innerNode.addChild(constant2)

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
        def innerNode = new InternalNode(add)
		innerNode = innerNode.addChild(variable1)
		innerNode = innerNode.addChild(variable2)

        expect:
        innerNode.eval(variableMap) == 14
    }

    def "testing one-deep tree with one variable and one constant"(){
        def variable1 = new VariableNode("x")
        def constant1 = new NumericConstantNode(2)
        def add = new Add()
        def variableMap = new HashMap()
        variableMap.put("x", 5)
        def innerNode = new InternalNode(add)
		innerNode = innerNode.addChild(variable1)
		innerNode = innerNode.addChild(constant1)
		

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

        def innerNode = new InternalNode(add)
		innerNode = innerNode.addChild(constant1)
		innerNode = innerNode.addChild(constant3)
		
        def innerNode1 = new InternalNode(add)
		innerNode1 = innerNode1.addChild(innerNode)
		innerNode1 = innerNode1.addChild(variable1)
		
        def innerNode2 = new InternalNode(add)
		innerNode2 = innerNode2.addChild(variable2)
		innerNode2 = innerNode2.addChild(constant2)
		
        def innerNode3 = new InternalNode(add)
		innerNode3 = innerNode3.addChild(innerNode1)
		innerNode3 = innerNode3.addChild(innerNode2)

        expect:
        innerNode3.eval(variableMap) == 23
    }

    def "testing copy"(){
        def childNode = new NumericConstantNode(3)
        def add = new Add()
        def node = new InternalNode(add)
		node = node.addChild(childNode)
        def copy = node.copy()
		def newNode = new NumericConstantNode(8)

        expect:
        node.function == copy.function
		
        for(int i = 0; i < node.children.size(); i++) {
			node.children[i].isEqual(copy.children[i])
		}
		
		copy.addChild(newNode)
		!(node.children.equals(copy.children))
    }

    def "testing addChild"(){
        def add = new Add()
        def node1 = new InternalNode(add)
        def constant1 = new NumericConstantNode(72)
        def constant2 = new NumericConstantNode(6)
		
		def newNode1 = node1.addChild(constant1)
		def newNode2 = newNode1.addChild(constant2)
	
        expect:
        node1.children.equals([])
        newNode1.children[0].isEqual(constant1)
		newNode2.children[0].isEqual(constant1)
		newNode2.children[1].isEqual(constant2)
        newNode2.eval(78)
    }
    
    def "testing size"(){
        def add = new Add()
        def node1 = new InternalNode(add)
        def node2 = new InternalNode(add)
        def constant1 = new NumericConstantNode(72)
        def constant2 = new NumericConstantNode(6)
        def constant3 = new NumericConstantNode(1)
        
		def newNode2 = node2.addChild(constant2)
		newNode2 = newNode2.addChild(constant3)
		def newNode1 = node1.addChild(newNode2)
		newNode1 = newNode1.addChild(constant1)

		
        expect:
        newNode1.size() == 5
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
        def node1 = new InternalNode(add)
		node1 = node1.addChild(constant1)
		node1 = node1.addChild(constant2)

		expect:
        node1.parent == null
        node1.children[0].parent.isEqual(node1)
        node1.children[1].parent.isEqual(node1)
    }
    
    def "testing removeChild"(){
        def add = new Add()
        def constant1 = new NumericConstantNode(72)
        def constant2 = new NumericConstantNode(6)
        def node1 = new InternalNode(add)
		
		node1 = node1.addChild(constant1)
		node1 = node1.addChild(constant2)
		
		def newNode1 = node1.removeChild(constant1)
		def newNode2 = newNode1.removeChild(constant2)
		
		expect:
        node1.children[0].isEqual(constant1)
		node1.children[1].isEqual(constant2)
        newNode1.children[0].isEqual(constant2)
		newNode2.children.equals([])
    }
	
	def "testing getRoot"() {
		def add = new Add()
		def constant1 = new NumericConstantNode(72)
		def constant2 = new NumericConstantNode(6)
		def constant3 = new NumericConstantNode(1)
		def node1 = new InternalNode(add)
		node1 = node1.addChild(constant1)
		node1 = node1.addChild(constant2)
		
		def node2 = new InternalNode(add)
		node2 = node2.addChild(node1)
		node2 = node2.addChild(constant3)
		
		expect:
		node2.children[0].children[1].getRoot().isEqual(node2)
	}
	
	def "testing toGet"() {
		def add = new Add()
		def constant1 = new NumericConstantNode(72)
		def constant2 = new NumericConstantNode(6)
		def constant3 = new NumericConstantNode(1)
		def node1 = new InternalNode(add)
		node1 = node1.addChild(constant1)
		node1 = node1.addChild(constant2)
		
		def node2 = new InternalNode(add)
		node2 = node2.addChild(node1)
		node2 = node2.addChild(constant3)
		
		def toFind = new NumericConstantNode(6)
		def found = node2.get(node2, toFind)
		
		expect:
		found.isEqual(constant2)
	}

}
