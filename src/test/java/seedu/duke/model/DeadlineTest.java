package seedu.duke.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void deadline() throws ParseException {
        Deadline d = new Deadline("return book", "28/08/19 18:00");
        assertEquals("D", d.getType());
        assertEquals("[D][✘] return book (by: Mon Aug 28 18:00:00 SGT 19)", d.toString());
    }
}
