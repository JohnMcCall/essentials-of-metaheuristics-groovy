package populationMethods

import geneticTreeStructure.GeneticTree
import java.util.Random
import operators.Crossovers
import operators.TournamentSelection

class TreeGeneticAlgorithm {
    // Algorithm 20

    // We need popsize to be global so that we can use it in the toString method, also added a default value
    def popsize = 100
    def geneticTree = new GeneticTree()
    def doFull = geneticTree.doFull

    // Our Algorithm takes a Genetic Algorithm Problem, a desired population size
    def minimize(problem, populationSize=popsize, selector=new TournamentSelection(), crossover=new Crossovers().onePointCrossover, args) {
        popsize = populationSize

        def startingPopulation = [] as Set

        popsize.times {
            def toAdd = problem.random(doFull, args)
            startingPopulation.add(toAdd) // Add a new random individual
        }

        def best = problem.create(doFull, args)
        def qualityOfBest = problem.quality(best)
        while(!problem.terminate(best, qualityOfBest)) {
            for(def individual: startingPopulation) {
                def newQuality = problem.quality(individual)
                if(newQuality < qualityOfBest) {
                    best = individual
                    qualityOfBest = newQuality
                }

            }
            //println(startingPopulation)
            def endingPopulation = [] as Set

            for(i in 0..(popsize/2)) {
                def parentA = selector.select(problem, startingPopulation as List)
                def parentB = selector.select(problem, startingPopulation as List)
                def children = crossover(parentA, parentB)
                endingPopulation.add(problem.tweak(children[0], doFull, [1, 3, args[2], args[3]]))
                endingPopulation.add(problem.tweak(children[1], doFull, [1, 3, args[2], args[3]]))
            }
            startingPopulation = endingPopulation
        }
        return best
    }

    String toString() {
        "GA_" + popsize
    }

}
