import duke.Commands.Command;
import duke.DirectProcessor.Parser;
import duke.DirectProcessor.TaskList;
import duke.Duke;
import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void addTodoTest() {
        try {
            Command c = Parser.parse("todo Eat dinner");
            assertEquals("duke.Commands.AddCommand", c.getClass().getName());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void addEventTest() {
        try {
            Command c = Parser.parse("event Eat dinner /at 19/08/2019 18:00:00");
            assertEquals("duke.Commands.AddCommand", c.getClass().getName());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void addDeadlineTest() {
        try {
            Command c = Parser.parse("deadline Eat dinner /by 19/08/2019 18:00:00");
            assertEquals("duke.Commands.AddCommand", c.getClass().getName());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void finishTest() {
        try {
            Command c = Parser.parse("done 2");
            assertEquals("duke.Commands.FinishCommand", c.getClass().getName());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void listTest() {
        try {
            Command c = Parser.parse("list");
            assertEquals("duke.Commands.ListCommand", c.getClass().getName());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void exitTest() {
        try {
            Command c = Parser.parse("bye");
            assertEquals("duke.Commands.ExitCommand", c.getClass().getName());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }
}
