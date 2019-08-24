import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DeleteCommandTest extends CommandTest {
    @Test
    void doValidDelete() {
        List<Task> expectedTasks = List.of(new Todo("a thing that needs to be done", false));
        List<Task> actualTasks = mutableTaskListOf(
                new Todo("a todo to be deleted", false),
                new Todo("a thing that needs to be done", false)
        );

        String expectedFileContents = "T\u001F0\u001Fa thing that needs to be done";

        String expectedSysOut = "\t____________________________________________________________\n"
                + "\tNoted. I've removed this task:\n"
                + "\t  [T][✘] a todo to be deleted\n"
                + "\tNow you have 1 tasks in the list.\n"
                + "\t____________________________________________________________\n";

        command = new DeleteCommand("1");
        command.execute(actualTasks, ui, store);

        Assertions.assertEquals(expectedTasks, actualTasks);
        assertFileContents(expectedFileContents);
        assertStdOutContents(expectedSysOut);
        assertExit(false);
    }

    @Test
    void doDeleteMissingIndex() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            new DeleteCommand("");
        });
    }

    @Test
    void doDeleteNonNumericIndex() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            new DeleteCommand("garbage input");
        });
    }

    @Test
    void doDeleteInvalidIndex() {
        command = new DeleteCommand("1");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            command.execute(mutableTaskListOf(), ui, store);
        });
    }

    @Test
    void doDeleteOutOfRangeIndex() {
        command = new DeleteCommand("0");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            command.execute(mutableTaskListOf(), ui, store);
        });
    }

    @Test
    void doDeleteOutOfNegativeIndex() {
        command = new DeleteCommand("-1");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            command.execute(mutableTaskListOf(), ui, store);
        });
    }
}