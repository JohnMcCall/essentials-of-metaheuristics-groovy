package experiments

import operators.TournamentSelection
import populationMethods.TreeGeneticAlgorithm
import functions.*
import geneticProgrammingProblems.RobocodeProblem
import geneticTreeStructure.Crossover
import geneticTreeStructure.InternalNode
import geneticTreeStructure.NumericConstantNode

class RobocodeExperimentRunner {

    static runExperiment(searchers, problems, numRuns = 5) {
        def popsize = 50
        def selector = new TournamentSelection()
        def crossover = new Crossover()

        def maxDepth = 4

        def ahead = new Ahead()
        def back = new Back()
        def turnLeft = new TurnLeft()
        def turnRight = new TurnRight()


        def rand = new Random()

        def functionSet = [
            {-> new InternalNode(ahead)},
            {-> new InternalNode(back)},
            {-> new InternalNode(turnLeft)},
            {-> new InternalNode(turnRight)},
        ]

        def terminalSet = [
            {-> new NumericConstantNode(rand.nextInt(101))},
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
                    cleanup()
                }
            }
        }
    }


    static cleanup(){
        def userHome = System.getProperty("user.home")

        File dir = new File("${userHome}/robocode/robots")
        
        for(File file: dir.listFiles()){
            if(file.getName() != "robot.database" && file.getName() != "chacha-mermaids-ec1.jar" && 
                file.getName() != "HappyProgrammersR1.jar" && file.getName() != "NicRoundOne.jar") {
                file.delete()
            }
        }
        
        File evolved = new File("evolved_robots")
        for(File file: evolved.listFiles()){
            if(file.isDirectory()) {
                for(File innerFile: file.listFiles()){
                    innerFile.delete()
                }
            } else {
                file.delete()
            }
        }
        System.gc()
    }

    static main(args) {
        def searchers = [
            new TreeGeneticAlgorithm(sizeLimit : 20)
        ]
        def problems = [
            new RobocodeProblem(maxIterations : 1500)
        ]
        cleanup()
        runExperiment(searchers, problems)
    }
}
