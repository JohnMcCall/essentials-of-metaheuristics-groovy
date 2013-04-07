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
    def sizeLimit = -1
    def static id = 0

    // Our Algorithm takes a Genetic Algorithm Problem, a desired population size
    def minimize(problem, populationSize=popsize, selector=new TournamentSelection(), crossover=new Crossovers().onePointCrossover, args) {
        popsize = populationSize

        def startingPopulation = [] as Set

        popsize.times {
            def toAdd = problem.random(doFull, args)
            startingPopulation.add(new TreeGenomeFitnessPair(toAdd, problem.quality(toAdd), toAdd.size(), sizeLimit, id)) // Add a new random individual
            id++
        }

        def bestTree = problem.create(doFull, args)
        def best = new TreeGenomeFitnessPair(bestTree, problem.quality(bestTree), bestTree.size(), sizeLimit, id=-1)
        while(!problem.terminate(best.genome, best.fitness)) {
            for(def individual: startingPopulation) {
                if(best.compareTo(individual) < 0) {
                    best = individual
                }

            }
            //println(startingPopulation)
            def endingPopulation = [] as Set

            for(i in 0..(popsize/2)) {
                def parentA = selector.select(problem, startingPopulation as List)
                def parentB = selector.select(problem, startingPopulation as List)
                def children = crossover(parentA.genome, parentB.genome)

                def childA = problem.tweak(children[0], doFull, [1, 3, args[2], args[3]])
                def childB = problem.tweak(children[1], doFull, [1, 3, args[2], args[3]])
                
                def counter = 0
                
                while (childA.size() > sizeLimit && counter <= 50) {
                    childA = problem.tweak(children[0], doFull, [1, 3, args[2], args[3]])
                    if(counter == 50){
                        childA = parentA.genome
                    }
                    counter++
                }
                
                counter = 0
                
                while (childB.size() > sizeLimit && counter <= 50) {
                    childB = problem.tweak(children[1], doFull, [1, 3, args[2], args[3]])
                    if(counter == 50){
                        childB = parentB.genome
                    }
                    counter++
                }

                endingPopulation.add(new TreeGenomeFitnessPair(childA, problem.quality(childA), childA.size(), sizeLimit, id))
                id++
                endingPopulation.add(new TreeGenomeFitnessPair(childB, problem.quality(childB), childB.size(), sizeLimit, id))
                id++
            }
            startingPopulation = endingPopulation
            //println best
            //println getAverageSize(startingPopulation)
        }
        return best
    }
    
    private getAverageSize(population){
        def total = 0
        population.each {
            total += it.size
        }
        total/population.size()
    }

    String toString() {
        "GA_" + popsize
    }

}
