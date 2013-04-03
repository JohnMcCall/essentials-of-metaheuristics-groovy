package operators;

import populationMethods.GenomeFitnessPair
import problems.OnesMax
import spock.lang.Specification;

/*
 * Using such large tournament sizes is a fairly blunt instrument, but it
 * (nearly) guarantees that we sample the "best" individual at least once
 * when running the tournament.
 */
class TournamentSelecionTest extends Specification {
	def "select returns the 'better' option on binary tournaments with pop size 2"() {
		given:
		TournamentSelection ts = new TournamentSelection(tournamentSize : 10)
		def problem = new OnesMax()
		def better = [1, 1, 1, 1]
		def worser = [0, 0, 0, 0]
		def genome1 = new GenomeFitnessPair(genome: better, fitness: 1)
		def genome2 = new GenomeFitnessPair(genome: worser, fitness: 0)
		def population = [genome1, genome2]
		
		when:
		def choice = ts.select(problem, population)
		
		then:
		choice == genome1
	}

	def "select returns the 'better' option on tournaments of size 10 with pop size 3"() {
		given:
		TournamentSelection ts = new TournamentSelection(tournamentSize : 20)
		def problem = new OnesMax()
		def better = [1, 1, 1, 1]
		def middle = [1, 0, 1, 0]
		def worser = [0, 0, 0, 0]
		def genome1 = new GenomeFitnessPair(genome: better, fitness: 2)
		def genome2 = new GenomeFitnessPair(genome: middle, fitness: 1)
		def genome3 = new GenomeFitnessPair(genome: worser, fitness: 0)
		def population = [genome1, genome2, genome3]
		
		when:
		def choice = ts.select(problem, population)
		
		then:
		choice == genome1
	}
}
