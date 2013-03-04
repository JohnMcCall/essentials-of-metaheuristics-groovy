package geneticTreeStructure

class Crossover {
    def Crossover(){

    }

    def subtreeCrossover = { tree1, tree2 ->
        def selector1 = new SubtreeSelection(tree1)
        def selector2 = new SubtreeSelection(tree2)
        def subtree1 = selector1.doSelection()
        def subtree2 = selector2.doSelection()
        def parent1 = subtree1.parent
        def parent2 = subtree2.parent

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
