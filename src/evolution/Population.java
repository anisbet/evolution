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

import java.util.ArrayList;
import java.util.List;

/**
 * This acts as a container class for a population of individuals and contains 
 * convenience methods.
 * 
 * @author Andrew Nisbet <anisbet@epl.ca>
 */
class Population
{
    private List<Individual> population = new ArrayList();

    void add(Individual individual)
    {
        this.population.add(individual);
    }

    void testFitness(Target target)
    {
        for (Individual i : this.population)
        {
            i.setRank(target.testFitness(i.getGeneSequence()));
        }
    }
    
    void view()
    {
        for (Individual i : this.population)
        {
            System.out.println(i + " : " + i.getRank());
        }
    }

    boolean isOptimal()
    {
        for (Individual i : this.population)
        {
            if (i.getRank() == 0) return true;
        }
        return false;
    }

    Iterable<Individual> getIterator()
    {
        return this.population;
    }

    Individual getEliteIndividual()
    {
        int lowestRank = 99999999;
        int indexLowestRankedIndividual = 0;
        for (int i = 0; i < this.population.size(); i++)
        {
            if (this.population.get(i).getRank() < lowestRank)
            {
                lowestRank = this.population.get(i).getRank();
                indexLowestRankedIndividual = i;
            }
        }
        return this.population.get(indexLowestRankedIndividual);
    }

    void replace(int i, Individual child)
    {
        this.population.remove(i);
        this.population.add(i, child);
    }

    int size()
    {
        return this.population.size();
    }

    Individual getIndividual(int i)
    {
        return this.population.get(i);
    }
}
