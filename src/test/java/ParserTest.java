import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void write_ToDoCommand_writtenCorrectly() {
        Parser testParser = new Parser(new TaskList());
        String testToDoStr = "todo return book to library";

        try {
            assertEquals(testParser.parse(testToDoStr), "Got it. I've added this task:\n  "
                    + "[T][✘] return book to library" + "\nNow you have 1 tasks in the list.");
            System.out.println("Todo Command correctly parsed.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void write_ToDoCommand_empty_exceptionThrown() {
        Parser testParser = new Parser(new TaskList());
        String testToDoStr = "todo ";

        try {
            assertEquals(testParser.parse(testToDoStr), "Got it. I've added this task:\n  "
                    + "[T][✘] return book to library" + "\nNow you have 1 tasks in the list.");
        } catch (DukeException e) {
            System.out.println("Exception successfully thrown: " + e.getMessage());
        }
    }

    @Test
    public void write_deadLineCommand_writtenCorrectly() {
        Parser testParser = new Parser(new TaskList());
        String testToDoStr = "deadline return book /by 12/12/1212 1212";

        try {
            assertEquals(testParser.parse(testToDoStr), "Got it. I've added this task:\n  "
                    + "[D][✘] return book (by: 12/12/1212 1212)" + "\nNow you have 1 tasks in the list.");
            System.out.println("deadline Command correctly parsed.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void write_deadLineCommand_empty_exceptionThrown() {
        Parser testParser = new Parser(new TaskList());
        String testToDoStr = "deadline ";

        try {
            assertEquals(testParser.parse(testToDoStr), "Got it. I've added this task:\n  "
                    + "[D][✘] return book (by: 12/12/1212 1212)" + "\nNow you have 1 tasks in the list.");
            fail();
        } catch (DukeException e) {
            System.out.println("Exception successfully thrown: " + e.getMessage());
        }
    }

    @Test
    public void write_deadLineCommand_wrong_dateFormat() {
        Parser testParser = new Parser(new TaskList());
        String testToDoStr = "deadline return book /by 13/13/2019 2460";

        try {
            assertEquals(testParser.parse(testToDoStr), "Got it. I've added this task:\n  "
                    + "[D][✘] return book (by: 13/13/2019 2460)" + "\nNow you have 1 tasks in the list.");
            fail();
        } catch (DukeException e) {
            System.out.println("Exception successfully thrown: " + e.getMessage());
        }
    }

}
