package xavier.rasschaert.hacker.org.model;

import lombok.NonNull;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntegerArray2D {
    private final int[][] array;

    public IntegerArray2D(@NonNull int[][] array) {
        this.array = deepCopy(array);
    }

    public void set(int x, int y, int val) {
        array[x][y] = val;
    }

    public int get(int x, int y) {
        return array[x][y];
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof IntegerArray2D) {
            return IntStream.range(0, getWidth() - 1)
                    .allMatch(i -> Arrays.equals(getColumn(i), ((IntegerArray2D) object).getColumn(i)));
        } else {
            return false;
        }

    }

    @Override
    public IntegerArray2D clone() {
        return new IntegerArray2D(deepCopy(array));
    }

    private int[][] deepCopy(@NonNull int[][] array) {
        int width = array.length;
        int height = array[0].length;
        int[][] copy = new int[width][height];
        IntStream.range(0, width)
                .forEach(i -> System.arraycopy(array[i], 0, copy[i], 0, height));
        return copy;
    }

    public IntegerArray2D transpose() {
        int[][] transpose = IntStream.range(0, getHeight())
                .mapToObj(i -> IntStream.range(0, getWidth())
                        .map(j -> array[j][i])
                        .toArray())
                .toArray(int[][]::new);
        return new IntegerArray2D(transpose);
    }

    public int getWidth() {
        return array.length;
    }

    public int getHeight() {
        return array[0].length;
    }

    public int[] getColumn(int index) {
        return array[index];
    }

    public String pprint() {
        return pprint(String::valueOf, " ");
    }

    public String pprint(@NonNull IntFunction<String> printValue, @NonNull String separator) {
        return Arrays.stream(array)
                .map(array -> IntStream.of(array)
                        .mapToObj(printValue)
                        .collect(Collectors.joining(separator)))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
