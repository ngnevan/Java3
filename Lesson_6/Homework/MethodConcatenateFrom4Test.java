import Lesson_6.Homework.MethodConcatenateFrom4;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MethodConcatenateFrom4Test {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 7}, new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}},
                {new int[]{2, 3, 1, 1, 7}, new int[]{1, 2, 1, 4, 2, 3, 1, 1, 7}},
                {new int[]{2, 2, 3, 2, 1, 7}, new int[]{1, 2, 4, 2, 2, 3, 2, 1, 7}},
                {new int[]{3, 2, 2, 3, 2, 1, 7}, new int[]{1, 4, 3, 2, 2, 3, 2, 1, 7}}
        });
    }

    private MethodConcatenateFrom4 method1;
    private int[] a;
    private int[] b;

    public MethodConcatenateFrom4Test(int[] a, int[] b) {
        this.a = a;
        this.b = b;
    }

    @Before
    public void init() {
        method1 = new MethodConcatenateFrom4();
    }

    @Test
    public void test1() {
        Assert.assertTrue("Test correct", Arrays.equals(a, method1.MethodConcatenateFrom4(b)));
    }
}
