/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package evolution;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrew Nisbet <anisbet@epl.ca>
 */
public class IndividualTest
{
    
    public IndividualTest()
    {
    }

    /**
     * Test of getGeneSequence method, of class Individual.
     */
    @Test
    public void testGetGeneSequence()
    {
        System.out.println("==getGeneSequence==");
        Individual instance = new Individual(1, "ABCD");
        // Since the value will necessarily be random we can't check exact values
        System.out.println("GENE: '" + new String(instance.getGeneSequence()) + "'");
        instance = new Individual(1, "A");
        System.out.println("GENE: '" + new String(instance.getGeneSequence()) + "'");
        try
        {
            instance = new Individual(0, "A");
            System.out.println("GENE: '" + new String(instance.getGeneSequence()) + "'");
            instance = new Individual(1, "");
            System.out.println("GENE: '" + new String(instance.getGeneSequence()) + "'");
        }
        catch (RuntimeException e)
        {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    /**
     * Test of setRank method, of class Individual.
     */
    @Test
    public void testSetRank()
    {
        System.out.println("==setRank==");
        Individual instance = new Individual(1, "A");
        instance.setRank(99);
        assertTrue(instance.getRank() == 99);
    }

    /**
     * Test of toString method, of class Individual.
     */
    @Test
    public void testToString()
    {
        System.out.println("==toString==");
        Individual instance = new Individual(1, "A");        
        System.out.println(instance.toString());
    }
    
}
