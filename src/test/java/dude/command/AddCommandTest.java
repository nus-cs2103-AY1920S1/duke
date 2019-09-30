package dude.command;

import dude.DudeException;
import dude.helper.Storage;
import dude.helper.Ui;
import dude.task.TaskList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void executeAdd_todoEmptyDescription_exceptionThrown() {
        try {
            System.setOut(new PrintStream(outContent));
            new AddCommand("todo", "todo", new String[]{"todo"}, "")
                    .execute(new TaskList(), new Ui(null, null), new Storage("", null));
            fail();
        } catch (DudeException de) {
            String correctExpected = ":( OOPS!!! The description of a todo cannot be empty.\n";
            assertEquals(correctExpected, de.getMessage());
        }
    }

    @Test
    public void executeAdd_deadlineWrongFormat_exceptionThrown() {
        try {
            System.setOut(new PrintStream(outContent));
            new AddCommand("deadline", "deadline WORK/by 20/10/2019 1800",
                    new String[]{"deadline", "WORK/by", "20/10/2019", "1800"}, "")
                    .execute(new TaskList(), new Ui(null, null), new Storage("", null));
            fail();
        } catch (DudeException de) {
            String correctExpected = ":( OOPS!!! For deadline please use the format\n"
                    + "\"deadline description /by end time\"\n";
            assertEquals(correctExpected, de.getMessage());
        }
    }

    @Test
    public void executeAdd_eventWrongFormat_exceptionThrown() {
        try {
            System.setOut(new PrintStream(outContent));
            new AddCommand("event", "event Celebration /at21/11/2019 0645 PM",
                    new String[]{"event", "Celebration"}, "")
                    .execute(new TaskList(), new Ui(null, null), new Storage("", null));
            fail();
        } catch (DudeException de) {
            String correctExpected = ":( OOPS!!! For event please use the format\n"
                    + "\"event description /at period\"\n";
            assertEquals(correctExpected, de.getMessage());
        }
    }
}
