/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author themhz
 */
public class GeneticAlg1 {

    private double popsize;

    public GeneticAlg1(double popsize) {
        this.popsize = popsize;
    }
    
    public ArrayList<Atom> initialiePopulation(){
        ArrayList<Atom> atoms = new ArrayList<Atom>();
        Atom a = new Atom("0011");
        atoms.add(a);
        Atom b = new Atom("1101");
        atoms.add(b);
        Atom c = new Atom("1110");
        atoms.add(c);
        Atom d = new Atom("0101");
        atoms.add(d);
        Atom e = new Atom("1010");
        atoms.add(e);
        Atom f = new Atom("0111");
        atoms.add(f);
        
        return atoms;
    }
    
    //Επιλογή του πληθισμού βάση της πιθανότητας επιλογής τους
    public  ArrayList<Atom> createWheel(ArrayList<Atom> atoms) {

        ArrayList<Double> randoms = this.randomGenerator();       
        ArrayList<Double> probabilitySum = this.generateProbabilitySum(atoms);
        ArrayList<Atom> selected = new ArrayList<Atom>();
       
        for (Double random : randoms) {
            for (Atom atom : atoms) {
                if (atom.from <= random && atom.to >= random) {
                    selected.add(atom);
                }
            }
        }

        return selected;
    }

    //Γενάει popsize νούμερα από το 0 εως το 1
    public ArrayList<Double> randomGenerator() {
        ArrayList<Double> rands = new ArrayList<Double>();
        Random r = new Random();
        Double p = 0.0;
        for (int i = 0; i < (int)this.popsize; i++) {
            p = (double) Math.round(r.nextDouble() * 100) / 100;
            rands.add(p);
        }
        return rands;
    }

    //Υπολογισμός αθροιστικών πιθανοτήτων
    public ArrayList<Double> generateProbabilitySum(ArrayList<Atom> atoms) {
        ArrayList<Double> probabilitySum = new ArrayList<Double>();
        Double temp = new Double(0.00);
        probabilitySum.add(temp);
        for (Atom atom : atoms) {
            atom.from = temp+0.01;
            atom.to = (double) Math.round((temp + atom.fitness) * 100) / 100;
            temp = atom.to;
            probabilitySum.add(temp);
        }

        return probabilitySum;
    }
    
    //Διασταύρωη των λύσεων
    public ArrayList<Atom> crossOver(ArrayList<Atom> atoms){
        ArrayList<Atom> children = new ArrayList<Atom>();
  
        int length = atoms.get(0).letter.length();
        int splits = length/2;        
        for(int i=0; i<atoms.size();i++){
            String dad = atoms.get(i).letter;
            String mom = atoms.get(i+1).letter;
           
            Atom kid1 = new Atom(atoms.get(i).letter.substring(0,splits)+atoms.get(i+1).letter.substring(splits,length));
            Atom kid2 = new Atom(atoms.get(i).letter.substring(splits,length)+atoms.get(i+1).letter.substring(0,splits));
            i++;
            children.add(kid1);
            children.add(kid2);

        }
        
        
        return children;
    }
    
    public void calculatefitness(ArrayList<Atom> atoms,  ArrayList<Item> items, int maxWeight){
        //Item 1 {p=10,w=10}
        //Item 2 {p=7 ,w=3}
        //Item 3 {p=8 ,w=1}
        //Item 4 {p=5 ,w=2}
        //Bag weight = 12    
        double totalFitness = 0;
        double probabilitySum = 0;
        for(Atom atom:atoms){
            double totalWeight = 0;
            double totalValue = 0;
            for(int i=0; i< atom.letter.length();i++){
                if(atom.letter.charAt(i)=='1'){                   
                    totalWeight += items.get(i).weight;
                    totalValue += items.get(i).value;
                }   
            }            
            atom.weight = totalWeight;
            atom.value = totalValue;
            if(totalWeight<=maxWeight){                
                atom.fitness = totalValue;
            }else{
                atom.fitness = 1;
            }
            
            totalFitness+=atom.fitness;
        }
                
        
        
        Double totalf=0.0;
        for(Atom atom:atoms){
            atom.fitness = atom.fitness/totalFitness;           
            totalf += atom.fitness;
        }
        //System.out.println(totalf);
        
        //Build rullet values
        //probabilitySum = totalFitness/this.popsize;
        
    }

}
