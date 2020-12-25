package day25;


public class ComboBreaker {

    public static final long CARD_PUBLIC_KEY = 14788856L;
    public static final long DOOR_PUBLIC_KEY = 19316454L;

    public static void main(String[] args) {
        System.out.println(ComboBreaker.part1());
    }

    public static Object part1() {
        long value = 1;
        int iterations = 0;
        while (true) {
            iterations++;
            value = transform(value, iterations - 1, iterations, 7);
            if (value == CARD_PUBLIC_KEY) {
                return transform(1, 0, iterations, DOOR_PUBLIC_KEY);
            }
        }
    }

    public static long transform(long value, int start, int iterations, long subject) {
        for (int i = start; i < iterations; i++) {
            value = value * subject;
            value = value % 20201227;
        }
        return value;
    }
}
