import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

class DoneCommandTest extends CommandTest {

    @Test
    void doValidMarkDone() {
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
    void doMarkDoneMissingIndex() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            new DoneCommand("");
        });
    }

    @Test
    @Disabled
    void doMarkDoneNonNumericIndex() {
        command = new DoneCommand("garbage input");
        Assertions.assertThrows(NumberFormatException.class, () -> {
            command.execute(mutableTaskListOf(), ui, store);
        });
    }

    @Test
    void doMarkDoneInvalidIndex() {
        command = new DoneCommand("1");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            command.execute(mutableTaskListOf(), ui, store);
        });
    }

    @Test
    void doMarkDoneOutOfRangeIndex() {
        command = new DoneCommand("0");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            command.execute(mutableTaskListOf(), ui, store);
        });
    }

    @Test
    void doMarkDoneOutOfNegativeIndex() {
        command = new DoneCommand("-1");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            command.execute(mutableTaskListOf(), ui, store);
        });
    }
}