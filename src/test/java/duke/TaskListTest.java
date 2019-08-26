package duke;

import duke.task.Task;
import duke.task.TaskEnum;
import duke.task.TaskMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void getTaskList() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        assertEquals(new ArrayList<Task>(), taskList.getTaskList());
    }

    @Test
    void add() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);

        assertEquals(new TaskMock("").getDescription(),
                taskList.add("description", TaskEnum.TODO).getDescription());
    }

    @Test
    void delete() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskMock taskMock = new TaskMock("");
        tasks.add(taskMock);
        TaskList taskList = new TaskList(tasks);

        assertEquals(taskMock.getDescription(),
                taskList.delete(1).getDescription());
    }

    @Test
    void done() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskMock taskMock = new TaskMock("");
        tasks.add(taskMock);
        TaskList taskList = new TaskList(tasks);

        assertEquals(taskMock.getDescription(),
                taskList.delete(1).getDescription());
    }

    @Test
    void list() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskMock taskMock = new TaskMock("");
        tasks.add(taskMock);
        TaskList taskList = new TaskList(tasks);
        assertEquals("Here are the tasks in your list:\n1.[✓] description",
                taskList.list());
    }
}