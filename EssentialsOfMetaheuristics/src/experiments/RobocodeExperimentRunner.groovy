package experiments

import operators.TournamentSelection
import populationMethods.TreeGeneticAlgorithm
import functions.*
import geneticProgrammingProblems.RobocodeProblem
import geneticTreeStructure.Crossover
import geneticTreeStructure.InternalNode
import geneticTreeStructure.NumericConstantNode

class RobocodeExperimentRunner {

	static runExperiment(searchers, problems, numRuns = 30) {
		def popsize = 10
		def selector = new TournamentSelection()
		def crossover = new Crossover()
	
		def maxDepth = 3
	
		def add = new Add()
		def subtract = new Subtract()
		def multiply = new Multiply()
		def divide = new Divide()
		def ahead = new Ahead()
		def back = new Back()
		def getEnergy = new GetEnergy()
		def getVelocity = new GetVelocity()
		def setMaxTurnRate = new SetMaxTurnRate()
		def setMaxVelocity = new SetMaxVelocity()
		def turnLeft = new TurnLeft()
		def turnRight = new TurnRight()
		
		
		def rand = new Random()
	
		def functionSet = [
			{-> new InternalNode(add)},
			{-> new InternalNode(subtract)},
			{-> new InternalNode(multiply)},
			{-> new InternalNode(divide)},
			{-> new InternalNode(ahead)},
			{-> new InternalNode(back)},
			{-> new InternalNode(getEnergy)},
			{-> new InternalNode(getVelocity)},
			{-> new InternalNode(setMaxTurnRate)},
			{-> new InternalNode(setMaxVelocity)},
			{-> new InternalNode(turnLeft)},
			{-> new InternalNode(turnRight)}
		]
	
		def terminalSet = [
			{-> new NumericConstantNode(rand.nextInt(101))},
			{-> new NumericConstantNode(0)},
			{-> new NumericConstantNode(1)}
		]
	
		def args = [
			1,
			maxDepth,
			functionSet,
			terminalSet
		]
		
		for (p in problems) {
			for (s in searchers) {
				for (i in 0..<numRuns) {
					p.evalCount = 0
					def genome = s.minimize(p, popsize, selector, crossover.subtreeCrossover, args)
                    println "${s.toString()}\t${p.toString()}\t${genome.fitness}"
				}
			}
		}
	}

	static main(args) {
		def searchers = [
			new TreeGeneticAlgorithm(sizeLimit : 20)
		]
		def problems = [
			new RobocodeProblem(maxIterations : 20)
		]
		runExperiment(searchers, problems)
	}
}
