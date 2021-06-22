import command.ListCommand;
import org.junit.jupiter.api.Test;
import parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void parse() {
        assertEquals(new ListCommand(), Parser.parse("list"));
    }
}