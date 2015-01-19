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
public class CrossOverTest
{
    
    public CrossOverTest()
    {
    }

    /**
     * Test of reproduce method, of class CrossOver.
     */
    @Test
    public void testReproduce()
    {
        System.out.println("== reproduce ==");
        Individual[] mates = new Individual[2];
        mates[0] = new Individual(10);
        mates[1] = new Individual(10);
        CrossOver instance = new CrossOver();
        Individual offSpring = instance.reproduce(mates);
        System.out.println("  Dad:'"+mates[0].toString()+"'");
        System.out.println("  Mom:'"+mates[1].toString()+"'");
        System.out.println("Child:'"+offSpring.toString()+"'");
    }
    
}
