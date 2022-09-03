package duke;

import duke.commands.Command;
import duke.directprocessor.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void addTodoTest() {
        try {
            Command c = Parser.parse("todo Eat dinner");
            assertEquals("duke.commands.AddCommand", c.getClass().getName());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void addEventTest() {
        try {
            Command c = Parser.parse("event Eat dinner /at 19/08/2019 18:00:00");
            assertEquals("duke.commands.AddCommand", c.getClass().getName());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void addDeadlineTest() {
        try {
            Command c = Parser.parse("deadline Eat dinner /by 19/08/2019 18:00:00");
            assertEquals("duke.commands.AddCommand", c.getClass().getName());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void finishTest() {
        try {
            Command c = Parser.parse("done 2");
            assertEquals("duke.commands.FinishCommand", c.getClass().getName());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void listTest() {
        try {
            Command c = Parser.parse("list");
            assertEquals("duke.commands.ListCommand", c.getClass().getName());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }

    @Test
    public void exitTest() {
        try {
            Command c = Parser.parse("bye");
            assertEquals("duke.commands.ExitCommand", c.getClass().getName());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, 2);
        }
    }
}
