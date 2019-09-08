import duke.command.DoneCommand;
import duke.model.Task;
import duke.model.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DoneCommandTest extends CommandTest {

    @Test
    void execute_validParams_taskMarkedAsDone() {
        List<Task> actualTasks = mutableTaskListOf(
                new Todo("initially not done todo", false)
        );
        List<Task> expectedTasks = List.of(
                new Todo("initially not done todo", true)
        );

        command = new DoneCommand("1");
        command.execute(actualTasks, ui, store);

        Assertions.assertEquals(expectedTasks, actualTasks);
        assertFileContents("T\u001F1\u001Finitially not done todo");
        assertStdOutContents("\t____________________________________________________________\n"
                + "\tNice! I've marked this task as done:\n"
                + "\t  [T][✓] initially not done todo\n"
                + "\t____________________________________________________________\n");
        assertExit(false);
    }

    @Test
    void execute_missingTaskNumber_exceptionThrown() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            new DoneCommand("");
        });
    }

    @Test
    void execute_nonNumericTaskNumber_exceptionThrown() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            new DoneCommand("garbage input");
        });
    }

    @Test
    void execute_noTaskForGivenTaskNumber_exceptionThrown() {
        command = new DoneCommand("1");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            command.execute(mutableTaskListOf(), ui, store);
        });
    }

    @Test
    void execute_outOfRangeTaskNumber_exceptionThrown() {
        command = new DoneCommand("0");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            command.execute(mutableTaskListOf(), ui, store);
        });
    }

    @Test
    void execute_negativeTaskNumber_exceptionThrown() {
        command = new DoneCommand("-1");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            command.execute(mutableTaskListOf(), ui, store);
        });
    }
}