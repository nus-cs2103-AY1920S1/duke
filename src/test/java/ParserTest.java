import org.junit.jupiter.api.Test;

import duke.parser.Comd;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.parser.Parser;

public class ParserTest {
    @Test
    public void getCommand_bye() {
        assertEquals(Comd.BYE, new Parser().getCommand("bye"));
    }

    @Test
    public void getCommand_list() {
        assertEquals(Comd.LIST, new Parser().getCommand("list"));
    }
}
