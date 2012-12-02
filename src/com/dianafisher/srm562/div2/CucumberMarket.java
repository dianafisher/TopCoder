package com.dianafisher.srm562.div2;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: dianafisher
 * Date: 12/1/12
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class CucumberMarket extends TestCase
{
    /**
     * Returns "YES" if all combinations of k prices are less than or equal to the given budget value.
     * Returns "NO" if there exists a combination which is greater than the given budget value.
     * */
    public String check(int[] price, int budget, int k)
    {
        // Sort the prices from lowest to highest
        // Given n is 9 or less, so Java sort method should do
        Arrays.sort(price);
        // Print out the array for debug purposes
//        for( int p : price )
//        {
//            System.out.println("p = " + p);
//        }

        // Sum up the k highest values in the array (which will be at the end of the array).
        int sum = 0;
        int index = price.length - 1;
        for( int i = 0; i < k; i++ )
        {
            sum += price[index];
            index--;
        }
        if( sum <= budget )
            return "YES";
        else return "NO";
    }

    public void test()
    {
        // Test 0
        int[] prices = {1000, 1, 10, 100};
        int budget = 1110;
        int k = 3;
        assertEquals("YES", check(prices, budget, k));

        // Test 1
        budget = 1109;
        assertEquals("NO", check(prices, budget, k));

        // Test 2
        prices = new int[]{33, 4};
        budget = 33;
        k = 1;
        assertEquals("YES", check(prices, budget, k));

        // Test 3
        prices = new int[]{1,1,1,1,1,1};
        budget = 2;
        k = 4;
        assertEquals("NO", check(prices, budget, k));

        // Test 4
        prices = new int[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000};
        budget = 10000;
        k = 9;
        assertEquals("YES", check(prices, budget, k));

    }
}
