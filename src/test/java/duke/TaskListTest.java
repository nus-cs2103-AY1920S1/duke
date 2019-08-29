package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import duke.task.ToDo;

class TaskListTest {

    TaskList tasks = new TaskList();

    @Test
    void taskListAdd() {
        tasks.add(new ToDo("I love testing"));
        assertEquals(1, tasks.getSize());
    }

    @Test
    void taskListRemove() {
        tasks.add(new ToDo("I will remove this test"));
        assertEquals(1, tasks.getSize());
        tasks.remove(1);
        assertEquals(0, tasks.getSize());
    }
}
