import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    // Create dummy TaskList object with some Tasks
    private TaskList createDummyTaskList() {
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.add(new ToDo("Cook lunch"));
        allTasks.add(new Event("Eat lunch ", " 1pm-2pm"));
        allTasks.add(new Deadline("Wash dishes ", " 02 Dec 2019, 18:00 PM"));

        TaskList tl = new TaskList(allTasks);
        return tl;
    }

    @Test
    void testAddTask() {
        // Check if the number of tasks increased by 1.
        TaskList taskList = createDummyTaskList();
        assertEquals(3, taskList.numTasks);
        taskList.addTask(new Task("random task"));
        assertEquals(4, taskList.numTasks);
    }

    @Test
    void testMarkAsDone() {
        // Check if the new task added is marked as done.
        TaskList taskList = new TaskList();
        Task dummyTask = new ToDo("random task");
        taskList.addTask(dummyTask);
        taskList.markAsDone(1);
        assertEquals(true, dummyTask.isDone());
    }

    @Test
    void deleteTask() {
        // Check that number of tasks decreased by 1.
        TaskList taskList = createDummyTaskList();
        int originalLength = taskList.numTasks;
        taskList.deleteTask(0);
        assertEquals(originalLength - 1, taskList.numTasks);
    }

    @Test
    void searchFor() {
        TaskList taskList = createDummyTaskList();
        TaskList filtered = taskList.searchFor("Cook");
        assertEquals(1, filtered.numTasks);
    }

    @Test
    void testToString() {
        TaskList taskList = createDummyTaskList();
        assertEquals("[T][ ] Cook lunch"
                + System.lineSeparator()
                + "[E][ ] Eat lunch (at: 1pm-2pm)"
                + System.lineSeparator()
                + "[D][ ] Wash dishes (by: 02 Dec 2019, 18:00 PM)"
                + System.lineSeparator(),
                taskList.toString());
    }
}