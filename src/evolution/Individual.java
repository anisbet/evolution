/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Andrew Nisbet
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package evolution;

import java.security.SecureRandom;

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
            this.alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz !@#$%&*";
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
    private int rank;
    
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
    
    /**
     * Creates an individual with a given gene sequence. Equivalent to a child
     * being born with the genes passed to it by it's parents.
     * @param gene 
     */
    public Individual(char[] gene)
    {
        this.gene = new char[gene.length];
        this.randomizer = new Randomizer();
        for (int i = 0; i < gene.length; i++)
        {
            this.gene[i] = gene[i];
        }
    }
    
    /**
     * Returns the fitness of this individual.
     * @return fitness score within the population.
     */
    public int getRank()
    {
        return rank;
    }

    /**
     * Sets the individual's ranking, or fitness score within the population.
     * @param rank fitness score
     */
    public void setRank(int rank)
    {
        this.rank = rank;
    }
    
    /** 
     * Gets the gene sequence.
     * @return gene sequence of char[].
     */
    public char[] getGene()
    {
        return gene;
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
        sb.append(" => ").append(this.rank);
        return sb.toString();
    }
}
