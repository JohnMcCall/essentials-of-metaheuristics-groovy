package geneticProgrammingProblems

import applications.robocode.BattleRunner
import applications.robocode.RobotBuilder

/*
* id : an id used in the generation of the name of the class.
* enemy_energy : the coefficient for the enemy's energy
* my_energy : the coefficient for our energy
* angle_diff : the coefficient for the different in angles between us and the point and then and the point
* distance : the coefficient for the distance between the point and the enemy
*/

class RobocodeMuPlus {
    Integer individualCount = 0
    Integer evalCount = 0
    Integer maxEvalCount = 100
    Random random = new Random()
    RobotBuilder robotBuilder = new RobotBuilder("templates/GateKeeperOS.template")
    BattleRunner battleRunner = new BattleRunner("templates/battle.template")
    def fitnesses = [:]
    
    def random() {
        ++individualCount
        [ 'id' : individualCount,
            'first' : random.nextInt(21) + 1,
            'second' : random.nextInt(21) + 1,
            'third' : random.nextInt(21) + 1
        ]
    }
    
    def copy(individual) {
        return individual.clone()
    }
    
    def tweak(individual) {
        ++individualCount
        def result = [ 'id' : individualCount,
            'first' : individual['first'] + random.nextInt(21) + 1,
            'second' : individual['second'] + random.nextInt(21) + 1,
            'third' : individual['third'] + random.nextInt(21) + 1
            ]
        return result
    }
    
    def quality(individual, bestSoFars = []) {
        if (fitnesses[individual['id']]) {
            return fitnesses[individual['id']]
        }
        ++evalCount
        robotBuilder.buildJarFile(individual)
        battleRunner.buildBattleFile(individual['id'], bestSoFars)
        def score = battleRunner.runBattle(individual['id'])
        fitnesses[individual['id']] = score
        return score
    }
    
    def terminate(bestIndividual, bestQuality) {
        evalCount > maxEvalCount
    }
}