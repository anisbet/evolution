/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution;

import java.security.SecureRandom;

/**
 * Cross over takes the genes from two individuals and swaps random lengths of alleles
 * with each other to produce a gene that is the same length, but a mixture of 
 * the father and mother's genes). The cross over takes place at a site selected
 * at random and continues to the end of the parent's gene.
 * @author Andrew Nisbet
 */
public class CrossOver implements RecombinationStrategy
{   

    @Override
    public Individual reproduce(Individual[] mates)
    {
        if (mates.length != 2)
        {
            throw new IllegalArgumentException("cross over recombination requires a 2 mates.");
        }
        char[] dad = mates[0].getGene();
        char[] mom = mates[1].getGene();
        int length = dad.length;
        char[] myNewGeneSequence = new char[length];
        SecureRandom sRandom = new SecureRandom();
        int site = sRandom.nextInt(length -2) + 1; // this select from 0 to last chromosone otherwise child might inherit all genes from one parent.
        for (int i = 0; i < length; i++)
        {
            if (i < site)
            {
                myNewGeneSequence[i] = mom[i];
            }
            else
            {
                myNewGeneSequence[i] = dad[i];
            }
        }
        return new Individual(myNewGeneSequence);
    }
    
}
