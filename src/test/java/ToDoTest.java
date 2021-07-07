
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {
    @Test
    void dummyTest(){
        assertEquals(2, 2);
        ToDo test = new ToDo("Why","T","");
        String testType = test.getType();
        assertEquals("T", testType);
        String testStatus = test.getStatus();
        assertEquals("\u2718", testStatus);
        test.markDone();
        testStatus = test.getStatus();
        assertEquals("\u2713", testStatus);
        String info = test.getTaskInfo();
        assertEquals("Why", info);
        String by = test.getBy();
        assertEquals("", by);

    }
    static void main(String[] args) {
    }
}