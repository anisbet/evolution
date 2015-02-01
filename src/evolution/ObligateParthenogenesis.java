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
 * Obligate parthenogenesis is the process in which organisms exclusively 
 * reproduce through asexual means.[26] Many species have been shown to 
 * transition to obligate parthenogenesis over evolutionary time. Among these 
 * species, one of the most well documented transitions to obligate parthenogenesis 
 * was found in almost all metazoan taxa, albeit through highly diverse mechanisms. 
 * These transitions often occur as a result of inbreeding or mutation within large 
 * populations.[27] There are a number of documented species, specifically 
 * salamanders and geckos, that rely on obligate parthenogenesis as their major 
 * method of reproduction. As such, there are over 80 species of unisex reptiles, 
 * mostly lizards but including a single snake species, amphibians and fishes in 
 * nature for which males are no longer a part of the reproductive process. 
 * A female will produce an ovum with a full set (two sets of genes) provided 
 * solely by the mother. Thus, a male is not needed to provide sperm to fertilize 
 * the egg. This form of asexual reproduction is thought in some cases to be a 
 * serious threat to biodiversity for the subsequent lack of gene variation and 
 * potentially decreased fitness of the offspring.
 * Reference: http://en.wikipedia.org/wiki/Parthenogenesis (Jan. 24, 2015).
 * 
 * @author Andrew Nisbet
 */
public class ObligateParthenogenesis implements RecombinationStrategy
{

    @Override
    public Individual[] reproduce(Individual[] mates)
    {
        Individual[] babies = new Individual[mates.length];
        for (int i = 0; i < mates.length; i++)
        {
            mates[i].mutate(2);
            babies[i] = mates[i];
        }
        return babies;
    }

    @Override
    public boolean foundMates(Individual[] mates)
    {
        return mates.length == 1;
    }
}
