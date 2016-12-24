package xavier.rasschaert.hacker.org.model;

import org.junit.Assert;
import org.junit.Test;

public class IntegerArray2DTest {

    @Test
    public void testValueManipulation() {
        IntegerArray2D array = new IntegerArray2D(new int[][]{{0, 1}, {1, 0}});
        Assert.assertEquals(0, array.get(0, 0));
        array.set(0, 0, 1);
        Assert.assertEquals(1, array.get(0, 0));
    }

    @Test
    public void testClone() {
        IntegerArray2D original = new IntegerArray2D(new int[][]{{0, 1}, {1, 0}});
        IntegerArray2D clone = original.clone();
        Assert.assertEquals(original, clone);
        clone.set(0, 0, 1);
        Assert.assertEquals(original.get(0, 0), 0);
        Assert.assertEquals(clone.get(0, 0), 1);
    }

    @Test
    public void testPPrint() {
        IntegerArray2D array = new IntegerArray2D(new int[][]{{0, 0}, {1, 0}});
        Assert.assertEquals("0 0" + System.lineSeparator() + "1 0", array.toString());
        Assert.assertEquals("a,a" + System.lineSeparator() + "b,a", array.toString(i -> i == 0 ? "a" : "b", ","));
    }

    @Test
    public void testSlice() {
        IntegerArray2D array = new IntegerArray2D(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Assert.assertEquals(new IntegerArray2D(new int[][]{{1}}), array.slice(0, 0, 0, 0));
        Assert.assertEquals(new IntegerArray2D(new int[][]{{1, 2}, {4, 5}}), array.slice(0, 0, 1, 1));
        Assert.assertEquals(new IntegerArray2D(new int[][]{{2, 3}, {5, 6}}), array.slice(1, 0, 2, 1));
        Assert.assertEquals(new IntegerArray2D(new int[][]{{4, 5}, {7, 8}}), array.slice(0, 1, 1, 2));
        Assert.assertEquals(array, array.slice(0, 0, 2, 2));
    }

    @Test
    public void testAddition() {
        IntegerArray2D augend = new IntegerArray2D(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        IntegerArray2D addend = new IntegerArray2D(new int[][]{{1, 2, 4}, {3, 5, 7}, {9, 8, 6}});
        IntegerArray2D sum = new IntegerArray2D(new int[][]{{2, 4, 7}, {7, 10, 13}, {16, 16, 15}});
        Assert.assertEquals(sum, augend.add(addend));
    }

    @Test
    public void testAdditionOfSmallerArray() {
        IntegerArray2D augend = new IntegerArray2D(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        IntegerArray2D addend = new IntegerArray2D(new int[][]{{1, 2}, {3, 5}, {9, 8}});
        IntegerArray2D sum = new IntegerArray2D(new int[][]{{2, 4, 3}, {7, 10, 6}, {16, 16, 9}});
        Assert.assertEquals(sum, augend.add(addend));
    }
}
