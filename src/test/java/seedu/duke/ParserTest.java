package seedu.duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void test1() {
       try {
           assertEquals("[D][\u2718] abc (by: 24/08/2019 18.00 PM)", Parser.readInFileLine("D | 0 | abc | 24/08/2019 1800").toString());
       } catch (Exception e) {
            assertEquals(1,2);
        }
    }

    @Test
    public void test2() {
        try {
            assertEquals("[D][\u2713] abc (by: 24/08/2019 18.00 PM)", (Parser.readInFileLine("D | 1 | abc | 24/08/2019 1800")).toString());
        } catch (Exception e) {
            assertEquals(1,2);
        }
    }

}
