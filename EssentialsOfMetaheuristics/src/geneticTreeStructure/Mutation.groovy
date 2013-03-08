package geneticTreeStructure

class Mutation {
    def selector = new SubtreeSelection()

    def randomReplacement(root, treeBuilder, args){
        def rootCopy = root.copy()
        def subtree = selector.doSelection(rootCopy, 1) // used one so that we grab any non-leaf node
        def parent = subtree.parent

        if(!parent.equals(null)){
            rootCopy = parent.removeChild(subtree)
            rootCopy = rootCopy.addChild(treeBuilder(args))
            return rootCopy
        } else {
            treeBuilder(args)
        }
    }
}
