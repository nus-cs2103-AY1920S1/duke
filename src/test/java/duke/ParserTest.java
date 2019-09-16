package duke;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParse() throws ParseException, DukeException {
        Parser p1 = new Parser("todo play", false);
        Parser p2 = new Parser("event meeting /at 09/06/1934 0700", false);
        Parser p3 = new Parser("deadline project /by 03/11/3241 1200", false);
        String s1 = "[T][ ][L] play";
        String s2 = "[E][ ][L] meeting (at: 09 Jun 1934, 07:00 AM)";
        String s3 = "[D][ ][L] project (by: 03 Nov 3241, 12:00 PM)";
        assertEquals(s1, p1.parse().toString());
        assertEquals(s2, p2.parse().toString());
        assertEquals(s3, p3.parse().toString());
    }
}
