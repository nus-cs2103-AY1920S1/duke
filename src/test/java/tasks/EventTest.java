package tasks;

import duke.tasks.Event;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event event;

    EventTest(){
        this.event = new Event("description", Task.dateTimeConversion("2/12/2019 1800"));
    }

    @Test
    void save_taskObject_formattedTaskString(){
        assertEquals("E|0|description|02/12/2019 1800", event.save());
    }

    @Test
    void toString_taskObject_formattedTaskString() {
        assertEquals("[E][✘] description(at: 02 Dec 2019 06:00 PM)", event.toString());
    }
}
