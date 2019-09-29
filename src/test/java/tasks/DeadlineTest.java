package tasks;

import duke.tasks.Deadline;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    private Deadline deadline;

    DeadlineTest(){
        this.deadline = new Deadline("return book", Task.dateTimeConversion("2/12/2019 1800"));
    }

    @Test
    void save_taskObject_formattedTaskString(){
        assertEquals("D|0|return book|02/12/2019 1800", deadline.save());
    }

    @Test
    void toString_taskObject_formattedTaskString() {
        assertEquals("[D][✘] return book(by: 02 Dec 2019 06:00 PM)", deadline.toString());
    }
}