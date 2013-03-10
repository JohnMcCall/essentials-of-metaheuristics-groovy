package geneticTreeStructure

class Mutation {
    def sizeLimit = -1
    def selector = new SubtreeSelection()

    def randomReplacement(root, treeBuilder, args){
        def rootCopy = root.copy()
        def subtree = selector.doSelection(rootCopy, 2) // used one so that we grab any non-leaf node
        def parent = subtree.parent

        if(!parent.equals(null)){
			def index = root.getIndex(parent)
            rootCopy = parent.removeChild(subtree)
			def addTo = root.get(root, index, 0)
            rootCopy = addTo.addChild(treeBuilder(args))
            if(rootCopy.size() > sizeLimit){
                return root
            } else {
                return rootCopy
            }
        } else {
            treeBuilder(args)
        }
    }
}
