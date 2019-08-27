package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testToString() {
        Task task1 = new Task("walk the dog");
        Task task2 = new Task("empty trash", true);
        assertEquals("[\u2718] walk the dog", task1.toString());
        assertEquals("[\u2713] empty trash", task2.toString());
    }
}
