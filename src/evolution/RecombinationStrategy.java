/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package evolution;

/**
 * Implementors use strategies to recombine genes. Some suggestions:
 * <ul>
 * <li>Crossing over</li>
 * <li>Conservative site-specific recombination</li>
 * <li>Transpositional recombination</li>
 * </ul>
 * @author Andrew Nisbet
 */
public interface RecombinationStrategy
{
    /**
     * The reproduction function.
     * @param mates - one or more individuals who's genes are to be passed to
     * the next generation.
     * @return offspring Individual.
     */
    public Individual reproduce(Individual[] mates);
}
