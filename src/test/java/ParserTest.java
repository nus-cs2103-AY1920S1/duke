import commands.AddCommand;
import commands.ExitCommand;
import commands.PrintCommand;

import oop.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void parsePrint() {
        assertEquals(new PrintCommand(), Parser.parse("list"));
    }

    @Test
    void parseToDo() {
        assertEquals(new AddCommand("todo", "random"),
                Parser.parse("todo random"));
    }

    @Test
    void parseExit() {
        assertEquals(new ExitCommand(), Parser.parse("bye"));
    }
}
