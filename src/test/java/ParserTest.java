import commands.AddCommand;
import commands.ExitCommand;
import commands.PrintCommand;

import exceptions.DukeException;
import oop.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void parsePrint() throws DukeException {
        assertEquals(new PrintCommand(), Parser.parse("list"));
    }

    @Test
    void parseToDo() throws DukeException {
        assertEquals(new AddCommand("todo random"),
                Parser.parse("todo random"));
    }

    @Test
    void parseExit() throws DukeException {
        assertEquals(new ExitCommand(), Parser.parse("bye"));
    }
}
