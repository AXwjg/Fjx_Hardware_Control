package io.oldwang.hardware_control;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        StringBuffer mIcNumber = new StringBuffer();
        mIcNumber.append("123");
        System.out.println("1: " + mIcNumber.toString());

        mIcNumber.setLength(0);
        mIcNumber.append("321");
        System.out.println("2: " + mIcNumber.toString());

    }
}