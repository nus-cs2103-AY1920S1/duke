import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.ToDoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskList;

    /**
     * Constructor.
     */
    public TaskListTest() {
        taskList = new TaskList();
        taskList.add(new ToDoTask("Say hi"));
        taskList.add(new DeadlineTask("task", "1/1/1991 1234"));
        taskList.add(new EventTask("sale", "1/1/1991 1234"));
    }

    @Test
    public void testTaskListSize() {
        assertEquals(3, taskList.size());
    }

    @Test
    public void testGetTaskList() {
        assertEquals("[D][Not done] task (by: 1/1/1991 1234)", taskList.get(1).toString());
    }
}
