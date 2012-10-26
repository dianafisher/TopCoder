package com.dianafisher.srm421.div2;

import junit.framework.TestCase;

/**
 *  Problem Statement
     
 There are N points situated on a straight line. The i-th point is located at coordinate x[i] and has a mass of m[i].
 The location of each point is strongly fixed and cannot be changed by any forces. Coordinates of all points are distinct.
 When another point P is added on the line and its position is not fixed, the point falls under the impact of gravitational forces from each of the given N points.
 Points located to the left of P force it to the left, and points located to the right of P force it to the right.
 When two points are located a distance of d apart and have masses m1 and m2,
 the value of gravitational force between them is F = G * m1 * m2 / d^2, where G is some positive constant.
 Point P is said to be an equilibrium point if the vector sum of gravitational forces from all points on P equals zero.
 In other words, the sum of the values of gravitational forces between P and all the points located to the left of P must
 be the same as the sum of the values of gravitational forces between P and all the points located to the right of P.
 It is known that there always exist exactly N-1 equilibrium points. Return a double[] containing their coordinates sorted in ascending order.
 *
 * */
public class EquilibriumPoints extends TestCase
{
    public double[] getPoints(int[] x, int[] m)
    {
        int resultSize = x.length - 1;
//        System.out.println("resultSize = " + resultSize);
        double[] result = new double[resultSize];
        for( int i = 0; i < resultSize; i++ )
        {
            int m0 = m[i];
            int m1 = m[i+1];
            int x0 = x[i];
            int x1 = x[i+1];

            double P = ((( (Math.sqrt(m0) * x1) / Math.sqrt(m1) ) + x0 ) / (1 + (Math.sqrt(m0) / Math.sqrt( m1 ) )));
            result[i] = P;
        }
        return result;
    }

    public void test()
    {
        int[] x = {1, 2};
        int[] m = {1, 1};
        double[] expected = {1.5};
//        test(x, m, expected);

        m = new int[]{1, 1000};
        expected = new double[]{1.0306534300317156};
//        test(x, m, expected);

        x = new int[]{1, 2, 3};
        m = new int[]{1, 2, 1};
        expected = new double[] {1.4060952084922507, 2.5939047915077493 };
//        test(x, m, expected);

        x = new int[]{2, 3, 5, 7};
        m = new int[]{3, 2, 7, 5};
        expected = new double[]{2.532859446114924, 3.7271944335152813, 6.099953640852538 };
        test(x, m, expected);

    }

    private void test(int[] x, int[] m, double[] expected)
    {
        double[] calculated = getPoints(x,m);
        System.out.println("calculated.length = " + calculated.length);
        System.out.println("expected.length = " + expected.length);
        for( int i = 0; i < calculated.length; i++)
        {
            assertEquals(expected[i], calculated[i], 0.001);
        }
    }
}
