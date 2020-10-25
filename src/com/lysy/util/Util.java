package com.lysy.util;

import com.lysy.Main;

import java.util.LinkedList;
import java.util.List;

public class Util {
    public static List<Roller> firstPole = new LinkedList<>();
    public static List<Roller> secondPole = new LinkedList<>();
    public static List<Roller> thirdPole = new LinkedList<>();
    public static int numberOfMoves = 0;

    public static boolean isNotEnd() {
        return (thirdPole.size() == Main.numberOfRollers) ? false : true;
    }

    public static void countMoves() {
        numberOfMoves++;
    }

    public static void draw() {
        String gap = new String();
        for (int i = Main.numberOfRollers; i > 0; i--) {
            System.out.print(String.valueOf(Math.abs(i - Main.numberOfRollers) + 1) + ".  ");
            gap = firstPole.size() >= i ? String.valueOf(firstPole.get(i - 1).getWeight()) + " " : "  ";
            System.out.print(gap);
            System.out.print("  ");
            gap = secondPole.size() >= i ? String.valueOf(secondPole.get(i - 1).getWeight()) + " " : "  ";
            System.out.print(gap);
            System.out.print("  ");
            gap = thirdPole.size() >= i ? String.valueOf(thirdPole.get(i - 1).getWeight()) + " ": "  ";
            System.out.print(gap);
            System.out.print("  ");
            System.out.println();
        }
        System.out.println("================");
        System.out.println("    1   2   3 \n \n \n");
    }

    public static void initPoles() {
        for (int i = Main.numberOfRollers; i > 0; i--) {
            firstPole.add(new Roller(i));
        }
    }


    public static void move() {
        if (Main.numberOfRollers % 2 == 0) {
            switch (numberOfMoves % 3) {
                case 1:
                    setMove(firstPole, secondPole);
                    break;
                case 2:
                    setMove(firstPole, thirdPole);
                    break;
                case 0:
                    setMove(secondPole, thirdPole);
                    break;
            }
        } else {
            switch (numberOfMoves % 3) {
                case 1:
                    setMove(firstPole, thirdPole);
                    break;
                case 2:
                    setMove(firstPole, secondPole);
                    break;
                case 0:
                    setMove(secondPole, thirdPole);
                    break;
            }
        }
    }

    public static void setMove(List<Roller> firstPole, List<Roller> secondPole) {
        Roller roller1 = (firstPole.size() - 1 < 0) ? null : firstPole.get(firstPole.size() - 1);
        Roller roller2 = (secondPole.size() - 1 < 0) ? null : secondPole.get(secondPole.size() - 1);

        long x = roller1 != null ? roller1.getWeight() : Long.MAX_VALUE;
        long y = roller2 != null ? roller2.getWeight() : Long.MAX_VALUE;

        if (x > y) {
            firstPole.add(new Roller(y));
            if (!secondPole.isEmpty()) secondPole.remove(roller2);
        } else {
            secondPole.add(new Roller(x));
            if (!firstPole.isEmpty()) firstPole.remove(roller1);
        }
    }
}
