package Task;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void TestStringConversion() throws ParseException {
        assertEquals("[D][✘] run(by: Wed Dec 12 12:00:00 SGT 2012)", new Deadline("run", "12/12/2012 1200").toString());
    }

}