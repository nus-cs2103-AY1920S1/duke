import duke.Commands.Command;
import duke.DirectProcessor.Parser;
import duke.DirectProcessor.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void addTodoTest() {
        Command c = Parser.parse("todo Eat dinner");
        assertEquals("duke.Commands.AddCommand", c.getClass().getName());
    }

    @Test
    public void addEventTest() {
        Command c = Parser.parse("event Eat dinner /at 19/08/2019 18:00:00");
        assertEquals("duke.Commands.AddCommand", c.getClass().getName());
    }

    @Test
    public void addDeadlineTest() {
        Command c = Parser.parse("deadline Eat dinner /by 19/08/2019 18:00:00");
        assertEquals("duke.Commands.AddCommand", c.getClass().getName());
    }

    @Test
    public void finishTest() {
        Command c = Parser.parse("done 2");
        assertEquals("duke.Commands.FinishCommand", c.getClass().getName());
    }

    @Test
    public void listTest() {
        Command c = Parser.parse("list");
        assertEquals("duke.Commands.ListCommand", c.getClass().getName());
    }

    @Test
    public void exitTest() {
        Command c = Parser.parse("bye");
        assertEquals("duke.Commands.ExitCommand", c.getClass().getName());
    }
}
