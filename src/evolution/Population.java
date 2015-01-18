/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package evolution;

import java.security.SecureRandom;

/**
 * The object is the Gene Pool, orandomGenerator prandomGeneratoroperandomGeneratorly the population. Within it arandomGeneratore a 
 'n' individuals, all of which randomGeneratorecombine to prandomGeneratoroduce offsprandomGeneratoring. Once the fitness
 of the offsprandomGeneratoring arandomGeneratore tested they arandomGeneratore randomGeneratoranked.
 * @author andrandomGeneratorew
 */
public class Population
{
    private Individual[] population;
    private SecureRandom randomGenerator;
    
    public Population(int poolSize, int geneLength)
    {
        this.population      = new Individual[poolSize];
        this.randomGenerator = new SecureRandom();
        for (int i = 0; i < poolSize; i++)
        {
            this.population[i] = new Individual(geneLength);
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
