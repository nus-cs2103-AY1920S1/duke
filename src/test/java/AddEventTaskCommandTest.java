import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

class AddEventTaskCommandTest extends CommandTest {
    @Test
    void addValidEvent() {
        List<Task> expectedTasks = List.of(
                new Event("an event to attend", false, LocalDateTime.of(2019, 8, 6, 14, 0))
        );
        List<Task> actualTasks = mutableTaskListOf();

        final String expectedFileContents = "E\u001F0\u001Fan event to attend\u001F6/8/2019 1400";

        final String expectedSysOut = "\t____________________________________________________________\n"
                + "\tGot it. I've added this task: \n"
                + "\t  [E][✘] an event to attend (at: 6/8/2019 1400)\n"
                + "\tNow you have 1 tasks in the list.\n"
                + "\t____________________________________________________________\n\n";

        command = new AddEventTaskCommand("an event to attend /at 6/8/2019 1400");
        command.execute(actualTasks, ui, store);

        Assertions.assertEquals(expectedTasks, actualTasks);
        assertFileContents(expectedFileContents);
        assertStdOutContents(expectedSysOut);
        assertExit(false);
    }

    @Test
    void addEventMissingArgs() {
        Assertions.assertThrows(EmptyTaskDescriptionException.class, () -> {
            new AddEventTaskCommand("");
        });
    }

    @Test
    void addEventMissingDescription() {
        Assertions.assertThrows(EmptyTaskDescriptionException.class, () -> {
            new AddEventTaskCommand("/at 6/8/2019 1400");
        });
    }

    @Test
    void addEventMissingDateTime() {
        Assertions.assertThrows(Throwable.class, () -> {
            new AddEventTaskCommand("an event to attend");
        });
    }

    @Test
    void addEventBlankDateTime() {
        Assertions.assertThrows(Throwable.class, () -> {
            new AddEventTaskCommand("an event to attend /at");
        });
    }

    @Test
    void addEventUnexpectedDateFormat() {
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            new AddEventTaskCommand("an event to attend /at 12/31/2019 1400");
        });
    }

}