package duke.tasklist;

import duke.tasks.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void add_todo_correctly() {
        ToDo td = new ToDo("mock task");
        String result = taskList.addTask(td);
        assertEquals(td.toString(), result);
    }
}