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
 * Evolution is a simulator of how software can represent evolve solutions to 
 * a simple problem: Create the string 'Hello World!".
 * @author andrew Nisbet
 */
public class Evolution
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // 
        String inputString                       = "Hello World!";
        Fitness fitnessTest                      = new Fitness(inputString);
        RecombinationStrategy reproductionMethod = new CrossOver();
        MateSelectionStrategy mateSelection      = new DominantPair();
        Population population = new Population(
                20, 
                inputString.length(), 
                fitnessTest, 
                reproductionMethod,
                mateSelection
        );
        System.out.println("Initial population: ");
        System.out.println(population.toString());
        int generationCount = 1;
        while (! population.isOptimal())
        {
            System.out.println("generation " + generationCount + " " + population.getEliteIndividual());
            population.makeBabies();
            generationCount++;
        }
        System.out.println("generation " + generationCount + " " + population.getEliteIndividual());
    }
    
}
