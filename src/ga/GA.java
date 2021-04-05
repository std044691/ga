/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author themhz
 */
public class GA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Item 1 {p=10,w=10}
        //Item 2 {p=7 ,w=3}
        //Item 3 {p=8 ,w=1}
        //Item 4 {p=5 ,w=2}
        //Bag weight = 12
        Item i1= new Item("Item 1", 10, 10);
        Item i2= new Item("Item 2", 7, 3);
        Item i3= new Item("Item 3", 8, 1);
        Item i4= new Item("Item 4", 5, 2);
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);
        
        double popsize = 6;
        int maxWeight = 12;
        GeneticAlg1 ga = new GeneticAlg1(popsize);
        ArrayList<Atom> atoms = ga.initialiePopulation();
        ga.calculatefitness(atoms, items, maxWeight);       
        ga.generateProbabilitySum(atoms);
        ArrayList<Atom> selected = ga.createWheel(atoms);
        ArrayList<Atom> newpopulation = ga.crossOver(selected);
        System.out.println(selected);
        System.out.println(newpopulation);
        
        //System.out.println(selected);
        //
        //ga.calculatefitness(newpopulation, items, maxWeight);
        //System.out.println(newpopulation);
        
//        for (Atom atom : selected) {
//            atom.selected++;
//            System.out.print(atom.letter + ",");
//        }
//        System.out.println("");
//        
//        for (Atom atom : atoms) {
//            System.out.println(atom.letter+":" + Math.round(((atom.selected / popsize) * 100)*100)/100 + "%");
//        }

    }

}
