package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    private TaskList taskList1;
    private Ui ui;
    private Storage storage;


    @Test
    public void testIsExit() {
        assertEquals(false, new DeleteCommand(1).isExit());
        assertEquals(false, new DeleteCommand(10).isExit());
    }

    @Test
    public void testExecute() {
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Deadline("submit", LocalDateTime.now(), false);
        Task task2 = new Deadline("submit another", LocalDateTime.now());
        taskList.add(task1);
        taskList.add(task2);
        taskList1 = new TaskList(taskList);
        ui = new Ui();
        storage = new Storage("data/duke.txt");

        assertEquals(false, new DeleteCommand(10).execute(taskList1, ui, storage));
        assertEquals(true, new DeleteCommand(2).execute(taskList1, ui, storage));
    }
}
