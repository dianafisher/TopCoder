package com.dianafisher.srm421.div2;

import junit.framework.TestCase;

/**
 * Problem Statement
     
 Imagine a skyscraper with 10^N floors, numbered 0 to 10^N-1. The floor indicator in the elevator shows the floor number using exactly N digits,
 padding it with leading zeroes if necessary. Each digit is shown as a 5x3 field of small lamps, some of which are lit and some of which are unlit.
 Here is a representation of all the digits from 0 to 9 ('#' represents lit lamps and '.' represents unlit lamps) :
 ###...#.###.###.#.#.###.###.###.###.###
 #.#...#...#...#.#.#.#...#.....#.#.#.#.#
 #.#...#.###.###.###.###.###...#.###.###
 #.#...#.#.....#...#...#.#.#...#.#.#...#
 ###...#.###.###...#.###.###...#.###.###
  Here, as in the actual floor indicator, consecutive digits are separated by a single column of unlit lamps.
 Some of the lamps in the floor indicator are malfunctioning and always remain unlit.
 Imagine that you are stuck in this elevator and you want to know the current floor number.
 You decide to calculate it as the average value of all floor numbers that could possibly be represented
 by the current state of the indicator, assuming that any number of the unlit lamps might be malfunctioning.
 You are given the state of the indicator as a String[] where each element represents a row of lamps, and rows
 are given from top to bottom. Return the average that you calculate, or -1 if no valid floor number could possibly be represented by the indicator.
 *
 *
 * */
public class FloorIndicator extends TestCase
{
    public double averageFloor(int N, String[] indicator)
    {
        return 0;
    }

    public void test()
    {
        // test 0
        int N = 1;
        String[] indicator = {  "###",
                                "#.#",
                                "###",
                                "#.#",
                                "###"};

        assertEquals(8.0, averageFloor(N, indicator), 1E-9);

        // test 1
        N = 2;
        indicator = new String[]{"###.###",
                                 "#.#.#.#",
                                 "#.#.###",
                                 "#.#...#",
                                 "###.###"};
        assertEquals(48.5, averageFloor(N, indicator), 1E-9);

        // test 2
        N = 2;
        indicator = new String[]{   ".......",
                                    ".......",
                                    ".......",
                                    ".......",
                                    "......."};
        assertEquals(49.5, averageFloor(N, indicator), 1E-9);

        // test 3
        N = 1;
        indicator = new String[] {   "...",
                                     ".#.",
                                     "...",
                                     "...",
                                     "..."};
        assertEquals(-1.0, averageFloor(N, indicator), 1E-9);

    }

}
