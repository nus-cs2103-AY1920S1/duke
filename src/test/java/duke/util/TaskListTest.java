package duke.util;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void add() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("Test"));
    }

    @Test
    void delete() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("Test"));
        taskList.delete(1);
        assertEquals(0, taskList.listSize());
    }

    @Test
    void getByIndex() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("Test"));
        taskList.getByIndex(0);
    }

    @Test
    void listSize() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("Test"));
        assertEquals(1, taskList.listSize());
    }
}