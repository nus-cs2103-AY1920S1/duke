import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class ParserTest {

    @Test
    public void testDateConversion() {
        try {
            assertEquals("2 September 2019, 12.00AM", Parser.convertDate("02/09/2019 0000"));
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void testDetailParsing() {
        String[] expectedOutputs = new String[] {"project meeting", "Mon 2-4pm"};
        String input = "project meeting /at Mon 2-4pm";

        try {
            assertEquals(expectedOutputs[0], Parser.parseDetails(input)[0]);
            assertEquals(expectedOutputs[1], Parser.parseDetails(input)[1]);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void testCommandParsing() {
        String[] expectedOutputs = new String[] {"done", "1"};
        String input = "done 1";
        assertEquals(expectedOutputs[0], Parser.parseCommand(input)[0]);
        assertEquals(expectedOutputs[1], Parser.parseCommand(input)[1]);
    }
}
