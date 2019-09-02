package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ToDoTest {
    @Test
    void todoToStringTest() {
        Todo testTask = new Todo("eat banana", false);
        String testTaskDescription = testTask.toString();
        assertEquals("T-N-eat banana", testTaskDescription);
    }

    @Test
    void todoIsDoneTest() {
        Todo toBeDone = new Todo("run in circles", false);
        boolean isTestTaskDone = toBeDone.setTaskAsDone(true);
        assertTrue(isTestTaskDone);

        isTestTaskDone = toBeDone.setTaskAsDone(false);
        assertFalse(isTestTaskDone);
    }
}
