package populationMethods
import problems.OnesMax

class Algorithm19 {
	def maximize(numParents, numChildren){
		def individualArr = []
		
		for (i in 0..numParents) {
			individualArr.add(problem.create())	  
		}
		def best = individualArr[0]
		def bestQuality = problem.quality(best)
		while(!problem.terminate(best, problem.quality(best))) {
			for (individual in individualArr) {
				def quality = problem.quality(individual)
				if (quality > bestQuality) {
					best = individual
				}
				
			}
			
			//Calculating the best of individualArr
			def sortedIndividualArr = []
			for (ind in individualArr){
				def indQuality = problem.quality(individual)
				def index = 0
				
				if(sortedIndividualArr.empty){
					sortedIndividualArr.add(0,ind)
				} else {
				   while(index <= sortedIndividualArr.size && indQuality < problem.quality(sortedIndividualArr.get(index))){
						index++
				   	}
				   //Left off here, add to the arr at the right index
				   sortedIndividualArr.add(index,ind)
				}
				
			}
			individualArr =[]
			for (i in 0..numParents){
				individualArr.add(sortedIndividualArr.get(i))
			}
			
		}
	}
}
