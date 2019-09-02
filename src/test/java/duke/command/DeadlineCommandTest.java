package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {


    @Test
    public void testIsExit() {
        assertEquals(false, new DeadlineCommand("deadline test".split("\\s+")).isExit());
        assertEquals(false, new DeadlineCommand("deadline test /by ".split("\\s+")).isExit());
        assertEquals(false, new DeadlineCommand("deadline test /by 20/12/2019 1800".split("\\s+"))
                .isExit());
    }

    @Test
    public void testExecute() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/dukeTest.txt");

        assertEquals(Messages.DATETIME_PARSE_EXCEPTION, new DeadlineCommand("deadline test /by 20/12/2019"
                .split("\\s+"))
                .execute(new TaskList(), storage));



        LocalDateTime dateTime = LocalDateTime.parse("20/12/2019 1800", formatter);
        Task deadlineTask = new Deadline("test", dateTime);
        assertEquals(String.join("\n", Messages.ADDED_TASK_MESSAGE,
                Messages.COMMAND_INDENTATION + Messages.COMPLETION_INDENTATION + deadlineTask.toString(),
                String.format(Messages.LIST_SIZE_FORMAT, 1)),
                new DeadlineCommand("deadline test /by 20/12/2019 1800".split("\\s+"))
                .execute(new TaskList(), storage));



    }
}
