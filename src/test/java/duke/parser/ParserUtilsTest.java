package duke.parser;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserUtilsTest {

    @Test
    public void parseUserEnterInputExceptionNotThrown() {
        assertDoesNotThrow(() -> ParserUtils.parse("event project meeting /at 2/12/2019 1800"));
        assertDoesNotThrow(() -> ParserUtils.parse("event project meeting /at 2/2/2019 1800"));
        assertDoesNotThrow(() -> ParserUtils.parse(" event   project meeting     /at         2/2/2019 1800   "));
        assertDoesNotThrow(() -> ParserUtils.parse("done 1"));
        assertDoesNotThrow(() -> ParserUtils.parse("list !@#$"));
        assertDoesNotThrow(() -> ParserUtils.parse("bye  "));
        assertDoesNotThrow(() -> ParserUtils.parse("     bye 0.0.0"));
        assertDoesNotThrow(() -> ParserUtils.parse("bye !@#$"));
    }

    @Test
    public void parseUserEnterInputExceptionThrown() {
        assertThrows(DukeException.class, () -> ParserUtils.parse(""));
        assertThrows(DukeException.class, () -> ParserUtils.parse("  "));
        assertThrows(DukeException.class, () -> ParserUtils.parse("event  /at 2/12/2019 1800"));
        assertThrows(DukeException.class, () -> ParserUtils.parse("event project meeting /at "));
        assertThrows(DukeException.class, () -> ParserUtils.parse("event project meeting 2/12/2019 1800"));
        assertThrows(DukeException.class, () -> ParserUtils.parse("event project meeting /at 02/12/0000 1800"));
        assertThrows(DukeException.class, () -> ParserUtils.parse("event project meeting /at 2/12/2019 9999"));
        assertThrows(DukeException.class, () -> ParserUtils.parse("done @#$%"));
        assertThrows(DukeException.class, () -> ParserUtils.parse("!@# delete 1"));
    }
}
