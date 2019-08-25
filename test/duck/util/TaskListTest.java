package duck.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static duck.util.ObjectsForTest.*;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private TaskList list1;
    private TaskList list2;

    @Test
    void testEquals_sameContent_equals() {
        list1 = new TaskList(Arrays.asList(tasks));
        list2 = new TaskList(Arrays.asList(tasks));
        assertEquals(list1, list2);
    }

    @Test
    void testEquals_differentLength_notEquals() {
        list1 = new TaskList(Arrays.asList(tasks));
        list2 = new TaskList();
        list2.add(todo);
        list2.add(deadline);
        assertNotEquals(list1, list2);
    }

    @Test
    void testEquals_differentContent_notEquals() {
        list1 = new TaskList(Arrays.asList(tasks));
        list2 = new TaskList();
        list2.add(todo);
        list2.add(event);
        list2.add(deadline);
    }
}