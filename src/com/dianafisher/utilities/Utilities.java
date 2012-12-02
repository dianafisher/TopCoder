package com.dianafisher.utilities;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: dianafisher
 * Date: 12/1/12
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utilities extends TestCase {

    // n choose k
    // the number of k-sized subsets which exist in the set n
    // num combinations = n! / k! * (n-k)!
    // Order does not matter for combinations.
    // No replacement of items.
    public static int getNumCombinations(int n, int k)
    {
        int numerator = factorial(n);
        int denominator = factorial(k) * factorial(n-k);
        return numerator / denominator;
    }

    public static void combinationsChooseK(int[] list, int k)
    {
        int n = list.length;
        if (k > n) return;

        // put everything in an array list
        ArrayList a = new ArrayList();
        for (int i : list)
        {
            a.add(i);
        }

        combinations(a, new ArrayList() , k);

    }

    private static void combinations(ArrayList list, ArrayList prefix, int k)
    {
        if (list.size() < k) return;
        else if (k == 0) System.out.println(prefix);
        else
        {
            ArrayList prefix2 = new ArrayList();
            for (int i = 0; i < prefix.size(); i++)
                prefix2.add(prefix.get(i));

            prefix2.add(list.get(0));
            ArrayList subList = new ArrayList();
            for (int i = 1; i < list.size(); i++)
                subList.add(list.get(i));

            combinations(subList, prefix2, k-1);
            combinations(subList, prefix, k);
        }
    }

    // How many combinations with replacement?
    // (n + k -1)! / k! * (n-1)!
    public static int getNumCombinationsWithReplacement(int n, int k)
    {
        int top = n + k - 1;
        int numerator = factorial(top);
        int denominator = factorial(k) * factorial(n-1);
        return numerator / denominator;
    }

    // Order matters for permutations.  A permutation is an ordered combination
    // The number of permutations of n items (without replacement) is n!
    // The number of permutations of n items WITH replacement is n^k (choosing k items).

    // Recursively calculates the factorial of n
    // factorial(5) = 5 * 4 * 3 * 2 * 1 = 120
    public static int factorial(int n)
    {
        if (n == 0) return 1;
        return (factorial(n-1) * n);
    }

    // Iteratively calculates the factorial of n
    public static int factorial_iterative(int n)
    {
        int result = 1;
        for (int i = 2; i < n+1; i++)
        {
            result *= i;
        }
        return result;
    }

    public void test()
    {
//        assertEquals(120, factorial_iterative(5));
//        assertEquals(120, factorial(5));
//        assertEquals(6, getNumCombinations(4, 2));
//        System.out.println("getNumCombinationsWithReplacement(4,2) = " + getNumCombinationsWithReplacement(4, 2));
        int[] A = {1,2,3,4};
        combinationsChooseK(A, 2);
        System.out.println();
        combinationsChooseK(A, 3);
        System.out.println();
        combinationsChooseK(A, 4);
        System.out.println();
        combinationsChooseK(A, 1);
    }
}

