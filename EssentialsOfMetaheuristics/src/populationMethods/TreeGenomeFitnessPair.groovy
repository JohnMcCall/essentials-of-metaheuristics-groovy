package populationMethods

class TreeGenomeFitnessPair implements Comparable{
    protected def genome
    protected def fitness
    protected def size
    def sizeLimit = -1

    def TreeGenomeFitnessPair(genome, fitness, size, sizeLimit) {
        this.genome = genome
        this.fitness = fitness
        this.size = size
        this.sizeLimit = sizeLimit

        if(size > sizeLimit) {
            this.fitness = Integer.MAX_VALUE
        }
    }

    //This is set so that default sort methods will return the pairs sorted in descending order by fitness
    public int compareTo(pair2) {
        if (this.fitness > pair2.fitness){
            -1
        } else if (this.fitness < pair2.fitness) {
            1
        } else {
            0
        }
    }

    public String toString() {
        return "<${fitness}, ${size}, ${genome}>"
    }
}