package duke.dukeCommand;

import duke.DukeException;
import duke.dukeHelper.Storage;
import duke.dukeHelper.Ui;
import duke.dukeTask.Task;
import duke.dukeTask.TaskList;
import duke.dukeTask.Todo;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DoneCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String separationLine = "    ____________________________________________________________";

    @Test
    public void executeDone_validInput_success() {
        System.setOut(new PrintStream(outContent));
        ArrayList<Task> temp = new ArrayList<>() {{
            add(new Todo("doneTest", 0));
        }};
        String expected = separationLine + "\n     Nice! I've marked this task as done:\n       [T][+] doneTest"
                + "\n" + separationLine + "\n" + System.lineSeparator();
        new DoneCommand("", new String[]{"done", "1"})
                .execute(new TaskList(temp), new Ui(), new Storage(""));
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void executeDone_noTaskNumber_exceptionThrown() {
        try {
            System.setOut(new PrintStream(outContent));
            new DoneCommand("", new String[]{"done"})
                    .execute(new TaskList(), new Ui(), new Storage(""));
            fail();
        } catch (DukeException de) {
            String correctExpected = separationLine
                    + "\n     \u2639 OOPS!!! Please specify number of a single task to mark as done.\n"
                    + separationLine + "\n";
            assertEquals(correctExpected, de.getMessage());
        }
    }

    @Test
    public void executeDone_itemNotInt_noOutputOutsideExecute() {
        System.setOut(new PrintStream(outContent));
        new DoneCommand("", new String[]{"done", "first item"})
                .execute(new TaskList(), new Ui(), new Storage(""));
        assertEquals("", outContent.toString());
    }

    @Test
    public void executeDone_intBeyondList_exceptionThrown() {
        try {
            System.setOut(new PrintStream(outContent));
            new DoneCommand("", new String[]{"done", "100"})
                    .execute(new TaskList(), new Ui(), new Storage(""));
            fail();
        } catch (DukeException de) {
            String correctExpected = separationLine + "\n     \u2639 OOPS!!! Please specify valid task number.\n"
                    + separationLine + "\n";
            assertEquals(correctExpected, de.getMessage());
        }
    }
}
