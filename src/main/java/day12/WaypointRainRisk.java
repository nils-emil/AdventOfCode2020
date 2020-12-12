package day12;


import javafx.util.Pair;
import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class WaypointRainRisk {

    public static int getManhattanDistance(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        List<Pair<String, Integer>> collect = lines.stream()
                .map(WaypointRainRisk::getDirection)
                .collect(Collectors.toList());
        int wayPointX = 10;
        int wayPointY = 1;
        int shipX = 0;
        int shipY = 0;
        for (Pair<String, Integer> command : collect) {
            if (command.getKey().equals("N")) {
                wayPointY += command.getValue();
            }
            if (command.getKey().equals("S")) {
                wayPointY -= command.getValue();
            }
            if (command.getKey().equals("E")) {
                wayPointX += command.getValue();
            }
            if (command.getKey().equals("W")) {
                wayPointX -= command.getValue();
            }
            if (command.getKey().equals("R")) {
                Pair<Integer, Integer> rotatedWayPoint = getRotatedWayPoint(command.getValue(), wayPointX, wayPointY);
                wayPointX = rotatedWayPoint.getKey();
                wayPointY = rotatedWayPoint.getValue();
            }
            if (command.getKey().equals("L")) {
                Pair<Integer, Integer> rotatedWayPoint = getRotatedWayPoint(-1 * command.getValue(), wayPointX, wayPointY);
                wayPointX = rotatedWayPoint.getKey();
                wayPointY = rotatedWayPoint.getValue();
            }

            if (command.getKey().equals("F")) {
                shipX += command.getValue() * wayPointX;
                shipY += command.getValue() * wayPointY;
            }
        }
        return Math.abs(shipX) + Math.abs(shipY);
    }


    private static Pair<Integer, Integer> getRotatedWayPoint(int degrees, int wayPointX, int wayPointY) {
        if (degrees % 360 == 90 || degrees % 360 == -270) {
            return new Pair<>(wayPointY, -1 * wayPointX);
        }
        if (degrees % 360 == 180 || degrees % 360 == -180) {
            return new Pair<>(-wayPointX, -wayPointY);
        }
        if (degrees % 360 == 270 || degrees % 360 == -90) {
            return new Pair<>(-1 * wayPointY, wayPointX);
        }
        return null;
    }

    private static Pair<String, Integer> getDirection(String line) {
        String hoinh = line.substring(0, 1);
        String distance = line.substring(1);
        return new Pair<>(hoinh, Integer.parseInt(distance));
    }

}
