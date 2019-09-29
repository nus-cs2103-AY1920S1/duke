package duke.util;

import org.junit.jupiter.api.Test;

import static duke.util.ObjectsForTest.DEADLINE1;
import static duke.util.ObjectsForTest.DEADLINE2;
import static duke.util.ObjectsForTest.EVENT;
import static duke.util.ObjectsForTest.TASK_LIST_ALL;
import static duke.util.ObjectsForTest.TODO1;
import static duke.util.ObjectsForTest.TODO2;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TaskListTest {

    @Test
    void contains_taskInList_success() {
        assertTrue(TASK_LIST_ALL.contains(TODO1));
        assertTrue(TASK_LIST_ALL.contains(DEADLINE2));
        assertTrue(TASK_LIST_ALL.contains(EVENT));
    }

    @Test
    void contains_taskNotInList_success() {
        assertFalse(TASK_LIST_ALL.contains(TODO2));
        assertFalse(TASK_LIST_ALL.contains(DEADLINE1));
    }
}