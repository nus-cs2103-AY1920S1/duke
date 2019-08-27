import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    void extractCommandTest() {
        String input = "todo buy eggs";
        assertEquals("todo", Parser.extractCommand(input));
    }

    @Test
    void parseDateTest() throws DukeException {
        String dateStr = "24/12/2018 1530";
        Date date = Parser.parseDate(dateStr);

        assertEquals(Duke.dateFormatter.format(date).toString(), dateStr);
    }
}
