package geneticTreeStructure

class GeneticTree {
	
	// The node returned by this is the root of the tree
    def doFull = { args ->
		def depth = args[0]
		def max = args[1]
		def functionSet = args[2]
		def terminalSet = args[3]
		
        if(depth >= max){
            getRandom(terminalSet)
        } else {
            def node = getRandom(functionSet)
            def arity = node.function.getArity()
			def newArgs = args.clone()
			newArgs[0]++
            arity.times{
                node.addChild(doFull(newArgs))
            }
            return node
        }
    }

    def getRandom(list){
        Random rand = new Random()
        list[rand.nextInt(list.size())]()
    }
}