package com.dianafisher.utilities;

import java.io.Console;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexTestHarness {

    public static void main(String[] args) {
//        String desired = "*R*RG*G*GR*RGG*G*GGR***RR*GG";
//        String desired = "R**GB*";
        String desired = "R*RR*GG";
//        String desired = "RRGGBB";

        Pattern redPattern = Pattern.compile("[R*]+[*R]+");
        Matcher matcher = redPattern.matcher(desired);

        Pattern greenPattern = Pattern.compile("[G*]+[*G]+");
        Matcher greenMatcher = greenPattern.matcher(desired);

//        Pattern bluePattern = Pattern.compile("[B*]+[B]");
        Pattern bluePattern = Pattern.compile("[B*]+[*B]+");
        Matcher blueMatcher = bluePattern.matcher(desired);

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
        }
        if (!blueFound){
            System.out.println("No blue found");
        }
    }


    public static void main2(String[] args){
        Console console = System.console();
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        while (true) {

            Pattern pattern =
                    Pattern.compile(console.readLine("%nEnter your regex: "));

            Matcher matcher =
                    pattern.matcher(console.readLine("Enter input string to search: "));

            boolean found = false;
            while (matcher.find()) {
                console.format("I found the text" +
                        " \"%s\" starting at " +
                        "index %d and ending at index %d.%n",
                        matcher.group(),
                        matcher.start(),
                        matcher.end());
                found = true;
            }
            if(!found){
                console.format("No match found.%n");
            }
        }
    }
}
