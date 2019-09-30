package dukepkg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DeadlineTest {
    @Test
    public void constructorTest(){
        Deadline d1 = new Deadline("finish 3230", "tmr");
        Assertions.assertEquals("[D][✘] finish 3230 (by: tmr)", d1.toString());
        Deadline d2 = new Deadline("read 3131", "8/12/2008 1800");
        //System.out.println(d2.toString());
        Assertions.assertEquals("[D][✘] read 3131 (by: 8/12/2008 1800)", d2.toString());
    }
}
