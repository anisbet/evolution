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
 *
 * @author Andrew Nisbet <anisbet@epl.ca>
 */
public class CrossOver implements RecombinationStrategy
{

    @Override
    public char[] mitosis(char[] dad, char[] mom)
    {
        int length = dad.length;
        char[] myNewGeneSequence = new char[length];
        SecureRandom sRandom = new SecureRandom();
        // Returns a pseudorandom, uniformly distributed int value between 0 
        // (inclusive) and the specified value (exclusive), drawn from this 
        // random number generator's sequence.
        int site = sRandom.nextInt(length);
        // Randomly select who's gene's get copied first.
        int who  = sRandom.nextInt(2);
        for (int i = 0; i < length; i++)
        {
            if (i <= site)
            {
                if (who == 0)
                {
                    myNewGeneSequence[i] = mom[i];
                }
                else
                {
                    myNewGeneSequence[i] = dad[i];
                }
            }
            else // Cross over and add dad's genes from here on.
            {
                if (who == 0)
                {
                    myNewGeneSequence[i] = dad[i];
                }
                else
                {
                    myNewGeneSequence[i] = mom[i];
                }
            }
        }
        return myNewGeneSequence;
    }
}
