package com.dianafisher.srm421.div2;

import junit.framework.TestCase;

/**
 * Problem Statement
     
 You are at the gym and you want to do some training. The training process is divided into one-minute segments.
 During each minute, you can either train or rest.
 If you choose to train during a minute, it increases your pulse by trainChange.
 That is, if your pulse was X, it becomes X + trainChange after a minute of training.
 You never want your pulse to exceed maxPulse, so you can train only if X + trainChange is less than or equal to maxPulse.
 If you choose to rest during a minute, it decreases your pulse by restChange.
 That is, if your pulse was X, it becomes X - restChange after a minute of rest.
 However, your pulse never falls below minPulse, so if X - restChange is less than minPulse, your pulse becomes minPulse instead of X - restChange.
 Your pulse is initially minPulse. You want to train for a total of needToTrain minutes (these minutes don't need to be consecutive).

 Return the minimum number of minutes your complete training process will take.
 If you can't train for needToTrain minutes, return -1 instead.

 Constraints
 -
 minPulse will be between 50 and 200, inclusive.
 -
 maxPulse will be between minPulse and 200, inclusive.
 -
 needToTrain, trainChange and restChange will each be between 1 and 200, inclusive.

 * */

public class GymTraining extends TestCase
{
    /***
     * returns the minimum number of minutes the training process will take
     *
     */
    public int trainingTime(int needToTrain, int minPulse, int maxPulse, int trainChange, int restChange)
    {
        if( minPulse + trainChange > maxPulse) return -1;

        int pulse = minPulse;
        int totalMinutes = 0;
        while( needToTrain > 0 )
        {
            totalMinutes++;
            if( pulse + trainChange <= maxPulse )
            {
                pulse += trainChange;
                needToTrain--;
            }
            else
            {
                pulse = Math.max( minPulse, pulse - restChange );
            }
        }
        return totalMinutes;
    }

    public void test()
    {
        assertEquals(10, trainingTime(5, 70, 120, 25, 15));
        assertEquals(109, trainingTime(100, 50, 100, 5, 200));
        assertEquals(-1, trainingTime(1, 60, 70, 11, 11));
        assertEquals(30050, trainingTime(200, 50, 200, 150, 1));
        assertEquals(40, trainingTime(19, 89, 143, 17, 13));
    }

    // original
    public int trainingTimeOrg(int needToTrain, int minPulse, int maxPulse, int trainChange, int restChange)
    {
        // see if we can train for even one minute.  if no, return -1
        if( minPulse + trainChange > maxPulse) return -1;

        // how long can we train before maxing out our pulse?
        int pulse = minPulse;
        int totalMinutes = 0;
        int trainingMinutes = 0;
        while( trainingMinutes < needToTrain )
        {
            while( (pulse + trainChange) <= maxPulse )
            {
                pulse += trainChange;
                trainingMinutes++;
                totalMinutes++;
            }
            // how long do we need to rest before we can start training again?
            if( trainingMinutes == needToTrain ) return totalMinutes;

            while( (pulse + trainChange) > maxPulse )
            {
                pulse -= restChange;
                totalMinutes++;
                if( pulse < minPulse )
                {
                    pulse = minPulse;
                    break;
                }
            }
        }
        return totalMinutes;
    }

}
