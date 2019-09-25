package duke.parser;

import duke.command.ByeCommand;
import duke.command.SendTasksCommand;
import duke.command.DoneTaskCommand;
import duke.command.AddTodoCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.DeleteTaskCommand;
import duke.command.FindCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_correctCommand_success() throws DukeException {
        assertEquals(new ByeCommand().getClass(),
                new Parser("bye").parse("bye").getClass());
        assertEquals(new SendTasksCommand().getClass(),
                new Parser("list").parse("list").getClass());
        assertEquals(new DoneTaskCommand("2").getClass(),
                new Parser("done 2").parse("done 2").getClass());
        assertEquals(new AddTodoCommand("project increments").getClass(),
                new Parser("todo project increments").parse("todo "
                        + "project increments").getClass());
        assertEquals(new AddDeadlineCommand("return book ", " 15/09/2019 1700").getClass(),
                new Parser("deadline return book /by 15/09/2019 1700").parse("deadline "
                        + "return book /by 15/09/2019 1700").getClass());
        assertEquals(new AddEventCommand("project meeting ", " 17/12/2019 1500").getClass(),
                new Parser("event project meeting /at 17/12/2019 1500").parse("event "
                        + "project meeting /at 17/12/2019 1500").getClass());
        assertEquals(new DeleteTaskCommand("5").getClass(),
                new Parser("delete 5").parse("delete 5").getClass());
        assertEquals(new FindCommand("book").getClass(),
                new Parser("find book").parse("find book").getClass());
    }

    @Test
    public void parse_missingDone_exceptionThrown() {
        try {
            assertEquals(new DoneTaskCommand("2"),
                    new Parser("done").parse("done"));
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please indicate a task index.", e.toString());
        }
    }

    @Test
    public void parse_missingTodo_exceptionThrown() {
        try {
            assertEquals(new AddTodoCommand("project increments"),
                    new Parser("todo").parse("todo"));
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.toString());
        }
    }

    @Test
    public void parse_missingDeadline_exceptionThrown() {
        try {
            assertEquals(new AddDeadlineCommand("return book ", " 15/09/2019 1700"),
                    new Parser("deadline").parse("deadline"));
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty.", e.toString());
        }
    }

    @Test
    public void parse_missingDateTime1_exceptionThrown() {
        try {
            assertEquals(new AddDeadlineCommand("return book ", " 15/09/2019 1700"),
                    new Parser("deadline return book ").parse("deadline return book "));
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please provide a date and time.", e.toString());
        }
    }

    @Test
    public void parse_missingDateTime2_exceptionThrown() {
        try {
            assertEquals(new AddEventCommand("project meeting ", " 17/12/2019 1500"),
                    new Parser("event project meeting /at ").parse("event "
                            + "project meeting /at "));
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please provide a date and time.", e.toString());
        }
    }

    @Test
    public void parse_missingEvent_exceptionThrown() {
        try {
            assertEquals(new AddEventCommand("project meeting ", " 17/12/2019 1500"),
                    new Parser("event").parse("event"));
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of an event cannot be empty.", e.toString());
        }
    }

    @Test
    public void parse_missingDelete_exceptionThrown() {
        try {
            assertEquals(new DeleteTaskCommand("5"),
                    new Parser("delete").parse("delete"));
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please indicate a task index.", e.toString());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            assertEquals(new SendTasksCommand(), new Parser("blah").parse("blah"));
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.toString());
        }
    }

    @Test
    public void parse_missingKeyword_exceptionThrown() {
        try {
            assertEquals(new FindCommand("book"), new Parser("find").parse("find"));
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please indicate keyword to be searched.", e.toString());
        }
    }
}
