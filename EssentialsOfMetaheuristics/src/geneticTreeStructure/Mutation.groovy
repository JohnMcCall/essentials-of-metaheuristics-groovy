package geneticTreeStructure

class Mutation {
    def selector = null
    def root = null

    def Mutation(node){
        root = node
        selector = new SubtreeSelection(root)
    }


    def randomReplacement(tree, depth, max, functionSet, terminalSet){
        def subtree = selector.doSelection()
        def parent = subtree.parent

        if(!parent.equals(null)){
            parent.removeChild(subtree)
            subtree = tree(depth, max, functionSet, terminalSet)
            parent.addChild(subtree)
            return root
        } else {
            subtree = tree(depth, max, functionSet, terminalSet)
        }
    }
}
