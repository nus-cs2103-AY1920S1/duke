import duke.command.Command;
import duke.logic.Parser;
import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    @Test
    void parse() {
        Parser parser = new Parser();
        assertTrue(parser.parse("bye") instanceof Command);
    }
}