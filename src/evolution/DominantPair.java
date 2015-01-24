/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Andrew Nisbet
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package evolution;

/**
 * In the dominant pair method two elite individuals replace the population with
 * their offspring.
 * @author Andrew Nisbet
 */
class DominantPair implements MateSelectionStrategy
{

    @Override
    public Individual[] findMates(Population population, Individual individual)
    {
        // In this strategy the elite individual mates with the next lowest
        // ranked individual. Each member gets a partner. In an adaptation this
        // could be weighted so the elite individual might mate with the top 2,3,
        // 4 or more other individuals in the population.
        // Find individual(s) that have same or higher rank than me.
        Individual[] mates = new Individual[1];
        for (int i = 0; i < population.size(); i++)
        {
            // Find a mate that has as close to your ranking (or higher).
            if (population.get(i).equals(individual)) continue; // don't mate with yourself (ew).
            if (population.get(i) == null || population.get(i).isSelected()) continue; // can't select a dead partner. We are monogomous.
            if (population.get(i).getRank() < individual.getRank()) continue; // this candidate is out of your league.
            // So now we are left with candidates that are not myself and are 
            // not of lower ranking than me so now I need to find the best candidate
            // out of the remainder.
            if (mates[0] == null 
                || (mates[0].getRank() > population.get(i).getRank())) mates[0] = population.get(i);
        }
        if (mates[0] != null)
        {
            mates[0].setSelected();
        }
        return mates;
    }
    
}
