package duke.task;

import duke.command.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Event class
 */
class EventTest {
    /**
     * Tests if Event.toString outputs the correct String
     */
    @Test
    public void eventDateDetection_validDate_success() {
        Event event = new Event("A new event", false, "by 12/10/2019 2100");
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Do this thing",  false, ""));
        assertEquals("[E][\u2718] A new event (at: 12th of October 2019, 9PM)", event.toString());
    }
}