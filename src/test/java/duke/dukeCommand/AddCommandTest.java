package duke.dukeCommand;

import duke.DukeException;
import duke.dukeHelper.Storage;
import duke.dukeHelper.Ui;
import duke.dukeTask.TaskList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String separationLine = "    ____________________________________________________________";

    @Test
    public void executeAdd_todo_success() throws DukeException {
        System.setOut(new PrintStream(outContent));
        // Different systems use different lineSeparator (with println)
        String expected = separationLine + "\n     Got it. I've added this task:\n       [T][ ] home work\n     "
                + "Now you have 1 tasks in the list.\n" + separationLine + "\n" + System.lineSeparator();
        new AddCommand("todo", "todo home work", new String[]{"todo", "home work"}, "")
                .execute(new TaskList(), new Ui(), new Storage(""));
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void executeAdd_deadline_success() throws DukeException {
        System.setOut(new PrintStream(outContent));
        String expected = separationLine + "\n     Got it. I've added this task:\n       [D][ ] home work "
                + "(by: 20 Oct 2019, 06:00 PM)\n     Now you have 1 tasks in the list.\n" + separationLine + "\n"
                + System.lineSeparator();
        new AddCommand("deadline", "deadline home work /by 20/10/2019 1800",
                new String[]{"deadline", "home", "work", "/by", "20/10/2019", "1800"}, "")
                .execute(new TaskList(), new Ui(), new Storage(""));
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void executeAdd_event_success() throws DukeException {
        System.setOut(new PrintStream(outContent));
        String expected = separationLine + "\n     Got it. I've added this task:\n       [E][ ] Birthday party "
                + "(at: 21 Nov 2019, 06:45 PM)\n     Now you have 1 tasks in the list.\n" + separationLine + "\n"
                + System.lineSeparator();
        new AddCommand("event", "event Birthday party /at 21.11.2019 0645 PM",
                new String[]{"event", "Birthday", "party"}, "")
                .execute(new TaskList(), new Ui(), new Storage(""));
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void executeAdd_todoEmptyDescription_exceptionThrown() {
        try {
            System.setOut(new PrintStream(outContent));
            new AddCommand("todo", "todo", new String[]{"todo"}, "")
                    .execute(new TaskList(), new Ui(), new Storage(""));
            String wrongExpected = separationLine + "\n     Got it. I've added this task:\n       [T][ ] \n     "
                    + "Now you have 1 tasks in the list.\n" + separationLine + "\n" + System.lineSeparator();
            assertEquals(wrongExpected, outContent.toString());
            fail();
        } catch (DukeException de) {
            String correctExpected = separationLine + "\n     \u2639 OOPS!!! The description of a todo cannot be empty."
                    + "\n" + separationLine + "\n";
            assertEquals(correctExpected, de.getMessage());
        }
    }

    @Test
    public void executeAdd_deadlineWrongFormat_exceptionThrown() {
        try {
            System.setOut(new PrintStream(outContent));
            new AddCommand("deadline", "deadline WORK/by 20/10/2019 1800",
                    new String[]{"deadline", "WORK/by", "20/10/2019", "1800"}, "")
                    .execute(new TaskList(), new Ui(), new Storage(""));
            String wrongExpected = separationLine + "\n     Got it. I've added this task:\n       [D][ ] WORK "
                    + "(by: 20 Oct 2019, 06:00 PM)\n     Now you have 1 tasks in the list.\n" + separationLine + "\n";
            assertEquals(wrongExpected, outContent.toString());
            fail();
        } catch (DukeException de) {
            String correctExpected = separationLine + "\n     \u2639 OOPS!!! For deadline please use the format\n"
                    + "               \"deadline description /by end time\"\n" + separationLine + "\n";
            assertEquals(correctExpected, de.getMessage());
        }
    }

    @Test
    public void executeAdd_eventWrongFormat_exceptionThrown() {
        try {
            System.setOut(new PrintStream(outContent));
            new AddCommand("event", "event Celebration /at21/11/2019 0645 PM",
                    new String[]{"event", "Celebration"}, "")
                    .execute(new TaskList(), new Ui(), new Storage(""));
            String wrongExpected = separationLine + "\n     Got it. I've added this task:\n       [E][ ] Birthday party"
                    + " (at: 21 Nov 2019, 06:45 PM)\n     Now you have 1 tasks in the list.\n" + separationLine + "\n"
                    + System.lineSeparator();
            assertEquals(wrongExpected, outContent.toString());
            fail();
        } catch (DukeException de) {
            String correctExpected = separationLine + "\n     \u2639 OOPS!!! For event please use the format\n"
                    + "               \"event description /at period\"\n" + separationLine + "\n";
            assertEquals(correctExpected, de.getMessage());
        }
    }
}
