package geneticTreeStructure

class Crossover {

    def subtreeCrossover = { tree1, tree2 ->
        def selector = new SubtreeSelection()
        def subtree1 = selector.doSelection(tree1, 2) // used 2 so that we can select any node in the tree
        def subtree2 = selector.doSelection(tree2, 2)
        def parent1 = subtree1.parent
        def parent2 = subtree2.parent

		//TODO: set parent1 and parent2 equal to the result of the swaps
        swap(parent1, subtree1, subtree2, tree2)
        swap(parent2, subtree2, subtree1, tree1)


    }

    private swap(parent, subtree1, subtree2, tree) {
        if(parent != null){
            parent.removeChild(subtree1)
            parent.addChild(subtree2)
        } else {
            tree = subtree2
        }
    }
}
