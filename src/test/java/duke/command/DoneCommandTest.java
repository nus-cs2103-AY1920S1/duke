package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.PastOperationList;
import duke.task.Task;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    private TaskList taskList1;
    private Storage storage;
    private PastOperationList pastOperationList;
    private static final String DATETIME_PATTERN = "dd/MM/yyyy HHmm";



    @Test
    public void testIsExit() {
        assertEquals(false, new DoneCommand(1).isExit());
        assertEquals(false, new DoneCommand(10).isExit());
    }

    @Test
    public void testExecute() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
        LocalDateTime dateTime = LocalDateTime.parse("08/09/2019 1130", formatter);


        List<Task> taskList = new ArrayList<>();
        Task task1 = new Deadline("submit", dateTime, false);
        Task task2 = new Deadline("submit another", dateTime);
        taskList.add(task1);
        taskList.add(task2);
        taskList1 = new TaskList(taskList);
        storage = new Storage("data/dukeTest.txt");
        pastOperationList = new PastOperationList();

        assertEquals(Messages.INVALID_SIZE_EXCEPTION, new DoneCommand(10)
                .execute(taskList1, storage, pastOperationList));
        assertEquals(String.join("\n", Messages.DONE_MESSAGE, Messages.COMMAND_INDENTATION
                + Messages.COMPLETION_INDENTATION + "[D][O] submit (by: 08 September 2019, 11:30 AM)"),
                new DoneCommand(1).execute(taskList1, storage, pastOperationList));
    }
}
