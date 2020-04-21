import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestCheckArray {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new int[] {1, 1, 4, 4, 1, 4, 4, 1, 4}, true},
                {new int[] {1, 1, 1}, false},
                {new int[] {4, 4, 4, 4}, false}
        });
    }

    private int[] array;
    private boolean flag;

    public TestCheckArray(int[] array, boolean flag) {
        this.array = array;
        this.flag = flag;
    }

    @Test
    public void massTest() {
        Assert.assertEquals(flag, Main.checkArray(array));
    }
}
