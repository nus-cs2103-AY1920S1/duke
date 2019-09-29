import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for testing TaskList class.
 */
public class TaskListTest {
    private Storage storage;
    private Ui ui;
    private TaskList listOfTasks;

    /**
     * Tests addTaskTest method of TaskList class.
     */
    @Test
    public void addTaskTest() {
        listOfTasks = new TaskList();
        listOfTasks.addTask(new Todo("todo buy bread"));
        assertEquals("todo buy bread", listOfTasks.getListOfTasks().get(0).getDescription());
        assertEquals(1, listOfTasks.getSize());
    }

    /**
     * Tests removeTask method of TaskList class.
     */
    @Test
    public void removeTaskTest() throws DukeException {
        listOfTasks = new TaskList();
        listOfTasks.addTask(new Todo("todo buy bread"));
        listOfTasks.addTask(new Todo("todo buy butter"));
        listOfTasks.removeTask(1);
        assertEquals(1, listOfTasks.getSize());
        assertEquals("todo buy bread", listOfTasks.getListOfTasks().get(0).getDescription());
    }
}