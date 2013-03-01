package geneticTreeStructure

import spock.lang.Specification
import functions.Add

class SubtreeSelectionTests extends Specification {
    /** 
     * We Tried to use setup but it doesn't work.
    def setup() {
        def isLeaf = {x -> x.leafNode}
        
        def constant1 = new NumericConstantNode(98796)
        def constant2 = new NumericConstantNode(1234)
        def constant3 = new NumericConstantNode(234)
        
        def add = new Add()
        def innerNode1 = new InternalNode(add, [constant2, constant3])
        def root = new InternalNode(add, [innerNode1, constant1])
    } **/
    
    def "Test countNodes"() {
        def isLeaf = {x -> x.leafNode}
        
        def constant1 = new NumericConstantNode(98796)
        def constant2 = new NumericConstantNode(1234)
        def constant3 = new NumericConstantNode(234)
        
        def add = new Add()
        def innerNode1 = new InternalNode(add, [constant2, constant3])
        def root = new InternalNode(add, [innerNode1, constant1])
        
        def selector = new SubtreeSelection(root)
        
        expect:
        selector.countNodes(root, isLeaf)
        selector.counter == 3
        
    }
    
    def "Test pickNodes"() {
        def isLeaf = {x -> x.leafNode}
        
        def constant1 = new NumericConstantNode(98796)
        def constant2 = new NumericConstantNode(1234)
        def constant3 = new NumericConstantNode(234)
        
        def add = new Add()
        def innerNode1 = new InternalNode(add, [constant2, constant3])
        def root = new InternalNode(add, [innerNode1, constant1])
        
        def selector = new SubtreeSelection(root)
        
        expect:
        def pickedNode = selector.pickNodes(root, 2, isLeaf)
        pickedNode.isEqual(constant3)
    }
    
    
}
