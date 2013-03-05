package geneticTreeStructure

class SubtreeSelection {
	// Closure to determine if a node is a leafNode ---- We should write some other "selecting" functions eventually
	def isLeaf = {x -> x.leafNode}
	def isNotLeaf = {x -> !x.leafNode}
	def any = {x -> true}
	
	def closureList = [isLeaf, isNotLeaf, any]
	
	def counter = 0
	
	// pass it the index of which closure to grab from closureList
	def doSelection(root, closureNumber) {
		counter = 0 
		def func = closureList[closureNumber]
		countNodes(root, func)
		if(counter == 0) {
			return null
		} else {
			Random rand = new Random()
			def randInt = rand.nextInt(counter) + 1
			counter = 0
			return pickNodes(root, randInt, func)
		}
	}
	
	def countNodes(node, func) {
		if(func(node)) {
			counter++
		}
		node.children.each {
			countNodes(it, func)
		}
	}
	
	def pickNodes(node, randInt, func) {
		if(func(node)) {
			counter++
			if(counter >= randInt) {
				return node
			}
		}
		
		for(def child: node.children){
			def tempNode = pickNodes(child, randInt, func)
			if(tempNode != null) {
				return tempNode
			}
		}
		return null
	}
	
}
