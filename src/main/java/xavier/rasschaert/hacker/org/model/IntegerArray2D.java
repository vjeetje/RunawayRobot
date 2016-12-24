package xavier.rasschaert.hacker.org.model;

import lombok.NonNull;
import org.apache.commons.lang3.Range;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntegerArray2D {
    /**
     * the two dimensional array of integers
     */
    private final int[][] array;

    /**
     * Constructs a IntegerArray2D with (x,y) = array[x,y]
     *
     * @param array the array
     */
    public IntegerArray2D(@NonNull int[][] array) {
        this.array = deepCopy(array);
    }

    /**
     * Constructs a IntegerArray2D with dimensions [width, height] and value val
     *
     * @param width  the width
     * @param height the height
     * @param val    the default value
     */
    public IntegerArray2D(int width, int height, int val) {
        int[][] array = IntStream.range(0, height)
                .mapToObj(j -> IntStream.range(0, width)
                        .map(i -> val)
                        .toArray())
                .toArray(int[][]::new);
        this.array = deepCopy(array);
    }

    public void set(int x, int y, int val) {
        array[y][x] = val;
    }

    public int get(int x, int y) {
        return array[y][x];
    }

    /**
     * get the value on the location (x, y) is it exists, if not get the default value
     *
     * @param x   the x-coordinate
     * @param y   the y-coordinate
     * @param val the default value
     * @return value on (x, y) if it exists, val otherwise
     */
    public int getOrElse(int x, int y, int val) {
        return contains(x, y) ? get(x, y) : val;
    }

    /**
     * tests if the the position (x, y) is in the bounds of the array
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return true in (x, y) is a position in the array
     */
    public boolean contains(int x, int y) {
        return Range.between(0, getWidth() - 1).contains(x) && Range.between(0, getHeight() - 1).contains(y);
    }

    /**
     * adds the array to the existing array element-wise.
     *
     * @param array the array
     * @return the 2 dimensional array addition
     */
    public IntegerArray2D add(@NonNull IntegerArray2D array) {
        return add(Position.ORIGIN, array);
    }

    /**
     * adds the array to the existing array element-wise starting from the given position
     *
     * @param p     the position of the top left corner the sub array is placed
     * @param array the sub array
     * @return the 2 dimensional array addition starting from the given position
     */
    public IntegerArray2D add(@NonNull Position p, @NonNull IntegerArray2D array) {
        int[][] transformed = IntStream.range(p.getY(), getHeight())
                .mapToObj(j -> IntStream.range(p.getX(), getWidth())
                        .map(i -> get(p.getX() + i, p.getY() + j) + array.getOrElse(i, j, 0))
                        .toArray())
                .toArray(int[][]::new);
        return new IntegerArray2D(transformed);
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof IntegerArray2D) {
            return IntStream.range(0, getHeight() - 1)
                    .allMatch(i -> Arrays.equals(getRow(i), ((IntegerArray2D) object).getRow(i)));
        } else {
            return false;
        }

    }

    @Override
    public IntegerArray2D clone() {
        return new IntegerArray2D(deepCopy(array));
    }

    private int[][] deepCopy(@NonNull int[][] array) {
        int width = array[0].length;
        int height = array.length;
        int[][] copy = new int[height][width];
        IntStream.range(0, height)
                .forEach(i -> System.arraycopy(array[i], 0, copy[i], 0, width));
        return copy;
    }

    /**
     * get the sub array with x in [x1, x2] and y in [y1, y2]
     *
     * @param x1 the left x-coordinate
     * @param y1 the top y-coordinate
     * @param x2 the right x-coordinate
     * @param y2 the bottom y-coordinate
     * @return the sub array
     */
    public IntegerArray2D slice(int x1, int y1, int x2, int y2) {
        int width = x2 - x1 + 1;
        int height = y2 - y1 + 1;
        int[][] copy = new int[height][width];
        IntStream.rangeClosed(y1, y2)
                .forEach(i -> System.arraycopy(getRow(i), x1, copy[i - y1], 0, width));
        return new IntegerArray2D(copy);
    }

    public int getWidth() {
        return array[0].length;
    }

    public int getHeight() {
        return array.length;
    }

    public int[] getRow(int index) {
        return array[index];
    }

    @Override
    public String toString() {
        return toString(String::valueOf, " ");
    }

    public String toString(@NonNull IntFunction<String> printValue, @NonNull String separator) {
        return Arrays.stream(array)
                .map(array -> IntStream.of(array)
                        .mapToObj(printValue)
                        .collect(Collectors.joining(separator)))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
