package com.dianafisher.srm563.div2;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: dianafisher
 * Date: 12/8/12
 * Time: 9:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class CoinsGameEasy extends TestCase
{
    // goal is to make exactly one coin fall off the board
    public int minimalSteps(String[] board)
    {
        // board dimensions?
        int h = board.length;
        if (h == 0) return -1;

        int moveCount = 0;
        int[] stepsPerDirection = new int[4]; // 0 (left), 1 (right), 2 (up), 3 (down)
        int w = board[0].length();
        System.out.println("w = " + w);
        System.out.println("h = " + h);
        // where are the coins?
        for (int i = 0; i < h; i++)
        {
            String row = board[i];
            for (int j = 0; j < w; j++)
            {
                char ch = row.charAt(j);
                if (ch == 'o')
                {
                    System.out.println("found coin at " + i + ", " + j);
                    // find how many steps to move coin off board
                    int left = j+1;
                    System.out.println("left steps = " + left);
                    int possibleLeft = 0;
                    for (int steps = j; steps > -1; steps--)
                    {
                        char next = row.charAt(steps);
                        System.out.println("next = " + next);
                        if (next == '#') break;
                        else possibleLeft+=1;
                    }
                    System.out.println("possibleLeft = " + possibleLeft);
                    System.out.println();
                    stepsPerDirection[0] = possibleLeft;
                    int right = w-j;
                    System.out.println("right steps = " + right);
                    int possibleRight = 0;
                    for (int steps = 0; steps < right; steps++)
                    {
                        char next = row.charAt(steps);
                        System.out.println("next = " + next);
                        if (next == '#') break;
                        else possibleRight+=1;
                    }
                    System.out.println("possibleRight = " + possibleRight);
                    stepsPerDirection[1] = possibleRight;
                    System.out.println();

                    int up = i+1;
                    System.out.println("up = " + up);
                    int possibleUp = 0;
                    for (int steps = i; steps > -1; steps--)
                    {
                        System.out.println("steps = " + steps);
                        String row_up = board[steps];
                        char next = row_up.charAt(j);
                        System.out.println("next = " + next);
                        if (next == '#') break;
                        else possibleUp+=1;
                    }
                    System.out.println("possibleUp = " + possibleUp);
                    stepsPerDirection[2] = possibleUp;
                    System.out.println();

                    int down = h-i;
                    System.out.println("down = " + down);
                    int possibleDown = 0;
                    for (int steps = 0; steps < right; steps++)
                    {
                        String row_down = board[steps];
                        char next = row_down.charAt(j);

                        System.out.println("next = " + next);
                        if (next == '#') break;
                        else possibleDown+=1;
                    }
                    System.out.println("possibleDown = " + possibleDown);
                    stepsPerDirection[3] = possibleDown;
                    System.out.println();

                    // find the direction with the smallest number of steps
                    int min = stepsPerDirection[0];
                    int direction = 0;
                    for (int n = 1; n < 4; n++)
                    {
                        if (stepsPerDirection[n] < min )
                        {
                            min = stepsPerDirection[n];
                            direction = n;
                        }
                    }
                    System.out.println("direction = " + direction);
                    System.out.println("min = " + min);

                    // move the coin and update the board
                    switch (direction)
                    {
                        case 0: //left

                            break;
                        case 1: // right
                            break;
                        case 2:  // up
                            break;
                        case 3: // down
                            break;
                    }
                }
            }
        }
        return -1;
    }

    public void test()
    {
        String[] board0 = {"oo"};
        assertEquals(1, minimalSteps(board0));

        String[] board1 = new String[]{
                ".#",
                ".#",
                ".#",
                "o#",
                "o#",
                "##",
        };
        assertEquals(4, minimalSteps(board1));

        String[] board5 = new String[]{
          "###########",
          "........#o#",
          "###########",
          ".........o#",
          "###########",
        };
        assertEquals(10, minimalSteps(board5));

        String[] board6 = new String[]{
          "############",
          ".........#o#",
          "############",
          "..........o#",
          "############",
        };
        assertEquals(-1, minimalSteps(board6));  // need at least 11 steps to win (too many)
    }

}
