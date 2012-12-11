package com.dianafisher.srm558.div1;

import junit.framework.TestCase;

import java.util.ArrayList;

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
        int maxL = maximumL(desiredColor);
        System.out.println("maxL = " + maxL);
        return 0;
    }

    private int maximumL(String desiredColor)
    {
        int count = 0;
        ArrayList<Integer> redCounts = new ArrayList<Integer>();
        ArrayList<Integer> greenCounts = new ArrayList<Integer>();
        ArrayList<Integer> blueCounts = new ArrayList<Integer>();

        int idx = 0;

        // move idx past any wildcards
        while (idx < desiredColor.length() && desiredColor.charAt(idx) == '*') {
            idx++;
            count++;
        }
//        System.out.println("first index not * = " + idx);
//        System.out.println("count = " + count);
        // check what character we have now
        if (idx < desiredColor.length())
        {
            char current = desiredColor.charAt(idx);
            while (idx < desiredColor.length())
            {
                System.out.println("-----");
                System.out.println("idx = " + idx);
                System.out.println("current = " + current);
                System.out.println("count = " + count);
                char next = desiredColor.charAt(idx);
                System.out.println("next = " + next);
                if (next == current || next == '*')
                    count++;
                else
                {
                    switch(current)
                    {
                        case 'R':
                            redCounts.add(count);
                            break;
                        case 'G':
                            greenCounts.add(count);
                            break;
                        case 'B':
                            blueCounts.add(count);
                            break;
                        default: break;
                    }
                    count = 1;
                    current = next;
                }
                idx++;
            }
        }

        // find the smallest count
        int min = 50;

        System.out.println("red counts:");
        for (int c : redCounts)
        {
            System.out.print(c + ", ");
            if (c < min) min = c;
        }
        System.out.println();
        System.out.println("green counts:");
        for (int c : greenCounts)
        {
            System.out.print(c + ", ");
            if (c < min) min = c;
        }
        System.out.println();
        System.out.println("blue counts:");
        for (int c : blueCounts)
        {
            System.out.print(c + ", ");
            if (c < min) min = c;
        }
        System.out.println();

//        System.out.println("highest L:" + min);
        return min;
    }

    public void test()
    {
//        getMinimumCost("*R*RG*G*GR*RGG*G*GGR***RR*GG", 7, 1);
        getMinimumCost("R*RR*GG", 10, 58);
//        assertEquals(204, getMinimumCost("R*RR*GG", 10, 58));
//        assertEquals(30, getMinimumCost("*R*RG*G*GR*RGG*G*GGR***RR*GG", 7, 1));
    }

}
