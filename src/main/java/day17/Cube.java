package day17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Cube {

    private int x;
    private int y;
    private int z;
    private boolean isActive;


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Cube(int x, int y, int z, boolean active) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.isActive = active;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<Cube> getUndocumentedNeigbours(List<Cube> cubes) {
        if (!isActive) {
            return Collections.emptyList();
        }
        List<Cube> neigbours = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    int neighborurZ = this.z - z;
                    int neighborurX = this.x - x;
                    int neighborurY = this.y - y;
                    boolean b = x == 0 && y == 0 && z == 0;
                    if (!b) {
                        Optional<Cube> any = cubes
                                .stream()
                                .filter(e -> e.isLocatedAt(neighborurX, neighborurY, neighborurZ))
                                .findAny();
                        if (!any.isPresent()) {
                            Cube cube = new Cube(neighborurX, neighborurY, neighborurZ, false);
                            neigbours.add(cube);
                        }
                    }
                }
            }
        }
        return neigbours;
    }

    public long getNumberOfActiveNeightours(List<Cube> cubes) {
        long counter = 0;
        for (int xx = -1; xx <= 1; xx++) {
            for (int yy = -1; yy <= 1; yy++) {
                for (int zz = -1; zz <= 1; zz++) {
                    int neighborurX = this.x + xx;
                    int neighborurY = this.y + yy;
                    int neighborurZ = this.z + zz;
                    boolean b = xx == 0 && yy == 0 && zz == 0;
                    if (!b) {
                        Optional<Cube> any = cubes
                                .stream()
                                .filter(e -> e.isLocatedAt(neighborurX, neighborurY, neighborurZ))
                                .filter(e -> e.isActive)
                                .findAny();
                        if (any.isPresent()) {
                            counter++;
                        }
                    }
                    else {
                        System.out.println("fck");
                    }
                }
            }
        }

        return counter;
    }


    @Override
    public String toString() {
        return "{ x: " + x + ", y: " + y + ", z: " + z + ", active: " + isActive + " }";
    }

    public boolean isLocatedAt(int x, int y, int z) {
        return this.x == x && this.y == y && this.z == z;
    }
}
