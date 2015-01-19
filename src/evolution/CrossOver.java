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
        // Returns a pseudorandom, uniformly distributed int value between 0 
        // (inclusive) and the specified value (exclusive), drawn from this 
        // random number generator's sequence.
        int site = sRandom.nextInt(length);
        for (int i = 0; i < length; i++)
        {
            if (i <= site)
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
