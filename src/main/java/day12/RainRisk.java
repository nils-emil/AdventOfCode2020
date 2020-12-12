package day12;


import javafx.util.Pair;
import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class RainRisk {

    public static int getManhattanDistance(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        List<Pair<String, Integer>> collect = lines.stream()
                .map(RainRisk::getDirection)
                .collect(Collectors.toList());
        int degrees = 0;
        int east = 10;
        int west = 0;
        int north = 1;
        int south = 0;
        for (Pair<String, Integer> command : collect) {
            if (command.getKey().equals("N")) {
                north += command.getValue();
            }
            if (command.getKey().equals("S")) {
                south += command.getValue();
            }
            if (command.getKey().equals("E")) {
                east += command.getValue();
            }
            if (command.getKey().equals("W")) {
                west += command.getValue();
            }
            if (command.getKey().equals("R")) {
                degrees -= command.getValue();
            }
            if (command.getKey().equals("L")) {
                degrees += command.getValue();
            }
            if (command.getKey().equals("F")) {
                if (degrees % 360 == 0) {
                    north += command.getValue();
                }
                if (degrees % 360 == 90 || degrees % 360 == -270) {
                    east += command.getValue();
                }
                if (degrees % 360 == 180 || degrees % 360 == -180) {
                    south += command.getValue();
                }
                if (degrees % 360 == 270 || degrees % 360 == -90) {
                    west += command.getValue();
                }
            }
            int x = east - west;
            int y = north - south;
            System.out.println(x);
            System.out.println(y);
            System.out.println(command.toString().replace("=", " "));
            System.out.println("Facing " + degrees);
            System.out.println("----------------------");
        }
        System.out.println("east " + east);
        System.out.println("west " + west);
        System.out.println("north " + north);
        System.out.println("south " + south);
        int x = east - west;
        int y = north - south;
        System.out.println(Math.abs(x));
        System.out.println(Math.abs(y));
        return Math.abs(east - west) + Math.abs(north - south);
    }

    private static Pair<String, Integer> getDirection(String line) {
        String hoinh = line.substring(0, 1);
        String distance = line.substring(1);
        return new Pair<>(hoinh, Integer.parseInt(distance));
    }


    private static String turnLeftRightIntoFacing(Pair<String, Integer> leftRight, String facing) {
        int timesToFace = leftRight.getValue() / 90;
        String facingResult = facing;
        for (int i = 0; i < timesToFace; i++) {
            facingResult = turn(facing, leftRight.getKey());
        }
        return facingResult;
    }

    private static String turn(String faceing, String leftOrRight) {
        if (leftOrRight.equals("L")) {
            if (faceing.equals("N")) {
                return "W";
            }
            if (faceing.equals("E")) {
                return "N";
            }
            if (faceing.equals("W")) {
                return "S";
            }
            if (faceing.equals("S")) {
                return "E";
            }

        }
        if (leftOrRight.equals("R")) {
            if (faceing.equals("N")) {
                return "E";
            }
            if (faceing.equals("E")) {
                return "S";
            }
            if (faceing.equals("W")) {
                return "N";
            }
            if (faceing.equals("S")) {
                return "W";
            }
        }
        return faceing;
    }


}
