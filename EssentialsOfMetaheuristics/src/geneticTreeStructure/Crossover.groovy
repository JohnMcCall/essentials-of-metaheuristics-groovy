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

		//TODO: set parent1 and parent2 equal to the result of the swaps
        tree1copy = swap(parent1, subtree1, subtree2, tree2copy)
        tree2copy = swap(parent2, subtree2, subtree1, tree1copy)
        
        [tree1copy, tree2copy]

    }

    private swap(parent, subtree1, subtree2, tree) {
        if(parent != null){
            tree = parent.removeChild(subtree1)
            tree = tree.addChild(subtree2)
        } else {
            tree = subtree2
        }
    }
}
