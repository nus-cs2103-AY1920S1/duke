package dukepkg;

import junit.framework.TestCase;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DeadlineTest {
    @Test
    public void constructorTest(){
        Deadline d1 = new Deadline("finish 3230", "tmr");
        assertEquals("[D][✘] finish 3230 (by: tmr)", d1.toString());
        Deadline d2 = new Deadline("read 3131", "8/12/2008 1800");
        System.out.println(d2.toString());
        TestCase.assertEquals("[D][✘] read 3131 (by: 8/12/2008 1800)", d2.toString());
    }
}
