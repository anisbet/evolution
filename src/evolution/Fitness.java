/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package evolution;

/**
 * This class tests the fitness of the resultant strings.
 * @author andrew
 */
public class Fitness
{
    private final String target;
    
    public Fitness(String finalResult)
    {
        this.target = finalResult;
    }
    
    public int testFitness(char[] gene)
    {
        // paranoid checking
        if (gene.length != this.target.length())
        {
            throw new IllegalArgumentException("huh? the gene length differs from the target string");
        }
        // The fitness of two strings can be measured by a hamming distance of 
        // the differences between the two strings. If the two strings are equal
        // the fitness value is 0.
        int fitness = 0; 
        for (int i = 0; i < gene.length; i++)
        {
            // To excentuate the difference we compute the square of the differences in the sequence.
            fitness += Math.pow((double)((int)gene[i] - (int)this.target.charAt(i)), 2.0);
        }
        return (int)fitness; // TODO finish this.
    }
    
    @Override
    public String toString()
    {
        return this.target;
    }
}
