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
