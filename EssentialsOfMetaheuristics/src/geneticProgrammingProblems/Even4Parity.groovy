package geneticProgrammingProblems

import geneticTreeStructure.GeneticTree
import geneticTreeStructure.Mutation

class Even4Parity {
    Integer evalCount = 0
    Integer maxIterations = 1000
    def maximalQuality = 0 // the lower the quality the better for this problem (normally it would be 16)
    def mutator = new Mutation()
    def geneticTree = new GeneticTree()

    def quality = { tree ->
        def fitness = 16
        evalCount++
        println("Evalcount: " + evalCount)
        def tests = [ [d0 : true, d1 : true, d2 : true, d3 : true],
                      [d0 : true, d1 : true, d2 : true, d3 : false],
                      [d0 : true, d1 : true, d2 : false, d3 : true],
                      [d0 : true, d1 : true, d2 : false, d3 : false],
                      [d0 : true, d1 : false, d2 : true, d3 : true],
                      [d0 : true, d1 : false, d2 : true, d3 : false],
                      [d0 : true, d1 : false, d2 : false, d3 : true],
                      [d0 : true, d1 : false, d2 : false, d3 : false],
                      [d0 : false, d1 : true, d2 : true, d3 : true],
                      [d0 : false, d1 : true, d2 : true, d3 : false],
                      [d0 : false, d1 : true, d2 : false, d3 : true],
                      [d0 : false, d1 : true, d2 : false, d3 : false],
                      [d0 : false, d1 : false, d2 : true, d3 : true],
                      [d0 : false, d1 : false, d2 : true, d3 : false],
                      [d0 : false, d1 : false, d2 : false, d3 : true],
                      [d0 : false, d1 : false, d2 : false, d3 : false]]
        tests.each {
            def result = tree.eval(it)
            if(result){
                fitness--
            }
        }
        return fitness
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
}
