package task;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import static duke.util.ObjectsForTest.*;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void from_todoTaskStr() {
        assertEquals(todo, Task.from(todoTaskInfo));
    }

    @Test
    void from_deadlineTaskStr() {
        assertEquals(deadline, Task.from(deadlineTaskInfo));
    }

    @Test
    void from_eventTaskStr() {
        assertEquals(event, Task.from(eventTaskInfo));
    }
}