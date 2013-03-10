package geneticTreeStructure

import functions.*
import spock.lang.Specification

class CrossoverTests extends Specification {
    def "testing subtree crossover"(){
        def add = new Add()
        def subtract = new Subtract()
        def innerNode1 = {-> new InternalNode(add) }
        def innerNode2 = {-> new InternalNode(subtract) }
        def functionSet = [innerNode1, innerNode2]

        def constant1 = {-> new NumericConstantNode(72) }
        def constant2 = {-> new NumericConstantNode(6) }

        def variable1 = {-> new VariableNode("x") }
        def variable2 = {-> new VariableNode("y") }
        def variableMap = new HashMap()
        variableMap.put("x", 5)
        variableMap.put("y", 9)

        def terminalSet = [constant1, constant2, variable1, variable2]
        def gpTree = new GeneticTree()
	def args = [1, 3, functionSet, terminalSet]
        def root1 = gpTree.doFull(args)
        def root2 = gpTree.doFull(args)
        
        println(root1)
        println(root2)

        def crossover = new Crossover()        
        def results = crossover.subtreeCrossover(root1, root2)
        
        expect:
        println(results[0])
        println(results[1])
    }
}
