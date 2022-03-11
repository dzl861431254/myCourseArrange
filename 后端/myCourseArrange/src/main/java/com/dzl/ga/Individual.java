package com.dzl.ga;
//chromosome--->染色体
import com.dzl.entity.Course;

import com.dzl.entity.Team;
import com.dzl.entity.Timetable;
//初始化一个潜在结构成的种族。这通常是随机的，但偶尔也可能采用系统化的方法会更好，可以利用对搜索空间的已知信息来初始化种群。

//Individual 类代表词汇表候选解，主要负责存储和操作一条染色体，请注意，Individual类也有两个构造方法，一个构造方法接受一个
// 整数（代表染色体的长度），在初始化对象时将创建一条随机的染色体，另一个构造方法接受一个整数数组，用他作为染色体
//除了管理Individual的染色体，它也追踪个体的适应度值，也知道如何将自己打印为一个字符串
public class Individual {
    private  int[]chromosome;
    private double fitness=-1;
    public Individual(int[] chromosome){
        this.chromosome=chromosome;
    }

    public Individual(int chromosomeLength){
        this.chromosome=new int[chromosomeLength];
        for (int gene=0;gene<chromosomeLength;gene++){
            if (0.5<Math.random()){
                this.setGene(gene,1);
            }else{
                this.setGene(gene,0);
            }
        }
    }

    public int[] getChromosome(){
        return  this.chromosome;
    }

    public int getChromosomeLength(){
        return this.chromosome.length;
    }

    public void setGene(int offset,int gene){
        this.chromosome[offset]=gene;
    }
    public int getGene(int offset){
        return this.chromosome[offset];
    }
    public void setFitness(double fitness){
        this.fitness=fitness;
    }

    public String toString(){
        String output="";
        for (int gene=0;gene<this.chromosome.length;gene++){
            output+=this.chromosome[gene];
        }
        return output;
    }
    public double getFitness() {
        return this.fitness;
    }


    public Individual(Timetable timetable){
        int numClasses=timetable.getNumClasses();
        //1 gene for room,1 for time  1 for professor
        int chromosomeLength=numClasses*3;
        //Create random individual
        int newChromosome[]=new int[chromosomeLength];
        int chromosomeIndex=0;

        //Loop through teams
        for (Team team:timetable.getTeamsAsArray()){
            //Loop through courses
            for (int courseId:team.getCourseIds()){
            //Add random time
            int timeslotId=timetable.returnRandomTimeslot().getTimeslotId();
            newChromosome[chromosomeIndex]=timeslotId;
            chromosomeIndex++;

            //Add random room
            int roomId=timetable.returnRandomRoom().getRoomId();
            newChromosome[chromosomeIndex]=roomId;
            chromosomeIndex++;

            //Add random professor
            Course course=timetable.getCourse(courseId);
            newChromosome[chromosomeIndex]=course.haveProfessors();
            chromosomeIndex++;

        }
    }
        this.chromosome=newChromosome;
    }


}
