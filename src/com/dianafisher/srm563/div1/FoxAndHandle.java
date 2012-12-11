package com.dianafisher.srm563.div1;

import junit.framework.TestCase;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: dianafisher
 * Date: 12/9/12
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class FoxAndHandle extends TestCase{

    /**
     * Given string S, find the lexicographically smallest valid handle H.
     * S can be obtained by shuffling H and some permutation of H together.
     *
     * Shuffling: interleaving the letters of X and Y to obtain Z.  Cannot change order of X or Y.
     *
     * If S = 'ceiiclel', a possible handle is 'ciel'.  Permute 'ciel' to 'eicl' and shuffle it with 'ciel' to obtain 'ceiiclel
     * */
    public String lexSmallestName(String S)
    {
        System.out.println("S = " + S);
//        int[] characterCounts = new int[26];
//        for (int i = 0; i < S.length(); i++)
//        {
//            characterCounts[S.charAt(i) - 'a']++;
//        }
//
//        // print characterCounts array
//        for (int i : characterCounts)
//        {
//            System.out.print(i + " ");
//        }
//        System.out.println();

        // what does this code do?
        boolean[] p = new boolean[256];
        char[] letters = new char[S.length()/2];
        int count = 0;
        for (int i = 0; i < S.length(); i++)
        {
            if (!p[S.charAt(i)])
            {
                letters[count++] = S.charAt(i);
            }
            p[S.charAt(i)] = !p[S.charAt(i)];
        }
        Arrays.sort(letters);
        for (char c : letters)
        {
            System.out.print(c + " ");
        }
        System.out.println();
//
//        for (boolean b : p)
//        {
//            if (b) System.out.print("1");
//            else System.out.print("0");
//        }
//        System.out.println();

        int pos = 0;
        int N = S.length();
        Letter[] lets = new Letter[N/2];
        boolean[] used = new boolean[N];
        for (int i = 0; i < letters.length; i++)
        {
            int start = pos;
            while (pos < N && (used[pos] || S.charAt(pos) != letters[i]))
                pos++;

            if (pos == N)
            {
                pos = start;
                while (used[pos] || S.charAt(pos) != letters[i])
                    pos--;
            }
            lets[i] = new Letter(pos, letters[i]);
            used[pos] = true;
        }

        System.out.print("letters (before sorting): ");
        for (Letter l : lets)
            System.out.print(l + " ");
        System.out.println();

        Arrays.sort(lets);
        // print lets array
        System.out.print("letters (after sorting by position): ");
        for (Letter l : lets)
            System.out.print(l + " ");
        System.out.println();


        String result = "";
        for (int i = 0; i < N/2; i++)
        {
            result += lets[i].c;
        }
        System.out.println("result = " + result);
        System.out.println();
        return result;
    }

    class Letter implements Comparable<Letter>
    {
        int pos;
        char c;

        public Letter(int p, char ch)
        {
            this.pos = p;
            this.c = ch;
        }

        @Override
        public int compareTo(Letter letter) {
            return this.pos - letter.pos;
        }

        @Override
        public String toString() {
            return this.pos + "->" + this.c;
        }
    }



    public void test()
    {


        lexSmallestName("foxfox"); // -> fox, fxo, ofx, oxf, xfo.  Lexicographically smallest is fox

        lexSmallestName("ccieliel"); // returns ceil
        lexSmallestName("abaabbab"); // returns aabb
        lexSmallestName("bbbbaaaa"); // returns bbaa
        lexSmallestName("fedcbafedcba");  // afedcb
        lexSmallestName("nodevillivedon");  // deilvon

    }


}
