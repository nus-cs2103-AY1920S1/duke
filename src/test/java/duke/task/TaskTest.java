package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getStatus() {
        assertEquals(0, new Task("").getStatus());
    }
}