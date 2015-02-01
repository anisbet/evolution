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
 * A single member of a population. This individual will evolve its internal string
 * towards a target string without knowledge of the contents of the target string.
 * The individual will be 'born' with a given set of genes that it inherits from
 * its parents and is judged for its fitness (closeness in match to the target 
 * string), and its choice of mates is limited by its fitness.
 * 
 * @author Andrew Nisbet <anisbet@epl.ca>
 */
class Individual
{
    private final char[] gene;
    private int rank;
    private static RecombinationStrategy STRATEGY = null;
    private static String possibleAlphabet;
    
    public static void setGeneticRecombinationStrategy(RecombinationStrategy strategy)
    {
        STRATEGY = strategy;
    }

    /**
     *
     * @param geneLength the value of geneLength
     * @param possibleAlphabet the value of possibleAlphabet
     */
    Individual(int geneLength, String possibleAlphabet)
    {
        this.possibleAlphabet = possibleAlphabet;
        if (geneLength < 1 || possibleAlphabet.length() < 1)
        {
            throw new IllegalArgumentException("Gene length and possible alphabet "
                    + "must be greater than or equal to one.");
        }
        this.gene = new char[geneLength]; 
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < gene.length; i++) 
        {
            gene[i] = possibleAlphabet.charAt(secureRandom.nextInt(possibleAlphabet.length()));
        }
    }
    
    /**
     * Constructor to test fitness etc.
     * @param string - typically the target string to assure fitness = 0.
     */
    Individual(String string)
    {
        this.gene = new char[string.length()]; 
        for (int i = 0; i < gene.length; i++) 
        {
            gene[i] = string.charAt(i);
        }
    }
    
    private Individual(char[] gene)
    {
        this.gene = new char[gene.length];
        System.arraycopy(gene, 0, this.gene, 0, gene.length);
    }
    
    char[] getGeneSequence()
    {
        return this.gene;
    }

    void setRank(int rank)
    {
        this.rank = rank;
    }
    
    int getRank()
    {
        return this.rank;
    }
    
    Individual mate(Individual mate)
    {
        if (STRATEGY == null)
        {
            throw new IllegalStateException("Strategy unset in Individual.");
        }
        return new Individual(STRATEGY.mitosis(this.gene, mate.gene));
    }
    
    @Override
    public String toString()
    {
        return new String(this.gene);
    }

    void mutate(int mutations)
    {
        for (int i = 0; i < mutations; i++)
        {
            // Pick a neucleotide somewhere along the gene and flip it.
            SecureRandom secureRandom = new SecureRandom();
            int nextNucleotide = secureRandom.nextInt(gene.length);
            gene[nextNucleotide] = pick(gene[nextNucleotide], secureRandom);
        }
    }
        // Picks a random flip of either a char higher or lower or no change.
    private char pick(char c, SecureRandom r)
    {
        int which = r.nextInt(3);
        int pos = this.possibleAlphabet.indexOf(c);
//            System.out.print("mutation type:" + which);
        char retChar;
        switch (which)
        {
            case 0:
                // pick lower character but check we need to wrap around start and end of string
                // if 'c' is the first or last character in the alphabet.
                if (pos <= 0)
                {
                    retChar = this.possibleAlphabet.charAt(this.possibleAlphabet.length() -1);
                }
                else
                {
                    retChar = this.possibleAlphabet.charAt(pos -1);
                }
                break;
            case 2:
                // increase the character by one.
                if (pos >= this.possibleAlphabet.length() -1)
                {
                    retChar = this.possibleAlphabet.charAt(0);
                }
                else
                {
                    retChar = this.possibleAlphabet.charAt(pos +1);
                }
                break;
            default: // 1 selected don't mutate the nucleotide.
                retChar = c;
                break;
        }
//        System.out.println(" changing " + c + " to " + retChar);
        return retChar;
    }
}
