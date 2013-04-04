package functions

class Multiply implements FunctionInterface {
        def name = "Multiply"
        def arity = 2
        
        def doMath = {x, y -> x * y}
        
        String toString(){
                "Name: " + name
        }
        
        def isEqual(function) {
            this.name == function.name
        }
		
		String makeJava(list) {
			def leftChild = list[0]
			def rightChild = list[1]
			leftChild.makeJava() + " * " +  rightChild.makeJava()
		}

}
