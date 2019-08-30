import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskTest {

    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void test_markAsDone() {
        assertEquals("Nice! I've marked this task as done:\n  [T] Test Task 1",
                new Task("T","Test Task 1").markAsDone());
        assertEquals("Nice! I've marked this task as done:\n [T] Test Task 2",
                new Task("T", "0", "Test Task 2").markAsDone());
    }

    @Test
    public void testToString() {
        assertEquals("[T][x] Test Task 3", new Task("T", "Test Task 3").toString());
        assertEquals("[T][v] Test Task 4", new Task("T", "1", "Test Task 4").toString());
    }

}
