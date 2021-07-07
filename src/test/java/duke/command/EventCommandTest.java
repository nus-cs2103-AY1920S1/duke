package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.PastOperationList;
import duke.task.Task;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventCommandTest {
    private TaskList taskList1;
    private Storage storage;
    private PastOperationList pastOperationList;


    @Test
    public void testIsExit() {
        assertEquals(false, new EventCommand("event a".split("\\s+")).isExit());
        assertEquals(false, new EventCommand("event a /at".split("\\s+")).isExit());
        assertEquals(false, new EventCommand("event a /at SG".split("\\s+")).isExit());
    }

    @Test
    public void testExecute() {
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Deadline("submit", LocalDateTime.now(), false);
        Task task2 = new Deadline("submit another", LocalDateTime.now());
        taskList.add(task1);
        taskList.add(task2);
        taskList1 = new TaskList(taskList);
        storage = new Storage("data/dukeTest.txt");
        pastOperationList = new PastOperationList();

        assertEquals(String.join("\n", Messages.ADDED_TASK_MESSAGE, Messages.COMMAND_INDENTATION
                + Messages.COMPLETION_INDENTATION + new Event("a", "SG", false),
                String.format(Messages.LIST_SIZE_FORMAT, 3)),
                new EventCommand("event a /at SG".split("\\s+")).execute(taskList1, storage, pastOperationList));
    }
}
