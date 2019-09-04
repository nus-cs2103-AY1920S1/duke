import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    @Test
    void parse() {
        Command actual = Parser.parse("deadline return book /by 2/12/2019 1800", 1);
        AddCommand expected = new AddCommand(Deadline.genDeadlineTask("deadline return book /by 2/12/2019 1800"));
        assertTrue(actual.equals(expected));
    }
}
