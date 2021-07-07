import cs2103t.duke.task.TaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTypeTest {
    @Test
    public void convertToTaskType() {
        assertEquals(TaskType.T, TaskType.convertToTaskType("T"));
        assertEquals(TaskType.T, TaskType.convertToTaskType("Todo"));
        assertEquals(TaskType.D, TaskType.convertToTaskType("D"));
        assertEquals(TaskType.D, TaskType.convertToTaskType("Deadline"));
        assertEquals(TaskType.E, TaskType.convertToTaskType("E"));
        assertEquals(TaskType.E, TaskType.convertToTaskType("Event"));
        assertEquals(TaskType.LIST, TaskType.convertToTaskType("list"));
        assertEquals(TaskType.DONE, TaskType.convertToTaskType("done"));
        assertEquals(TaskType.DELETE, TaskType.convertToTaskType("delete"));
        assertEquals(TaskType.EXIT, TaskType.convertToTaskType("exit"));
        assertEquals(TaskType.EXIT, TaskType.convertToTaskType("bye"));

        assertEquals(null, TaskType.convertToTaskType("blah"));
        assertEquals(null, TaskType.convertToTaskType(""));
    }

}
