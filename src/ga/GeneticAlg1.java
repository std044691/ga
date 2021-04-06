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

    private double popSize;
    private Atom bestsolution;
    public int maxWeight;

    public GeneticAlg1(double popsize) {
        this.popSize = popsize;
        this.bestsolution = new Atom("0000");        
    }

    public ArrayList<Atom> initialiePopulation(int size) {
        ArrayList<Atom> atoms = new ArrayList<Atom>();
        atoms = randomSolutions(size);
        //atoms = customSolutions();
        

        return atoms;
    }

    //Επιλογή του πληθισμού βάση της πιθανότητας επιλογής τους
    public ArrayList<Atom> select(ArrayList<Atom> atoms) {

        //Δημουργία της εξαναγκασμένης ρουλέτας
        ArrayList<Double> randoms = this.randomGenerator();   
        ArrayList<Double> probabilitySum = this.generateProbabilitySum(atoms);
        //Προσορινός επιλεγμένος πληθισμός που θα γεμίσει μετά
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

    //Γενάει popSize νούμερα από το 0 εως το 1
    public ArrayList<Double> randomGenerator() {
        ArrayList<Double> rands = new ArrayList<Double>();
        Random r = new Random();
        Double p = 0.0;
        for (int i = 0; i < (int) this.popSize; i++) {
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
            atom.from = temp + 0.01;
            atom.to = (double) Math.round((temp + atom.fitness) * 100) / 100;
            temp = atom.to;
            probabilitySum.add(temp);
        }

        return probabilitySum;
    }

    //Διασταύρωη των λύσεων
    public ArrayList<Atom> crossOver(ArrayList<Atom> atoms) {
        ArrayList<Atom> children = new ArrayList<Atom>();

        int length = atoms.get(0).letter.length();
        
        //int splits = length / 2;
        int splits = (int)(Math.random() * atoms.size());
        
        System.out.println("splits at:"+splits);
        for (int i = 0; i < atoms.size(); i++) {
            String dad = atoms.get(i).letter;
            try{
                String mom = atoms.get(i + 1).letter;

                Atom kid1 = new Atom(atoms.get(i).letter.substring(0, splits) + atoms.get(i + 1).letter.substring(splits, length));
                Atom kid2 = new Atom(atoms.get(i).letter.substring(splits, length) + atoms.get(i + 1).letter.substring(0, splits));
                i++;
                children.add(kid1);
                children.add(kid2);
            }catch(Exception e){
                children.add(atoms.get(i));
            }
            

        }

        return children;
    }

    public void calculatefitness(ArrayList<Atom> atoms, ArrayList<Item> items, int maxWeight) {
        this.maxWeight = maxWeight;
        //Item 1 {p=10,w=10}
        //Item 2 {p=7 ,w=3}
        //Item 3 {p=8 ,w=1}
        //Item 4 {p=5 ,w=2}
        //Bag weight = 12    
        double totalFitness = 0;        
        for (Atom atom : atoms) {
            double totalWeight = 0;
            double totalValue = 0;
            for (int i = 0; i < atom.letter.length(); i++) {
                if (atom.letter.charAt(i) == '1') {
                    totalWeight += items.get(i).weight;
                    totalValue += items.get(i).value;
                }
            }
            atom.weight = totalWeight;
            atom.value = totalValue;
            if (totalWeight <= maxWeight) {
                atom.fitness = totalValue;
            } else {
                atom.fitness = 1;
            }

            totalFitness += atom.fitness;
        }

        //Double totalf = 0.0;
        for (Atom atom : atoms) {
            atom.fitness = atom.fitness / totalFitness;
            //totalf += atom.fitness;
        }       
    }
        
    public void evaluation(ArrayList<Atom> atoms) throws CloneNotSupportedException{
        for(Atom atom :atoms){
            if(this.bestsolution.value<atom.value && atom.weight<=this.maxWeight){
                this.bestsolution = (Atom)atom.clone();
            }
        }
    }
    

    public ArrayList<Atom> randomSolutions(int size) {
         ArrayList<Atom> atoms = new ArrayList<Atom>();             
         int min = 0;
         int max = 1;
         int range = max - min + 1;
         String nible = "";
         // generate random numbers within 1 to 10
         for (int j = 0; j < this.popSize; j++) {
             for (int i = 0; i < size; i++) {
                 int rand = (int) (Math.random() * range) + min;
                 nible += rand;
             }
             Atom b = new Atom(nible);
             atoms.add(b);
             nible="";
         }

         //System.out.println("");
         return atoms;
     }
    public ArrayList<Atom> customSolutions() {
         ArrayList<Atom> atoms = new ArrayList<Atom>();             
         Atom a = new Atom("00110");
         atoms.add(a);
         Atom b = new Atom("11101");
         atoms.add(b);
         Atom c = new Atom("01010");
         atoms.add(c);
         Atom d = new Atom("10101");
         atoms.add(d);
         Atom e = new Atom("01110");
         atoms.add(e);                
         Atom f = new Atom("01101");
         atoms.add(f);                        



         return atoms;
     }


    public Atom getBestsolution() {
         return bestsolution;
     }

   
   
   
}
