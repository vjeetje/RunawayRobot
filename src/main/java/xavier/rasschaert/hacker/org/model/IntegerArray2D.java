package xavier.rasschaert.hacker.org.model;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntegerArray2D {
    private int[][] array;

    public IntegerArray2D(int[][] array) {
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
        if (object instanceof IntegerArray2D) {
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

    private int[][] deepCopy(int[][] array) {
        int[][] copy = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            int[] column = array[i];
            copy[i] = new int[column.length];
            System.arraycopy(column, 0, copy[i], 0, column.length);
        }
        return copy;
    }

    public IntegerArray2D transpose() {
        int[][] transpose = new int[getHeight()][getWidth()];
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                transpose[i][j] = array[j][i];
            }
        }
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

    public String pprint(IntFunction<String> printValue, String separator) {
        return Arrays.stream(array)
                .map(array -> printArray(array, printValue, separator))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String printArray(int[] values, IntFunction<String> printValue, String separator) {
        return IntStream.of(values)
                .mapToObj(printValue)
                .collect(Collectors.joining(separator));
    }
}
