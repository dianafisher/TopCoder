package com.dianafisher.utilities;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: dianafisher
 * Date: 12/1/12
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utilities extends TestCase {

    private static Random random;    // pseudo-random number generator
    private static long seed;        // pseudo-random number generator seed

    // static initializer
    static {
        // this is how the seed was set in Java 1.4
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }


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
//        System.out.println("---------");
//        System.out.println("incoming list = " + list);
//        System.out.println("incoming prefix = " + prefix);
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

//            System.out.println("prefix = " + prefix);
//            System.out.println("prefix2 = " + prefix2);
//            System.out.println("subList = " + subList);
//            System.out.println("k = " + k);

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

    // Linear time algorithm to find the top k elements in an array.
    public static Comparable[] top_k(Comparable[] a, int k)
    {
        if (k < 0 || k >= a.length) {
            throw new RuntimeException("Selected element out of bounds");
        }
        shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if      (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a;
        }
        return a;
    }

    // partition the subarray a[lo .. hi] by returning an index j
    // so that a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {

            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;

            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put v = a[j] into position
        exch(a, lo, j);

        // with a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    /***********************************************************************
     *  Helper sorting functions
     ***********************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * Return an integer uniformly between 0 (inclusive) and N (exclusive).
     */
    public static int uniform(int N) {
        return random.nextInt(N);
    }

    /**
     * Rearrange the elements of an array in random order.
     */
    public static void shuffle(Object[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = i + uniform(N-i);     // between i and N-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public void test()
    {
//        assertEquals(120, factorial_iterative(5));
//        assertEquals(120, factorial(5));
//        assertEquals(6, getNumCombinations(4, 2));
//        System.out.println("getNumCombinationsWithReplacement(4,2) = " + getNumCombinationsWithReplacement(4, 2));
        int[] A = {1,2,3,4};
        combinationsChooseK(A, 2);

        Integer[] aList = new Integer[]{31, 45, 91, 51, 66, 82, 28, 33, 11, 83, 84, 27, 36};

        Integer[] result = (Integer[])top_k(aList, aList.length - 5);
        for (int i : result)
            System.out.println(i);
//        System.out.println();
//        combinationsChooseK(A, 3);
//        System.out.println();
//        combinationsChooseK(A, 4);
//        System.out.println();
//        combinationsChooseK(A, 1);
    }
}

