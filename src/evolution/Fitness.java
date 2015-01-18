/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    public int testFitness(Individual offSpring)
    {
        return 0; // TODO finish this.
    }
    
    @Override
    public String toString()
    {
        return this.target;
    }
}
