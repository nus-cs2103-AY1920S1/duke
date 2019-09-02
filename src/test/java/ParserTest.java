import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void parse1() {
        assertEquals(new PrintCommand(), Parser.parse("add"));
    }

    @Test
    void parse2() {
        assertEquals(new AddCommand("random", "random"), Parser.parse("bye"));
    }

    @Test
    void parse3() {
        assertEquals(new ExitCommand(), Parser.parse("any"));
    }
}