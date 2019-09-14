package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Task;
import duke.task.TaskEnum;
import duke.task.TaskMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TaskListTest {

    @Test
    void getTaskList_emptyTaskList_emptyTaskListReturned() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        assertEquals(new ArrayList<Task>(), taskList.getTaskList());
    }

    @Test
    void add_taskListWithOneTask_similarTaskReturned() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);

        assertEquals(new TaskMock("").getDescription(),
            taskList.add("description", TaskEnum.TODO).getDescription());
    }

    @Test
    void delete_taskListWithOneTask_deletedTaskDescriptionReturned() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskMock taskMock = new TaskMock("");
        tasks.add(taskMock);
        TaskList taskList = new TaskList(tasks);

        assertEquals(taskMock.getDescription(),
            taskList.delete(1).getDescription());
    }

    @Test
    void done_taskListWithOneTask_checkedTaskDescriptionReturned() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskMock taskMock = new TaskMock("");
        tasks.add(taskMock);
        TaskList taskList = new TaskList(tasks);

        assertEquals(taskMock.getDescription(),
            taskList.delete(1).getDescription());
    }

    @Test
    void list_taskListWithOneTask_tasksStringsReturned() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskMock taskMock = new TaskMock("");
        tasks.add(taskMock);
        TaskList taskList = new TaskList(tasks);
        assertEquals("Here are the tasks in your list:\n1.[\u2713] description",
            taskList.list());
    }
}