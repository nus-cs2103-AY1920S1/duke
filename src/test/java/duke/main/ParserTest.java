package duke.main;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    void parserTest() throws DukeException  {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("done 2") instanceof DoneCommand);
        assertTrue(Parser.parse("delete 2") instanceof DeleteCommand);

        assertTrue(Parser.parse("todo todo") instanceof AddTodoCommand);
        assertTrue(Parser.parse("deadline deadline /by deadlineBy") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("deadline deadline /by 25/07/2015 1500") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("event event /at eventAt") instanceof AddEventCommand);
        assertTrue(Parser.parse("event event /at 26/07/2014 1600") instanceof AddEventCommand);
    }
}
