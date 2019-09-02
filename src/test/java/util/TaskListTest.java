package util;

import org.junit.jupiter.api.Test;
import tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskListTest {
    @Test
    void isEmptyTest(){
        TaskList emptyList = new TaskList();
        int listSize = emptyList.size();
        assertEquals(0, listSize);
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void addTest() {
        TaskList testList = new TaskList();
        boolean isAdded = testList.add(new Todo("dummy", false));
        assertTrue(isAdded);
    }
}
