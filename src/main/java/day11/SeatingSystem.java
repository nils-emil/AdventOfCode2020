package day11;


import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SeatingSystem {

    public static int getNumberOfOccupiedSeats(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        List<String[]> seatsList = lines.stream()
                .map(e -> e.split(""))
                .collect(Collectors.toList());
        String[][] seatsInput = new String[seatsList.size()][seatsList.get(0).length];
        for (int i = 0; i < seatsList.size(); i++)
            seatsInput[i] = seatsList.get(i);
        List<Integer> dx = Arrays.asList(1, -1, 0, 0, -1, -1, 1, 1);
        List<Integer> dy = Arrays.asList(0, 0, 1, -1, -1, 1, -1, 1);
        while (true) {
            boolean changed = false;
            String[][] seats = new String[seatsList.size()][seatsList.get(0).length];
            for (int i = 0; i < seatsInput.length; i++)
                seats[i] = Arrays.copyOf(seatsInput[i], seatsInput[i].length);
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    int occupied = 0;
                    for (int pos = 0; pos < dx.size(); pos++) {
                        int x = i + dx.get(pos);
                        int y = j + dy.get(pos);
                        if (directionContainsOccupiedSeat(seats, x, y, dx.get(pos), dy.get(pos))) {
                            occupied++;
                        }
                    }
                    boolean someOneSeated = seats[i][j].equals("L") && occupied == 0;
                    boolean someOneIsLeaving = seats[i][j].equals("#") && occupied >= 5;
                    if (someOneSeated) {
                        changed = true;
                        seatsInput[i][j] = "#";
                    } else if (someOneIsLeaving) {
                        changed = true;
                        seatsInput[i][j] = "L";
                    }
                }

            }
            if (!changed) {
                break;
            }

        }
        List<String> collect = Arrays.stream(seatsInput).map(e -> String.join("", e)).collect(Collectors.toList());
        String join = String.join(",", collect);
        int charCount = 0;
        char temp;

        for (int i = 0; i < join.length(); i++) {
            temp = join.charAt(i);

            if (temp == '#')
                charCount++;
        }
        return charCount;
    }

    private static boolean directionContainsOccupiedSeat(String[][] seats, int x, int y, Integer xincrement, Integer yincrement) {
        if (!isBetweenBounds(x, y, seats)) {
            return false;
        }
        if (seats[x][y].equals("#")) {
            return true;
        }
        if (seats[x][y].equals("L")) {
            return false;
        }
        if (seats[x][y].equals(".")) {
            return directionContainsOccupiedSeat(seats,  x + xincrement,  y + yincrement,  xincrement,  yincrement);
        }
        return false;
    }


    private static boolean isBetweenBounds(int x, int y, String[][] seats) {
        int xLen = seats.length;
        int yLen = seats[0].length;
        return x < xLen && x >= 0 && y < yLen && y >= 0;
    }


}
