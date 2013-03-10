package geneticTreeStructure

class SubtreeSelection {
	def isLeaf = {x -> x.leafNode}
	def isNotLeaf = {x -> !x.leafNode}
	def any = {x -> true}
	
	def closureList = [isLeaf, isNotLeaf, any]
	
	// pass it the index of which closure to grab from closureList
	def doSelection(root, closureNumber) {
		def func = closureList[closureNumber]
		def count = countNodes(root, func, 0)
		if(count == 0) {
			return null
		} else {
			Random rand = new Random()
			def randInt = rand.nextInt(count) + 1
			return pickNodes(root, randInt, func)
		}
	}
	
	def countNodes(node, func, count) {
		if(func(node)) {
			count++
		}
		node.children.each {
			count = countNodes(it, func, count)
		}
		
		return count
	}
	
	def counter = 0
	def pickedNode = null
	
	def pickNodes(node, randInt, func) {
		counter = 0
		pickedNode = null
		pickNodesHelper(node, randInt, func)
		return pickedNode
	}
	
	def pickNodesHelper(node, randInt, func) {
		def count = counter
		if(func(node)) {
			counter++
			if(counter == randInt) {
				pickedNode = node
			}
		}
		
		for(def child: node.children){
			def tempNode = pickNodesHelper(child, randInt, func)
			if(tempNode != null) {
				return tempNode
			}
		}
		return null
	}
	
}
