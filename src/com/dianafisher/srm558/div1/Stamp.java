package com.dianafisher.srm558.div1;

import junit.framework.TestCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: dianafisher
 * Date: 12/9/12
 * Time: 9:42 PM
 */
public class Stamp extends TestCase
{
    public int getMinimumCost(String desiredColor, int stampCost, int pushCost)
    {
        System.out.println("desiredColor = " + desiredColor);
        int L = maxLength(desiredColor);
        System.out.println("L = " + L);

        // initialize character array
        char[] stampedChars = new char[desiredColor.length()];
        for (int i = 0; i < desiredColor.length(); i++) {
            stampedChars[i] = ' ';
        }

        // use dynamic programming!
        int N = desiredColor.length();
        System.out.println("N = " + N);
        int[] cost = new int[N];
        for (int i = 0; i < N; i++) {
            cost[i] = 0;
        }
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = 0;
        }
        // convert characters to integers
        for (int i = 0; i < N; i++) {
            char c = desiredColor.charAt(i);
            if (c == '*') a[i] = 7;
            if (c == 'R') a[i] = 1;
            if (c == 'G') a[i] = 2;
            if (c == 'B') a[i] = 4;
        }
        int res = Integer.MAX_VALUE;

        // try all possibilities
        for (int len = 1; len <= N; len++) {
            cost[0] = 0;

            // calculate minimum cost
            for (int i = 1; i < N; i++) cost[i] = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                // for each position, attempt to paint
                int color = 7; // '*'
                for (int j = i; j < N; j++) {
                    color &= a[j];
//                    System.out.println("color = " + color);
                    if (color == 0) break;
                    int seg = j - i + 1;
                    if (seg <  len) continue;

                    System.out.println("j = " + j);
                    if (j < N-1)
                        cost[j+1] = Math.min(cost[j+1], cost[i] + ((seg + len - 1) / len) * pushCost);
                }
            }

            res = Math.min(res, cost[N-1] + stampCost * len);
        }
        System.out.println("res = " + res);
        System.out.println();

        return 0;
    }

    private int maxLength(String desiredColor)
    {
        Pattern redPattern = Pattern.compile("([*]*[R]+[*]*)+");
        Matcher matcher = redPattern.matcher(desiredColor);

        Pattern greenPattern = Pattern.compile("([*]*[G]+[*]*)+");
        Matcher greenMatcher = greenPattern.matcher(desiredColor);

        Pattern bluePattern = Pattern.compile("([*]*[B]+[*]*)+");
        Matcher blueMatcher = bluePattern.matcher(desiredColor);

        int result = Integer.MAX_VALUE;
        int stampCount = 0;
        boolean redFound = false;
        boolean greenFound = false;
        boolean blueFound = false;
        while( matcher.find()) {

            System.out.format("redMatcher found the text" +
                    " \"%s\" starting at " +
                    "index %d and ending at index %d.%n",
                    matcher.group(),
                    matcher.start(),
                    matcher.end());
            redFound = true;
            int length = matcher.end() - matcher.start();
//            int numStamps = length/3;
//            System.out.println("numStamps = " + numStamps);
//            stampCount += numStamps;
            if (length < result) result = length;
            System.out.println("red length = " + length);
        }
        if (!redFound){
            System.out.println("No red found");
        }

        while( greenMatcher.find()) {

            System.out.format("greenMatcher found the text" +
                    " \"%s\" starting at " +
                    "index %d and ending at index %d.%n",
                    greenMatcher.group(),
                    greenMatcher.start(),
                    greenMatcher.end());
            greenFound = true;
            int length = greenMatcher.end() - greenMatcher.start();
//            int numStamps = length/3;
//            System.out.println("numStamps = " + numStamps);
//            stampCount += numStamps;

            if (length < result) result = length;
            System.out.println("green length = " + length);

        }
        if (!greenFound){
            System.out.println("No green found");
        }

        while(blueMatcher.find()) {

            System.out.format("blueMatcher found the text" +
                    " \"%s\" starting at " +
                    "index %d and ending at index %d.%n",
                    blueMatcher.group(),
                    blueMatcher.start(),
                    blueMatcher.end());
            blueFound = true;
            int length = blueMatcher.end() - blueMatcher.start();
//            int numStamps = length/3;
//            System.out.println("numStamps = " + numStamps);
//            stampCount += numStamps;

            if (length < result) result = length;
            System.out.println("blue length = " + length);

        }
        if (!blueFound){
            System.out.println("No blue found");
        }
//        System.out.println("stampCount = " + stampCount);
//        int remaining = desiredColor.length() - stampCount*3;
//        int value = remaining/3 + remaining % 3;
//        System.out.println("desiredColor length = " + desiredColor.length());
//        System.out.println("remaining = " + remaining);
//        System.out.println("value = " + value);
        return result;
    }

    public void test()
    {
//        getMinimumCost("*****B", 1, 1);
//        getMinimumCost("RRGGBB", 1, 1);
//        getMinimumCost("R**GB*", 1, 1);
//        getMinimumCost("BRRB", 2, 7);
//        getMinimumCost("R*RR*GG", 10, 58);
//        getMinimumCost("*B**B**B*BB*G*BBB**B**B*", 5, 2);
        getMinimumCost("*R*RG*G*GR*RGG*G*GGR***RR*GG", 7, 1);
//        assertEquals(204, getMinimumCost("R*RR*GG", 10, 58));
//        assertEquals(30, getMinimumCost("*R*RG*G*GR*RGG*G*GGR***RR*GG", 7, 1));
    }

}
