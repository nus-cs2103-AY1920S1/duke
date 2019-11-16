import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Represent a Test class for Todo class.
 * The 'TodoTest' class supports the following operators
 * (i) Checking that a todo object can be instantiated correctly
 * with only the description of task,
 * (ii) Checking that a todo object can be instantiated correctly
 * with the description of task, and whether it is done
 * (iii) Checking that the string representation of each
 * object is correct.
 */
public class TodoTest {

    /**
     * Tests that a Todo object can be instantiated correctly
     * with only the description in its constructor.
     * Asserts that a Todo object is created.
     */
    @Test
    public void instantiate_instantiateWithDescription_todoObject() {
        Todo todo = new Todo("read book");
        assertTrue(todo instanceof Todo);
    }

    /**
     * Tests that a Todo object can be instantiated correctly
     * with string representation of whether a task is done, and
     * the description of task in its constructor.
     * Asserts that a Todo object is created.
     */
    @Test
    public void instatiate_instantiateWithIsDone_todoObject() {
        Todo todo = new Todo("1", "read book");
        assertTrue(todo instanceof Todo);
    }


    /**
     * Asserts that the string representation of Todo object is correct.
     * The Todo object created uses a String description of task
     * as its parameter in constructor.
     */
    @Test
    public void testToString_objectWithDescription_string() {
        assertEquals("[T][x] read book\n", new Todo("read book").toString());
    }

    /**
     * Asserts that the string representation of Todo object is correct.
     * The Todo object created uses a String description of task
     * and string representation of whether a task is done
     * as its parameter in constructor.
     * The Todo object is marked as done.
     */
    @Test
    public void testToString_objectWithIsDone_string() {
        assertEquals("[T][v] read book\n", new Todo("0", "read book").toString());
    }

    /**
     * Asserts that the string representation of Todo object is correct.
     * The Todo object created uses a String description of task
     * and string representation of whether a task is done
     * as its parameter in constructor.
     * The Todo object is marked as not done.
     */
    @Test
    public void testToString_objectWithIsNotDone_string() {
        assertEquals("[T][x] read book\n", new Todo("1", "read book").toString());
    }
}