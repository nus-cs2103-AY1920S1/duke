import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testDateConversion() {
        assertEquals("2 September 2019, 12.00AM", Parser.dateConversion("02/09/2019 0000"));
    }

    @Test
    public void testDetailParsing() {
        String[] expected = new String[] {"project meeting", "Mon 2-4pm"};
        String input = "project meeting /at Mon 2-4pm";
        assertEquals(expected[0], Parser.parseDetails(input)[0]);
        assertEquals(expected[1], Parser.parseDetails(input)[1]);
    }

    @Test
    public void testCommandParsing() {
        String[] expected = new String[] {"DONE", "1"};
        String input = "done 1";
        assertEquals(expected[0], Parser.parseCommand(input)[0]);
        assertEquals(expected[1], Parser.parseCommand(input)[1]);
    }
}
