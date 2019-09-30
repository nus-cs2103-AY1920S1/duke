package duke.test.Core;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;

import duke.core.Parser;
import duke.helper.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parserParseTestAdd() throws DukeException {
        String addToDo = "todo borrow book";
        Command c = Parser.parse(addToDo);
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parserParseTestDone() throws DukeException {
        String addDone = "done 3";
        Command c = Parser.parse(addDone);
        assertTrue(c instanceof DoneCommand);
    }

    @Test
    public void parserParseTestDelete() throws DukeException {
        String addDelete = "delete 3";
        Command c = Parser.parse(addDelete);
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    public void parserParseTestList() throws DukeException {
        String addList = "list";
        Command c = Parser.parse(addList);
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void parserParseTest_exceptionThrown() {
        try {
            String testError = "Blah";
            assertEquals(testError, Parser.parse(testError));
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("\u2639 " + "OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
