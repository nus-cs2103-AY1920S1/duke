import duke.util.Parser;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    void parserErrorTest() {
        assertThrows(DukeException.class, () -> Parser.parse("This is input which is invalid."));
    }
}