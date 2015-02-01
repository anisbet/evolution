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
 * Evolution is a workbench to test evolutionary processes. The application evolves
 * strings into a given target string and displays information about the process.
 * @author Andrew Nisbet <anisbet@epl.ca>
 */
public class Evolution
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int individualCount = 5; // in future this will come from cmd line options
        int mutations       = 3;
        Individual.setGeneticRecombinationStrategy(new CrossOver());
        Target target = new Target(
                "Hello World!", 
                "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz !@#$%&*");
        Population population = new Population();
        for (int i = 0; i < individualCount; i++)
        {
            population.add(new Individual(target.getGeneLength(), target.getPossibleAphabet()));
        }
        population.testFitness(target);
        population.view();
        int generationCount = 1;
        while (! population.isOptimal())
        {
            Individual elite = population.getEliteIndividual();
            int eliteIndex = 0;
            for (int i = 0; i < population.size(); i++)
            {
                Individual mate = population.getIndividual(i);
                if (mate.equals(elite)) 
                {
                    eliteIndex = i;
                    continue;
                }
                Individual child = elite.mate(mate);
                population.replace(i, child);
            }
            // Without this the population settles into a local minimum, and
            // the entire population remains homogeneous.
            elite.mutate(mutations);
            population.replace(eliteIndex, elite);
            population.testFitness(target);
            System.out.println("Generation: " + generationCount);
            System.out.println("the elite individual: " + elite);
            population.view();
            generationCount++;
//            break;
        }
    }
    
}
