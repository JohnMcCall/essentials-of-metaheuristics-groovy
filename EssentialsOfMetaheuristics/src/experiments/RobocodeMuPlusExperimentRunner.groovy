package experiments

import geneticProgrammingProblems.RobocodeMuPlus
import populationMethods.MuPlusLambdaES

class RobocodeMuPlusExperimentRunner {

    static runExperiment(searchers, problems, numRuns = 1) {
        for (p in problems) {
            for (s in searchers) {
                for (i in 0..<numRuns) {
                    p.evalCount = 0
                    def result = s.maximize(p)
                    println "${s.toString()}\t${p.toString()}\t${p.quality(result)}\t${result}"                    
                }
            }
        }
    }

    static main(args) {
        def searchers = [
            new MuPlusLambdaES() // numParents: 10, numChildren: 20)
        ]
        def problems = [
            new RobocodeMuPlus(maxEvalCount: 500)
        ]
        // It would be nice to collect the total time here and include it in the
        // output.
        runExperiment(searchers, problems)
    }

}