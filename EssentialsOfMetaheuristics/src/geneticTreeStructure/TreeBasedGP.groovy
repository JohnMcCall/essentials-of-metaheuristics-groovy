package geneticTreeStructure

class TreeBasedGP {

    def doFull(depth, max, functionSet, terminalSet){
        if(depth >= max){
            getRandom(terminalSet)
        } else {
            def node = getRandom(functionSet)
            def arity = node.function.getArity()
            arity.times{
                node.addChild(doFull(depth+1, max, functionSet, terminalSet))
            }
            return node
        }
    }

    def getRandom(list){
        Random rand = new Random()
        list[rand.nextInt(list.size())].copy()
    }
}