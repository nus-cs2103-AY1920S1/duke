import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse() {
        Parser parser = new Parser();
        assertTrue(parser.parse("bye") instanceof Command);
    }
}