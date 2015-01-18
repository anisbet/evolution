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
public class FitnessTest
{
    
    public FitnessTest()
    {
    }

    /**
     * Test of testFitness method, of class Fitness.
     */
    @Test
    public void testTestFitness()
    {
        System.out.println("== testFitness ==");
        Fitness instance = new Fitness("h");
        int result = instance.testFitness(new char[]{'h'});
        System.out.println("FITNESS:" + result);
        assertEquals(0, result);
        
        result = instance.testFitness(new char[]{'g'});
        System.out.println("FITNESS:" + result);
        assertEquals(1, result);
        
        result = instance.testFitness(new char[]{'i'});
        System.out.println("FITNESS:" + result);
        assertEquals(1, result);
        
        instance = new Fitness("hello");
        result = instance.testFitness(new char[]{'h','e','l','l','n'});
        System.out.println("FITNESS:" + result);
        assertEquals(1, result);
    }
    
}
