package geneticTreeStructure

import functions.Add
import spock.lang.Specification

class FunctionTests extends Specification {
	def "testing add"(){
		def add = new Add()
		expect:
		add.doMath(3, 4) == 7
		add.doMath(2, 10) == 12
		add.doMath(-9, 4) == -5
	}

}
