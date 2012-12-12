package com.dianafisher.srm558.div1;

import junit.framework.TestCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: dianafisher
 * Date: 12/9/12
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stamp extends TestCase
{
    public int getMinimumCost(String desiredColor, int stampCost, int pushCost)
    {
        System.out.println("desiredColor = " + desiredColor);
        int maxL = maxLength(desiredColor);
        System.out.println("maxL = " + maxL);
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
            if (length < result) result = length;
            System.out.println("blue length = " + length);

        }
        if (!blueFound){
            System.out.println("No blue found");
        }
        return result;
    }

    public void test()
    {
        getMinimumCost("RRGGBB", 1, 1);
        getMinimumCost("R**GB*", 1, 1);
        getMinimumCost("BRRB", 2, 7);
        getMinimumCost("R*RR*GG", 10, 58);
        getMinimumCost("*B**B**B*BB*G*BBB**B**B*", 5, 2);
        getMinimumCost("*R*RG*G*GR*RGG*G*GGR***RR*GG", 7, 1);
//        assertEquals(204, getMinimumCost("R*RR*GG", 10, 58));
//        assertEquals(30, getMinimumCost("*R*RG*G*GR*RGG*G*GGR***RR*GG", 7, 1));
    }

}
