import duke.command.AddDeadlineTaskCommand;
import duke.command.EmptyTaskDescriptionException;
import duke.model.Deadline;
import duke.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

class AddDeadlineTaskCommandTest extends CommandTest {
    @Test
    void execute_validParams_newDeadlineAdded() {
        List<Task> expectedTasks = List.of(
                new Deadline("a deadline to meet", false, LocalDateTime.of(2019, 6, 6, 0, 0))
        );
        List<Task> actualTasks = mutableTaskListOf();

        final String expectedFileContents = "D\u001F0\u001Fa deadline to meet\u001F6/6/2019 0000";

        final String expectedSysOut = "\t____________________________________________________________\n"
                + "\tGot it. I've added this task: \n"
                + "\t  [D][✘] a deadline to meet (by: 6/6/2019 0000)\n"
                + "\tNow you have 1 tasks in the list.\n"
                + "\t____________________________________________________________\n\n";

        command = new AddDeadlineTaskCommand("a deadline to meet /by 6/6/2019 0000");
        command.execute(actualTasks, ui, store);

        Assertions.assertEquals(expectedTasks, actualTasks);
        assertFileContents(expectedFileContents);
        assertStdOutContents(expectedSysOut);
        assertExit(false);
    }


    @Test
    void execute_missingParams_exceptionThrown() {
        Assertions.assertThrows(EmptyTaskDescriptionException.class, () -> {
            new AddDeadlineTaskCommand("");
        });
    }

    @Test
    void execute_missingDescription_exceptionThrown() {
        Assertions.assertThrows(EmptyTaskDescriptionException.class, () -> {
            new AddDeadlineTaskCommand("/by 6/6/2019 0000");
        });
    }

    @Test
    void execute_missingDateTime_exceptionThrown() {
        Assertions.assertThrows(Throwable.class, () -> {
            new AddDeadlineTaskCommand("a deadline to meet");
        });
    }

    @Test
    void execute_blankDateTime_exceptionThrown() {
        Assertions.assertThrows(Throwable.class, () -> {
            new AddDeadlineTaskCommand("a deadline to meet /by");
        });
    }

    @Test
    void execute_incorrectDateTimeFormat_exceptionThrown() {
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            new AddDeadlineTaskCommand("a deadline to meet /by 12/31/2019 0000");
        });
    }
}