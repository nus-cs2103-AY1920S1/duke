import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    @Test
    public void testParseEvent() {
        String input = "event Welcome Tea /at Tembusu College";
        Parser parser = new Parser(input);
        parser.parseEvent();
        assertEquals("Welcome Tea", parser.eventDescript);
        assertEquals("Tembusu College", parser.at);

    }

    @Test
    public void testParseDeadline() {
        String input = "deadline Homework /by 30/08/2019 23:59";
        Parser parser = new Parser(input);
        parser.parseDeadline();
        assertEquals("Homework", parser.deadlineDescript);
        assertEquals("30/08/2019 23:59", parser.by);

    }
}
