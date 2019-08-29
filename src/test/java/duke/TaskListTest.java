package duke;

import duke.task.Task;
import duke.task.TaskImplStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    void getTasks() {
        assertEquals(new ArrayList<Task>(), taskList.getTasks());
    }

    @Test
    void add() {
        assertEquals(0, taskList.getTasks().size());
        taskList.add(new TaskImplStub("Test"));
        assertEquals(1, taskList.getTasks().size());
    }
}
