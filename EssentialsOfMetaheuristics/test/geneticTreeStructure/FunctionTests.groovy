package geneticTreeStructure

import functions.Add
import functions.Subtract
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

}
