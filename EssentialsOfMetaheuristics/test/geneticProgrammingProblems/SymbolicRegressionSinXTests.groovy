package geneticProgrammingProblems

import functions.*
import geneticTreeStructure.GeneticTree
import geneticTreeStructure.InternalNode
import geneticTreeStructure.NumericConstantNode
import geneticTreeStructure.VariableNode
import spock.lang.Specification

class SymbolicRegressionSinXTests extends Specification {
	def "testing getPoints"() {
		def problem = new SymbolicRegressionSinX()
		def givenPoints = problem.getPoints()
		def actualPoints = [0.6283185307179586, 1.2566370614359172, 1.8849555921538759, 2.5132741228718345, 
							3.141592653589793, 3.7699111843077517, 4.39822971502571, 5.026548245743669, 
							5.654866776461628, 6.283185307179586]
		
		expect:
		givenPoints == actualPoints
	}
	
	def "testing quality"() {
		def problem = new SymbolicRegressionSinX()
		def geneticTree = new GeneticTree()
		def functionSet = [{-> new InternalNode(new Add())}, {-> new InternalNode(new Subtract())}]
		def terminalSet = [{-> new VariableNode("x")}, {-> new NumericConstantNode(83)}, 
                                        {-> new NumericConstantNode(1990)}, {-> new NumericConstantNode(12)}]
		
		def root = geneticTree.doFull([1, 5, functionSet, terminalSet])
		def quality = problem.quality(root)
		
		expect:
		quality >= 0
		println(quality)
	}
}
