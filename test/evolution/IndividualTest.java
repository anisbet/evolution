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
 * @author andrew
 */
public class IndividualTest
{
    private Individual i;
    private String search;
    public IndividualTest()
    {
        this.search = "hello";
        this.i = new Individual(this.search.length());
    }

    /**
     * Test of mate method, of class Individual.
     */
    @Test
    public void testMate()
    {
        System.out.println("mate");
        Individual mate = new Individual(this.search.length());
        Individual offSpring = this.i.mate(mate);
        System.out.println(">>" + offSpring);
    }

    /**
     * Test of mutate method, of class Individual. Because the mutations are 
     * by their nature random and even include the possibility of no change, this
     * method does not use assertions.
     */
    @Test
    public void testMutate()
    {
        System.out.println("== mutate ==");
        System.out.println(">>" + this.i.toString());
        for (int i = 0; i < 5; i++)
        {
            System.out.println(">>" + this.i.mutate(1));
        }
    }
}
