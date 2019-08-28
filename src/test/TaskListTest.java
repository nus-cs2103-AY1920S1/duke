import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    TaskList taskListTest = new TaskList();
    @Test
    void add() {
        try {
            taskListTest.add(new Todo("todo abc"));
            taskListTest.add(new Deadline("deadline abc", "9/12/2019 0000"));
            taskListTest.add(new Event("event abc", "9/12/2019 0000"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void size() {
        try {
            taskListTest.add(new Todo("todo abc"));
            taskListTest.add(new Deadline("deadline abc", "9/12/2019 0000"));
            taskListTest.add(new Event("event abc", "9/12/2019 0000"));
            if (taskListTest.size() != 3) {
                fail();
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

//    @Test
//    void get() {
//        try {
//            taskListTest.add(new Todo("todo abc"));
//            taskListTest.add(new Deadline("deadline abc", "9/12/2019 0000"));
//            taskListTest.add(new Event("event abc", "9/12/2019 0000"));
//        } catch (Exception e) {
//            fail(e.getMessage());
//        }
//    }

    @Test
    void remove() {
        try {
            taskListTest.add(new Todo("todo abc"));
            taskListTest.add(new Deadline("deadline abc", "9/12/2019 0000"));
            taskListTest.add(new Event("event abc", "9/12/2019 0000"));
            taskListTest.remove(0);
            taskListTest.remove(0);
            taskListTest.remove(0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}