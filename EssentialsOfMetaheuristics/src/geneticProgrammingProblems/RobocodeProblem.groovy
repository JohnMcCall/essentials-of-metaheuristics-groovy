package geneticProgrammingProblems

import applications.robocode.BattleRunner
import applications.robocode.RobotBuilder
import geneticTreeStructure.GeneticTree
import geneticTreeStructure.Mutation

class RobocodeProblem {
    Integer evalCount = 0
    Integer maxIterations = 1000
    def maximalQuality = Integer.MAX_VALUE
    def mutator = new Mutation()
    def geneticTree = new GeneticTree()
    def robotBuilder = new RobotBuilder("templates/GateKeeperOS.template")
    def battleRunner = new BattleRunner("templates/battle.template")
    def opponents = [
        "evolved.ChaChaR1",
        "evolved.HappyProgrammersR1",
        "nicsEvolved.NicRoundOne"
    ]
    def static id = 0;

    def quality = { tree ->
        evalCount++

        def movementCode = tree.makeJava()
        def values = ["id" : id, "movementCode" : movementCode]
        def score = 0
        opponents.each {
            robotBuilder.buildJarFile(values)
            battleRunner.buildBattleFile(values["id"], it)
            
            score += battleRunner.runBattle(values["id"])
            
            values["id"]++
            id++
        }
        
        return score/opponents.size()
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
        this.class.name.split("\\.")[-1] + "_" + maxIterations
    }


}
