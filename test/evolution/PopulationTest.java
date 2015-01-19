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
public class PopulationTest
{
    private Population p;
    private Fitness fitness;
    public PopulationTest()
    {
        this.fitness = new Fitness("Hello World!");
        this.p = new Population(20, "Hello World!".length(), fitness);
    }

    /**
     * Test of cull method, of class Population.
     */
    @Test
    public void testCull()
    {
        System.out.println("== cull ==");
        System.out.println(this.p.toString());
        this.p.cull(1);
        System.out.println(this.p.toString());
    }
   
}
