import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestCutAaray {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[] {1, 7}},
                {new int[] {8, 4, 3, 7, 1, 9}, new int[] {3, 7, 1, 9}},
                {new int[] {0, 6, 0, 8, 3}, new int[] {}},
                {new int[] {4, 4, 4, 4, 4, 4}, new int[] {}}
        });
    }

    private int[] array;
    private int[] newArray;

    public TestCutAaray(int[] array, int[] newArray) {
        this.array = array;
        this.newArray = newArray;
    }

    @Test
    public void massTest() {
        Assert.assertArrayEquals(newArray, Main.cutArray(array));
    }
}
