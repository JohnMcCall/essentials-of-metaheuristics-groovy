package experiments

import operators.TournamentSelection
import populationMethods.TreeGeneticAlgorithm
import functions.*
import geneticProgrammingProblems.RobocodeProblem
import geneticTreeStructure.Crossover
import geneticTreeStructure.GeneticTree
import geneticTreeStructure.InternalNode
import geneticTreeStructure.NumericConstantNode
import geneticTreeStructure.TerminalFunctionNode
import geneticTreeStructure.VariableNode

class RobocodeExperimentRunner {

    static runExperiment(searchers, problems, numRuns = 5) {
        def popsize = 50
        def selector = new TournamentSelection()
        def crossover = new Crossover()
        
        def nodeTreeMaker = new GeneticTree()
        
        def maxDepth = 4
        
        def rand = new Random()
        
        def getHeading = new GetHeading()
        def getBearing = new GetBearing()
        def getDistance = new GetDistance()
        
        def add = new Add()
        def subtract = new Subtract()
        def multiply = new Multiply()
        def divide = new Divide()
        
        def nodeFuntionSet = [
            {-> new InternalNode(add)},
            {-> new InternalNode(subtract)},
            {-> new InternalNode(multiply)},
            {-> new InternalNode(divide)},
        ]
        
        def nodeTerminalSet = [
            {-> new TerminalFunctionNode(getHeading)},
            {-> new TerminalFunctionNode(getBearing)},
            {-> new TerminalFunctionNode(getDistance)},
            {-> new NumericConstantNode(rand.nextInt(361) + 1)},
            {-> new NumericConstantNode(1)},
            {-> new NumericConstantNode(180)}
            
        ]
        
        def nodeArgs = [
            1,
            3,
            nodeFuntionSet,
            nodeTerminalSet
        ]

        def ahead = new Ahead([args: nodeArgs, treeMaker: nodeTreeMaker])
        def back = new Back([args: nodeArgs, treeMaker: nodeTreeMaker])
        def turnLeft = new TurnLeft([args: nodeArgs, treeMaker: nodeTreeMaker])
        def turnRight = new TurnRight([args: nodeArgs, treeMaker: nodeTreeMaker])

        def functionSet = [
            {-> new InternalNode(ahead)},
            {-> new InternalNode(back)},
            {-> new InternalNode(turnLeft)},
            {-> new InternalNode(turnRight)},
        ]
        
        def terminalSet = [
            {-> new VariableNode("x")} //just want to ignore it
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
            if(file.getName() != "HappyRobot526319.jar" && file.getName() != "HypeMachine_31.jar") {
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
