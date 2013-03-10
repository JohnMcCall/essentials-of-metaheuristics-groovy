package geneticTreeStructure

class Crossover {
    
    def subtreeCrossover = { tree1, tree2 ->
        def tree1copy = tree1.copy()
        def tree2copy = tree2.copy()

        def selector = new SubtreeSelection()
        def subtree1 = selector.doSelection(tree1copy, 2) // used 2 so that we can select any node in the tree
        def subtree2 = selector.doSelection(tree2copy, 2)

        def parent1 = subtree1.parent
        def parent2 = subtree2.parent

        tree1copy = swap(parent1, subtree1, subtree2, tree1copy)
        tree2copy = swap(parent2, subtree2, subtree1, tree2copy)
      
		return [tree1copy, tree2copy]
    }

    private swap(parent, subtree1, subtree2, tree) {
        if(parent != null){
            def index = tree.getIndex(parent)
            tree = parent.removeChild(subtree1)
            def addTo = tree.get(tree, index, 0)
            tree = addTo.addChild(subtree2)
        } else {
            tree = subtree2
        }
    }
}
