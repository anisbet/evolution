/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package evolution;

/**
 * The object is the Gene Pool, or properly the population. Within it are a 
 * 'n' individuals, all of which recombine to produce offspring. Once the fitness
 * of the offspring are tested they are ranked.
 * @author andrew
 */
public class Population
{
    private Individual[] population;
    
    public Population(int poolSize, int geneLength)
    {
        this.population = new Individual[poolSize];
        for (int i = 0; i < poolSize; i++)
        {
            this.population[i] = new Individual(geneLength);
        }
    }
    
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
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Ranking:\n");
        for (int i = 0; i < population.length; i++)
        {
            sb.append(i).append(") ").append(this.population[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
