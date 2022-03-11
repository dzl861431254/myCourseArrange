package com.dzl.ga;

import com.dzl.entity.Timetable;
//这个类将包含遗传算法本身的操作所需要的方法和变量，例如这个类包含处理交叉，变异，适应度评估和终止条件检查的逻辑。

public class GeneticAlgorithm {
    private int populationSize;//种族规模
    private double mutationRate;//变异率
    private double crossoverRate;//交叉率
    private int elitismCount;//精英成员数
    protected int tournamentSize;

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount,int tournamentSize) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.tournamentSize=tournamentSize;
    }


    public Population initPopulation(Timetable timetable){
        //Initialize population
        Population population=new Population(this.populationSize,timetable);
        return population;
    }

    public double calcFitness(Individual individual, Timetable timetable){
        //Create new timetable object to use---cloned from an existing  timetable
        Timetable threadTimetable=new Timetable(timetable);
        threadTimetable.createClasses(individual);

        //Calcuate fitness
        int clashes=threadTimetable.calcClashes();
        double fitness=1/(double)(clashes+1);
        individual.setFitness(fitness);
        return fitness;
    }

    public void evalPopulation(Population population,Timetable timetable){
        double populationFitness=0;
        //Loop over population evaluating individuals and summing population
        //fitness
        for (Individual individual:population.getIndividuals()){
            populationFitness+=this.calcFitness(individual,timetable);
        }
        population.setPopulationFitness(populationFitness);
    }

    public boolean isTerminationConditionMet(Population population){
        return population.getFittest(0).getFitness()==1.0;
    }
    public boolean isTerminationConditionMet(int generationsCount,int maxGenerations){
        return (generationsCount>maxGenerations);
    }

    //运用变异和交叉来进化种群。交叉算子是一个过程，在这个过程中，种群中的个体交换他们的遗传信息，希望创建一个新的个体，
    //包含亲代基因组钟过最好的部份
    //在交叉过程中，考虑种群中每个个体是否参与交叉，这是使用交叉率参数。通过比较交叉率和随机数，我们可以决定个体是否应该参与交叉
    //还是应该直接加入下一个种群，不受交叉影响。如果选择了一个个体参与交叉，就需要找到第二个亲代。要找到第二个亲代，我们需要在多
    //种可能的选择方法中挑一个

    public Population mutatePopulation(Population population,Timetable timetable){
        //Initialize new population
        Population newPopulation =new Population(this.populationSize);
        //Loop over current population by fitness
        for (int populationIndex=0;populationIndex<population.size();populationIndex++){
            Individual individual=population.getFittest(populationIndex);
            //Create random Individual to swap genes with
            Individual randomIndividual=new Individual(timetable);
            //Loop over individual's genes
            for (int geneIndex=0;geneIndex<individual.getChromosomeLength();geneIndex++){
                //skip mutation if this an elite individaul(跳过变异如果是一个符合的个体)
                if(populationIndex>this.elitismCount){
                    //Does this gene need mutation
                    if (this.mutationRate>Math.random()){
                        //Swap for new gene
                        individual.setGene(geneIndex,randomIndividual.getGene(geneIndex));
                    }
                }
            }
            //Add individual to population
            newPopulation.setIndividual(populationIndex,individual);
        }
        //Return mutated population
        return newPopulation;
    }




    //Todo:改成第三章的方法

    public Individual selectParent(Population population){
        //Create tournament
        Population tournament =new Population(this.tournamentSize);
        //Add random individuals to the tournament
        population.shuffle();
        for (int i = 0; i <this.tournamentSize ; i++) {
            Individual tournamentIndividual=population.getIndividual(i);
            tournament.setIndividual(i,tournamentIndividual);
        }
        //Return the best
        return  tournament.getFittest(0);
    }

//改成第二章的
    public Population crossoverPopulation(Population population) {
        //Create new population
        Population newPopulation=new Population(population.size());
        //Loop over current population by fitness
        for (int populationIndex=0;populationIndex<population.size();populationIndex++){
            Individual parent1=population.getFittest(populationIndex);
            //Apply crossover to this individual?
            if (this.crossoverRate>Math.random()&&populationIndex>elitismCount){
                //Initialize offspring
                Individual offfspring=new Individual(parent1.getChromosomeLength());
                //Find second parent
                Individual parent2=selectParent(population);
                //Loop over genome
                for (int geneIndex=0;geneIndex<parent1.getChromosomeLength();geneIndex++){
                    //use half of parent1's genes and half of parent2's genes
                    if(0.5>Math.random()){
                        offfspring.setGene(geneIndex,parent1.getGene(geneIndex));
                    }else{
                        offfspring.setGene(geneIndex,parent2.getGene(geneIndex));
                    }
                }
                //Add offspring to new population
                newPopulation.setIndividual(populationIndex,offfspring);
            }else {
                //Add individual to new population without applying crossover
                newPopulation.setIndividual(populationIndex,parent1);
            }
        }
            return newPopulation;
    }
}
