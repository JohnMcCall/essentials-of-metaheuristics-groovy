package geneticTreeStructure

class Mutation {
    def selector = new SubtreeSelection()

    def randomReplacement(root, treeBuilder, args){
        def subtree = selector.doSelection(root, 1) // used one so that we grab any non-leaf node
        def parent = subtree.parent

        if(!parent.equals(null)){
            parent.removeChild(subtree)
            subtree = treeBuilder(args)
            parent.addChild(subtree)
            return root
        } else {
            subtree = treeBuilder(args)
        }
    }
}
