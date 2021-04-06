/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author themhz
 */
public class GA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        //Item 1 {p=10,w=10}
        //Item 2 {p=7 ,w=3}
        //Item 3 {p=8 ,w=1}
        //Item 4 {p=5 ,w=2}
        //Bag weight = 12                    
        //Bag weight = popsize=6;
        
        double popsize = 4;
        int maxWeight = 10;
        
        GeneticAlg1 ga = new GeneticAlg1(popsize);                
        ArrayList<Item> items = new ArrayList<Item>();
        
        //Όνομα αντικειμένου, Αξία, Βάρος
        items.add(new Item("Item 1", 10, 5));
        items.add(new Item("Item 2", 40, 4));
        items.add(new Item("Item 3", 100, 6));
        items.add(new Item("Item 4", 50, 3));
        items.add(new Item("Item 5", 50, 3));
        

        ArrayList<Atom> atoms = ga.initialiePopulation(items.size());        
        for(int i=0;i<1000;i++){
            ga.calculatefitness(atoms, items, maxWeight);
            ga.evaluation(atoms);
            Collections.shuffle(atoms);            
            ArrayList<Atom> selected = (ArrayList<Atom>)ga.select(atoms).clone();
            
                        
            ArrayList<Atom> newpopulation = ga.crossOver(selected);
                                                
            System.out.println("Parents:"+atoms);
            System.out.println("Kids:"+newpopulation);
            System.out.println("Generation "+i+" best solution:"+ga.getBestsolution());
            System.out.println("------------------------");
            
            atoms = (ArrayList<Atom>)newpopulation.clone();            
        }

        
        
    }

}
