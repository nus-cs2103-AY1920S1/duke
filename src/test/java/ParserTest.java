import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.parser.Parser;

public class ParserTest {
    @Test
    public void getCommand_bye() {
        assertEquals(0, new Parser().getCommand("bye"));
    }

    @Test
    public void getCommand_list() {
        assertEquals(1, new Parser().getCommand("list"));
    }
}
