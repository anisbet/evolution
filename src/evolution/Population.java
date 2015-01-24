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
    public static int MUTATIONS = 3;
    private Individual[] population;
    private Individual[] nextGeneration;
    private SecureRandom randomGenerator;
    private RecombinationStrategy reproductionMethod;
    private Fitness fitness;
    private MateSelectionStrategy mateSelection;
    
    public Population(
            int poolSize, 
            int geneLength, 
            Fitness fitness, 
            RecombinationStrategy strategy,
            MateSelectionStrategy mateSelection
    )
    {
        this.population         = new Individual[poolSize];
        this.nextGeneration     = new Individual[poolSize]; // Populated after mating.
        this.randomGenerator    = new SecureRandom();
        this.reproductionMethod = strategy;
        this.fitness            = fitness;
        this.mateSelection      = mateSelection;
        for (int i = 0; i < poolSize; i++)
        {
            this.population[i] = new Individual(geneLength);
            this.population[i].setRank(fitness.testFitness(this.population[i].getGene()));
        }
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
    
    /**
     * Computes the fitness of each member of the population.
     */
    public void computeFitness()
    {
        for (int i = 0; i < this.population.length; i++)
        {
            Individual individual = this.population[i];
            if (individual != null)
            {
                individual.setRank(this.fitness.testFitness(individual.getGene()));
            }
        }
    }
    
    public int size()
    {
        return this.population.length;
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

    /**
     * Determines if the goal of the evolutionary experiment is reached.
     * @return true if one member of the population evolved the solution string
     * and therefore has a rank of '0' - a perfect match, and false otherwise.
     */
    public boolean isOptimal()
    {
        for (int i = 0; i < this.population.length; i++)
        {
            if (this.population[i] != null && this.population[i].getRank() < 1)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Arranges a barn dance where elite pairs meet and mate to produce the next 
     * generation.
     */
    void makeBabies()
    {
        while (this.hasUnmatchedPairs())
        {
            Individual eliteIndividual = this.getEliteIndividual();
            Individual[] partners      = this.mateSelection.findMates(this, eliteIndividual);
            Individual baby            = this.reproductionMethod.reproduce(partners);
        }
        this.replaceGeneration();
    }

    /**
     * 
     * @return the populations lowest ranked individual.
     */
    Individual getEliteIndividual()
    {
        Individual bestSoFar = null;
        for (int i = 0; i < this.population.length; i++)
        {
            if (this.population[i] != null)
            {
                Individual currentIndividual = this.population[i];
                if (bestSoFar == null 
                   || currentIndividual.getRank() < bestSoFar.getRank())
                {
                    bestSoFar = currentIndividual;
                }
            }
        }
        return bestSoFar;
    }

    /**
     * 
     * @return true if all the members of the population have mated, and false otherwise.
     */
    private boolean hasUnmatchedPairs()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Responsible for replacing the current generation with the next.
     * Takes care of the book keeping, that is, making sure the population count
     * is constant.
     */
    private void replaceGeneration()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
