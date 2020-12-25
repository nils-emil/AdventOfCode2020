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
                    Cube cube = new Cube(row, column, 0,0, true);
                    cubes.add(cube);
                } else if (characted == '.') {
                    Cube cube = new Cube(row, column, 0, 0,false);
                    cubes.add(cube);
                }

            }
        }
        for (int iteration = 0; iteration < 6; iteration++) {
            for (int cubeCount = 0; cubeCount < cubes.size(); cubeCount++) {
                List<Cube> newCubes = cubes.get(cubeCount).getUndocumentedNeigbours(cubes);
                cubes.addAll(newCubes);
            }
            long[] neigborusCount = new long[cubes.size()];
            for (int c = 0; c < cubes.size(); c++) {
                Cube cube = cubes.get(c);
                long numberOfActiveNeightours = cube.getNumberOfActiveNeightours(cubes);
                neigborusCount[c] = numberOfActiveNeightours;
            }
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
            System.out.println("Iteration done");
        }
        List<Cube> cubeStream = cubes
                .stream()
                .filter(Cube::getIsActive)
                .collect(Collectors.toList());
        return cubeStream.size();
    }

}
