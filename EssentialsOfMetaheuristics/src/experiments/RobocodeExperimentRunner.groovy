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
		def popsize = 25
		def selector = new TournamentSelection()
		def crossover = new Crossover()
	
		def maxDepth = 4
	
		def ahead = new Ahead()
		def back = new Back()
		def setMaxTurnRate = new SetMaxTurnRate()
		def setMaxVelocity = new SetMaxVelocity()
		def turnLeft = new TurnLeft()
		def turnRight = new TurnRight()
		
		
		def rand = new Random()
	
		def functionSet = [
			{-> new InternalNode(ahead)},
			{-> new InternalNode(back)},
			{-> new InternalNode(turnLeft)},
			{-> new InternalNode(turnRight)},
                        {-> new InternalNode(setMaxTurnRate)},
                        {-> new InternalNode(setMaxVelocity)}
		]
	
		def terminalSet = [
			{-> new NumericConstantNode(rand.nextInt(101))},
//			{-> new NumericConstantNode(0)},
//			{-> new NumericConstantNode(1)},
//                        {-> new NumericConstantNode(Math.PI)},
//                        {-> new NumericConstantNode(2)}
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
                                        println "${s.toString()}\t${p.toString()}\t${genome.fitness}\t${genome.genome.makeJava()}"
				}
			}
		}
	}

	static main(args) {
		def searchers = [
			new TreeGeneticAlgorithm(sizeLimit : 20)
		]
		def problems = [
			new RobocodeProblem(maxIterations : 500)
		]
		runExperiment(searchers, problems)
	}
}
