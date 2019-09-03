import duke.Parser;
import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    void parserTest() {
        assertThrows(DukeException.class, () -> Parser.parse("asdfgh"));
    }


}