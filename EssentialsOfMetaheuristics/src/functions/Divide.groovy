package functions

class Divide implements FunctionInterface {
        def name = "Divide"
        def arity = 2
        
        def doMath = { x, y -> if(y == 0) {1} else {x / y} }
        
        String toString(){
                "Name: " + name
        }

        def isEqual(function) {
            this.name == function.name
        }
		
		String makeJava(list) {
			def leftChild = list[0]
			def rightChild = list[1]
			leftChild.makeJava() + " / " +  rightChild.makeJava()
		}
}
