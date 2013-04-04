package geneticTreeStructure

import functions.*
import spock.lang.Specification

class FunctionTests extends Specification {
    def "testing add"(){
        def add = new Add()
        expect:
        add.doMath(3, 4) == 7
        add.doMath(2, 10) == 12
        add.doMath(-9, 4) == -5
    }

    def "testing subtract"(){
        def subtract = new Subtract()
        expect:
        subtract.doMath(3, 4) == -1
        subtract.doMath(10, 2) == 8
        subtract.doMath(9, -4) == 13
    }
    
    def "testing divide"(){
        def divide = new Divide()
        expect:
        divide.doMath(8, 4) == 2
        divide.doMath(10, 0) == 1
        divide.doMath(3, 6) == 0.5
    }
    
    def "testing multiply"(){
        def multiply = new Multiply()
        expect:
        multiply.doMath(3, 4) == 12
        multiply.doMath(10, 2) == 20
        multiply.doMath(9, -4) == -36
    }
	
	def "testing ahead"() {
		def ahead = new Ahead()
		def list = [new NumericConstantNode(5)]
		
		def add = new InternalNode(new Add())
		def child1 = new NumericConstantNode(2)
		def child2 = new NumericConstantNode(6)
		add = add.addChild(child1)
		add = add.addChild(child2)
		
		def advancedList = [add]
		expect:
		list[0].value == 5
		"ahead(5);" == ahead.makeJava(list)
		"ahead(2 + 6);" == ahead.makeJava(advancedList)
	}

}
