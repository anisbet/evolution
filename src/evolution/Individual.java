/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package evolution;

import java.security.SecureRandom;
import java.util.Random;

/**
 * The physical individual that will reproduce with another individual.
 * @author andrew
 */
public class Individual
{
    public class Randomizer
    {
        private final String alphabet; 
        private final int N;
        private SecureRandom r;
        
        public Randomizer()
        {
            this.alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&*";
            this.N    = alphabet.length();
            this.r    = new SecureRandom();
        }

        public void initGeneSequence(char[] gene)
        {
            for (int i = 0; i < gene.length; i++) 
            {
                gene[i] = alphabet.charAt(r.nextInt(N));
            }
        }
        
        public void mutate(char[] gene, int mutations)
        {
            for (int i = 0; i < mutations; i++)
            {
                // Pick a neucleotide somewhere along the gene and flip it.
                int nextNucleotide = r.nextInt(gene.length);
                gene[nextNucleotide] = pick(gene[nextNucleotide]);
            }
        }
        
        // Picks a random flip of either a char higher or lower or no change.
        private char pick(char c)
        {
            int which = r.nextInt(3);
            int pos = this.alphabet.indexOf(c);
//            System.out.print("mutation type:" + which);
            char retChar;
            switch (which)
            {
                case 0:
                    // pick lower character but check we need to wrap around start and end of string
                    // if 'c' is the first or last character in the alphabet.
                    if (pos <= 0)
                    {
                        retChar = this.alphabet.charAt(this.alphabet.length() -1);
                    }
                    else
                    {
                        retChar = this.alphabet.charAt(pos -1);
                    }
                    break;
                case 2:
                    // increase the character by one.
                    if (pos >= this.alphabet.length() -1)
                    {
                        retChar = this.alphabet.charAt(0);
                    }
                    else
                    {
                        retChar = this.alphabet.charAt(pos +1);
                    }
                    break;
                default: // 1 selected don't mutate the nucleotide.
                    retChar = c;
                    break;
            }
//            System.out.println(" changing " + c + " to " + retChar);
            return retChar;
        }
    }
    
    private char[] gene;
    private Randomizer randomizer;
    /**
     * Creates individual with a pre-defined gene length.
     * @param geneLength 
     */
    public Individual(int geneLength)
    {
        if (geneLength < 1)
        {
            throw new IllegalArgumentException("Genes must have more than 1 nucleotide.");
        }
        this.gene = new char[geneLength];
        // fill the gene with random nucleotides.
        this.randomizer = new Randomizer();
        this.randomizer.initGeneSequence(gene);
    }
    
    public Individual mate(Individual mate)
    {
        return new Individual(this.gene.length);
    }
    
    public String mutate(int mutations)
    {
        this.randomizer.mutate(gene, mutations);
        return this.toString();
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.gene.length; i++)
        {
            sb.append(this.gene[i]);
        }
        return sb.toString();
    }
}
