package com.dianafisher.srm563.div2;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: dianafisher
 * Date: 12/8/12
 * Time: 9:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class FoxAndHandleEasy extends TestCase {

    public String isPossible_Mine(String S, String T)
    {
        // return "YES" if T can be obtained by expanding S exactly once.
        int index = T.indexOf(S);
        if (index == -1) return "No";

        ArrayList<String> expansions = new ArrayList<String>();
        for (int i = 0; i < S.length()-1; i++)
        {
            String prefix = S.substring(0, i+1);

            String suffix = S.substring(i+1);

            String str = prefix + S + suffix;
            expansions.add(str);
        }
        expansions.add(S+S);
        if( expansions.contains(T) ) return "Yes";
        return "No";
    }

    public String isPossible_Regex(String S, String T)
    {
        if( T.contains(S) && T.replaceFirst(S, "").equals(S))
        {
            return "Yes";
        }
        return "No";
    }

    public String isPossible(String S, String T) {
        for (int i = 0; i < S.length(); i++) {
            String V = S.substring(0, i) + S + S.substring(i);
            if (V.equals(T)) return "Yes";
        }
        return "No";
    }


    public void test()
    {
        assertEquals("Yes", isPossible("Ciel", "CieCiell"));
        assertEquals("No", isPossible("Ciel", "FoxCiel"));
        assertEquals("Yes", isPossible("FoxCiel", "FoxFoxCielCiel"));
        assertEquals("No", isPossible("FoxCiel", "FoxCielCielFox"));
        assertEquals("No", isPossible("Ha", "HaHaHaHa"));
        assertEquals("No", isPossible("TheHandleCanBeVeryLong", "TheHandleCanBeVeryLong"));
        assertEquals("Yes", isPossible("Long", "LongLong"));
    }

}
