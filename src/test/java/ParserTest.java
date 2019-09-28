import logic.DukeException;
import logic.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseDateTime() throws DukeException {
        LocalDateTime test = Parser.parseDateTime("2/12/2019 1820");
        LocalDateTime actual = LocalDateTime.of(2019, 12, 2, 18, 20);
        assertEquals(test, actual);
    }

}