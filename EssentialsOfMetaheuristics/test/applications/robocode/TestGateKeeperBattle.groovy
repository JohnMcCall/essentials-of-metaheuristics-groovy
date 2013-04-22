package applications.robocode

import spock.lang.Specification


/*
 * This assumes that there is a copy of Robocode in your home directory,
 * and that it has been configured (via the GUI) to be able to load robot
 * files from the evolved_robots directory in this project.
 */
class TestGateKeeperBattle extends Specification {
	/*
	 * id : an id used in the generation of the name of the class.
	 * enemy_energy : the coefficient for the enemy's energy
	 * my_energy : the coefficient for our energy
	 * angle_diff : the coefficient for the different in angles between us and the point and then and the point
	 * distance : the coefficient for the distance between the point and the enemy
	 */
	def id
	def movementCode = "setTurnRight( 245 );setBack( 56 * moveDirection );setAhead( 44 * moveDirection );"
	def robotBuilder
	def battleRunner

	def setup() {
		Random random = new Random()
		id = random.nextInt(1000000)
        def values = ["id" : id, "movementCode" : movementCode]

		robotBuilder = new RobotBuilder("templates/GateKeeperOS.template")
		robotBuilder.buildJarFile(values)

		battleRunner = new BattleRunner("templates/battle.template")
	} 

	def "Check that the battle file is correctly constructed"() {
		when:
		battleRunner.buildBattleFile(id, "sample.Walls")

		then:
		confirmBattleFile()
	}

	def "Check that we can run a battle and extract the scores"() {
		given:
		battleRunner.buildBattleFile(id, "sample.Walls")

        when:
		def score = battleRunner.runBattle(id)

        then:
		score >= 0
	}

	def confirmBattleFile() {
		File file = new File("evolved_robots/evolve.battle")
        def contents = file.readLines()
        def interestingLines = contents.findAll { line ->
            (line.indexOf("robocode.battle.selectedRobots") >= 0)
        }
        assert interestingLines.size() == 1
		assert interestingLines[0].indexOf("sample.Walls") >= 0
        assert interestingLines[0].indexOf("evolved.GateKeeper_${id}") >= 0
        return true
	}
}