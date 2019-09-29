package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskListTest {

    //group of tests dependent on their order of execution that must be grouped
    //tests various methods of the TaskList abstraction
    @Test
    void dependentTaskOperationstest() {
        assertAll("Task List Operations",
                () -> {
                    TaskList taskList = new TaskList();

                    assertEquals(0, taskList.getSize());
                    assertThrows(IndexOutOfBoundsException.class,
                            () -> taskList.getTaskByIndex(0));
                    assertThrows(IndexOutOfBoundsException.class,
                            () -> taskList.deleteTaskByIndex(0));

                    taskList.addTask(new TaskStub());
                    assertEquals(1, taskList.getSize());
                    assertEquals(1, taskList.getAllTasks().size());
                    taskList.getTaskByIndex(0);
                    taskList.deleteTaskByIndex(0);

                    assertEquals(0, taskList.getSize());
                    assertThrows(IndexOutOfBoundsException.class,
                            () -> taskList.getTaskByIndex(0));
                    assertThrows(IndexOutOfBoundsException.class,
                            () -> taskList.deleteTaskByIndex(0));
                });
    }
}
