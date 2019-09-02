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

public class ListCommandTest {
    private TaskList taskList1;
    private Storage storage;


    @Test
    public void testIsExit() {
        assertEquals(false, new ListCommand().isExit());
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

        assertEquals(String.join("\n", Messages.LIST_MESSAGE,
                Messages.COMMAND_INDENTATION + "1." + task1 + "\n" + Messages.COMMAND_INDENTATION + "2." + task2),
                new ListCommand().execute(taskList1, storage));
    }
}
