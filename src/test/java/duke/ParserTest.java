package duke;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    void extractCommand_validInput_pass() {
        String input = "todo buy eggs";
        assertEquals("todo", Parser.extractCommand(input));
    }

    @Test
    void parseDate_validInput_pass() throws DukeException {
        String dateStr = "24/12/2018 1530";
        Date date = Parser.parseDate(dateStr);

        assertEquals(Duke.dateFormatter.format(date), dateStr);
    }
}
