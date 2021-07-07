package duke;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testAddTodo() {
        try {
            assertEquals(new AddCommand("todo read book"), Parser.parse("todo read book"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAddDeadline() {
        try {
            assertEquals(new AddCommand("deadline submit report /by 11/10/2019 1700"),
                    Parser.parse("deadline submit report /by 11/10/2019 1700"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAddEvent() {
        try {
            assertEquals(new AddCommand("event team project meeting on 2/10/2019 1400"),
                    Parser.parse("event team project meeting on 2/10/2019 1400"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDelete() {
        try {
            assertEquals(new DeleteCommand(1), Parser.parse("delete 1"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDone() {
        try {
            assertEquals(new DoneCommand(2), Parser.parse("done 2"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testList() {
        try {
            assertEquals(new ListCommand(), Parser.parse("list"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testBye() {
        try {
            assertEquals(new ExitCommand(), Parser.parse("bye"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
