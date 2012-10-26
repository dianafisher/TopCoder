package com.dianafisher.srm421.div2;

import junit.framework.TestCase;

import java.util.Vector;

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

        Vector<Double> result = new Vector<Double>();
        for( int i = 0; i < resultSize; i++ )
        {
            double lo = x[i];
            double hi = x[i+1];
            for( int iteration = 0; iteration < 500; iteration++ )
            {
                System.out.println(String.format("lo at index %d, hi at index %d", i, (i+1) ));
                System.out.println(String.format("lo = %f, hi = %f", lo, hi));

                double mid = (lo + hi)/2;
                System.out.println("mid = " + mid);
                double F = 0;
                for( int j = 0; j < i+1; j++ )
                {
//                    System.out.println(String.format("comparing %f with %f", m[j], mid));
                    F -= getForceOf(m[j], mid - x[j]);
                }
                for( int j = i+1; j < x.length; j++ )
                {
                    F += getForceOf(m[j], x[j] - mid);
                }
                if( F < 0 ) lo = mid;
                else hi = mid;
                System.out.println("F = " + F);
            }
            result.add( ( lo + hi ) / 2);
        }
        double[] r = new double[result.size()];
        int index = 0;
        for( Double d : result )
        {
            r[index] = d;
            index++;
        }
        return r;
    }

    private double getForceOf( double m1, double distance )
    {
        return m1/(distance * distance);
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
        test(x, m, expected);

        x = new int[]{2, 3, 5, 7};
        m = new int[]{3, 2, 7, 5};
        expected = new double[]{2.532859446114924, 3.7271944335152813, 6.099953640852538 };
//        test(x, m, expected);

    }

    private void test(int[] x, int[] m, double[] expected)
    {
        double[] calculated = getPoints(x,m);
        assertEquals( expected.length, calculated.length );

        for( int i = 0; i < calculated.length; i++)
        {
            assertEquals(expected[i], calculated[i], 0.001);
        }
    }
}
