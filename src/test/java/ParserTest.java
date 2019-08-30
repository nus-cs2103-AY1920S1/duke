import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testFormatDate() {
        assertEquals(LocalDateTime.of(0100, 01, 01, 01, 00), Parser.formatDate("01/01/2000 0100"));
    }

    @Test
    public void testParse() throws DukeException {
        assertEquals(new AddCommand(new Deadline("D", "Test Task 5", Parser.formatDate("01/01/2000 0100"))),
                Parser.parse("deadline Test Task 5 /by 01/01/2000 0100"));
    }
}
