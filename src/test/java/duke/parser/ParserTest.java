package duke.parser;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parseUserEnterInputExceptionNotThrown() {
        assertDoesNotThrow(() -> Parser.parse("event project meeting /at 2/12/2019 1800"));
        assertDoesNotThrow(() -> Parser.parse("event project meeting /at 2/2/2019 1800"));
        assertDoesNotThrow(() -> Parser.parse(" event   project meeting     /at         2/2/2019 1800   "));
        assertDoesNotThrow(() -> Parser.parse("done 1"));
        assertDoesNotThrow(() -> Parser.parse("list !@#$"));
        assertDoesNotThrow(() -> Parser.parse("bye  "));
        assertDoesNotThrow(() -> Parser.parse("     bye 0.0.0"));
        assertDoesNotThrow(() -> Parser.parse("bye !@#$"));
    }

    @Test
    public void parseUserEnterInputExceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse(""));
        assertThrows(DukeException.class, () -> Parser.parse("  "));
        assertThrows(DukeException.class, () -> Parser.parse("event  /at 2/12/2019 1800"));
        assertThrows(DukeException.class, () -> Parser.parse("event project meeting /at "));
        assertThrows(DukeException.class, () -> Parser.parse("event project meeting 2/12/2019 1800"));
        assertThrows(DukeException.class, () -> Parser.parse("event project meeting /at 02/12/0000 1800"));
        assertThrows(DukeException.class, () -> Parser.parse("event project meeting /at 2/12/2019 9999"));
        assertThrows(DukeException.class, () -> Parser.parse("done @#$%"));
        assertThrows(DukeException.class, () -> Parser.parse("!@# delete 1"));
    }
}
