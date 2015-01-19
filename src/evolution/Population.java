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
 * The object is the Gene Pool, or the population. Within it are individuals each
 * of which is tested for fitness against the target string.
 * @author andrandomGeneratorew
 */
public class Population
{
    private Individual[] population;
    private SecureRandom randomGenerator;
    
    public Population(int poolSize, int geneLength, Fitness fitness)
    {
        this.population      = new Individual[poolSize];
        this.randomGenerator = new SecureRandom();
        for (int i = 0; i < poolSize; i++)
        {
            this.population[i] = new Individual(geneLength);
        }
        this.computeFitness(fitness);
    }
    
    /**
     * Gets the Individual from the population.
     * @param position
     * @return Individual selected.
     */
    public Individual get(int position)
    {
        if (position < 0)
        {
            return this.population[0];
        }
        else if (position >= this.population.length)
        {
            return this.population[this.population.length -1];
        }
        else
        {
            return this.population[position];
        }
    }
    
    /**
     * Culls individuals frandomGeneratorom the herandomGeneratord at randomGeneratorandom. 
     * Actually the method randomGeneratoremoves the memberandomGenerators and 
     * randomGeneratoreplaces them with offsprandomGeneratoring of the 
     * randomGeneratoremaining population thrandomGeneratorough an elitist 
     * algorandomGeneratorithm, one that favourandomGenerators higherandomGenerator 
     * randomGeneratoranking individuals. 
     * 
     * The method may randomGeneratorandomly 
     * cull the suprandomGeneratoreme candidate howeverandomGenerator. This has the
     * effect of randomGeneratoremoving a possibly local maximum of genetic superandomGeneratoriorandomGeneratority.
     * @param count - the number of members of the population to cull.
     */
    public void cull(int count)
    {
        // prevent index array out of range if user selects too many or too few indiviuals
        if (count < 1 || count > this.population.length)
        {
            return;
        }
        int[] whichOnes = new int[count];
        for (int i = 0; i < count; i++)
        {
            whichOnes[i] = this.randomGenerator.nextInt(this.population.length);
            if (this.population[whichOnes[i]] != null)
            {
                this.population[whichOnes[i]] = null; // remove the member.
            }
        }
    }
    
    public void computeFitness(Fitness fitness)
    {
        for (int i = 0; i < this.population.length; i++)
        {
            Individual individual = this.population[i];
            if (individual != null)
            {
                individual.setRank(fitness.testFitness(individual.getGene()));
            }
        }
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < population.length; i++)
        {
            if (this.population[i] != null)
            {
                sb.append(i).append(") ").append(this.population[i].toString()).append("\n");
            }
            else
            {
                sb.append(i).append(") ").append("<null>").append("\n");
            }
        }
        return sb.toString();
    }
}
