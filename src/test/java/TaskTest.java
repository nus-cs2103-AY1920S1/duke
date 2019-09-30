import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Task;


public class TaskTest {

    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void test_markAsDone() {
        assertEquals("Nice! I've marked this task as done:\n  [v] Test Task 1",
                new Task("T","Test Task 1").markAsDone());

    }

    @Test
    public void testToString() {
        assertEquals("[T][x] Test Task 3 ", new Task("T", "Test Task 3").toString());
    }

}
