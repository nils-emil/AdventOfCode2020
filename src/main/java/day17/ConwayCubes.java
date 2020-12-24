package day17;


import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConwayCubes {

    public static long getSum(String inputfile) throws FileNotFoundException {
        List<String> lines = FileUtils.getCsvLines(inputfile);
        List<Cube> cubes = new ArrayList<>();
        for (int column = 0; column < lines.get(0).length(); column++) {
            for (int row = 0; row < lines.size(); row++) {
                char characted = lines.get(row).charAt(column);
                if (characted == '#') {
                    Cube cube = new Cube(row, column, 0, true);
                    cubes.add(cube);
                } else if (characted == '.') {
                    Cube cube = new Cube(row, column, 0, false);
                    cubes.add(cube);
                }

            }
        }
        for (Cube c : cubes) {
            System.out.println(c);
        }
        for (int iteration = 0; iteration < 6; iteration++) {
//            for (int cubeCount = 0; cubeCount < cubes.size(); cubeCount++) {
//                List<Cube> newCubes = cubes.get(cubeCount).getUndocumentedNeigbours(cubes);
//            }
            long[] neigborusCount = new long[cubes.size()];
            System.out.println(Arrays.toString(neigborusCount));
            printMap(cubes);
            for (int c = 0; c < cubes.size(); c++) {
                Cube cube = cubes.get(c);
                long numberOfActiveNeightours = cube.getNumberOfActiveNeightours(cubes);
                neigborusCount[c] = numberOfActiveNeightours;
                System.out.println(cube.toString() + " has " + numberOfActiveNeightours + " neigbours");
            }
            System.out.println(Arrays.toString(neigborusCount));

            for (int c = 0; c < cubes.size(); c++) {
                long count = neigborusCount[c];
                Cube cube = cubes.get(c);
                if (cube.getIsActive()) {
                    boolean isActive = count == 2 || count == 3;
                    cube.setActive(isActive);
                } else {
                    boolean isActive = count == 3;
                    cube.setActive(isActive);
                }
            }
            System.out.println(Arrays.toString(neigborusCount));
            System.out.println("--------------------------- Iteration ended");
            long count = cubes
                    .stream()
                    .filter(Cube::getIsActive)
                    .count();
            System.out.println(count + " active cubes");
        }
        List<Cube> cubeStream = cubes
                .stream()
                .filter(Cube::getIsActive)
                .collect(Collectors.toList());
        return cubeStream.size();
    }

    private static void printMap(List<Cube> cubes) {
        int currentMax = 1;
        for (Cube e : cubes) {
            Integer z = e.getZ();
            if (e.getZ() > currentMax) {
                currentMax = e.getZ();
            }
        }

        for (int z = 0; z < currentMax; z++) {
            for (int xPos = 0; xPos < 10; xPos++) {
                StringBuilder result = new StringBuilder();
                for (int yPos = 0; yPos < 10; yPos++) {
                    int finalXPos = xPos;
                    int finalYPos = yPos;
                    Optional<Cube> any = cubes.stream()
                            .filter(e -> e.getX() == finalXPos && e.getY() == finalYPos)
                            .findAny();
                    boolean active = false;
                    if (any.isPresent() && any.get().getIsActive()) {
                        active = true;
                    }
                    result.append(active ? "#" : ".");

                }
                System.out.println(result);
            }
        }
    }


}
