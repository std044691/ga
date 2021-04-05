/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

/**
 *
 * @author themhz
 */
public class Atom {
    public String letter;
    public Double probability;
    public Double from;
    public Double to;
    public Double selected;
    public double fitness;
    public double weight;
    public double value;
    
    public Atom(String letter) {
        this.letter = letter;
        this.probability = 0.0;
        this.from=0.0;
        this.to=0.0;
        this.selected=0.0;
        this.fitness =0.0;
        this.weight =0.0;
        this.value =0.0;
    }
    
    public String toString(){
        return this.letter +
                " weight :"+this.weight +
                " value :"+this.value + 
                " fitness :"+this.fitness + 
                " from :"+this.from + " - " + this.to + 
                "\n";
    }
    
}
