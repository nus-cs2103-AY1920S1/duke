package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import exception.DukeException;
import duke.Parser;

class EndCommandTest {
    @Test
    void variableIsExit_returnsTrueByDefault() throws ParseException, DukeException {
        Command c = Parser.parse("bye");
        assertTrue(c.isExit(), "Creation of EndCommand did not set isExit to true");
    }
}
