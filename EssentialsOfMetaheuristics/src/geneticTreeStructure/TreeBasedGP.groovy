package geneticTreeStructure

class TreeBasedGP {

    def doFull(depth, max, functionSet, terminalSet){
        if(depth >= max){
            getRandom(terminalSet).copy()
        } else {
            def node = getRandom(functionSet).copy()
            def arity = node.function.getArity()
            arity.times{
                print(depth)
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