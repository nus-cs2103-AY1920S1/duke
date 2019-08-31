import command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse() {
        assertEquals(new ListCommand(), Parser.parse("list"));
    }
}