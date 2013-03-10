package geneticTreeStructure

import spock.lang.Specification
import functions.Add

class SubtreeSelectionTests extends Specification {
    
    def "Test countNodes"() {
        def isLeaf = {x -> x.leafNode}
        
        def constant1 = new NumericConstantNode(98796)
        def constant2 = new NumericConstantNode(1234)
        def constant3 = new NumericConstantNode(234)
        
        def add = new Add()
        def innerNode1 = new InternalNode(add)
        innerNode1 = innerNode1.addChild(constant2)
        innerNode1 = innerNode1.addChild(constant3)
        def root = new InternalNode(add)
        root = root.addChild(innerNode1)
        root = root.addChild(constant1)
        
        def selector = new SubtreeSelection()
        
        expect:
        def result = selector.countNodes(root, isLeaf, 0)
		def testResult = selector.countNodes(constant3, isLeaf, 0)
        result == 3
		testResult == 1
        
    }
    
    def "Test pickNodes"() {
        def isLeaf = {x -> x.leafNode}
        
        def constant1 = new NumericConstantNode(98796)
        def constant2 = new NumericConstantNode(1234)
        def constant3 = new NumericConstantNode(234)
        
        def add = new Add()
        def innerNode1 = new InternalNode(add)
        innerNode1 = innerNode1.addChild(constant2)
        innerNode1 = innerNode1.addChild(constant3)
        def root = new InternalNode(add)
        root = root.addChild(innerNode1)
        root = root.addChild(constant1)
        
        def selector = new SubtreeSelection()
        
        expect:
        def pickedNode = selector.pickNodes(root, 2, isLeaf)
        pickedNode.isEqual(constant3)
    }
    
    
}
