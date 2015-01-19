/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package evolution;

/**
 * Evolution is a simulator of how software can represent evolve solutions to 
 * a simple problem: Create the string 'Hello World!".
 * @author andrew Nisbet
 */
public class Evolution
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // 
        String inputString = "Hello World!";
        Fitness fitnessTest = new Fitness(inputString);
        Population p = new Population(20, inputString.length(), fitnessTest);
        System.out.println(p.toString());
        Individual individual = p.get(0);
        System.out.println(">>" + individual.toString());
        for (int i = 0; i < 5; i++)
        {
            System.out.println(">>" + individual.mutate(1));
        }
    }
    
}
