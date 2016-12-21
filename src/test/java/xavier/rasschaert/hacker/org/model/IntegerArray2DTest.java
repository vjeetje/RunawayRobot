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
        Assert.assertEquals(original.get(0, 0), clone.get(0, 0));
        clone.set(0, 0, 1);
        Assert.assertEquals(original.get(0, 0), 0);
        Assert.assertEquals(clone.get(0, 0), 1);
    }

    @Test
    public void testPPrint() {
        IntegerArray2D array = new IntegerArray2D(new int[][]{{0, 0}, {1, 0}});
        Assert.assertEquals("0 0" + System.lineSeparator() + "1 0", array.pprint());
        Assert.assertEquals("a,a" + System.lineSeparator() + "b,a", array.pprint(i -> i == 0 ? "a" : "b", ","));
    }

    @Test
    public void testTranspose() {
        IntegerArray2D array = new IntegerArray2D(new int[][]{{1, 2}, {3, 4}});
        Assert.assertEquals(1, array.get(0, 0));
        Assert.assertEquals(2, array.get(0, 1));
        Assert.assertEquals(3, array.get(1, 0));
        Assert.assertEquals(4, array.get(1, 1));

        IntegerArray2D transpose = array.transpose();
        Assert.assertEquals(1, transpose.get(0, 0));
        Assert.assertEquals(3, transpose.get(0, 1));
        Assert.assertEquals(2, transpose.get(1, 0));
        Assert.assertEquals(4, transpose.get(1, 1));
    }
}
