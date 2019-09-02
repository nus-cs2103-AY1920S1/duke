package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    private TaskList taskList1;
    private Storage storage;


    @Test
    public void testIsExit() {
        assertEquals(false, new DoneCommand(1).isExit());
        assertEquals(false, new DoneCommand(10).isExit());
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

        assertEquals(Messages.INVALID_SIZE_EXCEPTION, new DoneCommand(10).execute(taskList1, storage));
        task2.completeTask();
        assertEquals(String.join("\n", Messages.DONE_MESSAGE, Messages.COMMAND_INDENTATION
                + Messages.COMPLETION_INDENTATION + task2.toString()),
                new DoneCommand(2).execute(taskList1, storage));
    }
}
