package duke.command;

import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String separationLine = "    ____________________________________________________________";

    @Test
    public void executeFind() {
        System.setOut(new PrintStream(outContent));
        ArrayList<Task> temp = new ArrayList<>() {
            {
                add(new Todo("todo list test", 0));
                add(new Deadline("deadline list test", 1, "tonight"));
                add(new Deadline("deadline list t3st", 0, "whenever"));
                add(new Event("event list test", 0, "the weekend"));
                add(new Todo("todo list t3st", 0));
            }
        };
        String expected = separationLine + "\n     Here are the matching tasks in your list:" + System.lineSeparator()
                + "     1.[T][ ] todo list test" + System.lineSeparator()
                + "     2.[D][+] deadline list test (by: tonight)" + System.lineSeparator()
                + "     3.[E][ ] event list test (at: the weekend)" + System.lineSeparator()
                + separationLine + "\n" + System.lineSeparator();
        new FindCommand("", null, "test").execute(new TaskList(temp), new Ui(), new Storage(""));
        assertEquals(expected, outContent.toString());
    }
}
