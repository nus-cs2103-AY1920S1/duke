/**
 * Test class to test parse() method in Parser.
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTest() {
        assertEquals(new DoneCommand(2), Parser.parse("done 3"));
    }

}
