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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andrew
 */
public class PopulationTest
{
    private Population p;
    private Fitness fitness;
    public PopulationTest()
    {
        this.fitness = new Fitness("Hello World!");
        RecombinationStrategy strategy = new CrossOver();
        MateSelectionStrategy mateSelection = new DominantPair();
        this.p = new Population(20, "Hello World!".length(), fitness, strategy, mateSelection);
        
        for (int i = 0; i < this.p.size(); i++)
        {
            this.p.get(i).setSelected();
            System.out.println("Are there unmatched pairs? " + i + ")" + this.p.hasUnmatchedPairs());
        }
    }

    /**
     * Test of cull method, of class Population.
     */
    @Test
    public void testCull()
    {
        System.out.println("== cull ==");
        System.out.println(this.p.toString());
        this.p.cull(1);
        System.out.println(this.p.toString());
    }
   
}
