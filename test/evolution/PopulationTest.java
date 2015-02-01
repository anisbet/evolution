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
public class PopulationTest
{
    
    public PopulationTest()
    {
    }

    /**
     * Test of testFitness method, of class Population.
     */
    @Test
    public void testTestFitness()
    {
        System.out.println("==testFitness==");
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Target target = new Target("this", alphabet);
        Population population = new Population();
        population.add(new Individual(4, alphabet));
        population.add(new Individual("this")); // Force an individual to have a fitness of 0.
        population.add(new Individual(4, "this"));
        population.testFitness(target);
        population.view();
        assertTrue(population.isOptimal());
    }

    /**
     * Test of isOptimal method, of class Population.
     */
    @Test
    public void testIsOptimal()
    {
        System.out.println("==isOptimal==");
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Target target = new Target("this", alphabet);
        Population population = new Population();
        population.add(new Individual(4, alphabet));
        population.add(new Individual(4, "this"));
        population.testFitness(target);
//        population.view();
        assertFalse(population.isOptimal());
        population.add(new Individual("this")); // Force an individual to have a fitness of 0.
        assertTrue(population.isOptimal());
    }

    /**
     * Test of getIterator method, of class Population.
     */
    @Test
    public void testGetIterator()
    {
        System.out.println("==getIterator==");
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Target target = new Target("this", alphabet);
        Population population = new Population();
        population.add(new Individual(4, alphabet));
        population.add(new Individual("this")); // Force an individual to have a fitness of 0.
        population.add(new Individual(4, "this"));
        population.testFitness(target);
        population.view();
        for (Individual i : population.getIterator())
        {
            System.out.println("INDIVIDUAL: '" + i + "'");
        }
    }

    /**
     * Test of getEliteIndividual method, of class Population.
     */
    @Test
    public void testGetEliteIndividual()
    {
        System.out.println("==getEliteIndividual==");
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Target target = new Target("this", alphabet);
        Population population = new Population();
        population.add(new Individual(4, alphabet));
        population.add(new Individual("this")); // Force an individual to have a fitness of 0.
        population.add(new Individual(4, "this"));
        population.testFitness(target);
        population.view();
        assertTrue(population.isOptimal());
        Individual best = population.getEliteIndividual();
        System.out.println("BEST: '" + best + "'");
    }
}
