package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for testing Task class.
 */
class TaskTest {

    Task task;

    /**
     * Tests getCompletedStatus method of Task class.
     */
    @Test
    void getCompletedStatusTest() {
        this.task = new Task("read book");
        assertEquals(false, task.isCompleted());
        this.task = new Task("read book", true);
        assertEquals(true, task.isCompleted());
    }

    /**
     * Tests changeToCompletedStatus method of Task class.
     */
    @Test
    void changeToCompletedStatusTest() {
        this.task = (new Task("read book")).changeToCompletedStatus();
        assertEquals(true, task.isCompleted());
    }

    /**
     * Tests getDescription method of Task class.
     */
    @Test
    void getDescriptionTest() {
        this.task = new Task("buy bread");
        assertEquals("buy bread", task.getDescription());
    }

    /**
     * Tests toIndicationInsideFile method of Task class.
     */
    @Test
    void toIndicationInsideFileTest() {
        this.task = new Task("buy bread");
        String expected = "T | 0 | buy bread";
        assertEquals(expected, task.toIndicationInsideFile());
    }
}