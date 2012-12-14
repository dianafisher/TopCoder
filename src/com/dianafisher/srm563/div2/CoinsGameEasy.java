package com.dianafisher.srm563.div2;

import junit.framework.TestCase;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: dianafisher
 * Date: 12/8/12
 * Time: 9:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class CoinsGameEasy extends TestCase
{
    int n;
    int m;
    String[] board;
    int result;

    class Coin {
        int x;
        int y;

        public Coin(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(x).append(",").append(y);
            return builder.toString();
        }

    }

    // goal is to make exactly one coin fall off the board
    public int minimalSteps(String[] board)
    {
        this.board = board;
        result = 11;
        n = board.length;
        m = board[0].length();
        int count = 0;

        // arrays to hold x and y value of each of the two coins
        Coin[] coins = new Coin[2];

        // figure out where the coins are in the board
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'o') {
                    // found a coin
                    coins[count] = new Coin(i, j);
                    count++;
                }
            }
        }
        // print where the coins are in the board
        for (int i = 0; i < 2; i++) {
            System.out.println(coins[i]);
        }

        // move coins around board
        move(coins[0].x, coins[0].y, coins[1].x, coins[1].y, 0);
        System.out.println("result = " + result);
        if (result == 11) return -1;
        return result;
    }

    private int[] deltaX = {1, 0, -1, 0}; // right, down, left, up
    private int[] deltaY = {0, 1, 0, -1};
    private final static int NUM_DIRECTIONS = 4;

    private void move(int x1, int y1, int x2, int y2, int numMoves) {
//        System.out.println("coin 1: " + x1 + "," + y1);
//        System.out.println("coin 2: " + x2 + "," + y2);
//        System.out.println();

        if (numMoves > 10) return;
        if (offBoard(x1, y1) && offBoard(x2, y2)) {
//            System.out.println("both coins fell off.");
            return;
        }
        if (offBoard(x1, y1) || offBoard(x2, y2)) {
//            System.out.println("one coin fell off");
//            System.out.println("numMoves = " + numMoves);
            result = Math.min(result, numMoves);
            return;
        }

        // check each direction
        for (int i = 0; i < NUM_DIRECTIONS; i++) {
            int nx1, nx2, ny1, ny2;
            nx1 = x1 + deltaX[i];
            ny1 = y1 + deltaY[i];
            if (!offBoard(nx1, ny1) && board[nx1].charAt(ny1) == '#') {
                nx1 = x1;
                ny1 = y1;
            }
            nx2 = x2 + deltaX[i];
            ny2 = y2 + deltaY[i];
            if (!offBoard(nx2, ny2) && board[nx2].charAt(ny2) == '#') {
                nx2 = x2;
                ny2 = y2;
            }

            move(nx1, ny1, nx2, ny2, numMoves+1);

        }
    }

    private boolean offBoard(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    private boolean isObstacle(int x, int y) {
        return board[x].charAt(y) == '#';
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
//
//        String[] board6 = new String[]{
//          "############",
//          ".........#o#",
//          "############",
//          "..........o#",
//          "############",
//        };
//        assertEquals(-1, minimalSteps(board6));  // need at least 11 steps to win (too many)
    }

}
