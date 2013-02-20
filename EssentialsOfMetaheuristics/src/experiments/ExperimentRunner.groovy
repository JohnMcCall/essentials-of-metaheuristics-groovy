package experiments

import problems.Griewank
import problems.OnesMax

import populationMethods.GeneticAlgorithm
import populationMethods.GeneticAlgorithmWithoutSets

class ExperimentRunner {

    static runExperiment(searchers, problems, numRuns = 30, populationSize) {
        for (p in problems) {
            for (s in searchers) {
                for (i in 0..<numRuns) {
                    p.evalCount = 0
                    def result = s.maximize(p, populationSize)
                    println "${s.toString()}\t${p.toString()}\t${p.quality(result)}"
                }
            }
        }
    }

    static main(args) {
        def searchers = [
            new GeneticAlgorithm(),
			new GeneticAlgorithmWithoutSets()

        ]
        def problems = [
           new OnesMax(numBits: 250, maxIterations: 250),
           new Griewank()
        ]
        // It would be nice to collect the total time here and include it in the
        // output.
        runExperiment(searchers, problems, 100)
		println "-------------------------------------------------------------"
		runExperiment(searchers, problems, 1000)
    }

}
