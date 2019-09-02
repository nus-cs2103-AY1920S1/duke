package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {
    private TaskList taskList1;
    private Storage storage;


    @Test
    public void testIsExit() {
        String[] arr = "find another".split("\\s+");
        assertEquals(false, new FindCommand(Arrays.copyOfRange(arr, 1, arr.length)).isExit());
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
        String[] arr1 = "find another".split("\\s+");


        assertEquals(String.join("\n", Messages.FIND_TASK_MESSAGE, Messages.COMMAND_INDENTATION
                + "1." + task2), new FindCommand(Arrays.copyOfRange(arr1, 1, arr1.length))
                .execute(taskList1, storage));
    }
}
