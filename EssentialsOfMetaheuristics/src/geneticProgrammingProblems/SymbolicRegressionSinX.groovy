package geneticProgrammingProblems

import geneticTreeStructure.GeneticTree
import geneticTreeStructure.Mutation

class SymbolicRegressionSinX {
	Integer evalCount = 0
	Integer maxIterations = 1000
	def numPoints = 10.0
	def maximalQuality = 0 // the lower the quality the better for this problem
	def mutator = new Mutation([sizeLimit : 50])
	def geneticTree = new GeneticTree()
	
	def points = getPoints()
	
	def quality = { tree ->
		evalCount++
		// Create an array of results of calling Sin(x) on every point
		def sinArray = []
		points.each {
			sinArray.add(Math.sin(it))
		}
		
		// Create an array of results of calling our Tree with x-> every point
		def treeArray = []
		points.each {
			treeArray.add(tree.eval(["x" : it])) // assume x is the only variable
		}
		// Take the absolute value of the difference between the two at every point 
		def absArray = []
		def count = 0
		treeArray.each {
			absArray.add(Math.abs(it - sinArray[count]))
			count++
		}
		// Take the average of these points to give us the quality
		def result = 0
		absArray.each {
			result += it
		}
		result/numPoints
	}
	
	def create = { treeBuilder = geneticTree.doFull, args ->
		random(treeBuilder, args)
	}
	
	def copy = { tree -> tree.clone() }
	
	def tweak = { tree, treeBuilder = geneticTree.doFull, args  ->
		mutator.randomReplacement(tree, treeBuilder, args)
	}
	
	def random = { treeBuilder = geneticTree.doFull, args ->
		treeBuilder(args)
	}
	
	def terminate = { tree, q = quality(tree) ->
		evalCount >= maxIterations || q == maximalQuality
	}
	
	String toString() {
		this.class.name.split("\\.")[-1] + "_" + numPoints + "_" + maxIterations
	}
	
	private getPoints() {
		def toReturn = []
		def interval = (2*Math.PI/numPoints)
		def previous = 0
		numPoints.times {
			toReturn.add(interval + previous)
			previous += interval
		}
		
		toReturn
	}

}
