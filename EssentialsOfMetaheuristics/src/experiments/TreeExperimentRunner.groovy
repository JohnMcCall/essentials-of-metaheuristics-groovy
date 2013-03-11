package experiments

import functions.*
import geneticProgrammingProblems.SymbolicRegressionSinX
import geneticTreeStructure.Crossover
import geneticTreeStructure.InternalNode
import geneticTreeStructure.NumericConstantNode
import geneticTreeStructure.VariableNode
import operators.TournamentSelection
import populationMethods.TreeGeneticAlgorithm

class TreeExperimentRunner {
    
    static runExperiment(searchers, problems, numRuns = 10) {
        def popsize = 1
        def selector = new TournamentSelection()
        def crossover = new Crossover()
        
        def maxDepth = 3
        
        def add = new Add()
        def subtract = new Subtract()
        def multiply = new Multiply()
        def divide = new Divide()
        def rand = new Random()
        
        def functionSet = [{-> new InternalNode(add)}, {-> new InternalNode(subtract)}, 
                            {-> new InternalNode(multiply)}, {-> new InternalNode(divide)}]
        
        def terminalSet = [{-> new VariableNode("x")}, {-> new NumericConstantNode(rand.nextInt(50))}, 
                            {-> new NumericConstantNode(0)}, {-> new NumericConstantNode(1)}] 
        
        def args = [1, maxDepth, functionSet, terminalSet]
		
        println("Beginning Experiment!")
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
            new TreeGeneticAlgorithm(sizeLimit : 75)
        ]
        def problems = [
            new SymbolicRegressionSinX(numPoints : 20, maxIterations : 500),
            new SymbolicRegressionSinX(numPoints : 10, maxIterations : 500),
            new SymbolicRegressionSinX(numPoints : 20, maxIterations : 1000),
            new SymbolicRegressionSinX(numPoints : 10, maxIterations : 1000)
        ]
        // It would be nice to collect the total time here and include it in the
        // output.
        runExperiment(searchers, problems)
    }

}
