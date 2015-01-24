/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */

package evolution;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrew Nisbet <anisbet@epl.ca>
 */
public class DominantPairTest
{
    
    public DominantPairTest()
    {
    }

    /**
     * Test of findMates method, of class DominantPair.
     */
    @Test
    public void testFindMates()
    {
        System.out.println("==findMates==");
        MateSelectionStrategy mSelection = new DominantPair();
        Population population = new Population(10, 12, new Fitness("this or that"), new CrossOver(), mSelection);
        Individual individual = population.getEliteIndividual();
        Individual[] result = mSelection.findMates(population, individual);
        System.out.println("        population:\n" + population);
        System.out.println("pair selection for: " + individual);
        for (int i = 0; i < result.length; i++)
        {
            System.out.println("              mate: " + result[i]);
        }
    }
    
}
