package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Task task;

    @Test
    void getCompletedStatusTest() {
        this.task = new Task("read book");
        assertEquals(false, task.isCompleted());
        this.task = new Task("read book", true);
        assertEquals(true, task.isCompleted());
    }

    @Test
    void changeToCompletedStatusTest() {
        this.task = (new Task("read book")).changeToCompletedStatus();
        assertEquals(true, task.isCompleted());
    }

    @Test
    void getDescriptionTest() {
        this.task = new Task("buy bread");
        assertEquals("buy bread", task.getDescription());
    }

    @Test
    void toIndicationInsideFileTest() {
        this.task = new Task("buy bread");
        String expected = "T | 0 | buy bread";
        assertEquals(expected, task.toIndicationInsideFile());
    }
}