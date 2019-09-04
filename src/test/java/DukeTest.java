import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Event;
import duke.command.Command;
import duke.command.AddCommand;
import duke.command.UpdateCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.Parser;
import duke.error.DukeException;

public class DukeTest {

    @Test
    public void testTodo_toString() {
        //test 1
        assertEquals("[T][ ] hello", new Todo("hello").toString());
        //test 2
        Task todo = new Todo("hello");
        todo.markAsDone();
        assertEquals("[T][X] hello", todo.toString());
    }

    @Test
    public void testEvent_toString() {
        //test 1
        assertEquals("[E][ ] return book (at: 2/12/2019 1800)", new Event("return book", "2/12/2019 1800").toString());
        //test 2
        Task event = new Event("return book", "2/12/2019 1800");
        event.markAsDone();
        assertEquals("[E][X] return book (at: 2/12/2019 1800)", event.toString());
    }

    @Test
    public void testDeadline_toString() {
        //test 1
        assertEquals("[D][ ] return book (by: 2/12/2019 1800)",
                new Deadline("return book", "2/12/2019 1800").toString());
        //test 2
        Task deadline = new Deadline("return book", "2/12/2019 1800");
        deadline.markAsDone();
        assertEquals("[D][X] return book (by: 2/12/2019 1800)", deadline.toString());
    }

    @Test
    public void testCommandType() throws DukeException {
        Parser parser = new Parser();
        assertTrue((parser.parse("todo hello")) instanceof AddCommand);
        assertTrue((parser.parse("event return book /at 2/12/2019 1800")) instanceof AddCommand);
        assertTrue((parser.parse("deadline return book /by 2/12/2019 1800")) instanceof AddCommand);
        assertTrue((parser.parse("done 1")) instanceof UpdateCommand);
        assertTrue((parser.parse("delete 1")) instanceof DeleteCommand);
        assertTrue((parser.parse("list")) instanceof ListCommand);
        assertTrue((parser.parse("find hello")) instanceof FindCommand);
        assertTrue((parser.parse("bye")) instanceof ExitCommand);
    }

    @Test
    public void testAddCommand() throws DukeException {
        //test 1
        assertEquals("[D][ ] return book (by: 2nd December 2019, 6PM)",
                new AddCommand("D", "return book /by 2/12/2019 1800").toString());
        //test 2
        try {
            Command c = new AddCommand("D", "return book /by 2/12/2019");
            fail(); // test should not reach this line
        } catch (DukeException e) {
            assertEquals("Invalid date format: dd/mm/yyyy HHmm only!", e.getMessage());
        }
        //test 3
        try {
            Parser parser = new Parser();
            parser.parse("todo ");
        } catch (DukeException e) {
            assertEquals("       OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }



}
